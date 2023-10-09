<template>
   <SideBarMedecin v-if="doc"></SideBarMedecin>
   <SideBarPatient v-else></SideBarPatient>
    
  </template>
    
    <script>
   import SideBarMedecin from './SideBarMedecin.vue';
   import SideBarPatient from './SideBarPatient.vue';
   import keycloak from '../main.js';
  import jwtDecode from 'jwt-decode';
  
    export default {
      name: 'SideBar', 
      components:
      {
        SideBarMedecin,
        SideBarPatient
      },
      data() {
        return {
         
          doc:'',
          decodedToken:{},
          roles:{}

        };
      },
      methods: {
        

        
      },
      mounted(){
        
        
        if (keycloak.token) {
     this.decodedToken = jwtDecode(keycloak.token);
     this.roles = this.decodedToken.realm_access.roles;
     if((this.roles.includes('patient')))
     {
        console.log("hello patient")  ; 
        this.doc=false;
     }
     if((this.roles.includes('medecin')))
     {
          console.log("hello doc")  ;
        this.doc=true;
        console.log(this.doc)  ;
     }
   
     
       
   }
      },
      
    };
    </script>
    
    <style scoped>
  
    </style>
    