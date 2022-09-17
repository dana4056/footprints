<template>
  <div>
    <tool-bar></tool-bar>
    <div id="wrap">
      <div id="wrap2">
        <div style="float:left; width:300px;">
          <div id="category">
            <p>음식 카테고리를 설정해주세요.</p>
            <select v-model="category">
              <option value="카테고리" selected="selected" disabled hidden>카테고리</option>
              <option value="KOR">한식</option>
              <option value="CHI">중식</option>
              <option value="JAP">일식</option>
              <option value="ETC">기타</option>
            </select>
          </div>
          
          <div id="postTTL">
            <p>게시물이 유효한 시간을 정해주세요.</p>
            <input v-model="valid_time" type="datetime-local" 
            v-bind:min=minDate v-on:focusout="setMinValue">
          </div>

          <div id="peopleNum">
            <p>모집할 인원을 정해주세요.</p>
            <select v-model.number="max_person_num">
              <option value="0" selected="selected">상관없음</option>
              <option value="2">2명</option>
              <option value="3">3명</option>
              <option value="4">4명</option>
              <option value="5">5명</option>
              <option value="6">6명</option>
              <option value="7">7명</option>
              <!-- <option value="etc">그 외</option> -->
            </select>
          </div>

          <div id="place">
            <label>음식을 나눌 장소를 지정해주세요.</label>
            <div class="kmap" ref="map"></div>
            <input v-model="take_loc" v-if="inputVisible" 
            placeholder="장소 별명: ex) 세븐일레븐 앞">
          </div>
        </div>

        <div style="float:right; width:350px;">
          <input v-model="post_name" id="post_name" placeholder="제목을 입력하세요.">
          <hr>
          <textarea v-model="post_content" id="post_content" type="text" placeholder="배달팁, 최소주문 금액을 적어주세요!"></textarea>
          <button type="submit" v-on:click.prevent="register">수정</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import ToolBar from '../components/ToolBar.vue'
import Swal from 'sweetalert2';
import dayjs from 'dayjs'

export default {
  components:{
        ToolBar,
  },
  data() {
    return {
      post_id: this.$store.state.deliveryPost.post_id,
      post_name: this.$store.state.deliveryPost.post_name,     // 글 제목
      post_content: this.$store.state.deliveryPost.post_content,  // 글 내용
      category: this.$store.state.deliveryPost.category,      // 음식 카테고리
      take_loc: this.$store.state.deliveryPost.take_loc,      // 음식 나눌 장소
      participant_num: this.$store.state.deliveryPost.participant_num,  // 현재 참가 인원
      max_person_num: this.$store.state.deliveryPost.max_person_num,   // 모집 인원
      valid_time: this.$store.state.deliveryPost.valid_time,       // 게시물 유효 시간
      view_num: this.$store.state.deliveryPost.view_num,         // 조회수
      user_name: this.$store.state.deliveryPost.member.nick,     // 작성자 이름
      area_name: this.$store.state.deliveryPost.member.area,     // 행정지역명

      minDate: "",
      latitude: this.$store.state.deliveryPost.lat,
      longtitude: this.$store.state.deliveryPost.lon,
      inputVisible: true,
    }
  },
  beforeCreate(){
    this.$store.dispatch('FETCH_USER');
    //URL로 접근할 수 있으니 다시 상세정보 가져오기
    let post_id = this.$route.params.post_id;
    this.$store.dispatch('FETCH_DELIVERY_DETAIL', post_id);
    setTimeout(() => {
      let post = this.$store.state.deliveryPost
      this.post_id = post.post_id;
      this.post_name = post.post_name;     // 글 제목
      this.post_content = post.post_content;  // 글 내용
      this.category = post.category;      // 음식 카테고리
      this.take_loc = post.take_loc;      // 음식 나눌 장소
      this.participant_num = post.participant_num;  // 현재 참가 인원
      this.max_person_num = post.max_person_num;   // 모집 인원
      this.valid_time = post.valid_time;       // 게시물 유효 시간
      this.view_num = post.view_num;         // 조회수
      this.user_name = post.member.nick;     // 작성자 이름
      this.area_name = post.member.area;     // 행정지역명
      this.latitude = post.lat;
      this.ongtitude = post.lon;

      console.log("fetch nick: ",this.$store.state.member.nick);
      console.log("this.user_name: ",post.member.nick);
      if(this.$store.state.member.nick !== post.member.nick){
        alert("게시물을 수정할 수 있는 권한이 없습니다.");
        this.$router.replace(`/delivery/post/${this.post_id}`);
      }
    }, 800);

  },
  mounted() {
    let $vm = this;
    // 날짜 입력 최소값 지정(현시간)
    $vm.minDate = dayjs().format("YYYY-MM-DDTHH:mm");

    // 지도 창 생성
    let kakao = window.kakao;
    let container = this.$refs.map;
    let options = {
      center: new kakao.maps.LatLng(this.latitude, this.longtitude),
      level: 3
    };
    let mapInstance = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴

    let geocoder = new kakao.maps.services.Geocoder();

    // 마커 생성
    let marker = new kakao.maps.Marker({
      position: mapInstance.getCenter(),
    }); 
    marker.setMap(mapInstance);

    // 줌인 줌아웃
    let zoomControl = new kakao.maps.ZoomControl();
    mapInstance.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);

    // 클릭 이벤트 등록
    kakao.maps.event.addListener(mapInstance, 'click', function(mouseEvent) {        
      let latlng = mouseEvent.latLng;

      marker.setPosition(latlng);

      let callback = function(result, status) {
        if (status === kakao.maps.services.Status.OK) {
            console.log(result[0].address_name);
        }
      }
      geocoder.coord2RegionCode(latlng.getLng(), latlng.getLat(), callback);
      $vm.inputVisible = true;
    });
  },
  methods: {
    register() {
      if (this.submitData()){
        // this.$store.dispatch('FETCH_USER') //의도가 뭐지
          const post = {
            post_id: this.post_id,
            post_name: this.post_name,           // 글 제목
            post_content: this.post_content,     // 글 내용
            category: this.category,             // 음식 카테고리
            take_loc: this.take_loc,             // 음식 나눌 장소
            participant_num: this.participant_num,                  // 현재 참가 인원
            max_person_num: this.max_person_num, // 모집 인원
            valid_time: this.valid_time,         // 게시물 유효 시간
            view_num: this.view_num ,            // 조회수
            nick: this.user_name,
            area :this.area_name
        }
        console.log("AMEND_DELIVERY_POST\n",post); 
        this.$store.dispatch('AMEND_DELIVERY_POST', post);

				Swal.fire({
          icon: 'success',
          title: '글이 수정되었습니다.',
          confirmButtonText: '배달 모집 목록 보러가기',
        }).then(() => {
          this.$router.replace(`/delivery/post/${this.post_id}`);
        })
			}
      else {
        alert("입력이 올바른지 확인해주세요.")
      }
    },
		submitData() {
      if (this.post_name != "" && this.post_content != "" &&  this.category != "카테고리" && this.take_loc != "" && this.valid_time != "") {
        return true;
      }
      else {
        return false;
      }
    },
    setMinValue() {
      const valid = dayjs(this.valid_time);
      if(valid.isBefore(dayjs(),"minute")) {
          alert('현재 시간보다 이전의 날짜는 설정할 수 없습니다.');
          this.valid_time = dayjs();
      }
    }
  }
}
</script>

<style scoped>
#wrap {
  height: 800px;
  padding: 80px 0;
  background-color: #f2f2f2;
}
#wrap2 {
  width: 800px;
  height: 570px;
  margin: 0px auto;
  padding: 40px 50px 30px;
  background-color: white;
  border-radius: 30px;
  font-size: 15px;
  text-align: left;
}
p {
  margin-top: 0px;
  margin-left: 3px;
}
select, #postTTL > input, #place > input{
  box-sizing: border-box;
  width: 80%;
  height: 35px;
  margin-bottom: 15px;
  padding: 8px 15px 9px;
  background: #F8F8F8;
  border: 1px solid #BDBDBD;
  border-radius: 30px;
  outline: none;
}
#postTTL > input {
  padding-left: 20px;
}
#post_name{
  margin-top: 5px;
  padding-left: 5px;
  border: none;
  color: #555;
  font-size: 25px;
  font-weight: bold;
  outline: none;
}
hr{
  margin: 25px 0;
}
#post_content{
  width: 300px;
  height: 400px;
  padding: 5px;
  border: none;
  font-size: 15px;
  outline: none;
}
button {
  width: 80px;
  height: 35px;
  margin-top: 20px;
  background-color: #ddd;
  border: none;
  border-radius: 10px;
  float: right;
  cursor: pointer;
}
.kmap{
  width: 400px;
  height: 220px;
  margin-top: 20px;
}
#place > input {
  margin: 15px 60px 0;
  text-align: center;
}
</style>