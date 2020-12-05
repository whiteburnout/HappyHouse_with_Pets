<template>
  <div>
    <HHHeader></HHHeader>
    <HHSearchBar></HHSearchBar>
    <!-- <SearchDetail></SearchDetail> -->

    <b-row>
      <!-- <b-col cols="3" class="px-0" style="max-height: 15%;"
        ><ResultList></ResultList
      ></b-col> -->
      <b-col class="px-0"><ResultMap></ResultMap></b-col>
    </b-row>
  </div>
</template>

<script>
import HHHeader from '../HHHeader';
//import ResultList from './ResultList';
import ResultMap from './ResultMap';
import HHSearchBar from '../HHSearchBar';
// import SearchDetail from './SearchDetail'

export default {
  components: {
    HHHeader,
    //ResultList,
    ResultMap,
    HHSearchBar,
    // SearchDetail
  },
};
</script>

<style></style>
