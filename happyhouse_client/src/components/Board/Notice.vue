<template>
  <div>
    <HHHeader></HHHeader>
    <b-container>
      <h1 class="my-5">공지사항</h1>
      <hr class="mb-5" />
      <template v-if="getItems.length == 0"
        ><h6>게시글이 없습니다.</h6></template
      >
      <template v-else>
        <b-table
          id="notice"
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
        <!-- 페이지 처리 -->
        <b-pagination
          v-model="currentPage"
          :total-rows="getItems.length"
          :per-page="perPage"
          aria-controls="notice"
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
import { mapGetters, mapActions, mapState  } from 'vuex';

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
    this.reqNoticeList();
  },
  computed: {
    ...mapState(['userInfo']),
    ...mapGetters(boardStore, ['getItems']),
  },
  methods: {
    ...mapActions(boardStore, ['reqNoticeList']),
    itemHandler(value) {
      this.$router.push({ name: 'item', params: { no: value.no } });
    },
    write(){
      if(this.userInfo == null || this.userInfo.id != 'admin')//관리자만 작성 가능
        alert("관리자만 작성 가능합니다.");
      else
       this.$router.push({ name: 'insert' });//관리자면 insert로 이동
    }
  },
  components: {
    HHHeader,
    HHFooter,
  },
};
</script>

<style></style>
