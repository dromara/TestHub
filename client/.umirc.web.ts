import { defineConfig } from 'umi';
import { transitionTimezoneTimestamp } from './src/utils/webpack';
const MonacoWebpackPlugin = require('monaco-editor-webpack-plugin');

const chainWebpack = (config: any, { webpack }: any) => {
  config.plugin('monaco-editor').use(MonacoWebpackPlugin, [
    {
      languages: ['mysql', 'pgsql', 'sql', 'xml', 'html', 'json', 'freemarker2', 'text'],
    },
  ]);
};

export default defineConfig({
  publicPath: '/',
  chainWebpack,
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
      platform: 'WEB',
      version: '${process.env.APP_VERSION}'
    });`,
  ],
});
