<script setup>

import { ref } from 'vue';
import ToggleQuestion from "./ToggleQuestion.vue";

const questions =ref([{
    id:1,
question:"How can I reach customer support?", answer:"For assistance, feel free to reach out to our customer support team at support@example.com or give us a call at +1 123-456-7890.",
},{ id:2,
    question:"How To Contact Seller for more information?", answer:"To contact seller, use the platform's in-app feature or call/message them directly. If necessary, contact the platform's customer support for assistance."
},{
    id:3,
    question:"App Installation Failed,How to Update System Information?", answer:"Update your device's operating system, free up storage space, clear app cache, ensure a stable internet connection, restart your device, and contact app support for assistance. If issues persist, uninstall and reinstall the app."
},{
    id:4,
    question:"New Update Fixed All Bugs and Issues" ,answer:"Great news! The new update has successfully resolved all bugs and issues. Enjoy the improved and bug-free experience with the latest version." 
}]);

</script>

<template>
    <div id="help" className='gettingstarted'>
            <h2>Getting Started</h2>
            <div className='gettingstarted-1'>
                <ToggleQuestion  v-for="question in questions" :key="question.id" :question="question" />
            </div>
        </div>
</template>
<style scoped src="../css/HelpPage.css">

</style>