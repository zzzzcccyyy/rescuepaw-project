<template>
  <div>
    <h1>流浪动物救助收养平台</h1>
    <div v-if="loading">加载中...</div>
    <div v-else>
      <AnimalCard v-for="animal in animals" :key="animal.id" :animal="animal" />
    </div>
  </div>
</template>

<script>
import { ref, onMounted } from 'vue';
import axios from 'axios';
import AnimalCard from '@/components/AnimalCard.vue';

export default {
  components: { AnimalCard },
  setup() {
    const animals = ref([]);
    const loading = ref(true);

    onMounted(async () => {
      try {
        const response = await axios.get('/api/animals');
        animals.value = response.data;
      } catch (error) {
        console.error('加载动物信息失败:', error);
      } finally {
        loading.value = false;
      }
    });

    return { animals, loading };
  },
};
</script>