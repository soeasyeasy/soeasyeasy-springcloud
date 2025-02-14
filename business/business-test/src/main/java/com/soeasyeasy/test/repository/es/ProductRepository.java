package com.soeasyeasy.test.repository.es;

import com.soeasyeasy.test.pojo.dto.Product;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * 产品存储库
 *
 * @author hc
 * @date 2025/02/14
 */
public interface ProductRepository extends ElasticsearchRepository<Product, String> {
    /**
     * 自定义通过name查询
     *
     * @param name
     * @return
     */
    List<Product> findByName(String name);

    /**
     * 根据name模糊查询
     */
    List<Product> findByNameLike(String name);

    /**
     * 根据描述模糊查询
     */
    List<Product> findByDescriptionLike(String description);


    /**
     * 根据id批量查询
     */
    List<Product> findByIdIn(List<String> ids);
}
