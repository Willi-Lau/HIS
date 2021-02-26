package com.lwy.demo.mapper;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.concurrent.ConcurrentMap;

@Repository
public interface DoctorLoginMapper {
    /**
     * 根据用户密码验证登陆返回是医生还是管理员
     */
      @Select("select dutype from doctoruser where duusername =#{username} and dupassword = #{password}")
    String doctorlogin(ConcurrentMap map);
}
