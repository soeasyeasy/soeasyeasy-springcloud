package com.soeasyeasy.system.controller;

import com.soeasyeasy.common.entity.PageResult;
import com.soeasyeasy.system.entity.dto.UserOrgRoleDTO;
import com.soeasyeasy.system.entity.param.UserOrgRoleReq;
import com.soeasyeasy.system.service.UserOrgRoleService;
import com.soeasyeasy.system.convertor.UserOrgRoleConverter;
import com.soeasyeasy.system.entity.UserOrgRoleEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
* 用户组织角色关系表控制层
*
* @author system
* @date 2025-06-18 17:03:51
*/
@RestController
@RequestMapping("/user-org-role")
@RequiredArgsConstructor
public class UserOrgRoleController {

    private final UserOrgRoleService userOrgRoleService;
    private final UserOrgRoleConverter userOrgRoleConverter;

    /**
     * 根据id查询
     *
     * @param id 主键
     * @return UserOrgRole
     */
    @GetMapping("/{id}")
    public UserOrgRoleDTO getById(@PathVariable String id) {
        UserOrgRoleEntity entity = userOrgRoleService.getById(id);
        return userOrgRoleConverter.entityToDto(entity);
    }

    /**
     * 查询所有
     *
     * @param userOrgRoleReq 入参
     * @return {@link List }<{@link UserOrgRoleDTO}>
     */
    @PostMapping("/list")
    public List<UserOrgRoleDTO> list(@RequestBody UserOrgRoleReq userOrgRoleReq) {
        return userOrgRoleService.list(userOrgRoleReq,userOrgRoleConverter);
    }

    /**
    * 分页查询
    *
    * @param userOrgRoleReq 入参
    * @return {@link PageResult }<{@link UserOrgRoleDTO }> 分页数据
    */
    @PostMapping("/page")
    public PageResult<UserOrgRoleDTO> page(@RequestBody UserOrgRoleReq userOrgRoleReq) {
        return userOrgRoleService.pageList(userOrgRoleReq, userOrgRoleConverter);
    }

    /**
     * 保存
     *
     * @param userOrgRoleReq 入参
     * @return Boolean 保存成功返回true
     */
    @PostMapping("/save")
    public Boolean save(@RequestBody UserOrgRoleReq userOrgRoleReq) {
        UserOrgRoleEntity entity = userOrgRoleConverter.reqToDO(userOrgRoleReq);
        return userOrgRoleService.save(entity);
    }

    /**
     * 修改
     * @param userOrgRoleReq 入参
     * @return Boolean 修改成功返回true
     */
    @PostMapping("/update")
    public Boolean update(@RequestBody UserOrgRoleReq userOrgRoleReq) {
        return userOrgRoleService.updateById(userOrgRoleConverter.reqToDO(userOrgRoleReq));
    }

    /**
     * 删除
     * @param id 主键
     * @return Boolean 删除成功返回true
     */
    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable String id) {
        return userOrgRoleService.removeById(id);
    }
}