package ${package}.entity.param;

import com.soeasyeasy.common.entity.PageParam;
import ${package}.entity.${table.className}Entity;
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
public class ${table.className}Req extends PageParam<${table.className}Entity>{
<#list table.columns as column>
    <#if column.javaType == "LocalDateTime">
        /**
        * ${column.columnComment!''}（起始时间）
        */
        private String ${column.reqJavaField};
        /**
        * ${column.columnComment!''}（起始时间）
        */
        private String ${column.reqJavaField}Start;

        /**
        * ${column.columnComment!''}（结束时间）
        */
        private String ${column.reqJavaField}End;
    <#else>
        /**
        * ${column.columnComment!''}
        */
        <#if column.validations??>
            <#list column.validations as validation>
                @${validation}
            </#list>
        </#if>
        private String ${column.reqJavaField};
    </#if>
</#list>
}