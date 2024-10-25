package com.zhuwl.springcloud.controller;

import com.zhuwl.springcloud.entity.CommonResult;
import com.zhuwl.springcloud.entity.Product;
import com.zhuwl.springcloud.service.StockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController()
@Slf4j
@RequestMapping("/stock")
public class StockController {

    @Autowired
    private StockService stockService;

    @GetMapping("/{id}")
    public CommonResult<Product> getStockById(@PathVariable("id") Long id) {
        return stockService.getStockById(id);
    }

    @PostMapping("/increment/{id}/{quantity}")
    public CommonResult<Product> incrementStock(@PathVariable("id") Long id, @PathVariable("quantity") Integer quantity) {
        if (id == null || quantity == null || quantity <= 0) {
            return CommonResult.failed("无效的参数。");
        }
        stockService.increaseStockById(id, quantity);
        System.out.println("库存更新成功，当前库存为：" + stockService.getStockById(id));
        return stockService.getStockById(id);
    }

    @PostMapping("/decrement/{id}/{quantity}")
    public CommonResult<Product> decrementStock(@PathVariable("id") Long id, @PathVariable("quantity") Integer quantity) {
        if (id == null || quantity == null || quantity <= 0) {
            return CommonResult.failed("无效的参数。");
        }
        if(stockService.decreaseStockById(id, quantity) == 1) {
            System.out.println("库存更新成功，当前库存为：" + stockService.getStockById(id));
            return stockService.getStockById(id);
        } else {
            return CommonResult.failed("库存不足！");
        }
    }

}
