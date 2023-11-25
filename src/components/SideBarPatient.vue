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
      <div style="margin-top: 40px">
        <nav
          class="w3-sidebar w3-collapse w3-white w3-animate-left"
          style="z-index: 3; width: 300px"
          ref="mySidebar"
        >
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
          <router-link to="/" class="link">
            <div class="w3-container">
              <h5 style="margin-right: 50px">
                <strong>Tableau de Bord</strong>
              </h5>
            </div>
          </router-link>
          <div class="w3-bar-block">
            <router-link to="/rdvPatient" class="link">
              <a href="#" class="w3-bar-item w3-button w3-padding"
                ><i class="fa fa-eye fa-fw"></i>  Rendez-vous</a
              ></router-link
            >
            <router-link to="/MesConseilsP" class="link"
              ><a href="#" class="w3-bar-item w3-button w3-padding"
                ><i class="fa fa-heart fa-fw"></i> ConseilsSanté
              </a></router-link
            >
            <router-link to="/MaSante" class="link"
              ><a href="#" class="w3-bar-item w3-button w3-padding"
                ><i class="fa fa-stethoscope fa-fw"></i>&nbsp;&nbsp;MaSanté 
              </a></router-link
            >
            <br />
            <a
              href="#"
              class="w3-bar-item w3-button w3-padding"
              @click="logout"
            >
              &nbsp;<strong> Se déconnecter</strong> </a
            ><br /><br />
          </div>
        </nav>

        <div
          class="w3-overlay w3-hide-large w3-animate-opacity"
          style="cursor: pointer"
          title="close side menu"
          ref="overlayBg"
        ></div>
      </div>
    </body>
  </html>
</template>

<script>
import keycloak from "../main.js";
import jwtDecode from "jwt-decode";

export default {
  name: "SideBarPatient",
  data() {
    return {
      decodedToken: {},
    };
  },
  mounted() {
    if (keycloak.token) {
      this.decodedToken = jwtDecode(keycloak.token);
      console.log(this.decodedToken.given_name);
      this.$router.push("/");
    }
  },

  methods: {
    logout() {
      this.$router.push("/");

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
  border-radius: 10px;
}
.w3-container-rounded:hover {
  transform: translate(5px, -5px);
  transition: transform 0.2s ease;
}
.link {
  text-decoration: none;
}
</style>
