# foot-print

## Project setup
```
npm install
```

### vue 프로젝트 빌드
```
// vueROOT경로에서 실행해야 함 
npm run build 
```

위의 명령어로 빌드하면 spring 프로젝트(springROOT/src/main/resources/static)으로 빌드파일 저장

### 경로 설정한 부분
vueROOT/vue.config.js
```
...
const path = require("path");
module.exports = {
  outputDir: path.resolve(__dirname, "../../src/main/resources/static"),
}

```
