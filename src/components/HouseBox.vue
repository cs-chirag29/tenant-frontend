<template>
  <div class="homeoptioncard" @click="navigateToProperty">
    <div class="homeoption-1">
      <img src="https://www.villabalisale.com/uploads/images/property/2023-04-26-property-6448915ee5dca.jpg" alt="home" /> <!-- Static image path -->
    </div>
    <div class="homeoption-2">
      <h3>{{ property.propertyName }}</h3>
      <p class="truncate-text">{{ property.description }}</p>
      <h2>Rs 2500/day</h2>
    </div>
  </div>
</template>

<script setup>
import { defineProps } from 'vue';
import { useRouter } from 'vue-router';

const props = defineProps({
  property: {
    type: Object,
    required: true
  }
});

const router = useRouter();

const navigateToProperty = () => {
  console.log('Property in navigateToProperty:', props.property); // Debugging output
  if (props.property && props.property.propertyId) {
    router.push(`/house/${props.property.propertyId}`);
  } else {
    console.error('Property ID is missing');
  }
};

// Debugging output
console.log('Property in HouseBox:', props.property);
</script>



<style scoped src="../css/HomeOptions.css"></style>
