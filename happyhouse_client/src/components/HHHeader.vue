<template>
  <div>
    <b-navbar type="dark">
      <!-- logo -->
      <b-navbar-brand router-link to="/">
        <img src="../assets/logo.png" alt="Kitten" class="pl-4" id="logo-img" />
      </b-navbar-brand>

      <!-- items -->
      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

      <b-collapse id="nav-collapse" is-nav>
        <b-navbar-nav class="ml-auto" v-if="getAccessToken">
          <b-nav-item router-link to="">로그아웃</b-nav-item>
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
import { mapGetters } from 'vuex';

export default {
  computed: {
    ...mapGetters(['getAccessToken', 'getUserId', 'getUserName']),
  },
  methods: {
    onClickLogout() {
      this.$store
        .dispatch('LOGOUT')
        .then(() => this.$router.replace('/').catch(() => {}));
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
