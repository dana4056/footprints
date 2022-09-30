<template>
  <div id="wrap">
    <div id="contents">
      <h1>403</h1>
      <h3>요청하신 페이지에 대한 권한이 없습니다.</h3>
      <div id="btnBox">
        <button id="beforeBnt" v-on:click="beforePage()">이전페이지</button>
        <router-link to="/home"><button id="homeBnt">홈으로</button></router-link>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  methods: {
    beforePage(){
      history.back();
    }
  }
}
</script>

<style scoped>
  button{
    box-sizing: border-box;
    width: 95px;
    height: 32px;
    margin: 0px 4px;
    border-radius: 10px;
    border-style: solid;
    cursor: pointer;
  }
  #wrap{
    width: 50%;
    background-color: #d9e2db;
    height: 50vh;
    margin: 25vh auto;
    position: relative;
  }
  #contents{
    position: absolute;
    top: 25%;
    left: 25%;
  }
  #beforeBnt{
    background-color: white;
    color: black;
    border-color: #f6f6f6;
  }
  #homeBnt{
    background-color: #76917c;
    color: white;
    border-color: #667a6a;
  }

</style>