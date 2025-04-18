package ${package}.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.soeasyeasy.db.core.BaseServiceImpl;
import com.soeasyeasy.common.entity.PageParam;
import ${package}.convertor.${table.className}Converter;
import ${package}.entity.${table.className}Entity;
import ${package}.entity.dto.${table.className}DTO;
import ${package}.entity.param.${table.className}Req;
import ${package}.mapper.${table.className}Mapper;
import ${package}.service.${table.className}Service;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import java.time.LocalDateTime;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ${table.className}ServiceImpl extends BaseServiceImpl<${table.className}Mapper, ${table.className}Entity,${table.className}DTO> implements ${table.className}Service {
    private final ${table.className}Converter ${table.variableName}Converter;

    @Override
    public QueryWrapper <${table.className}Entity> buildQueryWrapper(PageParam<?> pageParam) {
        if (pageParam instanceof ${table.className}Req req) {
            ${table.className}Entity entity = ${table.variableName}Converter.reqToDO(req);
        QueryWrapper<${table.className}Entity> queryWrapper = new QueryWrapper<>();
            queryWrapper.lambda()
                <#list table.columns as column>
                <#if column.queryCondition>
                    <#if column.javaType == "String">
                        .eq(StringUtils.isNotBlank(entity.get${column.javaField?cap_first}()),${table.className}Entity::get${column.javaField?cap_first},entity.get${column.javaField?cap_first}())
                    <#elseif column.javaType == "LocalDateTime">
                        // 时间范围处理（约定请求参数后缀为Start/End）
                        .ge(Objects.nonNull(req.get${column.javaField?cap_first}Start()),${table.className}Entity::get${column.javaField?cap_first},req.get${column.javaField?cap_first}Start())
                        .le(Objects.nonNull(req.get${column.javaField?cap_first}End()),${table.className}Entity::get${column.javaField?cap_first},req.get${column.javaField?cap_first}End())
                    <#else>
                        .eq(Objects.nonNull(entity.get${column.javaField?cap_first}()),${table.className}Entity::get${column.javaField?cap_first},entity.get${column.javaField?cap_first}())
                    </#if><#if column_has_next></#if>
                </#if>
                </#list>;
            return queryWrapper;
        }
        return super.buildQueryWrapper(pageParam);
    }
}