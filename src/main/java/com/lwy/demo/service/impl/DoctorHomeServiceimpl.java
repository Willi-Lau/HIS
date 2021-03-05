package com.lwy.demo.service.impl;

import com.lwy.demo.entity.*;
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

    @Override
    public CopyOnWriteArrayList<Inspection_items> selectallInspection() {
        return mapper.selectallInspection();
    }

    @Override
    public CopyOnWriteArrayList<Test_items> selectalltest() {
        return mapper.selectalltest();
    }

    @Override
    public int selectMRid(int prrid) {
        return mapper.selectMRid(prrid);
    }

    @Override
    public int countInspection() {
        return mapper.countInspection();
    }

    @Override
    public int countTest() {
        return mapper.countTest();
    }

    @Override
    public void insertInspection(Inspection inspection) {
        mapper.insertInspection(inspection);
    }

    @Override
    public void insertTest(Test test) {
        mapper.insertTest(test);
    }

    @Override
    public void insertPay(Pay pay) {
        mapper.insertPay(pay);
    }

    @Override
    public int countDoctorTestrecord() {
        return mapper.countDoctorTestrecord();
    }

    @Override
    public void insertDoctorTestrecord(DoctorTestrecode doctorTestrecode) {
            mapper.insertDoctorTestrecord(doctorTestrecode);
    }

    @Override
    public int countDoctorInspectionrecord() {
        return mapper.countDoctorInspectionrecord();
    }

    @Override
    public void insertDoctorInspectionrecord(DocotrInspectionrecord docotrInspectionrecord) {
        mapper.insertDoctorInspectionrecord(docotrInspectionrecord);
    }

    @Override
    public void updateTestalive(ConcurrentMap map) {
        mapper.updateTestalive(map);
    }

    @Override
    public void updateInspectionalive(ConcurrentMap map) {
        mapper.updateInspectionalive(map);
    }

    @Override
    public void updatepayalive(ConcurrentMap map) {
        mapper.updatepayalive(map);
    }

    @Override
    public CopyOnWriteArrayList<Drug> selectallDrug() {
        return mapper.selectallDrug();
    }

    @Override
    public CopyOnWriteArrayList<NoDrug> selectallNoDrug() {
        return mapper.selectallNoDrug();
    }

    @Override
    public void insertPayfromdrug(Pay pay) {
        mapper.insertPayfromdrug(pay);
    }

    @Override
    public void updateDrugNum(ConcurrentMap map) {
        mapper.updateDrugNum(map);
    }

    @Override
    public int countHandle() {
        return mapper.countHandle();
    }

    @Override
    public int countDoctorDrugrecord() {
        return mapper.countDoctorDrugrecord();
    }

    @Override
    public void insertDoctorDrugrecord(DoctorDrugrecord doctorDrugrecord) {
        mapper.insertDoctorDrugrecord(doctorDrugrecord);
    }

    @Override
    public void insertHandle(Handle handle) {
        mapper.insertHandle(handle);
    }

    @Override
    public CopyOnWriteArrayList<Medical_record> selectallTestAndInsprction(int mrid) {
        return mapper.selectallTestAndInsprction(mrid);
    }


}
