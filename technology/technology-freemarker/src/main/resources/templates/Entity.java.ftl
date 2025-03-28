package ${package}.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
<#list table.columns as column>
    <#if column.javaType == "LocalDateTime">
        import java.time.LocalDateTime;
        <#break>
    </#if>
</#list>

@Data
@TableName("${table.tableName}")
public class ${table.className} {
<#list table.columns as column>
    /**
    * ${column.columnComment!''}
    */
    <#if column.isPrimaryKey>
        @TableId(value = "${column.columnName}")
    <#else>
        @TableField(value = "${column.columnName}")
    </#if>
    private ${column.javaType} ${column.javaField};
</#list>
}