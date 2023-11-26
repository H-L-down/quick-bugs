import Vue from 'vue';
import VueRouter from 'vue-router';
import Home from '../pages/Home.vue';
import Test from '../pages/TestPage.vue';
import Login from '@/pages/login/Login.vue';

Vue.use(VueRouter);

const routes = [
    {
        path: '/',
        name: 'home',
        component: Home,
        meta: {
            requireAuth: true
        }
    },
    {
        path: '/login',
        name: 'login',
        component: Login,
        meta: {
            requireAuth: false
        }
    },
    {
        path: '/test',
        name: 'test',
        component: Test,
        meta: {
            requireAuth: false
        }
    }
];

const router = new VueRouter({
    mode: 'history',
    routes,
});

router.beforeEach((to, from, next) => {
    console.log(to);
    console.log(from);
    if (to.meta.requireAuth) {
        if (window.sessionStorage.getItem('token')) {
            next();
        } else {
            Vue.prototype.$message.warn('请先登录');
            next('/login');
        }
    }
    next();
})

export default router;
