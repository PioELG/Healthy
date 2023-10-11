<template>
    <!DOCTYPE html>
    <html>
    <head>
    <title>W3.CSS Template</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    </head>
    <body >
      
        <div class="w3-main" style="margin-left:300px;margin-top:20px;">

            <!-- Header -->
            <header class="w3-container" style="padding-top:27px">
              <h4><b><i class="fa fa-dashboard"></i> Mon Tableau de bord</b></h4>
            </header>
          
            <div class="w3-row-padding w3-margin-bottom bon">
              
              <div class="w3-quarter">
                <router-link to="/MonHistorique" class="link">
                <div class="w3-container w3-black w3-padding-16 w3-container-rounded">
                  <div class="w3-left"><i class="fa fa-history w3-xxxlarge"></i></div>
                  <div class="w3-right">
                    
                  </div>
                  <div class="w3-clear"></div>
                  <h4>MonHistorique</h4>
                </div>
              </router-link>
              </div>
              
              <div class="w3-quarter">
                <router-link to="/rdvMedecin" class="link">
                <div class="w3-container w3-blue w3-padding-16 w3-container-rounded">
                  <div class="w3-left"><i class="fa fa-eye w3-xxxlarge"></i></div>
                  <div class="w3-right">
                    
                  </div>
                  <div class="w3-clear"></div>
                  <h4>MesRendez-Vous</h4>
                
                </div>
            </router-link>
              </div>
              
              <div class="w3-quarter">
                <router-link to="/Mesconseils" class="link">
                <div class="w3-container w3-teal w3-padding-16 w3-container-rounded">
                  <div class="w3-left"><i class="fa fa-heart w3-xxxlarge"></i></div>
                  <div class="w3-right">
                    
                  </div>
                  <div class="w3-clear"></div>
                  <h4>MesConseils</h4>
                </div>
            </router-link>
              </div>
              
              <div class="w3-quarter">
                <router-link to="/MesPatients" class="link">
                <div class="w3-container w3-orange w3-text-white w3-padding-16 w3-container-rounded">
                  <div class="w3-left"><i class="fa fa-users w3-xxxlarge"></i></div>
                  <div class="w3-right">
                    
                  </div>
                  <div class="w3-clear"></div>
                  <h4>MesPatients</h4>
                </div>
            </router-link>
              </div>
            
            </div>
          
            <div class="w3-panel">
              <div class="w3-row-padding" style="">
               
                <div class="w3-twothird">
                  <b><h4>Notifications</h4></b>
                  <table class="w3-table w3-striped w3-white">
                    <tr v-for="notif in notifs " :key="notif.id">
                      <td><i class="fas fa-exclamation-circle" style="font-size:24px; color: rgb(243, 11, 11)"></i></td>
                      <td >
                        <div class="contexte">
                        Le patient <strong>{{ getNomPrenom(notif.initiateur).nom }} {{ getNomPrenom(notif.initiateur).prenom }}</strong>  vient d'envoyer {{notif.contexte}}

                        </div>
                       
                      </td>
                      <td><i class="fas fa-check-circle" style="font-size:24px; color: green;" @click="supprimerNotification(notif.id)"></i></td>
                      
                    </tr>
                    <tr v-for="rdv in rdvDemain " :key="rdv.id">
                      <td><i class="fas fa-exclamation-circle" style="font-size:24px; color: rgb(243, 11, 11)"></i></td>
                      <td >
                        <div class="contexte">
                        Votre rendez-vous avec le patient {{ getNomPrenom(rdv.malade_id).nom }} {{ getNomPrenom(rdv.malade_id).prenom }} est pour  demain à <strong>{{ rdv.heure }}</strong>

                        </div>
                       
                      </td>
                      <td><i class="fas fa-check-circle" style="font-size:24px; color: green;"></i></td>
                      
                    </tr>
                         
          
                  </table>
                </div>
              </div>
            </div>
        </div>  
        
     </body>
    </html>
  </template>
  
  <script>
   import keycloak from '@/main';
  import axios from 'axios';

  export default {
    
    name: 'NouvelleMedecin', // Remplacez par le nom de votre composant
    data() {
    return {
      notifs:[],
      malades:[],
      maladesRdv:[],
      rdvs: [], 
      rdvDemain: [], 
    };
  },
  methods: {
    fetchNotif() {
  const accessToken = keycloak.token; // Remplacez par votre jeton d'accès

  // Définissez l'en-tête d'autorisation avec le jeton d'accès
  const config = {
    headers: {
      'Authorization': `Bearer ${accessToken}` // Assurez-vous de mettre le type d'autorisation (Bearer) avant le jeton
    }
  };

  axios.get('http://192.168.224.1:8080/api/notification/doc', config) // Utilisez la configuration avec l'en-tête d'autorisation
    .then(response => {
      this.notifs = response.data;
    })
    .catch(error => {
      console.error('Erreur lors de la récupération des notifs :', error);
    });
     
    axios.get('http://192.168.224.1:8080/api/malade/St', config) // Utilisez la configuration avec l'en-tête d'autorisation
    .then(response => {
      this.malades = response.data;
    })
    .catch(error => {
      console.error('Erreur lors de la récupération des malades :', error);
    });
},
async supprimerNotification(NotifId) {
    const accessToken = keycloak.token; // Remplacez par votre jeton d'accès

// Définissez l'en-tête d'autorisation avec le jeton d'accès
const config = {
  headers: {
    'Authorization': `Bearer ${accessToken}` // Assurez-vous de mettre le type d'autorisation (Bearer) avant le jeton
  }
};
    if (confirm("Êtes-vous sûr de vouloir supprimer cette notification ?")) {
      try {
        // Envoyez une requête de suppression à votre API Backend en utilisant l'ID du conseil
         await axios.delete(`http://192.168.224.1:8080/api/notification/${NotifId}`,config);

        // Gérez la réponse de l'API (par exemple, affichez un message de succès)
        console.log('prescription supprimée avec succès !');

        // Mettez à jour la liste des conseils en supprimant le conseil supprimé
        this.notifs = this.notifs.filter(n => n.id !== NotifId);
      } catch (error) {
        console.error('Erreur lors de la suppression de la notification :', error);
        // Gérez les erreurs de l'API (par exemple, affichez un message d'erreur)
      }
    }
  },
  getNomPrenom(maladeId) {
        const malade = this.malades.find(m => m.id === maladeId);
        if (malade) {
          return {
            nom: malade.nom,
            prenom: malade.prenom,
           
          };
        }
        return { nom: '', prenom: '' };
      },
      async fetchRdv() {
        const accessToken = keycloak.token;
        const config = {
          headers: {
            'Authorization': `Bearer ${accessToken}`
          }
        };
  
      try {
        const response = await axios.get('http://192.168.224.1:8080/api/rdv',config);
        this.rdvs = response.data;

        // Filtrer les rendez-vous pour obtenir ceux du lendemain
        const demain = new Date();
        demain.setDate(demain.getDate() + 1);

       
        this.rdvDemain = this.rdvs.filter(rdv => {
          const rdvDate = new Date(rdv.date);
          return rdvDate.toDateString() === demain.toDateString();
        });
      } catch (error) {
        console.error('Erreur lors de la récupération des rendez-vous :', error);
      }
    },

    
  },
  mounted() {
      this.fetchNotif();
      console.log(this.notifs);
      console.log(this.malades);
      this.fetchRdv();
      
      
    }
    
};
  </script>
  
  <style scoped>
  html,body,h1,h2,h3,h4,h5 {font-family: "Raleway", sans-serif}
  .w3-container-rounded {
    border-radius: 10px; /* Vous pouvez ajuster le rayon selon vos préférences */
  }
  .w3-container-rounded:hover {
    transform: translate(5px, -5px); /* Déplacez la div vers le bas et vers la droite */
    transition: transform 0.2s ease; /* Ajoutez une transition fluide */
  }
  .w3-container
  {
      padding-top:22px
  }
  .w3-main
  {
    height: 100%;
    min-height: 100vh; /*le code qui m'a permis d'étendre la div sur toute la page */
  }
  .w3-row-padding
  {
      margin-left:20px;
  
  }

  .w3-container
  {
      padding: 16 px;
  }
  .link
      {
          text-decoration: none;
      }
  .w3-table{
    border-radius: 10px;
    width: 100%;
    min-width: 100vh;
    

  }
      .w3-table td i {
         /* Couleur de l'icône */
        margin-right: 10px; /* Marge à droite de l'icône */
      }
    
      /* Ajoutez une animation de transition pour les notifications */
      .w3-table td {
        transition: transform 0.2s ease;
      }
    
      /* Lorsque vous survolez une ligne de notification, appliquez une transformation */
      .w3-table tr:hover td {
        transform: translateX(10px); /* Déplacez la ligne vers la droite au survol */
      }
    
      /* Style pour le titre "Notifications" */
      h5 {
        font-size: 18px; /* Taille de police */
        margin-bottom: 10px; /* Marge en bas du titre */
      }
      .contexte {
        border-radius: 10px;
        padding: 10px; 
      }


  </style>
  