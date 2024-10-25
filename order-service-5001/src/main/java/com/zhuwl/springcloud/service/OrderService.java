package com.zhuwl.springcloud.service;

import com.zhuwl.springcloud.entity.CommonResult;
import com.zhuwl.springcloud.entity.Order;

public interface OrderService {

    /**
     * 查询订单
     * @param id
     * @return CommonResult<Order>
     */
    CommonResult<Order> getOrderById(Long id);

    /**
     * 新增订单
     * @param order
     * @return order在数据库里自增的orderId
     */
    CommonResult<Long> createOrder(Order order);
}
