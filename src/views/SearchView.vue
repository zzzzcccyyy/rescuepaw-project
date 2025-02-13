<template>
  <div>
    <h1>搜索和筛选</h1>
    <form @submit.prevent="searchAnimals">
      <input v-model="searchType" placeholder="种类" />
      <input v-model="searchGender" placeholder="性别" />
      <input v-model="minAge" type="number" placeholder="最小年龄" />
      <input v-model="maxAge" type="number" placeholder="最大年龄" />
      <button type="submit">搜索</button>
    </form>
    <div v-if="loading">加载中...</div>
    <div v-else>
      <AnimalCard v-for="animal in searchResults" :key="animal.id" :animal="animal" />
    </div>
  </div>
</template>

<script>
import { ref } from 'vue';
import axios from 'axios';
import AnimalCard from '@/components/AnimalCard.vue';

export default {
  components: { AnimalCard },
  setup() {
    const searchType = ref('');
    const searchGender = ref('');
    const minAge = ref('');
    const maxAge = ref('');
    const searchResults = ref([]);
    const loading = ref(false);

    const searchAnimals = async () => {
      try {
        loading.value = true;
        const response = await axios.get('/api/search/animals', {
          params: {
            type: searchType.value,
            gender: searchGender.value,
            minAge: minAge.value,
            maxAge: maxAge.value,
          },
        });
        searchResults.value = response.data;
      } catch (error) {
        console.error('搜索失败:', error);
      } finally {
        loading.value = false;
      }
    };

    return { searchType, searchGender, minAge, maxAge, searchResults, loading, searchAnimals };
  },
};
</script>