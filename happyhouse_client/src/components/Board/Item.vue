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
      <b-row
        align-h="end"
        v-if="
          (getItem.admin === '1' && getUserNo == getItem.id) ||
            getUserId == 'admin'
        "
      >
        <b-col cols="1"
          ><b-button variant="warning" router-link :to="{ name: 'modify' }"
            >수정</b-button
          ></b-col
        >
        <b-col cols="1"
          ><b-button variant="danger" @click="onDelete">삭제</b-button></b-col
        >
      </b-row>
    </b-container>
    <HHFooter></HHFooter>
  </div>
</template>

<script>
import HHHeader from '../HHHeader';
import HHFooter from '../HHFooter';
import { mapGetters, mapActions } from 'vuex';

const boardStore = 'boardStore';

export default {
  created() {
    this.reqItem(this.$route.params.no);
  },
  computed: {
    ...mapGetters(['getUserNo', 'getUserId']),
    ...mapGetters(boardStore, ['getItem']),
  },
  methods: {
    ...mapActions(boardStore, ['reqItem', 'reqDelete']),
    onDelete() {
      let result = this.reqDelete(this.getItem.no);
      if (result == 0) {
        alert('게시글이 삭제되지 않았습니다.');
      } else {
        alert('게시글이 삭제되었습니다.');
        this.$router.go(-1);
      }
    },
  },
  components: {
    HHHeader,
    HHFooter,
  },
};
</script>

<style>
.col,
pre {
  text-align: left;
}
</style>
