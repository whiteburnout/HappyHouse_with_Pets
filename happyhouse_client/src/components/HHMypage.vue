<template>
<div>
  <HHHeader/>
    <b-container class="bv-example-row">
    <b-row>
      <b-col></b-col>
      <b-col cols="8">
        <b-card
          class="mt-3"
          header="내 정보"
          style="max-width: 40rem;"
          align="left"
        >
      <b-form class="login" >
        <b-form-group label="아이디" label-for="userid">
          <b-form-input
            id="id"
            v-model="user.id"
            required
            readonly
          ></b-form-input>
        </b-form-group>
        <b-form-group label="비밀번호" label-for="userpwd">
          <b-form-input
            type="password"
            id="pass"
            v-model="user.pass"
            required
          ></b-form-input>
        </b-form-group>
          <b-form-group label="비밀번호 확인" label-for="pwdcheck">
          <b-form-input
            type="password"
            id="pwdcheck"
            v-model="user.pass"
            required
          ></b-form-input>
          </b-form-group>
          <b-form-group label="이메일" label-for="email">
          <b-form-input
            id="email"
            v-model="user.email"
            required
          ></b-form-input>
        </b-form-group>
        <b-button type="button" variant="primary" class="m-1" @click="onSubmit"
          >정보수정</b-button
        >
        <b-button type="button" variant="success" class="m-1" @click="remove"
          >회원탈퇴</b-button
        >
      </b-form>
      </b-card>
      </b-col>
      <b-col></b-col>
    </b-row>
  </b-container>
  <HHFooter/>
  </div>
</template>

<script>
import HHHeader from './HHHeader.vue'
import HHFooter from './HHFooter.vue'
import Vue from "vue";
import VueRouter from "vue-router";
import { mapState } from "vuex";
import { Delete, Modify, login } from "../store/modules/userStore";

Vue.use(VueRouter);


export default {

  created() {//생성할때 자동완성
    this.user.id = this.userInfo.id;
    this.user.pass = this.userInfo.pass;
    this.user.email = this.userInfo.email;
  },

  watch:{
    userInfo: function(){
      this.user.id = this.userInfo.id;
      this.user.pass = this.userInfo.pass;
      this.user.email = this.userInfo.email;
    }
  },
  data() {
    return {
      user: {id : null, pass : null, email : null}
    };
  },
  computed: {
    ...mapState(["userInfo"])
  },
  methods: {
    onSubmit(){//회원정보 수정 수정 후에 토큰정보 변경을 위해 로그아웃 후 로그인
      Modify(this.user);
      this.$store
      .dispatch('LOGOUT')
      .then(() => {
        login(
        this.user,
        (response) => {
          if (response.data.message === "success") {
            let token = response.data["access-token"];
            this.$store.commit("setIsLogined", true);
            localStorage.setItem("access-token", token);

            this.$store.dispatch("GET_MEMBER_INFO", token);
            console.log("성공");
            this.$router.replace(`/`)
          } else {
            this.isLoginError = true;
            console.log("실패");
          }
        },
        (error) => {
          console.error(error);
          alert("아이디 또는 비밀번호가 일치하지 않습니다.");
        }
       );})
      .catch(() => {});
    },

    remove(){//회원탈퇴
      Delete(this.user.id)
      this.$store
        .dispatch('LOGOUT')
        .then(() => {this.$router.replace("/")})
        .catch(() => {});
      this.$router.replace(`/`)
    }
  },

  components:{
    HHHeader,
    HHFooter,
  }
}
</script>

<style>

</style>