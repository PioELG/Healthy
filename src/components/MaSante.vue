<template>
    <div class="w3-main" style="margin-left:300px;margin-top:20px;">
      
       
      <div class="container">
        <router-link to="/VoirPrescription" class="link">
          <div class="left-div">
              <h1>Ma Prescription</h1>
              <p>Cliquez ici pour consulter les prescriptions du médecin.</p>
          </div>
        </router-link>
        <router-link to="/AjouterConstantes"  class="link" >
          <div class="right-div">
              <h1>Mon Etat</h1>
              <p >Cliquez ici pour mettre à jour ou surveiller votre état de santé.</p>
          </div>
        </router-link>
      </div>
      
      </div>
     
</template>
  
  <script>

  export default {
    name: 'MaSante', 
    data() {
      return {
      

      };
    },
    methods: {
    },
    mounted(){
     
 }
    ,
  };
  </script>
  
  <style scoped>
  html,body,h1,h2,h3,h4,h5 {font-family: "Raleway", sans-serif}
  .link
    {
        text-decoration: none;
    }
    
          .container {
            display: flex;
            flex-direction: row;
            justify-content: space-between;
            align-items: center;
            height: 100vh;
        }

        .left-div, .right-div {
            flex: 1;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            background-color: #ffffff;
            border-radius: 10px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            margin: 20px;
            height: 400px;
            transition: transform 0.3s ease;
        }

        .left-div:hover, .right-div:hover {
            transform: scale(1.05);
        }

        h1 {
            font-size: 24px;
            margin: 10px 0;
            color: #333;
        }

        p {
            font-size: 16px;
            color: #666;
        }

        @media (max-width: 768px) {
            .container {
                flex-direction: column;
            }

            .left-div, .right-div {
                width: 100%;
                margin-bottom: 20px;
            }
        }

  </style>
  