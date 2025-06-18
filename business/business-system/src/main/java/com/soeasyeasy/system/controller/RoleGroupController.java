package com.soeasyeasy.system.controller;

import com.soeasyeasy.common.entity.PageResult;
import com.soeasyeasy.system.convertor.RoleGroupConverter;
import com.soeasyeasy.system.entity.RoleGroupEntity;
import com.soeasyeasy.system.entity.dto.RoleGroupDTO;
import com.soeasyeasy.system.entity.param.RoleGroupReq;
import com.soeasyeasy.system.service.RoleGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 角色组表控制层
 *
 * @author system
 * @date 2025-06-18 17:03:51
 */
@RestController
@RequestMapping("/role-group")
@RequiredArgsConstructor
public class RoleGroupController {

    private final RoleGroupService roleGroupService;
    private final RoleGroupConverter roleGroupConverter;

    /**
     * 根据id查询
     *
     * @param id 主键
     * @return RoleGroup
     */
    @GetMapping("/{id}")
    public RoleGroupDTO getById(@PathVariable String id) {
        RoleGroupEntity entity = roleGroupService.getById(id);
        return roleGroupConverter.entityToDto(entity);
    }

    /**
     * 查询所有
     *
     * @param roleGroupReq 入参
     * @return {@link List }<{@link RoleGroupDTO}>
     */
    @PostMapping("/list")
    public List<RoleGroupDTO> list(@RequestBody RoleGroupReq roleGroupReq) {
        return roleGroupService.list(roleGroupReq, roleGroupConverter);
    }

    /**
     * 分页查询
     *
     * @param roleGroupReq 入参
     * @return {@link PageResult }<{@link RoleGroupDTO }> 分页数据
     */
    @PostMapping("/page")
    public PageResult<RoleGroupDTO> page(@RequestBody RoleGroupReq roleGroupReq) {
        return roleGroupService.pageList(roleGroupReq, roleGroupConverter);
    }

    /**
     * 保存
     *
     * @param roleGroupReq 入参
     * @return Boolean 保存成功返回true
     */
    @PostMapping("/save")
    public Boolean save(@RequestBody RoleGroupReq roleGroupReq) {
        RoleGroupEntity entity = roleGroupConverter.reqToDO(roleGroupReq);
        return roleGroupService.save(entity);
    }

    /**
     * 修改
     *
     * @param roleGroupReq 入参
     * @return Boolean 修改成功返回true
     */
    @PostMapping("/update")
    public Boolean update(@RequestBody RoleGroupReq roleGroupReq) {
        return roleGroupService.updateById(roleGroupConverter.reqToDO(roleGroupReq));
    }

    /**
     * 删除
     *
     * @param id 主键
     * @return Boolean 删除成功返回true
     */
    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable String id) {
        return roleGroupService.removeById(id);
    }
}