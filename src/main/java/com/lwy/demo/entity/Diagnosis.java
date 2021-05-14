package com.lwy.demo.entity;


import io.swagger.annotations.ApiOperation;
import lombok.Data;

import java.io.Serializable;


@Data
@ApiOperation("病大全")
public class Diagnosis implements Serializable {
    private int did;
    private String dname;
    private String dpinyin;
}
