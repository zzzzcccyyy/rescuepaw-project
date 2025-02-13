import { defineStore } from 'pinia';
import axios from 'axios';

export const useUserStore = defineStore('user', {
  state: () => ({
    user: null,
    token: localStorage.getItem('token') || null,
  }),
  actions: {
    async login(credentials) {
      try {
        const response = await axios.post('/api/auth/login', credentials);
        // 检查响应数据结构
        if (response.data && response.data.user && response.data.token) {
          this.user = response.data.user;
          this.token = response.data.token;
          localStorage.setItem('token', this.token);
        } else {
          console.error('登录响应数据结构不符合预期:', response.data);
        }
      } catch (error) {
        console.error('登录请求出错:', error);
        // 可以在这里添加更多错误处理逻辑，比如显示错误提示给用户
      }
    },
    async register(userData) {
      try {
        const response = await axios.post('/api/auth/register', userData);
        // 检查响应数据结构
        if (response.data && response.data.user && response.data.token) {
          this.user = response.data.user;
          this.token = response.data.token;
          localStorage.setItem('token', this.token);
        } else {
          console.error('注册响应数据结构不符合预期:', response.data);
        }
      } catch (error) {
        console.error('注册请求出错:', error);
        // 可以在这里添加更多错误处理逻辑，比如显示错误提示给用户
      }
    },
    logout() {
      this.user = null;
      this.token = null;
      localStorage.removeItem('token');
    },
  },
});