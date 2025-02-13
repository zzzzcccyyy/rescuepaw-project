<template>
  <div>
    <h1>领养申请</h1>
    <form @submit.prevent="submitAdoption">
      <label for="animalId">动物ID:</label>
      <input v-model="animalId" type="number" required />
      <label for="applyReason">申请理由:</label>
      <textarea v-model="applyReason" required></textarea>
      <button type="submit">提交申请</button>
    </form>
  </div>
</template>

<script>
import { ref } from 'vue';
import axios from 'axios';
import { useUserStore } from '@/store/userStore';

export default {
  setup() {
    const animalId = ref('');
    const applyReason = ref('');
    const userStore = useUserStore();

    const submitAdoption = async () => {
      try {
        const response = await axios.post('/api/adoptions', {
          userId: userStore.user.id,
          animalId: animalId.value,
          applyReason: applyReason.value,
        });
        alert('申请提交成功: ' + response.data.id);
      } catch (error) {
        alert('申请提交失败: ' + error.message);
      }
    };

    return { animalId, applyReason, submitAdoption };
  },
};
</script>