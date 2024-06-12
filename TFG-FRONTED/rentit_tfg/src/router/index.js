import { createRouter, createWebHistory } from 'vue-router'

//import AboutView from '../views/AboutView.vue'
import HomeView from "../views/HomeView.vue";
import Alquiler from '../views/Alquiler.vue';
import Categorias from '../views/Categorias.vue';
import Materiales from '../views/Materiales.vue';




const routes = [
 
  {
   
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
},
{
  path: '/materiales',
  name: 'Materiales',
  component: Materiales
}
  
  
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
