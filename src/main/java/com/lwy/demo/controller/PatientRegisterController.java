package com.lwy.demo.controller;


import com.lwy.demo.entity.PatientUser;
import com.lwy.demo.service.PatientRegisterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.sql.Timestamp;

@CrossOrigin
@RequestMapping("PatientRegisterController")
@RestController
@Api("患者注册界面")
public class PatientRegisterController {
    @Autowired
    private PatientRegisterService service;

    @ApiOperation("注册患者")
    @RequestMapping(value = "/registerpatient",method = RequestMethod.POST)
    public String registerpatient(@RequestBody PatientUser user){
        //设置创建时间
        user.setPucreate(new Timestamp(new Date().getTime()));
        //查找身份证是否被注册
        String card = service.selectpucard(user.getPucard());
        //查找用户名是否被注册
        String phone = service.selectpuphone(user.getPuphone());
        //查找账号手否被注册
        String username = service.selectpuusername(user.getPuusername());
        if(card != null){
            return "card";
        }
        else if(phone != null){
            return "phone";
        }
        else if(username != null){
            return "username";
        }
        else {
            //插入
            service.addpatientuser(user);
            return "success";
        }

    }

}
