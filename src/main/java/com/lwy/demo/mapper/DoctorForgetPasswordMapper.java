package com.lwy.demo.mapper;


import com.lwy.demo.entity.DoctorUser;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.concurrent.ConcurrentMap;


@Repository
public interface DoctorForgetPasswordMapper {
    /**
     * 根据是手机号还是身份证号，验证信息是否正确
     */

    DoctorUser selectforgetpasswordinf(ConcurrentMap list);
}
