package com.soeasyeasy.test.controller;

import com.soeasyeasy.test.pojo.dto.Product;
import com.soeasyeasy.test.service.ProductService;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


/**
 * 产品
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
    private ProductService productService;

    /**
     * 创建或更新产品
     *
     * @param product 产品
     * @return {@link Product }
     */
    @PostMapping
    public Product createOrUpdateProduct(@RequestBody Product product) {
        return productService.saveProduct(product);
    }


    /**
     * 按 ID 获取产品
     *
     * @param id
     * @return {@link Optional }<{@link Product }>
     */
    @GetMapping("/get/{id}")
    public Optional<Product> getProductById(@PathVariable String id) {
        return productService.findById(id);
    }


    /**
     * 按 ID 获取产品
     *
     * @param ids
     * @return {@link List }<{@link Product }>
     */
    @PostMapping("/getProductById")
    public List<Product> getProductById(@RequestBody List<String> ids) {
        return productService.findByIds(ids);
    }

    /**
     * 按名称搜索
     *
     * @param name 名字
     * @return {@link List }<{@link Product }>
     */
    @GetMapping("/search")
    public List<Product> searchByName(@RequestParam String name) {
        return productService.findByName(name);
    }


    /**
     * 按描述搜索
     */
    @GetMapping("/searchByDescription")
    public List<Product> searchByDescription(@RequestParam String description) {
        return productService.findByDescription(description);
    }

    /**
     * 获取所有产品
     *
     * @param page 页
     * @param size 大小
     * @return {@link Page }<{@link Product }>
     */
    @GetMapping
    public Page<Product> getAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return productService.findAll(pageable);
    }

    /**
     * 删除产品
     *
     * @param id 身份证
     * @return {@link String }
     */
    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable String id) {
        productService.deleteProduct(id);
        return "Product deleted successfully!";
    }
}