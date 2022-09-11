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
    /* background-color: #f7f7f7; */
    width: 100%;
    max-width: 1024px;
    margin: 0 auto;

    padding: 15px 45px;
    display: flex;
    justify-content: space-between;
    align-items: center;
}
.menutext{
    text-decoration: none;
    color: black;
    margin: 0 15px;
}
.chatlogo {
    display: inline-block;
    vertical-align: middle;
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
    color: black;
    text-decoration: none;
    font-weight: bold;
    cursor: pointer;
}
#logout{
   cursor: pointer;
}

</style>