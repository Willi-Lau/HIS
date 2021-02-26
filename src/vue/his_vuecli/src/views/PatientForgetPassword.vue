<template>
    <div class="patientforgetpassword">
        <div class="doctortab">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
                <el-breadcrumb-item :to="{ path: '/PatientLogin' }">患者登录</el-breadcrumb-item>
                <el-breadcrumb-item>患者忘记密码验证</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="patientforgetpassword_form">
            <el-form :inline="true" :model="formInline" class="demo-form-inline">
                <el-form-item label="输入账号">
                    <el-input v-model="formInline.username" placeholder="账号"></el-input>
                </el-form-item>

                <el-form-item label="选择验证方式">
                    <el-select v-model="formInline.type" placeholder="验证方式">
                        <el-option label="手机号" value="duphone"></el-option>
                        <el-option label="身份证号" value="ducard"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="输入验证信息">
                    <el-input v-model="formInline.inf" placeholder="信息"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="onSubmit">开始验证</el-button>
                </el-form-item>
            </el-form>
        </div>


    </div>
</template>

<script>
    export default {
        data() {
            return {
                formInline: {
                    username:'',
                    inf: '',
                    type: ''
                },
                inf:''
            }
        },
        methods: {
            onSubmit() {
                this.$axios.post('PatientForgetPasswordController/selectforgetpasswordinf',
                    this.$qs.stringify(
                        {
                            'username':this.formInline.username,
                            'key': this.formInline.type,
                            'value': this.formInline.inf
                        }
                    )
                ).then(response => {      //返回值部分

                    if (response.data === 'success') {
                        this.$notify({
                            title: '成功',
                            message: '验证成功',
                            type: 'success'
                        });
                        //跳转到设置新密码界面
                        this.$router.push({
                            path:'/PatientChangePassword' ,

                            query:{
                                username:this.formInline.username
                            }
                        })
                    } else {
                        this.$alert('', '输入信息有误，清修改', {
                            confirmButtonText: '确定',
                            callback: action => {
                                this.$message({
                                    type: 'info',
                                    message: `信息有误`
                                });
                            }
                        });
                    }
                }).catch(error => {
                    console.log(error)
                })


            }}
    }
</script>


<style>
    .patientforgetpassword_form{
        width: 400px;
        position: absolute;
        top: 150px;
        left: 600px;
    }
    .doctortab{
        position: absolute;
        left: 300px;
        top: 20px;
        color: #42b983;

    }


</style>