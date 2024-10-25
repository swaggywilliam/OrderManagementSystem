package com.zhuwl.springcloud.entity;

import lombok.Data;

@Data
public class Product {
    private Long productId;
    private String productName;
    private Integer stock;
    private Double productPrice;
}
