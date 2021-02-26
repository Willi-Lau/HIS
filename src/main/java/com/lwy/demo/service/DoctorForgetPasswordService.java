package com.lwy.demo.service;

import com.lwy.demo.entity.DoctorUser;

import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;

public interface DoctorForgetPasswordService {

    /**
     * 找回密码，首先判断验证信息是否正确
     */
    DoctorUser selectforgetpasswordinf(ConcurrentMap list);
}
