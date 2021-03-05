package com.lwy.demo.entity;


import io.swagger.annotations.Api;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Api("临时存储前端传递过来的医药药品清单")
public class TableDateDrug {
    private String drid;
    private String drname;
    private String drformat;
    private String drconsumption;
    private String drtype;
    private int drnum;
    private int dryounum;
    private BigDecimal drmoney;
    private int drmedical;
    private BigDecimal allmoney;
    private int prrid;
}
