import { postEmail, postNick, postLogin, postLogout, postMemberInfo, } from "../api/index.js"
import { fetchSido, fetchSigoongu, fetchEupmyeondong } from "../api/index.js"
import { findID, changePWD } from "../api/index.js"
import { fetchDeliveryList, postDeliveryPost, fetchDeliveryDetail } from "../api/index.js";
import { router } from '../routes/index.js';


export default{
  POST_EMAIL({commit}, email){
    postEmail(email)
        .then(response =>{ console.log(response);})
        .catch(error=>{ console.log(error);
                        return new Promise((resolve) => {
                          setTimeout(() => {
                            commit('SET_DUPLI_EMAIL', true)
                            resolve()
                          }, 1000)
                        })
                      }
              )
  },
  POST_NICK({commit}, nick){
    postNick(nick)
        .then(response =>{ console.log(response);})
        .catch(error=>{ console.log(error);
                        commit('SET_DUPLI_NICK', true)})
  },
  POST_MEMBER(context, member){
    postMemberInfo(member)
        .then(response =>{
          console.log(response);
          // commit('SET_MEMBER', member);
          router.replace({
            name:"signupCompleted",
            query:{nickName:member.nick,}
          });})
        .catch( error=>{console.log(error);} )
  }, 
  POST_LOGIN({commit}, member) {
    postLogin(member)
        .then(response => {
            console.log(response);
            
            commit('SET_MEMBER', member);
            router.replace("/home");
        })
        .catch(error => { 
          const code = error.response.status;
          if(code == 400) alert("존재하지 않는 닉네임입니다.");
          else if(code == 404) alert("비밀번호가 일치하지 않습니다.");
        })
  },
  POST_LOGOUT() {
    postLogout()
        .then(response => {
          console.log(response);
        })
        .catch(error => { 
          console.log(error);
        })
  },
  FETCH_SIDO({commit}){
    fetchSido()
    .then(response =>{ 
      console.log(response);
      const sidoList = response.data.response.result.featureCollection.features;
      commit("SET_SIDO_LIST", sidoList);
      })
    .catch(response => {
      console.log(response)
      const sidoList = response.data.response.result.featureCollection.features;
      commit("SET_SIDO_LIST", sidoList);
      })
  },
  FETCH_SIGOONGU({commit}, code){
    fetchSigoongu(code)
    .then(response =>{ 
      const sigoonguList = response.data.response.result.featureCollection.features;
      commit("SET_SIGOONGU_LIST", sigoonguList);
      })
    .catch(error => {console.log(error)})
  },
  FETCH_EUPMYEONDONG({commit}, code){
    fetchEupmyeondong(code)
    .then(response =>{ 
      const eupmyeondongList = response.data.response.result.featureCollection.features;
      commit("SET_EUPMYEONDONG_LIST", eupmyeondongList);
      })
    .catch(error => {console.log(error)})
  },
  FIND_ID({ commit }, email) {
    findID(email)
      .then(response => {
        console.log(response);
        console.log("1. " + response.data);
        commit('FIND_ID', response.data);
      })
      .catch(error => {
        console.log(error);
      })
  },
  CHANGE_PWD(context, memberChangeDTO) {
    changePWD(memberChangeDTO)
      .then(response => {
        console.log(response);
      })
      .catch(error => {
        console.log(error);
      })
  },
  FETCH_DELIVERY_LIST({commit}){
    fetchDeliveryList()
      .then(response =>{
        console.log("배달 리스트 뷰 페이지 정보 받아오기 성공(GET success)");
        console.log(response.data);
        commit('SET_DELIVERIES', response.data);
      })
      .catch(error =>{
        console.log("배달 리스트 뷰 페이지 정보 받아오기 실패(GET success)");
        console.log(error);
      })
  },
  POST_DELIVERY_POST(content, post){
    postDeliveryPost(post)
    .then(response =>{
      console.log(response);
    })
    .catch(error => {
      console.log(error);
    })
  },
  FETCH_DELIVERY_DETAIL({commit}, post_id){
    fetchDeliveryDetail(post_id)
      .then(response =>{
        console.log("상세페이지 정보 받아오기 성공(GET success)\n",response.data);
        console.log("createdDate",typeof(response.data.createdDate));
        console.log("valid_time",typeof(response.data.valid_time));
        commit('SET_DELIVERY_POST', response.data);
      })
      .catch(error => {
        console.log("상세페이지 정보 받아오기 실패");
        console.log(error);
      })
  }
}
