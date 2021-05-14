package com.lwy.demo.entity;

import io.swagger.annotations.Api;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Api("检验项目表")
public class Test_items implements Serializable {
    private String tiid;
    private String tiname;
    private String tipinyin;
    private BigDecimal timoney;
    private int timedical;
}
