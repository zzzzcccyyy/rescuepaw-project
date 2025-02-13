<template>
  <div>
    <h1>注册</h1>
    <form @submit.prevent="register">
      <input v-model="username" placeholder="用户名" required />
      <input v-model="email" type="email" placeholder="邮箱" required />
      <input v-model="password" type="password" placeholder="密码" required />
      <input v-model="phone" placeholder="手机号" />
      <button type="submit">注册</button>
    </form>
  </div>
</template>

<script>
import { ref } from 'vue';
import { useUserStore } from '@/store/userStore';
import { useRouter } from 'vue-router';

export default {
  setup() {
    const username = ref('');
    const email = ref('');
    const password = ref('');
    const phone = ref('');
    const userStore = useUserStore();
    const router = useRouter();

    const register = async () => {
      try {
        await userStore.register({
          username: username.value,
          email: email.value,
          password: password.value,
          phone: phone.value,
        });
        router.push('/');
      } catch (error) {
        alert('注册失败: ' + error.message);
      }
    };

    return { username, email, password, phone, register };
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