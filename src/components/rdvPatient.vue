<template>
    <header class="w3-container" style="padding-top:22px">
        <h5><b><i class="fa fa-address-book fa-fw "></i> Mes Rendez-vous</b></h5>
      </header>
    <div class="w3-main" style="margin-left: 300px; margin-top: 43px;">
      <div class="w3-row-padding w3-margin-bottom">
        <table id="customers">
          <tr>
            <th>Nom Docteur</th>
            <th>Prenom Docteur</th>
            <th>Date Rdv</th>
            <th>Heure Rdv</th>
          </tr>
          <tr v-for="rdv in rdvs" :key="rdv.id">
            <td>{{doctor.nom}}</td>
            <td>{{ doctor.prenom }}</td>
             <td>{{ rdv.date }}</td>
            <td>{{ rdv.heure }}</td>
           
          </tr>
        </table>
      </div>
      <br>
    </div>
  </template>
  
  <script>
  import keycloak from '@/main';
  import axios from 'axios';
  
  export default {
    name: 'rdvMedecin',
    data() {
      return {
        rdvs: [],
        doctor: [],
      };
    },
    methods: {
        async fetchRdv() {
        const accessToken = keycloak.token;
        const config = {
          headers: {
            'Authorization': `Bearer ${accessToken}`
          }
        };
  
        try {
          const rdvResponse = await axios.get('http://192.168.224.1:8080/api/rdv/patient', config);
          this.rdvs = rdvResponse.data;
  
          const doctorResponse = await axios.get('http://192.168.224.1:8080/api/doctor', config);
          this.doctor =doctorResponse.data;
        } catch (error) {
          console.error('Erreur lors de la récupération du doctor', error);
        }
      },
      
      
    },
    mounted() {
      this.fetchRdv();
      
    },
  };
  </script>
  
  <style scoped>
  #customers {
    font-family: Arial, Helvetica, sans-serif;
    border-collapse: collapse;
    width: 100%;
  
  }
  
  #customers td, #customers th {
    border: 1px solid #ddd;
    padding: 8px;
  }
  
  #customers tr:nth-child(even){background-color: #f2f2f2;}
  
  #customers tr:hover {background-color: #ddd;}
  
  #customers th {
    padding-top: 12px;
    padding-bottom: 12px;
    text-align: left;
    background-color: #04AA6D;
    color: white;
  }
  .table
  {
    margin-bottom: 80px;
  }
  .link
    {
        text-decoration: none;
    }
  </style>
  