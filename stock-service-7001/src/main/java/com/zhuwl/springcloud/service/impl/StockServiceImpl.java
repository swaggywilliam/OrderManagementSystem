package com.zhuwl.springcloud.service.impl;

import com.zhuwl.springcloud.dao.StockDao;
import com.zhuwl.springcloud.entity.CommonResult;
import com.zhuwl.springcloud.entity.Stock;
import com.zhuwl.springcloud.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockServiceImpl implements StockService {

    @Autowired
    private StockDao stockDao;

    @Override
    public CommonResult<Stock> getStockById(Long id) {
        Long availableQuantity = stockDao.getStockById(id);
        if (availableQuantity == null) {
            return CommonResult.failed("未查到该商品信息。");
        } else {
            Stock stock = new Stock();
            stock.setId(id);
            stock.setAvailableQuantity(availableQuantity);
            return CommonResult.success(stock);
        }
    }

    @Override
    public CommonResult<String> updateStockById(Long id, Long quantity) {
        Integer res = stockDao.updateStockById(id, quantity);
        if (res != 0) {
            return CommonResult.success("库存修改成功！");
        } else {
            return CommonResult.failed("库存修改失败！");
        }
    }

//    @Override
//    public void increaseStockById(Long id, Integer increment) {
//        Product prod = stockDao.getStockById(id);
//        stockDao.updateStockById(id, prod.getStock() + increment);
//    }
//
//    @Override
//    public Integer decreaseStockById(Long id, Integer decrement) {
//        Product prod = stockDao.getStockById(id);
//        if(prod.getStock() >= decrement) { // 库存余量大于扣减量
//            stockDao.updateStockById(id, prod.getStock() - decrement);
//            return 1; // 库存扣减成功
//        } else {
//            return 0; // 库存扣减失败
//        }
//    }
}
