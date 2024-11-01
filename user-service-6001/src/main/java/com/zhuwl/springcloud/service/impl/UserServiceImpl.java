package com.zhuwl.springcloud.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.zhuwl.springcloud.dao.UserDao;
import com.zhuwl.springcloud.entity.CommonResult;
import com.zhuwl.springcloud.entity.User;
import com.zhuwl.springcloud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    @HystrixCommand(fallbackMethod = "userService_TimeOutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
    })
    public CommonResult<User> getUserById(Long id) {
//        int i = 1/0; // 超时响应，测试服务降级

        // 从Redis中查询
        User cachedUser = (User)redisTemplate.opsForValue().get("user: " + id);
        if (cachedUser != null) { // 缓存命中
            return CommonResult.success(cachedUser);
        }

        // 从db中查询
        User dbUser = userDao.getUserById(id);
        if (dbUser != null) {
            // 数据库命中，存入缓存，过期时间1小时
            redisTemplate.opsForValue().set("user:" + id, dbUser, 1, TimeUnit.HOURS);
            return CommonResult.success(dbUser);
        } else { // 数据库未命中
            // 防止缓存穿透设计, 将空结果缓存，过期时间3分钟
            redisTemplate.opsForValue().set("user:" + id, null, 3, TimeUnit.MINUTES);
            return CommonResult.failed("未找到用户信息。");
        }
    }

    public CommonResult<User> userService_TimeOutHandler(Long id){
        return CommonResult.failed("查询超时，请稍后重试。");
    }

}
