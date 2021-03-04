package com.lwy.demo.entity;

import lombok.Data;

import java.sql.Timestamp;

/**
 * 检查表
 */
@Data
public class Inspection {
    private String inid;
    private int inmrid;
    private String indo;
    private String inend;
    private int innum;
    private Timestamp intime;
    private int inalive;
    private int inused;
    private int ingivemoney;
    private String inwater;
    //一对一检查医生记录表
    private DocotrInspectionrecord docotrInspectionrecord;
    //一对一检查项目列表
    private Inspection_items inspection_items;


}
