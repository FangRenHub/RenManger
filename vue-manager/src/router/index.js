import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    { path: '/', redirect: '/manager/home'},
    {
      path: '/manager',
      component: () => import('../views/Manager.vue'),
      children: [
        {
          path: 'admin',
          meta: { name: '/管理员信息'},
          component: () => import('../views/Admin.vue')
        },   
        {
          path: 'home',
          component: () => import('../views/Home.vue')
        }
      ] 
    },
    {
      path: '/NotFound',
      component: import('../views/NotFound.vue'),
    },
    {
      path: '/:pathMatch(.*)*',
      redirect: '/NotFound',
    },
  ],
})

export default router
