import Vuex from 'vuex'
import actions from './actions'

export const store = new Vuex.Store({
    state:{
        isDuplicateNick:false,
        isDuplicateEmail:false,
        member:{
            email: "",
            pw: "",
            nick: "",
            phone: "",
            area: ""
        },
        sidoList:{},
        sigoonguList:{},
        eupmyeondongList:{},
    },
    getters:{
        GET_MEMBER(state){
            return state.member;
        }
    },
    actions,
    mutations:{
        SET_DUPLI_NICK(state, bool){
            state.isDuplicateNick = bool;
        },
        SET_DUPLI_EMAIL(state, bool){
            state.isDuplicateEmail = bool;
        },
        SET_MEMBER(state, member){
            state.member = member;
        },
        SET_SIDO_LIST(state, List){
            state.sidoList = List;
        },
        SET_SIGOONGU_LIST(state, List){
            state.sigoonguList = List;
        },
        SET_EUPMYEONDONG_LIST(state, List){
            state.eupmyeondongList = List;
        },
    }
});
