package com.zhuwl.springcloud.controller;

import com.zhuwl.springcloud.entity.CommonResult;
import com.zhuwl.springcloud.entity.Stock;
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

    /**
     * 根据商品id查询库存余量
     * @param id 商品id
     * @return
     */
    @GetMapping("/{id}")
    public CommonResult<Stock> getStockById(@PathVariable("id") Long id) {
        return stockService.getStockById(id);
    }

    /**
     * 根据商品id和余量更新库存
     * @param id 商品id
     * @param quantity 商品余量（余量变动后的结果）
     * @return
     */
    @PostMapping("/{id}/{quantity}")
    public CommonResult<Stock> updateStockById(@PathVariable("id") Long id, @PathVariable("quantity") Long quantity) {
        if (id == null || quantity == null || quantity < 0) {
            return CommonResult.failed("无效参数，请检查后重试。");
        }
        stockService.updateStockById(id, quantity);
        System.out.println("库存更新成功，当前库存为：" + stockService.getStockById(id));
        return stockService.getStockById(id);
    }

//    @PostMapping("/increment/{id}/{quantity}")
//    public CommonResult<Product> incrementStock(@PathVariable("id") Long id, @PathVariable("quantity") Integer quantity) {
//        if (id == null || quantity == null || quantity <= 0) {
//            return CommonResult.failed("无效的参数。");
//        }
//        stockService.increaseStockById(id, quantity);
//        System.out.println("库存更新成功，当前库存为：" + stockService.getStockById(id));
//        return stockService.getStockById(id);
//    }
//
//    @PostMapping("/decrement/{id}/{quantity}")
//    public CommonResult<Product> decrementStock(@PathVariable("id") Long id, @PathVariable("quantity") Integer quantity) {
//        if (id == null || quantity == null || quantity <= 0) {
//            return CommonResult.failed("无效的参数。");
//        }
//        if(stockService.decreaseStockById(id, quantity) == 1) {
//            System.out.println("库存更新成功，当前库存为：" + stockService.getStockById(id));
//            return stockService.getStockById(id);
//        } else {
//            return CommonResult.failed("库存不足！");
//        }
//    }

}
