import axios from 'axios';


// 1. HTTP Request & Response와 관련된 기본 설정
const config = {
    baseUrl:"http://localhost:8080"
};

const areaConfig={
    key:"CEB52025-E065-364C-9DBA-44880E3B02B8",
    domain:"http://localhost:8080",
    service:"data",
    version:"2.0",
    request:"getfeature",
    format:"json",
    size:"1000",
    page:"1",
    attribue:"true",
    crs:"EPSG:3857",
    geomfilter:"BOX(13663271.680031825,3894007.9689600193,14817776.555251127,4688953.0631258525)",
  }	

  const member = {
    email: "",
    pw: "",
    nick: "",
    phone: "",
    area: ""
  }

// 2. API 함수들을 정리
function postEmail(email){
    member.emial = email;
    return axios.post(`${config.baseUrl}/signup/check-email`, member, {
        headers: {
            'Content-Type': 'application/json'
        }
    });
}

function postNick(nick){
    member.nick = nick;
    return axios.post(`${config.baseUrl}/signup/check-nick`, member, {
        headers: {
            'Content-Type': 'application/json'
        }
    });
}

function postLogin(member){
    return axios.post(`${config.baseUrl}/login-s`, member);
}

// function postPhone(phone){
//     return axios.post(`${config.baseUrl}/signup/authentic-code`, phone, {
//         headers: {
//             'Content-Type': 'application/json'
//         }
//     });
// }

function postMemberInfo(member){
    return axios.post(`${config.baseUrl}/signup`, member);
}

function fetchSido(){
    const data = "LT_C_ADSIDO_INFO";
    return axios.get(`/req/data?key=${areaConfig.key}&domain=${areaConfig.domain}&service=${areaConfig.service}&version=${areaConfig.version}&request=${areaConfig.request}&format=${areaConfig.format}&size=${areaConfig.size}&page=${areaConfig.page}&attribute=${areaConfig.attribue}&crs=${areaConfig.crs}&geomfilter=${areaConfig.geomfilter}&data=${data}`)
}

function fetchSigoongu(code){
    const data = "LT_C_ADSIGG_INFO";
    const attrFilter = `sig_cd:like:${code}`;
    return axios.get(`/req/data?key=${areaConfig.key}&domain=${areaConfig.domain}&service=${areaConfig.service}&version=${areaConfig.version}&request=${areaConfig.request}&format=${areaConfig.format}&size=${areaConfig.size}&page=${areaConfig.page}&attribute=${areaConfig.attribue}&crs=${areaConfig.crs}&geomfilter=${areaConfig.geomfilter}&data=${data}&attrFilter=${attrFilter} `)
}

function fetchEupmyeondong(code){
    const data = "LT_C_ADEMD_INFO";
    const attrFilter = `emd_cd:like:${code}`;
    return axios.get(`/req/data?key=${areaConfig.key}&domain=${areaConfig.domain}&service=${areaConfig.service}&version=${areaConfig.version}&request=${areaConfig.request}&format=${areaConfig.format}&size=${areaConfig.size}&page=${areaConfig.page}&attribute=${areaConfig.attribue}&crs=${areaConfig.crs}&geomfilter=${areaConfig.geomfilter}&data=${data}&attrFilter=${attrFilter} `)
}


export{
    postEmail,
    postNick,
    // postPhone,
    postLogin,
    postMemberInfo,
    fetchSido,
    fetchSigoongu,
    fetchEupmyeondong,
} 