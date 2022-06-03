const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true
})

const path = require("path");
module.exports = {
  outputDir: path.resolve(__dirname, "../../src/main/resources/static"),
  devServer: {
    proxy: {
      '/req': {
        target: 'https://api.vworld.kr',
        changeOrigin: true
      }
    },
  }
};

