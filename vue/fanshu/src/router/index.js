import Vue from 'vue'
import Router from 'vue-router'
import List from '../pages/List'
import SingUp from '../pages/SingUp'
import HelloWorld from '@/components/HelloWorld'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'HelloWorld',
      component: HelloWorld
    },
    {
      path: '/list',
      name: 'List',
      component: List
    },
    {
      path: '/singUp',
      name: 'SingUp',
      component: SingUp
    }
  ]
})
