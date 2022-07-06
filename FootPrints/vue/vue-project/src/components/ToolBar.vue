<template>
<header>
  <div class="header">
      <!-- router-link는 자동으로 a태그로 변환하고 부가적인 기능 제공 -->
    <router-link to="/home" class="logo"><img src="../assets/logo.png">발자취</router-link>
    <div v-if="!this.isLogin">
        <router-link to="/delivery/post" class="item"><button>배달 같이하기</button></router-link>
        <router-link to="/login" class="item">로그인</router-link> | 
        <router-link to="/signup" class="item">회원가입</router-link>
    </div>
    <div v-if="this.isLogin">
        <router-link to="/delivery/post" class="item"><button>배달 같이하기</button></router-link>
        <span id="u_name">{{this.$store.state.member.nick}}</span> 님 | 
        <span id="logout" v-on:click="logout">로그아웃</span>
    </div>
  </div>
</header>
</template>

<script>
// import axios from 'axios';
export default {
    data(){
        return {
            isLogin:false,
            userName:""
        }
    },
    created(){
        // const id = this.$cookies.get("JSESSIONID");
        // if(id != null){
        //     this.$cookies.set("JSESSIONID", id);
        //     this.isLogin = true;
        // }
        if(localStorage.getItem('jwt') != null){
            this.$store.dispatch('FETCH_NICK')
            if(this.$store.state.member.nick != ""){
                this.isLogin = true;
            }
        }
        
        // axios.get
    },
    methods: {
        logout(){
            localStorage.removeItem('jwt');
            // this.$store.dispatch('POST_LOGOUT');
            this.$router.go(0);       
        }
    }


}
</script>

<style scoped>
body{
    background-color: white;
}
header{
    border-bottom: 1px #ececec solid;
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
.item{
    margin: 0px 5px;
    color: black;
    text-decoration: none;
}
.item button{
    margin: 0px 15px;
    width: 106px;
    height: 32px;
    background-color: #7aab85;
    border: 1px solid #7aab85;
    color: white;
    box-sizing: border-box;
    border-radius: 10px;
}
.header .router-link-exact-active button{
    border: 1px solid #afafaf;
    background-color: white;
    color: #5d5d5d;
}
#u_name{
   font-weight: bold;
   cursor: pointer;
}
#logout{
   cursor: pointer;
}

</style>