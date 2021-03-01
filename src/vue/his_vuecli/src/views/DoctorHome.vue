<template>
    <div class="doctorhome">
<!--        侧边栏  显示所有桓泽和的挂号信息-->
        <div class="doctorhome_lect">
<!--            采用卡片模式-->
            <el-card class="box-card">
<!--            搜索，日期转换，根据日期选择挂号  + 根据患者姓名搜索患者信息
                姓名为空默认搜索 所有 日期为空默认搜索今天
   -->
                <div class="doctorhome_lect_form">
                    <el-form :inline="true" :model="this.doctorhomeform" class="demo-form-inline" size="mini">
                        <el-form-item label="患者姓名">
                            <el-input v-model="doctorhomeform.username" placeholder="" style="width: 80px;"></el-input>
                        </el-form-item>
                        <el-form-item label="检查时间" >
                            <el-col :span="11">
                                <el-date-picker
                                        type="date"
                                        value-format="yyyy-MM-dd"
                                        placeholder="选择日期" v-model="doctorhomeform.date" style="width: 130px"></el-date-picker>
                            </el-col>
                        </el-form-item>
                        <el-form-item>
                            <el-button type="primary" @click="onSubmit">查询</el-button>
                        </el-form-item>
                    </el-form>
                </div>


<!--                未诊患者-->
                <div class="doctorhome_lect_no" >

                        未诊患者
                        <el-table
                                :data="noform"
                                style="width: 100%"
                                size="mini"
                                :cell-style="{padding:'1px 0'}"


                        >
                            <el-table-column
                                    prop="prrid"
                                    label="挂号id"
                                    width="120"
                            />
                            <el-table-column
                                    prop="medical_record.patientUser.puname"
                                    label="姓名"
                                    width="120"
                            />
                            <el-table-column
                                    prop=""
                                    label="操作"
                                    width="180">
                                <template slot-scope="scope">
                                    <el-button type="primary" size="min" plain @click="bosschange(scope.row.medical_record.patientUser.puname,scope.row.prrid)">选择</el-button>
                                    <!--                         获取索引-->
                                    <!--                         {{scope.$index}}-->
                                </template>
                            </el-table-column>



                            </el-table>
                    <div class="block">

                        <el-pagination
                                @current-change="handleSizeChange"
                                :current-page="noformpageNo"
                                :page-size="4"
                                layout="total, prev, pager, next, jumper"
                                :total="noformtotal">
                        </el-pagination>
                    </div>


                </div>

<!--                已疹患者-->
                <div class="doctorhome_lect_yes" >
                    已诊患者
                    <el-table
                            :data="form"
                            style="width: 100%"
                            size="mini"
                            :cell-style="{padding:'2px 0'}"

                    >
                        <el-table-column
                                prop="prrid"
                                label="挂号id"
                                width="120"

                        />
                        <el-table-column
                                prop="medical_record.patientUser.puname"
                                label="姓名"
                                width="120"
                        />

                        <el-table-column
                                prop=""
                                label="操作"
                                width="180">
                            <template slot-scope="scope">
                                <el-button type="primary" size="mini" plain @click="bosschange(scope.row.medical_record.patientUser.puname,scope.row.prrid)">选择</el-button>

                                <!--                         获取索引-->
                                <!--                         {{scope.$index}}-->
                            </template>

                        </el-table-column>



                    </el-table>
                    <div class="block">

                        <el-pagination
                                @current-change="handleSizeChange2"
                                :current-page="formpageNo"
                                :page-size="4"
                                layout="total, prev, pager, next, jumper"
                                :total="formtotal">
                        </el-pagination>
                    </div>


                </div>


            </el-card>

<!--            右侧部分-->



        </div>
        <div class="doctorhome_right">
            <el-tabs v-model="activeName" type="card" @tab-click="handleClick" class="doctorhome_right_tabs">
                <el-tab-pane label="首页" name="first">主页！！！！！！</el-tab-pane>
                <el-tab-pane label="患者信息及病历" name="second">
<!--                             患者病例可修改部分 诊断结果等-->
                    <div class="doctorhome_right_tabs_patientinf_form">
                        <el-form ref="thisPagePatientPRRandMR" :model="thisPagePatientPRRandMR" label-width="80px">
<!--                            主诉-->
                            <el-form-item label="主诉">
                                <el-input
                                        label="主诉"
                                        type="textarea"
                                        :rows="3"
                                        placeholder="请输入内容"
                                        v-model="thisPagePatientPRRandMR.medical_record.mrchiefcomplaint">
                                </el-input>
                            </el-form-item>
<!--                            现病史-->
                            <el-form-item label="现病史">
                                <el-input
                                        label="现病史"
                                        type="textarea"
                                        :rows="3"
                                        placeholder="请输入内容"
                                        v-model="thisPagePatientPRRandMR.medical_record.mrpresentIllness">
                                </el-input>
                            </el-form-item>
<!--                            既往史-->
                            <el-form-item label="既往史">
                                <el-input
                                        label="既往史"
                                        type="textarea"
                                        :rows="3"
                                        placeholder="请输入内容"
                                        v-model="thisPagePatientPRRandMR.medical_record.mrpast">
                                </el-input>
                            </el-form-item>
<!--                            个人史-->
                            <el-form-item label="个人史">
                                <el-input
                                        label="个人史"
                                        type="textarea"
                                        :rows="3"
                                        placeholder="请输入内容"
                                        v-model="thisPagePatientPRRandMR.medical_record.mrpersonal">
                                </el-input>
                            </el-form-item>
<!--                            诊断结果-->
                            <el-form-item label="诊断结果">
                                <el-input
                                        label="诊断结果"
                                        type="textarea"
                                        :rows="3"
                                        placeholder="请输入内容"
                                        v-model="thisPagePatientPRRandMR.medical_record.mrfinal">
                                </el-input>
                            </el-form-item>
<!--                            固定信息部分-->
                            <div class="doctorhome_right_tabs_patientinf_form_r">
<!--                              姓名，性别，出生日期，过敏史等其他疾病，挂号时间，是否就诊-->
                                <el-form-item label="姓名">
                                    <el-input
                                            placeholder="请输入内容"
                                            v-model="thisPagePatientPRRandMR.medical_record.patientUser.puname"
                                            :disabled="true">
                                    </el-input>
                                </el-form-item>
                                <el-form-item label="性别">
                                    <el-input
                                            placeholder="请输入内容"
                                            v-model="thisPagePatientPRRandMR.medical_record.patientUser.pusex"
                                            :disabled="true">
                                    </el-input>
                                </el-form-item>
                                <el-form-item label="出生日期">
                                    <el-input
                                            placeholder="请输入内容"
                                            v-model="thisPagePatientPRRandMR.medical_record.patientUser.pubirthday.substring(0,10)"
                                            :disabled="true">
                                    </el-input>
                                </el-form-item>
                                <el-form-item label="过敏史">
                                    <el-input
                                            placeholder="请输入内容"
                                            v-model="thisPagePatientPRRandMR.medical_record.patientUser.puhistory"
                                            :disabled="true">
                                    </el-input>
                                </el-form-item>
                                <el-form-item label="挂号时间">
                                    <el-input
                                            placeholder="请输入内容"
                                            v-model="thisPagePatientPRRandMR.prrtime"
                                            :disabled="true">
                                    </el-input>
                                </el-form-item>
                                <el-form-item label="就诊否">
                                    <el-input
                                            placeholder="请输入内容"
                                            v-model="thisPagePatientPRRandMR.prralready"
                                            :disabled="true">
                                    </el-input>
                                </el-form-item>



                            </div>


                            <el-form-item>
<!--                                把左侧主诉，现病史，诊断结果等提交-->
                                <el-button type="primary" @click="updateLook">提交信息</el-button>
                                <el-button type="success" @click="beginLook">开始就诊</el-button>
                            </el-form-item>

                        </el-form>
<!--                        所有疾病的信息-->
                     <div class="doctorhome_right_tabs_patientinf_diagnosisform">
                         <el-form :inline="true" :model="choosedaignosis">
                             <el-form-item label="疾病快捷选择">
                                 <el-select v-model="choosedaignosis.diagnosis" filterable placeholder="请选择">
                                     <el-option
                                             v-for="item in daignosis"
                                             :key="item.dname"
                                             :label="item.dname + item.dpinyin"
                                             :value="item.dname">
                                     </el-option>
                                 </el-select>
                             </el-form-item>

                             <el-form-item>
                                 <el-button type="success" plain @click="adddiagnosisform">选择添加进诊断结果</el-button>
                             </el-form-item>
                         </el-form>
                     </div>

                    </div>



                </el-tab-pane>
                <el-tab-pane label="检查检验" name="third">
                    <el-tabs type="border-card">
                        <el-tab-pane label="检查">用户管理</el-tab-pane>
                        <el-tab-pane label="检验">配置管理</el-tab-pane>
                    </el-tabs>
                </el-tab-pane>
                <el-tab-pane label="检查检验结果及治疗" name="fourth">结果</el-tab-pane>
            </el-tabs>
        </div>


    </div>
</template>

<script>



    export default {
        data(){
          return{
              inf:'',
              activeName:'first',
              doctorusername:this.$route.query.username,
              doctorhomeform:{
                  username:'',
                  date:''
              }
              ,
              //所有疾病信息
              daignosis:[
                  {did:'',
                  dname:'',
                  dpinyin:''}
              ],
              //选择的疾病信息:
              choosedaignosis: {
                  diagnosis: ''
              }
              ,
              //没就诊的患者
              noform:{
                  medical_record:{
                      mrchiefcomplaint:'',
                      mrdid:'',
                      mrpresentIllness:'',
                      mrfinal:'',
                      mrid:'',
                      mrpast:'',
                      mrpersonal:'',
                      mrpid:'',
                      mrtime:'',
                      patientUser:{
                          puaddress:'',
                          pualive:'',
                          pubirthday:'',
                          pucard:'',
                          pucreate:'',
                          puhistory:'',
                          puid:'',
                          puname:'',
                          pupassword:'',
                          puphone:'',
                          pusex:'',
                          puusername:''
                      }
                  },
                  prrWithdrawal_number:'',
                  prralready:'',
                  prrdoid:'',
                  prrid:'',
                  prrmoney:'',
                  prrmrid:'',
                  prrpuid:'',
                  prrtime:''
              },
              //就诊患者
              form:{
                  medical_record:{
                      mrchiefcomplaint:'',
                      mrdid:'',
                      mrpresentIllness:'',
                      mrfinal:'',
                      mrid:'',
                      mrpast:'',
                      mrpersonal:'',
                      mrpid:'',
                      mrtime:'',
                      patientUser:{
                          puaddress:'',
                          pualive:'',
                          pubirthday:'',
                          pucard:'',
                          pucreate:'',
                          puhistory:'',
                          puid:'',
                          puname:'',
                          pupassword:'',
                          puphone:'',
                          pusex:'',
                          puusername:''
                      }
                  },
                  prrWithdrawal_number:'',
                  prralready:'',
                  prrdoid:'',
                  prrid:'',
                  prrmoney:'',
                  prrmrid:'',
                  prrpuid:'',
                  prrtime:''
              },
              //没就诊患者总数
              noformtotal:0,
              noformpageNo:1,
              //就诊总书
              formtotal:0,
              formpageNo:1,
              //选中此次病例的id
              thisPRRid:'',
              //选中患者的名字
              thisPagePatientname:'',
              //患者挂号，病例信息
              thisPagePatientPRRandMR:{
                  medical_record:{
                      mrchiefcomplaint:'',
                      mrdid:'',
                      mrpresentIllness:'',
                      mrfinal:'',
                      mrid:'',
                      mrpast:'',
                      mrpersonal:'',
                      mrpid:'',
                      mrtime:'',
                      patientUser:{
                          puaddress:'',
                          pualive:'',
                          pubirthday:'',
                          pucard:'',
                          pucreate:'',
                          puhistory:'',
                          puid:'',
                          puname:'',
                          pupassword:'',
                          puphone:'',
                          pusex:'',
                          puusername:''
                      }
                  },
                  prrWithdrawal_number:'',
                  prralready:'',
                  prrdoid:'',
                  prrid:'',
                  prrmoney:'',
                  prrmrid:'',
                  prrpuid:'',
                  prrtime:''

              }

          }
        },
        components: {

        }
        ,created() {
            //查找所有疾病的信息 selectallDiagnosis
            this.$axios.post('DoctorHomeController/selectallDiagnosis',
                this.$qs.stringify(
                    {
                    }
                )
            ).then(response => {      //返回值部分
                 this.daignosis = response.data;
            }).catch(error => {
                console.log(error)
            })
        },
        methods:{
            //选择的疾病添加到诊断结果
            adddiagnosisform(){
                // alert(this.choosedaignosis.diagnosis)
                //如果现在有患者才才能添加不然不能添加
                if(this.thisPRRid === ''){
                    this.$alert('', '选择失败！请选择患者', {
                        confirmButtonText: '确定',
                        callback: action => {
                            this.$message({
                                type: 'info',
                                message: `no`
                            });
                        }
                    });
                }
                else {
                    this.thisPagePatientPRRandMR.medical_record.mrfinal = this.thisPagePatientPRRandMR.medical_record.mrfinal + this.choosedaignosis.diagnosis
                }
            },
            //根据挂号id 修改主诉等信息
            updateLook(){
                if(this.thisPagePatientPRRandMR.prrWithdrawal_number === 1){
                    this.$alert('', '提交信息失败！此患者已经退号', {
                        confirmButtonText: '确定',
                        callback: action => {
                            this.$message({
                                type: 'info',
                                message: `no`
                            });
                        }
                    });
                }
                else if(
                    this.thisPagePatientPRRandMR.prralready === '未就诊'
                ){
                    this.$alert('', '提交信息失败！此患者未开始就诊，无法提交信息', {
                        confirmButtonText: '确定',
                        callback: action => {
                            this.$message({
                                type: 'info',
                                message: `no`
                            });
                        }
                    });
                }
                else {
                    //提交到后台
                    this.$axios.post('DoctorHomeController/updatePRRinf',
                        this.$qs.stringify(
                            {
                                prrid:this.thisPRRid,
                                mrchiefcomplaint:this.thisPagePatientPRRandMR.medical_record.mrchiefcomplaint,
                                mrpresentIllness:this.thisPagePatientPRRandMR.medical_record.mrpresentIllness,
                                mrpast:this.thisPagePatientPRRandMR.medical_record.mrpast,
                                mrpersonal:this.thisPagePatientPRRandMR.medical_record.mrpersonal,
                                mrfinal:this.thisPagePatientPRRandMR.medical_record.mrfinal,
                            }
                        )
                    ).then(response => {      //返回值部分
                        this.$alert('', '提交信息成功', {
                            confirmButtonText: '确定',
                            callback: action => {
                                this.$message({
                                    type: 'info',
                                    message: `yes`
                                });
                            }
                        });
                    }).catch(error => {
                        console.log(error)
                    })
                }

            },
            //开始看诊 没退号才可以看诊，退号了就不可以看诊
            beginLook(){
                 if(this.thisPagePatientPRRandMR.prrWithdrawal_number === 1){
                     this.$alert('', '开始就诊失败！此患者已经退号', {
                         confirmButtonText: '确定',
                         callback: action => {
                             this.$message({
                                 type: 'info',
                                 message: `no`
                             });
                         }
                     });
                 }else {

                    //设置看诊否为看诊
                    this.thisPagePatientPRRandMR.prralready = '已经就诊'
                    //传递到后台
                    this.$axios.post('DoctorHomeController/updatePrralready',
                        this.$qs.stringify(
                            {
                                prrid:this.thisPRRid
                            }
                        )
                    ).then(response => {      //返回值部分

                    }).catch(error => {
                        console.log(error)
                    })

                    //设置左侧模块把患者从未就诊模块移动到已经就诊模块
    //未就诊
                    this.$axios.post('DoctorHomeController/selectallPRrecode',
                        this.$qs.stringify(
                            {
                                patientusername:this.doctorhomeform.username,
                                today: this.doctorhomeform.date,
                                doctorusername:this.doctorusername,
                                prralready:0,
                                pageNo:1

                            }
                        )
                    ).then(response => {      //返回值部分
                        console.log(response.data)
                        this.noform = response.data
                        //未挂号会名字后面追加 一个(退)  退号只有在未看诊时才可以执行
                        for(let i=0;i<this.noform.length;i++){
                            if(this.noform[i].prrWithdrawal_number === 1){

                                this.noform[i].medical_record.patientUser.puname = this.noform[i].medical_record.patientUser.puname + '(退)'
                            }

                        }


                    }).catch(error => {
                        console.log(error)
                    })
                    //未就诊总数
                    this.$axios.post('DoctorHomeController/selectallPRrecodecount',
                        this.$qs.stringify(
                            {
                                patientusername:this.doctorhomeform.username,
                                today: this.doctorhomeform.date,
                                doctorusername:this.doctorusername,
                                prralready:0

                            }
                        )
                    ).then(response => {      //返回值部分
                        console.log(response.data)
                        this.noformtotal = response.data


                    }).catch(error => {
                        console.log(error)
                    })
                    //就诊
                    this.$axios.post('DoctorHomeController/selectallPRrecode',
                        this.$qs.stringify(
                            {
                                patientusername:this.doctorhomeform.username,
                                today: this.doctorhomeform.date,
                                doctorusername:this.doctorusername,
                                prralready:1,
                                pageNo:1

                            }
                        )
                    ).then(response => {      //返回值部分
                        console.log(response.data)
                        this.form = response.data


                    }).catch(error => {
                        console.log(error)
                    })
                    //就诊总数
                    this.$axios.post('DoctorHomeController/selectallPRrecodecount',
                        this.$qs.stringify(
                            {
                                patientusername:this.doctorhomeform.username,
                                today: this.doctorhomeform.date,
                                doctorusername:this.doctorusername,
                                prralready:1

                            }
                        )
                    ).then(response => {      //返回值部分
                        console.log(response.data)
                        this.formtotal = response.data


                    }).catch(error => {
                        console.log(error)
                    })
                 }
            },
            //点击选中 左侧信息
            bosschange(puname,prrid){
                this.thisPagePatientname = puname
                this.thisPRRid = prrid;
                //返回患者病例 挂号信息
                this.$axios.post('DoctorHomeController/selectPRRandMRbyid',
                    this.$qs.stringify(
                        {
                            id:prrid
                        }
                    )
                ).then(response => {      //返回值部分
                    console.log(response.data)
                    this.thisPagePatientPRRandMR = response.data
                    if(this.thisPagePatientPRRandMR.medical_record.patientUser.pusex === 1){
                        this.thisPagePatientPRRandMR.medical_record.patientUser.pusex = '男'
                    }
                    else {
                        this.thisPagePatientPRRandMR.medical_record.patientUser.pusex = '女'
                    }

                    if(this.thisPagePatientPRRandMR.prralready === 1){
                        this.thisPagePatientPRRandMR.prralready = '已经就诊'
                    }
                    else {
                        this.thisPagePatientPRRandMR.prralready = '未就诊'
                    }

                    if(this.thisPagePatientPRRandMR.prrWithdrawal_number === 1){
                        this.thisPagePatientPRRandMR.medical_record.patientUser.puname = this.thisPagePatientPRRandMR.medical_record.patientUser.puname+'(退)'
                    }
                }).catch(error => {
                    console.log(error)
                })
            },
            //未就诊换页
            handleSizeChange(val) {
                this.$axios.post('DoctorHomeController/selectallPRrecode',
                    this.$qs.stringify(
                        {
                            patientusername:this.doctorhomeform.username,
                            today: this.doctorhomeform.date,
                            doctorusername:this.doctorusername,
                            prralready:0,
                            pageNo:val
                        }
                    )
                ).then(response => {      //返回值部分
                    console.log(response.data)
                    this.noform = response.data
                    //未挂号会名字后面追加 一个(退)  退号只有在未看诊时才可以执行
                    for(let i=0;i<this.noform.length;i++){
                        if(this.noform[i].prrWithdrawal_number === 1){

                            this.noform[i].medical_record.patientUser.puname = this.noform[i].medical_record.patientUser.puname + '(退)'
                        }

                    }


                }).catch(error => {
                    console.log(error)
                })
            },
            //就诊换页
            handleSizeChange2(val) {
                this.$axios.post('DoctorHomeController/selectallPRrecode',
                    this.$qs.stringify(
                        {
                            patientusername:this.doctorhomeform.username,
                            today: this.doctorhomeform.date,
                            doctorusername:this.doctorusername,
                            prralready:1,
                            pageNo:val

                        }
                    )
                ).then(response => {      //返回值部分
                    console.log(response.data)
                    this.form = response.data
                }).catch(error => {
                    console.log(error)
                })
            },
              //根据日期 医生id查询
            onSubmit(){
                //未就诊
                this.$axios.post('DoctorHomeController/selectallPRrecode',
                    this.$qs.stringify(
                        {
                            patientusername:this.doctorhomeform.username,
                            today: this.doctorhomeform.date,
                            doctorusername:this.doctorusername,
                            prralready:0,
                            pageNo:1

                        }
                    )
                ).then(response => {      //返回值部分
                    console.log(response.data)
                    this.noform = response.data
                    //未挂号会名字后面追加 一个(退)  退号只有在未看诊时才可以执行
                    for(let i=0;i<this.noform.length;i++){
                        if(this.noform[i].prrWithdrawal_number === 1){

                            this.noform[i].medical_record.patientUser.puname = this.noform[i].medical_record.patientUser.puname + '(退)'
                        }

                    }


                }).catch(error => {
                    console.log(error)
                })
                //未就诊总数
                this.$axios.post('DoctorHomeController/selectallPRrecodecount',
                    this.$qs.stringify(
                        {
                            patientusername:this.doctorhomeform.username,
                            today: this.doctorhomeform.date,
                            doctorusername:this.doctorusername,
                            prralready:0

                        }
                    )
                ).then(response => {      //返回值部分
                    console.log(response.data)
                    this.noformtotal = response.data


                }).catch(error => {
                    console.log(error)
                })
                //就诊
                this.$axios.post('DoctorHomeController/selectallPRrecode',
                    this.$qs.stringify(
                        {
                            patientusername:this.doctorhomeform.username,
                            today: this.doctorhomeform.date,
                            doctorusername:this.doctorusername,
                            prralready:1,
                            pageNo:1

                        }
                    )
                ).then(response => {      //返回值部分
                    console.log(response.data)
                    this.form = response.data


                }).catch(error => {
                    console.log(error)
                })
                //就诊总数
                this.$axios.post('DoctorHomeController/selectallPRrecodecount',
                    this.$qs.stringify(
                        {
                            patientusername:this.doctorhomeform.username,
                            today: this.doctorhomeform.date,
                            doctorusername:this.doctorusername,
                            prralready:1

                        }
                    )
                ).then(response => {      //返回值部分
                    console.log(response.data)
                    this.formtotal = response.data


                }).catch(error => {
                    console.log(error)
                })

            }
        }
    }
</script>
<style>
    .doctorhome_lect{
        position: absolute;
        width: 475px;
        height: 700px;
        /*background-color: #42b983;*/
    }
    .doctorhome_lect_no{
        /*background-color: #42b983;*/
        height: 300px;
        border-top: 1px solid black;
    }
    .doctorhome_lect_yes{
        /*background-color: #2b8ea7;*/
        height: 300px;
        border-top: 1px solid black;
    }

    .doctorhome_right{
        position: absolute;
        left: 500px;
        /*z-index: 9999;*/
        right: 10px;
        height: 100%;
    }
    .doctorhome_right_tabs{
        width: 100%;
        height: 100%;
    }
    .doctorhome_right_tabs_patientinf_form{

        width: 400px;

    }
    .doctorhome_right_tabs_patientinf_form_r{
        position: absolute;
        width: 400px;
        height: 600px;
        /*background-color: #42b983;*/
        left: 450px;
        top: 0px;
    }
    .doctorhome_right_tabs_patientinf_diagnosisform{
        position: absolute;
        top: 400px;
        left: 435px;
    }
</style>