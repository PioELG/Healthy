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
  
  <div class="contenu">
    <router-view></router-view>
  </div>
  
  
</template>



<script>
import Header from './components/Header.vue';
import SideBar from './components/SideBar.vue';
import Nouvelle from './components/Nouvelle.vue';
import MaSante from './components/MaSante.vue';
import MonEtat from './components/MonEtat.vue';
import AjouterConstantes from './components/AjouterConstantes.vue';
import AjouterSymptomes from './components/AjouterSymptomes.vue';
import MesPatients from './components/MesPatients.vue';
import ListePatient from './components/ListePatient.vue';
import InfosPatient from './components/InfosPatient.vue';

export default {
  components: {
    Header,
    SideBar,
    Nouvelle,
    MaSante,
    MonEtat,
    AjouterConstantes,
    AjouterSymptomes,
    ListePatient,
    MesPatients,
    InfosPatient,

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
  background-color: #2c3e50;
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
.contenu
{
  background-color: #dfe2e4;
}

</style>
