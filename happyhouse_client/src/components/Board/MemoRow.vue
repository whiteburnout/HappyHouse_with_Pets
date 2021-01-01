<template>
  <b-row class="mb-1" style="padding-top : 1%;">
    <b-col>
      <b-card border-variant="info" class="mb-2" no-body>
        <template>
          <b-row>
            <div class="text-left ml-3">
              <p><strong>{{ memo.id }}</strong> {{ memo.memotime }}</p>
            </div>
              <b-button variant="outline-primary" style="position: relative; width:10%; height:30%; left:50%;" @click="modify">수정</b-button>
              <b-button variant="outline-danger" style="position: relative; width:10%; height:30%; left:50%;" @click="remove">삭제</b-button>
          </b-row>
        </template>
        <b-card-body
          class="text-left"
          :class="{ active: show, disactive: !show }"
        >
          <div>
            {{ memo.comment }}
          </div>
        </b-card-body>
        <b-card-body
          class="text-left"
          :class="{ active: !show, disactive: show }"
        >
          <b-form-textarea rows="2" v-model="memo.comment"></b-form-textarea>
        </b-card-body>
      </b-card>
    </b-col>
  </b-row>
</template>

<script>
import { mapState } from 'vuex';
import {deleteMemo, modifyMemo} from '../../store/modules/memoStore'


export default {
  name: 'memorow',
  props: {
    memo: {},
  },

  data() {
    return {

      memono: '',
      show: true,
    };
  },

  computed: {
    ...mapState(['userInfo']),
  },

  methods: {
    remove() {
      if(this.userInfo == null)//로그인 여부
        alert("로그인이 필요한 서비스입니다.");
      else if (this.memo.id != this.userInfo.id) {//본인만 삭제가능
        alert('본인만 수정할 수 있습니다.');
      }
      else{
      deleteMemo(this.memo.memono,
        (response) => {
          console.log(response);
          this.$emit('update');//최신화
        },
        (error) => {
          console.log(error);
        }
      );
      }
    },
    modify() {
      if(this.userInfo == null)//로그인 여부
        alert("로그인이 필요한 서비스입니다.");
      else if (this.memo.id != this.userInfo.id) {//본인만 삭제가능
        alert('본인만 수정할 수 있습니다.');
      } else {
        if (!this.show) {
          modifyMemo(
            this.memo,
            (response) => {
              console.log(response);
              this.$emit('update');//최신화
            },
            (error) => {
              console.log(error);
            }
          );
        }
        this.show = !this.show;
      }
    },
  },
};
</script>

<style>
.active {
  display: inline;
}
.disactive {
  display: none;
}
</style>
