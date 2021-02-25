<template>
  <div class="home">
      <div >

      </div>
        <div class="home_title">
            <div class="home_img">
                <img src="../assets/Choose/HOME1.png" alt="" width="300px">
            </div>
          <div class="home_inf" >欢迎光临HIS 选择您的身份</div>
            <div class="home_button">
                <el-button type="danger" round @click="gopatientlogin"> 我是患者</el-button>
                <el-button type="danger" round @click="godoctorlogin">我是医生</el-button>
            </div>

        </div>

  </div>
</template>

<script>
// @ is an alias to /src


export default {
  name: 'Home',
  components: {

  },
    methods:{
        godoctorlogin(){
            this.$router.push('/DoctorLogin')
        },
        gopatientlogin(){
            this.$router.push('/PatientLogin')
        }
    }
}
</script>
<style>
    .home{

        height: 800px;
        position: relative;
    }
    .home_title{
        position: absolute;
        top: 50px;
        left: 600px;
    }
    .home_inf{
        font-size: 40px;
        position: absolute;
        left: -100px;
        top: 300px;
        width: 600px;
        font-family: "HoloLens MDL2 Assets";

    }
    .home_button{
        position: absolute;
        left: 40px;
        top: 400px;
    }


</style>