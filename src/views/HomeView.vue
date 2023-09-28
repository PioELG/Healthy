<template>
  <div class="home">
    <img alt="Vue logo" src="../assets/logo.png">
   
  </div>
</template>

<script>
// @ is an alias to /src
import Header from '@/components/Header.vue';
import HelloWorld from '@/components/HelloWorld.vue'

export default {
  name: 'HomeView',
  components: {
    HelloWorld,
    Header
}
}
</script>
