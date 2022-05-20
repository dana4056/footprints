<template>
  <div className="body">
    <!-- <NavigationBar></NavigationBar> -->
    <header>
      <div className="header">
        <!-- router-link는 자동으로 a태그로 변환하고 부가적인 기능 제공 -->
        <router-link to="/home" className="logo"><img src="../assets/logo.png">발자취</router-link>
      </div>
    </header>
    <div id="wrap">
      <!-- SNS 회원가입 -->
      <h3>회원가입</h3>
      <div className="SnsBox">
        <div className="tinytext">SNS로 간편 회원가입</div>
        <ul>
          <li><img className="brandLogo" src="../assets/kakaoLogo.png"></li>
          <li><img className="brandLogo" src="../assets/NaverLogo.png"></li>
          <li><img className="brandLogo" src="../assets/facebookLogo.png"></li>
        </ul>
      </div>
      <!-- 일반 회원가입 form -->
      <div id="inputData">
        <form>
          <div className="inputDiv">
            <label>아이디</label>
            <div className="idGroup">
                        <span>
                            <input id='userId1' className="idItem" v-model="Id1" type="text" placeholder="아이디" required>
                        </span>
              <span className="idItem">@</span>
              <span className="idItem">
                <select name="domain" id='userId2' v-model="Id2" v-on:focusout="checkEmail">
                    <option value="" selected="selected" disabled hidden>----- 선택 -----</option>
                    <option value="naver.com">naver.com</option>
                    <option value="gmail.com">gmail.com</option>
                    <option value="skuniv.ac.kr">skuniv.ac.kr</option>
                </select>
                <!-- <span className="errorType" v-if="">사용할 수 없는 아이디입니다. </span> -->
              </span>
            </div>
          </div>

          <div className="inputDiv" v-bind:class="{errorType:isDiferrentPw()}">
            <label>비밀번호</label>
            <input id='password1' autoComplete="off"
                   v-model="Pw1" type="password" placeholder="비밀번호 (영어, 숫자, 특수문자 포함 8~20자)" required>
          </div>

          <div className="inputDiv" v-bind:class="{errorType:isDiferrentPw()}">
            <label>비밀번호 확인</label>
            <input id='password2' autoComplete="off"
                   v-model="Pw2" type="password" placeholder="비밀번호 재입력" required>
            <span className="errorType" v-if="isDiferrentPw()">비밀번호가 일치하지 않습니다.</span>
          </div>

          <div className="inputDiv" v-bind:class="{errorType:!isValidNick()}">
            <label>닉네임</label>
            <input id='nickname' v-model="Nick" v-on:focusout="checkNick"
                   type="text" placeholder="별명 (2~8자)" required>
            <span className="errortype" v-if="!isValidNick()">닉네임은 2~8글자이어야 합니다.</span>
          </div>

          <div className="inputDiv">
            <label>휴대폰 번호</label>
            <div className="phoneGroup">
              <span className="phoneItem"><input id='userPhone' v-model="Phone" type="text" placeholder="전화번호"></span>
              <span className="phoneItem"><button className="btn1"
                                                  v-on:click.prevent="sendAuthenticCode">인증번호 받기</button></span>
            </div>
            <div className="phoneGroup">
              <span className="phoneItem"><input id='certiNum' type="text" placeholder="인증번호"></span>
              <span className="phoneItem"><button className="btn1">확인</button></span>
            </div>
          </div>

          <div className="inputDiv">
            <label>지역설정</label>
            <input id='userArea' v-model="Area" type="text" readOnly placeholder="지역명(ex. 성북구 정릉동)">
            <button type="button" className="btn2" v-on:click="searchBox">지역 검색</button>
          </div>
          <div v-if="visable">
            <p>지역 검색</p>
            <select v-model="sido" v-on:change="serachSigoongu">
              <option value="시/도">시/도</option>
              <option v-for="i in sidoList" v-bind:key="i.properties.ctprvn_cd" v-bind:value="i.properties">
                {{ i.properties.ctp_kor_nm }}
              </option>
            </select>
            <select v-model="sigoongu" v-on:change="serachEupmyeondong">
              <option value="시/군/구">시/군/구</option>
              <option v-for="i in sigoonguList" v-bind:key="i.properties.sig_cd" 
              v-bind:value="i.properties">{{ i.properties.sig_kor_nm }}</option>
            </select>
            <select v-model="eupmyeondong">
              <option value="읍/면/동">읍/면/동</option>
              <option v-for="i in eupmyeondongList" v-bind:key="i.properties.emd_cd" 
              v-bind:value="i.properties">{{ i.properties.emd_kor_nm }}</option>
            </select>
            <button type="button" className="btn2" v-on:click="fixArea">확인</button>
          </div>
          <button type="submit" className="submitBtn" v-on:click.prevent="submitData">회원가입하기</button>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      Id1: "",
      Id2: "",
      Pw1: "",
      Pw2: "",
      Nick: "",
      Phone: "",
      sidoList:{},
      sigoonguList:{},
      eupmyeondongList:{},
      sido:{},
      sigoongu:{},
      eupmyeondong:{},
      Area: "",
      visable:false
    }
  },
  computed: {
    email() {
      return this.Id1 + "@" + this.Id2;
    },
    area(){
      return this.sido.ctp_kor_nm + " " + this.sigoongu.sig_kor_nm + " " + this.eupmyeondong.emd_kor_nm;
    }
  },
  created(){
    this.searchSido();
  },
  methods: {
    checkEmail() {   //이메일 중복체크 위해 보냄
      if (this.Id1 != "" && this.Id2 != "") {
        this.$store.dispatch('POST_EMAIL', this.email);
      }
    },
    checkNick() {   //닉네임 중복체크 위해 보냄
      if (this.Nick != "") {
        this.$store.dispatch('POST_NICK', this.Nick);
      }
    },
    sendAuthenticCode() {
      if (this.Phone != "") {
        this.$store.dispatch('POST_PHONE', this.Phone);
      }
    },
    submitData() {
      if (this.isValidAll()) {
        const member = {
          email: this.email,
          pw: this.Pw1,
          nick: this.Nick,
          phone: this.Phone,
          area: this.area
        }

        //back으로 member post요청
        this.$store.dispatch('POST_MEMBER', member);
      } else {
        alert("입력한 정보들을 확인해주세요");
      }
    },
    isValidAll() {  // 최종 양식 확인
      if (this.Id1 != "" && this.Id2 != "" && this.Pw1 != "" && this.Pw2 != "" &&
          this.Nick != "" && this.Phone != "" && this.Area != "") {
        if (this.isValidNick() && !this.isDiferrentPw()) {
          return true;
        } else {
          return false;
        }
      } else {
        return false;
      }
    },
    isDiferrentPw() {      // 비밀번호 & 비밀번호 확인란 일치 여부
      if (this.Pw1 != "" && this.Pw2 != "") {
        if (this.Pw1 != this.Pw2) {
          return true;
        } else {
          return false;
        }
      }
    },
    isValidNick() {   // 닉네임 형식 판단
      if (this.Nick != "") {
        if (this.Nick.length >= 2 && this.Nick.length <= 8) {
          return true;
        } else {
          return false;
        }
      } else {
        return true;
      }
    },
    // searchArea() {  // 지역 검색
    //   new window.daum.Postcode({
    //     oncomplete: (data) => {
    //       this.Area = data.sido + " " + data.sigungu + " " + data.bname;
    //     }
    //   }).open();
    // },
    searchSido(){  
      const config={
        key:"CEB52025-E065-364C-9DBA-44880E3B02B8",
        domain:"http://localhost:8080",
        service:"data",
        version:"2.0",
        request:"getfeature",
        format:"json",
        size:"1000",
        page:"1",
        attribue:"true",
        crs:"EPSG:3857",
        geomfilter:"BOX(13663271.680031825,3894007.9689600193,14817776.555251127,4688953.0631258525)",
        data:"LT_C_ADSIDO_INFO"
      }
    
      axios.get(`/req/data?key=${config.key}&domain=${config.domain}&service=${config.service}&version=${config.version}&request=${config.request}&format=${config.format}&size=${config.size}&page=${config.page}&attribute=${config.attribue}&crs=${config.crs}&geomfilter=${config.geomfilter}&data=${config.data} `)
        .then(response =>{ 
          this.sidoList = response.data.response.result.featureCollection.features;
          console.log(this.sidoList);
          })
        .catch(error => {console.log(error)})
    },
    serachSigoongu(){
      const config={
        key:"CEB52025-E065-364C-9DBA-44880E3B02B8",
        domain:"http://localhost:8080",
        service:"data",
        version:"2.0",
        request:"getfeature",
        format:"json",
        size:"1000",
        page:"1",
        attribue:"true",
        crs:"EPSG:3857",
        geomfilter:"BOX(13663271.680031825,3894007.9689600193,14817776.555251127,4688953.0631258525)",
        data:"LT_C_ADSIGG_INFO",
        attrFilter:`sig_cd:like:${this.sido.ctprvn_cd}`
      }		
      
      axios.get(`/req/data?key=${config.key}&domain=${config.domain}&service=${config.service}&version=${config.version}&request=${config.request}&format=${config.format}&size=${config.size}&page=${config.page}&attribute=${config.attribue}&crs=${config.crs}&geomfilter=${config.geomfilter}&data=${config.data}&attrFilter=${config.attrFilter} `)
        .then(response =>{ 
          this.sigoonguList = response.data.response.result.featureCollection.features;
          console.log(this.sigoonguList);
          })
        .catch(error => {console.log(error)})
    },
    serachEupmyeondong(){
      const config={
        key:"CEB52025-E065-364C-9DBA-44880E3B02B8",
        domain:"http://localhost:8080",
        service:"data",
        version:"2.0",
        request:"getfeature",
        format:"json",
        size:"1000",
        page:"1",
        attribue:"true",
        crs:"EPSG:3857",
        geomfilter:"BOX(13663271.680031825,3894007.9689600193,14817776.555251127,4688953.0631258525)",
        data:"LT_C_ADEMD_INFO",
        attrFilter:`emd_cd:like:${this.sigoongu.sig_cd}`
      }		
      
      axios.get(`/req/data?key=${config.key}&domain=${config.domain}&service=${config.service}&version=${config.version}&request=${config.request}&format=${config.format}&size=${config.size}&page=${config.page}&attribute=${config.attribue}&crs=${config.crs}&geomfilter=${config.geomfilter}&data=${config.data}&attrFilter=${config.attrFilter} `)
        .then(response =>{ 
          this.eupmyeondongList = response.data.response.result.featureCollection.features;
          console.log(response);
          })
        .catch(error => {console.log(error)})
    },
    fixArea(){
      this.Area = this.area;
    },
    searchBox(){
      this.visable = true;
    }
  }
}
</script>

<style scoped>
body {
  text-align: center;
  font-family: 'Noto Sans KR', sans-serif;
}

.header {
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

.logo img {
  margin: 3px 10px;
}

h3 {
  display: block;
}

#wrap {
  width: 360px;
  margin: 0 auto;
  padding: 100px 0;
}

.SnsBox ul {
  padding: 0;
  list-style: none;
}

.SnsBox li {
  display: inline;
}

.SnsBox .brandLogo {
  border-radius: 7px;
}

.SnsBox .tinytext {
  font-weight: lighter;
  font-size: 12px;
}

/* 일반 회원가입 form */
#inputData {
  padding: 50px 0 0 0;
  border-top: 1px solid rgb(226, 226, 226);
}

.inputDiv {
  margin: 0 0 30px;
}

label {
  text-align: left;
  display: block;
  font-size: 15px;
  font-weight: bold;
  color: #292929;
  margin: 0 0 12px;
}

input, select {
  font-family: 'Noto Sans KR', sans-serif;
  outline: none;
  width: 100%;
  height: 40px;
  background: #FAFAFA;
  border: 1px solid #BDBDBD;
  box-sizing: border-box;
  border-radius: 8px;
  padding: 8px 15px 9px;
  margin: 0 0 7px 0;
}

input:focus {
  outline: none;
  border-color: #737beb;
  background: #e8f0fe;
}

input:hover {
  background: #F3F3F3;
}

input::placeholder {
  color: #BDBDBD;
  font-weight: 100;
}

.errorType input {
  background: #fff6f6;
  outline: none;
  border-color: #eb7373;
}

.errorType span {
  text-align: left;
  font-size: 12px;
  color: #eb7373;
}

button {
  font-family: 'Noto Sans KR', sans-serif;
  width: 100%;
  height: 40px;
  background: #ffffff;
  color: #7aab85;
  border: 1px solid #7aab85;
  box-sizing: border-box;
  border-radius: 13px;
}

.btn1, .btn2 {
  padding: 8px 15px 9px;
}

.btn1 {
  margin: 0 0 7px 5px;
}

.btn1:hover, .btn2:hover {
  background: #c5e6cd54;
}

.submitBtn {
  background: #7aab85;
  border: 1px solid #7aab85;
  color: #ffffff;
  font-weight: bold;
}

.submitBtn:hover {
  background: #669270;
}

.idGroup {
  display: flex;
  align-items: center;
  justify-content: space-between;
}

.idItem {
  flex-grow: 1;
}

.phoneGroup {
  display: flex;
  align-items: center;
  justify-content: space-between;
}

.phoneItem:nth-child(1) {
  flex-grow: 3;
}

.phoneItem:nth-child(2) {
  flex-grow: 1;
}

.search{
  background-color:#669270;  
}




</style>
