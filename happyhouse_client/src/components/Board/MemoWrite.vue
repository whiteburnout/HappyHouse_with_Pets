<template>
  <b-row class="mb-3 mt-2">
    <b-col cols="11">
      <b-form-textarea
        id="content"
        placeholder="댓글 입력..."
        rows="2"
        v-model="form.comment"
      ></b-form-textarea>
    </b-col>
    <b-col><b-button variant="dark" @click="onSubmit">등록</b-button> </b-col>
  </b-row>
</template>

<script>
import {mapState} from "vuex";
import {registerMemo} from "../../store/modules/memoStore"

export default {
  name: "memowrite",

  data() {
    return {
        form: {
        boardno: 0,
        comment : "",
        id : "",
      }
    }
  },

  computed:{
    ...mapState(["userInfo"])
  },

  methods: {
    onSubmit() {
      if(this.userInfo == null)//로그인 여부
        alert("로그인이 필요한 서비스입니다.");
      else{
        this.form.id = this.userInfo.id;
        this.form.boardno = this.$route.params.no;
        registerMemo(
            this.form,
          (response) => {
            console.log(response);
            this.$emit('update');//최신화
            this.form.comment = "";
          },
          (error) => {
            console.log(error);
          }
        );
      }
    }, 
  },
};

</script>

<style></style>
