<template>
<div style="white-space:nowrap">
    <b-button type="button" pill variant="outline-danger" @click="getHospital">동물병원</b-button>
    <b-button type="button" pill variant="outline-secondary" @click="getPharmacy">동물약국</b-button>
    <b-button type="button" pill variant="success" @click="getPark">공원</b-button>
</div>
</template>

<script>
import {mapGetters} from 'vuex'

export default {
    data() {
        return {
            hospital : false,
            pharmacy : false,
            park : false,
        }
    },
    methods: {
        getHospital(){
            if(this.hospital == false){
                this.$store.dispatch("HOSPITAL", this.getHouse[0].dong);
                this.hospital = true;
            //this.$router.push('/result');
            }

            else{
                this.$store.commit("HOSPITALCLEAR");
                this.hospital = false;
            }
        },
        getPharmacy(){
            if(this.pharmacy == false){
                this.$store.dispatch("PHARMACY", this.getHouse[0].dong);
                this.pharmacy = true;
            //this.$router.push('/result');
            }

            else{
                this.$store.commit("PHARMACYCLEAR");
                this.pharmacy = false;
            }
        },
        getPark(){
            if(this.park == false){
                this.$store.dispatch("PARK", this.getHouse[0].dong);
                this.park = true;
            //this.$router.push('/result');
            }

            else{
                this.$store.commit("PARKCLEAR");
                this.park= false;
            }
        }
    },

    computed: {
    ...mapGetters([
      'getHouse'
    ])
  },
}
</script>

<style>

</style>