import { postEmail, postNick, postPhone, postMemberInfo, postLogin } from "../api/index.js"
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
    POST_LOGIN(context, member) {
        postLogin(member)
            .then(response => {
                console.log(response);
                router.replace("/home");
            })
            .catch(error => { console.log(error); })
    },
}
