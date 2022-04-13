<template>
  <div id="wrap">
    <div id="logo">
      <img src="./assets/logo.png">
      <h3>발자취</h3>
    </div>
    <SignUpSNS></SignUpSNS>
    <InputData></InputData>
  </div>
</template>

<script>
import InputData from './components/InputData.vue'
import SignUpSNS from './components/SignUpSNS.vue'

export default {
  components: {
    InputData,
    SignUpSNS
  }
}
</script>

<style>
body {
  text-align: center;
  font-family: 'Noto Sans KR', sans-serif;
}
img{
  width: 30px;
  margin: 3px;
}
h3{
  display: inline;
}
#logo{
  display: flex;
  align-items: center;
  align-content: center;
  justify-content: center;
  margin: 30px;
}

#wrap {
    width: 360px;
    margin: 0 auto;
    padding: 130px 0;
}
ul{
  list-style: none;
}
</style>
