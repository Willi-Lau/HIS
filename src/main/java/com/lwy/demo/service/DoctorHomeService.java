package com.lwy.demo.service;

import com.lwy.demo.entity.*;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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
    /**
     * 返回所有检查项目
     */
    CopyOnWriteArrayList<Inspection_items> selectallInspection();
    /**
     * 返回所有检验项目
     */
    CopyOnWriteArrayList<Test_items> selectalltest();
    /**
     * 根据prrid 查找Mrid
     */
    int selectMRid(int prrid);
    /**
     * 查找检验表有多少条数据
     */
    int countInspection();
    /**
     * 查找检查表有多少条数据
     */
    int countTest();
    /**
     * 把提交得检验项目提交到检验表中   传入 挂号历史积极  t
     */
    void insertInspection(Inspection inspection);
    /**
     * 把提交得检查项目提交到检验表中
     */
    void insertTest(Test test);
    /**
     * 把检查检验项目提交到  缴费表
     */
    void insertPay(Pay pay);
    /**
     * 统计检验医生记录表条数 需要自定义
     */
    int countDoctorTestrecord();
    /**
     * 根据病历id查找 检查，检验，缴费得信息
     */
    CopyOnWriteArrayList<Medical_record> selectallTestAndInsprction(int mrid);
    /**
     * 插入数据到检验医生记录表
     */
    void insertDoctorTestrecord(DoctorTestrecode doctorTestrecode);
    /**
     * 统计检查医生记录表得条数
     */
    int countDoctorInspectionrecord();
    /**
     * 插入检查医生记录表中
     */
    void insertDoctorInspectionrecord(DocotrInspectionrecord docotrInspectionrecord);
    /**
     * 将检验表相关信息设置为停用
     */
    void updateTestalive(ConcurrentMap map);
    /**
     * 将检查表相关信息设置为停用
     */
    void updateInspectionalive(ConcurrentMap map);
    /**
     * 将付款表相关信息设置为停用
     */
    void updatepayalive(ConcurrentMap map);
    /**
     * 查找所有的药品信息*
     */
    CopyOnWriteArrayList<Drug> selectallDrug();

    /**
     * 查找所有的非药品信息
     */
    CopyOnWriteArrayList<NoDrug> selectallNoDrug();
    /**
     * 提交药品到支付记录
     */
    void insertPayfromdrug(Pay pay);
    /**
     * 根据药品id修改库存
     */
    void updateDrugNum(ConcurrentMap map);
    /**
     * 获取Handle表得个数
     */
    int countHandle();
    /**
     * 统计药品医生记录表数量
     */
    int countDoctorDrugrecord();
    /**
     * 添加到开药流水里 DoctorDrugrecord
     */
    void insertDoctorDrugrecord(DoctorDrugrecord doctorDrugrecord);

    /**
     * 添加到Hanlde
     * @param handle
     */
    void insertHandle(Handle handle);
    /**
     * 统计非药品流水表的条数
     */
    int countDoctorNoDrugRecord();
    /**
     * 根据mr 病历表查找医生Id
     */
    int selectDridByMR(int mrid);

    /**
     * 提交到非药品流水
     * @param doctorNodrugrecord
     */
    void insertDoctorNoDrugRedord(DoctorNodrugrecord doctorNodrugrecord);
    /**
     * 根据mrid返回患者所有非药品相关处理
     */
    CopyOnWriteArrayList<Handle>selectallHandle(int mrid);
    /**
     * 根据id 在药品清单查找名字
     */
    String selectNameByidFromDrug(String id);
    /**
     * 根据id 在非药品清单查找名字
     */
    String selectNameByidFromNoDrug(String id);
    /**
     * 向 Handle Drug Pay 表插入对应记录
     */
    void insertDrugOrHandleWithPayRecord(DrugOrHandleWithPayRecord drugOrHandleWithPayRecord);
    /**
     * 统计pay 表数据个数
     */
    int countPay();
}
