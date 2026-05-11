<template>
  <div class="scenic-page container mx-auto px-4 py-8">
    <h1 class="text-3xl font-bold text-center mb-10 text-slate-800 relative inline-block w-full">
      <span class="relative z-10 text-gradient-primary">延边风景名胜</span>
      <span class="absolute bottom-1 left-1/2 transform -translate-x-1/2 w-24 h-3 bg-teal-200/50 -z-0 blur-sm rounded-full"></span>
    </h1>
    
    <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-8">
      <div v-for="scenic in scenicList" :key="scenic.id" 
           class="glass-card overflow-hidden group h-full flex flex-col hover:-translate-y-2 transition-transform duration-500">
        <!-- Image with Overlay -->
        <div class="h-56 overflow-hidden relative">
          <img :src="scenic.coverImage" :alt="scenic.name" class="w-full h-full object-cover group-hover:scale-110 transition-transform duration-[2s]">
          <div class="absolute inset-0 bg-gradient-to-t from-black/60 via-transparent to-transparent opacity-60"></div>
          <div class="absolute top-4 right-4 bg-white/90 backdrop-blur-md px-3 py-1.5 rounded-xl text-xs font-bold text-orange-600 shadow-lg border border-white/40">
            ¥{{ scenic.ticketPrice }}
          </div>
        </div>
        
        <!-- Content -->
        <div class="p-6 flex-1 flex flex-col">
          <h2 class="text-xl font-bold mb-2 text-slate-800 group-hover:text-teal-600 transition-colors">{{ scenic.name }}</h2>
          <p class="text-sm text-slate-500 mb-4 line-clamp-3 leading-relaxed flex-1">{{ scenic.description }}</p>
          
          <div class="flex items-center justify-between pt-5 border-t border-slate-100 mt-auto">
            <div class="flex items-center text-xs text-slate-500 font-medium">
              <span class="mr-3 flex items-center bg-slate-50 px-2 py-1 rounded"><svg class="w-3.5 h-3.5 mr-1 text-slate-400" fill="none" stroke="currentColor" viewBox="0 0 24 24"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M17.657 16.657L13.414 20.9a1.998 1.998 0 01-2.827 0l-4.244-4.243a8 8 0 1111.314 0z"></path><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 11a3 3 0 11-6 0 3 3 0 016 0z"></path></svg>{{ scenic.location }}</span>
              <span class="flex items-center text-amber-500 font-bold"><svg class="w-3.5 h-3.5 mr-1 fill-current" viewBox="0 0 20 20"><path d="M9.049 2.927c.3-.921 1.603-.921 1.902 0l1.07 3.292a1 1 0 00.95.69h3.462c.969 0 1.371 1.24.588 1.81l-2.8 2.034a1 1 0 00-.364 1.118l1.07 3.292c.3.921-.755 1.688-1.54 1.118l-2.8-2.034a1 1 0 00-1.175 0l-2.8 2.034c-.784.57-1.838-.197-1.539-1.118l1.07-3.292a1 1 0 00-.364-1.118L2.98 8.72c-.783-.57-.38-1.81.588-1.81h3.461a1 1 0 00.951-.69l1.07-3.292z"></path></svg>{{ scenic.rating }}</span>
            </div>
            <router-link :to="`/scenic/${scenic.id}`" 
                         class="text-xs font-bold text-white bg-gradient-to-r from-teal-500 to-emerald-500 hover:from-teal-600 hover:to-emerald-600 px-4 py-2 rounded-xl shadow-md shadow-teal-200 transition-all active:scale-95">
              查看详情
            </router-link>
          </div>
        </div>
      </div>
    </div>

    <!-- Pagination -->
    <div v-if="total > pageSize" class="flex justify-center mt-12 space-x-3">
      <button @click="changePage(current - 1)" :disabled="current === 1"
              class="px-5 py-2.5 bg-white text-slate-600 rounded-xl shadow-sm border border-slate-200 hover:border-teal-400 hover:text-teal-600 disabled:opacity-50 disabled:cursor-not-allowed transition-all font-medium text-sm flex items-center">
        <svg class="w-4 h-4 mr-1" fill="none" stroke="currentColor" viewBox="0 0 24 24"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 19l-7-7 7-7"></path></svg> 上一页
      </button>
      <div class="flex items-center px-4 bg-slate-50 rounded-xl text-sm font-bold text-slate-600">
        <span class="text-teal-600">{{ current }}</span>
        <span class="mx-2 text-slate-300">/</span>
        <span>{{ Math.ceil(total / pageSize) }}</span>
      </div>
      <button @click="changePage(current + 1)" :disabled="current >= Math.ceil(total / pageSize)"
              class="px-5 py-2.5 bg-white text-slate-600 rounded-xl shadow-sm border border-slate-200 hover:border-teal-400 hover:text-teal-600 disabled:opacity-50 disabled:cursor-not-allowed transition-all font-medium text-sm flex items-center">
        下一页 <svg class="w-4 h-4 ml-1" fill="none" stroke="currentColor" viewBox="0 0 24 24"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 5l7 7-7 7"></path></svg>
      </button>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { getScenicList } from '@/api/scenic'

const scenicList = ref([])
const current = ref(1)
const pageSize = ref(6)
const total = ref(0)

const fetchData = async (page = 1) => {
  try {
    const res = await getScenicList({ current: page, size: pageSize.value })
    scenicList.value = res.records
    total.value = res.total
    current.value = res.current
  } catch (error) {
    console.error('Error:', error)
  }
}

const changePage = (page) => {
  if (page >= 1 && page <= Math.ceil(total.value / pageSize.value)) {
    fetchData(page)
    window.scrollTo({ top: 0, behavior: 'smooth' })
  }
}

onMounted(() => fetchData())
</script>
