<template>
  <div display="flex">
    <div id="content" v-if="this.$store.state.myDPostList.length != 0">
      <div v-for="myDelivery in this.$store.state.myDPostList" class="listbox" v-bind:key="myDelivery">
        <img class="listbox-img" :src="require('../assets/' + myDelivery.category + '.png')">
        <div class="listbox-content">
          <div class="listbox-head">
            <div class="res-name"><router-link v-bind:to="`/delivery/post/${myDelivery.post_id}`">{{ myDelivery.post_name }}</router-link></div>
            <div class="category" v-bind:class="myDelivery.category">{{this.categories[myDelivery.category]}}</div>
            <!-- <div class="time"><small>마감기한 : {{  myDelivery.valid_time.format("M/D  HH:mm")}}</small></div> -->
          </div>
          <router-link v-bind:to="`/delivery/post/${myDelivery.post_id}`"><p>{{ myDelivery.post_content }}</p></router-link>
          <div class="listbox-foot">
            <div class="detail-info">
              <small>{{myDelivery.area_name}}</small>
              <button class="area-btn"><img src="../assets/placeholder.png">{{ myDelivery.take_loc }}</button>
              <img src="../assets/people.png" alt="">
              <small class="cnt">{{ myDelivery.participant_num }}/{{ myDelivery.max_person_num }}</small>
            </div>
            <div class="ago">
              <!-- <small>{{caltime(myDelivery.createdDate)}}전 게시</small> -->
            </div>
          </div>
        </div>
      </div>
    </div>
    <div v-else id="nothing">
      <p>아직 참여한 글이 없습니다!</p>
      <router-link to="/delivery/post/new-post">배달 게시물 작성하기</router-link> 
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
      'PIZ': '피자',
      'FRI': '치킨',
      'BUN': '분식',
      'FAS': '패스트푸드',
      'JOK': '족발/보쌈',
      'DES': '디저트',
      'ETC': '기타',
    },
  } 
}
}
</script>

<style scoped>
#content{
  width:600px;
  padding: 5px 0px 200px 0px;  
}
.add-btn{
  -webkit-box-flex:1;
  text-align: right;
}
.listbox, .listbox-head, .listbox-foot{
  display: -webkit-box;
}
.listbox{
  padding: 60px 0 25px 0;
  border-bottom: 1px solid #eeeeef;
}
.listbox-img{
  width: 100px;
  margin: 0 30px;
  background-color: rgb(242, 242, 242);
  border-radius: 10px;
}
.listbox-content{
  -webkit-box-flex:1;
  padding-right: 15px;
}
.listbox-content p{
  color: #666;
  font-size: 13px;
  text-align: left;
  line-height: 20px;
}
a {
  text-decoration: none;
}
.listbox-head{
  line-height: 20px;
}
.res-name a{
  color: #000;
  font-weight: bold;
  text-decoration: none;
}
.category{
  height: 19px;
  margin: 0px 7px;
  padding: 0 7px;
  border-radius: 3px;
  color: #ffffff;
  font-size: xx-small;
  vertical-align:middle;
}
.sortThing {
  width:45%;
}
label {
  margin: 0 0 12px;
  color: #292929;
  font-size: 15px;
  font-weight: bold;
  text-align: left;
  display: block;
}

/* ----- 카테고리 태그 색상 지정 -------*/
.KOR{
  background-color: #6157cf;
}
.CHI{
  background-color: #ff6e6c;
}
.JAP{
  background-color: #ff0400;
}
.PIZ{
  background-color: #7cc66bbd;
}
.FRI{
  background-color: #ffde5b;
}
.BUN{
  background-color: #e55e5e;
}
.FAS{
  background-color: #ffaa00;
}
.JOK{
  background-color: #c28156;
}
.DES{
  background-color: #ff67949f;
}
.ETC{
  background-color: #8c8c8c;
}
/* ---------------------------------- */

.detail-info{
  line-height: 30px;
}
.detail-info img{
  width: 20px;
  vertical-align:middle;
}
button img{
  margin: 0;
}
.cnt{
  padding-left: 5px;
  font-family: 'Roboto';
  line-height: 20px;
}
.area-btn{
  box-sizing: border-box;
  height: 30px;
  margin: 0px 15px;
  padding: 0px 14px 0px 10px;
  background-color: white;
  border: 1px solid #afafaf;
  border-radius: 27px;
  color: #5d5d5d;
}
.time{
  -webkit-box-flex:1;
  color:#ff6b33;
  text-align: right;
  line-height: 20px;
}
.ago{
  -webkit-box-flex:1;
  color: #959595;
  text-align: right;
  line-height: 30px;
}
.time small, .ago small{
  font-size: 11px;
  vertical-align:middle;
}
button {
  box-sizing: border-box;
  width: 100%;
  height: 40px;
  background: #ffffff;
  border: 1px solid #7aab85;
  border-radius: 13px;
  color: #7aab85;
  font-family: 'Noto Sans KR', sans-serif;
}
#nothing {
  box-sizing: border-box;
  width: 700px;
  height: 300px;
  margin-top: 80px;
  padding: 90px 0;
  background-color:#97c9a23f;
  border: 3px dashed #cbcbcb;
  border-radius: 50px;
}
</style>