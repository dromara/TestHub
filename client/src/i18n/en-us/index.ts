import common from './common';
import menu from './menu';
import setting from './setting';
import login from './login';
import httpEnUs from '@/components/TestHub/http/i18n/en-us';
import { LangType } from '@/constants';

export default {
  lang: LangType.EN_US,
  ...common,
  ...setting,
  ...menu,
  ...login,
  ...httpEnUs
};
