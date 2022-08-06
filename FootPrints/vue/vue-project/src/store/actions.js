import { fetchNoticeList, fetchNoticeDetail, postNotice } from "../api/index.js"
import { postEmail, postNick, postLogin, postMemberInfo } from "../api/index.js"
// import { fetchSido, fetchSigoongu, fetchEupmyeondong } from "../api/index.js"
import { findID, findPW, changePWD } from "../api/index.js"
import { findPostID, findRoom, findUser, findChatLogs, postChatData } from "../api/index.js"
import { fetchUser, fetchDeliveryList, postDeliveryPost, fetchDeliveryDetail, fetchDeliveryList_Category, fetchDeliveryList_Time, fetchDeliveryList_Area } from "../api/index.js";
import { changeMember } from "../api/index.js";
import { router } from '../routes/index.js';

export default{ 
  FETCH_USER({commit}){
    fetchUser()
      .then(response =>{
        console.log("API:FETCH_USER\n멤버 가져오기 성공",response.data);
        const member = {
          nick: response.data.nick,
          email: "test",
          pw: "test",
          area: response.data.area
        }
        commit('SET_MEMBER',member)
      })
      .catch(error => {
        const code = error.response.status;
        if(code == 403){
          console.log("API:FETCH_USER\n멤버 가져오기 실패(로그인 필요)",error);
          alert("FETCH_USER 로그인 후 이용하세요");
          //history.back(); 
          router.replace("/home");
          // location.href = "http://localhost:8080/home"
        }
        else{
          console.log("API:FETCH_USER\n멤버 가져오기 실패(??)",error);
        }
      })
  },

  // 리스트뷰 페이지 데이터 로드
  FETCH_NOTICE_LIST({ commit }) {
    fetchNoticeList()
      .then(response => {
        console.log("API:FETCH_NOTICE_LIST\n공지사항 정보 받아오기 성공", response.data);
        commit('SET_NOTICELIST', response.data);
      })
      .catch(error => {
        console.log("API:FETCH_NOTICE_LIST\n공지사항 리스트 뷰 페이지 정보 받아오기 실패", error);
      })
  },

  FETCH_NOTICE_DETAIL({ commit }, notice_id) {
    fetchNoticeDetail(notice_id)
      .then(response => {
        console.log("API:FETCH_NOTICE_DETAIL\n공지사항 상세정보 받아오기 성공", response.data);
        commit('SET_NOTICE', response.data);
      })
      .catch(error => {
        console.log("API:FETCH_NOTICE_DETAIL\n공지사항 상세정보 페이지 정보 받아오기 실패", error);
      })
  },

  // 공지사항 작성
  POST_NOTICE(content, noticeDTO) {
    postNotice(noticeDTO)
      .then(response => {
        console.log("API:POST_NOTICE\n공지사항 등록 성공", response);
      })
      .catch(error => {
        console.log("API:POST_NOTICE\n공지사항 등록 실패", error);
      })
  },
  // 이메일 중복체크
  POST_EMAIL({commit}, email){
    postEmail(email)
      .then(response => {
          console.log('API:POST_EMAIL\n사용가능 이메일',response);
          commit('SET_DUPLI_EMAIL', false)
      })
      .catch(error => {
          console.log('API:POST_EMAIL\n사용불가 이메일',error);
          commit('SET_DUPLI_EMAIL', true)
      })
  },
  
  // 닉네임 중복체크
  POST_NICK({commit}, nick){
    postNick(nick)
        .then(response =>{
            console.log('API:POST_NICK\n사용가능 닉네임',response);
            commit('SET_DUPLI_NICK', false);
          })
        .catch(error=>{
            console.log('API:POST_NICK\n사용불가 닉네임(중복)',error);
            commit('SET_DUPLI_NICK', true)})
  },
  // 회원가입
  POST_MEMBER(context, member){
    postMemberInfo(member)
        .then(response =>{
          if(response.data == "SUCCESS"){
              console.log('API:POST_MEMBER\n회원가입 성공',response);
              router.replace({
              name: "signupCompleted",
              query: { nickName: member.nick, }
            });
          }
          else{
            console.log('API:POST_MEMBER\n회원가입 실패',response.data);
            alert("회원가입 실패");
          }
          })
        .catch( error=>{console.log('API:POST_MEMBER\n회원가입 실패',error);} )
  }, 
  // 로그인
  POST_LOGIN({commit}, member) {
    postLogin(member)
        .then(response => {
          console.log('API:POST_LOGIN\n로그인 성공',response);
            
          localStorage.setItem('jwt', response.data); // 로컬 스토리지에 저장
          commit('SET_MEMBER', member);
          router.replace("/home");

          findPostID(member.nick)
          .then(response => {
            console.log("API:SET_FIND_POSTID 사용자의 POST_ID 리스트 받아오기 성공", response.data);
            commit('SET_FIND_POSTID', response.data);

            findRoom(response.data)
            .then(response => {
              console.log("API:SET_FIND_ROOM 4 Room 리스트 받아오기 성공", response.data);
              commit('SET_FIND_ROOM', response.data);
            })
            .catch(error => {
              console.log("사용자의 Room 리스트 받아오기 실패", error);
            })
          })
          .catch(error => {
            console.log("사용자의 POST_ID 리스트 받아오기 실패", error);
          })
        })
        .catch(error => { 
          const code = error.response.status;
          if(code == 400){
            console.log("API:POST_LOGIN\n로그인 실패 - 존재하지 않는 닉네임",error);
            alert("API:POST_LOGIN\n로그인 실패 - 존재하지 않는 닉네임입니다.");
          }
          else if(code == 404){
            console.log("API:POST_LOGIN\n로그인 실패 - 비밀번호 불일치",error);
            alert("비밀번호가 일치하지 않습니다.");
          }
          else{console.log("API:POST_LOGIN\n로그인 실패",error)}
        })
  },
  // 로그아웃
  // POST_LOGOUT() {
  //   postLogout()
  //       .then(response => {
  //         console.log(response);
  //       })
  //       .catch(error => { 
  //         console.log(error);
  //       })
  // },
  // 시/도 정보 가져오기
  // FETCH_SIDO({commit}){
  //   fetchSido()
  //   .then(response =>{ 
  //     console.log(response);
  //     const sidoList = response.data.response.result.featureCollection.features;
  //     commit("SET_SIDO_LIST", sidoList);
  //     })
  //   .catch(response => {
  //     console.log(response)
  //     const sidoList = response.data.response.result.featureCollection.features;
  //     commit("SET_SIDO_LIST", sidoList);
  //     })
  // },
  // // 시/군/구 정보 가져오기
  // FETCH_SIGOONGU({commit}, code){
  //   fetchSigoongu(code)
  //   .then(response =>{ 
  //     const sigoonguList = response.data.response.result.featureCollection.features;
  //     commit("SET_SIGOONGU_LIST", sigoonguList);
  //     })
  //   .catch(error => {console.log(error)})
  // },
  // // 읍/면/리 정보 가져오기
  // FETCH_EUPMYEONDONG({commit}, code){
  //   fetchEupmyeondong(code)
  //   .then(response =>{ 
  //     const eupmyeondongList = response.data.response.result.featureCollection.features;
  //     commit("SET_EUPMYEONDONG_LIST", eupmyeondongList);
  //     })
  //   .catch(error => {console.log(error)})
  // },

  // 아이디 찾기
  FIND_NICK({ commit }, email) {
    return findID(email)
      .then(response => {
        console.log('API:FIND_NICK\n닉네임찾기 성공',response);
        commit('SET_FIND_MEMBER_NICK', response.data)
      })
      .catch(error => { console.log('API:FIND_NICK\n닉네임찾기 실패',error); })
  },

  FIND_PWD({ commit }, email) {
    return findPW(email)
      .then(response => {
        console.log('API:FIND_PWD\n비밀번호 찾기 성공',response);
        commit('SET_FIND_MEMBER_EMAIL', response.data)
      })
      .catch(error => {
        console.log('API:FIND_PWD\n비밀번호 찾기 실패',error);
      })
  },
  // 비밀번호 변경
  CHANGE_PWD({ commit }, memberDTO) {
    return changePWD(memberDTO)
      .then(response => {
        console.log('API:CHANGE_PWD\n비밀번호 변경 성공',response);
        commit('SET_PWCHANGE_DONE', response.data);
      })
      .catch(error => {
        console.log('API:CHANGE_PWD\n비밀번호 변경 실패',error);
      })
  },
  // 리스트뷰 페이지 데이터 로드
  FETCH_DELIVERY_LIST({commit}){
    fetchDeliveryList()
      .then(response =>{
        console.log("API:FETCH_DELIVERY_LIST\n배달 리스트 뷰 페이지 정보 받아오기 성공",response.data);
        commit('SET_DELIVERIES', response.data);
      })
      .catch(error =>{
        const code = error.response.status;
        if(code == 403){
          alert("FETCH_DELIVERY_LIST 로그인 후 이용하세요");
          //history.back(); 
          router.replace("/home");
          // location.href = "http://localhost:8080/home"
        }
        else{
          console.log("API:FETCH_DELIVERY_LIST\n배달 리스트 뷰 페이지 정보 받아오기 실패",error);
        }
      })
  },
  // 리스트뷰 페이지 데이터 로드
  FETCH_DELIVERY_LIST_CATEGORY({ commit }, category) {
    fetchDeliveryList_Category(category)
      .then(response => {
        console.log("API:FETCH_DELIVERY_LIST_CATEGORY\n카테고리별 배달 리스트 뷰 페이지 정보 받아오기 성공\n", response.data);
        commit('SET_DELIVERIES', response.data);
      })
      .catch(error => {
        const code = error.response.status;
        if (code == 403) {
          alert("FETCH_DELIVERY_LIST_CATEGORY 로그인 후 이용하세요");
          //history.back();
          router.replace("/home");
          // location.href = "http://localhost:8080/home"
        }
        else {
          console.log("API:FETCH_DELIVERY_LIST_CATEGORY\n카테고리별 배달 리스트 뷰 페이지 정보 받아오기 실패\n", error);
        }
      })
  },
  FETCH_DELIVERY_LIST_SORT_TIME({ commit }, time) {
    fetchDeliveryList_Time(time)
      .then(response => {
        console.log("API:FETCH_DELIVERY_LIST_TIME\n시간별 배달 리스트 뷰 페이지 정보 받아오기 성공(GET success)\n", response.data);
        commit('SET_DELIVERIES', response.data);
      })
      .catch(error => {
        const code = error.response.status;
        if (code == 403) {
          alert("FETCH_DELIVERY_LIST_SORT_TIME 로그인 후 이용하세요");
          //history.back();
          router.replace("/home");
          // location.href = "http://localhost:8080/home"
        }
        else {
          console.log("API:FETCH_DELIVERY_LIST_TIME\n시간별 배달 리스트 뷰 페이지 정보 받아오기 실패\n", error);
        }
      })
  },

FETCH_DELIVERY_LIST_SORT_AREA({ commit }, area) {
  fetchDeliveryList_Area(area)
    .then(response => {
      console.log("API:FETCH_DELIVERY_LIST_SORT_AREA\n지역별 배달 리스트 뷰 페이지 정보 받아오기 성공(GET success)\n", response.data);
      commit('SET_DELIVERIES', response.data);
    })
    .catch(error => {
      const code = error.response.status;
      if (code == 403) {
        alert("FETCH_DELIVERY_LIST_SORT_AREA 로그인 후 이용하세요");
        //history.back();
        router.replace("/home");
        // location.href = "http://localhost:8080/home"
      }
      else {
        console.log("API:FETCH_DELIVERY_LIST_SORT_AREA\n지역별 배달 리스트 뷰 페이지 정보 받아오기 실패\n", error);
      }
    })
},


  // 게시물 작성
  POST_DELIVERY_POST(content, post){
    postDeliveryPost(post)
    .then(response =>{
      console.log("API:POST_DELIVERY_POST\n게시물 등록 성공",response);
    })
    .catch(error => {
      const code = error.response.status;
      if(code == 403){
        alert("POST_DELIVERY_POST 로그인 후 이용하세요");
        router.replace("/home");
      }
      else{
        console.log("API:POST_DELIVERY_POST\n게시물 등록 실패",error);
      }
    })
  },
  // 글 작성시 방 정보 테이블에 row 추가
  // POST_ROOMINFO(content, roomInfo){
  //   postRoomInfo(roomInfo)
  //   .then(response =>{
  //     console.log("API:POST_ROOMINFO\n방 정보 등록 성공",response);
  //   })
  //   .catch(error => {
  //     console.log("API:POST_ROOMINFO\n방 정보 등록 실패",error);
  //   })
  // },
  // 상세 페이지 데이터 로드
  FETCH_DELIVERY_DETAIL({commit}, post_id){
    fetchDeliveryDetail(post_id)
      .then(response =>{
        console.log("API:FETCH_DELIVERY_DETAIL\n상세페이지 정보 받아오기 성공",response.data);
        commit('SET_DELIVERY_POST', response.data);
      })
      .catch(error => {
        const code = error.response.status;
        if(code == 403){
          console.log("API:FETCH_DELIVERY_DETAIL\n상세페이지 정보 받아오기 실패 - 로그인 필요",error);
          alert("FETCH_DELIVERY_DETAIL 로그인 후 이용하세요");
          router.replace("/home");
        }
        else{
          console.log("API:FETCH_DELIVERY_DETAIL\n상세페이지 정보 받아오기 실패",error);
        }
      })
  },
  // POST_LOGIN 안으로 이동 -> 초기에 PostIdList와 RoomList가 초기화 되어야 채팅 뷰 이동시 올바르게 생성됨
  // FIND_POST_ID({ commit }, nick) {
  //   findPostID(nick)
  //     .then(response => {
  //       console.log("API:SET_FIND_POSTID 사용자의 POST_ID 리스트 받아오기 성공", response.data);
  //       commit('SET_FIND_POSTID', response.data);
  //     })
  //     .catch(error => {
  //       console.log("사용자의 POST_ID 리스트 받아오기 실패", error);
  //     })
  // },
  // FIND_ROOM({ commit }, list) {
  //   findRoom(list)
  //     .then(response => {
  //       console.log("API:SET_FIND_ROOM 사용자의 Room 리스트 받아오기 성공", response.data);
  //       commit('SET_FIND_ROOM', response.data);
  //     })
  //     .catch(error => {
  //       console.log("사용자의 Room 리스트 받아오기 실패", error);
  //     })
  // },
  FIND_USER({ commit }, post_id) {
    findUser(post_id)
      .then(response => {
        console.log("API:SET_FIND_USER 채팅방에 속한 사용자 nick 리스트 받아오기 성공", response.data);
        commit('SET_FIND_USER', response.data);
      })
      .catch(error => {
        console.log("채팅방에 속한 사용자 nick 리스트 받아오기 실패", error);
      })
  },
  FIND_CHAT_LOGS({ commit }, post_id) {
    findChatLogs(post_id)
      .then(response => {
        console.log("API:SET_FIND_CHAT_LOGS 채팅방 chatlogs 리스트 받아오기 성공", response.data);
        commit('SET_FIND_CHAT_LOGS', response.data);
      })
      .catch(error => {
        console.log("채팅방 chatlogs 리스트 받아오기 실패", error);
    })
  },
  POST_CHAT_DATA({ commit }, chatData) {
    postChatData(chatData)
      .then(response => {
        console.log("채팅 보내기 성공", response.data);

        findChatLogs(chatData.post_id)
        .then(response => {
          console.log("API:SET_FIND_CHAT_LOGS 채팅방 chatlogs 리스트 받아오기 성공", response.data);
          commit('SET_FIND_CHAT_LOGS', response.data);
        })
        .catch(error => {
          console.log("채팅방 chatlogs 리스트 받아오기 실패", error);
        })

      })
      .catch(error => {
        console.log("채팅 보내기 실패", error);
      })
  },
  CHANGE_MEMBER({ commit }, memberDTO) {
    return changeMember(memberDTO)
      .then(response => {
        console.log('API:CHANGE_PWD\n회원정보 변경 성공', response);
        commit('SET_MEMBER_CHANGE_DONE', response.data);
      })
      .catch(error => {
        console.log('API:CHANGE_PWD\n회원정보 변경 실패', error);
      })
  },
}
