<template>
<header>
  <div class="header">
    <router-link to="/home" class="logo"><img src="../assets/logo.png">발자취</router-link>
    <div v-if="this.$store.state.member.nick ==''">
        <router-link to="/notice/post" class="menutext">공지사항</router-link>
        <router-link to="/delivery/post" class="item"><button>배달 같이하기</button></router-link>
        <router-link to="/login" class="item">로그인</router-link> |
        <router-link to="/signup" class="item">회원가입</router-link>
    </div>
    <div v-else>
        <router-link to="/notice/post" class="menutext">공지사항</router-link>
        <router-link v-bind:to="`/chat/${this.$store.state.member.nick}`" class="chatlogo"><img src="../assets/chat.png"></router-link>
        <router-link to="/delivery/post" class="item"><button>배달 같이하기</button></router-link>
        <router-link  v-bind:to="`/users/${this.$store.state.member.nick}`" id="u_name">{{this.$store.state.member.nick}}</router-link>
        님 |
        <span id="logout" v-on:click="logout">로그아웃</span>
    </div>
  </div>
</header>
</template>

<script>
export default {
    methods: {
        logout(){
            localStorage.removeItem('jwt');
            localStorage.removeItem('vuex');
            this.$router.replace("/home");
            this.$store.state.member = {nick: "",  email: "", pw: "", area: ""}
            this.$store.state.authority = "";
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
    width: 100%;
    max-width: 1024px;
    margin: 0 auto;
    padding: 15px 45px;
    display: flex;
    align-items: center;
    justify-content: space-between;
}
.menutext{
    margin: 0 15px;
    color: black;
    text-decoration: none;
}
.chatlogo {
    display: inline-block;
    vertical-align: middle;
}
.logo {
    margin: 0px 10px;
    color: black;
    text-decoration: none;
    font-weight: bold;
    display: flex;
    align-items: center;
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
    box-sizing: border-box;
    width: 106px;
    height: 32px;
    margin: 0px 15px;
    background-color: #7aab85;
    border: 1px solid #7aab85;
    border-radius: 10px;
    color: white;
}
.header .router-link-exact-active button{
    background-color: white;
    border: 1px solid #afafaf;
    color: #5d5d5d;
}
#u_name{
    color: black;
    font-weight: bold;
    text-decoration: none;
    cursor: pointer;
}
#logout{
   cursor: pointer;
}
</style>