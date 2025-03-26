package com.soeasyeasy.test.controller;

import com.soeasyeasy.common.annotation.DisableGlobalResponse;
import com.soeasyeasy.common.entity.PageParam;
import com.soeasyeasy.common.entity.PageResult;
import com.soeasyeasy.test.converter.ProductConverter;
import com.soeasyeasy.test.entity.DO.ProductDO;
import com.soeasyeasy.test.entity.DTO.ProductDTO;
import com.soeasyeasy.test.service.ProductDbService;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


/**
 * 产品控制层
 *
 * @author hc
 * @date 2025/02/14
 */
@RestController
@RequestMapping("/products")
public class ProductController {

    /**
     * 产品服务
     */
    @Resource
    private ProductDbService productDbService;

    /**
     * 创建或更新产品
     *
     * @param product 产品
     * @return {@link ProductDTO }
     */
    @PostMapping
    public Boolean createOrUpdateProduct(@RequestBody ProductDTO product) {
        return productDbService.saveOrUpdate(ProductConverter.INSTANCE.dtoToEntity(product));
    }


    /**
     * 按 ID 获取产品
     *
     * @param id 主键
     * @return {@link Optional }<{@link ProductDTO }>
     */
    @GetMapping("/get/{id}")
    @DisableGlobalResponse
    public ProductDTO getProductById(@PathVariable String id) {
        return ProductConverter.INSTANCE.entityToDto(productDbService.getById(id));
    }


    /**
     * 按 ID 获取产品
     *
     * @param ids 主键集合
     * @return {@link List }<{@link ProductDTO }>
     */
    @PostMapping("/getProductById")
    public List<ProductDTO> getProductById(@RequestBody List<String> ids) {
        return ProductConverter.INSTANCE.entityToDto(productDbService.listByIds(ids));
    }

    /**
     * 按名称搜索
     *
     * @param name 名字
     * @return {@link List }<{@link ProductDTO }>
     */
    @GetMapping("/search")
    public List<ProductDTO> searchByName(@RequestParam String name) {
        return ProductConverter.INSTANCE.entityToDto(productDbService.listByMap(new HashMap<>() {{
            put("name", name);
        }}));
    }


    /**
     * 获取所有产品
     *
     * @param page 页
     * @param size 大小
     * @return {@link Page }<{@link ProductDTO }>
     */
    @GetMapping
    public PageResult<ProductDTO> getAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        PageResult<ProductDO> pageResult = productDbService.pageList(new PageParam<ProductDO>(page, size));
        return new PageResult<>(pageResult.getCurrent(), pageResult.getSize(), pageResult.getTotal(),
                pageResult.getRecords().stream().map(ProductConverter.INSTANCE::entityToDto).collect(Collectors.toList()));
    }

    /**
     * 删除产品
     *
     * @param id 主键
     * @return {@link String }
     */
    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productDbService.removeById(id);
        return "Product deleted successfully!";
    }
}