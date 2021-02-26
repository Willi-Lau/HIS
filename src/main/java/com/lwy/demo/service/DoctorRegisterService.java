package com.lwy.demo.service;

import com.lwy.demo.entity.DoctorUser;

import java.util.concurrent.CopyOnWriteArrayList;

public interface DoctorRegisterService {
    /**
     * 查找所有科室
     */
    CopyOnWriteArrayList<String> selectalldepartment();
    /**
     * 存入医生用户信息
     */
     void registerdoctor(DoctorUser doctorUser);
    /**
     * 查找账号是否被注册
     */
    String selectusername(String duusername);
    /**
     * 查找身份证是否被是注册
     */
    String selectcard(String ducard);
    /**
     * 查找手机是否被注册
     */
    String selectphone(String duphone);
}
