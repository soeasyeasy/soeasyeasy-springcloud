package com.soeasyeasy.system.controller;

import com.soeasyeasy.common.entity.PageResult;
import com.soeasyeasy.system.convertor.ApiConverter;
import com.soeasyeasy.system.entity.ApiEntity;
import com.soeasyeasy.system.entity.dto.ApiDTO;
import com.soeasyeasy.system.entity.param.ApiReq;
import com.soeasyeasy.system.service.ApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * API信息控制层
 *
 * @author system
 * @date 2025-06-18 17:03:51
 */
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ApiController {

    private final ApiService apiService;
    private final ApiConverter apiConverter;

    /**
     * 根据id查询
     *
     * @param id 主键
     * @return Api
     */
    @GetMapping("/{id}")
    public ApiDTO getById(@PathVariable String id) {
        ApiEntity entity = apiService.getById(id);
        return apiConverter.entityToDto(entity);
    }

    /**
     * 查询所有
     *
     * @param apiReq 入参
     * @return {@link List }<{@link ApiDTO}>
     */
    @PostMapping("/list")
    public List<ApiDTO> list(@RequestBody ApiReq apiReq) {
        return apiService.list(apiReq, apiConverter);
    }

    /**
     * 分页查询
     *
     * @param apiReq 入参
     * @return {@link PageResult }<{@link ApiDTO }> 分页数据
     */
    @PostMapping("/page")
    public PageResult<ApiDTO> page(@RequestBody ApiReq apiReq) {
        return apiService.pageList(apiReq, apiConverter);
    }

    /**
     * 保存
     *
     * @param apiReq 入参
     * @return Boolean 保存成功返回true
     */
    @PostMapping("/save")
    public Boolean save(@RequestBody ApiReq apiReq) {
        ApiEntity entity = apiConverter.reqToDO(apiReq);
        return apiService.save(entity);
    }

    /**
     * 修改
     *
     * @param apiReq 入参
     * @return Boolean 修改成功返回true
     */
    @PostMapping("/update")
    public Boolean update(@RequestBody ApiReq apiReq) {
        ApiEntity apiEntity = apiConverter.reqToDO(apiReq);
        if (Objects.isNull(apiEntity.getId())) {
            return apiService.save(apiEntity);
        }
        return apiService.updateById(apiEntity);
    }

    /**
     * 删除
     *
     * @param id 主键
     * @return Boolean 删除成功返回true
     */
    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable String id) {
        return apiService.removeById(id);
    }
}