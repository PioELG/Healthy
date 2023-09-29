<template>
    <div class="w3-main" style="margin-left:300px;margin-top:43px;">

        <!-- Header -->
      
      
      <div class="container" v-if="patient.length !== 0 && constante.length !== 0 ">
        <div class="patient-info">
            <h2>Informations du Patient</h2>
            <p><strong>Nom : </strong> {{ patient[0].nom}}</p>
            <p><strong>Prénom : </strong> {{ patient[0].prenom}}</p>
            <p><strong>Âge :</strong> 35 ans</p>
            <p><strong>Pathologie :</strong>{{ patient[0].pathologie}}</p>
            <p><strong>Statut :</strong> {{ patient[0].statut}}</p>
            
        </div>
        <div class="constants">
          <h2>Constantes du Patient</h2>
<p><strong><i class="fa fa-balance-scale" style="color: rgb(0, 255, 140);"></i> Poids :</strong> {{ constante[0].poids}}  kg</p>
<p><strong><i class="fa fa-stethoscope" style="color: green;"></i> Tension Artérielle :</strong> {{ constante[0].pressionArterielle }} mmHg</p>
<p><strong><i class="fa fa-heartbeat" style="color: red;"></i> Fréquence Cardiaque :</strong> {{ constante[0].freqCar }} bpm</p>
<p><strong><i class="fa fa-cloud" style="color: rgb(0, 136, 255) ;"></i>  Fréquence Respiratoire :</strong> {{ constante[0].freqRes}} cycles/min</p>
<p><strong><i class="fa fa-thermometer" style="color: rgb(236, 141, 6);"></i> Température :</strong> {{constante[0].temperature}}°C</p>

          
            <!-- Ajoutez d'autres constantes ici -->
        </div>
        <div class="symptoms">
            <h2>Symptômes post-consultation</h2>
            <ul v-for="symptome in symptomes" :key="symptome.id">
                <li>{{symptome.nom}}</li>
                
                <!-- Ajoutez d'autres symptômes ici -->
            </ul>
        </div>

        <div class="prescription">
          <table>
            <tr>
              <td>
                <h2>Prescription</h2>   
              </td>
              <td></td>
              <td>
                <i class="fa fa-plus" style="color: green;"></i> 
              </td>
              
            </tr>
          </table>
        
           
          <div class="medicament">
            <p>Paracetamol</p><br>
            <ul>
              <li>1 comprimé le matin</li>
              <li>2 comprimés le soir</li>
            </ul>
            
            <div class="icones">
              <i class="fa fa-pencil" style="color: blue;"></i> &nbsp;&nbsp;&nbsp;<!-- Icône de modification -->
              <i class="fa fa-trash" style="color: red;"></i> &nbsp;&nbsp;&nbsp;
            </div>
        </div>
        <div class="medicament">
            <p>Amoxicilline </p> 
            <ul>
              <li>1 comprimé le matin</li>
              <li>1 comprimé le soir</li>
            </ul>
            
            <p> </p>
            <div class="icones">
              <i class="fa fa-pencil" style="color: blue;"></i> &nbsp;&nbsp;&nbsp;<!-- Icône de modification -->
              <i class="fa fa-trash" style="color: red;"></i> &nbsp;&nbsp;&nbsp;
            </div>
        </div>
            <br>
            <p ><!-- Ajoutez la classe 'vert' ou 'rouge' en fonction de l'état -->
                Le patient est <strong class="etat-traitement">à jour</strong> dans son traitement.
            </p>
        </div>
        <div class="rdv">
          <table>
            <tr>
              <td>
                <h2>Rendez-vous avec ce patient</h2>
              </td>
              <td></td> <td></td>
              <td>
                <i class="fa fa-plus" style="color: green;"></i> 
              </td>
            </tr>
          </table>
          
          <p>Aucun rendez-vous prévu</p>
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
          symptomes:[]
        };
      },
      methods: {
        // Les méthodes de votre composant vont ici
        fetchMalade() {
  const accessToken = keycloak.token; // Remplacez par votre jeton d'accès
        
  // Définissez l'en-tête d'autorisation avec le jeton d'accès
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
      console.error('Erreur lors de la récupération des conseils du jour :', error);
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
 
 
 

  axios.get(`http://192.168.224.1:8080/api/constantes/${id}`, config) // Utilisez la configuration avec l'en-tête d'autorisation
    .then(response => {
      
       
        this.constante=response.data; 
        console.log(this.constante);
      
    })
    .catch(error => {
      console.error('Erreur lors de la récupération des conseils du jour :', error);
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
        
      },
      mounted(){
          // Les propriétés calculées de votre composant vont ici
          this.patient = [],
          this.loading = true,
          this.constante=[],
          this.symptomes=[],
           this.fetchMalade();
           this.fetchConstante();
           this.fetchSymptomes();
           
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
  
    </style>
    