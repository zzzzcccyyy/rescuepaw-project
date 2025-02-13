<template>
  <div>
    <h1>动物档案管理</h1>
    <button @click="openAddAnimalModal">添加动物</button>
    <table v-if="!loading">
      <thead>
        <tr>
          <th>ID</th>
          <th>名字</th>
          <th>种类</th>
          <th>操作</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="animal in animals" :key="animal.id">
          <td>{{ animal.id }}</td>
          <td>{{ animal.name }}</td>
          <td>{{ animal.type }}</td>
          <td>
            <button @click="editAnimal(animal)">编辑</button>
            <button @click="deleteAnimal(animal.id)">删除</button>
          </td>
        </tr>
      </tbody>
    </table>
    <div v-else>加载中...</div>

    <!-- 添加/编辑动物模态框 -->
    <div v-if="showModal" class="modal">
      <form @submit.prevent="saveAnimal">
        <label>名字: <input v-model="currentAnimal.name" required /></label>
        <label>种类: <input v-model="currentAnimal.type" required /></label>
        <label>年龄: <input v-model="currentAnimal.age" type="number" required /></label>
        <button type="submit">保存</button>
        <button type="button" @click="closeModal">取消</button>
      </form>
    </div>
  </div>
</template>

<script>
import { ref, onMounted } from 'vue';
import { useAnimalStore } from '@/store/animalStore';
import axios from 'axios';

export default {
  setup() {
    const animalStore = useAnimalStore();
    const showModal = ref(false);
    const currentAnimal = ref({ name: '', type: '', age: 0 });

    onMounted(() => {
      animalStore.fetchAnimals();
    });

    const openAddAnimalModal = () => {
      currentAnimal.value = { name: '', type: '', age: 0 };
      showModal.value = true;
    };

    const editAnimal = (animal) => {
      currentAnimal.value = { ...animal };
      showModal.value = true;
    };

    const saveAnimal = async () => {
      if (currentAnimal.value.id) {
        await axios.put(`/api/animals/${currentAnimal.value.id}`, currentAnimal.value);
      } else {
        await axios.post('/api/animals', currentAnimal.value);
      }
      animalStore.fetchAnimals();
      showModal.value = false;
    };

    const deleteAnimal = async (id) => {
      await axios.delete(`/api/animals/${id}`);
      animalStore.fetchAnimals();
    };

    const closeModal = () => {
      showModal.value = false;
    };

    return {
      animals: animalStore.animals,
      loading: animalStore.loading,
      showModal,
      currentAnimal,
      openAddAnimalModal,
      editAnimal,
      saveAnimal,
      deleteAnimal,
      closeModal,
    };
  },
};
</script>

<style scoped>
.modal {
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  background: white;
  padding: 20px;
  border: 1px solid #ccc;
}
</style>