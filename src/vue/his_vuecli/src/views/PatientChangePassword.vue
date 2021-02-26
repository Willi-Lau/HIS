<template>
    <div class="doctorchangepassword">
        <!--        {{formInline.username}}-->
        <div class="doctortab">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
                <el-breadcrumb-item :to="{ path: '/PatientLogin' }">患者登录</el-breadcrumb-item>
                <el-breadcrumb-item :to="{ path: '/PatientForgetPassword' }">患者忘记密码验证</el-breadcrumb-item>
                <el-breadcrumb-item > 改变密码</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="doctorchangepassword_form">
            <el-form :inline="true" :model="formInline" class="demo-form-inline">
                <el-form-item label="设置新密码">
                    <el-input v-model="formInline.password" placeholder="密码"></el-input>
                </el-form-item>


                <el-form-item>
                    <el-button type="primary" @click="onSubmit">提交</el-button>
                </el-form-item>
            </el-form>
        </div>

    </div>
</template>

<script>



    export default {
        data(){
            return{
                formInline: {
                    username:this.$route.query.username,
                    password:''
                },
            }
        },
        components: {

        },
        methods:{
            onSubmit(){
                this.$axios.post('PatientChangePasswordController/changepassword',
                    this.$qs.stringify(
                        {
                            'username':this.formInline.username,
                            'password': this.formInline.password

                        }
                    )
                ).then(response => {      //返回值部分
                    this.$alert('', '修改成功', {
                        confirmButtonText: '确定',
                        callback: action => {
                            this.$router.push({
                                path:'/PatientLogin' ,


                            })
                            this.$message({
                                type: 'info',
                                message: `修改成功`
                            });
                        }
                    });


                }).catch(error => {
                    console.log(error)
                })

            }        }
    }
</script>

<style>
    .doctortab{
        position: absolute;
        left: 100px;
        top: 20px;
        color: #42b983;

    }
    .doctorchangepassword_form{
        position: absolute;
        left: 500px;
        top: 300px;
    }
</style>