package com.lwy.demo.mapper;


import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.concurrent.ConcurrentMap;

@Repository
public interface DoctorChangePasswordMapper {

    /**
     * 根据用户名修改密码
     */
    @Update("update doctoruser set dupassword = #{password} where duusername = #{username}")
    void changepassword(ConcurrentMap map);
}
