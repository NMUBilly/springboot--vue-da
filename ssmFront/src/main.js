import Vue from 'vue'
import App from './App'
import router from './router'
Vue.config.productionTip = false
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import  axios from 'axios'
import './assets/css/main.css'
import './assets/css/main.css'

Vue.use(ElementUI)
Vue.prototype.$http=axios
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})


