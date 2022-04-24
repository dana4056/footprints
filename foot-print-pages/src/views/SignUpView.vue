<template>
  <div>
    <!-- <NavigationBar></NavigationBar> -->
    <header>
        <div class="header">
        <!-- router-link는 자동으로 a태그로 변환하고 부가적인 기능 제공 -->
            <router-link to="/home" class="logo"><img src="../assets/logo.png">발자취</router-link>
        </div>
    </header>
    <div id="wrap">
        <!-- <div id="logo">
            <img src="../assets/logo.png">
            <h3>발자취</h3>
        </div> -->
        <!-- SNS 회원가입 -->
        <h3>회원가입</h3>
        <div class="SnsBox">
            <div class="tinytext">SNS로 간편 회원가입</div>
            <ul>
                <li><img class="brandLogo" src="../assets/kakaoLogo.png"></li>
                <li><img class="brandLogo" src="../assets/NaverLogo.png"></li>
                <li><img class="brandLogo" src="../assets/facebookLogo.png"></li>
            </ul>
        </div>
        <!--  -->
      <InputData></InputData>
    </div>
  </div>
</template>

<script>
import InputData from '../components/InputData.vue' 
//import NavigationBar from './components/common/NavigationBar.vue'

export default {
  components: {
    // NavigationBar,
    InputData,
  }
}
</script>

<style>
body {
  text-align: center;
  font-family: 'Noto Sans KR', sans-serif;
}

.header{
    /* background-color: #f7f7f7; */
    width: 100%;
    max-width: 1024px;
    margin: 0 auto;

    padding: 15px 45px;
    display: flex;
    justify-content: space-between;
    align-items: center;
}
.logo {
    display: flex;
    margin: 0px 10px;
    color: black;
    text-decoration: none;
    align-items: center;
    font-weight: bold;
}
.logo img{
    margin: 3px 10px;
}
h3{
    display: block;
}

#wrap {
    width: 360px;
    margin: 0 auto;
    padding: 100px 0;
}

.SnsBox ul{
    padding: 0;
    list-style: none;
}
.SnsBox li{
    display: inline;
}
.SnsBox .brandLogo{
    border-radius: 7px;
}
.SnsBox .tinytext{
    font-weight: lighter;
    font-size: 12px;
}

</style>
