<template>
  <div id="wrap" :class="{fixedWrapper:isShowmap, scrollWrapper:!isShowmap}">
    <tool-bar></tool-bar>
    <show-map ref="showMap"></show-map>
    <div id="content">
      <div id="area">현재 설정된 지역은 <b>{{this.$store.state.persistedStore.deliveryPost_presentArea}}</b> 입니다.</div>
      <div id="sort-box">
        <label>음식 카테고리</label>
          <div class="selectBox">
            <select class="sortThing" v-model="category" v-on:focus="NoneCategory" v-on:change="SelectCategory">
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
          <div class="selectBox">
            <select class="sortThing" v-model="sort_criteria" v-on:focus="BeforeSort" v-on:change="SelectSortCriteria">
              <option value="" selected="selected" disabled hidden>----- 선택 -----</option>
              <option value="default">글 작성 시간 순</option>
              <option value="near">마감기한 가까운 순</option>
              <option value="far">마감기한 먼 순</option>
            </select>
          </div>

        <div id="search-area" >
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
          <img class="listbox-img" :src="require('../assets/category/' + delivery.category + '.png')" alt="">
          <div class="listbox-content">
            <div class="listbox-head">
              <div class="res-name"><router-link v-bind:to="`/delivery/post/${delivery.post_id}`">{{ delivery.post_name }}</router-link></div>
              <div class="category" v-bind:class="delivery.category">{{this.categories[delivery.category]}}</div>
              <!-- <div class="time"><small>마감기한 : {{  delivery.valid_time.format("M/D  HH:mm")}}</small></div> -->
              <div class="time"><small>{{ caltime(delivery.valid_time)}}남음</small></div>
            </div>
            <router-link v-bind:to="`/delivery/post/${delivery.post_id}`"><p>{{ delivery.post_content }}</p></router-link>
            <div class="listbox-foot">
              <div class="detail-info">
                <small>{{delivery.post_area}}</small>
                <button class="area-btn" v-on:click="change(delivery.post_id)"><img src="../assets/placeholder.png">{{ delivery.take_loc }}</button>
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
          <img class="listbox-img" :src="require('../assets/category/' + delivery.category + '.png')" alt="">
          <div class="listbox-content">
            <div class="listbox-head">
              <div class="res-name"><router-link v-bind:to="`/delivery/post/${delivery.post_id}`">{{ delivery.post_name }}</router-link></div>
              <div class="category" v-bind:class="delivery.category">{{this.categories[delivery.category]}}</div>
              <div class="time" id="invalid"><small>마감기한이 지난 글입니다.</small></div>
            </div>
            <p>{{ delivery.post_content }}</p>
            <div class="listbox-foot">
              <div class="detail-info">
                <small>{{delivery.post_area}}</small>
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
import ShowMap from '../components/ShowMap.vue'
import dayjs from 'dayjs'

export default {
  components:{
    ToolBar,
    FooterArea,
    UpButton,
    ShowMap,
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
      area: this.$store.state.persistedStore.deliveryPost_presentArea,
      now: "",
      isShowmap : false,
    }
  },
  beforeCreate(){
    this.$store.dispatch('FETCH_DELIVERY_LIST', this.$store.state.persistedStore.deliveryPost_presentArea);
  },
  created() {
    this.now = dayjs();
  },
  methods:{
    change(post_id){
      this.$store.commit('SET_ISLOADING', true); // 로딩 시작
      this.isShowmap =  this.$refs.showMap.openMap;

      this.$store.dispatch('FETCH_DELIVERY_DETAIL', post_id);
    },
    caltime(time){
      // 콘솔창에 시간 객체 찍을 때 표시되는 속성명과 dayjs객체 속성명 다름
      // ex) 시간(hour) -> 콘솔에는 H로 dayjs에는 h로 표시  dayjs로 다뤄야함
      const now = dayjs();

      const beforeTime = (now < time) ? now : time;
      const afterTime = (now < time) ? time : now;

      const diffH = afterTime.diff(beforeTime,"hour", true);

      if(diffH<24){
        const ago = afterTime.diff(beforeTime, "minute");
        const ago_H = parseInt(ago / 60);
        const ago_M = ago - (ago_H * 60);
        if(ago_H === 0){
          return String(ago_M)+"분 ";
        }else{
          return String(ago_H)+"시간 "+String(ago_M)+"분 ";
        }
      }else{
        const ago_D = afterTime.diff(beforeTime, "day");
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
        }, 300);
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
      setTimeout(() => {
          this.$store.getters.GET_DELIVERIES;
        }, 300);
    },
    searchArea() {
      new window.daum.Postcode({
        oncomplete: (data) => {
          const sido = data.sido;
          const sigoongu = data.sigungu;
          const eupmyeondong = data.bname;

          this.area = sido+" "+sigoongu+" "+eupmyeondong;
          this.$store.state.persistedStore.deliveryPost_presentArea = this.area;
          this.$store.dispatch('FETCH_DELIVERY_LIST', this.$store.state.persistedStore.deliveryPost_presentArea);

          setTimeout(() => {
            this.NoneCategory();
            this.BeforeSort();
            this.$store.getters.GET_DELIVERIES;
          }, 300);
        }
      }).open();
    }
  }
}
</script>

<style scoped>
.fixedWrapper{
  position: fixed;
  width: 100%;
  height: 100%;
  overflow: hidden;
}
.scrollWrapper{
    min-height: 100%;
    position: relative;
}

#content{
  width: 800px;
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
  padding: 5px 3px;
  background-color: #767676;
  border: 1px solid #767676;
  border-radius: 20px;
  color: #fff;
  height: 30px;
  width: 60px;
  font-size: 10px;
}
#search-area{
  -webkit-box-flex:1;
}
#search-area button{
  float: right;
  margin: 0 24px 0 0;
  width: 30%;
}
.add-btn{
  margin: auto 0;
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
.selectBox{
  margin: auto 15px auto 5px;
}
.sortThing {
  border-radius: 20px;
  padding-left: 20px;
  color: black;
  width: 135px;
  height: 30px;
}
label {
  margin: auto 0;
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
  width: 80%;
  text-align: left;
  line-height: 30px;
}
.detail-info img{
  width: 20px;
  vertical-align:middle;
}
.area-btn img{
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
  margin: 0px 8px;
  background-color: #fff;
  border: 1px solid #afafaf;
  border-radius: 27px;
  color: #5d5d5d;
  cursor: pointer;
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
.btn1, .btn2 {
  box-sizing: border-box;
  width: 100%;
  height: 40px;
  background: #ffffff;
  border: 1px solid #7aab85;
  border-radius: 13px;
  color: #7aab85;
  font-family: 'Noto Sans KR', sans-serif;
  padding: 8px 15px 9px;
}
.invalid {
  background-color: #f7f7f7;
  pointer-events: none;
  opacity: 0.2;
}
#invalid {
  color: red;
  font-weight: bold;
  font-size: 14px;
}
</style>