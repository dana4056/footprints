<template>
  <div>
    <div class="container">
      <div class="header">
        <p class="category">배달</p>
        <p class="seeAll" id="delivery" @click="setting">전체보기</p>
      </div>
      <div class="list">
        <div class="image"></div>
        <p class="title">제목1</p>
      </div>
      <div class="list">
        <div class="image"></div>
        <p class="title">asdasdsdasdasds</p>
      </div>
      <div class="list">
        <div class="image"></div>
        <p class="title">너무 길면 어떻게 되는거지</p>
      </div>
      <div class="list">
        <div class="image"></div>
        <p class="title">제목4</p>
      </div>
    </div>

    <div class="container">
      <div class="header">
        <p class="category">좋아요</p>
        <p class="seeAll" id="like" @click="setting">전체보기</p>
      </div>
      <div class="list">
        <div class="image"></div>
        <p class="title">제목1</p>
      </div>
      <div class="list">
        <div class="image"></div>
        <p class="title">asdasdsdasdasds</p>
      </div>
      <div class="list">
        <div class="image"></div>
        <p class="title">너무 길면 어떻게 되는거지</p>
      </div>
      <div class="list">
        <div class="image"></div>
        <p class="title">제목4</p>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  methods: {
    setting(evnet){
      this.$emit("setting", evnet.currentTarget.id);
    }
  }
}
</script>

<style scoped>
.container {
  margin-top: 50px;
  height: 270px;
  width: 1000px;
}
.header {
  width: 100%;
  padding: 0 0;
  height: 70px;
}
.header > * {
  float: left;
}
.category {
  width: 200px;
  text-align: left;
  margin: 0 10px;
  font-size: 30px;
}
.seeAll {
  margin-left: 620px;
  margin-top: 40px;
  font-size: 18px;
  font-weight: bold;
  cursor: pointer;
}
.list {
  width: 185px;
  height: 300px;
  margin-right: 60px;
  float: left;
}
.title {
  vertical-align: bottom;
  float: center;
}
.image {
  width: 180px;
  height: 180px;
  border: 3px solid rgba(0, 0, 0, 0.385);
  border-radius: 15px;
}
</style>