package com.zhuwl.springcloud.dao;

import com.zhuwl.springcloud.entity.OrderItem;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface OrderItemDao {

    /**
     * 根据订单id查询订单项信息
     * @param id 订单id
     * @return
     */
    @Select("SELECT id, order_id, product_id, product_price, quantity, total_price, created_time " +
            "FROM tb_order_item " +
            "WHERE order_id = #{orderId}")
    List<OrderItem> getOrderItemsByOrderId(Long id);

    @Insert("insert into tb_order_item(order_id, product_id, product_price, quantity, total_price) " +
            "values(#{orderId}, #{productId}, #{productPrice}, #{quantity}, #{productPrice} * #{quantity})")
    Integer createOrderItem(OrderItem orderItem);
}
