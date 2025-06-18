package com.soeasyeasy.system.controller;

import com.soeasyeasy.common.entity.PageResult;
import com.soeasyeasy.system.entity.dto.UserOrgDTO;
import com.soeasyeasy.system.entity.param.UserOrgReq;
import com.soeasyeasy.system.service.UserOrgService;
import com.soeasyeasy.system.convertor.UserOrgConverter;
import com.soeasyeasy.system.entity.UserOrgEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
* 用户组织关系表控制层
*
* @author system
* @date 2025-06-18 17:03:51
*/
@RestController
@RequestMapping("/user-org")
@RequiredArgsConstructor
public class UserOrgController {

    private final UserOrgService userOrgService;
    private final UserOrgConverter userOrgConverter;

    /**
     * 根据id查询
     *
     * @param id 主键
     * @return UserOrg
     */
    @GetMapping("/{id}")
    public UserOrgDTO getById(@PathVariable String id) {
        UserOrgEntity entity = userOrgService.getById(id);
        return userOrgConverter.entityToDto(entity);
    }

    /**
     * 查询所有
     *
     * @param userOrgReq 入参
     * @return {@link List }<{@link UserOrgDTO}>
     */
    @PostMapping("/list")
    public List<UserOrgDTO> list(@RequestBody UserOrgReq userOrgReq) {
        return userOrgService.list(userOrgReq,userOrgConverter);
    }

    /**
    * 分页查询
    *
    * @param userOrgReq 入参
    * @return {@link PageResult }<{@link UserOrgDTO }> 分页数据
    */
    @PostMapping("/page")
    public PageResult<UserOrgDTO> page(@RequestBody UserOrgReq userOrgReq) {
        return userOrgService.pageList(userOrgReq, userOrgConverter);
    }

    /**
     * 保存
     *
     * @param userOrgReq 入参
     * @return Boolean 保存成功返回true
     */
    @PostMapping("/save")
    public Boolean save(@RequestBody UserOrgReq userOrgReq) {
        UserOrgEntity entity = userOrgConverter.reqToDO(userOrgReq);
        return userOrgService.save(entity);
    }

    /**
     * 修改
     * @param userOrgReq 入参
     * @return Boolean 修改成功返回true
     */
    @PostMapping("/update")
    public Boolean update(@RequestBody UserOrgReq userOrgReq) {
        return userOrgService.updateById(userOrgConverter.reqToDO(userOrgReq));
    }

    /**
     * 删除
     * @param id 主键
     * @return Boolean 删除成功返回true
     */
    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable String id) {
        return userOrgService.removeById(id);
    }
}