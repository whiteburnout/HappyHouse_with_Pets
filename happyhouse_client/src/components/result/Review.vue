<template>
<b-col
    id="review-box"
    class="m-2 p-1 border border-light rounded shadow"
    style="overflow:auto;"
  >
    <b-row class="pl-3 py-1" >
      <b-icon
        style="position:absolute; left : 3%"
        icon="arrow-left"
        scale="1"
        aria-hidden="true"
        variant="dark"
        @click="closereview"
      ></b-icon>
    </b-row>

    <b-row>
      <hr>
    </b-row>
    <!-- 리뷰창과 리뷰입력창 -->
    <review-row v-on:update="update" v-for="(review, index) in reviews" :key="index" :review="review"/>
    <review-write v-on:update="update" :houseno = "houseno" />
  </b-col>
</template>

<script>
import ReviewWrite from "./ReviewWrite.vue";
import ReviewRow from "./ReviewRow.vue";
import { mapActions, mapState } from 'vuex';

const reviewStore = 'reviewStore';

export default {

  props:{
    houseno:{},
  },

  components:{
    ReviewWrite,
    ReviewRow,
  },

  computed:{
    ...mapState(reviewStore, ['reviews'])
  },

  methods: {
    ...mapActions(reviewStore, ['reviewList']),

    update(){//review 변화(삭제, 수정, 신규)있으면 엑시오스 호출하여 리프레쉬
      console.log("update");
      this.reviewList(this.houseno);
    },
    closereview(){//리뷰창 닫기
      this.$emit("closereview");
    }
  },
}
</script>

<style>
#review-box{
  background: white;
  margin: 0px;
  position:absolute;
  right: 0%;
  height: 95%;
  width: 20%;
  z-index: 8;
}
</style>