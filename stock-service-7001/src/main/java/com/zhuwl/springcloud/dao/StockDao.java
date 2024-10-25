package com.zhuwl.springcloud.dao;

import com.zhuwl.springcloud.entity.Product;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface StockDao {

    /**
     * 查询库存
     * @param id
     */
    @Select("select * from tb_stock where product_id = #{id}")
    Product getStockById(@Param("id") Long id);

    /**
     * 更新库存
     *
     * @param id
     * @param quantity
     */
    @Update("update tb_stock set stock = #{quantity} where product_id = #{id}")
    Integer updateStockById(@Param("id") Long id, @Param("quantity") Integer quantity);
}
