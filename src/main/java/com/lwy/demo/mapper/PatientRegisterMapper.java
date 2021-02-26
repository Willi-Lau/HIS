package com.lwy.demo.mapper;


import com.lwy.demo.entity.PatientUser;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRegisterMapper {
    /**
     * 查找账号是否被注册过
     */
    @Select("select puid from patientuser where puusername = #{puusername}")
    String selectpuusername(String puusername);
    /**
     * 查找用户是否注册过（身份证）
     */
    @Select("select puid from patientuser where pucard = #{pucard}")
    String selectpucard(String pucard);
    /**
     * 查找手机是否已经绑定
     */
    @Select("select puid from patientuser where puphone = #{puphone}")
    String selectpuphone(String puphone);
    /**
     * 把注册信息插入到表中
     */
     void addpatientuser(PatientUser user);
}
