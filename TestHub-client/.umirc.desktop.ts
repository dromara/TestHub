import { defineConfig } from 'umi';
import { transitionTimezoneTimestamp } from './src/utils/webpack';

export default defineConfig({
  publicPath: './',
  history: {
    type: 'hash',
  },
  define: {
    __UMI_ENV__: process.env.UMI_ENV,
    __ENV__: process.env.UMI_ENV,
    __BUILD_TIME__: transitionTimezoneTimestamp(new Date().getTime()),
    __APP_NAME__: process.env.APP_NAME,
    __DOC_URL__: "http://www.nsrule.com",
    __APP_VERSION__: process.env.APP_VERSION,
    __APP_PORT__: process.env.APP_PORT,
  },
  headScripts: [
    `window.dataLayer = window.dataLayer || [];
    function gtag() {
      window.dataLayer.push(arguments);
    }
    gtag('js', new Date());
    gtag('config', 'G-V8M4E5SF61', {
      platform: 'DESKTOP',
      version: '${process.env.APP_VERSION}'
    });`,
  ],
});
