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
        <a href="#" class="w3-hide-large w3-large">
          <i class="fa fa-bars" style="margin: 10px" @click="toggleSidebar"></i>
        </a>
      </div>
      <SideBarMedecin v-if="active" />
    </body>
  </html>
</template>

<script>
import SideBarMedecin from "./SideBarMedecin.vue";
export default {
  name: "Header",
  components: {
    SideBarMedecin,
  },

  data() {
    return {
      active: false,
    };
  },
  methods: {
    toggleSidebar() {
      this.active = !this.active;
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
.w3-container-rounded {
  border-radius: 10px; 
}
.w3-container-rounded:hover {
  transform: translate(
    5px,
    -5px
  ); 
  transition: transform 0.2s ease; 
}
.w3-bar w3-top w3-black w3-large {
  z-index: 4;
}
</style>
