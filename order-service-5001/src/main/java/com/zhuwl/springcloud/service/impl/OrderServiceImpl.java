package com.zhuwl.springcloud.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.zhuwl.springcloud.dao.OrderDao;
import com.zhuwl.springcloud.dao.OrderItemDao;
import com.zhuwl.springcloud.entity.*;
import com.zhuwl.springcloud.feignclients.StockFeignClient;
import com.zhuwl.springcloud.feignclients.UserFeignClient;
import com.zhuwl.springcloud.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private OrderItemDao orderItemDao;

    @Autowired
    private UserFeignClient userFeignClient;

    @Autowired
    private StockFeignClient stockFeignClient;

    /**
     * 查询订单
     * @param id
     * @return
     */
    @Override
    @HystrixCommand(fallbackMethod = "orderService_TimeOutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
    })
    public CommonResult<Order> getOrderById(Long id) {
        // 查询Order基本信息
        Order order = orderDao.getOrderById(id);
        if (order == null) {
            return CommonResult.failed("未查到该订单！");
        }

        // 查询User信息，调用user-service，返回的是CommonResult<User>
        CommonResult<User> user_res = userFeignClient.getUserById(order.getUserId());
        if (user_res.getCode() != 200) {
            return CommonResult.failed("未查到用户信息");
        }
        order.setUser(user_res.getData());

        // 查询订单项
        List<OrderItem> orderItemsByOrderId = orderItemDao.getOrderItemsByOrderId(id);
        order.setOrderItems(orderItemsByOrderId);
        return CommonResult.success(order);
    }

    /**
     * 查询订单超时，服务降级
     * @param id
     * @return
     */
    CommonResult<Order> orderService_TimeOutHandler(Long id) {
        return CommonResult.failed("订单查询超时。");
    }

    /**
     * 新增订单
     * @param order
     * @return
     */
    @Transactional
    @Override
    public CommonResult<Long> createOrder(Order order) {
        // 创建订单，基本信息的保存
        orderDao.createOrder(order);

        // 获取order在db中的自增id
        Long orderId = order.getId();

        BigDecimal totalPrice = BigDecimal.ZERO;

        // 遍历订单项，1. 判断库存是否充足；2. 计算订单总价
        for (OrderItem item : order.getOrderItems()) {
            Stock stock = stockFeignClient.getStockById(item.getProductId()).getData();
            if (item.getQuantity() >= stock.getAvailableQuantity()) {
                return CommonResult.failed("商品库存不足，订单添加失败。");
            } else {
                // 更新商品库存
                stockFeignClient.updateStockById(item.getProductId(), stock.getAvailableQuantity() - item.getQuantity());
                // 创建订单项，订单项信息的保存
                item.setOrderId(orderId);
                orderItemDao.createOrderItem(item);
                // 累计订单总价
                totalPrice = totalPrice.add(item.getProductPrice().multiply(new BigDecimal(item.getQuantity())));
            }
        }

        // 更新订单总价
        order.setTotalPrice(totalPrice);
        orderDao.updateOrder(order);

        return CommonResult.success(orderId);
    }
}
