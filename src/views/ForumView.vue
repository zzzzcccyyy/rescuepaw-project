<template>
  <div>
    <h1>论坛</h1>
    <form @submit.prevent="addPost">
      <input v-model="newPostTitle" placeholder="标题" required />
      <textarea v-model="newPostContent" placeholder="内容" required></textarea>
      <button type="submit">发布帖子</button>
    </form>
    <div v-if="loading">加载中...</div>
    <div v-else>
      <div v-for="post in posts" :key="post.id" class="forum-post">
        <h2>{{ post.title }}</h2>
        <p>{{ post.content }}</p>
        <p>发布者: {{ post.user.username }}</p>
      </div>
    </div>
  </div>
</template>

<script>
import { ref, onMounted } from 'vue';
import axios from 'axios';
import { useUserStore } from '@/store/userStore';

export default {
  setup() {
    const posts = ref([]);
    const loading = ref(true);
    const newPostTitle = ref('');
    const newPostContent = ref('');
    const userStore = useUserStore();

    const fetchPosts = async () => {
      try {
        const response = await axios.get('/api/forum/posts');
        posts.value = response.data;
      } catch (error) {
        console.error('加载帖子失败:', error);
      } finally {
        loading.value = false;
      }
    };

    const addPost = async () => {
      try {
        const response = await axios.post('/api/forum/posts', {
          userId: userStore.user.id,
          title: newPostTitle.value,
          content: newPostContent.value,
        });
        posts.value.push(response.data);
        newPostTitle.value = '';
        newPostContent.value = '';
      } catch (error) {
        alert('发布帖子失败: ' + error.message);
      }
    };

    onMounted(fetchPosts);

    return { posts, loading, newPostTitle, newPostContent, addPost };
  },
};
</script>

<style scoped>
.forum-post {
  border: 1px solid #ccc;
  padding: 16px;
  margin: 16px;
}
</style>

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