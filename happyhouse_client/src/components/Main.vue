<template>
  <div style="position:relative; height:100vh; overflow: auto;">
    <HHHeader></HHHeader>
    <HHSearchBar></HHSearchBar>
    <HHContent></HHContent>
    <HHFooter></HHFooter>
  </div>
</template>

<script>
import HHContent from './HHContent.vue';
import HHFooter from './HHFooter.vue';
import HHHeader from './HHHeader.vue';
import HHSearchBar from './HHSearchBar.vue';

export default {
  components: {
    HHHeader,
    HHSearchBar,
    HHContent,
    HHFooter,
  },
};
</script>

<style>
body {
  font-family: 'Jua', sans-serif;
  color: #444444;
}

a {
  color: #47b2e4;
}

a:hover {
  color: #73c5eb;
  text-decoration: none;
}

h1,
h2,
h3,
h4,
h5,
h6 {
  font-family: 'Jua', sans-serif;
}
</style>
