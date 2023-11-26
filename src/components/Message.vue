<template>
  <!DOCTYPE html>
  <html>
    <head>
      <link
        rel="stylesheet"
        href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"
      />
    </head>
    <body>
  <div class="w3-main" style="margin-left: 300px; margin-top: 20px">

    <main>
      <br />
      <div
        class="chat"
        v-for="message in messages"
        :key="message.id"
        v-bind:class="{
          patient: message.sender === 'Patient',
          doctor: message.sender === 'Medecin',
        }"
        @mouseover="showDeleteIcon(message.id)"
        @mouseleave="hideDeleteIcon(message.id)"
      >
        <p>{{ message.contenu }}</p>
        <span
          class="delete-icon"
          @click="supprimerMessage(message.id)"
          v-if="message.sender === 'Medecin' && message.showDeleteIcon"
        >
          <i class="fa fa-trash" style="color: red"></i>
        </span>

      </div>
      <div class="message-input">
        <input
          type="text"
          placeholder="Écrivez votre message..."
          v-model="msg"
        />
        <button @click="submitMessage">Envoyer</button>
      </div>
    </main>

  </div>
  </body>
  </html>
</template>

<script>
import axios from "axios";
import keycloak from "../main.js";

export default {
  name: "Message", 
  data() {
    return {
      messages: [],
      msg: "",
    };
  },
  methods: {
    showDeleteIcon(messageId) {
      const message = this.messages.find((message) => message.id === messageId);
      if (message && message.sender === "Medecin") {
        message.showDeleteIcon = true;
      }
    },
    hideDeleteIcon(messageId) {
      const message = this.messages.find((message) => message.id === messageId);
      if (message && message.sender === "Medecin") {
        message.showDeleteIcon = false;
      }
    },

    async submitMessage() {
      const accessToken = keycloak.token; 

      const config = {
        headers: {
          Authorization: `Bearer ${accessToken}`, 
        },
      };
      const id = this.$route.params.id;

      try {
        await axios.post(
          "http://localhost:8080/api/message/doc",
          { contenu: this.msg, patient_id: id },
          config
        );
        await axios.post(
          "http://localhost:8080/api/notification/doc",
          { contexte: "un nouveau message", cible: id },
          config
        );

        console.log("message envoyé avec succès !");
        this.fetchMessage();
        this.msg = "";
      } catch (error) {
        console.error("Erreur lors de l'ajout du message :", error);
      }
    },
    fetchMessage() {
      const accessToken = keycloak.token; 

      const config = {
        headers: {
          Authorization: `Bearer ${accessToken}`, 
        },
      };
      const id = this.$route.params.id;

      axios
        .get(`http://localhost:8080/api/message/MedecinMP/${id}`, config) 
        .then((response) => {
          this.messages = response.data;
        })
        .catch((error) => {
          console.error(
            "Erreur lors de la récupération des  du messages :",
            error
          );
        });
    },
    async supprimerMessage(MessageId) {
      const accessToken = keycloak.token; 

      const config = {
        headers: {
          Authorization: `Bearer ${accessToken}`, 
        },
      };
      if (confirm("Êtes-vous sûr de vouloir supprimer ce Message ?")) {
        try {
          await axios.delete(
            `http://localhost:8080/api/message/${MessageId}`,
            config
          );

          console.log("Message supprimé avec succès !");

          this.messages = this.messages.filter((m) => m.id !== MessageId);
        } catch (error) {
          console.error("Erreur lors de la suppression du message :", error);
        }
      }
    },
  },
  mounted() {
    this.fetchMessage();
  },
};
</script>

<style scoped>
html,
body,
h1,
h2,
h3,
h4,
h5 {
  font-family: "Raleway", sans-serif;
}
.chat {
  border: none;

  margin: 10px;
  border-radius: 10px;
  padding: 10px;
  margin-bottom: 20px;
  background-color: #f7f7f7;
}

.doctor {
  background-color: #b2e0ff; /* Bleu pour les messages du patient */
  color: #000; /* Texte noir pour les messages du patient */
  text-align: right;
  
}

.patient {
  background-color: #dcf8c6; /* Vert pour les messages du médecin */
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
  background-color: #007bff;
  color: #fff;
  border: none;
  padding: 5px 10px;
  border-radius: 5px;
  cursor: pointer;
}
.w3-main {
  height: 100%;
  min-height: 100vh; /*le code qui m'a permis d'étendre la div sur toute la page */
}
</style>
