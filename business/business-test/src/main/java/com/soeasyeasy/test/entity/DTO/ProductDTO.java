package com.soeasyeasy.test.entity.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.annotation.Id;

/**
 * @author hc
 */
@Data
public class ProductDTO {
    @Id
    private String id;
    @NotNull
    private String name;
    private String description;
    private double price;

    private String status;

}
