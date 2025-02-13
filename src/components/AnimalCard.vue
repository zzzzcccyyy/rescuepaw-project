<template>
  <div class="animal-card">
    <img :src="animal.imageUrl" :alt="animal.name" />
    <h2>{{ animal.name }}</h2>
    <p>种类: {{ animal.type }}</p>
    <p>年龄: {{ animal.age }}</p>
    <p>性别: {{ animal.gender }}</p>
    <p>健康状况: {{ animal.healthStatus }}</p>
    <router-link :to="`/animal/${animal.id}`">查看详情</router-link>
  </div>
</template>

<script>
export default {
  props: {
    animal: {
      type: Object,
      required: true,
    },
  },
};
</script>

<style scoped>
.animal-card {
  border: 1px solid #ccc;
  padding: 16px;
  margin: 16px;
  text-align: center;
}
</style>