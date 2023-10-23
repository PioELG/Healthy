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
      <div class="w3-bar w3-top w3-black w3-large" style="z-index: 4">
        <aside :class="{ active: active }">
          <span
            class="w3-bar-item w3-right"
            style="font-family: 'Pacifico', cursive"
            >Healthy</span
          >
        </aside>

        <div v-if="doc">
          <router-link to="/" class="link">
            <a href="#" class="w3-hide-large w3-large">
              <i class="fa fa-home" style="margin: 10px"></i>
            </a>
          </router-link>

          <router-link class="link" to="/ListePatient">
            <a href="#" class="w3-hide-large w3-large">
              <i class="fa fa-users fa-fw" style="margin: 10px"></i>
            </a>
          </router-link>

          <router-link class="link" to="/rdvMedecin">
            <a href="#" class="w3-hide-large w3-large">
              <i class="fa fa-eye fa-fw" style="margin: 10px"></i>
            </a>
          </router-link>

          <router-link to="/PublierConseil" class="link">
            <a href="#" class="w3-hide-large w3-large">
              <i class="fa fa-heart fa-fw" style="margin: 10px"></i>
            </a>
          </router-link>

          <router-link to="/ListeMedicaments" class="link">
            <a href="#" class="w3-hide-large w3-large">
              <i class="fa fa-medkit fa-fw" style="margin: 10px"></i>
            </a>
          </router-link>

          <router-link to="/ListePathologies" class="link">
            <a href="#" class="w3-hide-large w3-large">
              <i class="fa fa-bug fa-fw" style="margin: 10px"></i>
            </a>
          </router-link>

          <a href="#" class="w3-hide-large w3-large link" @click="logout">
            <i class="fas fa-sign-out-alt" style="margin: 10px"></i>
          </a>
        </div>
        <div v-if="!doc">
          <router-link to="/" class="link">
            <a href="#" class="w3-hide-large w3-large">
              <i class="fa fa-home" style="margin: 10px"></i>
            </a>
          </router-link>

          <router-link to="/rdvPatient" class="link">
            <a href="#" class="w3-hide-large w3-large">
              <i class="fa fa-eye fa-fw" style="margin: 10px"></i>
            </a>
          </router-link>

          <router-link to="/MesConseilsP" class="link">
            <a href="#" class="w3-hide-large w3-large">
              <i class="fa fa-heart fa-fw" style="margin: 10px"></i>
            </a>
          </router-link>

          <router-link to="/MaSante" class="link">
            <a href="#" class="w3-hide-large w3-large">
              <i class="fa fa-stethoscope fa-fw" style="margin: 10px"></i>
            </a>
          </router-link>

          <a href="#" class="w3-hide-large w3-large link" @click="logout">
            <i class="fas fa-sign-out-alt" style="margin: 10px"></i>
          </a>
        </div>
      </div>
      <SideBarMedecin v-if="active" />
    </body>
  </html>
</template>

<script>
import SideBarMedecin from "./SideBarMedecin.vue";
import keycloak from "../main.js";
import jwtDecode from "jwt-decode";
export default {
  name: "Header",
  components: {
    SideBarMedecin,
  },

  data() {
    return {
      doc: "",
      decodedToken: {},
      roles: {},
    };
  },
  methods: {
    logout() {
      keycloak.logout();
      this.$router.push("/");
    },
  },
  mounted() {
    if (keycloak.token) {
      this.decodedToken = jwtDecode(keycloak.token);
      this.roles = this.decodedToken.realm_access.roles;
      if (this.roles.includes("patient")) {
        console.log("hello patient");
        this.doc = false;
      }
      if (this.roles.includes("medecin")) {
        console.log("hello doc");
        this.doc = true;
        console.log(this.doc);
      }
    }
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
.w3-bar w3-top w3-black w3-large {
  z-index: 4;
}
.link {
  text-decoration: none;
}
</style>
