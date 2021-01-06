import axios from "axios";

const SERVER_URL = process.env.VUE_APP_SERVER_URL;

// 쿠키 생성 함수
function setCookie(cName, cValue, cDay){//("이름", "on", "day") day = 1, 2, 3...
    var expire = new Date();
    expire.setDate(expire.getDate() + cDay);
    var cookies = cName + '=' + escape(cValue) + '; path=/ '; // 한글 깨짐을 막기위해 escape(cValue)를 합니다.
    if(typeof cDay != 'undefined') cookies += ';expires=' + expire.toGMTString() + ';';
    document.cookie = cookies;
}

function getCookie(cookie_name) {
    var x, y;
    var val = document.cookie.split(';');
  
    for (var i = 0; i < val.length; i++) {
      x = val[i].substr(0, val[i].indexOf('='));
      y = val[i].substr(val[i].indexOf('=') + 1);
      x = x.replace(/^\s+|\s+$/g, ''); // 앞과 뒤의 공백 제거하기
      if (x == cookie_name) {
        return unescape(y); // unescape로 디코딩 후 값 리턴
      }
    }
  }

function addCookie(id) {
    var items = getCookie('visited'); // 이미 저장된 값을 쿠키에서 가져오기
    var maxItemNum = 5; // 최대 저장 가능한 아이템개수
    var expire = 1; // 쿠키값을 저장할 기간
    if (items) {
      var itemArray = items.split(',');
      if (itemArray.indexOf(id) != -1) {
        // 이미 존재하는 경우 종료
        console.log('Already exists.');
      }
      else {
        // 새로운 값 저장 및 최대 개수 유지하기
        itemArray.unshift(id);
        if (itemArray.length > maxItemNum ) itemArray.length = 5;
        items = itemArray.join(',');
        setCookie('visited', items, expire);
      }
    }
    else {
      // 신규 id값 저장하기
      setCookie('visited', id, expire);
    }
  }
const searchStore = {

    namespaced: true,
    state: {
        houseposition: [],
        hospitalposition: [],
        pharmacyposition: [],
        parkposition: [],
        house: [],
        deal: [],
        hospital: [],
        pharmacy: [],
        park: [],
    },
    getters: {
        getHousePosition(state) {
            return state.houseposition;
        },
        getHospitalPosition(state) {
            return state.hospitalposition;
        },
        getPharmacyPosition(state) {
            return state.pharmacyposition;
        },
        getParkPosition(state) {
            return state.parkposition;
        },
        getHouse(state) {
            return state.house;
        },
        getDeal(state) {
            return state.deal;
        },
        getHospital(state) {
            return state.hospital;
        },
        getPharmacy(state) {
            return state.pharmacy;
        },
        getPark(state) {
            return state.park;
        }
    },
    mutations: {
        HOUSEPOSITION(state, payload) {
            // state.house = payload.house;
            state.houseposition = [];
            state.deal = [];
            for (let index = 0; index < payload.house.length; index++) {
                state.houseposition.push({ position: { lat: Number(payload.house[index].lat), lng: Number(payload.house[index].lng) } });
            }
        },
        HOSPITALPOSITION(state, payload) {
            state.hospitalposition = [];
            for (let index = 0; index < payload.hospital.length; index++) {
                state.hospitalposition.push({ position: { lat: Number(payload.hospital[index].hosY), lng: Number(payload.hospital[index].hosX) } });
            }
            console.log(state.hospitalposition);
        },
        PHARMACYPOSITION(state, payload) {
            state.pharmacyposition = [];
            for (let index = 0; index < payload.pharmacy.length; index++) {
                state.pharmacyposition.push({ position: { lat: Number(payload.pharmacy[index].pharY), lng: Number(payload.pharmacy[index].pharX) } });
            }
        },
        PARKPOSITION(state, payload) {
            state.parkposition = [];
            for (let index = 0; index < payload.park.length; index++) {
                state.parkposition.push({ position: { lat: Number(payload.park[index].parkY), lng: Number(payload.park[index].parkX) } });
            }
        },
        HOSPITALCLEAR(state) {
            state.hospitalposition = [];
        },
        PHARMACYCLEAR(state) {
            state.pharmacyposition = [];
        },
        PARKCLEAR(state) {
            state.parkposition = [];
        },
        HOUSE(state, payload) {
            state.house = payload.house;
        },
        DEAL(state, payload) {
            state.deal = payload.deal;
        },
        HOSPITAL(state, payload) {
            state.hospital = payload.hospital;
        },
        PHARMACY(state, payload) {
            state.pharmacy = payload.pharmacy;
        },
        PARK(state, payload) {
            state.park = payload.park;
        },
        deleteHouse(state) { 
            state.house = [];
            state.houseposition = [];
        }
    },
    actions: {
        SEARCH(context, payload) {
            context.commit("deleteHouse");
            let temp = (payload.keyword);
            axios
                .get(`${SERVER_URL}house/` + payload.keyword)
                .then((response) => {
                    addCookie(temp), context.commit("HOUSEPOSITION", { house: response.data }),
                        context.commit("HOUSE", { house: response.data }), console.log(temp)
                })
                .catch((exp) => { alert("검색결과가 없습니다."); console.log(exp);})
        },
        DEAL(context, payload) {
            axios
                .get((`${SERVER_URL}house/` + payload.dong + '/' + payload.aptName))
                .then(response => { console.log(response), context.commit("DEAL", { deal: response.data }) })
                .catch(exp => console.log(exp))
        },
        HOSPITAL(context, payload) {
            axios
                .get((`${SERVER_URL}animal/hospital/` + payload))
                .then(response => {
                    context.commit("HOSPITAL", { hospital: response.data }),
                        context.commit("HOSPITALPOSITION", { hospital: response.data })
                })
                .catch(exp => console.log(exp))
        },
        PHARMACY(context, payload) {
            axios
                .get((`${SERVER_URL}animal/pharmacy/` + payload))
                .then(response => {
                    console.log(response), context.commit("PHARMACY", { pharmacy: response.data }),
                        context.commit("PHARMACYPOSITION", { pharmacy: response.data })
                })
                .catch(exp => console.log(exp))
        },
        PARK(context, payload) {
            axios
                .get((`${SERVER_URL}animal/park/` + payload))
                .then(response => {
                    console.log(response), context.commit("PARK", { park: response.data }),
                        context.commit("PARKPOSITION", { park: response.data })
                })
                .catch(exp => console.log(exp))
        }
    }
}

export default searchStore;