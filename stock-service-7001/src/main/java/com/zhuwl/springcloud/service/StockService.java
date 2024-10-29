package com.zhuwl.springcloud.service;

import com.zhuwl.springcloud.entity.CommonResult;
import com.zhuwl.springcloud.entity.Stock;

public interface StockService {

    /**
     * 库存查询
     * @param id 商品id
     * @return
     */
    CommonResult<Stock> getStockById(Long id);

    /**
     * 库存修改
     * @param id 商品id
     * @param quantity 库存余量
     * @return
     */
    CommonResult<String> updateStockById(Long id, Long quantity);

//    /**
//     * 库存增加
//     *
//     * @param id
//     * @param increment
//     */
//    void increaseStockById(Long id, Integer increment);
//
//    /**
//     * 库存减少
//     * @param id
//     * @param decrement
//     * @return
//     */
//    Integer decreaseStockById(Long id, Integer decrement);

}
