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
      <header class="w3-container" style="padding-top: 22px">
        <h5>
          <b><i class="fa fa-address-book fa-fw"></i> Mes Rendez-vous</b>
        </h5>
      </header>

      <div
        class="w3-main"
        style="margin-left: 300px; margin-top: 20px; margin-right: 50px"
      >
        <div class="container">
          <h2>Ajouter Rendez-vous</h2>
          <form @submit.prevent="submitForm">
            <div class="form-group">
              <label for="nom">Jour du Rendez-vous:</label>
              <input type="date" id="nom" name="nom" v-model="date" required />
            </div>
            <div class="form-group">
              <label for="prenom">Heure du Rendez-vous :</label>
              <input
                type="time"
                id="prenom"
                name="prenom"
                v-model="heure"
                required
              />
            </div>

            <div class="form-group">
              <input type="submit" value="Modifier " />
            </div>
          </form>
        </div>
      </div>
    </body>
  </html>
</template>

<script>
import keycloak from "@/main";
import axios from "axios";
export default {
  name: "ModifierRdv",
  data() {
    return {
      date: "",
      heure: "",
    };
  },
  methods: {
    async submitForm() {
      const accessToken = keycloak.token;

      const config = {
        headers: {
          Authorization: `Bearer ${accessToken}`,
        },
      };
      const id = this.$route.params.id;

      try {
        await axios.put(
          `http://localhost:8080/api/rdv/${id}`,
          { date: this.date, heure: this.heure, malade_id: id },
          config
        );

        console.log("rdv ajouté avec succès !");
        this.date = "";
        this.heure = "";
        window.history.back();
      } catch (error) {
        console.error("Erreur lors de la modif du rdv :", error);
      }
    },
  },
  mounted() {},
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

.container {
  max-width: 600px;
  margin: 20px auto;
  padding: 20px;
  background-color: #fff;
  border-radius: 5px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
}

h1 {
  text-align: center;
  color: #333;
}

.form-group {
  margin-bottom: 20px;
}

label {
  font-weight: bold;
}

input[type="text"],
input[type="number"],
input[type="email"] {
  width: 100%;
  padding: 10px;
  margin-top: 5px;
  border: none;
  border-radius: 5px;
  background-color: #f4f4f4;
}

input[type="submit"] {
  background-color: #00b300;
  color: #fff;
  padding: 10px 20px;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}

input[type="submit"]:hover {
  background-color: #00b300;
}
input[type="date"] {
  width: 100%;
  padding: 10px;
  margin-top: 5px;
  border: none;
  border-radius: 5px;
  background-color: #f4f4f4;
}
input[type="time"] {
  width: 100%;
  padding: 10px;
  margin-top: 5px;
  border: none;
  border-radius: 5px;
  background-color: #f4f4f4;
}
.w3-main {
  height: 100%;
  min-height: 100vh; /*le code qui m'a permis d'étendre la div sur toute la page */
}
</style>
