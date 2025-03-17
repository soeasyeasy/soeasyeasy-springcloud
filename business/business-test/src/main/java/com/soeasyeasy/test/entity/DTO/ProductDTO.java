package com.soeasyeasy.test.entity.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @author hc
 */
@Data
@Document(indexName = "products")
@AllArgsConstructor
public class ProductDTO {
    @Id
    private String id;
    private String name;
    private String description;
    private double price;
}
