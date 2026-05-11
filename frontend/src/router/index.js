import { createRouter, createWebHistory } from 'vue-router'
import HomePage from '@/views/HomePage.vue'
import IntroductionPage from '@/views/IntroductionPage.vue'
import ScenicPage from '@/views/ScenicPage.vue'
import ScenicDetailPage from '@/views/ScenicDetailPage.vue'
import FoodPage from '@/views/FoodPage.vue'
import FoodDetailPage from '@/views/FoodDetailPage.vue'
import WebmasterPage from '@/views/WebmasterPage.vue'
import SurveyPage from '@/views/SurveyPage.vue'

const router = createRouter({
  history: createWebHistory(),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomePage
    },
    {
      path: '/introduction',
      name: 'introduction',
      component: IntroductionPage
    },
    {
      path: '/scenic',
      name: 'scenic',
      component: ScenicPage
    },
    {
      path: '/scenic/:id',
      name: 'scenic-detail',
      component: ScenicDetailPage
    },
    {
      path: '/food',
      name: 'food',
      component: FoodPage
    },
    {
      path: '/food/:id',
      name: 'food-detail',
      component: FoodDetailPage
    },
    {
      path: '/webmaster',
      name: 'webmaster',
      component: WebmasterPage
    },
    {
      path: '/survey',
      name: 'survey',
      component: SurveyPage
    }
  ],
  scrollBehavior() {
    return { top: 0 }
  }
})

export default router
