import { createRouter, createWebHistory } from 'vue-router';
import HomeView from '@/views/HomeView.vue';
import AnimalDetail from '@/views/AnimalDetail.vue';
import AdoptionApply from '@/views/AdoptionApply.vue';
import AdminAnimalManage from '@/views/AdminAnimalManage.vue';
import VolunteerRegister from '@/views/VolunteerRegister.vue';
import ForumView from '@/views/ForumView.vue';
import ActivityView from '@/views/ActivityView.vue';
import FeedbackView from '@/views/FeedbackView.vue';
import LoginView from '../views/LoginView.vue';
import RegisterView from '../views/RegisterView.vue';

const routes = [
  { path: '/', component: HomeView },
  { path: '/animal/:id', component: AnimalDetail },
  { path: '/adoption/apply', component: AdoptionApply },
  { path: '/admin/animals', component: AdminAnimalManage },
  { path: '/forum', component: ForumView },
  { path: '/activities', component: ActivityView },
  { path: '/feedback', component: FeedbackView },
  { path: '/login', component: LoginView },
  { path: '/register',component: RegisterView },
  { path: '/volunteer/register',component: VolunteerRegister}
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;