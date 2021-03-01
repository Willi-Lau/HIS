package com.lwy.demo.controller;


import com.github.pagehelper.PageHelper;
import com.lwy.demo.entity.Diagnosis;
import com.lwy.demo.entity.Patient_Registration_record;
import com.lwy.demo.service.DoctorHomeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;

@RestController
@RequestMapping("/DoctorHomeController")
@CrossOrigin
@Api("医生登陆主界面，操控各种各样的患者")
public class DoctorHomeController {

    @Autowired
    private DoctorHomeService service;


    @RequestMapping("/selectallPRrecode")
    @ApiOperation("未就诊/就诊 根据患者名字，医生名字，日期查询")
    CopyOnWriteArrayList<Patient_Registration_record> selectallPRrecode(String patientusername,
                                                                        String today,
                                                                        String doctorusername,
                                                                        int prralready,
                                                                        @RequestParam(defaultValue = "1") int pageNo,
                                                                        @RequestParam(defaultValue = "4") int pageSize) throws ParseException {
        Date today1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd");
        if(today == null || today.length()==0 || today == ""){
            today = simpleDateFormat.format(today1);
        }
        //获取传递进来的下一天
        String s1 = today.substring(0, 8);
        String tomorrow = s1 + Integer.toString(Integer.parseInt(today.substring(8)) + 1);
        ConcurrentHashMap<Object, Object> map = new ConcurrentHashMap<>();
        map.put("today",today);
        map.put("tomorrow",tomorrow);
        map.put("patientusername",patientusername);
        map.put("doctorusername",doctorusername);
        //判断就诊否
        map.put("prralready",prralready);
        PageHelper.startPage(pageNo,pageSize);
        return service.selectallPRrecode(map);
    }
    @RequestMapping("/selectallPRrecodecount")
    @ApiOperation("未就诊/就诊 根据患者名字，医生名字，日期查询  查询总条数")
    int selectallPRrecodecount(String patientusername,String today,String doctorusername,int prralready) throws ParseException {
        Date today1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd");
        if(today == null || today.length()==0 || today == ""){
            today = simpleDateFormat.format(today1);
        }
        //获取传递进来的下一天
        String s1 = today.substring(0, 8);
        String tomorrow = s1 + Integer.toString(Integer.parseInt(today.substring(8)) + 1);
        ConcurrentHashMap<Object, Object> map = new ConcurrentHashMap<>();
        map.put("today",today);
        map.put("tomorrow",tomorrow);
        map.put("patientusername",patientusername);
        map.put("doctorusername",doctorusername);
        //判断就诊否
        map.put("prralready",prralready);
        int size = service.selectallPRrecode(map).size();

        return size;

    }

    @RequestMapping("/selectPRRandMRbyid")
    @ApiOperation("根据挂号id查找挂号信息，病例信息")
    public Patient_Registration_record selectPRRandMRbyid(int id) {
        return service.selectPRRandMRbyid(id);
    }

    @RequestMapping("/updatePrralready")
    @ApiOperation("根据挂号id更改已经就诊")
    public void updatePrralready(int prrid) {
        service.updatePrralready(prrid);
    }
    @RequestMapping("/updatePRRinf")
    @ApiOperation("根据挂号id修改病历里相关信息，主诉等信息")
    public void updatePRRinf(int prrid,String mrchiefcomplaint,String mrpresentIllness,String mrpast,
                             String mrpersonal,String mrfinal) {
        ConcurrentMap<String, Object> map = new ConcurrentHashMap<>();
        map.put("prrid",prrid);
        map.put("mrchiefcomplaint",mrchiefcomplaint);
        map.put("mrpresentIllness",mrpresentIllness);
        map.put("mrpast",mrpast);
        map.put("mrpersonal",mrpersonal);
        map.put("mrfinal",mrfinal);

        service.updatePRRinf(map);
    }

    @RequestMapping("/selectallDiagnosis")
    @ApiOperation("查找所有的疾病信息")
    public CopyOnWriteArrayList<Diagnosis> selectallDiagnosis() {
        return service.selectallDiagnosis();
    }






}
