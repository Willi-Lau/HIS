<template>
    <div class="doctorregister">
        <div class="doctortabregister">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
                <el-breadcrumb-item :to="{ path: '/PatientLogin' }">患者登录</el-breadcrumb-item>
                <el-breadcrumb-item>患者注册</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="doctorregisterform">
            <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
                <el-form-item label="登录名" prop="duusername">
                    <el-input v-model="ruleForm.puname"></el-input>
                </el-form-item>

                <el-form-item label="密码" prop="dupassword">
                    <el-input type="password" v-model="ruleForm.dupassword" ></el-input>
                </el-form-item>

                <el-form-item label="确认密码" prop="dusure_password">
                    <el-input type="password" v-model="ruleForm.dusure_password" ></el-input>
                </el-form-item>

                <el-form-item label="真实姓名" prop="duname">
                    <el-input v-model="ruleForm.duname"></el-input>
                </el-form-item>

                <el-form-item label="性别" prop="dusex">
                    <el-radio-group v-model="ruleForm.dusex">
                        <el-radio label="男"></el-radio>
                        <el-radio label="女"></el-radio>
                    </el-radio-group>
                </el-form-item>

                <el-form-item label="身份证号" prop="ducard">
                    <el-input v-model="ruleForm.ducard"></el-input>
                </el-form-item>

                <el-form-item label="电话号码" prop="duphone">
                    <el-input v-model="ruleForm.duphone"></el-input>
                </el-form-item>

                <el-form-item label="住址" prop="duaddress">
                    <el-input v-model="ruleForm.duaddress"></el-input>
                </el-form-item>

                <el-form-item label="科室" prop="dudepartmentname">
                    <el-select v-model="ruleForm.dudepartmentname" placeholder="请选择科室" style="margin-left: 20px">

                        <ul v-for="(item,index) in department" >

                            <el-option :label="item" :value="item"></el-option>


                        </ul>

                    </el-select>
                </el-form-item>

                <el-form-item label="类别" prop="dutype">
                    <el-radio-group v-model="ruleForm.dutype" class="sex">
                        <el-radio label="医生"></el-radio>
                        <el-radio label="管理员"></el-radio>
                        <el-radio label="收费员"></el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item label="职称" prop="dutitle" >
                    <el-radio-group v-model="ruleForm.dutitle" class="sex">
                        <el-radio label="著名专家"></el-radio>
                        <el-radio label="教授"></el-radio>
                        <el-radio label="副教授"></el-radio>
                        <el-radio label="主治医师"></el-radio>
                        <el-radio label="副主治医师"></el-radio>
                        <el-radio label="管理员"></el-radio>
                        <el-radio label="收费员"></el-radio>

                    </el-radio-group>
                </el-form-item>

                <el-form-item>
                    <el-button type="primary" @click="submitForm('ruleForm')">立即创建</el-button>
                    <el-button @click="resetForm('ruleForm')">重置</el-button>
                    <el-button @click="returnoff()">返回</el-button>
                </el-form-item>
            </el-form>
        </div>

    </div>
</template>

<script>

    export default {
        created() {
            //找到所有的品牌
            this.$axios.post('DoctorRegisterController/selectalldepartment',

            ).then(response=>{      //返回值部分
                this.department = response.data
            }).catch(error=>{
                console.log(error)
            })

        },
        data() {

            {
                var validatePass = (rule, value, callback) => {
                    if (value === '') {
                        callback(new Error('请输入密码'));
                    } else {
                        if (this.ruleForm.dusure_password !== '') {
                            this.$refs.ruleForm.validateField('dusure_password');
                        }
                        callback();
                    }
                };

                var validatePass2 = (rule, value, callback) => {
                    if (value === '') {
                        callback(new Error('请再次输入密码'));
                    } else if (value !== this.ruleForm.dupassword) {
                        callback(new Error('两次输入密码不一致!'));
                    } else {
                        callback();
                    }
                };


                var checkphone = function (phone) {
                    var pattern =  /^([1]\d{10}|([\(（]?0[0-9]{2,3}[）\)]?[-]?)?([2-9][0-9]{6,7})+(\-[0-9]{1,4})?)$/;   //验证手机号
                    if (!pattern.test(phone)) {
                        return false;
                    }
                    else {
                        return true;
                    }
                }
                //验证手机
                var validatePhone =(rule, value, callback)=>{
                    if (value === '') {
                        callback(new Error('请输入电话号码'));
                    }
                    else if(checkphone(value) === false){
                        callback(new Error('电话号码格式错误!'));
                    }
                    else{
                        callback();
                    }
                };
                var checkemail = function(email) {
                    var pattern =   /^[1-9]\d{5}(18|19|([23]\d))\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$/;   //验证身份证号
                    if (!pattern.test(email)) {
                        return false;
                    }
                    else {
                        return true;
                    }
                }

                var validateEmail =(rule, value, callback)=>{
                    if (value === '') {
                        callback(new Error('请输入身份证号'));
                    }
                    else if(checkemail(value) === false){
                        callback(new Error('身份证号错误!'));
                    }
                    else{
                        callback();
                    }
                };
            }

            return {
                department:{},
                ruleForm: {
                    duusername:'',
                    dupassword:'',
                    dusure_password:'',
                    duname:'',
                    dusex:'',
                    ducard:'',
                    duphone:'',
                    duaddress:'',
                    dudepartmentname:'',
                    dutype:'',
                    dutitle:''
                },
                inf:'',

                rules: {
                    duusername: [
                        { required: true, message: '请输入注册账号', trigger: 'blur' },
                        { min: 5, max: 15, message: '长度在 5 到 15 个字符', trigger: 'blur' }
                    ],
                    dupassword: [
                        { validator: validatePass, trigger: 'blur' }
                    ],
                    dusure_password: [
                        { validator: validatePass2, trigger: 'blur' }
                    ],
                    duname: [
                        { required: true, message: '请输入姓名', trigger: 'blur' },
                        { min: 2, max: 4, message: '长度在 2 到 4 个汉字', trigger: 'blur' }
                    ],
                    dusex: [
                        { required: true, message: '请选择性别', trigger: 'change' }
                    ],
                    ducard: [
                        { validator: validateEmail, trigger: 'blur' }
                    ],
                    duphone: [
                        { validator: validatePhone, trigger: 'blur' }
                    ],
                    duaddress: [
                        { required: true, message: '请写入地址', trigger: 'blur' },
                        { min: 1, max: 100, message: '长度在 1 到 100 个字符', trigger: 'blur' }
                    ],
                    dudepartmentname: [
                        { required: true, message: '请选择科室', trigger: 'change' }
                    ],
                    dutype: [
                        { required: true, message: '请选择类别', trigger: 'change' }
                    ],
                    dutitle: [
                        { required: true, message: '请选择职称', trigger: 'change' }
                    ],


                }
            };
        },
        methods: {
            returnoff(){
                this.$router.push('/DoctorLogin');
            },
            submitForm(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {

                        //执行正确操作
                        //改变男女
                        if(this.ruleForm.dusex === "男"){
                            this.ruleForm.dusex = 1
                        }
                        else{
                            this.ruleForm.dusex = 0
                        }
                        this.$axios.post('DoctorRegisterController/registerdoctor',

                            this.ruleForm



                        ).then(response=>{      //返回值部分
                            this.inf = response.data

                        }).catch(error=>{
                            console.log(error)
                        })
                        if(this.inf === "success"){
                            //弹窗
                            this.$alert('', '创建成功', {
                                confirmButtonText: '确定',
                                callback: action => {
                                    this.$message({
                                        type: 'info',
                                        message: `Create User Success`
                                    });
                                }
                            });

                            this.$router.push('/DoctorLogin');
                        }
                        if(this.inf === "phone"){
                            this.$alert('', '创建失败 电话已被注册', {
                                confirmButtonText: '确定',
                                callback: action => {
                                    this.$message({
                                        message: `Create User false`
                                    });
                                }
                            });
                        }

                        if(this.inf === "card"){
                            this.$alert('', '创建失败 身份证已被注册', {
                                confirmButtonText: '确定',
                                callback: action => {
                                    this.$message({
                                        message: `Create User false`
                                    });
                                }
                            });
                        }

                        if(this.inf === "username"){
                            this.$alert('', '创建失败 账号已被注册', {
                                confirmButtonText: '确定',
                                callback: action => {
                                    this.$message({
                                        message: `Create User false`
                                    });
                                }
                            });
                        }





                    } else {
                        this.$notify.error({
                            title: '错误',
                            message: '请正确填写信息'
                        });
                        return false;
                    }
                });
            },
            resetForm(formName) {
                this.$refs[formName].resetFields();
            },

        }
    }
</script>

<style>
    .doctorregister{
        position: absolute;
        width: 650px;
        left: 400px;
    }
    .doctortabregister{
        position: absolute;
        left: -250px;
        top: 20px;
        color: #42b983;
        z-index: 99999;
    }
    .doctorregisterform{
        position: relative;
        top: 50px;
    }
</style>




