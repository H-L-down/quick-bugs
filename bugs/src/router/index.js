import Vue from 'vue';
import VueRouter from 'vue-router';
import Home from '../pages/HomeTest.vue';
import Test from '../pages/TestPage.vue';

Vue.use(VueRouter);

const routes = [
    {
        path: '/',
        name: 'home',
        component: Home,
    },
    {
        path: '/test',
        name: 'test',
        component: Test,
    }
];

const router = new VueRouter({
    mode: 'history',
    routes,
});

export default router;
