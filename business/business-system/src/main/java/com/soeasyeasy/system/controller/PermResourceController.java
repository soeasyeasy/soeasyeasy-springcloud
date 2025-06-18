package com.soeasyeasy.system.controller;

import com.soeasyeasy.common.entity.PageResult;
import com.soeasyeasy.system.entity.dto.PermResourceDTO;
import com.soeasyeasy.system.entity.param.PermResourceReq;
import com.soeasyeasy.system.service.PermResourceService;
import com.soeasyeasy.system.convertor.PermResourceConverter;
import com.soeasyeasy.system.entity.PermResourceEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
* 权限资源关系表控制层
*
* @author system
* @date 2025-06-18 17:03:51
*/
@RestController
@RequestMapping("/perm-resource")
@RequiredArgsConstructor
public class PermResourceController {

    private final PermResourceService permResourceService;
    private final PermResourceConverter permResourceConverter;

    /**
     * 根据id查询
     *
     * @param id 主键
     * @return PermResource
     */
    @GetMapping("/{id}")
    public PermResourceDTO getById(@PathVariable String id) {
        PermResourceEntity entity = permResourceService.getById(id);
        return permResourceConverter.entityToDto(entity);
    }

    /**
     * 查询所有
     *
     * @param permResourceReq 入参
     * @return {@link List }<{@link PermResourceDTO}>
     */
    @PostMapping("/list")
    public List<PermResourceDTO> list(@RequestBody PermResourceReq permResourceReq) {
        return permResourceService.list(permResourceReq,permResourceConverter);
    }

    /**
    * 分页查询
    *
    * @param permResourceReq 入参
    * @return {@link PageResult }<{@link PermResourceDTO }> 分页数据
    */
    @PostMapping("/page")
    public PageResult<PermResourceDTO> page(@RequestBody PermResourceReq permResourceReq) {
        return permResourceService.pageList(permResourceReq, permResourceConverter);
    }

    /**
     * 保存
     *
     * @param permResourceReq 入参
     * @return Boolean 保存成功返回true
     */
    @PostMapping("/save")
    public Boolean save(@RequestBody PermResourceReq permResourceReq) {
        PermResourceEntity entity = permResourceConverter.reqToDO(permResourceReq);
        return permResourceService.save(entity);
    }

    /**
     * 修改
     * @param permResourceReq 入参
     * @return Boolean 修改成功返回true
     */
    @PostMapping("/update")
    public Boolean update(@RequestBody PermResourceReq permResourceReq) {
        return permResourceService.updateById(permResourceConverter.reqToDO(permResourceReq));
    }

    /**
     * 删除
     * @param id 主键
     * @return Boolean 删除成功返回true
     */
    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable String id) {
        return permResourceService.removeById(id);
    }
}