<template>
    <div class="w3-main" style="margin-left:300px;margin-top:43px;">

        
      <h2>Voici l'historique de tous les médicaments déja prescrits au patient</h2>


      <ul class="chat-list" >
        <li class="chat-item" v-for="medicament in medicaments" :key="medicament.id">
            <div class="chat-preview" >
              <p>
                <h2>{{ medicament.nom }}</h2>
                

                <i class="fa fa-plus" style="color: green;" v-if="medicament.prescription!=='Oui'"></i> 

              </p>
               
            </div>
        </li>
        

        <!-- Ajoutez d'autres chats ici -->
    </ul>
      
       
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
  
 
 

  axios.get(`http://192.168.224.1:8080/api/medicament/all/${id}`, config) 
    .then(response => {
      
       
        this.medicaments=response.data;
      
      
    })
    .catch(error => {
      console.error('Erreur lors de la récupération des médicaments:', error);
    }).finally(()=> this.loading = false);
    
   
},

       
 
         
       },
       mounted(){
         
         this.medicaments=[],
          this.posologies= [],
          this.fetchMedicaments();
           
       
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
        
        border: 1px solid #ccc;
        padding: 10px;
        margin-bottom: 10px;
        border-radius: 5px;
        transition: transform 0.3s, background-color 0.3s;
        background-color: white; /* Fond blanc */
      }
      
      .chat-item:hover {
        transform: scale(1.02);
        cursor: pointer;
        background-color: rgb(38, 212, 143);
        color: rgb(0, 0, 0);
      }
      
      
      .chat-preview {
        flex-grow: 1;
      }
      


     </style>
     