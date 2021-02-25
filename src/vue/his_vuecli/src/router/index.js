import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import DoctorLogin from '../views/DoctorLogin.vue'
import PatientLogin from '../views/PatientLogin.vue'
import DoctorRegiste from '../views/DoctorRegiste.vue'
import PatientRegister from '../views/PatientRegister.vue'


Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/DoctorLogin',
    name: 'DoctorLogin',
    component: DoctorLogin
  },
  {
    path: '/PatientLogin',
    name: 'PatientLogin',
    component: PatientLogin
  },
  {
    path: '/DoctorRegiste',
    name: 'DoctorRegiste',
    component: DoctorRegiste
  },
  {
    path: '/PatientRegister',
    name: 'PatientRegister',
    component: PatientRegister
  },

]

const router = new VueRouter({
  routes
})

export default router
