// src/store/animalStore.js
import { defineStore } from 'pinia';
import axios from 'axios';

export const useAnimalStore = defineStore('animal', {
  state: () => ({
    animals: [],
    currentAnimal: null,
    loading: false,
    error: null,
  }),
  actions: {
    async fetchAnimals() {
      this.loading = true;
      try {
        const response = await axios.get('/api/animals');
        this.animals = response.data;
      } catch (error) {
        this.error = error.message;
      } finally {
        this.loading = false;
      }
    },
    async searchAnimals(params) {
      this.loading = true;
      try {
        const response = await axios.get('/api/search/animals', { params });
        this.animals = response.data;
      } catch (error) {
        this.error = error.message;
      } finally {
        this.loading = false;
      }
    },
  },
});