<template>
  <div class="container">
    <el-menu :router="true" :default-active="active" class="el-menu" mode="horizontal" @select="handleSelect">
      <el-menu-item index="/">番薯</el-menu-item>
      <el-menu-item index="/list"><i class="fa fa-flag" aria-hidden="true"></i>探索</el-menu-item>
      <el-menu-item index="/singUp" class="right"><i class="fa fa-user-o" aria-hidden="true"></i>注册</el-menu-item>
      <el-menu-item index="/singIn" class="right"><i class="fa fa-key" aria-hidden="true"></i>登录</el-menu-item>
    </el-menu>
  </div>
</template>

<script>
  export default {
    name: "Header",
    data() {
      return {
        active: '/'
      }
    },
    methods: {
      handleSelect(key, keyPath) {
        console.log(key, keyPath)
      }
    }
  }
</script>

<style scoped>
  .container {
    padding: 0 10%;
    background: #eef1f6;
  }

  .right {
    float: right;
  }

  .el-menu-item:first-child {
    margin-left: 0;
    font-size: 25px;
    font-weight: 100;
    background-color: #20a0ff;
    color: #fff;
  }

  .el-menu-item:first-child:hover {
    background-color: #20a0ff;
  }
</style>
