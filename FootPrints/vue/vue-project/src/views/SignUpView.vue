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
        <!-- 일반 회원가입 form -->
        <div id="inputData">
            <form>
                <div class="inputDiv">
                    <label>아이디</label>
                    <div class="idGroup">
                        <span>
                            <input id='userId1' class="idItem" v-model="Id1" type="text" placeholder="아이디" required>
                        </span>
                        <span class="idItem">@</span>
                        <span class="idItem">
                        <select name="domain" id='userId2' v-model="Id2">
                            <option value="naver.com">naver.com</option>
                            <option value="gmail.com">gmail.com</option>
                            <option value="skuniv.ac.kr">skuniv.ac.kr</option>
                        </select>
                        </span>
                    </div>
                </div>

                <div class="inputDiv" v-bind:class="{errorType:isDiferrentPw}">
                    <label>비밀번호</label>
                    <input id='password1' v-on:focusout="checkPw"  autocomplete="off"
                    v-model="Pw1" type="password" placeholder="비밀번호 (영어, 숫자, 특수문자 포함 8~20자)" required>
                </div>

                <div class="inputDiv" v-bind:class="{errorType:isDiferrentPw}">
                    <label>비밀번호 확인</label>
                    <input id='password2' v-on:focusout="checkPw"  autocomplete="off"
                    v-model="Pw2" type="password" placeholder="비밀번호 재입력" required>
                    <span class="errortype" v-if="isDiferrentPw">비밀번호가 일치하지 않습니다.</span>
                </div>

                <div class="inputDiv" v-bind:class="{errorType:isValidNick()}">
                    <label>닉네임</label>
                    <input id='nickname' v-model="Nick" type="text" placeholder="별명 (2~8자)" required>
                    <span class="errortype" v-if="isValidNick()">닉네임은 2~8글자이어야 합니다.</span>
                </div>

                <div class="inputDiv">
                    <label>휴대폰 번호</label>
                    <div class="phoneGroup">
                        <span class="phoneItem"><input id='userPhone' v-model="Phone" type="text" placeholder="전화번호"></span>
                        <span class="phoneItem"><button class="btn">인증번호 받기</button></span>
                    </div>
                    <div class="phoneGroup">
                        <span class="phoneItem"><input id='certiNum' type="text" placeholder="인증번호"></span>
                        <span class="phoneItem"><button class="btn">확인</button></span>
                    </div>
                </div>

                <div class="inputDiv">
                    <label>지역설정</label>
                    <input id='userArea' v-model="Area" type="text" placeholder="지역명(ex. 성북구 정릉동)">
                    <input id='RuserArea' type="text">
                </div>
                <button type="submit" v-on:click.prevent="submitData">회원가입하기</button>
            </form>
        </div>
    </div>
  </div>
</template>

<script>
// import axios from "axios"

export default {
  data(){
    return {
      Id1: "",
      Id2: "",
      Pw1: "",
      Pw2: "",
      Nick: "",
      Phone: "",
      Area: "",
      isDiferrentPw: false
    }
  },
  methods:{
    submitData(){
      if(this.isValidAll()){
        const member = {
          email: this.Id1+"@"+this.Id2,
          pw: this.Pw1,
          nick: this.Nick,
          phone: this.Phone,
          area: this.Area
        }

        //store에 member 저장
        this.$store.commit('SET_MEMBER', member);

        this.$router.replace("/signup-completed");

        // //back으로 member post요청
        // axios.post('http://localhost:8080/signup', member)
        // .then(response =>{
        //   console.log(response);
        //   this.$router.replace("/signup-completed");
        // }).catch(error=>{
        //     console.log(error);
        // })
      }
      else{
        alert("입력한 정보들을 확인해주세요");
      }
    },
    isValidAll(){  // 최종 양식 확인
      if(this.Id1 != "" && this.Id2 != "" && this.Pw1 != "" && this.Pw2 != "" &&
      this.Nick != "" && this.Phone != "" && this.Area != ""){
        if(!this.isValidNick() && !this.isDiferrentPw){
          return true;
        }
        return false;
      }
      else{
        return false;
      }
    },
    checkPw(){      // 비밀번호 & 비밀번호 확인란 일치 여부
      if(this.Pw1 != "" && this.Pw2 != ""){
        this.isDiferrentPwj = (this.Pw1 != this.Pw2) ? true : false;
      }
    },
    isValidNick(){   // 닉네임 형식 판단
      if(this.Nick != ""){
        if(this.Nick.length >=2 && this.Nick.length <= 8){
          return false;
        }
        else{
          return true;
        }
      }
    }
  }
}
</script>

<style scoped>
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
/* 일반 회원가입 form */
#inputData{
  padding:50px 0 0 0;
  border-top: 1px solid rgb(226, 226, 226);
}
.inputDiv{
    margin: 0 0 30px;
}
label{
    text-align: left;
    display: block;
    font-size: 15px;
    font-weight: bold;
    color: #292929;
    margin: 0 0 12px;
}

input, select{
    outline: none;
    width: 100%;
    height: 40px;
    background: #F8F8F8;
    border: 1px solid #BDBDBD;
    box-sizing: border-box;
    border-radius: 8px;
    padding: 8px 15px 9px;
    margin: 0 0 7px 0;
}
input:focus{
  outline: none;
  border-color:#737beb;
  background: #e8f0fe;
}
.errorType input{
  background: #fff6f6;
  outline: none;
  border-color:#eb7373;
}
.errorType span{
  text-align: left;
  font-size: 12px;
  color: #eb7373;
}
button{
    width: 100%;
    height: 40px;
    background: #cdcdcd;
    border: 1px solid #cdcdcd;
    box-sizing: border-box;
    border-radius: 10px;
}
.btn{
    padding: 8px 15px 9px;
    margin: 0 0 7px 5px;
    border-radius: 15px;
}
.idGroup{
    display: flex;
    align-items: center;
    justify-content: space-between;
}
.idItem { 
  flex-grow: 1;
  }

.phoneGroup{
    display: flex;
    align-items: center;
    justify-content: space-between;
}
.phoneItem:nth-child(1) { flex-grow: 3;}
.phoneItem:nth-child(2) { flex-grow: 1;}


</style>
