package com.lwy.demo.entity;

import io.swagger.annotations.ApiOperation;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
@ApiOperation("患者登陆日志表")
public class PatientLoginrecord implements Serializable {
    private int plrpid;
    private Timestamp plrtime;
}
