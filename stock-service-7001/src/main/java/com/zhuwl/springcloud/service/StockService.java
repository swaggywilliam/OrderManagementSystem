package com.zhuwl.springcloud.service;

import com.zhuwl.springcloud.entity.CommonResult;
import com.zhuwl.springcloud.entity.Product;

public interface StockService {

    /**
     * 库存查询
     * @param id
     */
    CommonResult<Product> getStockById(Long id);

    /**
     * 库存增加
     *
     * @param id
     * @param increment
     */
    void increaseStockById(Long id, Integer increment);

    /**
     * 库存减少
     * @param id
     * @param decrement
     * @return
     */
    Integer decreaseStockById(Long id, Integer decrement);

}
