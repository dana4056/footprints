import { postEmail, postNick, postLogin, postMemberInfo } from "../api/index.js"
import { fetchSido, fetchSigoongu, fetchEupmyeondong } from "../api/index.js"
import { router } from '../routes/index.js';

export default{
  POST_EMAIL(context, email){
    postEmail(email)
        .then(response =>{ console.log(response); 
              alert("사용가능한 이메일입니다.");})
        .catch(error=>{ console.log(error); 
              alert("중복된 이메일입니다.");})
  },
  POST_NICK(context, nick){
    postNick(nick)
        .then(response =>{ console.log(response);
          alert("사용가능한 닉네임입니다.");})
        .catch(error=>{ console.log(error);
          alert("중복된 닉네임입니다.");})
  },
  // POST_PHONE(context, phone){
  //   postPhone(phone)
  //       .then(response =>{ console.log(response);})
  //       .catch(error=>{ console.log(error);})
  // },
  POST_MEMBER({commit}, member){
    postMemberInfo(member)
        .then(response =>{
          console.log(response);
          commit('SET_MEMBER', member);
          router.replace("/signup-completed");})
        .catch( error=>{console.log(error);} )
  }, 
  POST_LOGIN(context, member) {
    postLogin(member)
        .then(response => {
            console.log(response);
            router.replace("/home");
        })
        .catch(error => { console.log(error); })
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
