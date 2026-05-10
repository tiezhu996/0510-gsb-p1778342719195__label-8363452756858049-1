<template>
  <div class="survey-page container mx-auto px-4 py-6 max-w-2xl">
    <div class="bg-white/90 backdrop-blur-sm rounded-lg shadow-sm border border-gray-100 p-6">
      <h1 class="text-xl font-bold text-center mb-6 text-emerald-700">在线调查问卷</h1>
      
      <form @submit.prevent="handleSubmit" class="space-y-5">
        <!-- Name -->
        <div>
          <label class="block text-sm font-medium text-gray-700 mb-1.5">姓名 <span class="text-red-500">*</span></label>
          <input v-model="form.name" type="text" required 
                 class="w-full px-3 py-2 text-sm border border-gray-200 rounded-md focus:ring-2 focus:ring-emerald-500/20 focus:border-emerald-500 transition-colors bg-gray-50/50 focus:bg-white text-gray-700 placeholder-gray-400">
        </div>

        <!-- Gender -->
        <div>
          <label class="block text-sm font-medium text-gray-700 mb-2">性别 <span class="text-red-500">*</span></label>
          <div class="flex space-x-6">
            <label class="flex items-center cursor-pointer group">
              <input v-model="form.gender" type="radio" value="男" class="w-4 h-4 text-emerald-600 border-gray-300 focus:ring-emerald-500">
              <span class="ml-2 text-sm text-gray-600 group-hover:text-emerald-700 transition-colors">男</span>
            </label>
            <label class="flex items-center cursor-pointer group">
              <input v-model="form.gender" type="radio" value="女" class="w-4 h-4 text-emerald-600 border-gray-300 focus:ring-emerald-500">
              <span class="ml-2 text-sm text-gray-600 group-hover:text-emerald-700 transition-colors">女</span>
            </label>
          </div>
        </div>

        <!-- Age Range -->
        <div>
          <label class="block text-sm font-medium text-gray-700 mb-1.5">年龄段 <span class="text-red-500">*</span></label>
          <select v-model="form.ageRange" required
                  class="w-full px-3 py-2 text-sm border border-gray-200 rounded-md focus:ring-2 focus:ring-emerald-500/20 focus:border-emerald-500 bg-gray-50/50 focus:bg-white text-gray-700">
            <option value="">请选择</option>
            <option value="18岁以下">18岁以下</option>
            <option value="18-30岁">18-30岁</option>
            <option value="31-50岁">31-50岁</option>
            <option value="51岁以上">51岁以上</option>
          </select>
        </div>

        <!-- Visit Purpose -->
        <div>
          <label class="block text-sm font-medium text-gray-700 mb-2">访问目的（多选）</label>
          <div class="grid grid-cols-2 md:grid-cols-3 gap-2">
            <label v-for="purpose in purposes" :key="purpose" class="flex items-center p-2 rounded border border-gray-100 hover:bg-gray-50 cursor-pointer transition-colors">
              <input type="checkbox" :value="purpose" v-model="selectedPurposes" class="w-3.5 h-3.5 text-emerald-600 rounded border-gray-300 focus:ring-emerald-500">
              <span class="ml-2 text-xs text-gray-600">{{ purpose }}</span>
            </label>
          </div>
        </div>

        <!-- Satisfaction -->
        <div>
          <label class="block text-sm font-medium text-gray-700 mb-2">满意度 <span class="text-red-500">*</span></label>
          <div class="flex items-center space-x-1 sm:space-x-2">
            <label v-for="n in 5" :key="n" class="cursor-pointer">
              <input v-model.number="form.satisfaction" type="radio" :value="n" class="sr-only peer">
              <div class="px-3 py-1.5 rounded-md border border-gray-200 text-sm peer-checked:bg-emerald-50 peer-checked:text-emerald-700 peer-checked:border-emerald-200 hover:bg-gray-50 transition-all text-center">
                {{ n }} <span class="text-xs">⭐</span>
              </div>
            </label>
          </div>
        </div>

        <!-- Suggestions -->
        <div>
          <label class="block text-sm font-medium text-gray-700 mb-1.5">建议或意见</label>
          <textarea v-model="form.suggestions" rows="3"
                    class="w-full px-3 py-2 text-sm border border-gray-200 rounded-md focus:ring-2 focus:ring-emerald-500/20 focus:border-emerald-500 bg-gray-50/50 focus:bg-white text-gray-700 resize-none"
                    placeholder="您的建议是我们进步的动力..."></textarea>
        </div>

        <!-- Email -->
        <div>
          <label class="block text-sm font-medium text-gray-700 mb-1.5">电子邮箱</label>
          <input v-model="form.email" type="email"
                 class="w-full px-3 py-2 text-sm border border-gray-200 rounded-md focus:ring-2 focus:ring-emerald-500/20 focus:border-emerald-500 bg-gray-50/50 focus:bg-white text-gray-700">
        </div>

        <!-- Buttons -->
        <div class="flex space-x-3 pt-2">
          <button type="submit" class="flex-1 bg-emerald-600 text-white py-2 rounded-md hover:bg-emerald-700 transition shadow-sm hover:shadow text-sm font-medium tracking-wide">
            提交问卷
          </button>
          <button type="button" @click="resetForm" class="flex-1 bg-white text-gray-600 border border-gray-200 py-2 rounded-md hover:bg-gray-50 transition text-sm font-medium">
            重置
          </button>
        </div>
      </form>

      <div v-if="submitted" class="mt-4 p-3 bg-emerald-50 border border-emerald-100 rounded-md text-emerald-700 text-center text-sm flex items-center justify-center">
        <svg class="w-4 h-4 mr-2" fill="none" stroke="currentColor" viewBox="0 0 24 24"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 13l4 4L19 7"></path></svg>
        问卷提交成功！感谢您的参与！
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { submitSurvey } from '@/api/survey'

const form = ref({
  name: '',
  gender: '',
  ageRange: '',
  satisfaction: null,
  suggestions: '',
  email: ''
})

const purposes = ['旅游观光', '学习研究', '工作出差', '探亲访友', '其他']
const selectedPurposes = ref([])
const submitted = ref(false)

const handleSubmit = async () => {
  try {
    const data = {
      ...form.value,
      visitPurpose: JSON.stringify(selectedPurposes.value)
    }
    await submitSurvey(data)
    submitted.value = true
    setTimeout(() => {
      submitted.value = false
      resetForm()
    }, 3000)
  } catch (error) {
    console.error('Error:', error)
    alert('提交失败，请重试')
  }
}

const resetForm = () => {
  form.value = {
    name: '',
    gender: '',
    ageRange: '',
    satisfaction: null,
    suggestions: '',
    email: ''
  }
  selectedPurposes.value = []
}
</script>
