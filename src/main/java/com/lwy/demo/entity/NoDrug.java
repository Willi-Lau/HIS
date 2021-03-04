package com.lwy.demo.entity;


import lombok.Data;

import java.math.BigDecimal;

/**
 * 非药品
 */
@Data
public class NoDrug {
    private String nid;
    private String nname;
    private String npinyin;
    private String nformat;
    private BigDecimal nmoney;
    private int nalive;
    private int nmedical;
    //一对一非药品医生记录表
    private DoctorNodrugrecord doctorNodrugrecord;
}
