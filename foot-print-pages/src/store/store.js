import Vuex from 'vuex'

const storage = {
    fetch(){
        const arr = [];
        if (localStorage.length > 0){ // 로컬 스토리지에 데이터가 존재한다면
            for( let i = 0; i < localStorage.length; i++){
                if(localStorage.key(i)!=='loglevel:webpack-dev-server'){
                    arr.push((localStorage.getItem(localStorage.key(i))));  
                }
            }
        }
        return arr;
    }
};

export const store = new Vuex.Store({
    state:{
        MemberList: storage.fetch()
    },
    getter:{
        getMembers(state){
            return state.MemberList;
        }
    },
    mutations:{
        addOneMember(state, Member){
            localStorage.setItem(Member.Id, JSON.stringify(Member));  
            //this. 말고 state.로 접근
            state.MemberList.push(Member); 
        }
    }
});