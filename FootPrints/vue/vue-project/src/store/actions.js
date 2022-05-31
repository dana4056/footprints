import { postEmail, postNick, postLogin, postMemberInfo } from "../api/index.js"
import { fetchSido, fetchSigoongu, fetchEupmyeondong } from "../api/index.js"
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
        .catch(error => { 
          const code = error.response.status;
          if(code == 400) alert("존재하지 않는 닉네임입니다.");
          else if(code == 404) alert("비밀번호가 일치하지 않습니다.");
        })
  },
  FETCH_SIDO({commit}){
    fetchSido()
    .then(response =>{ 
      console.log(response);
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
