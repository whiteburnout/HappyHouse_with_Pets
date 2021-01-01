<template>
  <div style="position:relative; height:100vh; overflow: hidden;">
    <HHHeader style="position:relative; height:15%;"></HHHeader>
    <!-- <SearchDetail></SearchDetail> -->

    <b-row style="position:relative; height:85%;">
      <b-col class="px-0"><ResultMap></ResultMap></b-col>
    </b-row>
  </div>
</template>

<script>
import HHHeader from '../HHHeader';
import ResultMap from './ResultMap';

export default {
  components: {
    HHHeader,
    ResultMap,
  },
};
</script>

<style>
</style>
