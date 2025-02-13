<template>
  <div>
    <h1>志愿者注册</h1>
    <form @submit.prevent="registerVolunteer">
      <label for="username">用户名:</label>
      <input v-model="username" required />
      <label for="email">邮箱:</label>
      <input v-model="email" type="email" required />
      <label for="password">密码:</label>
      <input v-model="password" type="password" required />
      <label for="phone">手机号:</label>
      <input v-model="phone" />
      <button type="submit">注册</button>
    </form>
  </div>
</template>

<script>
import { ref } from 'vue';
import axios from 'axios';

export default {
  setup() {
    const username = ref('');
    const email = ref('');
    const password = ref('');
    const phone = ref('');

    const registerVolunteer = async () => {
      try {
        const response = await axios.post('/api/volunteer/register', {
          username: username.value,
          email: email.value,
          password: password.value,
          phone: phone.value,
          role: 'VOLUNTEER',
        });
        alert('注册成功: ' + response.data.username);
      } catch (error) {
        alert('注册失败: ' + error.message);
      }
    };

    return { username, email, password, phone, registerVolunteer };
  },
};
</script>

<style scoped>
h1 {
  text-align: center;
}

.form-item {
  display: flex;
  align-items: center;
  margin-bottom: 10px;
}

label {
  width: 80px; /* 设置标签的固定宽度 */
  text-align: right; /* 标签文本右对齐 */
  margin-right: 10px; /* 标签右侧的间距 */
}

input {
  flex: 1; /* 输入框占据剩余的宽度 */
  margin: 5px;
  padding: 5px;
  width: 200px;
}

button {
  padding: 10px 20px;
  border: none;
  cursor: pointer;
}
</style>