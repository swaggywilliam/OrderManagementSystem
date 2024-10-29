package com.zhuwl.springcloud.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class OrderItem {

    private Long id;
    private Long orderId;
    private Long productId;
    private BigDecimal productPrice;
    private Integer quantity;
    private BigDecimal totalPrice;
    private LocalDateTime createdTime;
}
