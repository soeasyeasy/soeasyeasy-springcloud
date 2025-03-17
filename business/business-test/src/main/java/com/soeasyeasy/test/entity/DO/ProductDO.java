package com.soeasyeasy.test.entity.DO;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.data.annotation.Id;

/**
 * @author hc
 */
@Data
@TableName("tb_product")
public class ProductDO {
    @Id
    private String id;
    private String name;
    private String description;
    private double price;
}
