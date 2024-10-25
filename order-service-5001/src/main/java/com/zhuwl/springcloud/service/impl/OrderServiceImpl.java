package com.zhuwl.springcloud.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.zhuwl.springcloud.dao.OrderDao;
import com.zhuwl.springcloud.entity.CommonResult;
import com.zhuwl.springcloud.entity.Order;
import com.zhuwl.springcloud.entity.User;
import com.zhuwl.springcloud.feignclients.StockFeignClient;
import com.zhuwl.springcloud.feignclients.UserFeignClient;
import com.zhuwl.springcloud.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

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
        // service -> dao -> database
        Order order = orderDao.getOrderById(id);
        if (order == null) {
            return CommonResult.failed("未查到该订单。");
        }
        // 调用user-service，返回的是CommonResult<User>
        CommonResult<User> user_res = userFeignClient.getUserById(order.getUserId());
        if (user_res.getCode() == 200) {  // 正常响应，将User封装到Order当中
            order.setUser(user_res.getData());
            return CommonResult.success(order);
        } else { // 异常响应
            return CommonResult.failed("未查到该订单的用户信息。");
        }
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
    @Override
    public CommonResult<Long> createOrder(Order order) {
        // 商品库存减少 CommonResult<Product>
        Integer code = stockFeignClient.decrementStock(order.getProductId(), order.getProductQuantity()).getCode();
        if (code != 200) { // 响应码200-成功 500-失败
            return CommonResult.failed("商品库存不足！");
        }

        // 创建订单
        Integer responseCode = orderDao.createOrder(order);
        if (responseCode == 1) { // 创建成功
            return CommonResult.success(order.getOrderId());
        } else {
            return CommonResult.failed("订单创建失败");
        }
    }
}
