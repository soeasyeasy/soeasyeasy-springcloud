package com.soeasyeasy.test.service;

import com.soeasyeasy.test.pojo.dto.Product;
import com.soeasyeasy.test.repository.es.ProductRepository;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.query.HighlightQuery;
import org.springframework.data.elasticsearch.core.query.highlight.Highlight;
import org.springframework.data.elasticsearch.core.query.highlight.HighlightField;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


/**
 * 产品服务
 *
 * @author hc@date 2025/02/14
 */
@Service
public class ProductService {

    /**
     * 产品存储库
     */
    @Resource
    private ProductRepository productRepository;

    @Resource
    private ElasticsearchOperations elasticsearchOperations;

    /**
     * 保存产品
     *
     * @param product 产品
     * @return {@link Product }
     */// 创建或更新产品
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    /**
     * 按 ID 查找
     *
     * @param id 身份证
     * @return {@link Optional }<{@link Product }>
     */
    public Optional<Product> findById(String id) {
        return productRepository.findById(id);
    }


    /**
     * 按 ids 查找
     *
     * @param ids
     * @return {@link List }<{@link Product }>
     */
    public List<Product> findByIds(List<String> ids) {
        return productRepository.findByIdIn(ids);
    }

    /**
     * 按名称查找
     *
     * @param name 名字
     * @return {@link List }<{@link Product }>
     */
    public List<Product> findByName(String name) {
        return productRepository.findByNameLike(name);
    }

    /**
     * 按描述搜索
     */
    public List<Product> findByDescription(String description) {
        Criteria criteria = new Criteria("description").contains(description);
        HighlightField highlightField = new HighlightField("description");
        Highlight highlight = new Highlight(List.of(highlightField));
        HighlightQuery highlightQuery = new HighlightQuery(highlight, Product.class);
        CriteriaQuery query = new CriteriaQuery(criteria);
        query.setHighlightQuery(highlightQuery);
        SearchHits<?> searchHits = elasticsearchOperations.search(query, Product.class);
        for (SearchHit<?> hit : searchHits) {
            System.out.println(hit.getHighlightFields());
            // 处理高亮结果
        }
        return null;
    }

    /**
     * 查找全部
     *
     * @param pageable 可分页
     * @return {@link Page }<{@link Product }>
     */
    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    /**
     * 删除产品
     *
     * @param id 身份证
     */
    public void deleteProduct(String id) {
        productRepository.deleteById(id);
    }
}