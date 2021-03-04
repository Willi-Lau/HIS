package com.lwy.demo.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * 缴费表
 */
@Data
public class Pay {
    private int pid;
    private int pmrid;
    private String proid;
    private BigDecimal pmoney;
    private int pnum;
    private BigDecimal pallmoney;
    private Timestamp ptime;
    private String ptype;
    private int pgivemoney;
    private int palive;
}
