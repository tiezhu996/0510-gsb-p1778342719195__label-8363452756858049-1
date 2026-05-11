<template>
  <div class="food-page container mx-auto px-4 py-8">
    <h1 class="text-3xl font-bold text-center mb-10 text-slate-800 relative inline-block w-full">
      <span class="relative z-10 text-gradient-primary">延边特色美食</span>
      <span class="absolute bottom-1 left-1/2 transform -translate-x-1/2 w-24 h-3 bg-orange-200/50 -z-0 blur-sm rounded-full"></span>
    </h1>
    
    <!-- Top Section -->
    <div class="grid grid-cols-1 lg:grid-cols-3 gap-6 mb-10">
      <!-- Featured Food Carousel (Simplified) -->
      <div class="glass-card p-5 card-hover border-orange-100/50">
        <h3 class="text-base font-bold mb-4 text-slate-800 flex items-center">
           <span class="w-1.5 h-5 bg-gradient-to-b from-orange-400 to-red-500 rounded-full mr-3 shadow-md shadow-orange-200"></span>推荐美食
        </h3>
        <div v-if="featuredFoods.length" class="relative group cursor-pointer overflow-hidden rounded-xl shadow-md">
          <img :src="featuredFoods[0].coverImage" :alt="featuredFoods[0].name" class="w-full h-48 object-cover transform group-hover:scale-110 transition-transform duration-700">
          <div class="absolute inset-x-0 bottom-0 bg-gradient-to-t from-black/90 via-black/40 to-transparent p-4">
             <h4 class="font-bold text-lg text-white mb-1">{{ featuredFoods[0].name }}</h4>
             <p class="text-xs text-white/80 line-clamp-1">{{ featuredFoods[0].description }}</p>
          </div>
        </div>
      </div>

      <!-- Overall Introduction -->
      <div class="glass-card p-6 border-orange-100/50 relative overflow-hidden group">
        <div class="absolute top-0 right-0 w-32 h-32 bg-orange-400/10 rounded-full blur-3xl -mr-16 -mt-16 pointer-events-none"></div>
        <h3 class="text-lg font-bold mb-4 text-orange-900 flex items-center">
          <span class="text-2xl mr-2">🥘</span> 美食文化
        </h3>
        <p class="text-sm text-slate-600 leading-relaxed mb-6 text-justify relative z-10">
          延边美食融合了朝鲜族传统饮食文化与当地特色，以冷面、打糕、泡菜等闻名。朝鲜族饮食讲究色香味俱全，药食同源，每一道菜都是文化的传承...
        </p>
      </div>

      <!-- Ranking -->
      <div class="glass-card p-5 border-rose-100/50">
        <h3 class="text-base font-bold mb-4 text-slate-800 flex items-center">
          <span class="w-1.5 h-5 bg-gradient-to-b from-rose-400 to-pink-500 rounded-full mr-3 shadow-md shadow-rose-200"></span>人气榜
        </h3>
        <ol class="space-y-3">
          <li v-for="(food, idx) in ranking.slice(0, 5)" :key="food.id" class="flex items-center space-x-3 group cursor-pointer p-2 rounded-lg hover:bg-rose-50/50 transition-colors" @click="$router.push(`/food/${food.id}`)">
            <span class="flex-shrink-0 w-6 h-6 rounded-lg flex items-center justify-center text-xs font-bold shadow-sm transition-transform group-hover:scale-110"
                  :class="idx < 3 ? 'bg-gradient-to-br from-rose-400 to-orange-500 text-white shadow-rose-200' : 'bg-slate-100 text-slate-500'">
              {{ idx + 1 }}
            </span>
            <span class="flex-1 text-sm text-slate-600 group-hover:text-rose-600 truncate transition-colors font-medium">
              {{ food.name }}
            </span>
            <span class="text-xs text-rose-500 flex items-center bg-white px-2 py-0.5 rounded-full shadow-sm font-bold">🔥 {{ food.popularity }}</span>
          </li>
        </ol>
      </div>
    </div>

    <!-- Bottom Section - 4 Featured Foods -->
    <h2 class="text-xl font-bold text-slate-800 mb-6 flex items-center">
      <span class="w-1.5 h-6 bg-teal-500 rounded-full mr-3"></span> 精选推荐
    </h2>
    <div class="grid grid-cols-2 lg:grid-cols-4 gap-5">
      <div v-for="food in featuredFoods.slice(0, 4)" :key="food.id"
           class="glass-card overflow-hidden cursor-pointer group hover:-translate-y-1 transition-all duration-300"
           @click="$router.push(`/food/${food.id}`)">
        <div class="h-40 overflow-hidden relative">
           <img :src="food.coverImage" :alt="food.name" class="w-full h-full object-cover group-hover:scale-110 transition-transform duration-700">
           <div class="absolute top-2 right-2 bg-black/50 backdrop-blur-md px-2.5 py-1 rounded-lg text-[10px] text-white font-bold border border-white/20">
             {{ food.category }}
           </div>
        </div>
        <div class="p-4">
          <h4 class="font-bold text-sm mb-1.5 text-slate-800 truncate group-hover:text-teal-600 transition-colors">{{ food.name }}</h4>
          <p class="text-xs text-slate-500 line-clamp-1 mb-3">{{ food.description }}</p>
          <div class="flex items-center justify-between pt-2 border-t border-slate-50">
            <span class="text-xs text-rose-600 font-bold bg-rose-50 px-2 py-0.5 rounded-md">{{ food.priceRange }}</span>
            <span class="text-xs text-amber-500 font-bold flex items-center"><svg class="w-3.5 h-3.5 mr-1 fill-current" viewBox="0 0 20 20"><path d="M9.049 2.927c.3-.921 1.603-.921 1.902 0l1.07 3.292a1 1 0 00.95.69h3.462c.969 0 1.371 1.24.588 1.81l-2.8 2.034a1 1 0 00-.364 1.118l1.07 3.292c.3.921-.755 1.688-1.54 1.118l-2.8-2.034a1 1 0 00-1.175 0l-2.8 2.034c-.784.57-1.838-.197-1.539-1.118l1.07-3.292a1 1 0 00-.364-1.118L2.98 8.72c-.783-.57-.38-1.81.588-1.81h3.461a1 1 0 00.951-.69l1.07-3.292z"></path></svg>{{ food.rating }}</span>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { getFoodFeatured, getFoodRanking } from '@/api/food'

const featuredFoods = ref([])
const ranking = ref([])

onMounted(async () => {
  try {
    featuredFoods.value = await getFoodFeatured()
    ranking.value = await getFoodRanking()
  } catch (error) {
    console.error('Error:', error)
  }
})
</script>
