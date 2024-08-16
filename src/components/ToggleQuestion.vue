<script setup>
import { ref,defineProps } from "vue";

defineProps({
  question: Object
});
const showAnswer = ref(false);

const toggleAnswer=()=>{
    showAnswer.value=!showAnswer.value;
}
</script>

<template>
    
  <div class="question">
    <div class="question-header">
      <h3>{{ question.question }}</h3>
      <button @click="toggleAnswer">{{ showAnswer ? '-' : '+' }}</button>
    </div>
    <p v-if="showAnswer">{{ question.answer }}</p>
  </div>
</template>

<style scoped src="../css/ToggleQuestion.css">

</style>