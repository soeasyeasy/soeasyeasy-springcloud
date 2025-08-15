package com.soeasyeasy.es;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.ElasticsearchException;
import co.elastic.clients.elasticsearch._types.FieldValue;
import co.elastic.clients.elasticsearch._types.SortOptions;
import co.elastic.clients.elasticsearch._types.SortOrder;
import co.elastic.clients.elasticsearch._types.query_dsl.BoolQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch._types.query_dsl.RangeQuery;
import co.elastic.clients.elasticsearch.core.*;
import co.elastic.clients.elasticsearch.core.bulk.BulkOperation;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.elasticsearch.core.search.TotalHits;
import co.elastic.clients.elasticsearch.indices.CreateIndexResponse;
import co.elastic.clients.elasticsearch.indices.DeleteIndexResponse;
import co.elastic.clients.elasticsearch.indices.GetIndexResponse;
import co.elastic.clients.json.JsonData;
import com.soeasyeasy.common.entity.PageResult;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Elasticsearch 工具类 (基于 Spring Boot 3.x / Spring Data Elasticsearch 5.x / Elasticsearch Java API Client)
 * 封装了常见的索引管理、文档增删改查、搜索等操作。
 */
@Slf4j
@Component // 使其成为 Spring 管理的 Bean
public class ElasticsearchUtil {

    // Spring Data Elasticsearch 5.x 的核心客户端
    @Resource
    private ElasticsearchClient elasticsearchClient;

    // ===================== 索引操作 =====================

    /**
     * 创建索引
     *
     * @param indexName 索引名称
     * @return 是否创建成功
     */
    public boolean createIndex(String indexName) {
        try {
            CreateIndexResponse response = elasticsearchClient.indices().create(c -> c.index(indexName));
            return response.acknowledged();
        } catch (ElasticsearchException e) {
            if (e.getMessage().contains("resource_already_exists_exception")) {
                log.warn("索引 [{}] 已存在", indexName);
                return true; // 如果已存在，也可以认为是成功（或者根据业务需求返回 false）
            }
            log.error("创建索引 [{}] 失败: {}", indexName, e.getMessage(), e);
            return false;
        } catch (IOException e) {
            log.error("创建索引 [{}] 时发生IO异常: {}", indexName, e.getMessage(), e);
            return false;
        }
    }

    /**
     * 删除索引
     *
     * @param indexName 索引名称
     * @return 是否删除成功
     */
    public boolean deleteIndex(String indexName) {
        try {
            DeleteIndexResponse response = elasticsearchClient.indices().delete(d -> d.index(indexName));
            return response.acknowledged();
        } catch (ElasticsearchException e) {
            log.error("删除索引 [{}] 失败: {}", indexName, e.getMessage(), e);
            return false;
        } catch (IOException e) {
            log.error("删除索引 [{}] 时发生IO异常: {}", indexName, e.getMessage(), e);
            return false;
        }
    }

    /**
     * 检查索引是否存在
     *
     * @param indexName 索引名称
     * @return 是否存在
     */
    public boolean indexExists(String indexName) {
        try {
            GetIndexResponse response = elasticsearchClient.indices().get(g -> g.index(indexName));
            return response.result().containsKey(indexName);
        } catch (ElasticsearchException e) {
            if (e.getMessage().contains("index_not_found_exception")) {
                return false;
            }
            log.error("检查索引 [{}] 是否存在时发生错误: {}", indexName, e.getMessage(), e);
            return false;
        } catch (IOException e) {
            log.error("检查索引 [{}] 是否存在时发生IO异常: {}", indexName, e.getMessage(), e);
            return false;
        }
    }

    // ===================== 文档操作 =====================

    /**
     * 保存或更新单个文档 (如果 ID 存在则更新，不存在则创建)
     *
     * @param indexName 索引名称
     * @param document  文档对象 (POJO)
     * @param id        文档ID (可选，如果为空则由 ES 自动生成)
     * @param <T>       文档类型
     * @return 操作结果 (包含 ID 和版本号)
     */
    public <T> Result saveDocument(String indexName, T document, String id) {
        try {
            IndexResponse response;
            if (StringUtils.hasText(id)) {
                response = elasticsearchClient.index(i -> i
                        .index(indexName)
                        .id(id)
                        .document(document)
                );
            } else {
                response = elasticsearchClient.index(i -> i
                        .index(indexName)
                        .document(document)
                );
            }
            log.debug("保存文档成功，索引: {}, ID: {}, 版本: {}", indexName, response.id(), response.version());
            return new Result(response.id(), response.version(), response.result().jsonValue());
        } catch (Exception e) {
            log.error("保存文档到索引 [{}] 失败: {}", indexName, e.getMessage(), e);
            throw new RuntimeException("保存文档失败", e);
        }
    }

    /**
     * 批量保存文档
     *
     * @param indexName 索引名称
     * @param documents 文档列表
     * @param <T>       文档类型
     * @return 批量操作结果 (包含每个操作的详情)
     */
    public <T> BulkResponse bulkSaveDocuments(String indexName, List<T> documents) {
        try {
            List<BulkOperation> bulkOperations = documents.stream()
                    .map(doc -> BulkOperation.of(b -> b
                            .index(i -> i
                                    .index(indexName)
                                    .document(doc)
                            )
                    ))
                    .collect(Collectors.toList());

            BulkResponse response = elasticsearchClient.bulk(b -> b
                    .index(indexName)
                    .operations(bulkOperations)
            );

            if (response.errors()) {
                log.warn("批量保存文档时出现部分错误，错误数量: {}", response.items().size());
                // 可以遍历 response.items() 获取具体错误信息
            }
            log.debug("批量保存文档完成，总操作数: {}, 错误数: {}", documents.size(), response.items().size());
            return response;
        } catch (Exception e) {
            log.error("批量保存文档到索引 [{}] 失败: {}", indexName, e.getMessage(), e);
            throw new RuntimeException("批量保存文档失败", e);
        }
    }

    /**
     * 根据 ID 获取文档
     *
     * @param indexName 索引名称
     * @param id        文档ID
     * @param clazz     文档对应的 Java 类
     * @param <T>       文档类型
     * @return 文档对象，如果不存在则返回 null
     */
    public <T> T getDocumentById(String indexName, String id, Class<T> clazz) {
        try {
            GetResponse<T> response = elasticsearchClient.get(g -> g
                            .index(indexName)
                            .id(id),
                    clazz
            );

            if (response.found()) {
                log.debug("获取文档成功，索引: {}, ID: {}", indexName, id);
                return response.source();
            } else {
                log.debug("文档不存在，索引: {}, ID: {}", indexName, id);
                return null;
            }
        } catch (ElasticsearchException e) {
            if (e.getMessage().contains("index_not_found_exception")) {
                log.warn("索引 [{}] 不存在", indexName);
            } else if (e.getMessage().contains("document_missing_exception")) {
                log.debug("文档不存在，索引: {}, ID: {}", indexName, id);
            } else {
                log.error("获取文档失败，索引: {}, ID: {}: {}", indexName, id, e.getMessage(), e);
            }
            return null;
        } catch (IOException e) {
            log.error("获取文档时发生IO异常，索引: {}, ID: {}: {}", indexName, id, e.getMessage(), e);
            return null;
        }
    }

    /**
     * 根据 ID 删除文档
     *
     * @param indexName 索引名称
     * @param id        文档ID
     * @return 删除结果 (包含 ID 和版本号)
     */
    public Result deleteDocumentById(String indexName, String id) {
        try {
            DeleteResponse response = elasticsearchClient.delete(d -> d
                    .index(indexName)
                    .id(id)
            );
            log.debug("删除文档成功，索引: {}, ID: {}, 版本: {}", indexName, response.id(), response.version());
            return new Result(response.id(), response.version(), response.result().jsonValue());
        } catch (ElasticsearchException e) {
            if (e.getMessage().contains("document_missing_exception")) {
                log.warn("要删除的文档不存在，索引: {}, ID: {}", indexName, id);
                // 可以选择抛出异常或返回 null，这里返回 null 表示未删除
                return null;
            }
            log.error("删除文档失败，索引: {}, ID: {}: {}", indexName, id, e.getMessage(), e);
            throw new RuntimeException("删除文档失败", e);
        } catch (IOException e) {
            log.error("删除文档时发生IO异常，索引: {}, ID: {}: {}", indexName, id, e.getMessage(), e);
            throw new RuntimeException("删除文档失败", e);
        }
    }

    // ===================== 搜索操作 =====================

    /**
     * 根据关键词进行全文搜索 (匹配所有字段)
     *
     * @param indexName 索引名称
     * @param keyword   搜索关键词
     * @param clazz     返回结果的 Java 类
     * @param from      起始位置 (分页)
     * @param size      返回数量 (分页)
     * @param <T>       结果类型
     * @return 搜索结果列表
     */
    public <T> SearchResponse<T> searchByKeyword(String indexName, String keyword, Class<T> clazz, int from, int size) {
        try {
            Query query;
            if (keyword == null || keyword.trim().isEmpty()) {
                // ✅ 空关键词：查询所有文档
                query = Query.of(q -> q.matchAll(m -> m));
            } else {
                query = Query.of(q -> q
                        .multiMatch(m -> m
                                .query(keyword)
                                .fields("*") // 匹配所有字段，可根据需要指定具体字段
                        )
                );
            }

            SearchResponse<T> response = elasticsearchClient.search(s -> s
                            .index(indexName)
                            .query(query)
                            .from(from)
                            .size(size),
                    clazz
            );

            log.debug("关键词搜索完成，索引: {}, 关键词: '{}', 命中总数: {}", indexName, keyword, response.hits().total().value());
            return response;
        } catch (Exception e) {
            log.error("关键词搜索失败，索引: {}, 关键词: '{}': {}", indexName, keyword, e.getMessage(), e);
            throw new RuntimeException("搜索失败", e);
        }
    }

    /**
     * 根据字段进行精确匹配搜索 (Term Query)
     *
     * @param indexName  索引名称
     * @param fieldName  字段名
     * @param fieldValue 字段值
     * @param clazz      返回结果的 Java 类
     * @param from       起始位置 (分页)
     * @param size       返回数量 (分页)
     * @param <T>        结果类型
     * @return 搜索结果列表
     */
    public <T> SearchResponse<T> searchByTerm(String indexName, String fieldName, Object fieldValue, Class<T> clazz, int from, int size) {
        try {
            FieldValue termValue = getFieldValue(fieldValue);
            Query query = Query.of(q -> q
                    .term(t -> t
                            .field(fieldName)
                            .value(termValue)
                    )
            );

            SearchResponse<T> response = elasticsearchClient.search(s -> s
                            .index(indexName)
                            .query(query)
                            .from(from)
                            .size(size),
                    clazz
            );

            log.debug("精确匹配搜索完成，索引: {}, 字段: '{}', 值: '{}', 命中总数: {}", indexName, fieldName, fieldValue, response.hits().total().value());
            return response;
        } catch (Exception e) {
            log.error("精确匹配搜索失败，索引: {}, 字段: '{}', 值: '{}': {}", indexName, fieldName, fieldValue, e.getMessage(), e);
            throw new RuntimeException("搜索失败", e);
        }
    }

    private static FieldValue getFieldValue(Object fieldValue) {
        FieldValue termValue;
        if (fieldValue == null) {
            termValue = FieldValue.of((String) null);
        } else if (fieldValue instanceof String str) {
            termValue = FieldValue.of(str);
        } else if (fieldValue instanceof Integer i) {
            termValue = FieldValue.of(i);
        } else if (fieldValue instanceof Long l) {
            termValue = FieldValue.of(l);
        } else if (fieldValue instanceof Double d) {
            termValue = FieldValue.of(d);
        } else if (fieldValue instanceof Float f) {
            termValue = FieldValue.of(f.doubleValue());
        } else if (fieldValue instanceof Boolean b) {
            termValue = FieldValue.of(b);
        } else if (fieldValue instanceof Enum<?> e) {
            termValue = FieldValue.of(e.name());
        } else {
            // 对于复杂对象（如 POJO、LocalDateTime 等），尝试序列化为 JsonData 再转 FieldValue（某些版本支持）
            // ⚠️ 注意：不是所有版本都支持 .of(JsonData)
            // 更安全的方式：转换为字符串（如日期格式化）
            termValue = FieldValue.of(fieldValue.toString());
            // 或者：throw new IllegalArgumentException("不支持的 term 值类型: " + fieldValue.getClass());
        }
        return termValue;
    }

    /**
     * 根据字段进行范围搜索 (Range Query)
     *
     * @param indexName   索引名称
     * @param fieldName   字段名
     * @param from        起始值 (可为 null)
     * @param to          结束值 (可为 null)
     * @param includeFrom 是否包含起始值 (默认 true)
     * @param includeTo   是否包含结束值 (默认 true)
     * @param clazz       返回结果的 Java 类
     * @param pageFrom    起始位置 (分页)
     * @param pageSize    返回数量 (分页)
     * @param <T>         结果类型
     * @return 搜索结果列表
     */
    public <T> SearchResponse<T> searchByRange(String indexName, String fieldName,
                                               Object from, Object to,
                                               boolean includeFrom, boolean includeTo,
                                               Class<T> clazz, int pageFrom, int pageSize) {
        try {
            RangeQuery rangeQuery = RangeQuery.of(r -> {
                r.field(fieldName);
                if (from != null) {
                    if (includeFrom) {
                        r.gte(JsonData.of(from));
                    } else {
                        r.gt(JsonData.of(from));
                    }
                }
                if (to != null) {
                    if (includeTo) {
                        r.lte(JsonData.of(to));
                    } else {
                        r.lt(JsonData.of(to));
                    }
                }
                return r;
            });

            Query query = Query.of(q -> q.range(rangeQuery));

            SearchResponse<T> response = elasticsearchClient.search(s -> s
                            .index(indexName)
                            .query(query)
                            .from(pageFrom)
                            .size(pageSize),
                    clazz
            );

            log.debug("范围搜索完成，索引: {}, 字段: '{}', 范围: [{}{}, {}{}], 命中总数: {}",
                    indexName, fieldName,
                    includeFrom ? "[" : "(", from,
                    to, includeTo ? "]" : ")",
                    response.hits().total().value());
            return response;
        } catch (Exception e) {
            log.error("范围搜索失败，索引: {}, 字段: '{}', 范围: [{}{}, {}{}]: {}",
                    indexName, fieldName,
                    includeFrom ? "[" : "(", from,
                    to, includeTo ? "]" : ")",
                    e.getMessage(), e);
            throw new RuntimeException("搜索失败", e);
        }
    }

    /**
     * 复合查询示例 (Bool Query)
     * 构建更复杂的查询逻辑 (AND, OR, NOT)
     *
     * @param indexName      索引名称
     * @param mustQueries    必须匹配的查询列表
     * @param shouldQueries  至少匹配一个的查询列表
     * @param mustNotQueries 必须不匹配的查询列表
     * @param clazz          返回结果的 Java 类
     * @param from           起始位置 (分页)
     * @param size           返回数量 (分页)
     * @param <T>            结果类型
     * @return 搜索结果列表
     */
    public <T> SearchResponse<T> searchByBoolQuery(String indexName,
                                                   List<Query> mustQueries,
                                                   List<Query> shouldQueries,
                                                   List<Query> mustNotQueries,
                                                   Class<T> clazz, int from, int size) {
        try {
            BoolQuery.Builder boolBuilder = new BoolQuery.Builder();

            if (!CollectionUtils.isEmpty(mustQueries)) {
                boolBuilder.must(mustQueries);
            }
            if (!CollectionUtils.isEmpty(shouldQueries)) {
                boolBuilder.should(shouldQueries);
                // 如果有 should，通常需要设置 minimum_should_match
                boolBuilder.minimumShouldMatch("1");
            }
            if (!CollectionUtils.isEmpty(mustNotQueries)) {
                boolBuilder.mustNot(mustNotQueries);
            }

            Query query = Query.of(q -> q.bool(boolBuilder.build()));

            SearchResponse<T> response = elasticsearchClient.search(s -> s
                            .index(indexName)
                            .query(query)
                            .from(from)
                            .size(size),
                    clazz
            );

            log.debug("复合查询搜索完成，索引: {}, must: {}, should: {}, mustNot: {}, 命中总数: {}",
                    indexName,
                    mustQueries != null ? mustQueries.size() : 0,
                    shouldQueries != null ? shouldQueries.size() : 0,
                    mustNotQueries != null ? mustNotQueries.size() : 0,
                    response.hits().total().value());
            return response;
        } catch (Exception e) {
            log.error("复合查询搜索失败，索引: {}: {}", indexName, e.getMessage(), e);
            throw new RuntimeException("搜索失败", e);
        }
    }

    /**
     * 根据 ID 列表进行搜索
     *
     * @param indexName 索引名称
     * @param ids       ID 列表
     * @param clazz     返回结果的 Java 类
     * @param from      起始位置 (分页)
     * @param size      返回数量 (分页)
     * @param <T>       结果类型
     * @return 搜索结果列表
     */
    public <T> SearchResponse<T> searchByIds(String indexName, List<String> ids, Class<T> clazz, int from, int size) {
        try {
            Query query = Query.of(q -> q.ids(i -> i.values(ids)));
            SearchResponse<T> response = elasticsearchClient.search(s -> s
                            .index(indexName)
                            .query(query)
                            .from(from)
                            .size(size),
                    clazz
            );
            log.debug("ID列表搜索完成，索引: {}, ID数量: {}, 命中总数: {}", indexName, ids.size(), response.hits().total().value());
            return response;
        } catch (Exception e) {
            log.error("ID列表搜索失败，索引: {}, IDs: {}: {}", indexName, ids, e.getMessage(), e);
            throw new RuntimeException("搜索失败", e);
        }
    }

    /**
     * 对搜索结果进行排序
     *
     * @param indexName 索引名称
     * @param query     查询条件
     * @param sortField 排序字段
     * @param sortOrder 排序方向 (asc/desc)
     * @param clazz     返回结果的 Java 类
     * @param from      起始位置 (分页)
     * @param size      返回数量 (分页)
     * @param <T>       结果类型
     * @return 搜索结果列表
     */
    public <T> SearchResponse<T> searchWithSort(String indexName, Query query,
                                                String sortField, SortOrder sortOrder,
                                                Class<T> clazz, int from, int size) {
        try {
            SortOptions sort = SortOptions.of(s -> s
                    .field(f -> f
                            .field(sortField)
                            .order(sortOrder)
                    )
            );

            SearchResponse<T> response = elasticsearchClient.search(s -> s
                            .index(indexName)
                            .query(query)
                            .sort(sort)
                            .from(from)
                            .size(size),
                    clazz
            );

            log.debug("排序搜索完成，索引: {}, 排序字段: '{} {}', 命中总数: {}", indexName, sortField, sortOrder, response.hits().total().value());
            return response;
        } catch (Exception e) {
            log.error("排序搜索失败，索引: {}, 排序字段: '{} {}': {}", indexName, sortField, sortOrder, e.getMessage(), e);
            throw new RuntimeException("搜索失败", e);
        }
    }

    // ===================== 内部类 =====================

    /**
     * 用于封装文档操作（保存、删除）的结果
     */
    public static class Result {
        private final String id;
        private final long version;
        private final String result;

        public Result(String id, long version, String result) {
            this.id = id;
            this.version = version;
            this.result = result;
        }

        // Getters
        public String getId() {
            return id;
        }

        public long getVersion() {
            return version;
        }

        public String getResult() {
            return result;
        }

        @Override
        public String toString() {
            return "Result{" +
                    "id='" + id + '\'' +
                    ", version=" + version +
                    ", result='" + result + '\'' +
                    '}';
        }
    }

    // ===================== 工具方法 =====================

    /**
     * 从 SearchResponse 中提取文档列表
     *
     * @param response 搜索响应
     * @param <T>      文档类型
     * @return 文档列表
     */
    public static <T> List<T> extractDocuments(SearchResponse<T> response) {
        if (response == null || response.hits() == null || response.hits().hits() == null) {
            return Collections.emptyList();
        }
        return response.hits().hits().stream()
                .map(Hit::source)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    /**
     * 从 SearchResponse 中获取总命中数
     *
     * @param response 搜索响应
     * @return 总命中数
     */
    public static long getTotalHits(SearchResponse<?> response) {
        if (response == null || response.hits() == null || response.hits().total() == null) {
            return 0;
        }
        TotalHits totalHits = response.hits().total();
        return totalHits.value();
    }

    /**
     * 返回分页
     */
    public static <T> PageResult<T> extractPageResult(SearchResponse<T> response, int pageFrom, int pageSize) {
        List<T> documents = extractDocuments(response);
        long totalHits = getTotalHits(response);
        PageResult<T> pageResult = new PageResult<T>();
        pageResult.setRecords(documents);
        pageResult.setTotal(totalHits);
        pageResult.setCurrent((long) pageFrom);
        pageResult.setSize((long) pageSize);
        return pageResult;
    }
}