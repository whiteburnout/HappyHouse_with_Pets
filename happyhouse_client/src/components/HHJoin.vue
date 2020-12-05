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
          ></b-form-input>
          <div v-html="idcoment"></div>
          <!-- <b-button type="button" variant="primary" class="m-1" @click="join"
          >중복확인</b-button> -->
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
          <div v-html="passcoment"></div>
          </b-form-group>
          <b-form-group label="이메일" label-for="email">
          <b-form-input
            type="email"
             v-model="user.email"
            required
            placeholder="이메일 입력"
          ></b-form-input>
        </b-form-group>
        <b-button type="button" variant="primary" class="m-1" @click="join"
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

const SERVER_URL = process.env.VUE_APP_SERVER_URL;

Vue.use(VueRouter);


export default {

  data() {
    return {
      user:{id : "", pass : "", email : ""},
      passsecond:"",
      idcoment:"",
      passcoment:"",
      idconfirm: false,
      passconfirm: false,
    }
  },

  components:{
    HHHeader,
    HHFooter,
  },

  methods:{
    join(){
      
      if(this.idconfirm && this.passconfirm){
        if(this.user.email == ""){
        alert("이메일을 입력하세요.");
        }
        else{
        this.$store.dispatch("JOIN", this.user);
        this.$router.replace('/');
        }
      }
      else{
        alert("아이디와 비밀번호를 확인하세요.")
      }
    },
    idcheck(){
      axios
      .get((`${SERVER_URL}user/check/` + this.user.id))
      .then(res =>(this.result(res.data)))
      .catch(exp => console.log(exp))
    },
    passcheck(){
      if(this.user.pass == this.passsecond && this.user.pass != "" && this.passsecond != "")
      {
        this.passcoment = "<p style = \"color:green; font-size: 16px;\">비밀번호가 일치합니다.</p>"
        this.passconfirm = true;  
      }
      else if(this.user.pass != "" && this.passsecond != "")
      {
        this.passcoment = "<p style = \"color:red; font-size: 16px;\">비밀번호가 일치하지 않습니다.</p>"
        this.passconfirm = false;  
      }
      else if(this.user.pass == "" || this.passsecond == "")
      {
        this.passcoment = ""
        this.passconfirm = false;  
      }
    },

    result(data){
      if(!data)
      {
        this.idcoment = "<p style = \"color:green; font-size: 16px;\">사용 가능한 아이디입니다.</p>"
        this.idconfirm = true;
      }
      else
      {
        this.idcoment = "<p style = \"color:red; font-size: 16px;\">중복된 아이디입니다.</p>"
        this.idconfirm = false;
      }
    }
  }


}
</script>

<style>

</style>