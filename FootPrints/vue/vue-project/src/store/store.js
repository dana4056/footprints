import Vuex from 'vuex'
import actions from './actions'

export const store = new Vuex.Store({
    state:{
        isDuplicateNick:false,
        isDuplicateEmail:false,
        find_ID: "blabla",
        member:{
            nick: "",
            email: "",
            pw: "",
            area: "Seoul"
        },
        sidoList:{},
        sigoonguList:{},
        eupmyeondongList:{},
        sessionId:"",
        deliveryPostList:[],
        deliveryPost:{}
    },
    getters:{
        GET_MEMBER(state){
            return state.member;
        },
        GET_FINDID(state){
            return state.find_ID;
        },
        GET_FINDPWD(state){
            return state.find_ID;
        },
        GET_DELIVERIES(state){
            return state.deliveryPostList;
        },
        GET_DELIVERY_POST(state){
            return state.deliveryPost;
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
        FIND_ID(state, email){
            state.find_ID = email;
            console.log("2. " + state.find_ID);
        },
        SET_DELIVERIES(state, list){
            state.deliveryPostList = list;
        },
        SET_DELIVERY_POST(state, post){
            state.deliveryPost = post;
        }
    }
});
