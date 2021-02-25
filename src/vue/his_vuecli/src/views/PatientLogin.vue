<template>
    <div class="patientlogin">
        <div class="patienttab">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
                <el-breadcrumb-item>患者登录</el-breadcrumb-item>
            </el-breadcrumb>
        </div>

        <div class="patientlogin_inf">PATIENT LOGIN</div>
        <div class="patientlogin_02"></div>
        <div class="patientlogin_03"></div>
        <div class="patientlogin_01">

            <div class="patientlogin_01_form">
                <el-form ref="form" :model="form" label-width="80px">
                    <el-form-item label="用户名" >
                        <el-input v-model="form.name"></el-input>
                    </el-form-item>
                    <el-form-item label="密码">
                        <el-input v-model="form.name"></el-input>
                    </el-form-item>
                    <br><br>
                    <el-form-item>
                        <el-button type="primary" @click="onSubmit">登陆</el-button>
                        <el-button @click="nopassword">忘记密码</el-button>
                        <el-button @click="Register">注册</el-button>
                    </el-form-item>
                </el-form>
            </div>

        </div>

    </div>
</template>

<script>



    export default {
        data(){
            return{
                form:{
                    username:'',
                    password:''
                }
            }

        },
        components: {

        }
    }
</script>

<style>
    .patienttab{
        position: absolute;
        left: 300px;
        top: 20px;
        color: #42b983;

    }
    .patientlogin{

        height: 700px;
    }
    .patientlogin_inf{
        position: absolute;
        top: 100px;
        left: 550px;
        font-family: "Microsoft YaHei UI";
        font-size: 52px;

    }
    .patientlogin_01{
        position: absolute;
        background-color: #8ab8e3;
        top: 170px;
        left: 550px;
        width: 400px;
        height: 300px;
    }
    .patientlogin_01_form{
        margin: 20px;
        position: relative;
        right: 20px;
    }
    .patientlogin_02{
        position: absolute;
        height: 700px;
        width: 200px;
        background-color: black;
    }
    .patientlogin_03{
        position: absolute;
        left: 1329px;
        width: 200px;
        height: 700px;
        background-color: black;
    }

</style>