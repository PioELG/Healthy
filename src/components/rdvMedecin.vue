<template>
    <div class="w3-main" style="margin-left:300px;margin-top:43px;">

        <!-- Header -->
       
      
        <div class="w3-row-padding w3-margin-bottom">
          
            <table id="customers">
              <tr>
                <th>NomPatient</th>
                <th>PrenomPatient</th>
                <th>Pathologie</th>
                <th>Date</th>
                <th>Heure</th>
                
                <th>Statut</th>
                
                <th>Actions</th>
                
              </tr>
              <tr v-for="rdv in rdvs" :key="rdv.id">
               
                <td>{{rdv.date}}</td>
                <td>{{rdv.heure}}</td>
                <td>
                        <i class="fa fa-pencil" style="color: blue;"></i>
                     <!-- Icône de modification -->&nbsp;&nbsp;&nbsp;
                    <i class="fa fa-trash" style="color: red;" @click="supprimerRdv(rdv.id)"></i> &nbsp;&nbsp;&nbsp;<!-- Icône de suppression -->
                    <i class="fa fa-envelope"></i><!-- Icône de modification -->
                 
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
  import axios from 'axios';
   
     export default {
       name: 'rdvMedecin', // Remplacez par le nom de votre composant
       
       data() {
         return {
           // Les données de votre composant vont ici
           rdvs:[],
           
 
         };
       },
       methods: {
         // Les méthodes de votre composant vont ici
         // Les méthodes de votre composant vont ici
         fetchRdv() {
  const accessToken = keycloak.token; // Remplacez par votre jeton d'accès

  // Définissez l'en-tête d'autorisation avec le jeton d'accès
  const config = {
    headers: {
      'Authorization': `Bearer ${accessToken}` // Assurez-vous de mettre le type d'autorisation (Bearer) avant le jeton
    }
  };

  axios.get('http://192.168.224.1:8080/api/rdv', config) // Utilisez la configuration avec l'en-tête d'autorisation
    .then(response => {
      this.rdvs = response.data;
    })
    .catch(error => {
      console.error('Erreur lors de la récupération des rendez-vous:', error);
    });
    
},
async supprimerRdv(rdvId) {
    const accessToken = keycloak.token; // Remplacez par votre jeton d'accès

// Définissez l'en-tête d'autorisation avec le jeton d'accès
const config = {
  headers: {
    'Authorization': `Bearer ${accessToken}` // Assurez-vous de mettre le type d'autorisation (Bearer) avant le jeton
  }
};
    if (confirm("Êtes-vous sûr de vouloir supprimer ce rendez-vous ?")) {
      try {
        // Envoyez une requête de suppression à votre API Backend en utilisant l'ID du conseil
         await axios.delete(`http://192.168.224.1:8080/api/conseils/${rdvId}`,config);

        // Gérez la réponse de l'API (par exemple, affichez un message de succès)
        console.log('rdv supprimé avec succès !');

        // Mettez à jour la liste des conseils en supprimant le conseil supprimé
        this.rdvs = this.rdvs.filter(r => r.id !== rdvId);
      } catch (error) {
        console.error('Erreur lors de la suppression du conseil :', error);
        // Gérez les erreurs de l'API (par exemple, affichez un message d'erreur)
      }
    }
  }
         
       },
       mounted(){
         // Les propriétés calculées de votre composant vont ici
         this.fetchRdv();
         
         
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
     