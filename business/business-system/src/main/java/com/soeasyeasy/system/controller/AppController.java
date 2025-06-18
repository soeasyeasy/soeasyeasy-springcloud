package com.soeasyeasy.system.controller;

import com.soeasyeasy.common.entity.PageResult;
import com.soeasyeasy.system.convertor.AppConverter;
import com.soeasyeasy.system.entity.AppEntity;
import com.soeasyeasy.system.entity.dto.AppDTO;
import com.soeasyeasy.system.entity.param.AppReq;
import com.soeasyeasy.system.service.AppService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * 应用表控制层
 *
 * @author system
 * @date 2025-06-18 17:03:51
 */
@RestController
@RequestMapping("/app")
@RequiredArgsConstructor
public class AppController {

    private final AppService appService;
    private final AppConverter appConverter;

    /**
     * 根据id查询
     *
     * @param id 主键
     * @return App
     */
    @GetMapping("/{id}")
    public AppDTO getById(@PathVariable String id) {
        AppEntity entity = appService.getById(id);
        return appConverter.entityToDto(entity);
    }

    /**
     * 查询所有
     *
     * @param appReq 入参
     * @return {@link List }<{@link AppDTO}>
     */
    @PostMapping("/list")
    public List<AppDTO> list(@RequestBody AppReq appReq) {
        return appService.list(appReq, appConverter);
    }

    /**
     * 分页查询
     *
     * @param appReq 入参
     * @return {@link PageResult }<{@link AppDTO }> 分页数据
     */
    @PostMapping("/page")
    public PageResult<AppDTO> page(@RequestBody AppReq appReq) {
        return appService.pageList(appReq, appConverter);
    }

    /**
     * 保存
     *
     * @param appReq 入参
     * @return Boolean 保存成功返回true
     */
    @PostMapping("/save")
    public Boolean save(@RequestBody AppReq appReq) {
        AppEntity entity = appConverter.reqToDO(appReq);
        return appService.save(entity);
    }

    /**
     * 修改
     *
     * @param appReq 入参
     * @return Boolean 修改成功返回true
     */
    @PostMapping("/update")
    public Boolean update(@RequestBody AppReq appReq) {
        AppEntity appEntity = appConverter.reqToDO(appReq);
        if (Objects.isNull(appEntity.getId())) {
            return appService.save(appEntity);
        }
        return appService.updateById(appEntity);
    }

    /**
     * 删除
     *
     * @param id 主键
     * @return Boolean 删除成功返回true
     */
    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable String id) {
        return appService.removeById(id);
    }
}