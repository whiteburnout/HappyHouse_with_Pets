<template>
    <div id="map" style="width:100%; position:absolute; height:100%">
        <map-search v-on:init ="init"></map-search>
        <div style="background-color: white; width: 20%; height: 8%; z-index : 5; position:absolute; left:38%; top:85%; border-radius: 30px;">
          <center>
            <div style=" width: 90%; height:80%; margin:1.5%; padding : 1%;">
            <b-button variant="danger" style="width:20%; height:10%; position:relative; padding : 1%;" @click="hospitalswitch"
            >동물병원</b-button>
            <b-button variant="secondary" style="width:20%; height:10%; position:relative; padding : 1%;" @click="pharmacyswitch"
            >동물약국</b-button>
            <b-button variant="success" style="width:20%; height:10%; position:relative; padding : 1%;" @click="parkswitch"
            >공원</b-button>
            <b-button variant="info" style="width:20%; height:10%; position:relative; padding : 1%;" @click="coveniswitch"
            >편의점</b-button>
            <b-button variant="warning" style="width:20%; height:10%; position:relative; padding : 1%;" @click="marketswitch"
            >대형마트</b-button>
          </div>
          </center>
        </div>
          <search-detail v-show="show"
          v-on:close="close" v-on:showreview="showreview"></search-detail>
          <review v-show="review" v-on:closereview="closereview" :houseno="houseno"></review>
    </div>
</template>

<script>
import { mapGetters, mapActions } from 'vuex';
import MapSearch from './MapSearch.vue';
import SearchDetail from './SearchDetail.vue';
import Review from './Review.vue';
const houseMarker = require('../../assets/house.png'); //집마커
const img = require('../../assets/logo.png'); //로고 이미지
const hosMarker = require('../../assets/hospital.png'); //병원마커
const pharMarker = require('../../assets/pharmacy.png'); //약국마커
const parkMarker = require('../../assets/park.png'); //공원마커
const conveniMarker = require('../../assets/convenience.png');//편의점 마커
const marketMarker = require('../../assets/market.png');//편의점 마커

const searchStore = 'searchStore';
const reviewStore = 'reviewStore';

const KAKAO_MAP_API_KEY = process.env.VUE_APP_KAKAO_MAP_API_KEY;//카카오 맵 API 키

export default {
  name: 'app',

  components: {
    MapSearch,
    SearchDetail,
    Review,
  },

  data() {
    return {
      show: false,//디테일창
      review:false,//리뷰창
      houseno:null,//집번호
      map : null,/////카카오맵 옵션
      container : null,/////카카오맵 옵션
      options : null,///////카카오맵 옵션
      hosshow : false,////병원, 약국, 공원, 편의점, 마트 보여주기 여부
      phashow : false,
      parshow : false,
      conshow: false,
      marketshow: false,
      geocoder : null,//카카오맵 관련 변수
      ps : null,//카카오 맵 api에서 라이브러리 검색시 사용
      placeOverlay : null,//오버레이 관련 변수
      markers:[],//마커관리 배열들
      conmarker:[],
      marketmarker:[],
      hosmarker:[],
      pharmarker:[],
      parmarker:[],
    };
  },

  mounted() {//카카오맵 실행
    window.kakao && window.kakao.maps ? this.initMap() : this.addScript();
    this.geocoder = new kakao.maps.services.Geocoder();
    this.placeOverlay = new kakao.maps.CustomOverlay({zIndex:1});
  },

  computed: {
    ...mapGetters(searchStore, [
      'getHousePosition',
      'getHouse',
      'getHospitalPosition',
      'getParkPosition',
      'getPharmacyPosition',
      'getPharmacy',
      'getHospital',
      'getPark',
      'getDeal',
    ]),
  },

  watch: {
    getHousePosition: function() {
      this.initMap();
      this.addScript();
    },

  },

  methods: {
    ...mapActions(reviewStore, ['reviewList']),
    ...mapActions(searchStore, ['DEAL', 'HOSPITAL', 'PHARMACY', 'PARK']),
    ///////////////////라이브러리 마커 함수들
    coveniswitch(){//편의점 스위치
      if(!this.conshow){
        this.ps = new kakao.maps.services.Places(this.map);
        this.ps.categorySearch("CS2", this.placesSearchCB_con, {useMapBounds:true});
      }
        
      else{
        for ( var i = 0; i < this.conmarker.length; i++ ) {
            this.conmarker[i].setMap(null);
        }   
        this.conmarker = [];
      }

      this.conshow = !this.conshow;
    },
    marketswitch(){//마트 스위치
      if(!this.marketshow){
        this.ps = new kakao.maps.services.Places(this.map);
        this.ps.categorySearch("MT1", this.placesSearchCB_mar, {useMapBounds:true});
      }
        
      else{
        for ( var i = 0; i < this.marketmarker.length; i++ ) {
            this.marketmarker[i].setMap(null);
        }   
        this.marketmarker = [];
      }

      this.marketshow = !this.marketshow;
    },
    // 장소검색이 완료됐을 때 호출되는 콜백함수 입니다
    placesSearchCB_con(data, status) {//편의점 검색 라이브러리
    if (status === kakao.maps.services.Status.OK) {

        // 정상적으로 검색이 완료됐으면 지도에 마커를 표출합니다
        this.displayPlaces(data, this.conmarker, conveniMarker);
    } else if (status === kakao.maps.services.Status.ZERO_RESULT) {
        // 검색결과가 없는경우 해야할 처리가 있다면 이곳에 작성해 주세요

    } else if (status === kakao.maps.services.Status.ERROR) {
        // 에러로 인해 검색결과가 나오지 않은 경우 해야할 처리가 있다면 이곳에 작성해 주세요
        console.log("error");
    }
   },
   placesSearchCB_mar(data, status) {//마켓검색 라이브러리
    if (status === kakao.maps.services.Status.OK) {

        // 정상적으로 검색이 완료됐으면 지도에 마커를 표출합니다
        this.displayPlaces(data, this.marketmarker, marketMarker);
    } else if (status === kakao.maps.services.Status.ZERO_RESULT) {
        // 검색결과가 없는경우 해야할 처리가 있다면 이곳에 작성해 주세요

    } else if (status === kakao.maps.services.Status.ERROR) {
        // 에러로 인해 검색결과가 나오지 않은 경우 해야할 처리가 있다면 이곳에 작성해 주세요
        console.log("error");
    }
   },
   // 지도에 마커를 표출하는 함수입니다
    displayPlaces(places, arr, img) {

    for ( var i=0; i<places.length; i++ ) {

            // 마커를 생성하고 지도에 표시합니다
          var marker = this.addMarker(new kakao.maps.LatLng(places[i].y, places[i].x), arr, img);
          var content = '<div class="placeinfo">' +
                  '   <a class="title" href="' + places[i].place_url + '" target="_blank" title="' + places[i].place_name + '">' + places[i].place_name + '</a>';   

          if (places[i].road_address_name) {
              content += '    <span title="' + places[i].road_address_name + '">' + places[i].road_address_name + '</span>' +
                          '  <span class="jibun" title="' + places[i].address_name + '">(지번 : ' + places[i].address_name + ')</span>';
          }  else {
              content += '    <span title="' + places[i].address_name + '">' + places[i].address_name + '</span>';
          }                
        
          content += '    <span class="tel">' + places[i].phone + '</span>' + 
                      '</div>' + 
                      '<div class="after"></div>';

          // contentNode.innerHTML = content;

          var customOverlay = new kakao.maps.CustomOverlay({
              map: this.map,
              position: new kakao.maps.LatLng(places[i].y, places[i].x),
              content: content,
              yAnchor: 1 
          });
          //기본값으로 오버레이가 떠버려서 null로 꺼줘야함
          customOverlay.setMap(null);
        kakao.maps.event.addListener(
          marker,
          'mouseover',
          this.makeOverListener(this.map, marker, customOverlay)
        );

        kakao.maps.event.addListener(
          marker,
          'mouseout',
          this.makeOutListener(customOverlay)
        );
      }
    },

    // 마커를 생성하고 지도 위에 마커를 표시하는 함수입니다
    addMarker(position, arr, img) {
        var imageSrc = img, // 마커 이미지 url, 스프라이트 이미지를 씁니다
            imageSize = new kakao.maps.Size(30, 34),  // 마커 이미지의 크기
            imgOptions =  {
                spriteSize : new kakao.maps.Size(30, 34), // 스프라이트 이미지의 크기
                offset: new kakao.maps.Point(5, 15) // 마커 좌표에 일치시킬 이미지 내에서의 좌표
            },
            markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imgOptions),
                marker = new kakao.maps.Marker({
                position: position, // 마커의 위치
                image: markerImage 
            });

        marker.setMap(this.map); // 지도 위에 마커를 표출합니다
        arr.push(marker);  // 배열에 생성된 마커를 추가합니다

        return marker;
    },
    //////////////////라이브러리 마커 함수들 끝
    initMap() {
      this.container = document.getElementById('map');
      this.options = {
        center: new kakao.maps.LatLng(
          this.getHousePosition[0].position.lat,
          this.getHousePosition[0].position.lng
        ),
        level: 5,
      };
      this.map = new kakao.maps.Map(this.container, this.options); //마커추가하려면 객체를 아래와 같이 하나 만든다.
      // kakao.maps.event.addListener(this.map, 'idle', this.searchPlaces);

      var size = this.getHousePosition.length;
      var imageSrc = houseMarker, // 마커이미지의 주소입니다    
      imageSize = new kakao.maps.Size(30, 34), // 마커이미지의 크기입니다
      imageOption = {offset: new kakao.maps.Point(20, 58)}; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.
      for (let index = 0; index < size; index++) {
        let markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption);
        var marker = new kakao.maps.Marker({
          position: new kakao.maps.LatLng(
            this.getHousePosition[index].position.lat,
            this.getHousePosition[index].position.lng
          ),
          image: markerImage // 마커이미지 설정
        });
        marker.setMap(this.map);

        // 커스텀 오버레이에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
        var content = `<div class="wrap">
                <div class="info">
                    <div class="title">
                        주택 정보
                    </div>
                    <div class="body">
                        <div class="img">
                            <img src="${img}" width="73" height="70">
                       </div>
                        <div class="desc">
                            <div class="ellipsis">${this.getHouse[index].aptName}</div>
                        </div> 
                    </div> 
                </div>    
            </div>`;

        // 커스텀 오버레이를 생성합니다
        var customOverlay = new kakao.maps.CustomOverlay({
            map: this.map,
            position: new kakao.maps.LatLng(
            this.getHousePosition[index].position.lat,
            this.getHousePosition[index].position.lng
          ),
            content: content,
            yAnchor: 1 
        });
        //기본값으로 오버레이가 떠버려서 null로 꺼줘야함
        customOverlay.setMap(null);
        // 마커에 클릭이벤트를 등록합니다
        kakao.maps.event.addListener(marker, 'click', this.showdetail(this.change, index));
        kakao.maps.event.addListener(
          marker,
          'mouseover',
          this.makeOverListener(this.map, marker, customOverlay)
        );

        kakao.maps.event.addListener(
          marker,
          'mouseout',
          this.makeOutListener(customOverlay)
        );
      }
      this.HOSPITAL(this.getHouse[0].dong);//병원위치 미리구해두기
      this.PHARMACY(this.getHouse[0].dong);//병원위치 미리구해두기
      this.PARK(this.getHouse[0].dong);//공원위치 미리구해두기 ==>엑시오스 속도때문
      this.hosshow = false;
      this.phashow = false;
      this.parshow = false;
      this.conshow = false;
      this.marketshow = false;
      this.fail = true;
    },
    //병원 마커 스위치
    hospitalswitch(){
      var size = this.getHospitalPosition.length;
      if(size == 0 && !this.hosshow)
      {
        alert("주변에 동물병원이 없습니다.");
      }

      else if(this.hosshow){
        for ( var i = 0; i < this.hosmarker.length; i++ ) {
          this.hosmarker[i].setMap(null);
        }   
        this.hosmarker = [];
      }
      
      else if(size > 0 && !this.hosshow){
        // this.showMakers();
        let size = this.getHospitalPosition.length;
        let imageSrc = hosMarker, // 마커이미지의 주소입니다    
        imageSize = new kakao.maps.Size(30, 34), // 마커이미지의 크기입니다
        imageOption = {offset: new kakao.maps.Point(20, 58)}; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.
        for (let index = 0; index < size; index++) {
          let markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption);
          let marker = new kakao.maps.Marker({
            position: new kakao.maps.LatLng(
              this.getHospitalPosition[index].position.lat,
              this.getHospitalPosition[index].position.lng
            ),
            image: markerImage // 마커이미지 설정
          });
        marker.setMap(this.map);
        this.hosmarker.push(marker);
        let content = `<div class="wrap">
                <div class="info">
                    <div class="title">
                        ${this.getHospital[index].hosname}
                    </div>
                    <div class="body">
                        <div class="img">
                            <img src="${img}" width="73" height="70">
                       </div>
                        <div class="desc">
                            <div class="ellipsis">${this.getHospital[index].hosaddr}<br>${this.getHospital[index].hosphone}</div>
                        </div> 
                    </div> 
                </div>    
            </div>`;

        // 커스텀 오버레이를 생성합니다
        let customOverlay = new kakao.maps.CustomOverlay({
            map: this.map,
            position: new kakao.maps.LatLng(
            this.getHospitalPosition[index].position.lat,
            this.getHospitalPosition[index].position.lng
          ),
            content: content,
            yAnchor: 1 
        });
        //기본값으로 오버레이가 떠버려서 null로 꺼줘야함
        customOverlay.setMap(null);
        // 마커에 클릭이벤트를 등록합니다
        kakao.maps.event.addListener(
          marker,
          'mouseover',
          this.makeOverListener(this.map, marker, customOverlay)
        );

        kakao.maps.event.addListener(
          marker,
          'mouseout',
          this.makeOutListener(customOverlay)
        );
        }
      }
      this.hosshow = !this.hosshow;
    },
    //약국 마커 스위치
    pharmacyswitch(){
      var size = this.getPharmacyPosition.length;
      if(size == 0 && !this.phashow)
        alert("주변에 동물약국이 없습니다.");
      
      else if(this.phashow){
        for ( var i = 0; i < this.pharmarker.length; i++ ) {
          this.pharmarker[i].setMap(null);
        }   
        this.pharmarker = [];
      }

      else if(size > 0 && !this.phashow){
        let size = this.getPharmacyPosition.length;
        let imageSrc = pharMarker, // 마커이미지의 주소입니다    
        imageSize = new kakao.maps.Size(30, 34), // 마커이미지의 크기입니다
        imageOption = {offset: new kakao.maps.Point(20, 58)}; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.
        for (let index = 0; index < size; index++) {
          let markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption);
          let marker = new kakao.maps.Marker({
            position: new kakao.maps.LatLng(
              this.getPharmacyPosition[index].position.lat,
              this.getPharmacyPosition[index].position.lng
            ),
            image: markerImage // 마커이미지 설정
          });
        marker.setMap(this.map);
        this.pharmarker.push(marker);

        let content = `<div class="wrap">
                <div class="info">
                    <div class="title">
                        ${this.getPharmacy[index].pharname}
                    </div>
                    <div class="body">
                        <div class="img">
                            <img src="${img}" width="73" height="70">
                       </div>
                        <div class="desc">
                            <div class="ellipsis">${this.getPharmacy[index].pharaddr}<br>${this.getPharmacy[index].pharphone}</div>
                        </div> 
                    </div> 
                </div>    
            </div>`;

        // 커스텀 오버레이를 생성합니다
        let customOverlay = new kakao.maps.CustomOverlay({
            map: this.map,
            position: new kakao.maps.LatLng(
            this.getPharmacyPosition[index].position.lat,
            this.getPharmacyPosition[index].position.lng
          ),
            content: content,
            yAnchor: 1 
        });
        //기본값으로 오버레이가 떠버려서 null로 꺼줘야함
        customOverlay.setMap(null);
        // 마커에 클릭이벤트를 등록합니다
        kakao.maps.event.addListener(
          marker,
          'mouseover',
          this.makeOverListener(this.map, marker, customOverlay)
        );

        kakao.maps.event.addListener(
          marker,
          'mouseout',
          this.makeOutListener(customOverlay)
        );
        }
      }

      this.phashow = !this.phashow;
    },
    //공원 마커 스위치
    parkswitch(){
      var size = this.getParkPosition.length;
      if(size == 0 && !this.parshow)
        alert("주변에 공원이 없습니다.");
      
      else if(this.parshow){
        for ( var i = 0; i < this.parmarker.length; i++ ) {
          this.parmarker[i].setMap(null);
        }   
        this.parmarker = [];
      }

      else if(size > 0 && !this.parshow){
        let size = this.getParkPosition.length;
        let imageSrc = parkMarker, // 마커이미지의 주소입니다    
        imageSize = new kakao.maps.Size(30, 34), // 마커이미지의 크기입니다
        imageOption = {offset: new kakao.maps.Point(20, 58)}; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.
        for (let index = 0; index < size; index++) {
          let markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption);
          let marker = new kakao.maps.Marker({
            position: new kakao.maps.LatLng(
              this.getParkPosition[index].position.lat,
              this.getParkPosition[index].position.lng
            ),
            image: markerImage // 마커이미지 설정
          });
        marker.setMap(this.map);
        this.parmarker.push(marker);
        let content = `<div class="wrap">
                <div class="info">
                    <div class="title">
                        ${this.getPark[index].parkname}
                    </div>
                    <div class="body">
                        <div class="img">
                            <img src="${this.getPark[index].parkimg}" width="73" height="70">
                       </div>
                        <div class="desc">
                            <div class="ellipsis">${this.getPark[index].parkaddr}<br>${this.getPark[index].parkphone}</div>
                        </div> 
                    </div> 
                </div>    
            </div>`;

        // 커스텀 오버레이를 생성합니다
        let customOverlay = new kakao.maps.CustomOverlay({
            map: this.map,
            position: new kakao.maps.LatLng(
            this.getParkPosition[index].position.lat,
            this.getParkPosition[index].position.lng
          ),
            content: content,
            yAnchor: 1 
        });
        //기본값으로 오버레이가 떠버려서 null로 꺼줘야함
        customOverlay.setMap(null);
        // 마커에 클릭이벤트를 등록합니다
        kakao.maps.event.addListener(
          marker,
          'mouseover',
          this.makeOverListener(this.map, marker, customOverlay)
        );

        kakao.maps.event.addListener(
          marker,
          'mouseout',
          this.makeOutListener(customOverlay)
        );
        }
      }
      this.parshow = !this.parshow;
    },
    //커스텀오버레이 키기기
    makeOverListener(map, marker, customOverlay) {
      return function() {
        customOverlay.setMap(map);
      };
    },

    //커스텀오버레이 끄기기
    makeOutListener(customOverlay) {
      return function() {
        customOverlay.setMap(null);
      };
    },
    addScript() {
      const script = document.createElement('script');
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src = 
        'http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=' + KAKAO_MAP_API_KEY + '&libraries=services';
      document.head.appendChild(script);
    },
    showdetail(change, index) {//주택마커 클릭시 콜백함수
      return function() {

        change(index);
      };
    },
    change(index){//마커 클릭시
      this.center = this.getHousePosition[index].position;
      this.houseno = this.getHouse[index].no;
      this.DEAL({//거래정보 엑시오스
         dong: this.getHouse[index].dong,
         aptName: this.getHouse[index].aptName,
      });
      this.reviewList(this.houseno);//리뷰정보 엑시오스
      if(!this.show && !this.review)
        this.show = true;
      else if(this.show && !this.review)
      {
        this.show = false;
        this.show = true;
      }
      else if(!this.show && this.review){
        this.review = false;
        this.show = true;
      }
    },
    close(){//디테일창 닫기
      this.show = false;
      console.log(this.show);
    },
    init(){//리뷰와 디테일 초기화
      this.show = false;
      this.review = false;
    },
    showreview(){//더보기 클릭 시 리뷰창 띄우기
      this.review = true;//리뷰 띄우고
      this.show = false;//디테일창 감춤
    },
    closereview(){//리뷰창 닫기
      this.review = false;
      this.show = true;
    },
  }
}
</script>
<style>
.map_wrap, .map_wrap * {margin:0; padding:0;font-family:'Malgun Gothic',dotum,'돋움',sans-serif;font-size:12px;}
.map_wrap {position:relative;width:100%;height:350px;}
#category {position:absolute;top:10px;left:10px;border-radius: 5px; border:1px solid #909090;box-shadow: 0 1px 1px rgba(0, 0, 0, 0.4);background: #fff;overflow: hidden;z-index: 2;}
#category li {float:left;list-style: none;width:50px;border-right:1px solid #acacac;padding:6px 0;text-align: center; cursor: pointer;}
#category li.on {background: #eee;}
#category li:hover {background: #ffe6e6;border-left:1px solid #acacac;margin-left: -1px;}
#category li:last-child{margin-right:0;border-right:0;}
#category li span {display: block;margin:0 auto 3px;width:27px;height: 28px;}
#category li .category_bg {background:url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/places_category.png) no-repeat;}
#category li .bank {background-position: -10px 0;}
#category li .mart {background-position: -10px -36px;}
#category li .pharmacy {background-position: -10px -72px;}
#category li .oil {background-position: -10px -108px;}
#category li .cafe {background-position: -10px -144px;}
#category li .store {background-position: -10px -180px;}
#category li.on .category_bg {background-position-x:-46px;}
.placeinfo_wrap {position:absolute;bottom:28px;left:-150px;width:300px;}
.placeinfo {position:relative;width:100%;border-radius:6px;border: 1px solid #ccc;border-bottom:2px solid #ddd;padding-bottom: 10px;background: #fff;}
.placeinfo:nth-of-type(n) {border:0; box-shadow:0px 1px 2px #888;}
.placeinfo_wrap .after {content:'';position:relative;margin-left:-12px;left:50%;width:22px;height:12px;background:url('https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white.png')}
.placeinfo a, .placeinfo a:hover, .placeinfo a:active{color:#fff;text-decoration: none;}
.placeinfo a, .placeinfo span {display: block;text-overflow: ellipsis;overflow: hidden;white-space: nowrap;}
.placeinfo span {margin:5px 5px 0 5px;cursor: default;font-size:13px;}
.placeinfo .title {font-weight: bold; font-size:14px;border-radius: 6px 6px 0 0;margin: -1px -1px 0 -1px;padding:10px; color: #fff;background: #d95050;background: #d95050 url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/arrow_white.png) no-repeat right 14px center;}
.placeinfo .tel {color:#0f7833;}
.placeinfo .jibun {color:#999;font-size:11px;margin-top:0;}

.wrap {position: absolute;left: 0;bottom: 40px;width: 288px;height: 132px;margin-left: -144px;text-align: left;overflow: hidden;font-size: 12px;font-family: 'Malgun Gothic', dotum, '돋움', sans-serif;line-height: 1.5;}
.wrap * {padding: 0;margin: 0;}
.wrap .info {width: 286px;height: 120px;border-radius: 5px;border-bottom: 2px solid #ccc;border-right: 1px solid #ccc;overflow: hidden;background: #fff;}
.wrap .info:nth-child(1) {border: 0;box-shadow: 0px 1px 2px #888;}
.info .title {padding: 5px 0 0 10px;height: 30px;background: #eee;border-bottom: 1px solid #ddd;font-size: 18px;font-weight: bold;}
.info .close {position: absolute;top: 10px;right: 10px;color: #888;width: 17px;height: 17px;background: url('https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/overlay_close.png');}
.info .close:hover {cursor: pointer;}
.info .body {position: relative;overflow: hidden;}
.info .desc {position: relative;margin: 13px 0 0 90px;height: 75px;}
.desc .ellipsis {overflow: hidden;text-overflow: ellipsis;white-space: nowrap;}
.desc .jibun {font-size: 11px;color: #888;margin-top: -2px;}
.info .img {position: absolute;top: 6px;left: 5px;width: 73px;height: 71px;border: 1px solid #ddd;color: #888;overflow: hidden;}
.info:after {content: '';position: absolute;margin-left: -12px;left: 50%;bottom: 0;width: 22px;height: 12px;background: url('https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white.png')}
.info .link {color: #5085BB;}
</style>
