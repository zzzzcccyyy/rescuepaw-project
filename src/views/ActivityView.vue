<template>
  <div>
    <h1>活动发布</h1>
    <form @submit.prevent="addActivity">
      <input v-model="newActivityTitle" placeholder="标题" required />
      <textarea v-model="newActivityDescription" placeholder="描述" required></textarea>
      <input v-model="newActivityLocation" placeholder="地点" required />
      <input v-model="newActivityStartTime" type="datetime-local" required />
      <input v-model="newActivityEndTime" type="datetime-local" required />
      <button type="submit">发布活动</button>
    </form>
    <div v-if="loading">加载中...</div>
    <div v-else>
      <div v-for="activity in activities" :key="activity.id" class="activity">
        <h2>{{ activity.title }}</h2>
        <p>{{ activity.description }}</p>
        <p>地点: {{ activity.location }}</p>
        <p>时间: {{ activity.startTime }} - {{ activity.endTime }}</p>
      </div>
    </div>
  </div>
</template>

<script>
import { ref, onMounted } from 'vue';
import axios from 'axios';

export default {
  setup() {
    const activities = ref([]);
    const loading = ref(true);
    const newActivityTitle = ref('');
    const newActivityDescription = ref('');
    const newActivityLocation = ref('');
    const newActivityStartTime = ref('');
    const newActivityEndTime = ref('');

    const fetchActivities = async () => {
      try {
        const response = await axios.get('/api/activities');
        activities.value = response.data;
      } catch (error) {
        console.error('加载活动失败:', error);
      } finally {
        loading.value = false;
      }
    };

    const addActivity = async () => {
      try {
        const response = await axios.post('/api/activities', {
          title: newActivityTitle.value,
          description: newActivityDescription.value,
          location: newActivityLocation.value,
          startTime: newActivityStartTime.value,
          endTime: newActivityEndTime.value,
        });
        activities.value.push(response.data);
        newActivityTitle.value = '';
        newActivityDescription.value = '';
        newActivityLocation.value = '';
        newActivityStartTime.value = '';
        newActivityEndTime.value = '';
      } catch (error) {
        alert('发布活动失败: ' + error.message);
      }
    };

    onMounted(fetchActivities);

    return {
      activities,
      loading,
      newActivityTitle,
      newActivityDescription,
      newActivityLocation,
      newActivityStartTime,
      newActivityEndTime,
      addActivity,
    };
  },
};
</script>

<style scoped>
h1 {
  text-align: center;
}

form {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.form-item {
  margin: 10px;
  display: flex;
  flex-direction: column;
}

.form-item label {
  margin-bottom: 5px;
}

input,
textarea {
  padding: 8px;
  width: 300px;
}

.time-range {
  display: flex;
  align-items: center;
}

.time-range input {
  width: 130px;
}

.time-range span {
  margin: 0 10px;
}

button {
  margin-top: 10px;
  padding: 5px 10px;
  color: white;
  border: none;
  cursor: pointer;
}
</style>