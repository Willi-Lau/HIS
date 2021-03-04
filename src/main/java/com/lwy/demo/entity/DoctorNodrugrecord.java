package com.lwy.demo.entity;


import lombok.Data;

import java.sql.Timestamp;

/**
 * 非药品医生记录表
 */
@Data
public class DoctorNodrugrecord {
    private String dndrid;
    private int dndrmrid;
    private int dndrduid;
    private String dndrndid;
    private Timestamp dndrtime;
}
