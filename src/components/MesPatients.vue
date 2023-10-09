<template>
    <div class="w3-main" style="margin-left:300px;margin-top:20px;">

        <!-- Header -->
       
      
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
                    <router-link :to="'/InfosPatient/' + malade.id">
                        <i class="fa fa-pencil" style="color: blue;"></i>

                     </router-link><!-- Icône de modification -->&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                      
                    <router-link :to="'/Message/' + malade.id"><i class="fa fa-envelope"></i></router-link> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                 
                    <i class="fa fa-check fa-lg" style="color: green;" @click="FinTraitement(malade.id)"></i>  
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
       name: 'MesPatients', // Remplacez par le nom de votre composant
       
       data() {
         return {
           // Les données de votre composant vont ici
           malades:[],
 
         };
       },
       methods: {
         // Les méthodes de votre composant vont ici
         // Les méthodes de votre composant vont ici
         fetchMalade() {
  const accessToken = keycloak.token; // Remplacez par votre jeton d'accès

  // Définissez l'en-tête d'autorisation avec le jeton d'accès
  const config = {
    headers: {
      'Authorization': `Bearer ${accessToken}` // Assurez-vous de mettre le type d'autorisation (Bearer) avant le jeton
    }
  };

  axios.get('http://192.168.224.1:8080/api/malade/St', config) // Utilisez la configuration avec l'en-tête d'autorisation
    .then(response => {
      this.malades = response.data;
    })
    .catch(error => {
      console.error('Erreur lors de la récupération des conseils du jour :', error);
    });
    console.log(this.malades);
},

FinTraitement(idM)
{
  const accessToken = keycloak.token; // Remplacez par votre jeton d'accès

  // Définissez l'en-tête d'autorisation avec le jeton d'accès
  const config = {
    headers: {
      'Authorization': `Bearer ${accessToken}` // Assurez-vous de mettre le type d'autorisation (Bearer) avant le jeton
    }
  };

  axios
      .put(`http://192.168.224.1:8080/api/malade/Ft/${idM}`, {statut:"FinTraitement"},config)
      .then(response => {
        
        console.log('Réponse du serveur :', response.data);

       
      })
      .catch(error => {
        console.error('Erreur lors de la requête PUT :', error);
        // Gérez les erreurs ici
      });

      axios.get('http://192.168.224.1:8080/api/malade/St', config) // Utilisez la configuration avec l'en-tête d'autorisation
    .then(response => {
      this.malades = response.data;
    })
    .catch(error => {
      console.error('Erreur lors de la récupération des conseils du jour :', error);
    });  


    axios.delete(`http://192.168.224.1:8080/api/rdv/malade/${idM}`, config);

            console.log('Rendez-vous supprimé avec succès !');

    axios.delete(`http://192.168.224.1:8080/api/constantes/malade/${idM}`, config);

    axios.delete(`http://192.168.224.1:8080/api/notification/malade/${idM}`, config);

    axios.delete(`http://192.168.224.1:8080/api/symptomes/malade/${idM}`, config);
    
    axios.delete(`http://192.168.224.1:8080/api/message/malade/${idM}`, config);
    console.log(' supprimé avec succès !');
            
          
}
 
         
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
    margin-top:50px
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
    .w3-main{
      height: 100%;
        min-height: 100vh;
    }

    .infos {
      display: none; /* Masquer les informations au départ */
    }
    
    .fa-pencil:hover .infos {
      display: block; /* Afficher les informations lorsque survolé */
    }
    .w3-main
  {
    height: 100%;
    min-height: 100vh; /*le code qui m'a permis d'étendre la div sur toute la page */
  }
 
    
 </style>
     