<template>
  
    <div class="w3-main" style="margin-left:300px;margin-top:20px;">

        <!-- Header -->
      <br> <br>
      
      <div class="container" v-if="patient.length !== 0 && constante.length !== 0 ">
        <div class="patient-info">
            <h2>Informations du Patient</h2>
            <p><strong>Nom : </strong> {{ patient[0].nom}}</p>
            <p><strong>Prénom : </strong> {{ patient[0].prenom}}</p>
            <p><strong>Âge :</strong> 35 ans</p>
            <p><strong>Diagnostic :</strong>{{ patient[0].pathologie}}</p>
            <p><strong>Statut :</strong> {{ patient[0].statut}}</p>
            
        </div>
        
        <div class="constants">
          <h2>Constantes du Patient</h2>
<p><strong><i class="fa fa-balance-scale" style="color: rgb(0, 0, 0);"></i> Poids :</strong> {{ constante[0].poids}}  kg</p>
<p><strong><i class="fa fa-stethoscope" style="color: green;"></i> Tension Artérielle :</strong> {{ constante[0].pressionArterielle }} mmHg</p>
<p><strong><i class="fa fa-heartbeat" style="color: red;"></i> Fréquence Cardiaque :</strong> {{ constante[0].freqCar }} bpm</p>
<p><strong><i class="fa fa-cloud" style="color: rgb(0, 136, 255) ;"></i>  Fréquence Respiratoire :</strong> {{ constante[0].freqRes}} cycles/min</p>
<p><strong><i class="fa fa-thermometer" style="color: rgb(236, 141, 6);"></i> Température :</strong> {{constante[0].temperature}}°C</p>
        

  </div>
        

        <div class="prescription">
          <table>
            <tr>
              <td>
                <h2>Antécédents médicaux</h2>   
              </td>
              <td></td>
              <td>
                <router-link :to="'/AjouterPrescription/' +$route.params.id">
                <i class="fa fa-plus" style="color: green;"></i> 
              </router-link>
              
              </td>
              
            </tr>
          </table>
        </div>

        <div class="symptoms">
          <h2>Symptomes  </h2>
          <ul v-for="symptome in symptomes" :key="symptome.id">
              <li>{{symptome.nom}}</li>
              
              <!-- Ajoutez d'autres symptômes ici -->
          </ul>
      </div>

        <div class="prescription">
          <router-link :to="'/Historique/' +$route.params.id">   <i class="fa fa-history fa-lg" style="color: rgb(0, 128, 85); margin-left:500px;"></i> </router-link>
          <table>
            <tr>
              <td>
                <h2>Prescription</h2>   
              </td>
              <td></td>
              <td>
                <router-link :to="'/AjouterPrescription/' +$route.params.id">
                <i class="fa fa-plus" style="color: green;"></i> 
              </router-link>
              
              </td>
              
            </tr>
          </table>
           
          <ul class="chat-list" v-for="medicament in medicaments" :key="medicament.id">
            <li class="chat-item">
                <div class="chat-preview" >
                  <p>
                    <h4> <strong>{{ medicament.nom }}</strong> pendant {{ medicament.duree }} </h4> 

                    <i class="fa fa-trash" style="color: red; " @click="supprimerMedicament(medicament.id)"  ></i> &nbsp;&nbsp;&nbsp;   
                    <router-link :to="'/AjouterPosologie/' +medicament.id">  <i class="fa fa-plus" style="color: green;"></i> </router-link>  &nbsp;&nbsp;&nbsp; 
                    <i class="fa fa-eye" @click="toggle"></i>
                  </p>
                    <p v-for="posologie in getPosologies(medicament.id)" :key="posologie.id"  > 
                      {{ posologie.quantite }} {{ posologie.unite }} {{ posologie.heurePrise }}
        
                      <div>
                        <transition name="fade" mode="out-in">
                          <p v-if="show">
                            <router-link :to="'/ModifierPosologie/' +posologie.id"> <i class="fa fa-pencil" style="color: blue;" ></i> </router-link> &nbsp;&nbsp;&nbsp;
                            <i class="fa fa-trash" style="color: red; " @click="supprimerPosologie(posologie.id)"  ></i> &nbsp;&nbsp;&nbsp;   
                            </p>
                        </transition>
                        
                      </div>


                    
                     
                    </p>
                    
                </div>
            </li>
            
    
        </ul>
        
            
        </div>
        <div class="rdv">
          <table>
            <tr>
              <td>
                <h2>Rendez-vous avec ce patient</h2>
              </td>
              <td></td> <td></td>
              <td>
                <router-link :to="'/AjoutRdv/' +$route.params.id">
                <i class="fa fa-plus" style="color: green;"></i> 
              </router-link>
              </td>
            </tr>
          </table>
          
          <p v-if="rdvs.length === 0">Aucun rendez-vous prévu</p>
          <div v-else >
          <router-link :to="'/rdvMedecinPat/' +$route.params.id">
           <button class="btn"> Voir rdvs </button>
          </router-link>
        </div>
      </div>
    </div>
    
    </div>
    
  </template>
    
    <script>
 
  
    import keycloak from '@/main';
    import axios from 'axios';
  
    export default {
      name: 'InfosPatient', // Remplacez par le nom de votre composant
      
      data() {
        return {
          // Les données de votre composant vont ici
          patient:[],
          loading: false ,
          constante:[],
          symptomes:[],
          idP:'',
          medicaments:[],
          posologies:[],
          rdvs:[],
          show: false
        };
      },
      methods: {
        toggle() {
      this.show = !this.show;
    },
       
       
        fetchMalade() {
  const accessToken = keycloak.token; 
        
  
  const config = {
    headers: {
      'Authorization': `Bearer ${accessToken}` // Assurez-vous de mettre le type d'autorisation (Bearer) avant le jeton
    }
  };
  const id = this.$route.params.id;
  
 
 

  axios.get(`http://192.168.224.1:8080/api/malade/${id}`, config) // Utilisez la configuration avec l'en-tête d'autorisation
    .then(response => {
      
       
        this.patient=response.data;
      
      
    })
    .catch(error => {
      console.error('Erreur lors de la récupération des malades :', error);
    }).finally(()=> this.loading = false);
    
   
},
fetchConstante() {
  const accessToken = keycloak.token; // Remplacez par votre jeton d'accès
        
  // Définissez l'en-tête d'autorisation avec le jeton d'accès
  const config = {
    headers: {
      'Authorization': `Bearer ${accessToken}` // Assurez-vous de mettre le type d'autorisation (Bearer) avant le jeton
    }
  };
  const id = this.$route.params.id;
  this.idp=this.$route.params.id;
  console.log(this.idp);
 
 
 

  axios.get(`http://192.168.224.1:8080/api/constantes/${id}`, config) // Utilisez la configuration avec l'en-tête d'autorisation
    .then(response => {
      
       
        this.constante=response.data; 
        console.log(this.constante);
      
    })
    .catch(error => {
      console.error('Erreur lors de la récupération des constantes :', error);
    }).finally(()=> this.loading = false);
    
   
},
fetchSymptomes() {
  const accessToken = keycloak.token; // Remplacez par votre jeton d'accès
        
  // Définissez l'en-tête d'autorisation avec le jeton d'accès
  const config = {
    headers: {
      'Authorization': `Bearer ${accessToken}` // Assurez-vous de mettre le type d'autorisation (Bearer) avant le jeton
    }
  };
  const id = this.$route.params.id;

 
 
 

  axios.get(`http://192.168.224.1:8080/api/symptomes/${id}`, config) // Utilisez la configuration avec l'en-tête d'autorisation
    .then(response => {
      
       
        this.symptomes=response.data; 
        
      
    })
    .catch(error => {
      console.error('Erreur lors de la récupération des symptomes :', error);
    }).finally(()=> this.loading = false);
    
   
},
fetchMedicaments() {
  const accessToken = keycloak.token; // Remplacez par votre jeton d'accès
        
  // Définissez l'en-tête d'autorisation avec le jeton d'accès
  const config = {
    headers: {
      'Authorization': `Bearer ${accessToken}` // Assurez-vous de mettre le type d'autorisation (Bearer) avant le jeton
    }
  };
  const id = this.$route.params.id;
  
 
 

  axios.get(`http://192.168.224.1:8080/api/medicament/${id}`, config) // Utilisez la configuration avec l'en-tête d'autorisation
    .then(response => {
      
       
        this.medicaments=response.data;
      
      
    })
    .catch(error => {
      console.error('Erreur lors de la récupération des médicaments:', error);
    }).finally(()=> this.loading = false);
    
   
},
fetchPosologie() {
  const accessToken = keycloak.token; // Remplacez par votre jeton d'accès
        
  // Définissez l'en-tête d'autorisation avec le jeton d'accès
  const config = {
    headers: {
      'Authorization': `Bearer ${accessToken}` // Assurez-vous de mettre le type d'autorisation (Bearer) avant le jeton
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

  async supprimerMedicament(MedicamentId) {
    const accessToken = keycloak.token; // Remplacez par votre jeton d'accès

// Définissez l'en-tête d'autorisation avec le jeton d'accès
const config = {
  headers: {
    'Authorization': `Bearer ${accessToken}` // Assurez-vous de mettre le type d'autorisation (Bearer) avant le jeton
  }
};
    if (confirm("Êtes-vous sûr de vouloir supprimer ce médicament de la prescription ?")) {
      try {
        
        /* await axios.delete(`http://192.168.224.1:8080/api/medicament/${MedicamentId}`,config);

       
        console.log('prescription supprimée avec succès !');

        this.medicaments = this.medicaments.filter(m => m.id !== MedicamentId);*/

        await axios.put(`http://192.168.224.1:8080/api/medicament/statut/${MedicamentId}`,{prescription:"Non"},config);
        this.fetchMedicaments();



      } catch (error) {
        console.error('Erreur lors de la suppression de la prescription :', error);
        // Gérez les erreurs de l'API (par exemple, affichez un message d'erreur)
      }
    }
  },
  async fetchRdv() {
        const accessToken = keycloak.token;
        const config = {
          headers: {
            'Authorization': `Bearer ${accessToken}`
          }
        };
        const id = this.$route.params.id;
  
        try {
          const rdvResponse = await axios.get(`http://192.168.224.1:8080/api/rdv/${id}`, config);
          this.rdvs = rdvResponse.data;
  
         
        } catch (error) {
          console.error('Erreur lors de la récupération du doctor', error);
        }
      },
      async supprimerPosologie(posologieId) {
    const accessToken = keycloak.token; // Remplacez par votre jeton d'accès

// Définissez l'en-tête d'autorisation avec le jeton d'accès
const config = {
  headers: {
    'Authorization': `Bearer ${accessToken}` // Assurez-vous de mettre le type d'autorisation (Bearer) avant le jeton
  }
};
    if (confirm("Êtes-vous sûr de vouloir supprimer  ?")) {
      try {
        // Envoyez une requête de suppression à votre API Backend en utilisant l'ID du conseil
         await axios.delete(`http://192.168.224.1:8080/api/posologie/${posologieId}`,config);

        // Gérez la réponse de l'API (par exemple, affichez un message de succès)
        console.log('posologie supprimé avec succès !');

        // Mettez à jour la liste des conseils en supprimant le conseil supprimé
        this.posologies = this.posologies.filter(p => p.id !== posologieId);
      } catch (error) {
        console.error('Erreur lors de la suppression  :', error);
        // Gérez les erreurs de l'API (par exemple, affichez un message d'erreur)
      }
    }
  }
      
    },
        
    
      mounted(){
          // Les propriétés calculées de votre composant vont ici
          this.patient = [],
          this.loading = true,
          this.constante=[],
          this.symptomes=[],
          this.medicaments=[],
          this.posologies= [],
           this.fetchMalade();
           this.fetchConstante();
           this.fetchSymptomes();
           this.fetchMedicaments();
           this.fetchPosologie();
           this.fetchRdv();
          
        },
      // Autres options de composant (comme "props", "watch", etc.) vont ici
    };
    </script>
    
    <style scoped>
    html,body,h1,h2,h3,h4,h5 {font-family: "Raleway", sans-serif}
 #customers {
        font-family: Arial, Helvetica, sans-serif;
        border-collapse: collapse;
        width: 100%;
      
      }
      
      #customers td, #customers th {
        border: 1px solid #ddd;
        padding: 8px;
      }
      
      #customers tr:nth-child(even){background-color: #f2f2f2;}
      
      #customers tr:hover {background-color: #ddd;}
      
      #customers th {
        padding-top: 12px;
        padding-bottom: 12px;
        text-align: left;
        background-color: #04AA6D;
        color: white;
      }
      .table
      {
        margin-bottom: 80px;
      }

      body {
    font-family: Arial, sans-serif;
    margin: 0;
    padding: 0;
    background-color: #f0f0f0;
}



.container {
    max-width: 600px;
    margin: 20px auto;
    padding: 20px;
    background-color: #fff;
    border-radius: 5px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
}

h1 {
    text-align: center;
    color: #333;
}

.patient-info {
    margin-bottom: 20px;
}



.constants, .symptoms, .appointment, .prescription,.rdv {
    margin-top: 20px;
    padding: 15px;
    background-color: #f5f5f5;
    border-radius: 5px;
    border: 1px solid #ddd;
}


.prescription {
    position: relative; /* Permet de positionner le texte de l'état du traitement */
}

/* Style pour le texte avec fond vert */
.etat-traitement {
    background-color: #047408; /* Vert */
    color: #fff; /* Texte blanc */
    padding: 10px;
    border-radius: 5px;
    transition: background-color 0.3s, color 0.3s; /* Transition fluide */
}

/* Style pour le texte avec fond rouge */


/* Style pour la liste des symptômes */
.symptoms ul {
    list-style: disc;
    padding-left: 20px;
}

/* Style pour les éléments de la liste des symptômes */
.symptoms ul li {
    margin-bottom: 5px;
}
.medicament {
    padding: 10px;
    background-color: #f5f5f5;
    border-radius: 5px;
    margin-bottom: 10px;
    cursor: pointer;
    transition: background-color 0.3s, color 0.3s;
    display: flex;
}

/* Animation au survol */
.medicament:hover {
    background-color: #04AA6D;
    color: white;
}
.content {
    flex-grow: 1; /* Permet au contenu de s'étendre pour occuper l'espace disponible */
}

.icones {
    margin-left: auto; /* Pousse les icônes vers la droite (fin) */
}
.btn {
    display: inline-block;
    padding: 10px 20px;
    background-color: #007BFF;
    color: #fff;
    border: none;
    border-radius: 10px; /* Augmentation du rayon pour un aspect plus arrondi */
    cursor: pointer;
}

.btn:hover {
    background-color: #0056b3;
}
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
.w3-main
  {
    height: 100%;
    min-height: 100vh; /*le code qui m'a permis d'étendre la div sur toute la page */
  }
  .fade-enter-active, .fade-leave-active {
    transition: opacity 0.5s;
  }
  .fade-enter, .fade-leave-to {
    opacity: 0;
  }
  
  
    </style>
    