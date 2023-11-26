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
      <div class="w3-main" style="margin-left: 300px; margin-top: 43px">
        <header class="w3-container" style="padding-top: 22px">
          <h5>
            <b
              ><i class="fa fa-heart fa-fw"></i>
              <router-link to="/MesConseils">Mes Conseils</router-link></b
            >
          </h5>
        </header>
        <main>
          <section class="publication-form">
            <h2>Ajouter un nouveau conseil de santé :</h2>
            <form @submit.prevent="submitForm">
              <label for="conseil">Conseil de santé :</label>
              <textarea
                id="conseil"
                name="conseil"
                rows="4"
                v-model="nouveauConseil"
                required
              ></textarea>

              <button type="submit">Publier</button>
            </form>
          </section>
        </main>
      </div>
    </body>
  </html>
</template>

<script>
import keycloak from "@/main";
import axios from "axios";
export default {
  name: "ModifierConseil",
  data() {
    return {
      nouveauConseil: "",
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

      if (!this.nouveauConseil) {
        alert("Veuillez entrer un conseil de santé.");
        return;
      }

      try {
        await axios.post(
          "http://localhost:8080/api/conseils",
          { contenu: this.nouveauConseil },
          config
        );

        console.log("Conseil ajouté avec succès !");
        this.nouveauConseil = "";
      } catch (error) {
        console.error("Erreur lors de l'ajout du conseil :", error);
      }
    },
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
main {
  max-width: 800px;
  margin: 2rem auto;
  padding: 2rem;
  background-color: #fff;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

.publication-form {
  text-align: left;
}

.publication-form h2 {
  font-size: 1.5rem;
  margin-bottom: 1rem;
}

form {
  max-width: 600px;
  margin: 0 auto;
}

label {
  display: block;
  margin-bottom: 0.5rem;
}

input[type="text"],
textarea {
  width: 100%;
  padding: 0.5rem;
  margin-bottom: 1rem;
  border: 1px solid #ccc;
  border-radius: 4px;
}

button[type="submit"] {
  background-color: #007bff;
  color: #fff;
  padding: 0.5rem 1rem;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

button[type="submit"]:hover {
  background-color: #0056b3;
}
.w3-main {
  background-color: #e0e5ea;
}
</style>
