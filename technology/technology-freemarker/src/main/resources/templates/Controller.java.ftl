package ${package}.controller;

import com.soeasyeasy.common.entity.PageResult;
import ${package}.entity.dto.${table.className}DTO;
import ${package}.entity.param.${table.className}Req;
import ${package}.service.${table.className}Service;
import ${package}.convertor.${table.className}Converter;
import ${package}.entity.${table.className}Entity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
     *
     * @param id 主键
     * @return ${table.className}
     */
    @GetMapping("/{id}")
    public ${table.className}DTO getById(@PathVariable ${table.pkJavaType} id) {
        ${table.className}Entity entity = ${table.variableName}Service.getById(id);
        return ${table.variableName}Converter.entityToDto(entity);
    }

    /**
     * 查询所有
     *
     * @param ${table.variableName}Req 入参
     * @return {@link List }<{@link ${table.className}DTO}>
     */
    @PostMapping("/list")
    public List<${table.className}DTO> list(@RequestBody ${table.className}Req ${table.variableName}Req) {
        return ${table.variableName}Service.list(${table.variableName}Req,${table.variableName}Converter);
    }

    /**
    * 分页查询
    *
    * @param ${table.variableName}Req 入参
    * @return {@link PageResult }<{@link ${table.className}DTO }> 分页数据
    */
    @PostMapping("/page")
    public PageResult<UserDTO> page(@RequestBody ${table.className}Req ${table.variableName}Req) {
        return ${table.variableName}Service.pageList(${table.variableName}Req, ${table.variableName}Converter);
    }

    /**
     * 保存
     *
     * @param ${table.variableName}Req 入参
     * @return Boolean 保存成功返回true
     */
    @PostMapping("/save")
    public Boolean save(@RequestBody ${table.className}Req ${table.variableName}Req) {
        ${table.className}Entity entity = ${table.variableName}Converter.reqToDO(${table.variableName}Req);
        return ${table.variableName}Service.save(entity);
    }

    /**
     * 修改
     * @param ${table.variableName}Req 入参
     * @return Boolean 修改成功返回true
     */
    @PostMapping("/update")
    public Boolean update(@RequestBody ${table.className}Req ${table.variableName}Req) {
        return ${table.variableName}Service.updateById(${table.variableName}Converter.reqToDO(${table.variableName}Req));
    }

    /**
     * 删除
     * @param id 主键
     * @return Boolean 删除成功返回true
     */
    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable ${table.pkJavaType} id) {
        return ${table.variableName}Service.removeById(id);
    }
}