<template>
  <div>
    <HHHeader></HHHeader>
    <b-container>
      <h1 class="my-5">자유게시판</h1>
      <hr class="mb-5" />
      <template v-if="getItems.length == 0"
        ><h6>게시글이 없습니다.</h6></template
      >
      <template v-else>
        <b-table
          id="general"
          :items="getItems"
          :fields="fields"
          :per-page="perPage"
          :current-page="currentPage"
          @row-clicked="itemHandler"
        >
          <template #cell(index)="data">
            {{ getItems.length - data.index }}
          </template>
        </b-table>
        <!-- 페이지처리 -->
        <b-pagination
          v-model="currentPage"
          :total-rows="getItems.length"
          :per-page="perPage"
          aria-controls="general"
          align="center"
          class="my-5"
          pills
        ></b-pagination>
      </template>
      <b-row align-h="end">
        <b-button style="width:10%" variant="outline-primary" @click="write"
          >글쓰기</b-button
        >
      </b-row>
    </b-container>
    <HHFooter></HHFooter>
  </div>
</template>

<script>
import HHHeader from '../HHHeader';
import HHFooter from '../HHFooter';
import { mapGetters, mapActions, mapState } from 'vuex';

const boardStore = 'boardStore';

export default {
  data() {
    return {
      perPage: 10,
      currentPage: 1,
      fields: [
        { key: 'title', label: '제목' },
        { key: 'id', label: '작성자'},
        { key: 'wdate', label: '작성일' },
        { key: 'count', label: '조회수' },
      ],
    };
  },
  created() {
    this.reqGeneralList();
  },
  computed: {
    ...mapGetters(boardStore, ['getItems']),
    ...mapState(['userInfo']),
  },
  methods: {
    ...mapActions(boardStore, ['reqGeneralList']),
    itemHandler(value) {//게시글 클릭 시 세부내용으로
      this.$router.push({ name: 'item', params: { no: value.no } });
    },
    write(){
      if(this.userInfo == null)//로그인 유무 확인
      {
        alert("로그인이 필요한 서비스입니다.");
      }
      else
      {
        this.$router.push({ name: 'insert' });
      }
    }
  },
  components: {
    HHHeader,
    HHFooter,
  },
};
</script>

<style>
h1 {
  text-align: center;
}
</style>
