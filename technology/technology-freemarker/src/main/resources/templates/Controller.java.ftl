package ${package}.controller;

import ${package}.entity.dto.${table.className}DTO;
import ${package}.entity.param.${table.className}Req;
import ${package}.service.${table.className}Service;
import ${package}.convertor.${table.className}Converter;
import ${package}.entity.${table.className}Entity;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;
import java.util.List;

/**
* ${table.tableComment!''}控制层
*
* @author ${author!''}
* @date ${.now?string('yyyy-MM-dd HH:mm:ss')}
*/
@RestController
@RequestMapping("/${table.restPath}")
@RequiredArgsConstructor
public class ${table.className}Controller {

    private final ${table.className}Service ${table.variableName}Service;
    private final ${table.className}Converter ${table.variableName}Converter;

    /**
     * 根据id查询
     * @param id
     * @return ${table.className}
     */
    @GetMapping("/{id}")
    public ${table.className}DTO getById(@PathVariable ${table.pkJavaType} id) {
        ${table.className}Entity entity = ${table.variableName}Service.getById(id);
        return ${table.variableName}Converter.entityToDto(entity);
    }

    /**
     * 查询所有
     * @return List<${table.className}>
     */
    @GetMapping("/list")
    public List<${table.className}DTO> list() {
        return ${table.variableName}Converter.entityToDto(${table.variableName}Service.list());
    }

    /**
     * 保存
     * @param ${table.variableName}Req
     * @return Boolean
     */
    @PostMapping("/save")
    public Boolean save(@RequestBody ${table.className}Req ${table.variableName}Req) {
        ${table.className}Entity entity = ${table.variableName}Converter.reqToDO(${table.variableName}Req);
        return ${table.variableName}Service.save(entity);
    }

    /**
     * 修改
     * @param ${table.variableName}Req
     * @return Boolean
     */
    @PostMapping("/update")
    public Boolean update(@RequestBody ${table.className}Req ${table.variableName}Req) {
        return ${table.variableName}Service.updateById(${table.variableName}Converter.reqToDO(${table.variableName}Req));
    }

    /**
     * 删除
     * @param id
     * @return Boolean
     */
    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable ${table.pkJavaType} id) {
        return ${table.variableName}Service.removeById(id);
    }
}