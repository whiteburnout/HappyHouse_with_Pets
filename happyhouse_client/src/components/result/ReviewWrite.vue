<template>
  <b-row class="mb-3 mt-2" style="padding:1%">
    <b-card class="mb-2" no-body style="width:100%;padding:2%;">
    <!-- <b-col cols="11"> -->
      <b-form-rating v-model="form.rating" color="#ff8800"></b-form-rating>
      <b-form-textarea
        placeholder="리뷰 입력"
        rows="2"
        v-model="form.content"
      ></b-form-textarea>
    <!-- </b-col> -->
    <b-button variant="dark" @click="onSubmit">등록</b-button>
    </b-card>
  </b-row>
</template>

<script>
import {mapState, mapActions} from "vuex";

const reviewStore = 'reviewStore';
export default {

  data() {
    return {
        form: {
        id : null,
        content : "",
        houseno : null,
        rating : 0,
      }
    }
  },

  props:{
    houseno:{},
  },

  computed:{
    ...mapState(["userInfo"])
  },

  methods: {
    ...mapActions(reviewStore, ['reviewRegister']),
    async onSubmit() {//등록

      if(this.userInfo == null)
      {
        alert("로그인이 필요한 서비스입니다.");
      }

      else{
      this.form.id = this.userInfo.id;
      this.form.houseno = this.houseno;
      console.log(this.form);
      await this.reviewRegister(this.form);
      this.$emit("update");//최신화
      this.form.content = "";
      }
    }, 
  },
};

</script>

<style></style>
