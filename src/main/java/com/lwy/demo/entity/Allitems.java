package com.lwy.demo.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 前端检查检验项目放到一起时 临时存储
 */
@Data
public class Allitems implements Serializable {
    private String id;
    private String medical;
    private BigDecimal money;
    private String name;
    private String pinyin;
    private int prrid;

}
