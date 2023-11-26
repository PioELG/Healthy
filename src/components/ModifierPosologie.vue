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
  <div
    class="w3-main"
    style="margin-left: 300px; margin-top: 20px; border-radius: 5px"
  >
    <br />
    <br />
    <div
      class="posologie"
      style="margin-left: 50px; margin-right: 50px; padding: 70px"
    >
      <div class="form-group">
        <label for="quantite">Quantité :</label>
        <input
          type="number"
          id="quantite"
          name="quantite"
          required
          v-model="quantite"
        />
      </div>

      <div class="form-group">
        <label for="unite">Unité :</label>
        <select id="unite" name="unite" v-model="unite">
          <option value="comprimés">Comprimés</option>
          <option value="ml">ml</option>
          <option value="dose">dose</option>
        </select>
      </div>

      <div class="form-group">
        <label for="temps">Temps de Prise :</label>
        <select id="temps" name="temps" required v-model="heurePrise">
          <option value="le matin">Matin</option>
          <option value="à midi">Midi</option>
          <option value="le soir">Soir</option>
          <option value="Toutes les 2h">Toutes les 2 heures</option>
        </select>
      </div>
      <button
        type="button"
        id="ajouterPosologie"
        class="btn"
        @click="submitForm"
      >
        Modifier Posologie
      </button>
    </div>
  </div>
  </body>
  </html>
</template>

<script>
import keycloak from "@/main";
import axios from "axios";
export default {
  name: "ModifierPosologie", 
  data() {
    return {
      quantite: "",
      unite: "",
      heurePrise: "",
      showPosologie: false,
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

      if (this.quantite === "" || this.unite === "" || this.heurePrise === "") {
        this.showError = false;
        alert("Veuillez remplir tous les champs !");
      } else {
        try {
          await axios.put(
            `http://localhost:8080/api/posologie/${id}`,
            {
              quantite: this.quantite,
              unite: this.unite,
              heurePrise: this.heurePrise,
            },
            config
          );

          console.log("Posologie modifiée avec succès !");
          this.quantite = "";
          this.unite = "";
          this.heurePrise = "";

          window.history.back();
        } catch (error) {
          console.error("Erreur lors de la modif de la posologie:", error);
        }
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

h1 {
  text-align: center;
  color: #333;
}

.container {
  max-width: 800px;
  margin: 20px auto;
  padding: 20px;
  background-color: #fff;
  border-radius: 5px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
}

.form-group {
  margin-bottom: 20px;
}

label {
  display: block;
  margin-bottom: 5px;
  font-weight: bold;
}

input[type="text"],
input[type="number"],
select {
  width: 100%;
  padding: 10px;
  margin-bottom: 10px;
  border: 1px solid #ccc;
  border-radius: 5px;
}

select {
  appearance: none;
}

.posologie {
  display: flex;
  justify-content: space-between;
  align-items: center;
  background-color: white;
  border-radius: 5px;
}

.posologie label {
  width: 100%; 
  margin-right: 70 px;
}

.posologie select {
  width: 100%; 
}

.btn {
  display: inline-block;
  padding: 10px 20px;
  background-color: #007bff;
  color: #fff;
  border: none;
  border-radius: 10px;
  cursor: pointer;
}

.btn:hover {
  background-color: #0056b3;
}

#ajouterPrescription {
  margin-top: 20px; 
  background-color: #009900; 
}

@media (max-width: 768px) {
  .container {
    padding: 10px;
  }

  .posologie label {
    width: 45%;
  }

  .posologie input {
    width: 45%;
  }
}

.w3-main {
  height: 100%;
  min-height: 100vh; /*le code qui m'a permis d'étendre la div sur toute la page */
}
</style>
