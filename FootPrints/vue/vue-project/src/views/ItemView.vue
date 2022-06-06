
<template>
  <div>
    <tool-bar></tool-bar>
    배달 리스트 뷰 ~.~
    <router-link to="/writing" class="link">글 작성</router-link> 
    <hr>
    <router-link to="/viewDetails" class="link">상세보기 페이지 예시</router-link> 
  </div>
</template>

<script>
import ToolBar from '../components/ToolBar.vue'

export default {
  components:{
    ToolBar,
  }
}
</script>

<style scoped>
hr{
  width: 50%;
}
</style>