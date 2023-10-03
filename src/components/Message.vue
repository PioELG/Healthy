<template>
    <div class="w3-main" style="margin-left:300px;margin-top:43px;">

        <!-- Header -->
       
        <main>
          <div class="chat">
              <div class="message patient">
                  <p>John Doe : Bonjour, docteur. J'ai des douleurs à la poitrine.</p>
              </div>
              <div class="message doctor">
                  <p>Moi : Bonjour. Je suis là pour vous aider. Pouvez-vous décrire vos symptômes plus en détail ?</p>
              </div>
              <!-- Ajoutez d'autres messages ici -->
          </div>
          <div class="message-input">
              <input type="text" placeholder="Écrivez votre message...">
              <button>Envoyer</button>
          </div>
      </main>
       
      
        <!-- End page content -->
      </div>
     
   </template>
     
     <script>
   
    import keycloak from '../main.js';
   
   
     export default {
       name: 'Message', // Remplacez par le nom de votre composant
       data() {
         return {
           // Les données de votre composant vont ici
           doc:'',
           decodedToken:{},
           roles:{}
 
         };
       },
       methods: {
         // Les méthodes de votre composant vont ici
 
         
       },
       mounted(){
         // Les propriétés calculées de votre composant vont ici
         
       
       },
       // Autres options de composant (comme "props", "watch", etc.) vont ici
     };
     </script>
     
     <style scoped>
     html,body,h1,h2,h3,h4,h5 {font-family: "Raleway", sans-serif}
/* Styles de la page de chat */
.chat {
    border: none;
    padding: 10px;
    margin-bottom: 20px;
    background-color: #f7f7f7;
}

.message {
    padding: 10px;
    margin: 10px;
    border-radius: 5px;
}

.patient {
    background-color: #DCF8C6;
}

.doctor {
    background-color: #B2E0FF;
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

   
     </style>
     