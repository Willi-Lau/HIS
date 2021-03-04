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
                <el-form-item label="登录名" prop="puusername">
                    <el-input v-model="ruleForm.puusername"></el-input>
                </el-form-item>

                <el-form-item label="密码" prop="pupassword">
                    <el-input type="password" v-model="ruleForm.pupassword" ></el-input>
                </el-form-item>

                <el-form-item label="确认密码" prop="pusure_password">
                    <el-input type="password" v-model="ruleForm.pusure_password" ></el-input>
                </el-form-item>

                <el-form-item label="真实姓名" prop="puname">
                    <el-input v-model="ruleForm.puname"></el-input>
                </el-form-item>

                <el-form-item label="性别" prop="pusex">
                    <el-radio-group v-model="ruleForm.pusex">
                        <el-radio label="男"></el-radio>
                        <el-radio label="女"></el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item label="出生日期" required>
                    <el-col :span="11">
                        <el-form-item prop="pubirthday">
                            <el-date-picker type="date" placeholder="选择日期" v-model="ruleForm.pubirthday" style="width: 100%;"></el-date-picker>
                        </el-form-item>
                    </el-col>
                </el-form-item>

                <el-form-item label="身份证号" prop="pucard">
                    <el-input v-model="ruleForm.pucard"></el-input>
                </el-form-item>
                <el-form-item label="住址" prop="puaddress">
                    <el-input v-model="ruleForm.puaddress"></el-input>
                </el-form-item>
                <el-form-item label="电话号码" prop="puphone">
                    <el-input v-model="ruleForm.puphone"></el-input>
                </el-form-item>
                <el-form-item label="过敏史" prop="puhistory">
                <el-input

                        type="textarea"
                        :rows="2"
                        placeholder="请输入内容"
                        v-model="ruleForm.puhistory">
                </el-input>
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

        },
        data() {

            {
                var validatePass = (rule, value, callback) => {
                    if (value === '') {
                        callback(new Error('请输入密码'));
                    } else {
                        if (this.ruleForm.pusure_password !== '') {
                            this.$refs.ruleForm.validateField('pusure_password');
                        }
                        callback();
                    }
                };

                var validatePass2 = (rule, value, callback) => {
                    if (value === '') {
                        callback(new Error('请再次输入密码'));
                    } else if (value !== this.ruleForm.pupassword) {
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
                    puusername:'',
                    pupassword:'',
                    pusure_password:'',
                    puname:'',
                    pusex:'',
                    pubirthday:'',
                    pucard:'',
                    puaddress:'',
                    puphone:'',
                    puhistory:'',

                },
                inf:'',

                rules: {
                    puusername: [
                        { required: true, message: '请输入注册账号', trigger: 'blur' },
                        { min: 5, max: 15, message: '长度在 5 到 15 个字符', trigger: 'blur' }
                    ],
                    pupassword: [
                        { validator: validatePass, trigger: 'blur' }
                    ],
                    pusure_password: [
                        { validator: validatePass2, trigger: 'blur' }
                    ],
                    puname: [
                        { required: true, message: '请输入姓名', trigger: 'blur' },
                        { min: 2, max: 4, message: '长度在 2 到 4 个汉字', trigger: 'blur' }
                    ],
                    pusex: [
                        { required: true, message: '请选择性别', trigger: 'change' }
                    ],
                    pucard: [
                        { validator: validateEmail, trigger: 'blur' }
                    ],
                    puphone: [
                        { validator: validatePhone, trigger: 'blur' }
                    ],
                    puaddress: [
                        { required: true, message: '请写入地址', trigger: 'blur' },
                        { min: 1, max: 100, message: '长度在 1 到 100 个字符', trigger: 'blur' }
                    ]


                }
            };
        },
        methods: {
            returnoff(){
                this.$router.push('/PatientLogin');
            },
            submitForm(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {

                        //执行正确操作
                        //改变男女
                        if(this.ruleForm.pusex === "男"){
                            this.ruleForm.pusex = 1
                        }
                        else{
                            this.ruleForm.pusex = 0
                        }
                        this.$axios.post('PatientRegisterController/registerpatient',

                            this.ruleForm



                        ).then(response=>{      //返回值部分
                            this.inf = response.data
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

                                this.$router.push('/PatientLogin');
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
                        }).catch(error=>{
                            console.log(error)
                        })






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




