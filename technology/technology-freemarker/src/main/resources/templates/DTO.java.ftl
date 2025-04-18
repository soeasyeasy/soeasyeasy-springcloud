package ${package}.entity.dto;

import lombok.Data;
<#list table.columns as column>
    <#if column.javaType == "LocalDateTime">
        import java.time.LocalDateTime;
        <#break>
    </#if>
</#list>

/**
* ${table.tableComment!''}DTO
* @author ${author!''}
* @date ${.now?string('yyyy-MM-dd HH:mm:ss')}
*/
@Data
public class ${table.className}DTO {
<#list table.columns as column>
    /**
    * ${column.columnComment!''}
    */
    private String ${column.javaField};
</#list>
}