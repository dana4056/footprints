<template>
  <AppHeader></AppHeader>
  <AppSns></AppSns>
  <AppInput></AppInput>
</template>

<script>
import AppHeader from './components/AppHeader.vue'
import AppSns from './components/AppSns.vue'
import AppInput from './components/AppInput.vue'

export default {
  name: 'App',
  components: {
    AppHeader,
    AppSns,
    AppInput
  }
}
</script>

<style>
#app {
  margin-top: 0px;
}
body {
  background-color: #f3f3f3;
}
div {
  text-align: center;
}
</style>
