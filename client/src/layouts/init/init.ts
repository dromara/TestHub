import { clearOlderLocalStorage } from '@/utils';
import registerMessage from './registerMessage';
import registerNotification from './registerNotification';
import { getLang, setLang } from '@/utils/localStorage';
import { LangType } from '@/constants';
import initCaseXml from '@/components/TestHub/MonacoEditor/MyMonacoEditor/language/casexml';

const init = () => {
  clearOlderLocalStorage();

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
