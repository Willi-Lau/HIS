package com.lwy.demo.controller;


import com.github.pagehelper.PageHelper;
import com.lwy.demo.entity.*;
import com.lwy.demo.service.DoctorHomeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import javax.management.remote.JMXServerErrorException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
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


    @RequestMapping("/selectallInspection")
    @ApiOperation("查找所有的检验信息")
    public CopyOnWriteArrayList<Inspection_items> selectallInspection() {
        return service.selectallInspection();
    }

    @RequestMapping("/selectalltest")
    @ApiOperation("查找所有的检查信息")
    public CopyOnWriteArrayList<Test_items> selectalltest() {
        return service.selectalltest();
    }

    @RequestMapping("/upAllitems")
    @ApiOperation("提交检查检验结果，到检查表，检验表，和消费记录")
    public void upAllitems( @RequestBody Allitems[] thisAllitems){

            String id = "";

            for (int i=0;i<thisAllitems.length;i++){
                //根据挂号id查找病历id
                int mrid = service.selectMRid(thisAllitems[i].getPrrid());
                //根据id种类不同 分类处理 存入检查表
                 if(thisAllitems[i].getId().startsWith("i")){
                     Inspection inspection = new Inspection();
                     //查找检验表有多少条数据 手动写入自增id
                     int countInspection = service.countInspection();
                     id = "in"+Integer.toString(countInspection+1);
                     inspection.setInid(id);
                     inspection.setInmrid(mrid);
                     inspection.setIndo(thisAllitems[i].getId());
                     inspection.setInend("未做检查无结果");
                     inspection.setInnum(1);
                     inspection.setIntime(new Timestamp(new Date().getTime()));
                     inspection.setInalive(0);
                     inspection.setIngivemoney(0);
                     inspection.setInused(0);

                     //统计检查医生表中有几条数据
                     int countDoctorInspectionrecord = service.countDoctorInspectionrecord();
                     //提交到检查医生表
                     DocotrInspectionrecord docotrInspectionrecord = new DocotrInspectionrecord();
                     docotrInspectionrecord.setDirid("dir"+Integer.toString(countDoctorInspectionrecord+1));
                     docotrInspectionrecord.setDirmrid(mrid);
                     docotrInspectionrecord.setDiriid(thisAllitems[i].getId());
                     service.insertDoctorInspectionrecord(docotrInspectionrecord);

                     inspection.setInwater(docotrInspectionrecord.getDirid());
                     //提交到检查表
                     service.insertInspection(inspection);
                 }
                 //存入检验表
                 else{
                     Test test = new Test();
                     //查找检验表有多少条数据 手动写入自增id
                     int countTest = service.countTest();
                     id = "te"+Integer.toString(countTest+1);
                     test.setTid(id);
                     test.setTmrid(mrid);
                     test.setTdo(thisAllitems[i].getId());
                     test.setTend("未做检查无结果");
                     test.setTnum(1);
                     test.setTtime(new Timestamp(new Date().getTime()));
                     test.setTalive(0);
                     test.setTgivemoney(0);
                     test.setTused(0);

                     //统计检验医生表中有几条数据
                     int countDoctorTestrecord = service.countDoctorTestrecord();
                     //提交到检验医生表  操作时间，操作医生为空
                     DoctorTestrecode doctorTestrecode = new DoctorTestrecode();
                     doctorTestrecode.setDtrid("dtr"+Integer.toString(countDoctorTestrecord+1));
                     doctorTestrecode.setDtrmrid(mrid);
                     doctorTestrecode.setDtrtid(thisAllitems[i].getId());
                     service.insertDoctorTestrecord(doctorTestrecode);

                     test.setTwater(doctorTestrecode.getDtrid());
                     //提交到检验表
                     service.insertTest(test);
                 }
                 //存入缴费表
                 Pay pay = new Pay();
                 pay.setPmrid(mrid);
                 pay.setProid(id);
                 pay.setPmoney( thisAllitems[i].getMoney());
                 pay.setPnum(1);
                 pay.setPallmoney(thisAllitems[i].getMoney());
                 pay.setPtime(new Timestamp(new Date().getTime()));
                 pay.setPtype("未选择");
                 pay.setPgivemoney(0);
                 pay.setPalive(0);
                 //提交到缴费表
                service.insertPay(pay);
            }
    }

    @RequestMapping("/selectallTestAndInsprction")
    @ApiOperation("根据病历id查找 检查，检验，缴费得信息")
    public CopyOnWriteArrayList<Medical_record> selectallTestAndInsprction(int prrid) {
        //根据 prrid 找到 mrid
        int mrid = service.selectMRid(prrid);
        //根据mrid 查找相关病历信息
        CopyOnWriteArrayList<Medical_record> list = service.selectallTestAndInsprction(mrid);
        //System.out.println(list.toString());
        return list;

    }

    @RequestMapping("/updateTestInspectionPay")
    @ApiOperation("根据病历id，所作项目id,和创建时间 把相对应alive设置为1")
    public void updateTestInspectionPay(int mrid,String id,String time) throws ParseException {
        ConcurrentMap map = new ConcurrentHashMap();

        //根据id 类型，是检查in 还是检验 t 来分类操作
        if(id.substring(0,1).equals("t")){
               map.put("mrid",mrid);
               map.put("id",id);
               service.updateTestalive(map);
        }
        else {
                map.put("mrid",mrid);
                map.put("id",id);
              service.updateInspectionalive(map);
        }
        //pay
        service.updatepayalive(map);


    }

    @RequestMapping("/selectallDrug")
    @ApiOperation("返回所有药品目录")
    public CopyOnWriteArrayList<Drug> selectallDrug() {
        return service.selectallDrug();
    }

    @RequestMapping("/selectallNoDrug")
    @ApiOperation("返回所有非药品目录")
    public CopyOnWriteArrayList<NoDrug> selectallNoDrug() {
        return service.selectallNoDrug();
    }

    @RequestMapping("/insertpayfromDrug")
    @ApiOperation("添加药品到支付列表")
    public void insertpayfromDrug(@RequestBody TableDateDrug[] tableDataDrug) {
//        (drid=dr18, drname=整肠生, drformat=盒, drconsumption=一日四次, drtype=无,
//        drnum=5555, dryounum=15, drmoney=15.5, drmedical=1, allmoney=232.5, prrid=4)

        for (TableDateDrug d : tableDataDrug){
            //根据prrid 找到Mrid
            int mrid = service.selectMRid(d.getPrrid());

            //提交到pay表
            Pay pay = new Pay();
            pay.setPmrid(mrid);
            pay.setProid(d.getDrid());
            pay.setPmoney(d.getDrmoney());
            pay.setPnum(d.getDryounum());
            pay.setPallmoney(d.getAllmoney());
            pay.setPtime(new Timestamp(new Date().getTime()));
            pay.setPtype("未选择");
            pay.setPgivemoney(0);
            pay.setPalive(0);
            //提交到pay表
            service.insertPayfromdrug(pay);

            //修改drug 数量
            ConcurrentMap map = new ConcurrentHashMap();
            map.put("drid",d.getDrid());
            map.put("num",d.getDryounum());
            service.updateDrugNum(map);

            //提交到DoctorDrugrecord 药品医生记录表
            //统计药品医生记录表数量
            int countDoctorDrugrecord = service.countDoctorDrugrecord();
            //添加到开药流水表里
            DoctorDrugrecord doctorDrugrecord = new DoctorDrugrecord();
            doctorDrugrecord.setDdrid("ddr"+Integer.toString(countDoctorDrugrecord+1));
            doctorDrugrecord.setDdrmrid(mrid);
            doctorDrugrecord.setDdrdid(d.getDrid());
            //提交
             service.insertDoctorDrugrecord(doctorDrugrecord);

            //提交到Handle表
            Handle handle = new Handle();
            //获取Handle表条数
            int countHandle = service.countHandle();
            handle.setHid("h"+Integer.toString(countHandle+1));
            handle.setHmrid(mrid);
            handle.setHdo(d.getDrid());
            handle.setHnum(d.getDryounum());
            handle.setHtime(new Timestamp(new Date().getTime()));
            handle.setHalive(0);
            handle.setHgivemoney(0);
            handle.setHused(0);
            handle.setHwater(doctorDrugrecord.getDdrid());
            //提交
            service.insertHandle(handle);


        }
    }





}
