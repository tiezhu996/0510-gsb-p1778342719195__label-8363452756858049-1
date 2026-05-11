<template>
  <div v-if="food" class="food-detail container mx-auto px-4 py-4">
    <div class="bg-white rounded-lg shadow-sm border border-gray-100 overflow-hidden">
      <div class="h-64 overflow-hidden">
        <img :src="food.coverImage" :alt="food.name" class="w-full h-full object-cover">
      </div>
      <div class="p-5">
        <h1 class="text-xl font-bold mb-3 text-orange-600">{{ food.name }}</h1>
        <div class="flex flex-wrap items-center gap-4 mb-4 text-sm text-gray-500">
          <span class="flex items-center"><span class="mr-1">🏷</span>{{ food.category }}</span>
          <span class="flex items-center font-medium text-orange-600"><span class="mr-1">💰</span>{{ food.priceRange }}</span>
          <span class="flex items-center text-yellow-500"><span class="mr-1">⭐</span>{{ food.rating }} 分</span>
          <span class="flex items-center"><span class="mr-1">🔥</span>人气: {{ food.popularity }}</span>
        </div>
        <div class="prose prose-sm max-w-none border-t border-gray-100 pt-4">
          <p class="text-gray-700 leading-relaxed whitespace-pre-wrap">{{ food.detailContent }}</p>
        </div>
      </div>
    </div>
    <div class="text-center mt-6">
      <button @click="$router.back()" class="bg-gray-600 text-white px-5 py-2 text-sm rounded hover:bg-gray-700 transition-colors">
        返回列表
      </button>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useRoute } from 'vue-router'
import { getFoodDetail, increaseFoodPopularity } from '@/api/food'

const route = useRoute()
const food = ref(null)

onMounted(async () => {
  try {
    food.value = await getFoodDetail(route.params.id)
    await increaseFoodPopularity(route.params.id)
  } catch (error) {
    console.error('Error:', error)
  }
})
</script>
