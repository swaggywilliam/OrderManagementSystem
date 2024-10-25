package com.zhuwl.springcloud.feignclients;

import com.zhuwl.springcloud.entity.CommonResult;
import com.zhuwl.springcloud.entity.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "stock-service")
public interface StockFeignClient {

    @GetMapping("/stock/{id}")
    CommonResult<Product> getStockById(@PathVariable("id") Long id);

    @PostMapping("/stock/decrement/{id}/{quantity}")
    CommonResult<Product> decrementStock(@PathVariable("id") Long id, @PathVariable("quantity") Integer quantity);
}
