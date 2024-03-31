import common from './common';
import menu from './menu';
import login from './login';
import caseUS from './caseUS';
import setting from './setting';
import { LangType } from '@/constants';
import httpEnUs from '@/components/TestHub/http/i18n/en-us';
import pluginEnUs from '@/components/TestHub/plugins/i18n/plugin-en-us';

export default {
  lang: LangType.EN_US,
  ...common,
  ...setting,
  ...menu,
  ...caseUS,
  ...login,
  ...httpEnUs,
  ...pluginEnUs
};
