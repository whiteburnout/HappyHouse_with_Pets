<template>
<div>
  <b-col
    id="detail-box"
    class="m-2 p-1 border border-light rounded shadow"
    style="width:20%;"
  >
    <b-row class="pl-3 py-1" style="padding:0px" >
      <!-- 아파트사진 -->
      <img :src="src" alt="Image" class="rounded-0" style="position:relative; width:95%"/>
      <!-- 창닫기 화살표 -->
      <img src="../../assets/left.png" style="width:7%;position:absolute; left : 2%;" @click="close()"/>
    </b-row>
    <b-row style="position: relative; left : 5%">
      {{getDeal[0].aptName}}
    </b-row>
    <b-row>
      <b-form-rating v-model="average" color="#ff8800" readonly style="width:50%; position: relative; left : 4%;"></b-form-rating>
    </b-row>
    <b-row>
      <hr>
    </b-row>
    <b-row style="position: relative; left : 5%">
      리뷰
      <b-col style="position: relative; font-size:50%; margin-top:3%;" @click="showreview">
      더보기
      <b-icon icon="chevron-right" variant="dark"  ></b-icon>
      </b-col>
    </b-row>
    <div v-if="reviews[0] != null">
      <b-row>
        <div style="position: relative; left : 4%; font-size: 1.2rem;">
          <p>{{reviews[0].id}}</p>
        </div>
        <div class="indent" style="position: relative; left : 4%; font-size: 0.8rem; padding:1.8%">
          <p>{{reviews[0].wdate}}</p>
        </div>
      </b-row>
      <b-row style="width:95%">
        <div class="indent" style="position: relative; left : 4%; font-size: 1rem;">
          <p>{{reviews[0].content}}</p>
        </div>
      </b-row>
    </div>
    <div v-else-if="reviews[0] == null">
      <b-row>
        <div style="position: relative; left : 4%; font-size: 1rem;">
          리뷰가 없습니다.
        </div>
      </b-row>
    </div>
    <b-row class="p-1 mx-auto" style="height:50%; font-size : 1rem">
      <div style="overflow-y:scroll; position: relative; height:75%; width:100%">
        <b-table striped hover :items="items" :fields="fields"></b-table>
      </div>
    </b-row>
  </b-col>
  </div>
</template>

<script>
import { mapGetters, mapState } from 'vuex';
const img = require('../../assets/apt.jpg');
const searchStore = 'searchStore';
const reviewStore = 'reviewStore';

export default {
  computed: {
    ...mapGetters(searchStore, ['getDeal']),
    ...mapState(reviewStore, ['reviews']),
  },
  watch : {
    getDeal: function(){//마커 바뀌면 거래정보 바뀌는 것을 watch
      this.items = [];
      var size = this.getDeal.length;
      let temp = size % 5;
      if(temp == 0)//아파트 이미지 5개 랜덤으로 사용
        this.src = require('../../assets/apt.jpg');
      else if(temp == 1)
        this.src = require('../../assets/apt1.jpg');
      else if(temp == 2)
        this.src = require('../../assets/apt2.jpg');
      else if(temp == 3)
        this.src = require('../../assets/apt3.jpg');
      else if(temp == 4)
        this.src = require('../../assets/apt4.jpg');

      for (let index = 0; index < size; index++) {

      var floor = this.getDeal[index].floor;
      var area =  parseFloat(this.getDeal[index].area).toFixed(3);
      var dealAmount = this.getDeal[index].dealAmount;

      this.items[index] = {
        층 : floor + "층",
        면적 : area + "㎡",
        금액: dealAmount + "만원",
      }
    }
    console.log(this.items);
    },
    reviews: function(){//마커 바뀌면 평균 평점 구하기
      var size = this.reviews.length;
      var sum = 0;
      for (let index = 0; index < size; index++) {
        sum += Number(this.reviews[index].rating);
      }
      this.average = parseFloat(sum/size);
    }
  },
  data() {
    return {
      src : img,
      average: 0,
      review : false,
      detail: true,
      items: [],
      fields: ['층', '면적', '금액'],
    };
  },
  methods: {
    showreview(){
      this.$emit("showreview");
    },
    close(){
      this.$emit("close");
    },
  },
};
</script>

<style>
#detail-box {
  background: white;
  position:absolute;
  right: 0%;
  height: 95%;
  width: 20%;
  z-index: 8;
}
/* 들여쓰기 */
.indent{
  text-indent: 0.5em;
}
</style>
