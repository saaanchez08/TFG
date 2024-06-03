import { createRouter, createWebHistory } from 'vue-router'

//import AboutView from '../views/AboutView.vue'
import HomeView from "../views/HomeView.vue";
import Alquiler from '../views/Alquiler.vue';
import Categorias from '../views/Categorias.vue'



const routes = [
 
  {
    // path: '/about',
    // name: 'about',
    // // División del código a nivel de ruta
    // // Esto genera un chunk separado (about.[hash].js) para esta ruta
    // // que se carga de manera perezosa cuando la ruta es visitada.
    // component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
    path: "/",
    name: "home",
    component: HomeView,
},
{
  path: '/alquiler',
  name: 'Alquiler',
  component: Alquiler
},
{
  path: '/categorias',
  name: 'Categorias',
  component: Categorias
}
  
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
