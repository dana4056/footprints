<template>
    <div id="textBox">
        <h3>개인정보 수집·이용 안내</h3>
        <p>발자취 서비스에서는 아래와 같이 개인정보를 수집 및 이용합니다.
            <br>- 개인정보 수집 목적:  회원관리
            <br>- 개인정보 수집 항목:  아이디, 비밀번호, 이메일
            <br>- 보유 및 이용기간:  회원 탈퇴시까지
        </p>
        <button id="beforeBnt" v-on:click="beforePage()">이전페이지</button>
        <router-link to="/home"><button id="homeBnt">홈으로</button></router-link>
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
#textBox{
    padding: 15px 25px;
    border: solid 2px gray;
    display: inline-block;
    text-align: left;
    margin-top: 15%;
}
button{
    margin-right: 10px;
    padding: 5px 8px;
    background-color: #efefef;
    color: black;
    border-color: #bebebe;
    border-radius: 15px;
    border-style: solid;
    cursor: pointer;
}
button:hover {
      background-color:#b3b3b3;
      transition: 0.7s;
  }

</style>