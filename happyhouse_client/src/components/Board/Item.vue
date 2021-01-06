<template>
  <div>
    <HHHeader></HHHeader>
    <b-container class="my-5">
      <b-row>
        <b-col
          ><h2>{{ getItem.title }}</h2></b-col
        >
        <b-col cols="2"
          ><h3>
            <small>{{ getItem.id }}</small>
          </h3></b-col
        >
        <b-col cols="2"
          ><h3>
            <small>{{ getItem.wdate }}</small>
          </h3></b-col
        >
        <b-col cols="2"
          ><h3>
            <small>조회수 {{ getItem.count }}</small>
          </h3></b-col
        >
      </b-row>
      <hr class="mb-5" />
      <pre>{{ getItem.content }}</pre>
      <b-row align-h="end" style="padding:5%;">
        <b-col cols="1">
          <b-button variant="primary" @click = "modify">
            수정
          </b-button>
        </b-col>
        <b-col cols="1">
          <b-button variant="danger" @click= "onDelete">
            삭제
          </b-button>
        </b-col>
      </b-row>
        <memo-write v-on:update="update"></memo-write>
        <memo-row v-on:update="update" v-for="(memo, index) in memos" :key="index" :memo="memo"></memo-row>
    </b-container>
    <HHFooter></HHFooter>
  </div>
</template>

<script>
import HHHeader from '../HHHeader';
import HHFooter from '../HHFooter';
import MemoRow from '../Board/MemoRow';
import MemoWrite from '../Board/MemoWrite'
import {listMemo} from '../../store/modules/memoStore'
import { mapGetters, mapActions, mapState } from 'vuex';

const boardStore = 'boardStore';

export default {
  data() {
    return {
      memos:[],
    }
  },
  created() {
    this.reqItem(this.$route.params.no);//조회수 증가
    listMemo(
      this.$route.params.no,
      (response) => {
        console.log("성공");
        console.log(response.data);
        this.memos = response.data;
        console.log(this.memos);
      },
      (error) => {
        console.log(error);
      }
    );
  },
  computed: {
    ...mapGetters(['getUserNo', 'getUserId']),
    ...mapGetters(boardStore, ['getItem']),
    ...mapState(['userInfo']),
  },
  methods: {
    ...mapActions(boardStore, ['reqItem', 'reqDelete']),
    onDelete() {
      if(this.userInfo == null)//로그인 여부
        alert("로그인이 필요한 서비스입니다.");

      //본인과 관리자만 삭제가능
      else if(this.getItem.id == this.userInfo.id || this.userInfo.id == 'admin')
      {
      let result = this.reqDelete(this.getItem.no);
      if (result == 0) {
          alert('게시글이 삭제되지 않았습니다.');
      } else {
          alert('게시글이 삭제되었습니다.');
          this.$router.go(-1);
      }
      }
      else
      {
        alert("본인만 삭제할 수 있습니다.");
      }
    },
    modify(){
      if(this.userInfo == null)//로그인 여부
        alert("로그인이 필요한 서비스입니다.");
      else if(this.getItem.id == this.userInfo.id || this.userInfo.id == 'admin')
      {//본인과 관리자만 수정가능
        this.$router.push({ name: 'modify' });
      }
      else
      {
        alert("본인만 수정할 수 있습니다.");
      }
    },
    update(){// row와 write에서 이벤트 발생시 최신화
      listMemo(
        this.$route.params.no,
        (response) => {
          console.log("업뎃");
          this.memos = response.data;
        },
        (error) => {
          console.log(error);
        }
      );
    }
  },
  components: {
    HHHeader,
    HHFooter,
    MemoRow,
    MemoWrite
  },
};
</script>

<style>
.col,
pre {
  text-align: left;
}
</style>
