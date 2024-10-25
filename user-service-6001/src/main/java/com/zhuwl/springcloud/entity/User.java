package com.zhuwl.springcloud.entity;

import lombok.Data;

@Data
public class User {

    private Long id; // 用户id
    private String username; // 用户姓名
    private String address;  // 用户地址
}
