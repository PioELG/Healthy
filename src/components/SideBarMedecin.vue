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
      <div>
        <nav
          class="w3-sidebar w3-collapse w3-white w3-animate-left"
          style="z-index: 3; width: 300px"
          ref="mySidebar"
        >
          <!-- ... Votre contenu HTML ici ... -->
          <div class="w3-container w3-row">
            <div class="w3-col s4">
              <img
                src="https://cdn-icons-png.flaticon.com/128/552/552721.png"
                class="w3-circle w3-margin-right"
                style="width: 46px"
              />
            </div>
            <div class="w3-col s8 w3-bar" style="margin-top: 15px">
              <span
                >Bienvenue,
                <strong>{{ decodedToken.given_name }} </strong></span
              ><br />
            </div>
          </div>
          <hr />
          <div class="w3-container">
            <h5 style="margin-left: 7px"><strong>Dashboard</strong></h5>
          </div>
          <div class="w3-bar-block">
            <router-link to="/" class="link">
              <a href="#" class="w3-bar-item w3-button w3-padding"
                ><i class="fa fa-bell fa-fw"></i> Notifications</a
              ></router-link
            >
            <router-link class="link" to="/ListePatient"
              ><a href="#" class="w3-bar-item w3-button w3-padding"
                ><i class="fa fa-users fa-fw"></i>  Patients</a
              ></router-link
            >
            <router-link class="link" to="/rdvMedecin"
              ><a href="#" class="w3-bar-item w3-button w3-padding"
                ><i class="fa fa-eye fa-fw"></i>  Rendez-vous</a
              ></router-link
            >
            <router-link to="/PublierConseil" class="link">
              <a href="#" class="w3-bar-item w3-button w3-padding"
                ><i class="fa fa-heart fa-fw"></i> ConseilsSanté
              </a></router-link
            >
            <router-link to="/ListeMedicaments" class="link">
              <a href="#" class="w3-bar-item w3-button w3-padding"
                ><i class="fa fa-medkit fa-fw"></i>Médicaments</a
              ></router-link
            >
            <router-link to="/" class="link">
              <a href="#" class="w3-bar-item w3-button w3-padding"
                ><i class="fa fa-bug fa-fw"></i>Pathologies</a
              ></router-link
            >
            <a href="#" class="w3-bar-item w3-button w3-padding" @click="logout"
              >  Log Out</a
            ><br /><br />
          </div>
        </nav>

        <!-- Overlay effect when opening sidebar on small screens -->
        <div
          class="w3-overlay w3-hide-large w3-animate-opacity"
          style="cursor: pointer"
          title="close side menu"
          ref="overlayBg"
        ></div>
      </div>
      <!-- Overlay effect when opening sidebar on small screens -->
    </body>
  </html>
</template>

<script>
import keycloak from "../main.js";
import jwtDecode from "jwt-decode";

export default {
  name: "SideBarMedecin", // Remplacez par le nom de votre composant
  data() {
    return {
      decodedToken: {},
    };
  },
  mounted() {
    // Initialiser les variables avec les éléments DOM

    if (keycloak.token) {
      this.decodedToken = jwtDecode(keycloak.token);
      console.log(this.decodedToken.given_name);
    }
  },
  created() {
    // Initialiser les variables avec les éléments DOM
  },
  methods: {
    logout() {
      keycloak.logout();
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
.w3-container-rounded {
  border-radius: 10px; /* Vous pouvez ajuster le rayon selon vos préférences */
}
.w3-container-rounded:hover {
  transform: translate(
    5px,
    -5px
  ); /* Déplacez la div vers le bas et vers la droite */
  transition: transform 0.2s ease; /* Ajoutez une transition fluide */
}
.link {
  text-decoration: none;
}
</style>
