import { defineConfig } from 'umi';

export default defineConfig({
  publicPath: './',
  dynamicImport: false,
  define: {
    __APP_NAME__: "TestHub",
    __UMI_ENV__: process.env.UMI_ENV,
    __BUILD_TIME__: process.env.BUILD_TIME,
    __APP_VERSION__: process.env.APP_VERSION,
    __APP_PORT__: process.env.APP_PORT
  },
});
