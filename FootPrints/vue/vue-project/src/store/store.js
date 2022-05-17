import Vuex from 'vuex'
import actions from './actions'

export const store = new Vuex.Store({
    state:{
        member:{
            email: "",
            pw: "",
            nick: "",
            phone: "",
            area: ""
        }
    },
    getters:{
        GET_MEMBER(state){
            return state.member;
        }
    },
    actions,
    mutations:{
        SET_MEMBER(state, member){
            state.member = member;
        },
    }
});
