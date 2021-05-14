package com.lwy.demo.entity;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;


/**
 * 检验医生记录表
 */
@Data
public class DoctorTestrecode implements Serializable {

    private String dtrid;
    private int dtrmrid;
    private int dtrduid;
    private String  dtrtid;
    private Timestamp dtrtime;
}
