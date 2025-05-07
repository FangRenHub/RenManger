import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      redirect: '/home'
    },
    {
      path: '/',
      name: 'manager',
      component: import('../views/Manager.vue'),
      children: [
        {
          path: 'home',
          name: 'home',
          component: import('../views/Home.vue'),
        },
        {
          path: 'test',
          name: 'test',
          component: import('../views/test.vue'),
        },
      ],
    },
    {
      path: '/NotFound',
      name: 'NotFound',
      component: import('../views/NotFound.vue'),
    },
    {
      path: '/:pathMatch(.*)*',
      redirect: '/NotFound',
    },
  ],
})

export default router
