package ${package}.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import ${package}.entity.${table.className}Entity;
import org.apache.ibatis.annotations.Mapper;

/**
 * ${table.tableComment!''}Mapper
 * @author ${author}
 * @date ${.now?string('yyyy-MM-dd HH:mm:ss')}
 */
@Mapper
public interface ${table.className}Mapper extends BaseMapper<${table.className}Entity> {

}