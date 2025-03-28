package ${package}.controller;

import ${package}.entity.${table.className};
import ${package}.service.${table.className}Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/${table.restPath}")
public class ${table.className}Controller {
@Resource
private ${table.className}Service ${table.variableName}Service;

@GetMapping("/{id}")
public ${table.className} getById(@PathVariable ${table.pkJavaType} id) {
return ${table.variableName}Service.getById(id);
}

@GetMapping("/list")
public List<${table.className}> list() {
return ${table.variableName}Service.list();
}

@PostMapping("/save")
public Boolean save(@RequestBody ${table.className} ${table.variableName}) {
return ${table.variableName}Service.save(${table.variableName});
}

@PostMapping("/update")
public Boolean update(@RequestBody ${table.className} ${table.variableName}) {
return ${table.variableName}Service.updateById(${table.variableName});
}

@DeleteMapping("/{id}")
public Boolean delete(@PathVariable ${table.pkJavaType} id) {
return ${table.variableName}Service.removeById(id);
}
}