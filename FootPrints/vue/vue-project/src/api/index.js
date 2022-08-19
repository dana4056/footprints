import axios from 'axios';


// 1. HTTP Request & Response와 관련된 기본 설정
const config = {
    baseUrl:"http://localhost:8080"
};
 
// const areaConfig={
//     key:"CEB52025-E065-364C-9DBA-44880E3B02B8",
//     domain:"http://localhost:8080",
//     service:"data",
//     version:"2.0",
//     request:"getfeature",
//     format:"json",
//     size:"1000",
//     page:"1",
//     attribue:"true",
//     crs:"EPSG:3857",
//     geomfilter:"BOX(13663271.680031825,3894007.9689600193,14817776.555251127,4688953.0631258525)",
//   }	


function fetchUser(){
    return axios.get(`${config.baseUrl}/user`, {
        headers: {
            'X-AUTH-TOKEN': localStorage.getItem('jwt')
        }
    });
}

function fetchNoticeList() {
    return axios.get(`${config.baseUrl}/notice`);
}

function fetchNoticeDetail(notice_id){
    return axios.get(`${config.baseUrl}/notice/${notice_id}`,);
}

function findUserArea(nick){
    console.log("function findUserArea 실행")
    return axios.post(`${config.baseUrl}/findUserArea`, nick, {
        headers: {
            'Content-Type': 'text/plain'
        }
    });
}

function postNotice(noticeDTO){
    return axios.post(`${config.baseUrl}/notice/create`, noticeDTO);
}

function postEmail(email){
    return axios.post(`${config.baseUrl}/signup/check-email`, email, {
        headers: {
            'Content-Type': 'text/plain'
        }
    });
}

function postNick(nick){
    return axios.post(`${config.baseUrl}/signup/check-nick`, nick, {
        headers: {
            'Content-Type': 'text/plain'
        }
    });
}

function postLogin(loginMember){
    return axios.post(`${config.baseUrl}/login`, loginMember);
}

function postMemberInfo(member){
    return axios.post(`${config.baseUrl}/signup`, member);
}

function findID(email){
    return axios.post(`${config.baseUrl}/findID`, email, {
        headers: {
            'Content-Type': 'text/plain'
        }
    });
}

function findPW(email){
    return axios.post(`${config.baseUrl}/findPW`, email, {
        headers: {
            'Content-Type': 'text/plain'
        }
    });
}
  
function changePWD(changePwMemberDTO){
    return axios.post(`${config.baseUrl}/ChangePW`, changePwMemberDTO);
}

function fetchDeliveryList(area){
    console.log("function fetchDeliveryList 실행")
    return axios.post(`${config.baseUrl}/delivery/post`, area, {
        headers: {
            'Content-Type': 'text/plain',
            'X-AUTH-TOKEN': localStorage.getItem('jwt')
        }
    });
}

//카테고리 별로 불러오기 위함
function fetchDeliveryList_SORT(sortDTO) {
    return axios.post(`${config.baseUrl}/delivery/post/sort`, sortDTO,{
        headers: {
            'X-AUTH-TOKEN': localStorage.getItem('jwt'),
        },
    });
}

function postDeliveryPost(post){
    return axios.post(`${config.baseUrl}/delivery/post/create`, post,{
        headers: {
            'X-AUTH-TOKEN': localStorage.getItem('jwt')
        }
    });
}

function fetchAmendDeliveryPost(post_id) {
    return axios.get(`${config.baseUrl}/delivery/post/amend/${post_id}`, {
        headers: {
            'X-AUTH-TOKEN': localStorage.getItem('jwt')
        }
    });
}


function amendDeliveryPost(post) {
    return axios.post(`${config.baseUrl}/delivery/post/amend`, post, {
        headers: {
            'X-AUTH-TOKEN': localStorage.getItem('jwt')
        }
    });
}

function deleteChatData(post_id) {
    axios.post(`${config.baseUrl}/chat/delete`, post_id, {
        headers: {
            'Content-Type': 'application/json'
        }
    });
}

function deleteRoomInfo(post_id) {
    axios.post(`${config.baseUrl}/roomInfo/delete`, post_id, {
        headers: {
            'Content-Type': 'application/json'
        }
    });
}

function deletePost(post_id) {
    axios.post(`${config.baseUrl}/post/delete`, post_id, {
        headers: {
            'Content-Type': 'application/json'
        }
    });
}

function joinDeliveryPost(roomInfo) {
    axios.post(`${config.baseUrl}/roomInfo/join`, roomInfo);
}

function exitDeliveryPost(roomInfo) {
    axios.post(`${config.baseUrl}/roomInfo/exit`, roomInfo);
}

function fetchDeliveryDetail(post_id){
    return axios.get(`${config.baseUrl}/delivery/post/${post_id}`,{
        headers: {
            'X-AUTH-TOKEN': localStorage.getItem('jwt')
        }
    });
}

function findPostID(nick) {
    console.log("nick: " + nick);
	return axios.post(`${config.baseUrl}/chat/get-PostIdlist`, nick , {
        headers: {
            'Content-Type': 'text/plain'
        }
    });
}

  function findRoom(list) {
    console.log("postIDList: " + list);
    // const data = JSON.stringify(list)  //JSON으로 변환
    return axios.post(`${config.baseUrl}/chat/get-PostInfoList`, list);
  }

  function findUser(post_id) {
    console.log("post_id:" + post_id);
    return axios.post(`${config.baseUrl}/chat/get-NickList`, post_id, {
        headers: {
            'Content-Type': 'application/json'
        }
    });
  }

  function findChatLogs(post_id) {
    console.log("post_id:" + post_id);
    return axios.post(`${config.baseUrl}/chat/get-ChatList`, post_id, {
        headers: {
            'Content-Type': 'application/json'
        }
    });
  }
  
  function postChatData(chatData) {
    console.log(chatData);
    return axios.post(`${config.baseUrl}/chat/post-ChatDataList`, chatData, {
        headers: {
            'X-AUTH-TOKEN': localStorage.getItem('jwt')
        }
    });
  }

  function fetchMyDPost(nick) {
    return axios.post(`${config.baseUrl}/myPage/main-myPost`, nick, {
        headers: {
            'Content-Type': 'text/plain'
        }
    });
  }

  function fetchMyPartici(nick) {
    return axios.post(`${config.baseUrl}/myPage/main-attendPost`, nick, {
        headers: {
            'Content-Type': 'text/plain'
        }
    });
  }

  function changeMember(memberDTO){
    return axios.post(`${config.baseUrl}/myPage/changeMyInfo`, memberDTO);
  }

export{
    fetchUser,
    fetchNoticeList,
    fetchNoticeDetail,
    postNotice,
    postEmail,
    postNick,
    postLogin,
    postMemberInfo,
    findID,
    findPW,
    changePWD,
    
    postDeliveryPost,
    fetchAmendDeliveryPost,
    amendDeliveryPost,
    deleteChatData,
    deleteRoomInfo,
    deletePost,
    joinDeliveryPost,
    exitDeliveryPost,

    fetchDeliveryList,
    fetchDeliveryDetail,
    fetchDeliveryList_SORT,

    findPostID,
    findRoom,
    findUser,
    findChatLogs,
    postChatData,

    fetchMyDPost,
    fetchMyPartici,
    changeMember,
    findUserArea,
}