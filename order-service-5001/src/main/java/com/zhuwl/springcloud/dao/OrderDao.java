package com.zhuwl.springcloud.dao;

import com.zhuwl.springcloud.entity.Order;
import org.apache.ibatis.annotations.*;

public interface OrderDao {

    @Select("select * from tb_order where order_id = #{id}")
    Order getOrderById(@Param("id") Long id);

    @Insert("insert into tb_order(product_id, product_quantity, user_id) " +
                        "values(#{productId}, #{productQuantity}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "orderId") // 新增的数据的主键id 赋给 order.orderId
    Integer createOrder(Order order);

}
