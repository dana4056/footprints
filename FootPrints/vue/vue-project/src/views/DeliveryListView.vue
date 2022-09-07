<template>
  <div id="wrap">
    <tool-bar></tool-bar>
    <div id="content">
      <div id="area">현재 설정된 지역은 {{this.$store.state.deliveryPost_presentArea}} 입니다.</div>
      <div id="sort-box">
        <label>음식 카테고리</label>
          <div>
            <select class="sortThing" v-model="category" v-on:focus="NoneCategory" v-on:focusout="SelectCategory">
              <option value="" selected="selected" disabled hidden>----- 선택 -----</option>
              <!-- 여기서 값들 스트링으로 안 넣고 data.categories로 넣을 수 있을거같은데 -->
              <option value="KOR">한식</option>
              <option value="CHI">중식</option>
              <option value="JAP">일식</option>
              <option value="PIZ">피자</option>
              <option value="FRI">치킨</option>
              <option value="BUN">분식</option>
              <option value="FAS">패스트푸드</option>
              <option value="JOK">족발/보쌈</option>
              <option value="DES">디저트</option>
              <option value="ETC">기타</option>
            </select>  
          </div>

        <label>정렬 기준</label>
          <div>
            <select class="sortThing" v-model="sort_criteria" v-on:focus="BeforeSort" v-on:focusout="SelectSortCriteria">
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
          <router-link to="/delivery/post/new-post" class="link">
            <i class="fa-solid fa-circle-plus"></i>
          </router-link>
        </div>
      </div>

      <div id="box" v-bind:style="{height: (this.$store.state.deliveryPostList.length * 170 + 400) + 'px'}">
      <!------------------------------------ 유효한 게시물 리스트 요소 ---------------------------------------->
      <div v-for="delivery in this.$store.state.deliveryPostList" v-bind:key="delivery">
        <div v-if="delivery.valid_time.diff(this.now) >= 0" class="valid listbox">
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
      </div>
      <!------------------------------------------------------------------------------------------------->
      <!------------------------------------ 무효한 게시물 리스트 요소 ----------------------------------------->
      <div v-for="delivery in this.$store.state.deliveryPostList" v-bind:key="delivery">
        <div v-if="delivery.valid_time.diff(this.now) < 0" class="invalid listbox">
          <img class="listbox-img" :src="require('../assets/' + delivery.category + '.png')" alt="">
          <div class="listbox-content">
            <div class="listbox-head">
              <div class="res-name"><router-link v-bind:to="`/delivery/post/${delivery.post_id}`">{{ delivery.post_name }}</router-link></div>
              <div class="category" v-bind:class="delivery.category">{{this.categories[delivery.category]}}</div>
              <div class="time" id="invalid"><small>마감기한이 지난 글입니다.</small></div>
            </div>
            <p>{{ delivery.post_content }}</p>
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
      </div>
      <!------------------------------------------------------------------------------------------------->
    </div>
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
        'PIZ': '피자',
        'FRI': '치킨',
        'BUN': '분식',
        'FAS': '패스트푸드',
        'JOK': '족발/보쌈',
        'DES': '디저트',
        'ETC': '기타',
      },
      category: "",
      sort_criteria: "",
      area: this.$store.state.deliveryPost_presentArea,
      now: "",
    }
  },
  beforeCreate(){
    this.$store.dispatch('FETCH_DELIVERY_LIST', this.$store.state.deliveryPost_presentArea);
  },
  created() {
    this.now = dayjs();
  },
  methods:{
    caltime(created){
      const now = dayjs();
      if(created.isSame(now,"day")){
        const ago_H = now.diff(created,"h");
        const ago_M = now.diff(created,"m");
        if (ago_H == 0) {
          return String(ago_M)+"분 ";
        }
        return String(ago_H)+"시간 "+String(ago_M)+"분 ";
      }
      else {
        const ago_D = now.diff(created,"d");
        return String(ago_D)+"일 ";
      }
    },
    NoneCategory(){
      this.category = "";
    },
    SelectCategory(){
      const sortDTO = {
        category : this.category,
        sort_criteria: this.sort_criteria,
        area : this.area,
      }
      this.$store.dispatch('FETCH_DELIVERY_LIST_SORT', sortDTO);
      setTimeout(() => { 
          this.$store.getters.GET_DELIVERIES;
        }, 200);   
    },
    BeforeSort(){
      this.sort_criteria = "";
    },
    SelectSortCriteria(){
      const sortDTO = {
        category : this.category,
        sort_criteria: this.sort_criteria,
        area : this.area,
      }
      this.$store.dispatch('FETCH_DELIVERY_LIST_SORT', sortDTO);
      console.log(sortDTO.category, sortDTO.sort_criteria, sortDTO.area);
      setTimeout(() => { 
          this.$store.getters.GET_DELIVERIES;
        }, 200);   
    },
    searchArea() {
      new window.daum.Postcode({
        oncomplete: (data) => {
          console.log("API:카카오맵 주소받기 성공",data);
          const sido = data.sido;
          const sigoongu = data.sigungu;
          const eupmyeondong = data.bname;
          
          this.area = sido+" "+sigoongu+" "+eupmyeondong;
          this.$store.state.deliveryPost_presentArea = this.area;
          this.$store.dispatch('FETCH_DELIVERY_LIST', this.$store.state.deliveryPost_presentArea);
          
          setTimeout(() => { 
            this.NoneCategory();
            this.BeforeSort();
            this.$store.getters.GET_DELIVERIES;
          }, 100);  
        }
      }).open();
    }
  }
}
</script>

<style scoped>
#content{
  width: 700px;
  margin: 0 auto;
  padding: 50px 0 0 0;
}
#area{
  margin-bottom: 50px;
}
#footer{
  width: 100%;
  height: 300px;
}
#up_button{
  position: fixed;
  bottom: 70px;
  right: 200px;
  z-index: 1000;
}
#sort-box{
  margin: 10px 12px 10px 23px;
  display: -webkit-box;
}
#sort-box button{
  box-sizing: border-box;
  margin: 0px 5px;
  padding: 5px 8px;
  background-color: #767676;
  border: 1px solid #767676;
  border-radius: 20px;
  color: white;
  font-size: 10px;
}
.add-btn{
  -webkit-box-flex:1;
  text-align: right;
}
.fa-solid{
  color: #767676;
  font-size: 25px;
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
.btn1, .btn2 {
  padding: 8px 15px 9px;
}
.invalid {
  background-color: rgb(192, 192, 192);
  pointer-events: none;
  opacity: 0.7;
}
#invalid {
  color: red;
  font-weight: bold;
  font-size: 14px;
}
</style>