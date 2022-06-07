<template>
  <el-container id="main">
    <el-header>Header</el-header>
    <el-container>
      <el-aside width="220px">
        <ul class="leftNav">
          <li><div class="navIcon"><img src="../assets/images/nav1.png" alt="icon"></div><span class="ft">进口食品、生鲜</span></li>
          <li><div class="navIcon"><img src="../assets/images/nav2.png" alt="icon"></div><span class="ft">食品、饮料、酒</span></li>
          <li><div class="navIcon"><img src="../assets/images/nav3.png" alt="icon"></div><span class="ft">母婴、玩具、童装</span></li>
          <li><div class="navIcon"><img src="../assets/images/nav4.png" alt="icon"></div><span class="ft">家居、家庭清洁、纸巾</span></li>
          <li><div class="navIcon"><img src="../assets/images/nav5.png" alt="icon"></div><span class="ft">美妆、个人护理、洗护</span></li>
          <li><div class="navIcon"><img src="../assets/images/nav6.png" alt="icon"></div><span class="ft">女装、内衣、中老年</span></li>
          <li><div class="navIcon"><img src="../assets/images/nav7.png" alt="icon"></div><span class="ft">鞋靴、箱包、腕表配饰</span></li>
          <li><div class="navIcon"><img src="../assets/images/nav8.png" alt="icon"></div><span class="ft">男装、运动</span></li>
          <li><div class="navIcon"><img src="../assets/images/nav9.png" alt="icon"></div><span class="ft">手机、小家电、电脑</span></li>
          <li><div class="navIcon"><img src="../assets/images/nav10.png" alt="icon"></div><span class="ft">礼品、充值</span></li>
        </ul>
      </el-aside>
      <el-main>Main</el-main>
    </el-container>
  </el-container>
</template>

<script>
export default {
  name: "goodsMenu"
}
</script>

<style scoped>
  #main {
    margin: 0 7%;
  }
  #main .leftNav li {
    height: 40px;
    line-height: 40px;
    padding-left: 10px;
    background: #b01d1d url(../assets/images/n_arrow.gif) no-repeat 204px center;
    box-sizing: border-box;
    /*font-size: 14px;*/
  }
  #main .leftNav .navIcon {
    width: 20px;
    height: 40px;
    display: inline-block;
    text-align: center;
  }
  #main .leftNav .navIcon img {
    vertical-align: middle;
  }
  #main .leftNav .ft {
    font-size: 14px;
    color: #ffffff;
    padding-left: 10px;
  }
</style>