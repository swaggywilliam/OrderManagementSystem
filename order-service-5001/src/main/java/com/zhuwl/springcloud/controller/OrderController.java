package com.zhuwl.springcloud.controller;

import com.zhuwl.springcloud.entity.CommonResult;
import com.zhuwl.springcloud.entity.Order;
import com.zhuwl.springcloud.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/{id}")
    public CommonResult<Order> getOrderById(@PathVariable("id") Long id) {
        return orderService.getOrderById(id);
    }

    @PostMapping()
    public CommonResult<Order> createOrder(@RequestBody Order order) {
        return getOrderById(orderService.createOrder(order).getData());
    }
}
