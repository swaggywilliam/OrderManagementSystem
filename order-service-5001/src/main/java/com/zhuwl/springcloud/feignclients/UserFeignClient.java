package com.zhuwl.springcloud.feignclients;

import com.zhuwl.springcloud.entity.CommonResult;
import com.zhuwl.springcloud.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service")
public interface UserFeignClient {

    @GetMapping("/user/{id}")
    CommonResult<User> getUserById(@PathVariable("id") Long id);

}
