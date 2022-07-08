<template>
  <div class="footer">
      <div id="intro" class="textBox">
        <div class="logo">
          <img src="../assets/logo_gray.png">
          <span>발자취</span>
        </div>
        <p>여기는 footer인데 무엇을 써야할까요.<br>일단 뭐라도 있어보이게 블라블라 적어볼게요.</p>
      </div>
      <div id="contact" class="textBox">
        <p>CONTACT<br>SKUfootprints@gmail.com</p>
        <p>Team Page<br>www.footprints.com(미정)</p>
      </div>
  </div>
</template>

<script>
export default {

}
</script>

<style scoped>

.footer{
    /* height: 200px; */
    background-color: #f8f8f8;
    display: flex;
    justify-content: space-around;
    color: #d0d0d0;
    
}
.textBox{
  padding: 10px;
  margin: 30px auto;
  font-size:small;
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