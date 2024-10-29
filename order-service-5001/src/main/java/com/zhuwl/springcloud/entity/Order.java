package com.zhuwl.springcloud.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class Order {

    private Long id;
    private Long userId;
    private BigDecimal totalPrice;
    private Integer status;
    private LocalDateTime createdTime;
    private List<OrderItem> orderItems; // 关联的订单明细列表
    private User user;

//    private Long orderId;  // 订单id
//    private Long productId; // 商品id
//    private Integer productQuantity; // 商品订购数量
//    private Long userId; // 订购者id
//    private Integer status; // 订单状态，0-失效、1-有效
}
