package ${package}.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ${package}.entity.${table.className};
import ${package}.mapper.${table.className}Mapper;
import ${package}.service.${table.className}Service;
import org.springframework.stereotype.Service;

@Service
public class ${table.className}ServiceImpl
extends ServiceImpl
<${table.className}Mapper, ${table.className}>
implements ${table.className}Service {

}