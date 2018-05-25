// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import VueProgressBar from 'vue-progressbar'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'

import App from './App'
import router from './router'
import store from './store'
import api from './api'

Vue.config.productionTip = false

localStorage.setItem('debug', 'leancloud*'); // 开启调试模式

Vue.mixin({
  beforeCreate() {
    if (!this.$api) {
      this.$api = api;
    }
  }
});

const options = {
  color: '#bffaf3',
  failedColor: '#874b4b',
  thickness: '5px',
  transition: {
    speed: '0.2s',
    opacity: '0.6s',
    termination: 300
  },
  autoRevert: true,
  location: 'left',
  inverse: false
}

Vue.use(VueProgressBar, options);
Vue.use(ElementUI);

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  components: {App},
  template: '<App/>'
})
