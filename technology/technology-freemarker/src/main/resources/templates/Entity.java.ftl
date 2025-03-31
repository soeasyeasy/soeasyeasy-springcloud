package ${package}.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
<#list table.columns as column>
    <#if column.javaType == "LocalDateTime">
import java.time.LocalDateTime;
        <#break>
    </#if>
</#list>

/**
 * ${table.tableComment!''}数据库表实体
 * @author ${author!''}
 * @date ${.now?string('yyyy-MM-dd HH:mm:ss')}
 */
@Data
@TableName("${table.tableName}")
public class ${table.className}Entity {
<#list table.columns as column>
    /**
     * ${column.columnComment!''}
     */
    <#if column.isPrimaryKey>
    @TableId(value = "${column.columnName}")
    <#else>
    @TableField(value = "${column.columnName}")
    </#if>
    <#if column.columnName == "deleted">
    @TableLogic
    </#if>
    private ${column.javaType} ${column.javaField};
</#list>
}