<template>
  <div class="footer">
      <div id="intro" class="textBox">
        <div class="logo">
          <img src="../assets/logo_gray.png">
          <span><b>발자취</b></span>
        </div>
        <p><b>Since 2022</b></p>
        <p>이메일 (문의 및 피드백): <a href= "mailto:skufootprints@gmail.com" >skufootprints@gmail.com</a></p>
        <p><router-link to="/info" style="text-decoration:underline;">개인정보 수집·이용 안내</router-link></p>
      </div>
      <div id="contact" class="textBox"> 
        <p style="margin:0;"><b>CATEGORY</b></p>
        <p><router-link to="/signup">회원가입</router-link> 및 <router-link to="/">로그인</router-link></p>
        <p><router-link to="/delivery/post">배달 함께하기</router-link></p>
        <p><a href= "mailto:skufootprints@gmail.com" >오류신고 및 피드백</a></p>
      </div>
  </div>
</template>

<script>
export default {

}
</script>

<style scoped>
a {
  text-decoration: none;
  color: #d0d0d0;
}

.footer{
  /* height: 200px; */
  background-color: #f8f8f8;
  color: #d0d0d0;
  display: flex;
  justify-content: space-around;
}
.textBox{
  margin: 30px auto;
  padding: 50px 10px;
  font-size: small;
}
#intro{
  text-align: left;
  vertical-align: middole;
}
#contact{
  text-align: right;
}
.logo{
  height:25px;
}
.logo > img{
  width:25px;
  margin: 0 5px 0 0;
}
.logo > span{
  line-height: 25px;
  vertical-align: bottom;
}
</style>