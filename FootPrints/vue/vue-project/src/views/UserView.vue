<template>
  <div>
    test vue
  </div>
</template>

<script>
export default {
 created(){
    this.$store.dispatch('FETCH_TEST');
  }
}
</script>

<style>

</style>