package com.lwy.demo.controller;


import com.lwy.demo.entity.DoctorUser;
import com.lwy.demo.service.DoctorRegisterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CopyOnWriteArrayList;

@CrossOrigin
@RestController
@RequestMapping("/DoctorRegisterController")
@Api("医生注册界面")
public class DoctorRegisterController {

   @Autowired
    private DoctorRegisterService service;

   @ApiOperation("查找返回所有的科室名字")
   @RequestMapping("/selectalldepartment")
   public CopyOnWriteArrayList<String> selectalldepartment(){
       return service.selectalldepartment();
   }
    @ApiOperation("注册医生")
    @RequestMapping(value = "/registerdoctor",method = RequestMethod.POST)
   public String registerdoctor(@RequestBody DoctorUser doctorUser){
        //查找是否被注册 用户名
        String selectusername = service.selectusername(doctorUser.getDuusername());
        //查找身份证是否被注册过
        String selectcard = service.selectcard(doctorUser.getDucard());
        //查找手机是否被注册过
        String selectphone = service.selectphone(doctorUser.getDuphone());

        if(selectusername != null){
            System.out.println("username");
            return "username";
        }
        else if(selectcard != null){
            System.out.println("card");
            return "card";
        }
        else if(selectphone!= null){
            System.out.println("phone");
            return "phone";
        }
        else {
            System.out.println("s");
            //注册
            service.registerdoctor(doctorUser);
            return "success";
        }

   }


}
