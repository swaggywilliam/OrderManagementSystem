package com.zhuwl.springcloud.entity;

import lombok.Data;

@Data
public class Order {

    private Long orderId;  // 订单id
    private Long productId; // 商品id
    private Integer productQuantity; // 商品订购数量
    private Long userId; // 订购者id
    private Integer status; // 订单状态，0-失效、1-有效
    private User user;
}
