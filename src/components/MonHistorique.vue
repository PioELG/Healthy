<template>
  <!DOCTYPE html>
  <html>
    <head>
      <title>W3.CSS Template</title>
      <meta charset="UTF-8" />
      <meta name="viewport" content="width=device-width, initial-scale=1" />
      <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css" />
      <link
        rel="stylesheet"
        href="https://fonts.googleapis.com/css?family=Raleway"
      />
      <link
        rel="stylesheet"
        href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"
      />
    </head>
    <body>
      <div
        class="container"
        style="margin-left: 300px; margin-top: 60px; margin-bottom: 500px"
      >
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
              {{ medicament.datePresc }}</strong
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
          "http://192.168.224.1:8080/api/medicament/medecin",
          config
        );
        this.medicaments = response.data;

        axios
          .get("http://192.168.224.1:8080/api/malade", config) 

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
  background-color: #f5f5f5e2;
  padding: 1rem;
  border-radius: 5px;
}
</style>
