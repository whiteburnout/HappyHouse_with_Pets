import Vue from 'vue';
import VueRouter from 'vue-router'
import Main from '../components/Main.vue'
import Login from '../components/HHLogin.vue'
import Join from '../components/HHJoin.vue'
import Mypage from '../components/HHMypage.vue'
import Result from '../components/result/Result.vue'
//import Board from '../components/Board/Board.vue';
//import Insert from '../components/Board/Insert.vue';
import BoardRoute from './boardRouter';

Vue.use(VueRouter)

// const requireAuth = () => (to, from, next) => {//to:어디로 갈건지(me) from:어디서 왓는지(/)
//     const nextRoute = to.path;
  
//     if (store.getters.getAccessToken) {//로그인되어잇으면
//       return next();//다음 페이지
//     } else next("/login" + nextRoute);//아니면 로그인페이지
//   };

const routes = [
    {
        path: '/',
        name: 'Main',
        component: Main
    },
    {
        path: '/login',
        name: 'login',
        component: Login
    },
    {
        path: '/join',
        name: 'join',
        component: Join
    },
    {
        path: '/mypage',
        name: 'mypage',
        component: Mypage
    },
    {
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