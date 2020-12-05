import Vue from 'vue'
import App from './App.vue'
import router from './router/router'
import store from '../src/store/store.js'
import * as VueGoogleMaps from "vue2-google-maps";

import { BootstrapVue, BootstrapVueIcons, 
  BIcon, 
  BIconBarChartSteps,
  BIconMapFill,
  BIconSearch,
  BIconHr } from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import 'bootstrap-vue/dist/bootstrap-vue-icons.min.css'
import 'boxicons'

Vue.config.productionTip = false

// use BootstrapVue, IconsPlugin
Vue.use(BootstrapVue);
Vue.use(BootstrapVueIcons);

// regist component BIcon
Vue.component(BIcon);
Vue.component(BIconBarChartSteps);
Vue.component(BIconMapFill);
Vue.component(BIconSearch);
Vue.component(BIconHr);

// use vue2-google-maps
Vue.use(VueGoogleMaps, {
  load: {
    key: "AIzaSyBayoG7kVw-MTQPFWxzM7d5a6GsKJRN_6U",
    libraries: "places" //necessary for places input
  }
});

new Vue({
  render: h => h(App),
  router,
  store
}).$mount('#app')
