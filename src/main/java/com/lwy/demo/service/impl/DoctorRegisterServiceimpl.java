package com.lwy.demo.service.impl;

import com.lwy.demo.entity.DoctorUser;
import com.lwy.demo.mapper.DoctorRegisterMapper;
import com.lwy.demo.service.DoctorRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.CopyOnWriteArrayList;

@Service
@Transactional
public class DoctorRegisterServiceimpl implements DoctorRegisterService {

    @Autowired
    private DoctorRegisterMapper mapper;

    @Override
    public CopyOnWriteArrayList<String> selectalldepartment() {
        return mapper.selectalldepartmentname();

    }

    @Override
    public void registerdoctor(DoctorUser doctorUser) {
        mapper.registerdoctor(doctorUser);
    }

    @Override
    public String selectusername(String duusername) {
        return mapper.selectusername(duusername);
    }

    @Override
    public String selectcard(String ducard) {
        return mapper.selectcard(ducard);
    }

    @Override
    public String selectphone(String duphone) {
        return mapper.selectphone(duphone);
    }
}
