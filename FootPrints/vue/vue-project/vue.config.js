const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true
})

const path = require("path");
module.exports = {
  outputDir: path.resolve(__dirname, "../../src/main/resources/static"),
  devServer: {
    port: 8081,
    proxy: {
      '/':'http://localhost:8080',
      '/req': {
        target: 'https://api.vworld.kr',
        changeOrigin: true
      }
    },
    disableHostCheck: true
  }
};

