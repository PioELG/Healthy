<template>
    <div class="w3-main" style="margin-left:300px;margin-top:20px;">

        
        <header class="w3-container" style="padding-top:22px">
          <h5><b><i class="fa fa-stethoscope"></i> Mes Prescriptions</b></h5>
        </header>
       
        <main style="margin-left:30px;margin-right:30px;" >
          <ul class="chat-list" v-for="medicament in medicaments" :key="medicament.id">
              <li class="chat-item">
                  <div class="chat-preview" >
                      <h2>{{ medicament.nom }}</h2>
                      <p v-for="posologie in getPosologies(medicament.id)" :key="posologie.id"> 
                        {{ posologie.quantite }} {{ posologie.unite }} {{ posologie.heurePrise }}

                          
                      </p>
                      
                  </div>
              </li>
              
      
             
          </ul>
      </main>
       
      
       
      </div>
   </template>
     
     <script>
    
    import keycloak from '../main.js';
    import axios from 'axios';
   
   
     export default {
       name: 'VoirPrescription', 
       
       data() {
         return {
          
          medicaments:[],
          posologies:[],
          statut:""
 
         };
       },
       methods: {
         
         fetchMedicaments() {
  const accessToken = keycloak.token; 
        

  const config = {
    headers: {
      'Authorization': `Bearer ${accessToken}` 
    }
  };
  const id = this.$route.params.id;
  
 
 

  axios.get(`http://192.168.224.1:8080/api/medicament`, config) 
    .then(response => {
      
       
        this.medicaments=response.data;
      
      
    })
    .catch(error => {
      console.error('Erreur lors de la récupération des médicaments:', error);
    }).finally(()=> this.loading = false);
    
   
},
fetchPosologie() {
  const accessToken = keycloak.token;
        
 
  const config = {
    headers: {
      'Authorization': `Bearer ${accessToken}` 
    }
  };
  const id = this.$route.params.id;
  
 
 

  axios.get(`http://192.168.224.1:8080/api/posologie`, config) 
    .then(response => {
      
       
        this.posologies=response.data;
      
      
    })
    .catch(error => {
      console.error('Erreur lors de la récupération des posologies:', error);
    }).finally(()=> this.loading = false);
    
    
},
getPosologies(medId) {
    return this.posologies.filter(posologie => posologie.medicament_id === medId);
  },

  
  
       
 
         
       },
       mounted(){
         
         this.medicaments=[],
          this.posologies= [],
          this.fetchMedicaments();
           this.fetchPosologie();
       
       },
       
     };
     </script>
     
     <style scoped>
     html,body,h1,h2,h3,h4,h5 {font-family: "Raleway", sans-serif}

.chat-list {
    list-style: none;
    padding: 0;
}

.chat-item {
    display: flex;
    align-items: center;
    border: 1px solid #ccc;
    padding: 10px;
    margin-bottom: 10px;
    border-radius: 5px;
    transition: transform 0.3s, background-color 0.3s;
    background-color: white; 
}




.chat-preview {
    flex-grow: 1;
}
.w3-main
  {
    height: 100%;
    min-height: 100vh; /*le code qui m'a permis d'étendre la div sur toute la page */
  }
  
     </style>
     