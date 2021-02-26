package com.lwy.demo.service;

import com.lwy.demo.entity.DoctorLoginrecode;

import java.util.concurrent.ConcurrentMap;

public interface DoctorLoginService {
    /**
     * 根据用户密码验证登陆返回是医生还是管理员
     */
    String doctorlogin(ConcurrentMap map);
    /**
     * 根据用户名查找Id
     */
    int selectid(String username);
    /**
     * 写入用户登陆日志
     */
    void addrecord(DoctorLoginrecode doctor);
}
