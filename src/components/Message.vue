<template>
    <div class="w3-main" style="margin-left:300px;margin-top:20px;">

        <!-- Header -->
       
      
        <main>
            <br>
            <div class="chat" v-for="message in messages" :key="message.id" v-bind:class="{'patient':(message.sender==='Patient'), 'doctor':(message.sender === 'Medecin')}"
            @mouseover="showDeleteIcon(message.id)" @mouseleave="hideDeleteIcon(message.id)"
            >
              
                
              <p >{{ message.contenu }}</p>
              <span class="delete-icon" @click="supprimerMessage(message.id)" v-if="message.sender === 'Medecin' && message.showDeleteIcon" >
                <i class="fa fa-trash" style="color: red;" ></i>
              </span>
                
                <!-- Ajoutez d'autres messages ici -->
            </div>
            <div class="message-input">
                <input type="text" placeholder="Écrivez votre message..." v-model="msg">
                <button @click="submitMessage">Envoyer</button>
            </div>
        </main>
      
        <!-- End page content -->
      </div>
     
   </template>
     
     <script>
    import axios from 'axios';
    import keycloak from '../main.js';
   
   
     export default {
       name: 'Message', // Remplacez par le nom de votre composant
       data() {
         return {
           // Les données de votre composant vont ici
           messages:[],
           msg:'',
 
         };
       },
       methods: {
        showDeleteIcon(messageId) {
        const message = this.messages.find(message => message.id === messageId);
        if (message && message.sender === 'Medecin') {
            message.showDeleteIcon = true;
        }
    },
    hideDeleteIcon(messageId) {
        const message = this.messages.find(message => message.id === messageId);
        if (message && message.sender === 'Medecin') {
            message.showDeleteIcon = false;
        }
    },
        
        async submitMessage() {
            const accessToken = keycloak.token; // Remplacez par votre jeton d'accès

// Définissez l'en-tête d'autorisation avec le jeton d'accès
const config = {
  headers: {
    'Authorization': `Bearer ${accessToken}` // Assurez-vous de mettre le type d'autorisation (Bearer) avant le jeton
  }
};
const id = this.$route.params.id;

    // Envoie les données à votre API Backend en utilisant Axios
    try {
       await axios.post('http://192.168.224.1:8080/api/message/doc', {contenu:this.msg,patient_id:id},config);
       await axios.post('http://192.168.224.1:8080/api/notification/doc', { contexte:"un nouveau message",cible:id},config);


      // Gérez la réponse de l'API (par exemple, affichez un message de succès)
      console.log('message envoyé avec succès !');
      this.fetchMessage();
      // Réinitialisez le champ de texte
      this.msg = '';
      
      
    } catch (error) {
      console.error('Erreur lors de l\'ajout du message :', error);
      // Gérez les erreurs de l'API (par exemple, affichez un message d'erreur)
    }
},  
fetchMessage() {
  const accessToken = keycloak.token; // Remplacez par votre jeton d'accès

  // Définissez l'en-tête d'autorisation avec le jeton d'accès
  const config = {
    headers: {
      'Authorization': `Bearer ${accessToken}` // Assurez-vous de mettre le type d'autorisation (Bearer) avant le jeton
    }
  };
  const id = this.$route.params.id;


  axios.get(`http://192.168.224.1:8080/api/message/MedecinMP/${id}`, config) // Utilisez la configuration avec l'en-tête d'autorisation
    .then(response => {
      this.messages = response.data;
    })
    .catch(error => {
      console.error('Erreur lors de la récupération des  du messages :', error);
    });
    
},
async supprimerMessage(MessageId) {
    const accessToken = keycloak.token; // Remplacez par votre jeton d'accès

// Définissez l'en-tête d'autorisation avec le jeton d'accès
const config = {
  headers: {
    'Authorization': `Bearer ${accessToken}` // Assurez-vous de mettre le type d'autorisation (Bearer) avant le jeton
  }
};
    if (confirm("Êtes-vous sûr de vouloir supprimer ce Message ?")) {
      try {
        // Envoyez une requête de suppression à votre API Backend en utilisant l'ID du conseil
         await axios.delete(`http://192.168.224.1:8080/api/message/${MessageId}`,config);

        // Gérez la réponse de l'API (par exemple, affichez un message de succès)
        console.log('Message supprimé avec succès !');

        // Mettez à jour la liste des conseils en supprimant le conseil supprimé
        this.messages = this.messages.filter(m => m.id !== MessageId);
      } catch (error) {
        console.error('Erreur lors de la suppression du message :', error);
        // Gérez les erreurs de l'API (par exemple, affichez un message d'erreur)
      }
    }
  }

 


        
       },
       mounted(){
         // Les propriétés calculées de votre composant vont ici
         this.fetchMessage();
       
       },
       // Autres options de composant (comme "props", "watch", etc.) vont ici
     };
     </script>
     
     <style scoped>
     html,body,h1,h2,h3,h4,h5 {font-family: "Raleway", sans-serif}
/* Styles de la page de chat */
.chat {
    border: none;
    
    margin: 10px;
    border-radius: 10px;
    padding: 10px;
    margin-bottom: 20px;
    background-color: #f7f7f7;
   
   
}



.doctor {
    background-color: #B2E0FF; /* Bleu pour les messages du patient */
    color: #000; /* Texte noir pour les messages du patient */
    text-align: right;
    /* Ajuste la div à la taille du texte */
    
   
  }
  
  .patient {
    background-color: #DCF8C6; /* Vert pour les messages du médecin */
    color: #000; /* Texte noir pour les messages du médecin */
    text-align: left;
    
    
  }

.message-input {
    display: flex;
    justify-content: space-between;
    align-items: center;
    border-top: bone;
    padding: 10px;
    
}

input[type="text"] {
    width: 80%;
    padding: 5px;
    border: 1px solid #ccc;
    border-radius: 5px;
}

button {
    background-color: #007BFF;
    color: #fff;
    border: none;
    padding: 5px 10px;
    border-radius: 5px;
    cursor: pointer;
}
.w3-main
  {
    height: 100%;
    min-height: 100vh; /*le code qui m'a permis d'étendre la div sur toute la page */
  }
  
   
     </style>
     