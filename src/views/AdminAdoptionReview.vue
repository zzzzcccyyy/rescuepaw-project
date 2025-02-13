<template>
  <div>
    <h1>领养申请审核</h1>
    <div v-if="loading">加载中...</div>
    <div v-else>
      <table>
        <thead>
          <tr>
            <th>申请ID</th>
            <th>申请人</th>
            <th>动物名称</th>
            <th>状态</th>
            <th>操作</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="form in adoptionForms" :key="form.id">
            <td>{{ form.id }}</td>
            <td>{{ form.user.username }}</td>
            <td>{{ form.animal.name }}</td>
            <td>{{ form.status }}</td>
            <td>
              <button @click="approveForm(form.id)">通过</button>
              <button @click="rejectForm(form.id)">拒绝</button>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
import { ref, onMounted } from 'vue';
import axios from 'axios';

export default {
  setup() {
    const adoptionForms = ref([]);
    const loading = ref(true);

    const fetchAdoptionForms = async () => {
      try {
        const response = await axios.get('/api/adoptions');
        adoptionForms.value = response.data;
      } catch (error) {
        console.error('加载申请失败:', error);
      } finally {
        loading.value = false;
      }
    };

    const approveForm = async (id) => {
      try {
        await axios.put(`/api/adoptions/${id}/status?status=APPROVED`);
        await fetchAdoptionForms();
      } catch (error) {
        alert('操作失败: ' + error.message);
      }
    };

    const rejectForm = async (id) => {
      try {
        await axios.put(`/api/adoptions/${id}/status?status=REJECTED`);
        await fetchAdoptionForms();
      } catch (error) {
        alert('操作失败: ' + error.message);
      }
    };

    onMounted(fetchAdoptionForms);

    return { adoptionForms, loading, approveForm, rejectForm };
  },
};
</script>