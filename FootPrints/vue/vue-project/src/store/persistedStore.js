  // 새로고침해도 초기화되지 않는 데이터들
  // localStorage에 저장되기 때문에 신중
  // 접근시 this.$store.state.persistedStore.키값

  const PRESENTAREA = () =>{
    return "로그인 시 지역 설정됨"
  }

  const state = {
    deliveryPost_presentArea: PRESENTAREA()
  };
  
  const getters = {

};
  
  const mutations = {

  };
  
  const actions = {
 
  };
  
  export default {
    strict: process.env.NODE_ENV !== "production",
    state: {
      ...state
    },
    getters,
    mutations,
    actions
  };
   