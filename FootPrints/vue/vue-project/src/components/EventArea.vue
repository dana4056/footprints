<template>
  <div>
    이벤트 내용
    <!-- 가져온거 뿌리는 작업 해야됨 -->
  </div>
</template>

<script>
export default {
  created(){
    this.$store.dispatch('FETCH_EVENT_LIST')
  }
}
</script>

<style>

</style>