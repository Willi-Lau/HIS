package com.lwy.demo.entity;

import lombok.Data;

import java.sql.Timestamp;

/**
 * 处理表
 *
 */
@Data
public class Handle {
    private String hid;
    private int hmrid;
    private String hdo;
    private int hnum;
    private Timestamp htime;
    private int halive;
    private int hgivemoney;
    private int hused;
    private String hwater;
}
