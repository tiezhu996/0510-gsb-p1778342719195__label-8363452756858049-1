<template>
  <header class="sticky top-0 z-50 transition-all duration-300 glass-effect">
    <div class="container mx-auto">
      <div class="flex items-center justify-between py-3">
        <!-- Logo -->
        <router-link to="/" class="flex items-center space-x-3 group select-none">
          <div class="relative w-10 h-10 rounded-full overflow-hidden shadow-sm group-hover:shadow-md transition-all duration-300 ring-2 ring-white/50 group-hover:ring-teal-400">
            <img src="@/assets/images/logo.png" alt="Charm of Yanbian" class="w-full h-full object-cover transform group-hover:scale-110 transition-transform duration-500">
          </div>
          <div class="flex flex-col">
            <span class="text-lg font-bold tracking-wide text-gradient-primary leading-tight">魅力延边</span>
            <span class="text-[10px] text-slate-500 tracking-wider font-medium uppercase">延边朝鲜族自治州</span>
          </div>
        </router-link>
        
        <!-- Desktop Navigation -->
        <nav class="hidden md:flex items-center space-x-1">
          <router-link 
            v-for="item in navItems" 
            :key="item.path"
            :to="item.path"
            class="nav-item relative px-4 py-2 text-sm font-medium text-slate-600 rounded-xl transition-all duration-300 hover:text-teal-600 hover:bg-teal-50/50"
            :class="{ 'text-teal-700 bg-teal-50 font-bold': item.path === '/' ? $route.path === '/' : $route.path.startsWith(item.path) }"
          >
            {{ item.name }}
            <span class="absolute bottom-1.5 left-1/2 w-0 h-0.5 bg-teal-500 rounded-full transform -translate-x-1/2 transition-all duration-300 group-hover:w-1/2" 
                  :class="{ 'w-1/3': item.path === '/' ? $route.path === '/' : $route.path.startsWith(item.path) }"></span>
          </router-link>
        </nav>
        
        <!-- Mobile Menu Button -->
        <button @click="mobileMenuOpen = !mobileMenuOpen" class="md:hidden p-2 text-slate-600 hover:bg-slate-100 rounded-xl transition-colors active:scale-95">
          <div class="w-6 h-5 flex flex-col justify-between">
            <span class="w-full h-0.5 bg-current rounded-full transition-transform duration-300 origin-left" :class="{ 'rotate-45 translate-x-0.5': mobileMenuOpen }"></span>
            <span class="w-full h-0.5 bg-current rounded-full transition-opacity duration-300" :class="{ 'opacity-0': mobileMenuOpen }"></span>
            <span class="w-full h-0.5 bg-current rounded-full transition-transform duration-300 origin-left" :class="{ '-rotate-45 translate-x-0.5': mobileMenuOpen }"></span>
          </div>
        </button>
      </div>
      
      <!-- Mobile Navigation -->
      <transition 
        enter-active-class="transition duration-200 ease-out" 
        enter-from-class="transform -translate-y-4 opacity-0" 
        enter-to-class="transform translate-y-0 opacity-100"
        leave-active-class="transition duration-150 ease-in" 
        leave-from-class="transform translate-y-0 opacity-100" 
        leave-to-class="transform -translate-y-4 opacity-0"
      >
        <nav v-if="mobileMenuOpen" class="md:hidden py-4 border-t border-slate-100/50 space-y-1">
          <router-link 
            v-for="item in navItems" 
            :key="item.path"
            :to="item.path"
            @click="mobileMenuOpen = false"
            class="block py-3 px-4 text-sm font-medium text-slate-600 hover:text-teal-600 hover:bg-teal-50 rounded-xl transition-colors"
            :class="{ 'bg-teal-50 text-teal-700 font-bold': item.path === '/' ? $route.path === '/' : $route.path.startsWith(item.path) }"
          >
            {{ item.name }}
          </router-link>
        </nav>
      </transition>
    </div>
  </header>
</template>

<script setup>
import { ref } from 'vue'
import { useRoute } from 'vue-router'

const route = useRoute()
const mobileMenuOpen = ref(false)

const navItems = [
  { name: '首页', path: '/' },
  { name: '家乡简介', path: '/introduction' },
  { name: '风景名胜', path: '/scenic' },
  { name: '特色美食', path: '/food' },
  { name: '站长介绍', path: '/webmaster' },
  { name: '在线调查', path: '/survey' }
]
</script>

<style scoped>
/* No extra scoped styles needed thanks to glass-effect class */
</style>
