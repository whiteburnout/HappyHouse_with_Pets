<template>
  <div>
    <b-navbar type="dark" style="height : 100%">
      <!-- logo -->
      <b-navbar-brand router-link to="/">
        <img src="../assets/logo.png" alt="Kitten" class="pl-4" id="logo-img" />
      </b-navbar-brand>

      <!-- items -->
      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

      <!-- 로그인 유무에 따라서 화면구성 -->
      <b-collapse id="nav-collapse" is-nav>
        <b-navbar-nav class="ml-auto" v-if="userInfo != null">
          <b-nav-item @click="onClickLogout">로그아웃</b-nav-item>
          <b-nav-item router-link to="/mypage">내정보</b-nav-item>
          <b-nav-item router-link to="/board/notice">공지사항</b-nav-item>
          <b-nav-item router-link to="/board/general">자유게시판</b-nav-item>
        </b-navbar-nav>
        <b-navbar-nav class="ml-auto" v-else>
          <b-nav-item router-link to="/login">로그인</b-nav-item>
          <b-nav-item router-link to="/board/notice">공지사항</b-nav-item>
          <b-nav-item router-link to="/board/general">자유게시판</b-nav-item>
        </b-navbar-nav>
      </b-collapse>
    </b-navbar>
  </div>
</template>

<script>
import { mapState } from 'vuex';

export default {
  computed: {
    ...mapState(['userInfo']),
  },
  methods: {
    // 로그아웃
    onClickLogout() {
      this.$store
        .dispatch('LOGOUT')
        .then(() => {this.$router.replace("/")})
        .catch(() => {});
    },
  },
};
</script>

<style>
#logo-img {
  width: 110px;
  height: 100px;
}
.navbar {
  background-color: #6abea7;
}
</style>
