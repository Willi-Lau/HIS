package com.lwy.demo.entity;


import lombok.Data;

import java.sql.Timestamp;

/**
 * 病历表
 */
@Data
public class Medical_record {

    private int mrid;
    private int mrpid;
    private int mrdid;
    private Timestamp mrtime;
    private String mrchiefcomplaint;
    private String mrpresentIllness;
    private String mrpast;
    private String mrpersonal;
    private String mrfinal;
    //一对一医生表
    private DoctorUser doctorUser;
    //一对一患者表
    private PatientUser patientUser;


}
