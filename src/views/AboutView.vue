

<template>
  <Header/>
  <SideBar/>

  
  
</template>
<script>
// @ is an alias to /src
import Header from '@/components/Header.vue';
import SideBar from '@/components/SideBar.vue';

export default {
  name: 'AboutView',
  components: {
   SideBar,
   Header
}
}
</script>
