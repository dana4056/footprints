<template>
  <div>
    <tool-bar></tool-bar>
    <div class="wrap">
      <p :id="[clickAll ? 'isClicked' : ' ']"  v-on:click.prevent="goAll">전체</p>
      <p :id="[clickMine ? 'isClicked' : ' ']"  v-on:click.prevent="goMine">내 글</p>
      <p :id="[clickParticipation ? 'isClicked' : ' ']"      v-on:click.prevent="goParticipation">참여</p>
      <p :id="[clickSetting ? 'isClicked' : ' ']"   v-on:click.prevent="goSetting">설정</p>
    </div>
    <div class="wrap2">
      <div v-if="clickSetting === false" class="profile">
        <div id="profileBox">
          <img id="profileImage" :src="require('../assets/' + this.$store.state.member.nick + '.png')">
          <p id="profileNick">{{this.$store.state.member.nick}}</p>
          <div id="profileArea">
            <img id="areaImage" src="../assets/placeholder.png">
            <p id="areaName">{{this.$store.state.member.area}}</p>
          </div>
          <div class="router">
            <router-link to="/home" id="like">
              <img id="likeImage" src="../assets/heart.png">
              <p>좋아요</p>
            </router-link>
            <router-link v-bind:to="`/chat/${this.$store.state.member.nick}`" id="chat">
              <img id="chatImage" src="../assets/chatting.jpg">
              <p>채팅</p>
            </router-link>
          </div>
        </div>
      </div>
      <div class="component">
        <my-all @setting="setComponent" v-if="clickAll"></my-all>
        <my-post v-if="clickMine"></my-post>
        <my-participation v-if="clickParticipation"></my-participation>
      </div>
      <div class="component2">
        <my-setting v-if="clickSetting"></my-setting>
      </div>
    </div>
  </div>
</template>

<script>
import ToolBar from '../components/ToolBar.vue'
import MyAll from '../components/MyAll.vue'
import MyPost from '../components/MyPost.vue'
import MyParticipation from '../components/MyParticipation.vue'
import MySetting from '../components/MySetting.vue'
import { router } from '../routes/index.js';

export default {
  components:{
    ToolBar,
    MyAll,
    MyPost,
    MyParticipation,
    MySetting
  },
 created(){
    if(localStorage.getItem('jwt') == null){
      alert("놉!");
      router.replace("/home");
    }
    else {
      this.$store.dispatch('FETCH_MY_DPOST', this.$store.state.member.nick);
      this.$store.dispatch('FETCH_MY_PARTICI', this.$store.state.member.nick);
    }
  },
  data() {
    return {
      clickAll: true,
      clickMine: false,
      clickParticipation: false,
      clickSetting: false,
    }
  },
  methods: {
    setComponent(data) {
      if(data == "Mine") {
        this.goMine();
      }
      else if(data == "Participation") {
        this.goParticipation();
      }
    },
    goAll() {
      console.log("전체 뷰 클릭");
      this.clickAll = true;
      this.clickMine = false;
      this.clickParticipation = false;
      this.clickSetting = false;
    },
    goMine() {
      this.clickAll = false;
      this.clickMine = true;
      this.clickParticipation = false;
      this.clickSetting = false;
    },
    goParticipation() {
      this.clickAll = false;
      this.clickMine = false;
      this.clickParticipation = true;
      this.clickSetting = false;
    },
    goSetting() {
      this.clickAll = false;
      this.clickMine = false;
      this.clickParticipation = false;
      this.clickSetting = true;
    }
  }
}
</script>

<style scoped>
.wrap {
  height: 50px;
  background-color: rgb(243, 243, 243);
  padding-left: 750px;
  padding-top: 200px;
}
.wrap > p {
  width: 120px;
  margin: 0 60px 0 0;
  float: left;
  font-size: 18px;
  cursor: pointer;
}
#isClicked {
  padding-bottom: 7px;
  border-bottom:#7aab85 solid 15px;
}
.wrap2 {
  width: 100%;
  background-color: rgb(255, 255, 255);
  height: 800px;
}
.wrap2 > div{
  float: left;
}
.profile {
  width: 750px;
  height: 100%; 
  /* background-color: aqua; */
}
#profileBox {
  margin: 50px 100px 0px 350px;
  width: 270px;
  height: 380px;
  background-color: rgb(255, 255, 255);
  border-radius: 40px;
  box-shadow: 1px 1px 2px 6px #98d0a443;
}
#profileImage {
  margin-top: 30px;
  width: 100px;
  height: 100px;
  border-radius: 50%;
}
#profileNick {
  font-size: 21px;
  font-weight: bold;
  margin: 10px 0 20px 0
}
#profileArea {
  text-align: center;
  display: inline-block;
}
#profileArea > * {
  float: left;
}
#areaImage {
  width: 30px;
  opacity: 0.7;
}
#areaName {
  font-size: 16px;
  margin: 2px 0 0 0;
  color: rgb(166, 166, 166);
}
.router{
  padding: 0 40px 0 50px;
  margin-top: 30px;
  width: 160px;
  height: 120px;
  /* background-color: aqua; */
  text-align: center;
  display: inline-block;
}
.router > * {
  float: left;
  color: black;
  text-decoration: none;
  font-weight: bold;
  font-size: 17px;
}
#like {
  margin: 0 0;
}
#likeImage{
  margin-top: 5px;
  width: 35px;
  height: 34px;
}
#chat {
  margin: 0 0 0 58px;
}
#chatImage {
  width: 40px;
  height: 40px;
}
#chat > p {
  margin: 13px 0px 0 0px;
}
.component {
  height: 100%;
}
.component2 {
  width: 100%;
}
</style>