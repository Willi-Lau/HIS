package com.lwy.demo.entity;


import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * 挂号记录表
 */
@Data
public class Patient_Registration_record implements Serializable {

    private int prrid;
    private int prrpuid;
    private Timestamp prrtime;
    private BigDecimal prrmoney;
    private int prrmrid;
    private int prrWithdrawal_number;
    private int prrdoid;
    private int prralready;
    //一对一 病例id
    private Medical_record medical_record;
}
