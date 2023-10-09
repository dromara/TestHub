import layouts from './layouts';
import setting from './setting';
import caseI18n from './caseI18n';
import pluginZhCn from '@/components/testHub/plugins/i18n/plugin-zh-cn';
import testhub from './testhub';
import app from './app';
import login from './login';


export default {
  lang: 'zh-cn',
  ...login,
  ...app,
  ...setting,
  ...layouts,
  ...caseI18n,
  ...pluginZhCn,
  ...testhub
};

