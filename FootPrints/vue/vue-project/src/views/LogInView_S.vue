<template>
  <div>
    <tool-bar></tool-bar>
    <div id="inputData">
        <form>
          <div className="inputDiv">
            <label>아이디</label>
                <span>
                <input id='userId1' className="idItem" v-model="Id" type="text" placeholder="아이디" required>
                </span>
          </div>

          <div className="inputDiv">
            <label>비밀번호</label>
            <input id='password1' autoComplete="off"
                   v-model="Pwd"  placeholder="비밀번호" required>
          </div>

          <button type="submit" className="submitBtn" v-on:click.prevent="checkLogin">로그인하기</button>
        </form>
    </div>
  </div>
</template>

<script>
import ToolBar from '../components/ToolBar.vue'
export default {
    data() {
        return {
            Id: "",
            Pwd: "",
        }
    },
    computed() {
    },
    methods: {
        checkLogin(){
            if(this.PostValid()){
                const member = {
                  email: this.Id,
                  pwd: this.Pwd,
                  nick: 'nick',
                  phone: 'phone',
                  area: 'area'
                }
                this.$store.dispatch('POST_LOGIN', member)
                console.log(this.Pwd);
            }
        },
        PostValid(){
            if(this.Id != "" && this.Pwd != ""){
                return true;
            }
            else{
                alert("빈 칸을 두면 로그인이 안돼요.")
            }
        }
    },
    components:{
        ToolBar,
    },
}
</script>

<style scoped>
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
.submitBtn {
  background: #7aab85;
  border: 1px solid #7aab85;
  color: #ffffff;
  font-weight: bold;
}
.submitBtn:hover {
  background: #669270;
}
</style> 