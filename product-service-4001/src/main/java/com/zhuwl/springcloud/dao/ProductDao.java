package com.zhuwl.springcloud.dao;

import com.zhuwl.springcloud.entity.Product;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface ProductDao {

    /**
     * 根据商品id查询商品信息
     * @param id
     * @return  Product
     */
    @Select("select * from tb_product where id = #{id}")
    Product getProductById(@Param("id") Long id);
}
