<template>
  <div>
    배달 상위 노출
    <!-- 로그인 상태면 그 지역 -->
    <!-- 로그인 하지 않은 상태면 default 지역 -->
  </div>
</template>

<script>
export default {
created(){
    this.$store.dispatch('FETCH_DELIVERY_LIST');
    // api 새로 만들거나 토큰 있을때 없을때 경우 나눠서 코드 짜면 될듯
  },
}
</script>

<style>

</style>