<template>
  <div class="introduction-page container mx-auto px-4 py-6">
    <div class="grid grid-cols-1 lg:grid-cols-4 gap-6">
      <!-- Left Sidebar - Catalog -->
      <aside class="lg:col-span-1">
        <div class="bg-white rounded-2xl shadow-sm border border-slate-100 p-5 sticky top-20 card-hover">
          <h2 class="text-sm font-bold mb-3 text-slate-800 border-b border-slate-100 pb-3 flex items-center">
            <span class="w-1 h-4 bg-teal-500 rounded-full mr-2"></span>目录导航
          </h2>
          <nav class="space-y-1">
            <a v-for="section in sections" :key="section.id"
               :href="`#section-${section.id}`"
               class="block py-2.5 px-3 rounded-lg text-xs font-medium text-slate-500 hover:bg-slate-50 hover:text-teal-600 transition-all border-l-2 border-transparent hover:border-teal-500">
              {{ section.sectionName }}
            </a>
          </nav>
        </div>
      </aside>

      <!-- Main Content -->
      <main class="lg:col-span-3 space-y-5">
        <!-- Video Section -->
        <div v-if="videos.length" class="bg-white rounded-2xl shadow-sm border border-slate-100 p-5 card-hover">
          <h2 class="text-base font-bold mb-4 text-violet-700 flex items-center">
             <span class="w-1.5 h-1.5 bg-violet-500 rounded-full mr-2.5"></span>
             宣传视频
          </h2>
          <div class="rounded-xl overflow-hidden shadow-lg bg-black aspect-video">
            <video  :src="videos[0].videoUrl" autoplay controls class="w-full h-full object-cover" :poster="videos[0].coverImage"></video>
          </div>
        </div>

        <!-- Introduction Sections -->
        <div v-for="section in sections" :key="section.id" 
             :id="`section-${section.id}`"
             class="bg-white rounded-2xl shadow-sm border border-slate-100 p-6 card-hover group">
          <h2 class="text-lg font-bold mb-4 text-slate-800 flex items-center">
            <span class="w-1.5 h-6 bg-gradient-to-b from-teal-500 to-cyan-500 rounded-full mr-3 group-hover:h-8 transition-all duration-300"></span>
            {{ section.sectionName }}
          </h2>
          <div class="prose prose-sm max-w-none text-slate-600 prose-p:leading-relaxed prose-headings:text-slate-800">
            <p class="whitespace-pre-wrap">{{ section.content }}</p>
          </div>
          <div v-if="section.images" class="grid grid-cols-2 md:grid-cols-3 gap-4 mt-6">
            <div v-for="(img, idx) in JSON.parse(section.images)" :key="idx"
                 class="relative group cursor-zoom-in overflow-hidden rounded-xl shadow-sm hover:shadow-md transition-shadow duration-300"
                 @click="openLightbox(img)">
              <img :src="img" :alt="section.sectionName" class="w-full h-48 object-cover transform group-hover:scale-110 transition-transform duration-700">
              <div class="absolute inset-0 bg-black/0 group-hover:bg-black/10 transition-colors duration-300"></div>
            </div>
          </div>
        </div>
      </main>
    </div>

    <!-- Lightbox Modal -->
    <transition enter-active-class="transition duration-300 ease-out" enter-from-class="opacity-0" enter-to-class="opacity-100" leave-active-class="transition duration-200 ease-in" leave-from-class="opacity-100" leave-to-class="opacity-0">
      <div v-if="lightboxOpen" class="fixed inset-0 z-[100] flex items-center justify-center bg-black/90 backdrop-blur-sm p-4" @click="closeLightbox">
        <div class="relative max-w-5xl max-h-screen w-full flex items-center justify-center">
             <img :src="currentImage" class="max-w-full max-h-[90vh] object-contain rounded-lg shadow-2xl" @click.stop>
             <button class="absolute top-4 right-4 text-white hover:text-gray-300 focus:outline-none" @click="closeLightbox">
               <svg class="w-8 h-8" fill="none" stroke="currentColor" viewBox="0 0 24 24"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M6 18L18 6M6 6l12 12"></path></svg>
             </button>
        </div>
      </div>
    </transition>
  </div>
</template>

<script setup>
import { ref, onMounted, onUnmounted } from 'vue'
import { getIntroductionList, getVideoList } from '@/api/introduction'

const sections = ref([])
const videos = ref([])
const lightboxOpen = ref(false)
const currentImage = ref('')

const openLightbox = (img) => {
  currentImage.value = img
  lightboxOpen.value = true
  document.body.style.overflow = 'hidden'
}

const closeLightbox = () => {
  lightboxOpen.value = false
  setTimeout(() => {
    currentImage.value = ''
  }, 300)
  document.body.style.overflow = ''
}

const handleEsc = (e) => {
  if (e.key === 'Escape' && lightboxOpen.value) {
    closeLightbox()
  }
}

onMounted(async () => {
  try {
    sections.value = await getIntroductionList()
    videos.value = await getVideoList()
    window.addEventListener('keydown', handleEsc)
  } catch (error) {
    console.error('Error:', error)
  }
})

onUnmounted(() => {
  window.removeEventListener('keydown', handleEsc)
})
</script>
