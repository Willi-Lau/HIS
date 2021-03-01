package com.lwy.demo.mapper;

import com.lwy.demo.entity.Diagnosis;
import com.lwy.demo.entity.Patient_Registration_record;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;

@Repository
public interface DoctorHomeMapper {

    /**
     * 根据医生姓名 日期 读取就诊还是没就诊
     *  map.put("today",today);   日期
     *         map.put("tomorrow",tomorrow);   下一天
     *         map.put("patientusername",patientusername);   患者名字(账号)
     *       map.put("doctorusername",doctorusername);   医生名字(账号)
     */
    CopyOnWriteArrayList<Patient_Registration_record> selectallPRrecode(ConcurrentMap map);
    /**
     * 根据患者挂号记录的id 查找挂号记录，和病历表
     */
    Patient_Registration_record selectPRRandMRbyid(int id);
    /**
     * 根据挂号id设置已经看诊
     */
    @Update("update patient_registration_record set prralready = 1 where prrid = #{prrid}")
    void updatePrralready(int prrid);


    /**
     * 根据挂号id修改病历里相关信息，主诉等信息
     */
    void updatePRRinf(ConcurrentMap map);
    /**
     * 查找所有疾病信息
     */
    @Select("select * from diagnosis")
     CopyOnWriteArrayList<Diagnosis> selectallDiagnosis();
    /**
     * 返回所有检查项目
     */


    /**
     * 返回所有检验项目
     */


}
