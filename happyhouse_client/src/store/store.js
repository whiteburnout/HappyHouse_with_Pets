import Vue from "vue";
import Vuex from "vuex";
import axios from "axios";

import boardStore from "./modules/boardStore";

Vue.use(Vuex);

const SERVER_URL = process.env.VUE_APP_SERVER_URL;

export default new Vuex.Store({
    state: {
      accessToken: null,
      userNo: "",
      userId: "",
      userName: "",
      useremail: "",
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
      getAccessToken(state) {
        return state.accessToken;
      },
      getUserNo(state) {
        return state.userNo;
      },
      getUserId(state) {
        return state.userId;
      },
      getUserName(state) {
        return state.userName;
      },
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
      LOGIN(state, payload) {
        state.accessToken = payload["auth-token"];
        state.userNo = payload["user-no"]
        state.userId = payload["user-id"];
        state.userName = payload["user-name"];
        state.useremail = payload["email"];
      },
      LOGOUT(state) {
        state.accessToken = null;
        state.userNo = "";
        state.userId = "";
        state.userName = "";
      },
      HOUSEPOSITION(state, payload) {
        // state.house = payload.house;
        state.houseposition = [];
        state.deal = [];
        for (let index = 0; index < payload.house.length; index++) {
          state.houseposition.push({ position: { lat: Number(payload.house[index].lat), lng : Number(payload.house[index].lng) } });
        }
      },
      HOSPITALPOSITION(state, payload) {
        //state.hospitalposition = [];
        for (let index = 0; index < payload.hospital.length; index++) {
          state.hospitalposition.push({ position: { lat: Number(payload.hospital[index].hosY), lng : Number(payload.hospital[index].hosX) } });
        }
        console.log(state.hospitalposition);
      },
      PHARMACYPOSITION(state, payload) {
        //state.pharmacyposition = [];
        for (let index = 0; index < payload.pharmacy.length; index++) {
          state.pharmacyposition.push({ position: { lat: Number(payload.pharmacy[index].pharY), lng : Number(payload.pharmacy[index].pharX) } });
        }
      },
      PARKPOSITION(state, payload) {
        //state.parkposition = [];
        for (let index = 0; index < payload.park.length; index++) {
          state.parkposition.push({ position: { lat: Number(payload.park[index].parkY), lng : Number(payload.park[index].parkX) } });
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
        if (state.hospital.length == 0)
          alert("주변에 동물병원이 없습니다");
      },
      PHARMACY(state, payload) { 
        state.pharmacy = payload.pharmacy;
        if (state.pharmacy.length == 0)
          alert("주변에 동물약국이 없습니다");
      },
      PARK(state, payload) { 
        state.park = payload.park;
        if (state.park.length == 0)
          alert("주변에 공원이 없습니다");
      },

    },
    actions: {
      LOGIN(context, user) {
        return axios
          .post(`${SERVER_URL}user`, user)
          .then((response) => {
            context.commit("LOGIN", response.data);
            //스토리지 저장
            /////////////////////////////////
            axios.defaults.headers.common[//토큰정보를 담음
              "auth-token"
            ] = `${response.data["auth-token"]}`;
          });
      },
      LOGOUT(context) {
        context.commit("LOGOUT");
        axios.defaults.headers.common["auth-token"] = undefined;
        //스토리지 지우기
        ///////////////////////////////
      },
      MODIFY(context, user){
        axios
          .put(`${SERVER_URL}user/`, user)
          .then(() => {context.dispatch("LOGIN", user) })
          .catch(exp => console.log(exp));
      },

      DELETE(context, id) { 
        axios
          .delete(`${SERVER_URL}user/`+ id)
          .then(() => context.dispatch("LOGOUT"))
          .catch(exp => console.log(exp));
      },

      JOIN(context, user) { 
        console.log(SERVER_URL);
        axios
          .post(`${SERVER_URL}user/join`, user)
          .then(() => console.log("성공"))
          .catch(exp => console.log(exp));
      },

      SEARCH(context, payload) {
        axios
          .get(`${SERVER_URL}house/` + payload.selected + '/' + payload.keyword)
          .then((response) => {
            console.log(response + "스토어"), context.commit("HOUSEPOSITION", { house: response.data }),
              context.commit("HOUSE", { house: response.data })})
          .catch((exp) => console.log(exp))
      },
      DEAL(context, payload) {
        axios
          .get((`${SERVER_URL}house/` + payload.dong + '/' + payload.aptName))
          .then(response => { console.log(response), context.commit("DEAL", {deal : response.data})})
          .catch(exp => console.log(exp))
      },
      HOSPITAL(context , payload) { 
        axios
          .get((`${SERVER_URL}animal/hospital/` + payload))
          .then(response => {context.commit("HOSPITAL", { hospital: response.data }),
          context.commit("HOSPITALPOSITION", { hospital: response.data }) })
          .catch(exp => console.log(exp))
      },
      PHARMACY(context , payload) { 
        axios
          .get((`${SERVER_URL}animal/pharmacy/` + payload))
          .then(response => {
            console.log(response), context.commit("PHARMACY", { pharmacy: response.data }),
            context.commit("PHARMACYPOSITION", { pharmacy: response.data }) })
          .catch(exp => console.log(exp))
      },
      PARK(context , payload) { 
        axios
          .get((`${SERVER_URL}animal/park/` + payload))
          .then(response => {
            console.log(response), context.commit("PARK", { park: response.data }),
            context.commit("PARKPOSITION", { park: response.data }) })
          .catch(exp => console.log(exp))
      },
    },
  modules: {
      boardStore,
    }
  });