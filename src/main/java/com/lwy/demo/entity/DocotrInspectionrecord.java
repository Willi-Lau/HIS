package com.lwy.demo.entity;


import lombok.Data;

import java.sql.Timestamp;

/**
 * 检查医生记录表
 */
@Data
public class DocotrInspectionrecord {
    private String dirid;
    private int dirmrid;
    private int dirduid;
    private String diriid;
    private Timestamp dirtime;
}
