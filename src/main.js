// main.js
import { createApp } from 'vue';
import { createPinia } from 'pinia'; // 状态管理
import App from './App.vue';
import router from './router'; // 路由
import axios from 'axios'; // HTTP 请求库

// 配置 axios 全局默认值
axios.defaults.baseURL = 'http://localhost:8080'; // 后端 API 地址

// 请求拦截器
axios.interceptors.request.use(
    config => {
        const token = localStorage.getItem('token');
        if (token) {
            config.headers.Authorization = `Bearer ${token}`;
        }
        return config;
    },
    error => {
        console.error('请求拦截器出错:', error);
        return Promise.reject(error);
    }
);

// 响应拦截器
axios.interceptors.response.use(
    response => {
        return response;
    },
    error => {
        if (error.response && error.response.status === 401) {
            // 处理 Token 过期或无效的情况，例如跳转到登录页
            console.log('Token 过期或无效，跳转到登录页');
            router.push('/login');
        }
        return Promise.reject(error);
    }
);

// 获取 CSRF 令牌
const csrfMeta = document.querySelector('meta[name="csrf-token"]');
let csrfToken;
if (csrfMeta) {
    csrfToken = csrfMeta.getAttribute('content');
    axios.defaults.headers.post['X-CSRF-TOKEN'] = csrfToken;
} else {
    console.warn('未找到 CSRF token 的 meta 标签');
}

// 创建 Vue 应用
const app = createApp(App);

// 使用插件
app.use(createPinia()); // 使用 Pinia 状态管理
app.use(router); // 使用路由

// 挂载应用
app.mount('#app');