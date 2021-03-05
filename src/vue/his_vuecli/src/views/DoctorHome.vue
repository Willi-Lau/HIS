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
                                border
                                stripe
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
                                    <el-button type="success" size="small" round @click="bosschange(scope.row.medical_record.patientUser.puname,scope.row.prrid)">选择</el-button>
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
                            border
                            stripe
                            :data="form"
                            style="width: 100%"
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
                                <el-button type="success" size="small"  round @click="bosschange(scope.row.medical_record.patientUser.puname,scope.row.prrid)">选择</el-button>

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
                        <el-tab-pane label="检查">
                            <div>
                                <el-table
                                        border
                                        stripe
                                        height="250"
                                        ref="iimultipleTable1"
                                        :data="thisPageInspection_items"
                                        tooltip-effect="dark"
                                        style="width: 100%"
                                        @selection-change="handleSelectionChange">
                                    <el-table-column
                                            type="selection"
                                            width="55">
                                    </el-table-column>
                                    <el-table-column
                                            label="id"
                                            width="220"
                                    prop="iiid">
                                    </el-table-column>
                                    <el-table-column
                                            prop="iiname"
                                            label="名称"
                                            width="220">
                                    </el-table-column>
                                    <el-table-column
                                            prop="iimoney"
                                            label="费用"
                                            show-overflow-tooltip>
                                    </el-table-column>
                                    <el-table-column
                                            prop="iimedical"
                                            label="是否可以医保"
                                            width="220">
                                    </el-table-column>
                                </el-table>
                            </div>
                            <div>
                                <el-button type="success"   @click="updateitem()">提交到待提交库(请双击)</el-button>
                                <el-button type="danger"   @click="deleteitem()">取消选择</el-button>
<!--                                检查快捷搜索，填入-->
                                <div class="doctorhome_right_tabs_third_iimultipleTable">
                                    <el-form :inline="true" :model="chooseInspection">
                                        <el-form-item label="检查快捷选择">
                                            <el-select v-model="chooseInspection.Inspection" filterable placeholder="请选择">
                                                <el-option
                                                        v-for="item in thisPageInspection_items"
                                                        :key="item.iiname"
                                                        :label="item.iiname + item.iipinyin"
                                                        :value="item.iiname">
                                                </el-option>
                                            </el-select>
                                        </el-form-item>

                                        <el-form-item>
                                            <el-button type="success"  @click="adddInspectionform">选择添加进待提交库</el-button>
                                        </el-form-item>
                                    </el-form>
                                </div>
                            </div>
                        </el-tab-pane>

                        <el-tab-pane label="检验">
                            <div>
                                <el-table
                                        border
                                        stripe
                                        height="250"
                                        ref="timultipleTable"
                                        :data="thisPageTest_items"
                                        tooltip-effect="dark"
                                        style="width: 100%"
                                        @selection-change="handleSelectionChange2">
                                    <el-table-column
                                            type="selection"
                                            width="55">
                                    </el-table-column>
                                    <el-table-column
                                            label="id"
                                            width="220"
                                            prop="tiid">
                                    </el-table-column>
                                    <el-table-column
                                            prop="tiname"
                                            label="名称"
                                            width="220">
                                    </el-table-column>
                                    <el-table-column
                                            prop="timoney"
                                            label="费用"
                                            show-overflow-tooltip>
                                    </el-table-column>
                                    <el-table-column
                                            prop="timedical"
                                            label="是否可以医保"
                                            width="220">
                                    </el-table-column>
                                </el-table>
                            </div>
                            <div>
                                <el-button type="success"  @click="updateitem2()">提交到待提交库(请双击)</el-button>
                                <el-button type="danger"   @click="deleteitem2()">取消选择</el-button>
                                <div class="doctorhome_right_tabs_third_iimultipleTable">
                                    <el-form :inline="true" :model="chooseTest">
                                        <el-form-item label="检验快捷选择">
                                            <el-select v-model="chooseTest.Test" filterable placeholder="请选择">
                                                <el-option
                                                        v-for="item in thisPageTest_items"
                                                        :key="item.tiname"
                                                        :label="item.tiname + item.tipinyin"
                                                        :value="item.tiname">
                                                </el-option>
                                            </el-select>
                                        </el-form-item>

                                        <el-form-item>
                                            <el-button type="success"  @click="adddTestform">选择添加进待提交库</el-button>
                                        </el-form-item>
                                    </el-form>
                                </div>
                            </div>

                        </el-tab-pane>


                    </el-tabs>
<!--                    下面显示所有的检查检验得信息-->

                    <div>
                        <el-table
                                border
                                stripe
                                height="250"
                                :data="thisAllitems"
                                style="width: 100%"
                                :row-class-name="tableRowClassName">
                            <el-table-column
                                    prop="id"
                                    label="id"
                                    width="180">
                            </el-table-column>
                            <el-table-column
                                    prop="name"
                                    label="名称"
                                    width="180">
                            </el-table-column>
                            <el-table-column
                                    prop="money"
                                    label="费用">
                            </el-table-column>
                            <el-table-column
                                    prop="medical"
                                    label="是否可以医保">
                            </el-table-column>
                            <el-table-column
                                    prop="wrong"
                                    label="删除"
                                    width="180">
                                <template slot-scope="scope">
                                    <el-button  size="mini" type="danger" @click="deletethisAllitems(scope.$index)">删除</el-button>

                                    <!--                         获取索引-->
                                    <!--                         {{scope.$index}}-->
                                </template>

                            </el-table-column>
                        </el-table>
                        <el-button type="primary" size="min"  @click="upthisAllitems()">提交检查检验</el-button>
                        <el-button type="warning" size="min"  @click="clearthisAllitems()">清空</el-button>
                    </div>

                </el-tab-pane>
                <el-tab-pane label="检查检验结果" name="fourth">

<!--                    显示所有的检查检验 点击显示结果-->
<!--                    name:'',-->
<!--                    ismoney:'',-->
<!--                    isdo:'',-->
<!--                    final:'',-->
<!--                    time:'',-->
<!--                    dodoctor:'',-->
<!--                    water:'',-->
<!--                    alive:''-->
                    <div class="doctorhome_right_tabs_fourth_iitiend">
                        <el-table
                                border
                                stripe
                                height="250"
                                ref="ArrayTestAndInsprctionFinal"
                                :data="ArrayTestAndInsprctionFinal"
                                tooltip-effect="dark"
                                style="width: 100%"
                                @selection-change="handleSelectionChange">
                            <el-table-column
                                    prop="name"
                                    label="名称"
                                    width="220">
                            </el-table-column>
                            <el-table-column
                                    prop="ismoney"
                                    label="是否缴费"
                                    show-overflow-tooltip>
                            </el-table-column>
                            <el-table-column
                                    prop="isdo"
                                    label="是否已做"
                                    show-overflow-tooltip>
                            </el-table-column>
                            <el-table-column
                                    prop="wrong"
                                    label="点击查看"
                                    width="220">
                                <template slot-scope="scope">
                                    <el-button  size="mini" type="success" @click="lookArrayTestAndInsprctionFinal1(scope.$index)">查看</el-button>

                                    <!--                         获取索引-->
                                    <!--                         {{scope.$index}}-->
                                </template>

                            </el-table-column>
                            <el-table-column
                                    prop="back"
                                    label="退回"
                                    width="120">
                                <template slot-scope="scope">
                                    <el-button  size="mini" type="danger" @click="deleteArrayTestAndInsprctionFinal1(scope.$index)">退回</el-button>

                                    <!--                         获取索引-->
                                    <!--                         {{scope.$index}}-->
                                </template>

                            </el-table-column>

                        </el-table>
                    </div>

<!--                    根据选择得检查检验项目部分，显示具体的信息-->
                    <div class="doctorhome_fourth_form1">
                        <el-card class="box-card_form1">
                            <el-form ref="lookArrayTestAndInsprctionFinal" :model="lookArrayTestAndInsprctionFinal" label-width="80px">
                                <!--                            主诉-->
                                <el-form-item label="结果">
                                    <el-input
                                            label="主诉"
                                            type="textarea"
                                            :rows="14"
                                            placeholder="请输入内容"
                                            v-model="lookArrayTestAndInsprctionFinal.final">
                                    </el-input>
                                </el-form-item>


                            </el-form>
                        </el-card>
                    </div>
<!--                    其他信息-->
                    <div class="doctorhome_fourth_form1_other">
                        <el-card class="box-card_form1_other">
                            <el-form ref="lookArrayTestAndInsprctionFinal" :model="lookArrayTestAndInsprctionFinal" label-width="80px">
                                <!--                            主诉-->
                                <el-form-item label="时间">
                                    <el-input
                                            label="时间"
                                            type="text"
                                            placeholder="请输入内容"
                                            v-model="lookArrayTestAndInsprctionFinal.time">
                                    </el-input>
                                </el-form-item>
                                <el-form-item label="操作员号">
                                    <el-input
                                            label="操作员号"
                                            type="text"
                                            placeholder="请输入内容"
                                            v-model="lookArrayTestAndInsprctionFinal.dodoctor">
                                    </el-input>
                                </el-form-item>
                                <el-form-item label="操作流水">
                                    <el-input
                                            label="操作流水"
                                            type="text"
                                            placeholder="请输入内容"
                                            v-model="lookArrayTestAndInsprctionFinal.water">
                                    </el-input>
                                </el-form-item>




                            </el-form>
                        </el-card>
                        <div class="doctorhome_fourth_form1_other_refresh">
                            <el-button type="primary" icon="el-icon-refresh-left" @click="flash1">刷新所有结果</el-button>
                        </div>
                    </div>




                </el-tab-pane>
                <el-tab-pane label="开药" name="fifth">
<!--                    开药-->

                    <div id="app">
                        <el-table   border max-height="600px" :data="tableDataDrug" class="tb-edit" style="width: 100%" highlight-current-row  >

                            <el-table-column prop="ni" label="药品名称" width="180">
                                <!--          必须加 templateb = scope这个 才能使用 scpoe.row 来指向表格里本条数据-->
                                <template scope="scope">
                                    <!--            这里这个 @change 是根据 下拉框得改变 来改变 其他属性框里的值-->
                                    <el-select v-model="scope.row.drname" filterable placeholder="请选择" @change="handleEditdrname(scope.$index, scope.row.drname)">
                                        <el-option
                                                v-for="item in allDrug"
                                                :key="item.drname"
                                                :label="item.drname+item.drpinyin"
                                                :value="item.drname">
                                        </el-option>

                                    </el-select>
                                    <span>{{scope.row.drname}}</span>
                                </template>
                            </el-table-column>

                            <el-table-column label="id" width="50">
                                <template scope="scope">
<!--                                    <el-input size="small" v-model="scope.row.drid" placeholder="请输入内容" ></el-input>-->
                                    <span>{{scope.row.drid}}</span>
                                </template>
                            </el-table-column>
                            <el-table-column label="单位" width="50">
                                <template scope="scope">
<!--                                    <el-input size="small" v-model="scope.row.drformat" placeholder="请输入内容" ></el-input>-->
                                    <span>{{scope.row.drformat}}</span>
                                </template>
                            </el-table-column>
                            <el-table-column label="次数" width="90">
                                <template scope="scope">
<!--                                    <el-input size="small" v-model="scope.row.drconsumption" placeholder="请输入内容" ></el-input>-->
                                    <span>{{scope.row.drconsumption}}</span>
                                </template>
                            </el-table-column>
                            <el-table-column label="处方药否" width="90">
                                <template scope="scope">
<!--                                    <el-input size="small" v-model="scope.row.drtype" placeholder="请输入内容" ></el-input> -->
                                    <span>{{scope.row.drtype}}</span>
                                </template>
                            </el-table-column>
                            <el-table-column label="剩余数量" width="90">
                                <template scope="scope">
<!--                                    <el-input size="small" v-model="scope.row.drnum" placeholder="请输入内容" ></el-input> -->
                                    <span>{{scope.row.drnum}}</span>
                                </template>
                            </el-table-column>
                            <el-table-column label="开药数量" width="90">
                                <template scope="scope">
                                    <el-input size="small" v-model="scope.row.dryounum" placeholder="请输入内容" @change="changeDrugnum(scope.$index, scope.row.dryounum)"></el-input>
                                    <span>{{scope.row.dryounum}}</span>
                                </template>
                            </el-table-column>
                            <el-table-column label="单价" width="90">
                                <template scope="scope">
<!--                                    <el-input size="small" v-model="scope.row.drmoney" placeholder="请输入内容" ></el-input> -->
                                    <span>{{scope.row.drmoney}}</span>
                                </template>
                            </el-table-column>
                            <el-table-column label="总钱数" width="90">
                                <template scope="scope">
                                    <!--                                    <el-input size="small" v-model="scope.row.drmoney" placeholder="请输入内容" ></el-input> -->
                                    <span>{{scope.row.allmoney}}</span>
                                </template>
                            </el-table-column>
                            <el-table-column label="是否可以医保" width="90">
                                <template scope="scope">
<!--                                    <el-input size="small" v-model="scope.row.drmedical" placeholder="请输入内容" ></el-input>-->
                                    <span>{{scope.row.drmedical === 1? "可以医保" :"不可医保"}}</span>
                                </template>
                            </el-table-column>
                            <el-table-column label="操作">


                                <template scope="scope">
                                    <!--            <el-button size="small" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>-->
                                    <el-button size="small" type="danger" @click="handleDelete(scope.$index)">删除</el-button>
                                </template>
                            </el-table-column>
                        </el-table>
                        <el-button  type="primary" @click="add()">add</el-button>
                        <el-button  type="success" @click="upDrug()">提交</el-button>
<!--                        <br>数据:{{tableDataDrug}}-->
                    </div>


                </el-tab-pane>
                <el-tab-pane label="医疗处理" name="sixth">医疗处理！</el-tab-pane>
                <el-tab-pane label="医疗处理及开药记录" name="seventh">医疗处理及开药记录！</el-tab-pane>
            </el-tabs>
        </div>


    </div>
</template>

<script>



    export default {
        data(){
          return{
              //所有药品信息
              allDrug:[
                  {
                      drid:'',
                      drname:'',
                      drpinyin:'',
                      drformat:'',
                      drconsumption:'',
                      drtype:'',
                      drnum:'',
                      drmoney:'',
                      dralive:'',
                      drmedical:''
                  }
              ],
              //存储得开药表
              tableDataDrug:[
                  {
                      drid:'无',
                      drname:'请选择',
                      drformat:'无',
                      drconsumption: '无',
                      drtype:'无',
                      drnum:'无',
                      dryounum:'0',
                      drmoney:'无',
                      drmedical: '无',
                      allmoney:'0',
                      prrid:this.thisPRRid
                  }
              ],
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
              },
              //选择检验信息
              chooseInspection:{
                  Inspection:''
              },
              //选择检查信息
              chooseTest:{
                  Test:''
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
              //选中此次挂号的id
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

              },
              //所有检查目录
              thisPageInspection_items:[
                  {
                      iiid:'',
                      iiname:'',
                      iipinyin:'',
                      iimoney:'',
                      iimedical:''
                  }
              ],
              iimultipleSelection:[],
              //所有检验目录
              thisPageTest_items:[
                  {
                      tiid:'',
                      tiname:'',
                      tipinyin:'',
                      timoney:'',
                      timedical:''
                  }
              ],
              timultipleSelection:[],
              //已经开得检查检验
              thisAllitems:[
                  {
                      id:'',
                      name:'',
                      pinyin:'',
                      money:'',
                      medical:'',
                      wrong:'',
                      prrid:''
                  }
              ],
              //根据病历查找所有检查检验相关信息
              allTestAndInsprction:[
                  {
                      doctorUser:'',
                      handleList:'',
                      inspectionList:[
                          {
                              docotrInspectionrecord:{
                                  dirduid:  '',
                                  dirid:  '',
                                  diriid:  '',
                                  dirmrid:  '',
                                  dirtime:  ''
                              },
                              inalive: '',
                              indo: '',
                              inend: '',
                              ingivemoney: '',
                              inid: '',
                              inmrid: '',
                              innum: '',
                              intime: '',
                              inused:'',
                              inwater: '',
                              inspection_items:{
                                  iiid:  '',
                                  iiname:  '',
                                  iipinyin:  '',
                                  iimoney:  '',
                                  iimedical:  ''
                              }
                          }
                      ],
                      mrchiefcomplaint: '',
                      mrdid: '',
                      mrfinal: '',
                      mrid: '',
                      mrpast: '',
                      mrpersonal: '',
                      mrpid: '',
                      mrpresentIllness: '',
                      mrtime: '',
                      patientUser: '',
                      payList: '',
                      testList:[
                          {
                              doctorTestrecode:{
                                  dtrduid: '',
                                  dtrid: '',
                                  dtrmrid: '',
                                  dtrtid: '',
                                  dtrtime: ''
                              },
                            talive: '',
                            tdo: '',
                            tend: '',
                            tgivemoney: '',
                            tid: '',
                            tmrid: '',
                            tnum: '',
                            ttime: '',
                            tused: '',
                            twater: '',
                              test_items:{
                                  tiid: '',
                                  tiname: '',
                                  tipinyin: '',
                                  timoney: '',
                                  timedical: ''
                              }
                          }
                      ]
                  }
              ],
              //检查检验结果渲染得信息  名称 是否缴费 是否已做 结果 时间 操作医生 操作流水 是否停用
              ArrayTestAndInsprctionFinal:[
                  {
                      //所做项id
                      id:'',
                      mrid:'',
                      createtime:'',
                      name:'',
                      ismoney:'',
                      isdo:'',
                      final:'',
                      time:'',
                      dodoctor:'',
                      water:'',
                      alive:''
                  }
              ],
              //具体查看得结果
              lookArrayTestAndInsprctionFinal:{
                  final:'',
                  time:'',
                  dodoctor:'',
                  water:'',
              }
          }
        },
        components: {

        }
        ,created() {
            //查找所有非药品

            //查找所有的药品
            this.selectall()
            this.ArrayTestAndInsprctionFinal.splice(0,this.ArrayTestAndInsprctionFinal.length)
            //查找所有疾病的信息 selectallDiagnosis
            this.$axios.post('DoctorHomeController/selectallDrug',
                this.$qs.stringify(
                    {
                    }
                )
            ).then(response => {      //返回值部分
                 this.allDrug = response.data;
            }).catch(error => {
                console.log(error)
            })


            //查找所有的检验信息
            this.$axios.post('DoctorHomeController/selectallInspection',
                this.$qs.stringify(
                    {
                    }
                )
            ).then(response => {      //返回值部分
                this.thisPageInspection_items = response.data;
                for (let i=0;i<this.thisPageInspection_items.length ;i++){
                    if(this.thisPageInspection_items[i].iimedical === 1){
                        this.thisPageInspection_items[i].iimedical = '可以医保'
                    }
                    else {
                        this.thisPageInspection_items[i].iimedical = '不可以医保'
                    }
                }
            }).catch(error => {
                console.log(error)
            })
            //查找所有的检查信息
            this.$axios.post('DoctorHomeController/selectalltest',
                this.$qs.stringify(
                    {
                    }
                )
            ).then(response => {      //返回值部分
                this.thisPageTest_items = response.data;
                for (let i=0;i<this.thisPageTest_items.length ;i++){
                    if(this.thisPageTest_items[i].timedical === 1){
                        this.thisPageTest_items[i].timedical = '可以医保'
                    }
                    else {
                        this.thisPageTest_items[i].timedical = '不可以医保'
                    }
                }
            }).catch(error => {
                console.log(error)
            })
        },
        methods:{
            //开药增加
            add(){
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
                else{
                    this.tableDataDrug.push({
                            drid:'无',
                            drname:'请选择',
                            drformat:'无',
                            drconsumption: '无',
                            drtype:'无',
                            drnum:'无',
                            dryounum:'0',
                            drmoney:'无',
                            drmedical: '无',
                            allmoney:'0',
                            prrid:this.thisPRRid
                        }
                    );
                }
            },
            //删除药品
            handleDelete(index){
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

                    this.tableDataDrug.splice(index,1)
                    }
                },
            //提交药品
            upDrug(){


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
                    else{
                        //删除空
                        for(let i=0;i<this.tableDataDrug.length;i++){
                            if(this.tableDataDrug[i].drid === "无"){
                                this.tableDataDrug.splice(i,1);
                            }
                        }

                        this.$axios.post('DoctorHomeController/insertpayfromDrug',
                            this.tableDataDrug
                        ).then(response => {      //返回值部分
                            //清空药品记录
                            this.tableDataDrug = []
                            //重新查询所有的药品
                            this.ArrayTestAndInsprctionFinal.splice(0,this.ArrayTestAndInsprctionFinal.length)
                            //查找所有疾病的信息 selectallDiagnosis
                            this.$axios.post('DoctorHomeController/selectallDrug',
                                this.$qs.stringify(
                                    {
                                    }
                                )
                            ).then(response => {      //返回值部分
                                this.allDrug = response.data;
                            }).catch(error => {
                                console.log(error)
                            })

                        }).catch(error => {
                            console.log(error)
                        })
                    }



            },
            //数量改变 更改总钱数
            changeDrugnum(index,dryounum){
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
                    if (dryounum < 0 || dryounum > this.tableDataDrug[index].drnum) {
                        this.$alert('', '数量修改失败，请修改正确数量', {
                            confirmButtonText: '确定',
                            callback: action => {
                                this.$message({
                                    type: 'info',
                                    message: `no`
                                });
                            }
                        });
                    } else {
                        this.tableDataDrug[index].allmoney = dryounum * this.tableDataDrug[index].drmoney
                    }
                }

            },
            //选择药品名字改变信息
            handleEditdrname(index,drname){
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
                    this.tableDataDrug[index].prrid = this.thisPRRid
                    for (let i = 0; i < this.allDrug.length; i++) {
                        //找到对应的药品，添加进去
                        if (this.allDrug[i].drname === drname) {
                            this.tableDataDrug[index].drid = this.allDrug[i].drid,
                                this.tableDataDrug[index].drformat = this.allDrug[i].drformat,
                                this.tableDataDrug[index].drconsumption = this.allDrug[i].drconsumption,
                                this.tableDataDrug[index].drnum = this.allDrug[i].drnum,
                                this.tableDataDrug[index].drmoney = this.allDrug[i].drmoney,

                                this.tableDataDrug[index].drmedical = this.allDrug[i].drmedical
                                //


                        }
                    }
                }

            },

            //退回检查检验
            deleteArrayTestAndInsprctionFinal1(index){

                //alert(this.ArrayTestAndInsprctionFinal[index].createtime)
                //根据index 查找  病历id 所做项目id 项目创建时间 传入后台 给对应项目表和缴费表alive设置未1
                this.$axios.post('DoctorHomeController/updateTestInspectionPay',this.$qs.stringify(

                    {
                        id: this.ArrayTestAndInsprctionFinal[index].id,
                        mrid:this.ArrayTestAndInsprctionFinal[index].mrid,
                        time:this.ArrayTestAndInsprctionFinal[index].createtime
                    }
                    )
                ).then(response => {      //返回值部分

                }).catch(error => {
                    console.log(error)
                })
                //再次刷新
                this.flash1();

            },
            //点击查看具体结果
            lookArrayTestAndInsprctionFinal1(index){
                this.lookArrayTestAndInsprctionFinal.final = this.ArrayTestAndInsprctionFinal[index].final
                this.lookArrayTestAndInsprctionFinal.time = this.ArrayTestAndInsprctionFinal[index].time
                this.lookArrayTestAndInsprctionFinal.dodoctor = this.ArrayTestAndInsprctionFinal[index].dodoctor
                this.lookArrayTestAndInsprctionFinal.water = this.ArrayTestAndInsprctionFinal[index].water
            },
            //刷新检验检查结果
            flash1(){
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
                else{
//再次查询返回这个患者所有检验检查相关信息
                this.$axios.post('DoctorHomeController/selectallTestAndInsprction',
                    this.$qs.stringify({
                        prrid: this.thisPRRid
                    })


                ).then(response => {      //返回值部分
                    console.log(response.data)
                    this.allTestAndInsprction = response.data
                    //删除所有之前的记录
                    this.ArrayTestAndInsprctionFinal.splice(0,this.ArrayTestAndInsprctionFinal.length)

                    //替换 是否缴费 是否已做
                    for(let i=0;i<this.allTestAndInsprction[0].inspectionList.length;i++){
                        if(this.allTestAndInsprction[0].inspectionList[i].ingivemoney === 0){
                            this.allTestAndInsprction[0].inspectionList[i].ingivemoney = "未缴费"
                        }
                        else {
                            this.allTestAndInsprction[0].inspectionList[i].ingivemoney = "已缴费"
                        }
                        if(this.allTestAndInsprction[0].inspectionList[i].inused ===0){
                            this.allTestAndInsprction[0].inspectionList[i].inused = "未做"
                        }
                        else {
                            this.allTestAndInsprction[0].inspectionList[i].inused = "已做"
                        }
                        if(this.allTestAndInsprction[0].inspectionList[i].docotrInspectionrecord.dirtime === null){
                            this.allTestAndInsprction[0].inspectionList[i].docotrInspectionrecord.dirtime = "未做检查无信息"
                        }
                        if(this.allTestAndInsprction[0].inspectionList[i].docotrInspectionrecord.dirduid === 0){
                            this.allTestAndInsprction[0].inspectionList[i].docotrInspectionrecord.dirduid = "未做检查无信息"
                        }
                    }
                    for(let i=0;i<this.allTestAndInsprction[0].testList.length;i++){
                        if(this.allTestAndInsprction[0].testList[i].tgivemoney ===0){
                            this.allTestAndInsprction[0].testList[i].tgivemoney = "未缴费"
                        }
                        else {
                            this.allTestAndInsprction[0].testList[i].tgivemoney = "已缴费"
                        }
                        if(this.allTestAndInsprction[0].testList[i].tused ===0){
                            this.allTestAndInsprction[0].testList[i].tused = "未做"
                        }
                        else {
                            this.allTestAndInsprction[0].testList[i].tused = "已做"
                        }
                        if(this.allTestAndInsprction[0].testList[i].doctorTestrecode.dtrtime === null){
                            this.allTestAndInsprction[0].testList[i].doctorTestrecode.dtrtime = "未做检查无信息"
                        }
                        if(this.allTestAndInsprction[0].testList[i].doctorTestrecode.dtrduid === 0){
                            this.allTestAndInsprction[0].testList[i].doctorTestrecode.dtrduid = "未做检查无信息"
                        }
                    }
                    //检查
                    for(let i=0;i<this.allTestAndInsprction[0].inspectionList.length;i++){
                        //没被禁用再添加
                        if(this.allTestAndInsprction[0].inspectionList[i].inalive === 0){
                            this.ArrayTestAndInsprctionFinal.push({
                                mrid:this.allTestAndInsprction[0].inspectionList[i].inmrid,
                                createtime:this.allTestAndInsprction[0].inspectionList[i].intime,
                                id:this.allTestAndInsprction[0].inspectionList[i].inid,
                                name:this.allTestAndInsprction[0].inspectionList[i].inspection_items.iiname,
                                ismoney:this.allTestAndInsprction[0].inspectionList[i].ingivemoney,
                                isdo:this.allTestAndInsprction[0].inspectionList[i].inused,
                                final:this.allTestAndInsprction[0].inspectionList[i].inend,
                                time:this.allTestAndInsprction[0].inspectionList[i].docotrInspectionrecord.dirtime,
                                dodoctor:this.allTestAndInsprction[0].inspectionList[i].docotrInspectionrecord.dirduid,
                                water:this.allTestAndInsprction[0].inspectionList[i].docotrInspectionrecord.dirid,
                                alive: this.allTestAndInsprction[0].inspectionList[i].inalive
                        })
                        }
                    }
                    //检验
                    for(let i=0;i<this.allTestAndInsprction[0].testList.length;i++){
                        //没被禁用再添加
                        if(this.allTestAndInsprction[0].testList[i].talive === 0){
                            this.ArrayTestAndInsprctionFinal.push({
                                mrid:this.allTestAndInsprction[0].testList[i].tmrid,
                                createtime:this.allTestAndInsprction[0].testList[i].ttime,
                                id:this.allTestAndInsprction[0].testList[i].tid,
                                name:this.allTestAndInsprction[0].testList[i].test_items.tiname,
                                ismoney:this.allTestAndInsprction[0].testList[i].tgivemoney,
                                isdo:this.allTestAndInsprction[0].testList[i].tused,
                                final:this.allTestAndInsprction[0].testList[i].tend,
                                time:this.allTestAndInsprction[0].testList[i].doctorTestrecode.dtrtime,
                                dodoctor:this.allTestAndInsprction[0].testList[i].doctorTestrecode.dtrduid,
                                water:this.allTestAndInsprction[0].testList[i].doctorTestrecode.dtrid,
                                alive: this.allTestAndInsprction[0].testList[i].inalive
                            })
                        }
                    }





                }).catch(error => {
                    console.log(error)
                })
            }
            },
            //通过选择框将检验信息提交到库
            adddTestform(){
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
                    for (let i = 0; i < this.thisPageTest_items.length; i++) {
                        if (this.thisPageTest_items[i].tiname === this.chooseTest.Test) {
                            this.thisAllitems.push({
                                id: this.thisPageTest_items[i].tiid,
                                name: this.chooseTest.Test,
                                pinyin: this.thisPageTest_items[i].tipinyin,
                                money: this.thisPageTest_items[i].timoney,
                                medical: this.thisPageTest_items[i].timedical,
                                prrid: this.thisPRRid
                            })
                        }
                    }
                }

            },
            //通过选择框将检查信息提交到库
            adddInspectionform(){
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
                    for (let i = 0; i < this.thisPageInspection_items.length; i++) {
                        if (this.thisPageInspection_items[i].iiname === this.chooseInspection.Inspection) {
                            this.thisAllitems.push({
                                id: this.thisPageInspection_items[i].iiid,
                                name: this.chooseInspection.Inspection,
                                pinyin: this.thisPageInspection_items[i].iipinyin,
                                money: this.thisPageInspection_items[i].iimoney,
                                medical: this.thisPageInspection_items[i].iimedical,
                                prrid: this.thisPRRid
                            })
                        }
                    }
                }

            },
            //清空
            clearthisAllitems(){
                this.thisAllitems.splice(0,this.thisAllitems.length)
            },
            //提交检查检验
            upthisAllitems(){
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
                }else {
                    //upAllitems
                    this.$axios.post('DoctorHomeController/upAllitems',
                        this.thisAllitems

                    ).then(response => {      //返回值部分

                        //清空
                        this.thisAllitems.splice(0,this.thisAllitems.length)
                        this.deleteitem();
                        this.deleteitem2();
                        this.$notify({
                            title: '成功',
                            message: '成功提交',
                            type: 'success'
                        });
                        //再次查询返回这个患者所有检验检查相关信息
                        this.flash1()

                    }).catch(error => {
                        console.log(error)
                    })

                }
            },
            //删除待提交库中得某一条
            deletethisAllitems(index){
                this.thisAllitems.splice(index,1);

            },
            tableRowClassName({row, rowIndex}) {
                if (rowIndex === 1) {
                    return 'warning-row';
                } else if (rowIndex === 3) {
                    return 'success-row';
                }
                return '';
            },
            selectall(){
                //清空
                this.thisAllitems.splice(0,this.thisAllitems.length)
               //y由于每次点击添加都i会加入一次，最后倒数第一个第二个为无用代码，所以选择前面的就可以了，可以consloe.log查看就清楚了
                for (let i=0;i<this.iimultipleSelection.length-2;i++){

                    this.thisAllitems.push({
                        id:this.iimultipleSelection[i].iiid,
                        name:this.iimultipleSelection[i].iiname,
                        pinyin:this.iimultipleSelection[i].iipinyin,
                        money:this.iimultipleSelection[i].iimoney,
                        medical:this.iimultipleSelection[i].iimedical,
                        prrid: this.thisPRRid
                    })
                }
                for (let i=0;i<this.timultipleSelection.length-2;i++){
                    this.thisAllitems.push({
                        id:this.timultipleSelection[i].tiid,
                        name:this.timultipleSelection[i].tiname,
                        pinyin:this.timultipleSelection[i].tipinyin,
                        money:this.timultipleSelection[i].timoney,
                        medical:this.timultipleSelection[i].timedical,
                        prrid: this.thisPRRid
                    })
                }

                console.log(this.thisAllitems)
            },
            deleteitem(){
                this.$refs.iimultipleTable1.clearSelection();
            },
            //提交到前台库
            updateitem(val){
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
                    this.iimultipleSelection.push(val);
                    this.selectall();
                }

            },
            deleteitem2(){
                this.$refs.timultipleTable.clearSelection();
            },
            //提交到前台库
            updateitem2(val){
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
                    this.timultipleSelection.push(val);
                    this.selectall();
                }

            },

            //检验选择时触发，val为那个选项得所有的信息
            handleSelectionChange(val) {
                this.iimultipleSelection = val;

            },
            handleSelectionChange2(val) {
                this.timultipleSelection = val;

            },
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
    .doctorhome_right_tabs_third_iimultipleTable{
        position: absolute;
        top: 264px;
        left: 350px;
    }
    .doctorhome_right_tabs_fourth_iitiend{
        width: 1000px;
        height: 700px;
    }
    .doctorhome_fourth_form1{
        position: absolute;
        left: 0px;
        top: 260px;


         }
    .box-card_form1{
        height: 370px;

        width: 600px;
    }
    .doctorhome_fourth_form1_other{
        position: absolute;
        left: 630px;
        top: 260px;
        width: 350px;
    }
    .doctorhome_fourth_form1_other_refresh{
        margin-top: 20px;
        position: relative;
        left: 140px;
    }

/*    表格可操作核心部分*/
    .tb-edit .el-input {
        display: none
    }
    .tb-edit .current-row .el-input {
        display: block
    }
    .tb-edit .current-row .el-input+span {
        display: none
    }

    .tb-edit .el-select {
        display: none
    }
    .tb-edit .current-row .el-select {
        display: block
    }
    .tb-edit .current-row .el-select+span {
        display: none
    }
    /*    表格可操作核心部分*/


</style>