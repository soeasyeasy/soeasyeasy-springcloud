package ${package}.service;

import com.soeasyeasy.db.core.BaseService;
import ${package}.entity.${table.className}Entity;
import ${package}.entity.dto.${table.className}DTO;

/**
* ${table.tableComment!''}服务接口
* @author ${author!''}
* @date ${.now?string('yyyy-MM-dd HH:mm:ss')}
*/
public interface ${table.className}Service extends BaseService<${table.className}Entity,${table.className}DTO>  {

}