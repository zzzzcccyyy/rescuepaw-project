<template>
  <div>
    <h1>用户反馈</h1>
    <form @submit.prevent="submitFeedback">
      <textarea v-model="feedbackContent" placeholder="请输入您的反馈" required></textarea>
      <button type="submit">提交反馈</button>
    </form>
  </div>
</template>

<script>
import { ref } from 'vue';
import axios from 'axios';
import { useUserStore } from '@/store/userStore';

export default {
  setup() {
    const feedbackContent = ref('');
    const userStore = useUserStore();

    const submitFeedback = async () => {
      try {
        const response = await axios.post('/api/feedback', {
          userId: userStore.user.id,
          content: feedbackContent.value,
        });
        alert('反馈提交成功: ' + response.data.id);
        feedbackContent.value = '';
      } catch (error) {
        alert('反馈提交失败: ' + error.message);
      }
    };

    return { feedbackContent, submitFeedback };
  },
};
</script>

<style scoped>
/* 样式设置 */
h1 {
  text-align: center;
}

form {
  display: flex;
  flex-direction: column;
  align-items: center;
}

input {
  margin: 5px;
  padding: 5px;
  width: 200px;
}

button {
  margin-top: 10px;
  padding: 5px 10px;
}
</style>