import { postEmail, postNick, postPhone, postMemberInfo } from "../api/index.js"
import { fetchSido, fetchSigoongu, fetchEupmyeondong } from "../api/index.js"
import { router } from '../routes/index.js';

export default{
  POST_EMAIL(context, email){
    postEmail(email)
        .then(response =>{ console.log(response); })
        .catch(error=>{ console.log(error); })
  },
  POST_NICK(context, nick){
    postNick(nick)
        .then(response =>{ console.log(response);})
        .catch(error=>{ console.log(error);})
  },
  POST_PHONE(context, phone){
    postPhone(phone)
        .then(response =>{ console.log(response);})
        .catch(error=>{ console.log(error);})
  },
  POST_MEMBER({commit}, member){
    postMemberInfo(member)
        .then(response =>{
          console.log(response);
          commit('SET_MEMBER', member);
          router.replace("/signup-completed");})
        .catch( error=>{console.log(error);} )
  },
  FETCH_SIDO({commit}){
    fetchSido()
    .then(response =>{ 
      const sidoList = response.data.response.result.featureCollection.features;
      commit("SET_SIDO_LIST", sidoList);
      })
    .catch(error => {console.log(error)})
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
  }
}
