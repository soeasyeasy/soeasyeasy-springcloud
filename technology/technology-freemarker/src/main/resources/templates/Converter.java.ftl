package ${package}.convertor;

import ${package}.entity.param.${table.className}Req;
import ${package}.entity.dto.${table.className}DTO;
import ${package}.entity.${table.className}Entity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.soeasyeasy.common.converter.BaseEntityConverter;

/**
* ${table.tableComment!''}转换层
* @author ${author!''}
* @date ${.now?string('yyyy-MM-dd HH:mm:ss')}
*/
@Mapper(componentModel = "spring")
public interface ${table.className}Converter extends BaseEntityConverter<${table.className}DTO, ${table.className}Entity>{

    ${table.className}Converter INSTANCE = Mappers.getMapper(${table.className}Converter.class);

    ${table.className}Entity reqToDO(${table.className}Req req);

    ${table.className}Req doToReq(${table.className}Entity entity);
}