package com.zhuwl.springcloud.dao;

import com.zhuwl.springcloud.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


public interface UserDao {

    @Select("select * from tb_user where id = #{id}")
    User getUserById(@Param("id") Long id);
}
