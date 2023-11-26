const { defineConfig } = require('@vue/cli-service');
const path = require('path');
module.exports = defineConfig({
    transpileDependencies: true,
    css: {
        loaderOptions: {
            less: {
                lessOptions: {
                    javascriptEnabled: true
                }
            }
        }
    },
    devServer: {
        proxy: {
            '/api': {
                target: process.env.VUE_APP_PROXY_URL,
                changeOrigin: true,
                pathRewrite: {
                    '^/api': ''
                }
            }
        }
    }
});