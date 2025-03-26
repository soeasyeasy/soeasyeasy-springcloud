package com.soeasyeasy.test.entity.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.annotation.Id;

/**
 * @author hc
 */
@Data
public class ProductDTO {
    /**
     * 主键
     */
    @Id
    private String id;
    /**
     * 名称
     */
    @NotNull
    private String name;
    /**
     * 描述
     */
    private String description;
    /**
     * 价格
     */
    private double price;
    /**
     * 状态
     */
    private String status;

}
