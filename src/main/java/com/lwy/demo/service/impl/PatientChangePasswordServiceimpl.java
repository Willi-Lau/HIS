package com.lwy.demo.service.impl;

import com.lwy.demo.mapper.PatientChangePasswordMapper;
import com.lwy.demo.service.PatientChangePasswordService;
import com.sun.javafx.collections.MappingChange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.concurrent.ConcurrentMap;

@Service
@Transactional
public class PatientChangePasswordServiceimpl implements PatientChangePasswordService {
    @Autowired
    private PatientChangePasswordMapper mapper;
    @Override
    public void changepassword(ConcurrentMap map) {
        mapper.changepassword(map);
    }
}
