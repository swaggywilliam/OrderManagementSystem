package com.zhuwl.springcloud.entity;

import lombok.Data;

@Data
public class Stock {
    private Long id; // 商品id
    private Long availableQuantity; // 库存余量
}
