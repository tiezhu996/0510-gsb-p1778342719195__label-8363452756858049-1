<template>
  <footer class="bg-gradient-to-br from-slate-900 via-slate-800 to-slate-900 text-slate-300 py-12 mt-auto border-t border-slate-700/50">
    <div class="container mx-auto">
      <div class="grid grid-cols-1 md:grid-cols-3 gap-10">
        <!-- Brand -->
        <div class="space-y-4">
          <div class="flex items-center space-x-2">
            <div class="w-8 h-8 rounded-full bg-white/10 p-1">
              <img src="@/assets/images/logo.png" alt="Logo" class="w-full h-full object-cover rounded-full">
            </div>
            <span class="text-xl font-bold text-white tracking-wide">魅力延边</span>
          </div>
          <p class="text-sm text-slate-400 leading-relaxed max-w-xs">
            探索中国边疆的瑰宝，体验独特的朝鲜族风情，品味地道的特色美食。
          </p>
        </div>

        <!-- Quick Links -->
        <div class="flex flex-col space-y-3">
          <h4 class="text-white font-semibold mb-2">快速导航</h4>
          <div class="grid grid-cols-2 gap-2 text-sm">
            <router-link to="/" class="hover:text-teal-400 transition-colors">首页</router-link>
            <router-link to="/food" class="hover:text-teal-400 transition-colors">美食推荐</router-link>
            <router-link to="/introduction" class="hover:text-teal-400 transition-colors">家乡简介</router-link>
            <router-link to="/scenic" class="hover:text-teal-400 transition-colors">风景名胜</router-link>
            <router-link to="/webmaster" class="hover:text-teal-400 transition-colors">联系我们</router-link>
          </div>
        </div>

       
      </div>

      <div class="border-t border-white/5 mt-10 pt-6 flex flex-col md:flex-row justify-between items-center text-xs text-slate-500">
        <p>&copy; 2024 魅力延边·朝鲜族自治州. 版权所有.</p>
      </div>
    </div>
  </footer>
</template>

<script setup>
</script>
