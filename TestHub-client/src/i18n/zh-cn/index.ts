import menu from './menu';
import common from './common';
import login from './login';
import caseCN from './caseCN';
import setting from './setting';
import testhub from './testhub';
import { LangType } from '@/constants'
import httpEnUs from '@/components/TestHub/http/i18n/zh-cn';
import pluginZhCn from '@/components/TestHub/plugins/i18n/plugin-zh-cn';

export default {
  lang: LangType.ZH_CN,
  ...common,
  ...menu,
  ...login,
  ...caseCN,
  ...setting,
  ...testhub,
  ...httpEnUs,
  ...pluginZhCn
};
