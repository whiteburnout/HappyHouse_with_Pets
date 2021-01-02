<template>
  <b-row class="mb-1" style="font-size :1rem;">
    <b-col>
      <b-card class="mb-2" no-body style="width:100%;">
        <template>
          <b-row>
              <div style="position: relative; left : 4%; font-size: 1.1rem;">
                <p><strong>{{review.id}}</strong></p>
              </div>
              <div style=" display:inline;position: relative; left : 4%; font-size: 0.8rem; padding:1%">
                {{review.wdate}}
                <b-form-rating v-model="review.rating" color="#ff8800" readonly style="position: relative; left:-30%"></b-form-rating>
              </div>
              <b-button variant="outline-primary" style="position: absolute; width:15%; height:15%;left:70%; padding:0%;" @click="modify">수정</b-button>
              <b-button variant="outline-danger" style="position: absolute; width:15%; height:15%;left:85%; padding:0%;" @click="remove">삭제</b-button>
          </b-row>
        </template>
        <!-- 보여주기창 -->
        <b-card-body
          class="text-left"
          :class="{ active: show, disactive: !show }"
        >
          <div>
            {{review.content}}
          </div>
        </b-card-body>
        <!-- 수정창 -->
        <b-card-body
          class="text-left"
          :class="{ active: !show, disactive: show }"
        >
        <b-form-rating v-model="review.rating" color="#ff8800" style="width:50%; position: relative;"></b-form-rating>
          <b-form-textarea rows="2" v-model="review.content"></b-form-textarea>
        </b-card-body>
      </b-card>
    </b-col>
  </b-row>
</template>

<script>
import {mapActions, mapState} from 'vuex';
const reviewStore = 'reviewStore';

export default {
  name: 'app',
  props: {
    review: {},
  },

  data() {
    return {
      show: true,
    };
  },

  computed: {
    ...mapState(['userInfo']),
  },

  methods: {
  ...mapActions(reviewStore, ['reviewDelete', 'reviewModify']),
    async remove() {//삭제
      if(this.userInfo== null)
      {
        alert("로그인을 해주세요.");
      }
      else if(this.userInfo.id != this.review.id)
      {
        alert("본인만 삭제할 수 있습니다.");
      }
      else
      {
        console.log("remove");
        await this.reviewDelete(this.review.no);
        this.$emit('update');//최신화
      }
    },
    async modify() {//수정

      if(this.userInfo== null)
      {
        alert("로그인을 해주세요.");
      }
      else if (this.userInfo.id != this.review.id) {
        alert('본인만 수정할 수 있습니다.');
      } else {
        if (!this.show) {
          await this.reviewModify(this.review);
        }

        this.show = !this.show;
      }
    },
  },
};
</script>

<style>
.indent{
  text-indent: 0.5em;
}
.active {
  display: inline;
}
.disactive {
  display: none;
}
</style>
