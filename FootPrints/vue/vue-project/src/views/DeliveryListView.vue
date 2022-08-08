
<template>
  <div display="flex">
    <tool-bar></tool-bar>
    <div id="content">
      <!-- <router-link v-bind:to="`/delivery/post/${delivery.post_id}`" class="link">상세보기 페이지 예시</router-link>  -->
      <div id="sort-box"> 
        <!-- 수정 필요 부분 -->
        <!-- <button>음식 카테고리</button>
        <button>정렬: 기한 가까운 순</button> -->
        <label>음식 카테고리</label>
          <div>
            <select class="sortThing" v-model="category" v-on:focus="NoneCategory" v-on:focusout="SelectCategory">
              <option value="" selected="selected" disabled hidden>----- 선택 -----</option>
              <!-- 여기서 값들 스트링으로 안 넣고 data.categories로 넣을 수 있을거같은데 -->
              <option value="KOR">한식</option>
              <option value="CHI">중식</option>
              <option value="ETC">기타</option> 
            </select> 
          </div>

        <label>정렬 기준</label>
          <div>
            <select class="sortThing" v-model="sort" v-on:focus="BeforeSort" v-on:focusout="SelectSortCriteria">
              <option value="" selected="selected" disabled hidden>----- 선택 -----</option>
              <option value="default">글 작성 시간 순</option>
              <option value="near">마감기한 가까운 순</option>
              <option value="far">마감기한 먼 순</option>
            </select>
          </div>

        <div>
          <button type="button" class="btn2" v-on:click="searchArea">지역 검색</button>
        </div>

        <div class="add-btn">
          <router-link to="/delivery/post/create" class="link">
            <i class="fa-solid fa-circle-plus"></i>
          </router-link>
        </div>
      </div>

      <!------------------------------------ 게시물 리스트 요소 ---------------------------------------->
      <div v-for="delivery in this.$store.state.deliveryPostList" class="listbox" v-bind:key="delivery">
        <img class="listbox-img" :src="require('../assets/' + delivery.category + '.png')" alt="">
        <div class="listbox-content">
          <div class="listbox-head">
            <div class="res-name"><router-link v-bind:to="`/delivery/post/${delivery.post_id}`">{{ delivery.post_name }}</router-link></div>
            <div class="category" v-bind:class="delivery.category">{{this.categories[delivery.category]}}</div>
            <div class="time"><small>마감기한 : {{  delivery.valid_time.format("M/D  HH:mm")}}</small></div>
          </div>
          <router-link v-bind:to="`/delivery/post/${delivery.post_id}`"><p>{{ delivery.post_content }}</p></router-link>
          <div class="listbox-foot">
            <div class="detail-info">
              <small>{{delivery.area_name}}</small>
              <button class="area-btn"><img src="../assets/placeholder.png">{{ delivery.take_loc }}</button>
              <img src="../assets/people.png" alt="">
              <small class="cnt">{{ delivery.participant_num }}/{{ delivery.max_person_num }}</small>
            </div>
            <div class="ago">
              <small>{{caltime(delivery.createdDate)}}전 게시</small>
            </div>
          </div>
        </div>
      </div>
      <!------------------------------------------------------------------------------------------------->  
    </div>
    <up-button id="up_button"></up-button>
    <!-- 절대적으로 위치를 잡아준거라 relative하게 버튼의 위치를 잡아주는 작업 필요 -->
    <footer-area id="footer"></footer-area>
  </div>
</template>

<script>
import ToolBar from '../components/ToolBar.vue'
import FooterArea from '../components/FooterArea.vue'
import UpButton from '../components/UpButton.vue'
import dayjs from 'dayjs'

export default {
  components:{
    ToolBar,
    FooterArea,
    UpButton,
  },
  data(){
    return {
      categories:{
        'KOR': '한식',
        'CHI': '중식',
        'JAP': '일식',
        'ETC': '기타'
      },
      category: "",
      sort_criteria: "",
      Area: "",
    } 
  },
  beforeCreate(){
    this.$store.dispatch('FETCH_DELIVERY_LIST');
  },
  created() {
    this.$store.dispatch('FIND_POST_ID', this.$store.state.member.nick);
  },
  methods:{
    caltime(created){
      const now = dayjs();
      if(created.isSame(now,"day")){
          const ago_H = now.diff(created,"h");
          const ago_M = now.diff(created,"m");
          if(ago_H == 0){
            return String(ago_M)+"분 ";
          }
          return String(ago_H)+"시간 "+String(ago_M)+"분 ";
         }
      else{
          const ago_D = now.diff(created,"d");
          return String(ago_D)+"일 ";
      }
    },
    NoneCategory(){
      this.category = "";
    },
    SelectCategory(){
      this.$store.dispatch('FETCH_DELIVERY_LIST_CATEGORY', this.category);
      setTimeout(() => { 
          console.log("여기서 무언가를 해야 제대로 찍힐라나 싶네");
          this.$store.getters.GET_DELIVERIES;
        }, 100);   
    },
    BeforeSort(){
      this.sort_criteria = "";
    },
    SelectSortCriteria(){
      if(this.sort_criteria == "near" | this.sort_criteria == 'far'){
        this.$store.dispatch('FETCH_DELIVERY_LIST_SORT_TIME', this.sort);
      }
      //혹시 다른 조건 걸릴까봐 이런 if문으로 분기 해놓음
      setTimeout(() => { 
        console.log("여기서 무언가를 해야 제대로 찍힐라나 싶네");
        this.$store.getters.GET_DELIVERIES;
      }, 100);   
    },
    searchArea() {
      new window.daum.Postcode({
        oncomplete: (data) => {
          console.log("API:카카오맵 주소받기 성공",data);
          const sido = data.sido;
          const sigoongu = data.sigungu;
          const eupmyeondong = data.bname;
          
          this.Area = sido+" "+sigoongu+" "+eupmyeondong;
          this.$store.dispatch('FETCH_DELIVERY_LIST_SORT_AREA', this.Area);

          setTimeout(() => { 
            console.log("여기서 무언가를 해야 제대로 찍힐라나 싶네");
            this.$store.getters.GET_DELIVERIES;
          }, 100);  
        }
      }).open();
    }
  }
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
#up_button{ 
    height: 700px;
    position: fixed;
    bottom: 0;
    right: 200px;
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
.ETC{
  background-color: #8c8c8c;
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

.btn1, .btn2 {
  padding: 8px 15px 9px;
}


</style>