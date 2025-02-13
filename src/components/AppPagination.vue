<template>
  <div class="">
    <button 
      v-for="page in totalPages" 
      :key="page" 
      @click="$emit('page-change', page)"
      :class="{ active: currentPage === page }"
    >
      {{ page }}
    </button>
  </div>
</template>

<script>
export default {
  props: {
    totalPages: {
      type: Number,
      required: true,
    },
    currentPage: {
      type: Number,
      required: true,
    },
  },
  emits: ['page-change'],
};
</script>

<style scoped>
.pagination {
  margin: 20px 0;
}
.pagination button {
  margin: 0 5px;
  padding: 5px 10px;
}
.pagination button.active {
  background-color: #007bff;
  color: white;
}
</style>