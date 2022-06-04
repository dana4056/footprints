<template>
  <div>
    <tool-bar></tool-bar>
    <div>DeliveryView - {{  areaInfo }}</div>

    <!-- 글의 정보들 넣기 -->
    <ul class="delivery-list">
      <li v-for="item in deliveryListItems" v-bind:key="item.post_name" >
        <router-link v-bind:to="`delivery/item/${item.post_name}`">
              {{ item.post_name }}
        </router-link>
      </li>
    </ul>
  </div>
</template>

<script>
import ToolBar from '../components/ToolBar.vue'
import { store } from '../store/store.js'
export default {
    components:{
        ToolBar,
    },
    computed: {
      areaInfo() {
        return store.state.member.area;
      },
      deliveryListItems(){
        return store.state.deliveryList;
      }
    },
    created() {
      const areaName = this.areaInfo;
      store.dispatch('FETCH_DELIVERY_LIST', areaName);
    }
}
</script>

<style>
</style>