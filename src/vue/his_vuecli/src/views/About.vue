<template>


    <div id="app">
      <el-table :data="tableData" class="tb-edit" style="width: 100%" highlight-current-row @row-click="handleCurrentChange">
        <el-table-column label="日期" width="180">
          <template scope="scope">
            <el-input size="small" v-model="scope.row.date" placeholder="请输入内容" @change="handleEdit(scope.$index, scope.row)"></el-input> <span>{{scope.row.date}}</span>
          </template>
        </el-table-column>
        <el-table-column label="姓名" width="180">
          <template scope="scope">
            <el-input size="small" v-model="scope.row.name" placeholder="请输入内容" @change="handleEdit(scope.$index, scope.row)"></el-input> <span>{{scope.row.name}}</span>
          </template>
        </el-table-column>
        <el-table-column prop="address" label="地址">
          <template scope="scope">
            <el-input size="small" v-model="scope.row.address" placeholder="请输入内容" @change="handleEdit(scope.$index, scope.row)"></el-input>
            <span>{{scope.row.address}}</span>
          </template>
        </el-table-column>
        <el-table-column prop="ni" label="地址2">
<!--          必须加 templateb = scope这个 才能使用 scpoe.row 来指向表格里本条数据-->
          <template scope="scope">
<!--            这里这个 @change 是根据 下拉框得改变 来改变 其他属性框里的值-->
            <el-select v-model="scope.row.ni" placeholder="请选择" @change="handleEdit1(scope.$index, scope.row)">
              <el-option
                      v-for="item in options"
                      :key="item.value"
                      :label="item.label"
                      :value="item.value">
              </el-option>

            </el-select>
            <span>{{scope.row.ni}}</span>
          </template>
        </el-table-column>



        <el-table-column label="操作">


          <template scope="scope">
<!--            <el-button size="small" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>-->
            <el-button size="small" type="danger" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-button size="small" type="danger" @click="add()">add</el-button>
      <br>数据:{{tableData}}
    </div>


</template>

<script>



  export default {
    el: '#app',
    data(){
       return{

         tableData: [{
           date: '2016-05-02',
           name: '王小虎',
           address: '上海市普陀区金沙江路 1518 弄',
           ni:''
         }, {
           date: '2016-05-04',
           name: '王小虎',
           address: '上海市普陀区金沙江路 1517 弄',
           ni:''
         }, {
           date: '2016-05-01',
           name: '王小虎',
           address: '上海市普陀区金沙江路 1519 弄',
           ni:''
         }, {
           date: '2016-05-03',
           name: '王小虎',
           address: '上海市普陀区金沙江路 1516 弄',
           ni:''
         }],options: [{
           value: '选项1',
           label: '黄金糕'
         }, {
           value: '选项2',
           label: '双皮奶'
         }, {
           value: '选项3',
           label: '蚵仔煎'
         }, {
           value: '选项4',
           label: '龙须面'
         }, {
           value: '选项5',
           label: '北京烤鸭'
         }],

       }

    },
    methods: {
      add(){
        this.tableData.push({
          date: '',
          name: '',
          address: ''
        })
      },
      handleCurrentChange(row, event, column) {
        console.log(row, event, column, event.currentTarget)
      },
      handleEdit(index, row) {
        console.log(index, row);
      },
      handleDelete(index, row) {
        console.log(index, row);
      },
      handleEdit1(index, row){
        this.tableData[index].name = "hahaha";
      }

    }
  }
</script>
<style>

  /*body {*/
  /*  font-family: Helvetica Neue, Helvetica, PingFang SC, Hiragino Sans GB, Microsoft YaHei, SimSun, sans-serif;*/
  /*  overflow: auto;*/
  /*  font-weight: 400;*/
  /*  -webkit-font-smoothing: antialiased;*/
  /*}*/
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
</style>