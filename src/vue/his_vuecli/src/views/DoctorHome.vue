<template>
    <div class="doctorhome">
<!--        侧边栏-->
        <div class="doctorhome_lect">

        </div>

    </div>
</template>

<script>



    export default {
        data(){
          return{
              username:this.$route.query.username
          }
        },
        components: {

        }
        ,created() {

        }
    }
</script>
<style>
    .doctorhome_lect{
        position: absolute;
        width: 200px;
        height: 700px;
        background-color: black;
    }
</style>