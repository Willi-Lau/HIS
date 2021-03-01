package com.lwy.demo.entity;


import io.swagger.annotations.ApiOperation;
import lombok.Data;


@Data
@ApiOperation("病大全")
public class Diagnosis {
    private int did;
    private String dname;
    private String dpinyin;
}
