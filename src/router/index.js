import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import MesConseils from '../components/MesConseils.vue'
import Nouvelle from '../components/Nouvelle.vue'
import PublierConseil from '../components/PublierConseil.vue'

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
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
