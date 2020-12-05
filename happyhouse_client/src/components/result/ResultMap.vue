<template>
  <!-- <div id="app">
    <GmapMap
      :center="getHousePosition[0].position"
      :zoom="15"
      style="width:100%;  height: 800px;"
    >
      <GmapMarker
        v-for="(m, index) in getHousePosition"
        :key="index"
        :position="m.position"
        :clickable="true"
        :icon="markerOptions"
        @click="detail(index)"
      />
      <GmapMarker
        v-for="(m, index) in getHospitalPosition"
        :key="(m.lat, m.lng)"
        :position="m.position"
        :clickable="true"
        :icon="hosmarkerOptions"
        @click="hosdetail(m, getHospital, index)"
      />
      <GmapMarker
        v-for="(m, index) in getPharmacyPosition"
        :key="(m.lat, m.lng)"
        :position="m.position"
        :clickable="true"
        :icon="phamarkerOptions"
        @click="phardetail(m, getPharmacy, index)"
      />
      <GmapMarker
        v-for="(m, index) in getParkPosition"
        :key="(m.lat, m.lng)"
        :position="m.position"
        :clickable="true"
        :icon="parkmarkerOptions"
        @click="parkdetail(m, getPark, index)"
      />
      <GmapInfoWindow
        :options="infoOptions"
        :position="infoWindowPos"
        :opened="infoWinOpen"
        @click="infoWinOpen = false"
      >
        <div v-html="infoContent"></div>
      </GmapInfoWindow>
    </GmapMap>
  </div> -->
  <div id="map" style="width:100%; height:800px;"></div>
</template>

<script>
import { mapGetters } from 'vuex';
const houseMarker = require('../../assets/house.png'); //집마커
const img = require('../../assets/logo.png'); //구글 인포윈도우 이미지
const hosMarker = require('../../assets/hospital.png'); //병원마커
const pharMarker = require('../../assets/pharmacy.png'); //약국마커
const parkMarker = require('../../assets/park.png'); //공원마커

export default {
  name: 'App',
  data() {
    return {
      // default to montreal to keep it simple
      // change this to whatever makes sense
      center: {},
      //markers: [{position: {lat: 37.63722808742175, lng: 127.07187647722832 },},],
      markers: [],
      places: [],
      currentPlace: null,
      house: {},
      result: [],
      dong: '',
      aptName: '',
      markerOptions: {
        //매물마커
        url: houseMarker,
        size: { width: 60, height: 90, f: 'px', b: 'px' },
        scaledSize: { width: 30, height: 45, f: 'px', b: 'px' },
      },
      hosmarkerOptions: {
        //병원마커
        url: hosMarker,
        size: { width: 60, height: 90, f: 'px', b: 'px' },
        scaledSize: { width: 30, height: 45, f: 'px', b: 'px' },
      },
      phamarkerOptions: {
        //약국마커
        url: pharMarker,
        size: { width: 60, height: 90, f: 'px', b: 'px' },
        scaledSize: { width: 30, height: 45, f: 'px', b: 'px' },
      },
      parkmarkerOptions: {
        //공원마커
        url: parkMarker,
        size: { width: 60, height: 90, f: 'px', b: 'px' },
        scaledSize: { width: 30, height: 45, f: 'px', b: 'px' },
      },
      infoOptions: {
        pixelOffset: {
          width: 0,
          height: -35,
        },
      },
      infoWindowPos: {
        lat: 0,
        lng: 0,
      },
      infoWinOpen: false,
      ///////////////////infowindow에 넣을 데이터들
      name: '',
      phone: '',
      addr: '',
      src: '',
      ////////////////////
    };
  },

  mounted() {
   window.kakao && window.kakao.maps ? this.initMap() : this.addScript();
  },

  computed: {
    ...mapGetters([
      'getHousePosition',
      'getHouse',
      'getHospitalPosition',
      'getParkPosition',
      'getPharmacyPosition',
      'getPharmacy',
      'getHospital',
      'getPark',
    ]),
  },

  methods: {

    initMap() { 
      var container = document.getElementById('map');
      var options = { center: new kakao.maps.LatLng(33.450701, 126.570667),level: 3 };
      var map = new kakao.maps.Map(container, options); //마커추가하려면 객체를 아래와 같이 하나 만든다.
      var marker = new kakao.maps.Marker({
         position: map.getCenter()
      });
       marker.setMap(map);
    },
    addScript() {
      const script = document.createElement('script');
       /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src = 'http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=e3430083d75bcdedc109154500105017';
      document.head.appendChild(script); }

    },
    detail(index) {
      this.center = this.getHousePosition[index].position;
      console.log(this.getHouse[index].dong);

      this.$store.dispatch('DEAL', {
        dong: this.getHouse[index].dong,
        aptName: this.getHouse[index].aptName,
      });
    },

    phardetail(position, data, index) {
      this.name = data[index].pharname;
      this.phone = data[index].pharphone;
      this.addr = data[index].pharaddr;
      this.src = img;

      this.toggleInfoWindow(position, index);
    },
    hosdetail(position, data, index) {
      this.name = data[index].hosname;
      this.phone = data[index].hosphone;
      this.addr = data[index].hosaddr;
      this.src = img;

      this.toggleInfoWindow(position, index);
    },
    parkdetail(position, data, index) {
      console.log(data[index].img);
      this.name = data[index].parkname;
      this.phone = data[index].parkphone;
      this.addr = data[index].parkaddr;
      this.src = data[index].parkimg;

      this.toggleInfoWindow(position, index);
    },
    toggleInfoWindow: function(marker, idx) {
      this.infoWindowPos = marker.position;
      this.infoContent = this.getInfoWindowContent(marker);

      //check if its the same marker that was selected if yes toggle
      if (this.currentMidx == idx) {
        this.infoWinOpen = !this.infoWinOpen;
      }
      //if different marker set infowindow to open and reset current marker index
      else {
        this.infoWinOpen = true;
        this.currentMidx = idx;
      }
    },

    getInfoWindowContent: function() {
      return `<div class="card">
        <div class="card-image">
        <figure class="image is-4by3">
       <img src="${this.src}" alt="Placeholder image" style="width: 110px; height: 110px">
       </figure>
       </div>
       <div class="card-content">
        <div class="media">
      <div class="media-content">
        <p class="title is-4">${this.name}</p>
       </div>
      </div>
        <div class="content">
        ${this.addr}<br>
        ${this.phone}
       <br>
        <time datetime="2016-1-1"></time>
       </div>
        </div>
        </div>`;
    },
};
</script>

<style></style>
