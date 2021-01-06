import Vue from "vue";
import Vuex from "vuex";

import boardStore from "./modules/boardStore";
import searchStore from "./modules/searchStore";
import reviewStore from "./modules/reviewStore";

import jwt_decode from "jwt-decode";
import { findById } from "./modules/userStore";
Vue.use(Vuex);


export default new Vuex.Store({
    state: {
      isLogin: false, // 로그인 여부
      userInfo: null,
    },
    getters: {
      getUserInfo(state) { 
        return state.userInfo;
      }
    },
    mutations: {
      setIsLogined(state, isLogin) {
        state.isLogin = isLogin;
      },
      setUserInfo(state, userInfo) {
        state.isLogin = true;
        state.userInfo = userInfo;
      },
      logout(state) {
        state.isLogin = false;
        state.userInfo = null;
      },
    },
    actions: {
      async GET_MEMBER_INFO({ commit }, token) {
        console.log("호출");
        let decode = jwt_decode(token);
        await findById(
          decode.userid,
          (response) => {
            if (response.data.message === "success") {
              commit("setUserInfo", response.data.userInfo);
              console.log(this.userInfo);
              // router.push("/");
              // router.go(router.currentRoute);
            } else {
              console.log("유저 정보 없음!!");
            }
          },
          (error) => {
            console.log(error);
          }
        );
      },
      LOGOUT({ commit }) {
        console.log("로그아웃");
        commit("logout");
        localStorage.removeItem("access-token");
        // axios.defaults.headers.common["auth-token"] = undefined;
      },
  },
    
  modules: {
    boardStore,
    searchStore,
    reviewStore,
    }
  });