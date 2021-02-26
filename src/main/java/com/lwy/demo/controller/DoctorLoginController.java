package com.lwy.demo.controller;


import com.lwy.demo.entity.DoctorLoginrecode;
import com.lwy.demo.service.DoctorLoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@RestController
@RequestMapping("/DoctorLoginController")
@CrossOrigin
@Api("用户登陆")
public class DoctorLoginController {
    @Autowired
    private DoctorLoginService service;

    @RequestMapping("/doctorlogin")
    @ApiOperation("用户登陆验证返回工作类型")
    public String doctorlogin(String username,String password){
        ConcurrentMap map = new ConcurrentHashMap();
        map.put("username",username);
        map.put("password",password);
        String doctorlogin = service.doctorlogin(map);
        if(doctorlogin != null){
                //根据username 查找id
                int id = service.selectid(username);
                //记录登陆时间
                DoctorLoginrecode log = new DoctorLoginrecode();
                log.setDlrdid(id);
                log.setDlrtime(new Timestamp(new Date().getTime()));
                service.addrecord(log);
            return doctorlogin;
        }
        else {
            return "no";
        }


    }
}
