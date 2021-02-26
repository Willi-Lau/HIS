package com.lwy.demo.controller;


import com.lwy.demo.service.DoctorChangePasswordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@CrossOrigin
@RestController
@RequestMapping("/DoctorChangePasswordController")
@Api("医生修改密码界面")
public class DoctorChangePasswordController {

    @Autowired
    private DoctorChangePasswordService service;

    @RequestMapping("/changepassword")
    @ApiOperation("根据username 修改密码")
    public void changepassword(String username,String password) {
        ConcurrentMap map = new ConcurrentHashMap();
        map.put("username",username);
        map.put("password",password);

        service.changepassword(map);
    }
}
