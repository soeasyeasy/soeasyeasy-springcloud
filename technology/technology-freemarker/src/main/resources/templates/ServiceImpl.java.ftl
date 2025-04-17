package ${package}.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ${package}.convertor.${table.className}Converter;
import com.soeasyeasy.db.core.BaseServiceImpl;
import ${package}.entity.${table.className}Entity;
import ${package}.mapper.${table.className}Mapper;
import ${package}.service.${table.className}Service;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

/**
* ${table.tableComment!''}服务接口实现
* @author ${author!''}
* @date ${.now?string('yyyy-MM-dd HH:mm:ss')}
*/
@Service
@RequiredArgsConstructor
public class ${table.className}ServiceImpl extends BaseServiceImpl<${table.className}Mapper,${table.className}Entity> implements ${table.className}Service {
    private final ${table.className}Converter ${table.variableName}Converter;

}