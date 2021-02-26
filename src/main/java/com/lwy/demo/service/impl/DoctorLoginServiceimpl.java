package com.lwy.demo.service.impl;

import com.lwy.demo.entity.DoctorLoginrecode;
import com.lwy.demo.mapper.DoctorLoginMapper;
import com.lwy.demo.service.DoctorLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.ConcurrentMap;

@Service
@Transactional
public class DoctorLoginServiceimpl implements DoctorLoginService {
    @Autowired
    private DoctorLoginMapper mapper;

    @Override
    public String doctorlogin(ConcurrentMap map) {
        return mapper.doctorlogin(map);
    }

    @Override
    public int selectid(String username) {
        return mapper.selectid(username);
    }

    @Override
    public void addrecord(DoctorLoginrecode doctor) {
        mapper.addrecord(doctor);
    }
}
