package com.lwy.demo.mapper;

import com.lwy.demo.entity.DoctorUser;
import com.lwy.demo.entity.PatientUser;
import org.springframework.stereotype.Repository;

import java.util.concurrent.ConcurrentMap;

@Repository
public interface PatientForgetPasswordMapper {

    /**
     * 根据是手机号还是身份证号，验证信息是否正确
     */

    PatientUser selectforgetpasswordinf(ConcurrentMap map);
}
