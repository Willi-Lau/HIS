package com.lwy.demo.service;

import com.lwy.demo.entity.Diagnosis;
import com.lwy.demo.entity.Patient_Registration_record;

import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;

public interface DoctorHomeService {
    /**
     * 根据日期，医生姓名 查询 挂号历史记录表 以及病历表
     */
    CopyOnWriteArrayList<Patient_Registration_record> selectallPRrecode(ConcurrentMap map);
    /**
     * 根据挂号id查找挂号信息和病例信息
     */
    Patient_Registration_record selectPRRandMRbyid(int id);
    /**
     * 根据挂号idx修改患者已经就诊
     */
    void updatePrralready(int prrid);
    /**
     * 根据挂号id修改病历里相关信息，主诉等信息
     */
    void updatePRRinf(ConcurrentMap map);
    /**
     * 查找所有疾病信息
     */
    CopyOnWriteArrayList<Diagnosis> selectallDiagnosis();

}
