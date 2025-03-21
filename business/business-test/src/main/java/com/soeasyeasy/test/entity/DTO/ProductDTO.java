package com.soeasyeasy.test.entity.DTO;

import lombok.Data;
import org.springframework.data.annotation.Id;

/**
 * @author hc
 */
@Data
public class ProductDTO {
    @Id
    private String id;
    private String name;
    private String description;
    private double price;
    private String status;

}
