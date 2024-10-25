package com.zhuwl.springcloud.service.impl;

import com.zhuwl.springcloud.dao.StockDao;
import com.zhuwl.springcloud.entity.CommonResult;
import com.zhuwl.springcloud.entity.Product;
import com.zhuwl.springcloud.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockServiceImpl implements StockService {

    @Autowired
    private StockDao stockDao;

    @Override
    public CommonResult<Product> getStockById(Long id) {
        Product prod = stockDao.getStockById(id);
        if (prod == null) {
            return CommonResult.failed("未查到该商品信息。");
        } else {
            return CommonResult.success(prod);
        }
    }

    @Override
    public void increaseStockById(Long id, Integer increment) {
        Product prod = stockDao.getStockById(id);
        stockDao.updateStockById(id, prod.getStock() + increment);
    }

    @Override
    public Integer decreaseStockById(Long id, Integer decrement) {
        Product prod = stockDao.getStockById(id);
        if(prod.getStock() >= decrement) { // 库存余量大于扣减量
            stockDao.updateStockById(id, prod.getStock() - decrement);
            return 1; // 库存扣减成功
        } else {
            return 0; // 库存扣减失败
        }
    }
}
