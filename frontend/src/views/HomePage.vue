<template>
  <div class="home-page pb-12">
    <!-- Hero/Carousel Section -->
    <section class="carousel-section mb-10 pt-6">
      <div class="container mx-auto">
        <div v-if="carousels.length" class="relative h-[480px] rounded-3xl overflow-hidden shadow-2xl group transition-all duration-500 hover:shadow- teal-500/20">
          <div v-for="(item, index) in carousels" :key="item.id" 
               v-show="currentSlide === index"
               class="absolute inset-0 transition-opacity duration-1000">
            <img :src="item.imageUrl" :alt="item.title" class="w-full h-full object-cover transform scale-105 group-hover:scale-100 transition-transform duration-[2s]">
            <!-- Enhanced Gradient Overlay -->
            <div class="absolute inset-0 bg-gradient-to-t from-slate-900/90 via-slate-900/40 to-transparent"></div>
            
            <div class="absolute bottom-0 left-0 right-0 p-8 md:p-12 transform translate-y-2 group-hover:translate-y-0 transition-transform duration-500">
              <div class="max-w-3xl">
                <span class="inline-block px-3 py-1 mb-4 text-xs font-bold tracking-wider text-teal-300 uppercase bg-teal-900/50 backdrop-blur-md rounded-full border border-teal-500/30">
                  精选目的地
                </span>
                <h2 class="text-4xl md:text-5xl font-bold text-white tracking-tight mb-4 drop-shadow-lg leading-tight">
                  {{ item.title }}
                </h2>
                <div class="h-1.5 w-24 bg-gradient-to-r from-teal-400 to-blue-500 rounded-full mb-6"></div>
                <p class="text-slate-200 text-lg line-clamp-2 max-w-2xl opacity-0 group-hover:opacity-100 transition-opacity duration-500 delay-100 hidden md:block">
                  探索延边的无限魅力，体验独特的风土人情...
                </p>
              </div>
            </div>
          </div>
          
          <!-- Navigation Arrows -->
          <button @click="prevSlide" class="absolute left-6 top-1/2 -translate-y-1/2 w-12 h-12 flex items-center justify-center bg-white/10 hover:bg-white/30 backdrop-blur-md text-white rounded-full opacity-0 group-hover:opacity-100 transition-all duration-300 border border-white/20 hover:scale-110">
            <svg class="w-6 h-6" fill="none" stroke="currentColor" viewBox="0 0 24 24"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2.5" d="M15 19l-7-7 7-7"></path></svg>
          </button>
          <button @click="nextSlide" class="absolute right-6 top-1/2 -translate-y-1/2 w-12 h-12 flex items-center justify-center bg-white/10 hover:bg-white/30 backdrop-blur-md text-white rounded-full opacity-0 group-hover:opacity-100 transition-all duration-300 border border-white/20 hover:scale-110">
            <svg class="w-6 h-6" fill="none" stroke="currentColor" viewBox="0 0 24 24"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2.5" d="M9 5l7 7-7 7"></path></svg>
          </button>
          
          <!-- Dots Indicator -->
          <div class="absolute bottom-6 right-8 flex space-x-2">
            <button v-for="(item, index) in carousels" :key="index"
                    @click="currentSlide = index"
                    class="w-2.5 h-2.5 rounded-full transition-all duration-300"
                    :class="currentSlide === index ? 'bg-teal-400 w-8' : 'bg-white/40 hover:bg-white/80'">
            </button>
          </div>
        </div>
      </div>
    </section>

    <!-- Content Section -->
    <section class="content-section">
      <div class="container mx-auto">
        <div class="grid grid-cols-1 lg:grid-cols-12 gap-8">
          
          <!-- Left Column (News & Main Content) -->
          <div class="lg:col-span-8 space-y-8">
            <!-- News Section -->
            <div class="glass-card p-6 md:p-8">
              <div class="flex items-center justify-between mb-8">
                <h2 class="text-2xl font-bold text-slate-800 flex items-center">
                  <span class="w-2 h-8 bg-gradient-to-b from-teal-400 to-blue-500 rounded-full mr-4 shadow-lg shadow-teal-500/30"></span>
                  最新动态
                  <span class="ml-3 text-xs font-normal text-slate-400 uppercase tracking-widest hidden sm:inline-block">最新资讯</span>
                </h2>
              </div>
              
              <div class="grid grid-cols-1 gap-6">
                <div v-for="news in newsList" :key="news.id" class="flex flex-col sm:flex-row gap-5 group cursor-pointer p-4 rounded-xl hover:bg-slate-50 transition-colors border border-transparent hover:border-slate-100">
                  <div class="overflow-hidden rounded-2xl w-full sm:w-48 h-32 flex-shrink-0 shadow-md">
                    <img :src="news.coverImage" :alt="news.title" class="w-full h-full object-cover group-hover:scale-110 transition-transform duration-700">
                  </div>
                  <div class="flex-1 py-1 flex flex-col justify-between">
                    <div>
                      <div class="flex items-center space-x-2 mb-2">
                        <span class="px-2 py-0.5 rounded text-[10px] font-bold bg-blue-100 text-blue-600 uppercase">新闻</span>
                        <span class="text-xs text-slate-400">{{ news.createTime || '2023-10-24' }}</span>
                      </div>
                      <h3 class="text-lg font-bold text-slate-800 mb-2 group-hover:text-teal-600 transition-colors line-clamp-1">{{ news.title }}</h3>
                      <p class="text-slate-500 text-sm line-clamp-2 leading-relaxed">{{ news.summary }}</p>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>

          <!-- Right Column (Sidebar) -->
          <div class="lg:col-span-4 space-y-8">
            <!-- Introduction Card -->
            <div class="relative rounded-3xl overflow-hidden shadow-xl group h-[300px]">
              <img src="https://images.unsplash.com/photo-1545569341-9eb8b30979d9?w=800&q=80" alt="Intro" class="absolute inset-0 w-full h-full object-cover group-hover:scale-110 transition-transform duration-[3s]">
              <div class="absolute inset-0 bg-gradient-to-br from-indigo-900/90 to-violet-900/40 mix-blend-multiply"></div>
              <div class="absolute inset-0 bg-gradient-to-t from-black/60 to-transparent"></div>
              
              <div class="relative z-10 p-8 h-full flex flex-col justify-end">
                <div class="transform translate-y-4 group-hover:translate-y-0 transition-transform duration-500">
                  <h3 class="text-2xl font-bold text-white mb-2 flex items-center">
                    <span class="w-8 h-8 rounded-full bg-white/20 backdrop-blur flex items-center justify-center mr-3 text-lg">🏔️</span>
                    家乡简介
                  </h3>
                  <p class="text-indigo-100 text-sm leading-relaxed mb-6 opacity-90 line-clamp-3">
                    延边朝鲜族自治州位于吉林省东部，长白山下，图们江畔。这里山清水秀，风情万种，不仅有壮丽的自然风光...
                  </p>
                  <router-link to="/introduction" class="inline-flex items-center justify-center w-full py-3 text-sm font-bold text-white bg-white/20 hover:bg-white/30 backdrop-blur-md rounded-xl transition-all border border-white/20 hover:border-white/40">
                    探索更多 
                  </router-link>
                </div>
              </div>
            </div>

            <!-- Scenic Recommendation -->
            <div class="glass-card p-6">
              <div class="flex items-center justify-between mb-6">
                <h3 class="text-lg font-bold text-slate-800">风景名胜</h3>
                <router-link to="/scenic" class="text-xs font-semibold text-teal-500 hover:text-teal-700 bg-teal-50 px-2 py-1 rounded">查看全部</router-link>
              </div>
              <div class="grid grid-cols-2 gap-4">
                <div v-for="scenic in scenicList.slice(0, 4)" :key="scenic.id" 
                     class="group cursor-pointer" @click="$router.push(`/scenic/${scenic.id}`)">
                  <div class="relative overflow-hidden rounded-2xl aspect-square mb-3 shadow-sm">
                    <img :src="scenic.coverImage" :alt="scenic.name" class="w-full h-full object-cover group-hover:scale-110 transition-transform duration-700">
                    <div class="absolute inset-0 bg-gradient-to-t from-black/50 to-transparent opacity-0 group-hover:opacity-100 transition-opacity duration-300"></div>
                  </div>
                  <p class="text-sm font-bold text-center text-slate-700 group-hover:text-teal-600 transition-colors truncate">{{ scenic.name }}</p>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
  </div>
</template>

<script setup>
import { ref, onMounted, onUnmounted } from 'vue'
import { getCarouselList, getNewsLatest, getScenicRecommend } from '@/api/home'

const carousels = ref([])
const newsList = ref([])
const scenicList = ref([])
const currentSlide = ref(0)
let slideInterval = null

const fetchData = async () => {
  try {
    carousels.value = await getCarouselList()
    newsList.value = await getNewsLatest()
    scenicList.value = await getScenicRecommend()
  } catch (error) {
    console.error('Failed to fetch data:', error)
  }
}

const nextSlide = () => {
  currentSlide.value = (currentSlide.value + 1) % carousels.value.length
}

const prevSlide = () => {
  currentSlide.value = (currentSlide.value - 1 + carousels.value.length) % carousels.value.length
}

const startAutoSlide = () => {
  slideInterval = setInterval(nextSlide, 5000)
}

const stopAutoSlide = () => {
  if (slideInterval) {
    clearInterval(slideInterval)
  }
}

onMounted(() => {
  fetchData()
  startAutoSlide()
})

onUnmounted(() => {
  stopAutoSlide()
})
</script>
