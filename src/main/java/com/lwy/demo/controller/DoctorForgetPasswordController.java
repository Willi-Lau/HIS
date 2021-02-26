package com.lwy.demo.controller;

import com.lwy.demo.entity.DoctorUser;
import com.lwy.demo.service.DoctorForgetPasswordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;

@CrossOrigin
@RestController
@RequestMapping("/DoctorForgetPasswordController")
@Api("医生找回密码界面")
public class DoctorForgetPasswordController {

    @Autowired
    private DoctorForgetPasswordService service;

     @ApiOperation("验证修改密码时提交的电话号或身份证是否正确")
     @RequestMapping("/selectforgetpasswordinf")
    public String  selectforgetpasswordinf(String username,String key,String value){

        ConcurrentMap<String, String> map = new ConcurrentHashMap<>();
        map.put("username",username);
        map.put("key",key);
        map.put("value",value);

         DoctorUser duid = service.selectforgetpasswordinf(map);
         //NullPointException
         System.out.println(duid.toString());
        //查询到结果
        if(duid.getDuusername() != null){
            return "success";
        }
        else {
            return "no";
        }
    };


}
