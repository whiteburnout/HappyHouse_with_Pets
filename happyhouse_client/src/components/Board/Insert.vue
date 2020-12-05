<template>
  <div>
    <HHHeader></HHHeader>
    <b-container>
      <h1 class="my-5">글 작성</h1>
      <hr class="mb-5" />
      <b-form-group
        id="title-group"
        label="title"
        label-for="title"
        description="insert title"
      >
        <b-form-input
          id="title"
          v-model="title"
          required
          placeholder="title"
        ></b-form-input>
      </b-form-group>

      <b-form-group id="input-content" label="content" label-for="content">
        <b-form-textarea
          id="content"
          v-model="content"
          :state="content.length > 0"
          placeholder="content"
          rows="8"
        ></b-form-textarea>
      </b-form-group>

      <b-row>
        <b-col
          ><b-button
            type="submit"
            variant="primary"
            @click="onSubmit"
            :style="{ width: '100%' }"
            >Submit</b-button
          ></b-col
        >
        <b-col
          ><b-button type="reset" variant="danger" :style="{ width: '100%' }"
            >Reset</b-button
          ></b-col
        >
      </b-row>
    </b-container>
    <HHFooter></HHFooter>
  </div>
</template>

<script>
import HHHeader from '../HHHeader';
import HHFooter from '../HHFooter';
import { mapActions, mapGetters } from 'vuex';

const boardStore = 'boardStore';

export default {
  data() {
    return {
      title: '',
      content: '',
    };
  },
  computed: {
    ...mapGetters(['getUserNo', 'getUserId']),
  },
  methods: {
    ...mapActions(boardStore, ['reqInsertList']),
    onSubmit() {
      console.log(this.getUserNo);
      let result = this.reqInsertList({
        title: this.title,
        content: this.content,
        admin: this.getUserId == 'admin' ? 0 : 1,
        id: this.getUserNo,
      });
      if (result == 0) {
        alert('게시글이 등록되지 않았습니다.');
      } else {
        alert('게시글이 등록되었습니다.');
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

<style></style>
