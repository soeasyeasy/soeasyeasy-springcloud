package com.soeasyeasy.system.controller;

import com.soeasyeasy.common.entity.PageResult;
import com.soeasyeasy.system.entity.dto.UserRoleDTO;
import com.soeasyeasy.system.entity.param.UserRoleReq;
import com.soeasyeasy.system.service.UserRoleService;
import com.soeasyeasy.system.convertor.UserRoleConverter;
import com.soeasyeasy.system.entity.UserRoleEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
* 用户角色关系表控制层
*
* @author system
* @date 2025-06-18 17:03:51
*/
@RestController
@RequestMapping("/user-role")
@RequiredArgsConstructor
public class UserRoleController {

    private final UserRoleService userRoleService;
    private final UserRoleConverter userRoleConverter;

    /**
     * 根据id查询
     *
     * @param id 主键
     * @return UserRole
     */
    @GetMapping("/{id}")
    public UserRoleDTO getById(@PathVariable String id) {
        UserRoleEntity entity = userRoleService.getById(id);
        return userRoleConverter.entityToDto(entity);
    }

    /**
     * 查询所有
     *
     * @param userRoleReq 入参
     * @return {@link List }<{@link UserRoleDTO}>
     */
    @PostMapping("/list")
    public List<UserRoleDTO> list(@RequestBody UserRoleReq userRoleReq) {
        return userRoleService.list(userRoleReq,userRoleConverter);
    }

    /**
    * 分页查询
    *
    * @param userRoleReq 入参
    * @return {@link PageResult }<{@link UserRoleDTO }> 分页数据
    */
    @PostMapping("/page")
    public PageResult<UserRoleDTO> page(@RequestBody UserRoleReq userRoleReq) {
        return userRoleService.pageList(userRoleReq, userRoleConverter);
    }

    /**
     * 保存
     *
     * @param userRoleReq 入参
     * @return Boolean 保存成功返回true
     */
    @PostMapping("/save")
    public Boolean save(@RequestBody UserRoleReq userRoleReq) {
        UserRoleEntity entity = userRoleConverter.reqToDO(userRoleReq);
        return userRoleService.save(entity);
    }

    /**
     * 修改
     * @param userRoleReq 入参
     * @return Boolean 修改成功返回true
     */
    @PostMapping("/update")
    public Boolean update(@RequestBody UserRoleReq userRoleReq) {
        return userRoleService.updateById(userRoleConverter.reqToDO(userRoleReq));
    }

    /**
     * 删除
     * @param id 主键
     * @return Boolean 删除成功返回true
     */
    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable String id) {
        return userRoleService.removeById(id);
    }
}