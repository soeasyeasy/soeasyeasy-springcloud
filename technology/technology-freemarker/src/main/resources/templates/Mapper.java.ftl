package ${package}.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import ${package}.entity.${table.className};
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ${table.className}Mapper extends BaseMapper<${table.className}> {

}