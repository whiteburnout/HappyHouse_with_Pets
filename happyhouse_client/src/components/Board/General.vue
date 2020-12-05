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
      <b-row align-h="end" v-show="getAccessToken">
        <b-button variant="outline-primary" router-link :to="{ name: 'insert' }"
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
import { mapGetters, mapActions } from 'vuex';

const boardStore = 'boardStore';

export default {
  data() {
    return {
      perPage: 10,
      currentPage: 1,
      fields: [
        'index',
        { key: 'title', label: '제목' },
        { key: 'wdate', label: '작성일' },
        { key: 'count', label: '조회수' },
      ],
    };
  },
  created() {
    this.reqGeneralList();
  },
  computed: {
    ...mapGetters(['getAccessToken']),
    ...mapGetters(boardStore, ['getItems']),
  },
  methods: {
    ...mapActions(boardStore, ['reqGeneralList']),
    itemHandler(value) {
      this.$router.push({ name: 'item', params: { no: value.no } });
    },
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
