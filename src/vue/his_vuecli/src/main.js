import Vue from 'vue'
import App from './App.vue'
import router from './router'
import axios from 'axios'
import qs from 'qs'

import store from './store'
// import './plugins/element.js'


import ElementUI from 'element-ui';



import 'element-ui/lib/theme-chalk/index.css';

// 全局使用
Vue.use(ElementUI);



Vue.config.productionTip = false

//设置axios的基础url部分
axios.defaults.baseURL = 'http://localhost:8080/';
//将axios挂载到vue实例上，使用时就可以 this.$axios 这样使用了
Vue.prototype.$axios = axios;

Vue.prototype.$qs = qs;





new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
