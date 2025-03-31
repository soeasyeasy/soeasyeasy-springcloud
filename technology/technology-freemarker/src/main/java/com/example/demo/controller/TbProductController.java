package com.example.demo.controller;

import com.example.demo.convertor.TbProductConverter;
import com.example.demo.entity.TbProductEntity;
import com.example.demo.entity.dto.TbProductDTO;
import com.example.demo.entity.param.TbProductReq;
import com.example.demo.service.TbProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 产品控制层
 *
 * @author system
 * @date 2025-03-31 17:49:39
 */
@RestController
@RequestMapping("/tb-product")
@RequiredArgsConstructor
public class TbProductController {

    private final TbProductService tbProductService;
    private final TbProductConverter tbProductConverter;

    /**
     * 根据id查询
     *
     * @param id
     * @return TbProduct
     */
    @GetMapping("/{id}")
    public TbProductDTO getById(@PathVariable Long id) {
        TbProductEntity entity = tbProductService.getById(id);
        return tbProductConverter.entityToDto(entity);
    }

    /**
     * 查询所有
     *
     * @return List<TbProduct>
     */
    @GetMapping("/list")
    public List<TbProductDTO> list() {
        return tbProductConverter.entityToDto(tbProductService.list());
    }

    /**
     * 保存
     *
     * @param tbProductReq
     * @return Boolean
     */
    @PostMapping("/save")
    public Boolean save(@RequestBody TbProductReq tbProductReq) {
        TbProductEntity entity = tbProductConverter.reqToDO(tbProductReq);
        return tbProductService.save(entity);
    }

    /**
     * 修改
     *
     * @param tbProductReq
     * @return Boolean
     */
    @PostMapping("/update")
    public Boolean update(@RequestBody TbProductReq tbProductReq) {
        return tbProductService.updateById(tbProductConverter.reqToDO(tbProductReq));
    }

    /**
     * 删除
     *
     * @param id
     * @return Boolean
     */
    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable Long id) {
        return tbProductService.removeById(id);
    }
}