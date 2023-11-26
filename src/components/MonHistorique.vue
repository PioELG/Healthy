<template>
  <!DOCTYPE html>
  <html>
    <head>
      <link
        rel="stylesheet"
        href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"
      />
    </head>
    <body class="w3-light-grey">
      <div class="w3-main" style="margin-left: 300px; margin-top: 20px">
        <header style="margin-left: 20px">
          <h5>
            <b><i class="fa fa-history fa-fw"></i> Mon Historique</b>
          </h5>
        </header>

        <div
          v-for="medicament in medicaments"
          :key="medicament.id"
          style="margin-left: 45px; margin-top: 20px; margin-right: 45px"
          class="medoc"
        >
          <p>
            <strong
              >Vous avez prescrit du {{ medicament.nom }} à
              {{ getNomPrenom(medicament.patient_id).nom }}
              {{ getNomPrenom(medicament.patient_id).prenom }} le
              {{ formatDate(medicament.datePresc) }}</strong
            >
          </p>
        </div>
      </div>
    </body>
  </html>
</template>

<script>
import keycloak from "@/main";
import axios from "axios";
export default {
  name: "MesConseils",
  data() {
    return {
      medicaments: [],
      malades: [],
    };
  },
  methods: {
    getNomPrenom(maladeId) {
      const malade = this.malades.find((m) => m.id === maladeId);
      if (malade) {
        return {
          nom: malade.nom,
          prenom: malade.prenom,
        };
      }
      return { nom: "", prenom: "" };
    },
    async fetchMedicaments() {
      const accessToken = keycloak.token;
      const config = {
        headers: {
          Authorization: `Bearer ${accessToken}`,
        },
      };

      try {
        const response = await axios.get(
          "http://localhost:8080/api/medicament/medecin",
          config
        );
        this.medicaments = response.data;

        axios
          .get("http://localhost:8080/api/malade", config)

          .then((response) => {
            this.malades = response.data;

            console.log(this.messages);
          })
          .catch((error) => {
            console.error(
              "Erreur lors de la récupération des malades :",
              error
            );
          });
      } catch (error) {
        console.error("Erreur lors de la récupération des médicaments:", error);
      }
    },
    formatDate(dateString) {
      const currentDate = new Date();
      const targetDate = new Date(dateString);

      if (
        targetDate.getDate() === currentDate.getDate() + 1 &&
        targetDate.getMonth() === currentDate.getMonth() &&
        targetDate.getFullYear() === currentDate.getFullYear()
      ) {
        return "demain";
      } else {
        const options = {
          weekday: "long",
          year: "numeric",
          month: "long",
          day: "numeric",
        };
        return targetDate.toLocaleDateString("fr-FR", options);
      }
    },
  },
  mounted() {
    this.fetchMedicaments();
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
#customers {
  font-family: Arial, Helvetica, sans-serif;
  border-collapse: collapse;
  width: 100%;
}

.w3-main {
  height: 100%;
  min-height: 100vh; /*le code qui m'a permis d'étendre la div sur toute la page */
}
.medoc {
  background-color: #ffffff;
  padding: 1rem;
  border-radius: 5px;
}
</style>
