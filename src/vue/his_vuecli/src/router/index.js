import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import DoctorLogin from '../views/DoctorLogin.vue'
import PatientLogin from '../views/PatientLogin.vue'
import DoctorRegiste from '../views/DoctorRegiste.vue'
import PatientRegister from '../views/PatientRegister.vue'
import DoctorForgetPassword from '../views/DoctorForgetPassword.vue'
import PatientForgetPassword from '../views/PatientForgetPassword.vue'
import DoctorChangePassword from '../views/DoctorChangePassword.vue'
import PatientChangePassword from '../views/PatientChangePassword.vue'
import DoctorHome from '../views/DoctorHome.vue'
import AdminHome from '../views/AdminHome.vue'
import CashierHome from '../views/CashierHome.vue'

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
  {
    path: '/DoctorForgetPassword',
    name: 'DoctorForgetPassword',
    component: DoctorForgetPassword
  },
  {
    path: '/PatientForgetPassword',
    name: 'PatientForgetPassword',
    component: PatientForgetPassword
  },
  {
    path: '/DoctorChangePassword',
    name: 'DoctorChangePassword',
    component: DoctorChangePassword
  },
  {
    path: '/PatientChangePassword',
    name: 'PatientChangePassword',
    component: PatientChangePassword
  },
  {
    path: '/DoctorHome',
    name: 'DoctorHome',
    component: DoctorHome
  },
  {
    path: '/AdminHome',
    name: 'AdminHome',
    component: AdminHome
  },
  {
    path: '/CashierHome',
    name: 'CashierHome',
    component: CashierHome
  },
]

const router = new VueRouter({
  routes
})

export default router
