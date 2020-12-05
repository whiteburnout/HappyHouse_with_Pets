<template>
  <div class="px-auto" style="position:relative; height:800px; overflow-y:auto">
    <h3><b-icon icon="camera"></b-icon> 거래 정보</h3>
    <b-card-group deck v-for="(house, index) in getDeal" :key="index">
      <b-card
        no-body
        class="overflow-hidden"
        style="max-width: 540px;"
        @click="modal(index)"
      >
        <b-row no-gutters>
          <b-col md="6">
            <b-card-img :src="src" alt="Image" class="rounded-0"></b-card-img>
          </b-col>
          <b-col md="6">
            <b-card-body :title="house.aptName">
              <b-card-text>
                {{ house.dong }}
                {{ house.area }}㎡<br />
                {{ house.floor }}층
              </b-card-text>
            </b-card-body>
          </b-col>
        </b-row>
      </b-card>
    </b-card-group>

    <b-modal id="modal-center" v-model="modalShow" centered :title="apt">
      <p class="my-4">가격 : {{ dealAmount }}만원</p>
      <p class="my-4">건축연도 : {{ buildYear }}년</p>
      <p class="my-4">
        거래일 : {{ dealYear }}년 {{ dealMonth }}월 {{ dealDay }}일
      </p>
      <p class="my-4">면적 : {{ area }}㎡</p>
      <p class="my-4">층 : {{ floor }}층</p>
    </b-modal>
  </div>
</template>

<script>
import { mapGetters } from 'vuex';
const img = require('../../assets/apt.jpg');

export default {
  data() {
    return {
      src: img,
      modalShow: false,
      apt: '',
      dealAmount: '',
      buildYear: '',
      dealYear: '',
      dealMonth: '',
      dealDay: '',
      area: '',
      floor: '',
    };
  },

  methods: {
    modal(index) {
      this.modalShow = !this.modalShow;
      this.apt = this.getDeal[index].aptName;
      this.dealAmount = this.getDeal[index].dealAmount;
      this.buildYear = this.getDeal[index].buildYear;
      this.dealYear = this.getDeal[index].dealYear;
      this.dealMonth = this.getDeal[index].dealMonth;
      this.dealDay = this.getDeal[index].dealDay;
      this.area = this.getDeal[index].area;
      this.floor = this.getDeal[index].floor;
    },
  },

  computed: {
    ...mapGetters(['getDeal']),
  },
};
</script>

<style>
.card-title {
  color: black;
}
</style>
