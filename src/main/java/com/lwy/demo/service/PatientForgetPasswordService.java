package com.lwy.demo.service;

import com.lwy.demo.entity.DoctorUser;
import com.lwy.demo.entity.PatientUser;

import java.util.concurrent.ConcurrentMap;

public interface PatientForgetPasswordService {
    /**
     * 找回密码，首先判断验证信息是否正确
     */
    PatientUser selectforgetpasswordinf(ConcurrentMap map);
}
