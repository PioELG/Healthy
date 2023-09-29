<template>
    <!DOCTYPE html>
    <html>
    <head>
    <title>W3.CSS Template</title>
    </head>
    <body>
        <div class="w3-main" style="margin-left:300px;margin-top:0px;">

            <!-- Header -->
            <header class="w3-container" style="padding-top:27px">
              <h5><b><i class="fa fa-dashboard"></i> Mon Tableau de bord</b></h5>
            </header>
          
            <div class="w3-row-padding w3-margin-bottom">
              <div class="w3-quarter">
                <div class="w3-container w3-black w3-padding-16 w3-container-rounded">
                  <div class="w3-left"><i class="fa fa-comment w3-xxxlarge"></i></div>
                  <div class="w3-right">
                    <h3>2</h3>
                  </div>
                  <div class="w3-clear"></div>
                  <h4>MesMessages</h4>
                </div>
              </div>
              
              <div class="w3-quarter">
                <div class="w3-container w3-blue w3-padding-16 w3-container-rounded">
                  <div class="w3-left"><i class="fa fa-eye w3-xxxlarge"></i></div>
                  <div class="w3-right">
                    <h3>2</h3>
                  </div>
                  <div class="w3-clear"></div>
                  <h4>MesRendez-Vous</h4>
                </div>
              </div>
              
              <div class="w3-quarter">
                <router-link to="/Mesconseils" class="link">
                <div class="w3-container w3-teal w3-padding-16 w3-container-rounded">
                  <div class="w3-left"><i class="fa fa-heart w3-xxxlarge"></i></div>
                  <div class="w3-right">
                    <h3>3</h3>
                  </div>
                  <div class="w3-clear"></div>
                  <h4>MesConseils</h4>
                </div>
            </router-link>
              </div>
              
              <div class="w3-quarter">
                <router-link to="/MonEtat" class="link">
                <div class="w3-container w3-orange w3-text-white w3-padding-16 w3-container-rounded">
                  <div class="w3-left"><i class="fa fa-stethoscope w3-xxxlarge" ></i></div>
                  <div class="w3-right">
                    
                  </div>
                  <div class="w3-clear"></div>
                  <h4>MonEtat</h4>
                </div>
            </router-link>
              </div>
              
              
            </div>
          
            <div class="w3-panel">
              <div class="w3-row-padding" style="">
               
                <div class="w3-twothird">
                  <h5>Notifications</h5>
                  <table class="w3-table w3-striped w3-white">
                    <tr>
                      <td><i class="fa fa-stethoscope" style="font-size:24px"></i></td>
                      <td>Vous venez de faire une mis à jour de votre état de santé</td>
                      <td><i>10 mins</i></td>
                    </tr>
                    
                    <tr>
                      <td><i class="fa fa-bell w3-text-red w3-large"></i></td>
                      <td>Vous n'avez pas pris votre médicament à temps</td>
                      <td><i>15 mins</i></td>
                    </tr>
                    
                    <tr>
                      <td><i class="fa fa-comment w3-text-red w3-large"></i></td>
                      <td>Nouveau Message.</td>
                      <td><i>25 mins</i></td>
                    </tr>
                    
                    <tr>
                      <td><i class="fa fa-share-alt w3-text-green w3-large"></i></td>
                      <td>Vous venez de recevoir un nouveau conseil de santé</td>
                      <td><i>39 mins</i></td>
                    </tr>
          
                    <tr>
                      <td><i class="fa fa-address-book w3-text-red w3-large"></i></td>
                      <td>Votre Rendez-vous avec le docteur Mike est pour demain .</td>
                      <td><i>1h</i></td>
                    </tr>
          
                  </table>
                </div>
              </div>
            </div>
        </div>    
     </body>
    </html>
  </template>
  
  <script>
  

  export default {
    name: 'NouvellePatient', // Remplacez par le nom de votre composant
    data() {
    return {
      
    };
  },
  methods: {
    
  },
};
  </script>
  
  <style scoped>
  html,body,h1,h2,h3,h4,h5 {font-family: "Raleway", sans-serif}
.w3-container-rounded {
  border-radius: 10px; /* Vous pouvez ajuster le rayon selon vos préférences */
}
.w3-container-rounded:hover {
  transform: translate(5px, -5px); /* Déplacez la div vers le bas et vers la droite */
  transition: transform 0.2s ease; /* Ajoutez une transition fluide */
}
.w3-container
{
    padding-top:22px
}
.w3-main
{
    background: rgb(235, 235, 238);
    margin-left:300px;
    margin-top:43px;
    height: 100%;
}
.w3-row-padding
{
    margin:0 -16px;

}
.w3-container
{
    padding: 16 px;
}
.link
    {
        text-decoration: none;
    }
  </style>
  