<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${package}.mapper.${table.className}Mapper">
    <resultMap id="BaseResultMap" type="${package}.entity.${table.className}">
        <#list table.columns as column>
            <result column="${column.columnName}" property="${column.javaField}" jdbcType="${column.jdbcType}"/>
        </#list>
    </resultMap>

    <sql id="Base_Column_List">
        <#list table.columns as column>${column.columnName}<#if column_has_next>, </#if></#list>
    </sql>
</mapper>