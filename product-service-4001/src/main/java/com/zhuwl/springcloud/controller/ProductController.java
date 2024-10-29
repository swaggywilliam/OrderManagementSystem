package com.zhuwl.springcloud.controller;

import com.zhuwl.springcloud.entity.Product;
import com.zhuwl.springcloud.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/{id}")
    public Product getProdctById(@PathVariable("id") Long id) {
        return productService.getProductById(id);
    }
}
