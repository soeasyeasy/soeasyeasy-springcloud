package ${package}.entity.param;

import lombok.Data;
import jakarta.validation.constraints.*;
<#list table.columns as column>
    <#if column.javaType == "LocalDateTime">
        import java.time.LocalDateTime;
        <#break>
    </#if>
</#list>

/**
* ${table.tableComment!''}入参
* @author ${author!''}
* @date ${.now?string('yyyy-MM-dd HH:mm:ss')}
*/
@Data
public class ${table.className}Req {
<#list table.columns as column>
    /**
    * ${column.columnComment!''}
    */
    <#if column.validations??>
        <#list column.validations as validation>
    @${validation}
        </#list>
    </#if>
    private ${column.reqJavaType} ${column.reqJavaField};
</#list>
}