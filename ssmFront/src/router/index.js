import Vue from 'vue'
import Router from 'vue-router'
// import HelloWorld from '@/components/HelloWorld'
import Main from '@/view/Main';
import Welcome from '../view/Welcome'
import BookPrice from "../view/BookPrice.vue";
import BookList from "../view/BookList.vue"
Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Main',
      component: Main,
      redirect:"/Welcome",
      children:[{
        path:'/Welcome',
        name:'Welcome',
        component: Welcome
      },
        {
          path:'/BookPrice',
          name:'BookPrice',
          component: BookPrice
        },
        {
          path:'/BookList',
          name:'BookList',
          component:BookList
        }]
    },
  ]
})
