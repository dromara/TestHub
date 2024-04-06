import { clearOlderLocalStorage } from '@/utils';
import registerMessage from './registerMessage';
import registerNotification from './registerNotification';
import { getLang, setLang } from '@/utils/localStorage';
import { LangType } from '@/constants';
import initCaseXml from '@/components/TestHub/MonacoEditor/MyMonacoEditor/language/casexml';
// import zh_CN from 'monaco-editor-nls/locale/zh-hans';
// import { setLocaleData } from 'monaco-editor-nls';

const init = () => {
  clearOlderLocalStorage();

  // 设置语言数据
  // setLocaleData(zh_CN);

  initLang();

  registerMessage();
  registerNotification();

  initCaseXml();
};

// 初始化语言
const initLang = () => {
  const lang = getLang();
  if (!lang) {
    setLang(LangType.EN_US);
    document.documentElement.setAttribute('lang', LangType.EN_US);
    const date = new Date('2030-12-30 12:30:00').toUTCString();
    document.cookie = `CHAT2DB.LOCALE=${lang};Expires=${date}`;
  }
};

export default init;
