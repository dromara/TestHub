import { formatDate } from '@/utils';
import { defineConfig } from 'umi';
const MonacoWebpackPlugin = require('monaco-editor-webpack-plugin');

const chainWebpack = (config: any, { webpack }: any) => {
  config.plugin('monaco-editor').use(MonacoWebpackPlugin, [
    {
      languages: ['typescript', 'javascript', 'shell', 'yaml', 'java', 'mysql', 'pgsql', 'sql', 'xml', 'html', 'json', 'text'],
    },
  ]);
};

export default defineConfig({
  title: 'TestHub',
  history: {
    type: 'hash',
  },
  base: '/',
  publicPath: '/',
  hash: false,
  routes: [
    {
      path: '/',
      component: '@/components/AppContainer',
      routes: [
        { path: '/login', exact: true, component: '@/pages/login' },
        { path: '/test', exact: true, component: '@/pages/test' },
        { path: '/error', component: '@/pages/error' },
        {
          path: '/',
          component: '@/layouts',
          routes: [
            {
              exact: true,
              path: '/',
              component: '@/pages/case',
            },
            {
              exact: true,
              path: '/case',
              component: '@/pages/case',
            },
            {
              exact: true,
              path: '/tools',
              component: '@/pages/tools'
            },
            {
              exact: true,
              path: '/http',
              component: '@/pages/http'
            },
            {
              redirect: '/error',
            }
          ]
        }
      ],
    },

  ],
  mfsu: {},
  fastRefresh: {},
  dynamicImport: {
    loading: '@/components/Loading/LazyLoading'
  },
  nodeModulesTransform: {
    type: 'none',
  },
  chainWebpack,
  devServer: {
    port: 8001,
    host: '0.0.0.0',
    hot: true
  },
  define: {
    __APP_NAME__: "TestHub",
    __UMI_ENV__: "local",
    __BUILD_TIME__: "2023-08-01",
    __APP_VERSION__: '0.0.1',
    __APP_PORT__: 12003,
  }
});
