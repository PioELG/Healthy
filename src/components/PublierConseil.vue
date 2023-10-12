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
    <body class="w3-light-grey">
      <!-- Top container -->
      <div class="w3-main" style="margin-left: 300px; margin-top: 43px">
        <!-- Header -->
        <header class="w3-container" style="padding-top: 22px">
          <h5>
            <b
              ><i class="fa fa-heart fa-fw"></i>
              <router-link to="/MesConseils" class="link"
                >Mes Conseils</router-link
              ></b
            >
          </h5>
        </header>

        <div class="publication-form">
          <h2>Ajouter un nouveau conseil de santé :</h2>
          <form @submit.prevent="submitForm">
            <textarea
              id="conseil"
              name="conseil"
              rows="4"
              v-model="nouveauConseil"
            ></textarea>

            <button type="submit">Publier</button>
          </form>
        </div>

        <!-- End page content -->
      </div>
    </body>
  </html>
</template>

<script>
import keycloak from "@/main";
import axios from "axios";
export default {
  name: "PublierConseil", // Remplacez par le nom de votre composant
  data() {
    return {
      // Les données de votre composant vont ici
      nouveauConseil: "",
    };
  },
  methods: {
    async submitForm() {
      const accessToken = keycloak.token; // Remplacez par votre jeton d'accès

      // Définissez l'en-tête d'autorisation avec le jeton d'accès
      const config = {
        headers: {
          Authorization: `Bearer ${accessToken}`, // Assurez-vous de mettre le type d'autorisation (Bearer) avant le jeton
        },
      };

      // Vérifiez si le champ "nouveauConseil" est vide
      if (!this.nouveauConseil) {
        alert("Veuillez entrer un conseil de santé.");
        return;
      }

      // Envoie les données à votre API Backend en utilisant Axios
      try {
        await axios.post(
          "http://192.168.224.1:8080/api/conseils",
          { contenu: this.nouveauConseil },
          config
        );
        await axios.post(
          "http://192.168.224.1:8080/api/notification/doc",
          { contexte: "un nouveau conseil de santé", cible: "tous" },
          config
        );

        // Gérez la réponse de l'API (par exemple, affichez un message de succès)
        console.log("Conseil ajouté avec succès !");
        // Réinitialisez le champ de texte
        this.nouveauConseil = "";
      } catch (error) {
        console.error("Erreur lors de l'ajout du conseil :", error);
        // Gérez les erreurs de l'API (par exemple, affichez un message d'erreur)
      }
    },
    // Les méthodes de votre composant vont ici
  },
  computed: {
    // Les propriétés calculées de votre composant vont ici
  },
  // Autres options de composant (comme "props", "watch", etc.) vont ici
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
h5 {
  margin-left: 50px;
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
  height: 100%;
  min-height: 100vh;
}
.link {
  text-decoration: none;
}
.publication-form {
  border-radius: 20px;

  max-width: 800px;
  margin: 2rem auto;
  padding: 2rem;
  background-color: #fff;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}
</style>
