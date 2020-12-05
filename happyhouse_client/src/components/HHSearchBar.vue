<template>
  <section id="searchbar">
    <b-container>
      <!-- Using components -->
      <b-row class="py-4">
        <b-col>
          <b-input-group>
            <b-form-select
              v-model="payload.selected"
              :options="options"
              id="select"
              style="max-width: 15%;"
            ></b-form-select>
            <b-form-input
              v-model="payload.keyword"
              placeholder="아파트 이름, 동으로 검색해보세요."
              @keypress.enter="search"
            ></b-form-input>
            <b-input-group-append style="width: 10%;">
              <b-button variant="success" @click.stop="search">검색</b-button>
            </b-input-group-append>
          </b-input-group>
        </b-col>
        <template v-if="getRoot()">
          <b-col cols="4">
            <b-button-group style="width: 70%">
              <b-button type="button" variant="danger" @click="getHospital"
                >동물병원</b-button
              >
              <b-button type="button" variant="secondary" @click="getPharmacy"
                >동물약국</b-button
              >
              <b-button type="button" variant="success" @click="getPark"
                >공원</b-button
              >
            </b-button-group>
          </b-col>
        </template>
      </b-row>
    </b-container>
  </section>
</template>

<script>
import { mapGetters } from 'vuex';

export default {
  data() {
    return {
      payload: {
        selected: '',
        keyword: '',
      },
      options: [
        { value: '', text: '아파트/동 선택', disabled: true },
        { value: 'apt', text: '아파트' },
        { value: 'dong', text: '동' },
      ],
      hospital: false,
      pharmacy: false,
      park: false,
    };
  },
  methods: {
    search() {
      if (!this.payload.selected) {
        alert('아파트/동을 선택해주세요.');
      } else if (!this.payload.keyword) {
        alert('검색어를 입력해주세요.');
      } else {
        this.$store.dispatch('SEARCH', this.payload);
        this.$router.push('/result');
      }
    },
    getRoot() {
      //console.log(window.location.pathname);
      if (window.location.pathname == '/result') return true;
      else return false;
    },
    getHospital() {
      if (this.hospital == false) {
        this.$store.dispatch('HOSPITAL', this.getHouse[0].dong);
        this.hospital = true;
        //this.$router.push('/result');
      } else {
        this.$store.commit('HOSPITALCLEAR');
        this.hospital = false;
      }
    },
    getPharmacy() {
      if (this.pharmacy == false) {
        this.$store.dispatch('PHARMACY', this.getHouse[0].dong);
        this.pharmacy = true;
        //this.$router.push('/result');
      } else {
        this.$store.commit('PHARMACYCLEAR');
        this.pharmacy = false;
      }
    },
    getPark() {
      if (this.park == false) {
        this.$store.dispatch('PARK', this.getHouse[0].dong);
        this.park = true;
        //this.$router.push('/result');
      } else {
        this.$store.commit('PARKCLEAR');
        this.park = false;
      }
    },
  },

  computed: {
    ...mapGetters(['getHouse']),
  },
};
</script>

<style>
#searchbar {
  background: #6abea7;
}
#select {
  width: 20px;
}
.b-input-group-append {
  width: 100px;
}

.btn {
  width: 100%;
}
</style>
