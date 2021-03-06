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

// 2. API 함수들을 정리
function fetchUser(){
    return axios.get(`${config.baseUrl}/user`, {
        headers: {
            'X-AUTH-TOKEN': localStorage.getItem('jwt')
        }
    });
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

function postLogin(member){
    return axios.post(`${config.baseUrl}/login`, member);
}

function postMemberInfo(member){
    return axios.post(`${config.baseUrl}/signup`, member);
}

// function fetchSido(){
//     const data = "LT_C_ADSIDO_INFO";
//     return axios.get(`https://api.vworld.kr/req/data?key=${areaConfig.key}&domain=${areaConfig.domain}&service=${areaConfig.service}&version=${areaConfig.version}&request=${areaConfig.request}&format=${areaConfig.format}&size=${areaConfig.size}&page=${areaConfig.page}&attribute=${areaConfig.attribue}&crs=${areaConfig.crs}&geomfilter=${areaConfig.geomfilter}&data=${data}`,{
//         headers:{

//         }
//     });
// }

// function fetchSigoongu(code){
//     const data = "LT_C_ADSIGG_INFO";
//     const attrFilter = `sig_cd:like:${code}`;
//     return axios.get(`https://api.vworld.kr/req/data?key=${areaConfig.key}&domain=${areaConfig.domain}&service=${areaConfig.service}&version=${areaConfig.version}&request=${areaConfig.request}&format=${areaConfig.format}&size=${areaConfig.size}&page=${areaConfig.page}&attribute=${areaConfig.attribue}&crs=${areaConfig.crs}&geomfilter=${areaConfig.geomfilter}&data=${data}&attrFilter=${attrFilter} `)
// }

// function fetchEupmyeondong(code){
//     const data = "LT_C_ADEMD_INFO";
//     const attrFilter = `emd_cd:like:${code}`;
//     return axios.get(`https://api.vworld.kr/req/data?key=${areaConfig.key}&domain=${areaConfig.domain}&service=${areaConfig.service}&version=${areaConfig.version}&request=${areaConfig.request}&format=${areaConfig.format}&size=${areaConfig.size}&page=${areaConfig.page}&attribute=${areaConfig.attribue}&crs=${areaConfig.crs}&geomfilter=${areaConfig.geomfilter}&data=${data}&attrFilter=${attrFilter} `)
// }

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
  
function changePWD(memberDTO){
    return axios.post(`${config.baseUrl}/ChangePW`, memberDTO);
}

function fetchDeliveryList(){
      return axios.get(`${config.baseUrl}/delivery/post`, {
        headers: {
            'X-AUTH-TOKEN': localStorage.getItem('jwt')
        }
    });
}

//카테고리 별로 불러오기 위함
function fetchDeliveryList_Category(category) {
    return axios.get(`${config.baseUrl}/category`, {
        category,
        headers: {
            'X-AUTH-TOKEN': localStorage.getItem('jwt')
        },
    });
}

// 시간별로 불러오기 위함
function fetchDeliveryList_Time(time) {
    return axios.get(`${config.baseUrl}/sort_time`, {
        time,
        headers: {
            'X-AUTH-TOKEN': localStorage.getItem('jwt')
        },
    });
}

  function postDeliveryPost(post){
      return axios.post(`${config.baseUrl}/delivery/post/create`, post,{
        headers: {
            'X-AUTH-TOKEN': localStorage.getItem('jwt')
        }
    }
    );
  }
  
  function fetchDeliveryDetail(post_id){
    return axios.get(`${config.baseUrl}/delivery/post/${post_id}`,{
        headers: {
            'X-AUTH-TOKEN': localStorage.getItem('jwt')
        }
    });
  }
  

export{
    fetchUser,
    postEmail,
    postNick,
    postLogin,
    postMemberInfo,
    // fetchSido,
    // fetchSigoongu,
    // fetchEupmyeondong,
    findID,
    findPW,
    changePWD,
    fetchDeliveryList,
    postDeliveryPost,
    fetchDeliveryDetail,
    fetchDeliveryList_Category,
    fetchDeliveryList_Time,
}