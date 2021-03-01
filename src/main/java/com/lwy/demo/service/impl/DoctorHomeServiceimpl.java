package com.lwy.demo.service.impl;

import com.lwy.demo.entity.Diagnosis;
import com.lwy.demo.entity.Patient_Registration_record;
import com.lwy.demo.mapper.DoctorHomeMapper;
import com.lwy.demo.service.DoctorHomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;


@Transactional
@Service
public class DoctorHomeServiceimpl implements DoctorHomeService {

    @Autowired
    private DoctorHomeMapper mapper;

    @Override
    public CopyOnWriteArrayList<Patient_Registration_record> selectallPRrecode(ConcurrentMap map) {
        return mapper.selectallPRrecode(map);
    }

    @Override
    public Patient_Registration_record selectPRRandMRbyid(int id) {
        return mapper.selectPRRandMRbyid(id);
    }

    @Override
    public void updatePrralready(int prrid) {
        mapper.updatePrralready(prrid);
    }

    @Override
    public void updatePRRinf(ConcurrentMap map) {
        mapper.updatePRRinf(map);
    }

    @Override
    public CopyOnWriteArrayList<Diagnosis> selectallDiagnosis() {
        return mapper.selectallDiagnosis();
    }
}
