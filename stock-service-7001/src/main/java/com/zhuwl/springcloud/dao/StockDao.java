package com.zhuwl.springcloud.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface StockDao {

    /**
     * 查询库存
     * @param id
     * @return 库存余量
     */
    @Select("select availableQuantity from tb_stock where product_id = #{id}")
    Long getStockById(@Param("id") Long id);

    /**
     * 更新库存
     * @param id
     * @param quantity
     * @return
     */
    @Update("update tb_stock set availableQuantity = #{quantity} where product_id = #{id}")
    Integer updateStockById(@Param("id") Long id, @Param("quantity") Long quantity);

//    /**
//     * 更新库存
//     *
//     * @param id
//     * @param quantity
//     */
//    @Update("update tb_stock set stock = #{quantity} where product_id = #{id}")
//    Integer updateStockById(@Param("id") Long id, @Param("quantity") Integer quantity);
}
