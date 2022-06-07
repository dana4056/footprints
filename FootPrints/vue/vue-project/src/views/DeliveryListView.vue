
<template>
  <div>
    <tool-bar></tool-bar>
    <div id="content">
      배달 리스트 뷰 ~.~
      <router-link to="/delivery/post/create" class="link">글 작성</router-link> 
      <br>
      <router-link to="/viewDetails" class="link">상세보기 페이지 예시</router-link> 

      <div id="sort-box">
        <button>음식 카테고리</button>
        <button>정렬: 기한 가까운 순</button>
        <div class="add-btn">
          <router-link to="/delivery/post/create" class="link">
            <i class="fa-solid fa-circle-plus"></i>
          </router-link>
        </div>
      </div>

      <!------------------------------------ 게시물 리스트 요소 ---------------------------------------->
      <div v-for="delivery in this.$store.state.deliveries" class="listbox" v-bind:key="delivery">
        <img class="listbox-img" src="../assets/blank.png" alt="">
        <div class="listbox-content">
          <div class="listbox-head">
            <div class="res-name"><router-link v-bind:to="`/delivery/${delivery.post_id}`">{{ delivery.post_name }}</router-link></div>
            <div class="category" v-bind:class="delivery.category">{{this.categories[delivery.category]}}</div>
            <div class="time"><small>30:12</small></div>
          </div>
          <p>{{ delivery.post_content }}</p>
          <div class="listbox-foot">
            <div class="detail-info">
              <small>{{delivery.area_name}}</small>
              <button class="area-btn"><img src="../assets/placeholder.png">{{ delivery.take_loc }}</button>
              <img src="../assets/people.png" alt="">
              <small class="cnt">{{ delivery.participant_num }}/{{ delivery.person_num }}</small>
            </div>
            <div class="ago">
              <small>{{}}분 전 게시</small>
            </div>
          </div>
        </div>
      </div>
      <!------------------------------------------------------------------------------------------------->
      
    </div>
    <footer-area id="footer"></footer-area>
  </div>
</template>

<script>
import ToolBar from '../components/ToolBar.vue'
import FooterArea from '../components/FooterArea.vue'

export default {
  components:{
    ToolBar,
    FooterArea,
  },
  data(){
    return {
      categories:{
        'KOR': '한식',
        'CHI': '중식',
      }
    }
  },
  created(){
    this.$store.dispatch('FETCH_DELIVERY_LIST');
  },
}
</script>

<style scoped>

#wrap{
    height : 100%;
    position: relative;
}
#content{
    margin: 0 auto;
    width: 700px;
    padding: 100px 0;
      
}
#footer{
    height: 300px;
    position: absolute;  
    width: 100%;
    left: 0;
}
#sort-box{
  display: -webkit-box;
  margin: 10px 12px 10px 23px;
}

#sort-box button{
  font-size: 10px;
  margin: 0px 5px;
  padding: 5px 8px;
  background-color: #767676;
  border: 1px solid #767676;
  color: white;
  box-sizing: border-box;
  border-radius: 20px;
}
.add-btn{
  -webkit-box-flex:1;
  text-align: right;
}
.fa-solid{
  font-size: 25px;
  color: #767676;
}
.listbox, .listbox-head, .listbox-foot{
  display: -webkit-box;
}

.listbox{
  padding: 20px 0;
  border-bottom: 1px solid #eeeeef;
}
.listbox-img{
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

/* ----- 카테고리 태그 색상 지정 -------*/
.KOR{
  background-color: #6157cf;
}
.CHI{
  background-color: #ff6e6c;
}
/* ---------------------------------- */

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
  width: 106px;
  height: 30px;
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



</style>