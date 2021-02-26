package com.lwy.demo.service.impl;

import com.lwy.demo.entity.PatientUser;
import com.lwy.demo.mapper.PatientForgetPasswordMapper;
import com.lwy.demo.service.PatientForgetPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.nio.cs.ext.MacArabic;

import java.util.concurrent.ConcurrentMap;

@Service
@Transactional
public class PatientForgetPasswordServiceimpl implements PatientForgetPasswordService {

    @Autowired
    private PatientForgetPasswordMapper mapper;


    @Override
    public PatientUser selectforgetpasswordinf(ConcurrentMap map) {
        return mapper.selectforgetpasswordinf(map);
    }
}
