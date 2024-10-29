package com.zhuwl.springcloud.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Product {

    private Long id;                   // 商品ID
    private String productName;        // 商品名称
    private BigDecimal productPrice;   // 商品价格
    private String productDescription; // 商品描述

}
