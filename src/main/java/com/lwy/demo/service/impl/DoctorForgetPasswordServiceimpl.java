package com.lwy.demo.service.impl;

import com.lwy.demo.entity.DoctorUser;
import com.lwy.demo.mapper.DoctorForgetPasswordMapper;
import com.lwy.demo.mapper.DoctorRegisterMapper;
import com.lwy.demo.service.DoctorForgetPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;


@Transactional
@Service
public class DoctorForgetPasswordServiceimpl implements DoctorForgetPasswordService {

    @Autowired
    private DoctorForgetPasswordMapper mapper;

    @Override
    public DoctorUser selectforgetpasswordinf(ConcurrentMap list) {
        return mapper.selectforgetpasswordinf(list);
    }
}
