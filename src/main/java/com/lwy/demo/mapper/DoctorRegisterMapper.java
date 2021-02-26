package com.lwy.demo.mapper;

import com.lwy.demo.entity.DoctorUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Repository
public interface DoctorRegisterMapper {


    /**
     * 查找所有的医生科室名字
     */
    @Select("select dename from department")
     CopyOnWriteArrayList<String> selectalldepartmentname();
    /**
     * 注册医生
     */
    @Insert("insert into doctoruser(duid,duusername,dupassword,duname,dusex,ducard,duphone," +
            "duaddress,dudepartmentname,dutype,dutitle) values(#{duid},#{duusername},#{dupassword}" +
            ",#{duname},#{dusex},#{ducard},#{duphone},#{duaddress},#{dudepartmentname},#{dutype},#{dutitle})")
    void registerdoctor(DoctorUser doctorUser);
    /**
     * 查找账号是否被注册过
     */
    @Select("select duid from doctoruser where duusername = #{duusername}")
    String selectusername(String duusername);
    /**
     * 查找用户是否注册过（身份证）
     */
    @Select("select duid from doctoruser where ducard = #{ducard}")
    String selectcard(String ducard);
    /**
     * 查找手机是否已经绑定
     */
    @Select("select duid from doctoruser where duphone = #{duphone}")
    String selectphone(String duphone);

}
