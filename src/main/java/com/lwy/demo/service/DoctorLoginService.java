package com.lwy.demo.service;

import java.util.concurrent.ConcurrentMap;

public interface DoctorLoginService {
    /**
     * 根据用户密码验证登陆返回是医生还是管理员
     */
    String doctorlogin(ConcurrentMap map);
}
