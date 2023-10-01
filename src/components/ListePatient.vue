<template>
    <div class="w3-main" style="margin-left:300px;margin-top:43px;">

        <!-- Header -->
        <header class="w3-container" style="padding-top:22px">
          <h5><b><router-link to="/MesPatients" class="link"><i class="fa fa-users fa-fw "></i> Mes Patients</router-link></b></h5>
        </header>
      
        <div class="w3-row-padding w3-margin-bottom">
          
            <table id="customers">
              <tr>
                <th>Nom</th>
                <th>Prenom</th>
                <th>Email</th>
                <th>Pathologie</th>
                <th>Statut</th>
                
                <th>Actions</th>
                
              </tr>
              <tr v-for="malade in malades" :key="malade.id">
                <td>{{malade.nom}}</td>
                <td>{{malade.prenom}}</td>
                <td>{{malade.email}}</td>
                <td>{{malade.pathologie}}</td>
                <td>{{malade.statut}}</td>
                <td>
                    <i  class="fa fa-plus" style="color: green;" @click="ouvrirFormulaire(malade)"></i> &nbsp;&nbsp;&nbsp;<!-- Icône de modification -->
                 
                </td>
              </tr>
              <tr v-if="maladeEnCoursDeMiseAJour">
                <td colspan="6">
                  <form @submit.prevent="soumettreFormulaire">
                    <label for="nouvellePathologie">Nouvelle Pathologie:</label>
                    <input type="text" id="nouvellePathologie" v-model="nouvellePathologie" required>
                    <button type="submit">Enregistrer</button>
                   
                  </form>
                </td>
              </tr>
             
              
            </table>
            
            
          
        </div>
        <br>
        
       
      
        <!-- End page content -->
      </div>
     
   </template>
     
     <script>
        import keycloak from '@/main';
        import jwtDecode from 'jwt-decode';
    import axios from 'axios';
   
     export default {
       name: 'ListePatient', // Remplacez par le nom de votre composant

       data() {
         return {
           // Les données de votre composant vont ici
           malades:[],
           maladeEnCoursDeMiseAJour: null,
           nouvellePathologie: '',
           idDoc:''
 
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

  axios.get('http://192.168.224.1:8080/api/malade/Nt', config) // Utilisez la configuration avec l'en-tête d'autorisation
    .then(response => {
      this.malades = response.data;
    })
    .catch(error => {
      console.error('Erreur lors de la récupération des conseils du jour :', error);
    });
},
ouvrirFormulaire(malade) {
      // Ouvre le formulaire de mise à jour pour un malade spécifique
      this.maladeEnCoursDeMiseAJour = malade;
      this.nouvellePathologie = malade.pathologie;
      this.idDoc=jwtDecode(keycloak.token).sub
      
    },
   soumettreFormulaire() {
      // Soumet les données mises à jour au serveur
      if (this.maladeEnCoursDeMiseAJour) {
        const accessToken = keycloak.token; // Remplacez par votre jeton d'accès

// Définissez l'en-tête d'autorisation avec le jeton d'accès
const config = {
  headers: {
    'Authorization': `Bearer ${accessToken}` // Assurez-vous de mettre le type d'autorisation (Bearer) avant le jeton
  }
};
        // Mettez à jour les données du malade ici, puis réinitialisez le formulaire
        this.maladeEnCoursDeMiseAJour.pathologie = this.nouvellePathologie;
        this.maladeEnCoursDeMiseAJour.statut = "Sous traitement";
        this.maladeEnCoursDeMiseAJour.traitant=jwtDecode(keycloak.token).sub;

    
        
        console.log(jwtDecode(keycloak.token).sub)
        console.log("Bonjour")
        console.log(this.maladeEnCoursDeMiseAJour.traitant)
        const newData = {
      pathologie: this.nouvellePathologie,
      statut: this.maladeEnCoursDeMiseAJour.statut,
      traitant: this.maladeEnCoursDeMiseAJour.traitant
    };

    axios
      .put(`http://192.168.224.1:8080/api/malade/${this.maladeEnCoursDeMiseAJour.id}`, newData,config)
      .then(response => {
        // Gérez la réponse du serveur ici (par exemple, mettez à jour votre liste de patients avec la réponse)
        console.log('Réponse du serveur :', response.data);

        // Réinitialisez le formulaire et l'état de mise à jour
        
      })
      .catch(error => {
        console.error('Erreur lors de la requête POST :', error);
        // Gérez les erreurs ici
      });

        
      }
    },
         
       },
       mounted(){
         // Les propriétés calculées de votre composant vont ici
         this.fetchMalade();
         
        
         
         
       },
       // Autres options de composant (comme "props", "watch", etc.) vont ici
     };
     </script>
     
     <style scoped>
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
      .link
    {
        text-decoration: none;
    }
     </style>
     