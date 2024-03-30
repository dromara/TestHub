import { LangType } from '@/constants'
import menu from './menu';
import common from './common';
import setting from './setting';
import login from './login';

import httpEnUs from '@/components/TestHub/http/i18n/zh-cn';

export default {
  lang: LangType.ZH_CN,
  ...common,
  ...setting,
  ...menu,
  ...login,
  ...httpEnUs
};
