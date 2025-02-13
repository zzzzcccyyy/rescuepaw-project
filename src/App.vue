<template>
    <div id="app">
      <!-- 导航栏 -->
      <nav>
        <router-link to="/">首页</router-link>
        <router-link to="/adoption/apply">领养申请</router-link>
        <router-link to="/volunteer/register">志愿者注册</router-link>
        <router-link to="/forum">论坛</router-link>
        <router-link to="/activities">活动</router-link>
        <router-link to="/feedback">反馈</router-link>
        <router-link v-if="isAdmin" to="/admin/animals">动物管理</router-link>
        <router-link v-if="!isLoggedIn" to="/login">登录</router-link>
        <router-link v-if="!isLoggedIn" to="/register">注册</router-link>
        <button v-if="isLoggedIn" @click="logout">退出</button>
      </nav>
  
      <!-- 路由视图 -->
      <router-view></router-view>
    </div>
  </template>
  
  <script>
  import { computed } from 'vue';
  import { useUserStore } from '@/store/userStore';
  import { useRouter } from 'vue-router';
  
  export default {
    setup() {
      const userStore = useUserStore();
      const router = useRouter();
  
      // 计算属性：是否已登录
      const isLoggedIn = computed(() => userStore.isLoggedIn);
  
      // 计算属性：是否是管理员
      const isAdmin = computed(() => userStore.user?.role === 'ADMIN');
  
      // 退出登录
      const logout = () => {
        userStore.logout();
        router.push('/login');
      };
  
      return { isLoggedIn, isAdmin, logout };
    },
  };
  </script>
  
  <style>
  #app {
    font-family: Avenir, Helvetica, Arial, sans-serif;
    text-align: center;
    color: #2c3e50;
  }
  
  nav {
    padding: 20px;
    background-color: #f8f9fa;
    margin-bottom: 20px;
  }
  
  nav a {
    margin: 0 10px;
    text-decoration: none;
    color: #2c3e50;
  }
  
  nav a.router-link-exact-active {
    color: #42b983;
  }
  
  button {
    margin-left: 10px;
    padding: 5px 10px;
    background-color: #dc3545;
    color: white;
    border: none;
    cursor: pointer;
  }
  
  button:hover {
    background-color: #c82333;
  }
  </style>