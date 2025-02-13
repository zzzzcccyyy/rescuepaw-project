<template>
  <div>
    <h1>登录</h1>
    <form @submit.prevent="login">
      <input v-model="username" placeholder="用户名" required />
      <input v-model="password" type="password" placeholder="密码" required />
      <button type="submit">登录</button>
    </form>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import axios from '@/utils/request';

// 定义用户名和密码的响应式变量
const username = ref('');
const password = ref('');

const login = async () => {
  try {
    // 使用 axios 方式
    const response = await axios.post('/auth/login', {
       username: username.value,
       password: password.value
     });
     localStorage.setItem('token', response.data.token);
  } catch (error) {
    console.error('登录失败:', error);
    alert('登录失败: ' + error.message);
  }
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