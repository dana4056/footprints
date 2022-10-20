<template>
  <div>

    <div class="container"> 
      <div class="header">
        <p class="category">내 글</p>
        <p class="seeAll" id="Mine" @click="setting">전체보기</p>
      </div>
      <div v-for="(myDelivery, index) in this.$store.state.myDPostList" v-bind:key="myDelivery">
        <router-link class="list" v-if="index < 4" v-bind:to="`/delivery/post/${myDelivery.post_id}`">
          <img class="image" :src="require('../assets/category/' + myDelivery.category + '.png')">
          <p class="title">{{myDelivery.post_name}}</p>
        </router-link>
      </div>
      <div v-if="this.$store.state.myDPostList.length < 4">
        <div v-for="index in (4-this.$store.state.myDPostList.length)" v-bind:key="index">
          <div class="list">
            <div class="image empty"></div>
          </div>
        </div>
      </div>
    </div>

    <div class="container">
      <div class="header">
        <p class="category">참여</p>
        <p class="seeAll" id="Participation" @click="setting">전체보기</p>
      </div>
      <div v-for="(myPartici, index) in this.$store.state.myParticiList" v-bind:key="myPartici">
        <router-link class="list" v-if="index < 4" v-bind:to="`/delivery/post/${myPartici.post_id}`">
          <img class="image" :src="require('../assets/category/' + myPartici.category + '.png')">
          <p class="title">{{myPartici.post_name}}</p>
        </router-link>
      </div>
      <div v-if="this.$store.state.myParticiList.length < 4">
        <div v-for="index in (4-this.$store.state.myParticiList.length)" v-bind:key="index">
          <div class="list">
            <div class="image empty"></div>
          </div>
        </div>
      </div>
    </div>

  </div>
</template>

<script>
export default {
  data(){
    return {
      categories:{
        'KOR': '한식',
        'CHI': '중식',
        'JAP': '일식',
        'ETC': '기타'
      },
    } 
  },
  methods: {
    setting(evnet){
      this.$emit("setting", evnet.currentTarget.id);
    }
  }
}
</script>

<style scoped>
.container {
  width: 700px;
  height: 270px;
  margin-top: 50px;
}
.header {
  width: 100%;
  height: 60px;
  padding: 0 0;
}
.header > * {
  float: left;
}
.category {
  width: 200px;
  margin: 0 0;
  font-size: 27px;
  text-align: left;
}
.seeAll {
  margin-left: 368px;
  margin-top: 30px;
  font-size: 16px;
  font-weight: bold;
  cursor: pointer;
}
.list {
  width: 155px;
  height: 200px;
  margin-right: 10px;
  float: left;
}
.title {
  width: 140px;
  font-size: 15px;
  vertical-align: bottom;
}
.image {
  width: 135px;
  height: 135px;
  margin: 0 0;
  background-color: rgb(242, 242, 242);
  border-radius: 15px;
}
.empty {
  background-color: #fdfdfd;
  border: 1px dashed rgba(0,0,0,.247);
}
a {
  color: black;
  text-decoration: none;
}
</style>