package com.lwy.demo.entity;

import io.swagger.annotations.Api;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Api("临时存储非药品记录表")
public class TableDateNoDrug {

    private String nid;
    private String nname;
    private String pinyin;
    private String nformat;
    private BigDecimal nmoney;
    private int nmediacl;
    private int nnum;
    private BigDecimal nallmoney;
    private int prrid;


}
