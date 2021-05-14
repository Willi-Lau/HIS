package com.lwy.demo.entity;

import io.swagger.annotations.Api;
import lombok.Data;

import java.io.Serializable;

@Data
@Api("药品非药品和Pay表对应的信息")
public class DrugOrHandleWithPayRecord implements Serializable {
    private String pid;
    private String dhid;
}
