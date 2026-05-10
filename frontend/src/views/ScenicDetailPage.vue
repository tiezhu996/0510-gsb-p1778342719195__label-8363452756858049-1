<template>
  <div v-if="scenic" class="scenic-detail container mx-auto px-4 py-4">
    <div class="bg-white rounded-lg shadow-sm border border-gray-100 overflow-hidden">
      <div class="h-64 overflow-hidden">
        <img :src="scenic.coverImage" :alt="scenic.name" class="w-full h-full object-cover">
      </div>
      <div class="p-5">
        <h1 class="text-xl font-bold mb-3 text-blue-600">{{ scenic.name }}</h1>
        <div class="flex flex-wrap items-center gap-4 mb-4 text-sm text-gray-500">
          <span class="flex items-center"><span class="mr-1">📍</span>{{ scenic.location }}</span>
          <span class="flex items-center"><span class="mr-1">💰</span>¥{{ scenic.ticketPrice }}</span>
          <span class="flex items-center text-yellow-500"><span class="mr-1">⭐</span>{{ scenic.rating }} 分</span>
          <span class="flex items-center"><span class="mr-1">👁</span>{{ scenic.viewCount }} 次浏览</span>
        </div>
        <div class="prose prose-sm max-w-none border-t border-gray-100 pt-4">
          <p class="text-gray-700 leading-relaxed whitespace-pre-wrap">{{ scenic.detailContent }}</p>
        </div>
        <div v-if="scenic.images" class="grid grid-cols-2 md:grid-cols-4 gap-3 mt-5">
          <img v-for="(img, idx) in JSON.parse(scenic.images)" :key="idx"
               :src="img" class="rounded shadow-sm w-full h-32 object-cover hover:scale-105 transition-transform duration-300">
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
import { getScenicDetail, increaseScenicView } from '@/api/scenic'

const route = useRoute()
const scenic = ref(null)

onMounted(async () => {
  try {
    scenic.value = await getScenicDetail(route.params.id)
    await increaseScenicView(route.params.id)
  } catch (error) {
    console.error('Error:', error)
  }
})
</script>
