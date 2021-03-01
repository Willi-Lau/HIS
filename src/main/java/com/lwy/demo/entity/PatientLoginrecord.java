package com.lwy.demo.entity;

import io.swagger.annotations.ApiOperation;
import lombok.Data;

import java.sql.Timestamp;

@Data
@ApiOperation("患者登陆日志表")
public class PatientLoginrecord {
    private int plrpid;
    private Timestamp plrtime;
}
