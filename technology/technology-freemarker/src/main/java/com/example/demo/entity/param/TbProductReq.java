package com.example.demo.entity.param;

import lombok.Data;
import jakarta.validation.constraints.*;
        import java.time.LocalDateTime;

/**
* 产品入参
* @author system
* @date 2025-03-31 17:49:39
*/
@Data
public class TbProductReq {
    /**
    * 主键
    */
    private Long id;
    /**
    * 名称
    */
    private String name;
    /**
    * 描述
    */
    private String description;
    /**
    * 价格
    */
    private String price;
    /**
    * 创建时间
    */
    private String createTime;
    /**
    * 创建人
    */
    private String createBy;
    /**
    * 版本
    */
    private Integer version;
    /**
    * 删除标识
    */
    private Integer deleted;
    /**
    * 更新时间
    */
    private String updateTime;
    /**
    * 更新人
    */
    private String updateBy;
    /**
    * 业务id
    */
    private String uuid;
    /**
    * 状态
    */
    private Integer status;
}