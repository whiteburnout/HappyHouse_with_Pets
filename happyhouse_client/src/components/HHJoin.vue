<template>
<div>
    <HHHeader/>
    <b-container class="bv-example-row">
    <b-row>
      <b-col></b-col>
      <b-col cols="8">
        <b-card
          class="mt-3"
          header="회원가입"
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
            @blur="idcheck()"
            autocomplete="off"
          ></b-form-input>
          <!-- 아이디 중복여부 코맨트 -->
          <div v-html="idcomment"></div>
        </b-form-group>
        <b-form-group label="비밀번호" label-for="userpwd">
          <b-form-input
            type="password"
            v-model="user.pass"
            required
            placeholder="비밀번호 입력"
            @blur="passcheck()"
          ></b-form-input>
        </b-form-group>
          <b-form-group label="비밀번호 확인" label-for="pwdcheck">
          <b-form-input
            type="password"
             v-model="passsecond"
            required
            placeholder="비밀번호 확인"
            @blur="passcheck()"
          ></b-form-input>
          <!-- 비밀번호 일치여부 코맨트 -->
          <div v-html="passcomment"></div>
          </b-form-group>
          <b-form-group label="이메일" label-for="email">
          <b-form-input
            type="email"
             v-model="user.email"
            required
            placeholder="이메일 입력"
          ></b-form-input>
        </b-form-group>
        <b-button type="button" variant="primary" class="m-1" @click="onSubmit"
          >회원가입</b-button
        >
        <b-button type="button" variant="success" class="m-1" router-link to ='/'
          >돌아가기</b-button
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
import axios from 'axios';
import { Join } from "../store/modules/userStore";

const SERVER_URL = process.env.VUE_APP_SERVER_URL;

Vue.use(VueRouter);


export default {

  data() {
    return {
      user:{id : "", pass : "", email : ""},
      passsecond:"",//비밀번호 체크 변수(2번째)
      idcomment:"",//아이디 중복여부 알려주는 코맨트
      passcomment:"",//비밀번호 일치여부 알려주는 코맨트
      idconfirm: false,//중복여부 확인되면 true
      passconfirm: false,//일치여부 확인되면 true
    }
  },

  components:{
    HHHeader,
    HHFooter,
  },

  methods:{
    onSubmit(){//가입
      if(this.idconfirm && this.passconfirm){//비밀번호와 아이디가 확인되었을때
        if(this.user.email == ""){//이메일 비었을때
        alert("이메일을 입력하세요.");
        }
        else{
        Join(this.user);
        this.$router.replace('/');
        }
      }
      else{
        alert("아이디와 비밀번호를 확인하세요.")
      }
    },
    idcheck(){//아이디 중복여부 확인하는 비동기통신 엑시오스
      axios
      .get((`${SERVER_URL}user/check/` + this.user.id))
      .then(res =>(this.result(res.data)))//성공시 result함수 호출
      .catch(exp => console.log(exp))
    },
    passcheck(){//비밀번호 일치여부 확인하는 함수
      if(this.user.pass == this.passsecond && this.user.pass != "" && this.passsecond != "")
      {
        this.passcomment = "<p style = \"color:green; font-size: 16px;\">비밀번호가 일치합니다.</p>"
        this.passconfirm = true;  
      }
      else if(this.user.pass != "" && this.passsecond != "")
      {
        this.passcomment = "<p style = \"color:red; font-size: 16px;\">비밀번호가 일치하지 않습니다.</p>"
        this.passconfirm = false;  
      }
      else if(this.user.pass == "" || this.passsecond == "")
      {
        this.passcomment = ""
        this.passconfirm = false;  
      }
    },

    result(data){//비동기통신 값(true, false)에 따라서 해당 코맨트를 띄워준다.
      if(!data)
      {
        this.idcomment = "<p style = \"color:green; font-size: 16px;\">사용 가능한 아이디입니다.</p>"
        this.idconfirm = true;
      }
      else
      {
        this.idcomment = "<p style = \"color:red; font-size: 16px;\">중복된 아이디입니다.</p>"
        this.idconfirm = false;
      }
    }
  }


}
</script>

<style>

</style>