package com.lwy.demo.entity;


import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;


/**
 * 药品医生记录表
 */
@Data
public class DoctorDrugrecord implements Serializable {
    private String ddrid;
    private int ddrmrid;
    private int ddrduid;
    private String ddrdid;
    private Timestamp ddrtime;
}
