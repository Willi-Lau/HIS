package com.lwy.demo.entity;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class PatientLoginrecord {
    private int plrpid;
    private Timestamp plrtime;
}
