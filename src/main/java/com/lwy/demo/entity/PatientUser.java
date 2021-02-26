package com.lwy.demo.entity;


import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;

@Data
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
