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
        SET_MEMBER(state, member){
            state.member = member;
        },
        SET_SIDO_LIST(state, List){
            state.sidoList = List;
        //     console.log("-------------- sido --------------")
        //     console.log(state.sidoList)
        //     console.log(state.sidoList.properties)
        },
        SET_SIGOONGU_LIST(state, List){
            state.sigoonguList = List;
        },
        SET_EUPMYEONDONG_LIST(state, List){
            state.eupmyeondongList = List;
        },
    }
});
