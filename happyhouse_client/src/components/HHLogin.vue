<template>
<div>
    <HHHeader/>
    <b-container class="bv-example-row">
    <b-row>
      <b-col></b-col>
      <b-col cols="8">
        <b-card
          class="mt-3"
          header="로그인"
          style="max-width: 40rem;"
          align="left"
        >
      <b-form class="login">
        <b-form-group label="아이디" label-for="userid">
          <b-form-input
            id="userid"
            v-model="user.id"
            required
            placeholder="아이디 입력"
          ></b-form-input>
        </b-form-group>
        <b-form-group label="비밀번호" label-for="userpwd">
          <b-form-input
            type="password"
            id="userpwd"
            v-model="user.pass"
            required
            placeholder="비밀번호 입력"
            @keypress.enter="onSubmit"
          ></b-form-input>
        </b-form-group>
        <b-button type="button" variant="primary" class="m-1" @click="onSubmit"
          >로그인</b-button
        >
        <b-button type="button" variant="success" class="m-1" router-link to ='/join'
          >회원가입</b-button
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
import { login } from "../store/modules/userStore";

Vue.use(VueRouter);


export default {

  data: function() {
  return {
    user: {
      id:"",
      pass:""
    },
    isLoginError : null,
  };
},

computed: {
    nextRoute() {
      return this.$route.params.nextRoute ? this.$route.params.nextRoute : "";
    }
  },

  methods: {
    onSubmit() {
      // LOGIN 액션 실행
      // 서버와 통신(axios)을 해 토큰값을 얻어야 하므로 Actions를 호출.
      // this.$store
      //   .dispatch("LOGIN", this.user)
      //   .then(() => this.$router.replace(`/${this.nextRoute}`))
      //   .catch(({ message }) => (this.msg = message));
      localStorage.setItem("access-token", "");
      login(//로그인
        this.user,
        (response) => {
          if (response.data.message === "success") {
            let token = response.data["access-token"];
            this.$store.commit("setIsLogined", true);
            localStorage.setItem("access-token", token);

            this.$store.dispatch("GET_MEMBER_INFO", token);
            console.log("성공");
            this.$router.replace(`/${this.nextRoute}`);
          } else {
            this.isLoginError = true;
            alert("존재하지 않는 회원입니다.");
            console.log("실패");
          }
        },
        (error) => {
          console.error(error);
          alert("아이디 또는 비밀번호가 일치하지 않습니다.");
        }
      );
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