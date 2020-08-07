import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'

// 路由拦截
import './router/permission.js'

/*引入全局样式*/
import "./assets/style/global.css"

/*引入elementUI*/
import Element from 'element-ui'
import "element-ui/lib/theme-chalk/index.css"
Vue.use(Element)

/*引入axios*/
import axios from 'axios'
Vue.prototype.$axios = axios //

/*封装axios全局异常*/
import "./axios.js"

/*mavon-editor编辑器全局注册*/
import mavonEditor from 'mavon-editor'
import 'mavon-editor/dist/css/index.css'
Vue.use(mavonEditor)



Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
