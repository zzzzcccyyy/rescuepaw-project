<template>
  <div>
    <h1>领养成功故事</h1>
    <div v-if="loading">加载中...</div>
    <div v-else>
      <div v-for="story in successStories" :key="story.id" class="success-story">
        <h2>{{ story.title }}</h2>
        <p>{{ story.content }}</p>
        <p>发布者: {{ story.user.username }}</p>
      </div>
    </div>
  </div>
</template>

<script>
import { ref, onMounted } from 'vue';
import axios from 'axios';

export default {
  setup() {
    const successStories = ref([]);
    const loading = ref(true);

    onMounted(async () => {
      try {
        const response = await axios.get('/api/success-stories');
        successStories.value = response.data;
      } catch (error) {
        console.error('加载成功故事失败:', error);
      } finally {
        loading.value = false;
      }
    });

    return { successStories, loading };
  },
};
</script>

<style scoped>
.success-story {
  border: 1px solid #ccc;
  padding: 16px;
  margin: 16px;
}
</style>