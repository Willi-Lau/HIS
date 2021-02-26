package com.lwy.demo.service;

import java.util.concurrent.ConcurrentMap;

public interface DoctorChangePasswordService {
    /**
     * 根据用户名 修改密码
     */
    void changepassword(ConcurrentMap map);
}
