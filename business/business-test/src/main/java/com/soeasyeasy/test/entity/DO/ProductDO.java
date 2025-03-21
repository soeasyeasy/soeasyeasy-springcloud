package com.soeasyeasy.test.entity.DO;

import com.baomidou.mybatisplus.annotation.TableName;
import com.soeasyeasy.common.entity.BaseEntity;
import com.soeasyeasy.test.enums.StatusEnum;
import lombok.Data;

/**
 * @author hc
 */
@Data
@TableName("tb_product")
public class ProductDO extends BaseEntity {

    private String name;
    private String description;
    private double price;
    private StatusEnum status;
}
