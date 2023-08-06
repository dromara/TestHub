import React, { Fragment } from 'react';
import zhCN from './zh-cn';
import enUS from './en-us';

const locale = {
  'en-us': enUS,
  'zh-cn': zhCN,
};

const _isEN = (localStorage.getItem('lang') || '').toLowerCase().indexOf('en') > -1;
export function isEN() {
  return _isEN;
}
const langSet: any = locale[_isEN ? 'en-us' : 'zh-cn'];

export const currentLang = localStorage.getItem('lang') || 'en';

export type I18nKey = keyof typeof zhCN;
// function i18n(key: keyof typeof zhCN, ...args: any[]): string;
function i18n(key: string, ...args: any[]) {
  let result: string = langSet[key];
  if (result === undefined) {
    return `[${key}]`;
  } else {
    args.forEach((arg, i) => {
      result = result.replace(new RegExp(`\\{${i + 1}\\}`, 'g'), arg);
    });
    if (args.length) {
      result = result.replace(/\{(.+?)\|(.+?)\}/g, (_, singular, plural) => {
        const n = args[0];
        return n == 1 ? singular : plural;
      });
    }
    return result;
  }
}

export default i18n;
export { i18n };
