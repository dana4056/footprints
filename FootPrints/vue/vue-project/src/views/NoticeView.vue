<template>
  <div>
    <tool-bar></tool-bar>
    <!-- 애초에 add-btn은 권한이 있는 사람만 볼 수 있는건지 아니면 글 추가 하면 권한 없다고 하게 할지 -->
    <div class="add-btn">
        <router-link to="/notice/new-notice" class="link">
          <i class="fa-solid fa-circle-plus fa-3x"></i>
        </router-link>
    </div>
    <div v-for="notice in this.$store.state.noticeList" class="listbox" v-bind:key="notice">
        <div class="notice-content">
          <div class="notice-head">
            <div class="res-name"><router-link v-bind:to="`/notice/${notice.id}`">{{ notice.title }}</router-link></div>
          </div>
          <router-link v-bind:to="`/notice/${notice.id}`"><p>{{ notice.content }}</p></router-link>
          <div class="notice-foot">
            <div class="ago">
              <!-- 날짜만 뜨게 수정 -->
              <small>{{notice.post_time}}</small> 
              <small>{{notice.view_num}} 조회</small>
            </div>
          </div>
        </div>
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
  created(){
    this.$store.dispatch('FETCH_NOTICE_LIST')
  },
}
</script>

<style scoped>
.notice-content{
  -webkit-box-flex:1;
  padding-right: 15px;
}
.notice-content p{
  text-align: left;
  font-size: 13px;
  line-height: 20px;
  color: #666;
}
.notice-head{
  line-height: 20px;
}
.notice-content, .notice-head, .notice-foot{
  display: -webkit-box;
}
.add-btn{
  -webkit-box-flex:1;
  text-align: right;
}
</style>