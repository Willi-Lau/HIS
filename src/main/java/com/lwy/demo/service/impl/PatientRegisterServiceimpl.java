package com.lwy.demo.service.impl;

import com.lwy.demo.entity.PatientUser;
import com.lwy.demo.mapper.PatientRegisterMapper;
import com.lwy.demo.service.PatientRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@Service
public class PatientRegisterServiceimpl implements PatientRegisterService {
    @Autowired
    private PatientRegisterMapper mapper;

    @Override
    public String selectpuusername(String puusername) {
        return mapper.selectpuusername(puusername);
    }

    @Override
    public String selectpucard(String pucard) {
        return mapper.selectpucard(pucard);
    }

    @Override
    public String selectpuphone(String puphone) {
        return mapper.selectpuphone(puphone);
    }

    @Override
    public void addpatientuser(PatientUser user) {
            mapper.addpatientuser(user);
    }
}
