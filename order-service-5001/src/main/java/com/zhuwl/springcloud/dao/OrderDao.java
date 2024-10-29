package com.zhuwl.springcloud.dao;

import com.zhuwl.springcloud.entity.Order;
import org.apache.ibatis.annotations.*;

public interface OrderDao {

    /**
     * 根据订单id查询订单信息
     * @param id 订单id
     * @return
     */
    @Select("select * from tb_order where id = #{id}")
    Order getOrderById(@Param("id") Long id);

    @Insert("insert into tb_order(user_id, total_price) " +
                        "values(#{userId}, #{totalPrice})")
    @Options(useGeneratedKeys = true, keyProperty = "id") // 新增的数据的主键id 赋给 order.id
    Integer createOrder(Order order);

    @Update("update tb_order set user_id = #{userId}, total_price = #{totalPrice}, status = #{status} " +
            "where id = #{id}")
    Integer updateOrder(Order order);

}
