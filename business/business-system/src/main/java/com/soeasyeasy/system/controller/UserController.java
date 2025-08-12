package com.soeasyeasy.system.controller;

import com.soeasyeasy.common.entity.PageResult;
import com.soeasyeasy.system.convertor.UserConverter;
import com.soeasyeasy.system.entity.UserEntity;
import com.soeasyeasy.system.entity.dto.UserDTO;
import com.soeasyeasy.system.entity.param.UserReq;
import com.soeasyeasy.system.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * 用户控制层
 *
 * @author system
 * @date 2025-04-18 13:41:41
 */
@RestController
@RequestMapping("/userinfo")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserConverter userConverter;

    /**
     * 根据id查询
     *
     * @param id 主键
     * @return User
     */
    @GetMapping("/{id}")
    public UserDTO getById(@PathVariable String id) {
        UserEntity entity = userService.getById(id);
        return userConverter.entityToDto(entity);
    }

    /**
     * 查询所有
     *
     * @param userReq 入参
     * @return {@link List }<{@link UserDTO}>
     */
    @PostMapping("/list")
    public List<UserDTO> list(@RequestBody UserReq userReq) {
        return userService.list(userReq, userConverter);
    }

    /**
     * 分页查询
     *
     * @param userReq 入参
     * @return {@link PageResult }<{@link UserDTO }> 分页数据
     */
    @PostMapping("/page")
    @PreAuthorize("hasAuthority('user:page')")
    //@PostFilter("filterObject.records.name== authentication.name")
    public PageResult<UserDTO> page(@RequestBody UserReq userReq) {
        return userService.pageList(userReq, userConverter);
    }

    /**
     * 保存
     *
     * @param userReq 入参
     * @return Boolean 保存成功返回true
     */
    @PostMapping("/save")
    public Boolean save(@RequestBody UserReq userReq) {
        UserEntity entity = userConverter.reqToDO(userReq);
        return userService.save(entity);
    }

    /**
     * 修改
     *
     * @param userReq 入参
     * @return Boolean 修改成功返回true
     */
    @PostMapping("/update")
    public Boolean update(@RequestBody UserReq userReq) {
        UserEntity userEntity = userConverter.reqToDO(userReq);
        if (Objects.isNull(userReq.getId())) {
            return userService.save(userEntity);
        }
        return userService.updateById(userEntity);
    }

    /**
     * 删除
     *
     * @param id 主键
     * @return Boolean 删除成功返回true
     */
    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable String id) {
        return userService.removeById(id);
    }
}