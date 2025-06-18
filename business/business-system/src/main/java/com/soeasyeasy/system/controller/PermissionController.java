package com.soeasyeasy.system.controller;

import com.soeasyeasy.common.entity.PageResult;
import com.soeasyeasy.system.convertor.PermissionConverter;
import com.soeasyeasy.system.entity.PermissionEntity;
import com.soeasyeasy.system.entity.dto.PermissionDTO;
import com.soeasyeasy.system.entity.param.PermissionReq;
import com.soeasyeasy.system.service.PermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * 权限表控制层
 *
 * @author system
 * @date 2025-06-18 17:03:51
 */
@RestController
@RequestMapping("/permission")
@RequiredArgsConstructor
public class PermissionController {

    private final PermissionService permissionService;
    private final PermissionConverter permissionConverter;

    /**
     * 根据id查询
     *
     * @param id 主键
     * @return Permission
     */
    @GetMapping("/{id}")
    public PermissionDTO getById(@PathVariable String id) {
        PermissionEntity entity = permissionService.getById(id);
        return permissionConverter.entityToDto(entity);
    }

    /**
     * 查询所有
     *
     * @param permissionReq 入参
     * @return {@link List }<{@link PermissionDTO}>
     */
    @PostMapping("/list")
    public List<PermissionDTO> list(@RequestBody PermissionReq permissionReq) {
        return permissionService.list(permissionReq, permissionConverter);
    }

    /**
     * 分页查询
     *
     * @param permissionReq 入参
     * @return {@link PageResult }<{@link PermissionDTO }> 分页数据
     */
    @PostMapping("/page")
    public PageResult<PermissionDTO> page(@RequestBody PermissionReq permissionReq) {
        return permissionService.pageList(permissionReq, permissionConverter);
    }

    /**
     * 保存
     *
     * @param permissionReq 入参
     * @return Boolean 保存成功返回true
     */
    @PostMapping("/save")
    public Boolean save(@RequestBody PermissionReq permissionReq) {
        PermissionEntity entity = permissionConverter.reqToDO(permissionReq);
        return permissionService.save(entity);
    }

    /**
     * 修改
     *
     * @param permissionReq 入参
     * @return Boolean 修改成功返回true
     */
    @PostMapping("/update")
    public Boolean update(@RequestBody PermissionReq permissionReq) {
        PermissionEntity permissionEntity = permissionConverter.reqToDO(permissionReq);
        if (Objects.isNull(permissionEntity.getId())) {
            return permissionService.save(permissionEntity);
        }
        return permissionService.updateById(permissionEntity);
    }

    /**
     * 删除
     *
     * @param id 主键
     * @return Boolean 删除成功返回true
     */
    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable String id) {
        return permissionService.removeById(id);
    }
}