<template>
  <section id="searchbar" style="height: 7%">
    <b-container style="height: 100%">
      <!-- Using components -->
      <b-row class="py-4" style="height: 100%">
        <b-col style="height: 100%">
          <b-input-group>
            <b-form-input
              v-model="payload.keyword"
              placeholder="아파트 이름, 동으로 검색해보세요."
              @keypress.enter="search"
              @keyup="autocomplete"
              autocomplete="off"
              id="search-input"
              type="search"
            ></b-form-input>
            <b-input-group-append style="width: 10%;">
              <b-button variant="success" @click.stop="search">검색</b-button>
            </b-input-group-append>
          </b-input-group>
            <span style="background-color: white; width: 90%; max-height: 3000%; overflow: auto; display : block; z-index : 100; position:relative"
            v-if="payload.keyword != ''">
              <table style="width: 90%;">
                <td>
                  <tr v-for="(dong) in autodong" :key="dong.dong">
                    <td  @click="autosearch(dong.dong)">
                      <img src="../assets/locationmarker.png" style="width:7%;"/>
                      {{dong.city}}
                      {{dong.gugun}}
                      {{dong.dong}}
                    </td>
                  </tr>
                  <tr v-for="(house) in autohouse" :key="house.no">
                    <td  @click="autosearch(house.aptName)">
                      <img src="../assets/housemarker.png" style="width:7%;"/>
                      {{house.dong}}
                      {{house.aptName}}
                    </td>
                  </tr>
                </td>
                <td style="border-left:1px solid; width:30%;" v-if="visited.length > 0">
                  최근 검색 기록
                  <tr  v-for="(value) in visited" :key="value">
                    <td  @click="autosearch(value)">
                      <img src="../assets/watch.png" style="width:7%;"/>
                      {{value}}
                    </td>
                  </tr>
                </td>
              </table>
            </span>
        </b-col>
      </b-row>
      <b-row>

      </b-row>
    </b-container>
  </section>
</template>

<script>
import { mapActions } from 'vuex';
import {createInstance} from '../store/modules/index.js';

const instance = createInstance();
const searchStore = 'searchStore';

export default {
  data() {
    return {
      // 검색어
      payload: {
        keyword: '',
      },
      //지역이름 자동완성, 주택이름 자동완성
      autodong: [],
      autohouse : [],
      //최근 검색 기록 저장할 배열
      visited:[],
    };
  },

  created() {
    //쿠키에서 최근 검색기록 가져와서 저장
    let temp = this.getCookie("visited");
    let result = temp.split(',');
    this.visited = result;
  },
  methods: {
    ...mapActions(searchStore, ['SEARCH']),

    search() {//검색
      if (!this.payload.keyword) {
        alert('검색어를 입력해주세요.');
      } else {
        this.SEARCH(this.payload);
        this.$router.push('/result');
      }
    },

    autosearch(keyword){//검색어 자동완성을 클릭했을 시
        this.payload.keyword = keyword;
        this.SEARCH(this.payload);
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
