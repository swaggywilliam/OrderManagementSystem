package com.zhuwl.springcloud.feignclients;

import com.zhuwl.springcloud.entity.CommonResult;
import com.zhuwl.springcloud.entity.Stock;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "stock-service")
public interface StockFeignClient {

    @GetMapping("/stock/{id}")
    CommonResult<Stock> getStockById(@PathVariable("id") Long id);

    @PostMapping("/stock/{id}/{quantity}")
    CommonResult<Stock> updateStockById(@PathVariable("id") Long id, @PathVariable("quantity") Long quantity);
}
