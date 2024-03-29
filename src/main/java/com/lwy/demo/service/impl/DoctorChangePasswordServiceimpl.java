package com.lwy.demo.service.impl;

import com.lwy.demo.mapper.DoctorChangePasswordMapper;
import com.lwy.demo.service.DoctorChangePasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.ConcurrentMap;


@Service
@Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
public class DoctorChangePasswordServiceimpl implements DoctorChangePasswordService {

    @Autowired
    private DoctorChangePasswordMapper mapper;

    @Override
    public void changepassword(ConcurrentMap map) {
        mapper.changepassword(map);
    }
}
