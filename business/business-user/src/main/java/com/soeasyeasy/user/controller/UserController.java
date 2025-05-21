package com.soeasyeasy.user.controller;

import com.soeasyeasy.common.entity.PageResult;
import com.soeasyeasy.user.entity.dto.UserDTO;
import com.soeasyeasy.user.entity.param.UserReq;
import com.soeasyeasy.user.service.UserService;
import com.soeasyeasy.user.convertor.UserConverter;
import com.soeasyeasy.user.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return userService.list(userReq,userConverter);
    }

    /**
    * 分页查询
    *
    * @param userReq 入参
    * @return {@link PageResult }<{@link UserDTO }> 分页数据
    */
    @PostMapping("/page")
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
     * @param userReq 入参
     * @return Boolean 修改成功返回true
     */
    @PostMapping("/update")
    public Boolean update(@RequestBody UserReq userReq) {
        return userService.updateById(userConverter.reqToDO(userReq));
    }

    /**
     * 删除
     * @param id 主键
     * @return Boolean 删除成功返回true
     */
    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable String id) {
        return userService.removeById(id);
    }
}