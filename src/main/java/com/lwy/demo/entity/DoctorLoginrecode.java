package com.lwy.demo.entity;

import io.swagger.annotations.ApiOperation;
import lombok.Data;

import java.sql.Timestamp;


@Data
@ApiOperation("医生登陆日志")
public class DoctorLoginrecode {

    private int dlrdid;
    private Timestamp dlrtime;
}
