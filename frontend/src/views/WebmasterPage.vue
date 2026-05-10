<template>
  <div class="webmaster-page container mx-auto px-4 py-8">
    <h1 class="text-xl font-bold text-center mb-8 text-transparent bg-clip-text bg-gradient-to-r from-violet-600 to-indigo-600">
      站长介绍
    </h1>
    
    <div v-if="webmaster" class="grid grid-cols-1 lg:grid-cols-3 gap-6">
      <!-- Left Column -->
      <div class="space-y-5">
        <!-- Signature -->
        <div class="bg-white rounded-2xl shadow-sm border border-slate-100 p-5 card-hover">
          <h3 class="text-sm font-bold mb-3 text-violet-600 flex items-center">
             <span class="w-1 h-4 bg-violet-600 rounded-full mr-2.5"></span>个性签名
          </h3>
          <div class="relative">
            <span class="absolute top-0 left-0 text-3xl text-slate-200 font-serif leading-none">"</span>
            <p class="text-sm text-slate-600 italic leading-relaxed px-4 py-2 text-center">{{ webmaster.signature }}</p>
            <span class="absolute bottom-0 right-0 text-3xl text-slate-200 font-serif leading-none">"</span>
          </div>
        </div>

        <!-- Clock -->
        <div class="bg-gradient-to-br from-violet-500 to-indigo-600 text-white rounded-2xl shadow-lg shadow-indigo-200 p-5 relative overflow-hidden card-hover">
          <div class="absolute top-0 right-0 w-20 h-20 bg-white/10 rounded-full -mr-10 -mt-10 blur-xl"></div>
          <h3 class="text-sm font-bold mb-3 opacity-90 flex items-center"><svg class="w-4 h-4 mr-2" fill="none" stroke="currentColor" viewBox="0 0 24 24"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 8v4l3 3m6-3a9 9 0 11-18 0 9 9 0 0118 0z"></path></svg>当前时间</h3>
          <div class="text-center py-2">
            <div class="text-3xl font-mono font-bold tracking-widest text-shadow-sm">{{ currentTime }}</div>
            <div class="text-xs mt-1 opacity-80 font-medium">{{ currentDate }}</div>
          </div>
        </div>

        <!-- Contact Info -->
        <div class="bg-white rounded-2xl shadow-sm border border-slate-100 p-5 card-hover">
          <h3 class="text-sm font-bold mb-4 text-slate-800 flex items-center">
             <span class="w-1 h-4 bg-slate-800 rounded-full mr-2.5"></span>联系方式
          </h3>
          <div class="space-y-3">
            <div v-if="webmaster.email" class="flex items-center text-xs text-slate-600 group hover:text-violet-600 transition-colors cursor-default">
              <div class="w-8 h-8 rounded-full bg-slate-50 flex items-center justify-center mr-3 group-hover:bg-violet-50 transition-colors">📧</div> 
              {{ webmaster.email }}
            </div>
            <div v-if="webmaster.wechat" class="flex items-center text-xs text-slate-600 group hover:text-green-600 transition-colors cursor-default">
              <div class="w-8 h-8 rounded-full bg-slate-50 flex items-center justify-center mr-3 group-hover:bg-green-50 transition-colors">💬</div>  
              {{ webmaster.wechat }}
            </div>
            <div v-if="webmaster.qq" class="flex items-center text-xs text-slate-600 group hover:text-blue-500 transition-colors cursor-default">
              <div class="w-8 h-8 rounded-full bg-slate-50 flex items-center justify-center mr-3 group-hover:bg-blue-50 transition-colors">🐧</div> 
              {{ webmaster.qq }}
            </div>
            <div v-if="webmaster.phone" class="flex items-center text-xs text-slate-600 group hover:text-indigo-600 transition-colors cursor-default">
              <div class="w-8 h-8 rounded-full bg-slate-50 flex items-center justify-center mr-3 group-hover:bg-indigo-50 transition-colors">📱</div> 
              {{ webmaster.phone }}
            </div>
          </div>
        </div>
      </div>

      <!-- Right Column -->
      <div class="lg:col-span-2">
        <div class="bg-white rounded-2xl shadow-md border border-slate-50 p-6 card-hover h-full relative overflow-hidden">
          <div class="absolute top-0 left-0 w-full h-2 bg-gradient-to-r from-violet-500 to-indigo-500"></div>
          <div class="flex items-center space-x-6 mb-8 mt-2 pb-6 border-b border-slate-50">
            <div class="relative">
              <div class="absolute inset-0 bg-gradient-to-br from-violet-500 to-indigo-500 rounded-full blur opacity-30 transform translate-y-2"></div>
              <img :src="webmaster.avatar" :alt="webmaster.name" class="relative w-24 h-24 rounded-full shadow-lg ring-4 ring-white object-cover">
            </div>
            <div>
              <h2 class="text-xl font-bold text-slate-800 tracking-tight">{{ webmaster.name }}</h2>
              <div class="flex items-center mt-2 space-x-2">
                 <span class="px-2.5 py-0.5 rounded-full bg-violet-50 text-violet-600 text-xs font-bold border border-violet-100">站长</span>
                 <span class="px-2.5 py-0.5 rounded-full bg-indigo-50 text-indigo-600 text-xs font-bold border border-indigo-100">开发者</span>
              </div>
            </div>
          </div>
          
          <h3 class="text-base font-bold mb-4 text-slate-800 pl-4 border-l-4 border-violet-500">自我介绍</h3>
          <div class="prose prose-slate prose-sm max-w-none">
            <p class="leading-relaxed whitespace-pre-wrap text-justify text-slate-600">{{ webmaster.introduction }}</p>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted, onUnmounted } from 'vue'
import { getWebmasterInfo } from '@/api/webmaster'

const webmaster = ref(null)
const currentTime = ref('')
const currentDate = ref('')

const updateTime = () => {
  const now = new Date()
  currentTime.value = now.toLocaleTimeString('zh-CN')
  currentDate.value = now.toLocaleDateString('zh-CN', { weekday: 'long', year: 'numeric', month: 'long', day: 'numeric' })
}

let timeInterval = null

onMounted(async () => {
  try {
    webmaster.value = await getWebmasterInfo()
  } catch (error) {
    console.error('Error:', error)
  }
  updateTime()
  timeInterval = setInterval(updateTime, 1000)
})

onUnmounted(() => {
  if (timeInterval) clearInterval(timeInterval)
})
</script>
