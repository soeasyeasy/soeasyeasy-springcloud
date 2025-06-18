package com.soeasyeasy.system.controller;

import com.soeasyeasy.common.entity.PageResult;
import com.soeasyeasy.system.convertor.RoleConverter;
import com.soeasyeasy.system.entity.RoleEntity;
import com.soeasyeasy.system.entity.dto.RoleDTO;
import com.soeasyeasy.system.entity.param.RoleReq;
import com.soeasyeasy.system.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * 角色表控制层
 *
 * @author system
 * @date 2025-06-18 17:03:51
 */
@RestController
@RequestMapping("/role")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;
    private final RoleConverter roleConverter;

    /**
     * 根据id查询
     *
     * @param id 主键
     * @return Role
     */
    @GetMapping("/{id}")
    public RoleDTO getById(@PathVariable String id) {
        RoleEntity entity = roleService.getById(id);
        return roleConverter.entityToDto(entity);
    }

    /**
     * 查询所有
     *
     * @param roleReq 入参
     * @return {@link List }<{@link RoleDTO}>
     */
    @PostMapping("/list")
    public List<RoleDTO> list(@RequestBody RoleReq roleReq) {
        return roleService.list(roleReq, roleConverter);
    }

    /**
     * 分页查询
     *
     * @param roleReq 入参
     * @return {@link PageResult }<{@link RoleDTO }> 分页数据
     */
    @PostMapping("/page")
    public PageResult<RoleDTO> page(@RequestBody RoleReq roleReq) {
        return roleService.pageList(roleReq, roleConverter);
    }

    /**
     * 保存
     *
     * @param roleReq 入参
     * @return Boolean 保存成功返回true
     */
    @PostMapping("/save")
    public Boolean save(@RequestBody RoleReq roleReq) {
        RoleEntity entity = roleConverter.reqToDO(roleReq);
        return roleService.save(entity);
    }

    /**
     * 修改
     *
     * @param roleReq 入参
     * @return Boolean 修改成功返回true
     */
    @PostMapping("/update")
    public Boolean update(@RequestBody RoleReq roleReq) {
        RoleEntity roleEntity = roleConverter.reqToDO(roleReq);
        if (Objects.isNull(roleEntity.getId())) {
            return roleService.save(roleEntity);
        }
        return roleService.updateById(roleEntity);
    }

    /**
     * 删除
     *
     * @param id 主键
     * @return Boolean 删除成功返回true
     */
    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable String id) {
        return roleService.removeById(id);
    }
}