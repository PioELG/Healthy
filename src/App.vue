<template>
   <!-- <nav>
    <router-link to="/">Home</router-link> |
    <router-link to="/about">About</router-link>
  </nav>-->
  <div id="app" class="app">
s
    <Header></Header>
  </div>
  <SideBar :keycloaks=keycloak></SideBar>
  
  <router-view></router-view>
  
</template>



<script>
import Header from './components/Header.vue';
import SideBar from './components/SideBar.vue';
import Nouvelle from './components/Nouvelle.vue';


export default {
  components: {
    Header,
    SideBar,
    Nouvelle
},

};
</script>



<style>
/*#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}*/
app
{
  display: flex; 
  justify-content: space-between;
}

nav {
  padding: 30px;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
}

nav a.router-link-exact-active {
  color: #42b983;
}


</style>
