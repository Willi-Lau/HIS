package com.lwy.demo.entity;


import io.swagger.annotations.ApiOperation;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;

@Data
@ApiOperation("患者账户信息")
public class PatientUser {

    private int puid;
    private String puusername;
    private String pupassword;
    private String puname;
    private int pusex;
    private Date pubirthday;
    private String pucard;
    private String puaddress;
    private String puphone;
    private Timestamp pucreate;
    private String puhistory;
    private int pualive;

}
