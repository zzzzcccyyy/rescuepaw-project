<template>
  <div>
    <h1>健康与护理信息</h1>
    <div v-if="loading">加载中...</div>
    <div v-else>
      <div v-for="info in healthInfoList" :key="info.id" class="health-info">
        <h2>{{ info.title }}</h2>
        <p>{{ info.content }}</p>
      </div>
    </div>
  </div>
</template>

<script>
import { ref, onMounted } from 'vue';
import axios from 'axios';

export default {
  setup() {
    const healthInfoList = ref([]);
    const loading = ref(true);

    onMounted(async () => {
      try {
        const response = await axios.get('/api/health-info');
        healthInfoList.value = response.data;
      } catch (error) {
        console.error('加载健康信息失败:', error);
      } finally {
        loading.value = false;
      }
    });

    return { healthInfoList, loading };
  },
};
</script>

<style scoped>
.health-info {
  border: 1px solid #ccc;
  padding: 16px;
  margin: 16px;
}
</style>