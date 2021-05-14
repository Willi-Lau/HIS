package com.lwy.demo.entity;


import io.swagger.annotations.ApiOperation;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiOperation("医生账户信息")
public class DoctorUser implements Serializable {
    private int duid;
    private String duusername;
    private String dupassword;
    private String duname;
    private int dusex;
    private String ducard;
    private String duphone;
    private String duaddress;
    private String dudepartmentname;
    private String dutype;
    private String dutitle;
    private int dualive;
}
