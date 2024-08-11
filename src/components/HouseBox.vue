<script setup>
import {defineProps} from 'vue';
import { useRouter } from 'vue-router';

const props = defineProps({
  house: Object
});

const router = useRouter();

const navigateToHouse = () => {
  if (props.house.id) {
    router.push(`/house/${props.house.id}`);
  } else {
    console.error('House ID is missing');
  }
};
  </script>

<template>
    <button class="homeoptioncard" @click="navigateToHouse" >
      <div class="homeoption-1">
        <img :src="house.image" alt="home" />
      </div>
      <div class="homeoption-2">
        <h3>{{ house.name }}</h3>
        <p>{{ house.description }}</p>
        <h2>Rs {{ house.price }}/day</h2>
      </div>
    </button>
  </template>
  
  
  
  <style scoped src="../css/HomeOptions.css"></style>
  