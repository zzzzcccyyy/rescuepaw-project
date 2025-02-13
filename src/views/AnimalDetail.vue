<template>
    <div>
      <h1>动物详情</h1>
      <div v-if="loading">
        <LoadingSpinner />
      </div>
      <div v-else-if="error">
        <ErrorToast :message="error" />
      </div>
      <div v-else>
        <div class="animal-detail">
          <img :src="animal.imageUrl" :alt="animal.name" />
          <h2>{{ animal.name }}</h2>
          <p>种类: {{ animal.type }}</p>
          <p>年龄: {{ animal.age }}</p>
          <p>性别: {{ animal.gender }}</p>
          <p>健康状况: {{ animal.healthStatus }}</p>
          <p>描述: {{ animal.description }}</p>
        </div>
        <button @click="goBack">返回</button>
      </div>
    </div>
  </template>
  
  <script>
  import { ref, onMounted } from 'vue';
  import { useRoute, useRouter } from 'vue-router';
  import axios from 'axios';
  import LoadingSpinner from '@/components/LoadingSpinner.vue';
  import ErrorToast from '@/components/ErrorToast.vue';
  
  export default {
    components: { LoadingSpinner, ErrorToast },
    setup() {
      const route = useRoute();
      const router = useRouter();
      const animal = ref(null);
      const loading = ref(true);
      const error = ref(null);
  
      onMounted(async () => {
        try {
          const response = await axios.get(`/api/animals/${route.params.id}`);
          animal.value = response.data;
        } catch (err) {
          error.value = '加载动物详情失败: ' + err.message;
        } finally {
          loading.value = false;
        }
      });
  
      const goBack = () => {
        router.go(-1); // 返回上一页
      };
  
      return { animal, loading, error, goBack };
    },
  };
  </script>
  
  <style scoped>
  .animal-detail {
    border: 1px solid #ccc;
    padding: 20px;
    margin: 20px;
    text-align: center;
  }
  
  .animal-detail img {
    max-width: 100%;
    height: auto;
    border-radius: 10px;
  }
  
  button {
    margin-top: 20px;
    padding: 10px 20px;
    background-color: #42b983;
    color: white;
    border: none;
    cursor: pointer;
  }
  
  button:hover {
    background-color: #369f6e;
  }
  </style>