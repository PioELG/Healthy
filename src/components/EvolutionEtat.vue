<template>
  <div class="w3-main" style="margin-left: 300px; margin-top: 20px">
    <br /><br />
    <div class="historique">
      <h4>Evolution de l'Etat de santé du patient</h4>

      <ul class="chat-list">
        <li
          class="chat-item"
          v-for="constante in constantes"
          :key="constante.id"
        >
          <p>Date modification:{{ constante.dateModif }}</p>
          <div class="chat-preview">
            <p>
              <strong
                ><i class="fa fa-balance-scale" style="color: rgb(0, 0, 0)"></i>
                Poids :</strong
              >
              {{ constante.poids }} kg
            </p>
            <p>
              <strong
                ><i class="fa fa-stethoscope" style="color: green"></i> Tension
                Artérielle :</strong
              >
              {{ constante.pressionArterielle }} mmHg
            </p>
            <p>
              <strong
                ><i class="fa fa-heartbeat" style="color: red"></i> Fréquence
                Cardiaque :</strong
              >
              {{ constante.freqCar }} bpm
            </p>
            <p>
              <strong
                ><i class="fa fa-cloud" style="color: rgb(0, 136, 255)"></i>
                Fréquence Respiratoire :</strong
              >
              {{ constante.freqRes }} cycles/min
            </p>
            <p>
              <strong
                ><i
                  class="fa fa-thermometer"
                  style="color: rgb(236, 141, 6)"
                ></i>
                Température :</strong
              >
              {{ constante.temperature }}°C
            </p>
          </div>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
import keycloak from "../main.js";
import axios from "axios";

export default {
  name: "EvolutionEtat",

  data() {
    return {
      constantes: [],
    };
  },
  methods: {
    fetchConstantes() {
      const accessToken = keycloak.token;

      const config = {
        headers: {
          Authorization: `Bearer ${accessToken}`,
        },
      };
      const id = this.$route.params.id;

      axios
        .get(`http://192.168.224.1:8080/api/constantes/${id}`, config)
        .then((response) => {
          this.constantes = response.data;
        })
        .catch((error) => {
          console.error(
            "Erreur lors de la récupération des constantes :",
            error
          );
        });
    },
  },
  mounted() {
    this.fetchConstantes();
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

.chat-preview {
  flex-grow: 1;
}
.w3-main {
  height: 100%;
  min-height: 100vh; /*le code qui m'a permis d'étendre la div sur toute la page */
}
.historique {
  margin: 20px;
}
</style>
