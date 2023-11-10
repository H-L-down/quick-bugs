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
    }
});
