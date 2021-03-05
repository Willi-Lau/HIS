package com.lwy.demo.entity;


import io.swagger.annotations.Api;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Api("药品收费项目表")
public class Drug {
   private String drid;
   private String drname;
   private String drpinyin;
   private String drformat;
   private String drconsumption;
   private String drtype;
   private int drnum;
   private BigDecimal drmoney;
   private int dralive;
   private int drmedical;
   //一对一药品医生记录表
   private DoctorDrugrecord doctorDrugrecord;

}
