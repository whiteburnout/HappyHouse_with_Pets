import Vue from 'vue';
import VueRouter from 'vue-router'
import Main from '../components/Main.vue'
import Login from '../components/HHLogin.vue'
import Join from '../components/HHJoin.vue'
import Mypage from '../components/HHMypage.vue'
import Result from '../components/result/Result.vue'
import BoardRoute from './boardRouter';

Vue.use(VueRouter)

// const requireAuth = () => (to, from, next) => {//to:어디로 갈건지(me) from:어디서 왓는지(/)
//     const nextRoute = to.path;
  
//     if (store.getters.getAccessToken) {//로그인되어잇으면
//       return next();//다음 페이지
//     } else next("/login" + nextRoute);//아니면 로그인페이지
//   };

// const onlyAuthUser = async (to, from, next) => {
//     let token = localStorage.getItem("access-token");
//     if (store.state.userInfo == null && token) {
//       await store.dispatch("GET_MEMBER_INFO", token);
//     }
//     if (store.state.userInfo === null) {
//       alert("로그인이 필요한 페이지입니다..");
//       // next({ name: "login" });
//       router.push({ name: "login" });
//     } else {
//       next();
//     }
//   };

const routes = [
    {//메인
        path: '/',
        name: 'Main',
        component: Main
    },
    {//로그인
        path: '/login',
        name: 'login',
        component: Login
    },
    {//회원가입
        path: '/join',
        name: 'join',
        component: Join
    },
    {//내정보
        path: '/mypage',
        name: 'mypage',
        component: Mypage
    },
    {//검색결과
        path: '/result',
        name: 'result',
        component: Result
    },
].concat(BoardRoute);

const router = new VueRouter({
    mode: 'history',
    routes
})

export default router