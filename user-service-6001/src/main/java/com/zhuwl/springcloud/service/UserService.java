package com.zhuwl.springcloud.service;

import com.zhuwl.springcloud.entity.CommonResult;
import com.zhuwl.springcloud.entity.User;

public interface UserService {

    CommonResult<User> getUserById(Long id);
}
