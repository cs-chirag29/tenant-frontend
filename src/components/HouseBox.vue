<script setup>
import {defineProps} from 'vue';
import { useRouter } from 'vue-router';

const props = defineProps({
  house: Object
});

const router = useRouter();

const navigateToHouse = () => {
  if (props.house.unitId) {
    router.push(`/house/${props.house.unitId}`);
  } else {
    console.error('House ID is missing');
  }
};
console.log(props.house.propertyId);
  </script>

<template>
    <button class="homeoptioncard" @click="navigateToHouse" >
      <div class="homeoption-1">
        <img :src="house?.unitImage" alt="home" />
      </div>
      <div class="homeoption-2">
        <h3 :v-if="house?.property">{{ house?.property?.propertyName }}</h3>
        <p>{{ house?.property?.description }}</p>
        <h2>Rs {{ house?.rentAmount }}/day</h2>
      </div>
    </button>
  </template>
  
  
  
  <style scoped src="../css/HomeOptions.css"></style>
  