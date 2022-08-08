<template>
  <div display="flex">
    <div id="content" v-if="this.$store.state.myParticiList.length != 0">
      <div v-for="myPartici in this.$store.state.myParticiList" class="listbox" v-bind:key="myPartici">
        <img class="listbox-img" :src="require('../assets/' + myPartici.category + '.png')">
        <div class="listbox-content">
          <div class="listbox-head">
            <div class="res-name"><router-link v-bind:to="`/delivery/post/${myPartici.post_id}`">{{ myPartici.post_name }}</router-link></div>
            <div class="category" v-bind:class="myPartici.category">{{this.categories[myPartici.category]}}</div>
            <!-- <div class="time"><small>마감기한 : {{  myPartici.valid_time.format("M/D  HH:mm")}}</small></div> -->
          </div>
          <router-link v-bind:to="`/delivery/post/${myPartici.post_id}`"><p>{{ myPartici.post_content }}</p></router-link>
          <div class="listbox-foot">
            <div class="detail-info">
              <small>{{myPartici.area_name}}</small>
              <button class="area-btn"><img src="../assets/placeholder.png">{{ myPartici.take_loc }}</button>
              <img src="../assets/people.png" alt="">
              <small class="cnt">{{ myPartici.participant_num }}/{{ myPartici.max_person_num }}</small>
            </div>
            <div class="ago">
              <!-- <small>{{caltime(myPartici.createdDate)}}전 게시</small> -->
            </div>
          </div>
        </div>
      </div>
    </div>
    <div v-else id="nothing">
      <p>아직 참여한 글이 없습니다!</p>
      <router-link to="/delivery/post">배달 게시물 둘러보기</router-link> 
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
}
}
</script>

<style scoped>
#content{
  width:600px;
  padding: 5px 0px;  
  padding-bottom: 200px;
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
  background-color: rgb(242, 242, 242);
  width: 100px;
  margin: 0 30px;
  border-radius: 10px;
}
.listbox-content{
  -webkit-box-flex:1;
  padding-right: 15px;
}
.listbox-content p{
  text-align: left;
  font-size: 13px;
  line-height: 20px;
  color: #666;
}
a {
  text-decoration: none;
}
.listbox-head{
  line-height: 20px;
}
.res-name a{
  font-weight: bold;
  color: #000;
  text-decoration: none;
}
.category{
  font-size: xx-small;
  margin: 0px 7px;
  padding: 0 7px;
  height: 19px;
  border-radius: 3px;
  color: #ffffff;
  vertical-align:middle;
}
.sortThing {
  width:45%;
}
label {
  text-align: left;
  display: block;
  font-size: 15px;
  font-weight: bold;
  color: #292929;
  margin: 0 0 12px;
}
.KOR{
  background-color: #6157cf;
}
.CHI{
  background-color: #ff6e6c;
}
.JAP{
  background-color: #ff0400;
}
.ETC{
  background-color: #8c8c8c;
}
.detail-info{
  line-height: 30px;
}
.detail-info img{
  vertical-align:middle;
  width: 20px;
}
button img{
  margin: 0;
}
.cnt{
  font-family: 'Roboto';
  line-height: 20px;
  padding-left: 5px;
}
.area-btn{
  margin: 0px 15px;
  height: 30px;
  padding: 0px 14px 0px 10px;
  box-sizing: border-box;
  border-radius: 27px;
  border: 1px solid #afafaf;
  background-color: white;
  color: #5d5d5d;
}
.time{
  -webkit-box-flex:1;
  text-align: right;
  line-height: 20px;
  color:#ff6b33;
}
.ago{
  -webkit-box-flex:1;
  text-align: right;
  line-height: 30px;
  color: #959595;
}
.time small, .ago small{
  vertical-align:middle;
  font-size: 11px;
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
#nothing {
  margin-top: 80px;
  width: 700px;
  height: 300px;
  background-color:#97c9a23f;
  border-radius: 50px;
  border: 3px dashed #cbcbcb;
  padding: 90px 0;
  box-sizing: border-box;
}
</style>