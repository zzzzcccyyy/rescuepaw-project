<template>
  <div>
    <h1>个人中心</h1>
    <div v-if="user">
      <p>用户名: {{ user.username }}</p>
      <p>邮箱: {{ user.email }}</p>
      <p>角色: {{ user.role }}</p>
      <button @click="logout">退出登录</button>
    </div>
    <div v-else>
      <p>请先登录</p>
    </div>
  </div>
</template>

<script>
import { ref, onMounted } from 'vue';
import { useUserStore } from '@/store/userStore';
import { useRouter } from 'vue-router';

export default {
  setup() {
    const userStore = useUserStore();
    const router = useRouter();
    const user = ref(null);

    onMounted(() => {
      user.value = userStore.user;
    });

    const logout = () => {
      userStore.logout();
      router.push('/login');
    };

    return { user, logout };
  },
};
</script>