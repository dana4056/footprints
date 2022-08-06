<template>
  <div class="container">
    내가 쓴글 쫘라락
    from Post p
    where p.nick = :nick 
    싹 불러오자.
  </div>
</template>

<script>
export default {
methods: {
}
}
</script>

<style scoped>
.container {

}
</style>