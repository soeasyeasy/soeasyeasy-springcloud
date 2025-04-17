package com.soeasyeasy.user.controller;

import com.soeasyeasy.common.entity.PageResult;
import com.soeasyeasy.user.convertor.UserConverter;
import com.soeasyeasy.user.entity.UserEntity;
import com.soeasyeasy.user.entity.dto.UserDTO;
import com.soeasyeasy.user.entity.param.UserReq;
import com.soeasyeasy.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户控制层
 *
 * @author system
 * @date 2025-04-17 13:36:17
 */
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserConverter userConverter;

    /**
     * 根据id查询
     *
     * @param id
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
     * @return List<User>
     */
    @GetMapping("/list")
    public List<UserDTO> list() {
        return userConverter.entityToDto(userService.list());
    }

    /**
     * 分页查询
     *
     * @return List<User>
     */
    @PostMapping("/page")
    public PageResult<UserDTO> list(@RequestBody UserReq userReq) {
        return userService.pageList(userReq, UserConverter.INSTANCE);
    }

    /**
     * 保存
     *
     * @param userReq
     * @return Boolean
     */
    @PostMapping("/save")
    public Boolean save(@RequestBody UserReq userReq) {
        UserEntity entity = userConverter.reqToDO(userReq);
        return userService.save(entity);
    }

    /**
     * 修改
     *
     * @param userReq
     * @return Boolean
     */
    @PostMapping("/update")
    public Boolean update(@RequestBody UserReq userReq) {
        return userService.updateById(userConverter.reqToDO(userReq));
    }

    /**
     * 删除
     *
     * @param id
     * @return Boolean
     */
    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable String id) {
        return userService.removeById(id);
    }
}