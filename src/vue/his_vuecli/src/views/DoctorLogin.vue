<template>
    <div class="doctorlogin">
        <div class="doctorlogintab">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
                <el-breadcrumb-item>医生登录</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="doctorlogin_inf">DOCTOR LOGIN</div>
        <div class="doctorlogin_02"></div>
        <div class="doctorlogin_03"></div>
        <div class="doctorlogin_01">

            <div class="doctorlogin_01_form">
                <el-form ref="form" :model="form" label-width="80px">
                    <el-form-item label="用户名" >
                        <el-input v-model="form.username"></el-input>
                    </el-form-item>
                    <el-form-item label="密码">
                        <el-input v-model="form.password" type="password"></el-input>
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
        methods:{
            onSubmit(){
            //    登陆验证
                this.$axios.post('DoctorLoginController/doctorlogin',

                    this.$qs.stringify(
                        {
                            username:this.form.username,
                            password:this.form.password
                        }
                    )
                ).then(response=>{      //返回值部分
                    this.inf = response.data
                    if(this.inf === "医生"){

                        this.$router.push({
                            path:'/DoctorHome' ,               	//目标URL，为注册的路由

                            query:{
                                username:this.form.username      	//传入参数，参数会写入URL，params用法同query，只不过参数不会写进URL
                            }

                        })
                    }
                    if(this.inf === "管理员"){
                        this.$router.push({
                            path:'/AdminHome' ,               	//目标URL，为注册的路由

                            query:{
                                username:this.form.username      	//传入参数，参数会写入URL，params用法同query，只不过参数不会写进URL
                            }
                        })
                    }
                    if(this.inf === "收费员"){
                        this.$router.push({
                            path:'/CashierHome' ,               	//目标URL，为注册的路由

                            query:{
                                username:this.form.username      	//传入参数，参数会写入URL，params用法同query，只不过参数不会写进URL
                            }
                        })
                    }
                    if(this.inf === "检验医生"){
                        this.$router.push({
                            path:'/DoctorTestHome' ,               	//目标URL，为注册的路由

                            query:{
                                username:this.form.username      	//传入参数，参数会写入URL，params用法同query，只不过参数不会写进URL
                            }
                        })
                    }
                    if(this.inf === "检查医生"){
                        this.$router.push({
                            path:'/DoctorInspectionHome' ,               	//目标URL，为注册的路由

                            query:{
                                username:this.form.username      	//传入参数，参数会写入URL，params用法同query，只不过参数不会写进URL
                            }
                        })
                    }
                    if(this.inf === "药房医生"){
                        this.$router.push({
                            path:'/DoctorDrugHome' ,               	//目标URL，为注册的路由

                            query:{
                                username:this.form.username      	//传入参数，参数会写入URL，params用法同query，只不过参数不会写进URL
                            }
                        })
                    }
                    if(this.inf === "no"){
                        this.$alert('', '登陆失败 账号密码错误', {
                            confirmButtonText: '确定',
                            callback: action => {
                                this.$message({
                                    type: 'info',
                                    message: `no`
                                });
                            }
                        });
                    }

                }).catch(error=>{
                    console.log(error)
                })
            },
            Register(){
                this.$router.push('/DoctorRegiste');
            },
            nopassword(){
                this.$router.push('/DoctorForgetPassword');
            }
        },
        components: {

        }}
</script>

<style>
    .doctorlogintab{
        position: absolute;
        left: 300px;
        top: 20px;
        color: #42b983;
        z-index: 9999;

    }
    .doctorlogin{

        height: 700px;
    }
    .doctorlogin_inf{
        position: absolute;
        top: 100px;
        left: 550px;
        font-family: "Microsoft YaHei UI";
        font-size: 52px;

    }
    .doctorlogin_01{
        position: absolute;
        /*background-color: #42b983;*/
        top: 170px;
        left: 550px;
        width: 400px;
        height: 300px;
    }
    .doctorlogin_01_form{
        margin: 20px;
        position: relative;
        right: 20px;
    }
    .doctorlogin_02{
        position: absolute;
        height: 700px;
        width: 200px;
        background-color: black;
    }
    .doctorlogin_03{
        position: absolute;
        left: 1329px;
        width: 200px;
        height: 700px;
        background-color: black;
    }

</style>