import { defineConfig } from 'umi';
import { transitionTimezoneTimestamp } from './src/utils/webpack';
import MonacoWebpackPlugin from 'monaco-editor-webpack-plugin';

// const MonacoEditorEsmWebpackPlugin = require('monaco-editor-esm-webpack-plugin');


const chainWebpack = (config: any, { webpack }: any) => {
  config.plugin('monaco-editor').use(MonacoWebpackPlugin, [
    {
      languages: ['mysql', 'pgsql', 'sql', 'xml', 'html', 'json', 'freemarker2', 'text'],
      // languages: ['typescript', 'javascript', 'shell', 'yaml', 'java', 'mysql', 'pgsql', 'sql', 'xml', 'html', 'json', 'freemarker2', 'text'],
    },
  ]);
};

export default defineConfig({
  title: process.env.APP_NAME,
  base: '/',
  publicPath: '/',
  hash: true,
  routes: [
    {
      path: '/',
      component: '@/layouts/GlobalLayout',
      routes: [
        {
          path: '/login',
          component: '@/pages/login',
        },
        {
          path: '/demo',
          component: '@/pages/demo',
        },
        {
          path: '/http',
          component: 'main',
        },
        {
          path: '/tools',
          component: 'main',
        },

        {
          path: '/test',
          component: 'main',
        },
        {
          path: '/case',
          component: 'main',
        },
        {
          path: '/meta',
          component: 'main',
        },
        {
          path: '/',
          component: 'main',
        },
      ],
    },
  ],

  npmClient: 'yarn',
  dva: {},
  plugins: ['@umijs/plugins/dist/dva'],
  chainWebpack,
  proxy: {
    '/api': {
      // target: 'http://127.0.0.1:12003',
      target: 'http://173.168.1.154:12003',
      changeOrigin: true,
    },
  },
  targets: {
    chrome: 80,
  },
  links: [{ rel: 'icon', type: 'image/ico', sizes: '32x32', href: '/static/front/logo.ico' }],
  headScripts: [
    `if (localStorage.getItem('app-local-storage-versions') !== 'v4') {
      localStorage.clear();
      localStorage.setItem('app-local-storage-versions', 'v4');
    }`,
    `if (window.electronApi) { window.electronApi.startServerForSpawn() }`,

    {
      src: 'https://www.googletagmanager.com/gtag/js?id=G-V8M4E5SF61',
      async: true,
    },
  ],
  favicons: ['logo.ico'],
  define: {
    __UMI_ENV__: process.env.UMI_ENV,
    __ENV__: process.env.UMI_ENV,
    __BUILD_TIME__: transitionTimezoneTimestamp(new Date().getTime()),
    __APP_NAME__: process.env.APP_NAME,
    __DOC_URL__: "http://www.nsrule.com",
    __APP_VERSION__: process.env.APP_VERSION,
    __APP_PORT__: process.env.APP_PORT,
  },
  esbuildMinifyIIFE: true,
});
