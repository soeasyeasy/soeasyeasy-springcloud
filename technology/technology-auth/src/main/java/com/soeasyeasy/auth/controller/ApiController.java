package com.soeasyeasy.auth.controller;

import com.soeasyeasy.auth.convertor.ApiConverter;
import com.soeasyeasy.auth.entity.ApiEntity;
import com.soeasyeasy.auth.entity.dto.ApiDTO;
import com.soeasyeasy.auth.entity.param.ApiReq;
import com.soeasyeasy.auth.service.ApiService;
import com.soeasyeasy.common.entity.PageResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * API信息控制层
 *
 * @author system
 * @date 2025-04-18 15:11:28
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
        return apiService.updateById(apiConverter.reqToDO(apiReq));
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