package com.soeasyeasy.system.controller;

import com.soeasyeasy.common.entity.PageResult;
import com.soeasyeasy.system.convertor.OrgConverter;
import com.soeasyeasy.system.entity.OrgEntity;
import com.soeasyeasy.system.entity.dto.OrgDTO;
import com.soeasyeasy.system.entity.param.OrgReq;
import com.soeasyeasy.system.service.OrgService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * 组织架构表控制层
 *
 * @author system
 * @date 2025-06-18 17:03:51
 */
@RestController
@RequestMapping("/org")
@RequiredArgsConstructor
public class OrgController {

    private final OrgService orgService;
    private final OrgConverter orgConverter;

    /**
     * 根据id查询
     *
     * @param id 主键
     * @return Org
     */
    @GetMapping("/{id}")
    public OrgDTO getById(@PathVariable String id) {
        OrgEntity entity = orgService.getById(id);
        return orgConverter.entityToDto(entity);
    }

    /**
     * 查询所有
     *
     * @param orgReq 入参
     * @return {@link List }<{@link OrgDTO}>
     */
    @PostMapping("/list")
    public List<OrgDTO> list(@RequestBody OrgReq orgReq) {
        return orgService.list(orgReq, orgConverter);
    }

    /**
     * 分页查询
     *
     * @param orgReq 入参
     * @return {@link PageResult }<{@link OrgDTO }> 分页数据
     */
    @PostMapping("/page")
    public PageResult<OrgDTO> page(@RequestBody OrgReq orgReq) {
        return orgService.pageList(orgReq, orgConverter);
    }

    /**
     * 保存
     *
     * @param orgReq 入参
     * @return Boolean 保存成功返回true
     */
    @PostMapping("/save")
    public Boolean save(@RequestBody OrgReq orgReq) {
        OrgEntity entity = orgConverter.reqToDO(orgReq);
        return orgService.save(entity);
    }

    /**
     * 修改
     *
     * @param orgReq 入参
     * @return Boolean 修改成功返回true
     */
    @PostMapping("/update")
    public Boolean update(@RequestBody OrgReq orgReq) {
        OrgEntity orgEntity = orgConverter.reqToDO(orgReq);
        if (Objects.isNull(orgEntity.getId())) {
            return orgService.save(orgEntity);
        }
        return orgService.updateById(orgEntity);
    }

    /**
     * 删除
     *
     * @param id 主键
     * @return Boolean 删除成功返回true
     */
    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable String id) {
        return orgService.removeById(id);
    }
}