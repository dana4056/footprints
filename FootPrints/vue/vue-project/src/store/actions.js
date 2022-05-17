import { postEmail, postNick, postPhone, postMemberInfo } from "../api/index.js"
import { router } from '../routes/index.js';

export default{
    POST_EMAIL(email){
        postEmail(email)
            .then(response =>{ console.log(response); })
            .catch(error=>{ console.log(error); })
    },
    POST_NICK(nick){
        postNick(nick)
            .then(response =>{ console.log(response);})
            .catch(error=>{ console.log(error);})
    },
    POST_PHONE(phone){
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
}
