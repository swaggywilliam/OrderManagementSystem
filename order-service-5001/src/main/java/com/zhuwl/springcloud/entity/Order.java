package com.zhuwl.springcloud.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class Order {

    private Long id;                        // 订单id
    private Long userId;                    // 订购者id
    private BigDecimal totalPrice;          // 订单总价
    private Integer status;                 // 订单状态，0-失效、1-有效
    private LocalDateTime createdTime;      // 订单创建时间
    private List<OrderItem> orderItems;     // 关联的订单明细列表
    private User user;                      // 订购者信息

}
