<template>
  <b-container
    id="search-box"
    class="m-2 p-1 position-absolute border border-light rounded shadow"
  >
    <b-row align-v="center" class="pl-3 py-1">
      <div class="mouseover-cursor pr-3">
      </div>
    </b-row>
    <b-row class="p-1 mx-auto">
      <b-col style="padding:0px">
      <b-input-group>
        <b-form-input
            v-model="payload.keyword"
            id="search-input"
            type="search"
            class="form-control form-control"
            placeholder="아파트 이름, 동으로 검색해보세요."
            @keypress.enter="search"
            @keyup="autocomplete"
            autocomplete="off"
        ></b-form-input>
        <span>
          <b-button id="search-apt" variant="primary" @click="search">
            검색
          </b-button>
        </span>
      </b-input-group>
      <div style="background-color: white; width: 90%; max-height: 10%; overflow: auto; display : block; z-index : 100; position:relative"
          v-if="payload.keyword != ''">
          <table style="width: 100%; font-size:1.3rem;">
            <tr v-for="(dong) in autodong" :key="dong.dong">
              <td  @click="autosearch(dong.dong)">
                <img src="../../assets/locationmarker.png" style="width:7%;"/>
                {{dong.city}}
                {{dong.gugun}}
                {{dong.dong}}
              </td>
            </tr>
            <tr v-for="(house) in autohouse" :key="house.no">
              <td  @click="autosearch(house.aptName)">
                <img src="../../assets/housemarker.png" style="width:7%;"/>
                {{house.dong}}
                {{house.aptName}}
              </td>
            </tr>
          </table>
      </div>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import {mapActions} from 'vuex';
import {createInstance} from '../../store/modules/index.js';

const instance = createInstance();
const searchStore = 'searchStore';

export default {
  data() {
    return {
      //검색어
      payload: {
        keyword: '',
      },
      //지역이름 자동완성, 주택이름 자동완성
      autodong: [],
      autohouse : [],
    };
  },
  methods: {
    ...mapActions(searchStore, ['SEARCH']),
    async search() {//검색
      if (!this.payload.keyword) {
        alert('검색어를 입력해주세요.');
      } else {
        await this.SEARCH(this.payload);
        this.payload.keyword="";//자동검색창 닫기 위해서 ''검색
        this.autocomplete();
        this.$emit("init");
        // this.$router.push('/result');
      }
    },

    autosearch(keyword){//검색어 자동완성을 클릭했을 시 검색
        this.payload.keyword = keyword;
        this.SEARCH(this.payload);
        this.payload.keyword="";
        this.autocomplete();
        this.$router.push('/result');
    },

    autocomplete(){//검색어 자동완성 기능
      instance
      .get(`house/auto/dong/${this.payload.keyword}`)
      .then((res) => {this.autodong = [], this.autodong = res.data})
      .catch((error) => console.log(error))

      instance
      .get(`house/auto/house/${this.payload.keyword}`)
      .then((res) => {this.autohouse = [], this.autohouse = res.data})
      .catch((error) => console.log(error))
    },

    //쿠키가져오기
    getCookie(cookie_name) {
    var x, y;
    var val = document.cookie.split(';');
  
    for (var i = 0; i < val.length; i++) {
      x = val[i].substr(0, val[i].indexOf('='));
      y = val[i].substr(val[i].indexOf('=') + 1);
      x = x.replace(/^\s+|\s+$/g, ''); // 앞과 뒤의 공백 제거하기
      if (x == cookie_name) {
        return unescape(y); // unescape로 디코딩 후 값 리턴
      }
    }
    }
  }
}
</script>

<style scoped>
#search-box {
  /* z-index: 1000;
  max-width: 370px; */
  background: white;
  /* position: absolute;
   margin-top: 10px;
   margin-left: 10px; */
   position: relative;
   top: 1%;
   left: 1%;
   height:10%;
   width: 20%;
   z-index: 8;
}
#search-result {
  max-height: 300px;
  overflow: auto;
}
.mouseover-cursor {
  cursor: pointer;
}
</style>
