<template>
  <div>
    공지사항 내용 상위 몇 건만 뜨도록
    <div v-for="notice in this.$store.state.noticeList.slice(0,3)" class="listbox" v-bind:key="notice">
        <div class="notice-content">
          <div class="notice-head">
            <div class="res-name"><router-link v-bind:to="`/notice/${notice.id}`">{{ notice.title }}</router-link></div>
          </div>
          <router-link v-bind:to="`/notice/${notice.id}`"><p>{{ notice.content }}</p></router-link>
          <div class="notice-foot">
            <div class="ago">
              <!-- 날짜만 뜨게 수정 -->
              <small>{{notice.post_time}}</small>
              <br> 
              <small>{{notice.view_num}} 조회</small>
            </div>
          </div>
        </div>
      </div>
  </div>
</template>

<script>
export default {
  created(){
    this.$store.dispatch('FETCH_NOTICE_LIST')
  }
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
</style>