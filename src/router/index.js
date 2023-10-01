import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import MesConseils from '../components/MesConseils.vue'
import Nouvelle from '../components/Nouvelle.vue'
import PublierConseil from '../components/PublierConseil.vue'
import MaSante from '../components/MaSante.vue'
import AjouterConstantes from '../components/AjouterConstantes.vue'
import MonEtat from '../components/MonEtat.vue'
import AjouterSymptomes from '../components/AjouterSymptomes.vue'
import ListePatient from '../components/ListePatient.vue'
import MesPatients from '../components/MesPatients.vue'
import InfosPatient from '../components/InfosPatient.vue'
import rdvMedecin from '../components/rdvMedecin.vue'
import AjoutRdv from '../components/AjoutRdv.vue'


const routes = [
  {
    path: '/',
    name: 'Nouvelle',
    component: Nouvelle
  },
  {
    path: '/about',
    name: 'about',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
  },
  {
    path: '/MesConseils',
    name: 'MesConseils',
    component: MesConseils // Le composant pour MesConseils
  },
  {
    path: '/PublierConseil',
    name: 'PublierConseil',
    component:PublierConseil
    
  },
  {
    path: '/MaSante',
    name: 'MaSante',
    component:MaSante
    
  },
  {
    path: '/AjouterConstantes',
    name: 'AjouterConstantes',
    component:AjouterConstantes
    
  },
  {
    path: '/MonEtat',
    name: 'MonEtat',
    component:MonEtat
    
  },
  {
    path: '/AjouterSymptomes',
    name: 'AjouterSymptomes',
    component:AjouterSymptomes
    
  },
  {
    path: '/ListePatient',
    name: 'ListePatient',
    component:ListePatient
    
  },
  {
    path: '/MesPatients',
    name: 'MesPatients',
    component:MesPatients
  },
  {
    path: '/InfosPatient/:id',
    name: 'InfosPatient',
    component: InfosPatient
  },
  {
    path: '/rdvMedecin',
    name: 'rdvMedecin',
    component:rdvMedecin
  },
  {
    path:'/AjoutRdv/:id',
    name: 'AjoutRdv',
    component:AjoutRdv
  }
  
  


]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
