// src/api/animal.js
import axios from 'axios';

export const animalApi = {
  // 获取动物列表
  getAnimals() {
    return axios.get('/api/animals');
  },
  // 获取单个动物详情
  getAnimalById(id) {
    return axios.get(`/api/animals/${id}`);
  },
  // 提交领养申请
  submitAdoption(formData) {
    return axios.post('/api/adoptions', formData);
  },
};