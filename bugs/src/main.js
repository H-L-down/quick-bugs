import Vue from 'vue';
import App from './App.vue';
import router from './router';

import 'ant-design-vue/dist/antd.css';
import Antd from 'ant-design-vue';
import Vuex from 'vuex'
const store = new Vuex.Store({
    state: {},
    mutations: {
        increment (state) {
            state.count++
        }
    }
});
const bus = new Vue();
Vue.use(Vuex)
Vue.use(Antd);
Vue.config.productionTip = false;
Vue.prototype.$bus = bus;

new Vue({
    router,
    store,
    render: (h) => h(App),
}).$mount('#app');
