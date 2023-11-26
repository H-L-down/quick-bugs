import axios from "axios";

const BugsAxios = axios.create({
    baseURL: process.env.VUE_APP_BASE_URL,
    headers: { 'content-type': 'application/x-www-form-urlencoded' },
    timeout: 20000,
});

// 请求拦截器
BugsAxios.interceptors.request.use(
    config => {
        config.headers.token = window.sessionStorage.getItem("token");
        return config;
    },
    error => {
        console.log(error);
        return error;
    }
);

// 响应拦截器
BugsAxios.interceptors.response.use(
    response => {
        if (response.data.code === 2000) {
            // 发生了错误
            return Promise.reject(response.data);
        }
        return response.data;
    },
    error => {
        return Promise.reject(error);
    }
);

export default BugsAxios;