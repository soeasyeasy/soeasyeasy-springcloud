package com.soeasyeasy.system.controller;

import com.soeasyeasy.common.entity.PageResult;
import com.soeasyeasy.system.entity.dto.RolePermissionDTO;
import com.soeasyeasy.system.entity.param.RolePermissionReq;
import com.soeasyeasy.system.service.RolePermissionService;
import com.soeasyeasy.system.convertor.RolePermissionConverter;
import com.soeasyeasy.system.entity.RolePermissionEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
* 角色权限关系表控制层
*
* @author system
* @date 2025-06-18 17:03:51
*/
@RestController
@RequestMapping("/role-permission")
@RequiredArgsConstructor
public class RolePermissionController {

    private final RolePermissionService rolePermissionService;
    private final RolePermissionConverter rolePermissionConverter;

    /**
     * 根据id查询
     *
     * @param id 主键
     * @return RolePermission
     */
    @GetMapping("/{id}")
    public RolePermissionDTO getById(@PathVariable String id) {
        RolePermissionEntity entity = rolePermissionService.getById(id);
        return rolePermissionConverter.entityToDto(entity);
    }

    /**
     * 查询所有
     *
     * @param rolePermissionReq 入参
     * @return {@link List }<{@link RolePermissionDTO}>
     */
    @PostMapping("/list")
    public List<RolePermissionDTO> list(@RequestBody RolePermissionReq rolePermissionReq) {
        return rolePermissionService.list(rolePermissionReq,rolePermissionConverter);
    }

    /**
    * 分页查询
    *
    * @param rolePermissionReq 入参
    * @return {@link PageResult }<{@link RolePermissionDTO }> 分页数据
    */
    @PostMapping("/page")
    public PageResult<RolePermissionDTO> page(@RequestBody RolePermissionReq rolePermissionReq) {
        return rolePermissionService.pageList(rolePermissionReq, rolePermissionConverter);
    }

    /**
     * 保存
     *
     * @param rolePermissionReq 入参
     * @return Boolean 保存成功返回true
     */
    @PostMapping("/save")
    public Boolean save(@RequestBody RolePermissionReq rolePermissionReq) {
        RolePermissionEntity entity = rolePermissionConverter.reqToDO(rolePermissionReq);
        return rolePermissionService.save(entity);
    }

    /**
     * 修改
     * @param rolePermissionReq 入参
     * @return Boolean 修改成功返回true
     */
    @PostMapping("/update")
    public Boolean update(@RequestBody RolePermissionReq rolePermissionReq) {
        return rolePermissionService.updateById(rolePermissionConverter.reqToDO(rolePermissionReq));
    }

    /**
     * 删除
     * @param id 主键
     * @return Boolean 删除成功返回true
     */
    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable String id) {
        return rolePermissionService.removeById(id);
    }
}