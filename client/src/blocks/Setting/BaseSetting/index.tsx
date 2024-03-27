import React, { useState } from 'react';
import { LangType, ThemeType } from '@/constants';
import i18n, { currentLang } from '@/i18n';
import classnames from 'classnames';
import themeDarkImg from '@/assets/img/theme-dark.png';
import themeLightImg from '@/assets/img/theme-light.png';
import themeAutoImg from '@/assets/img/theme-auto.png';
import { Select } from 'antd';
import Iconfont from '@/components/base/Iconfont';
import { setLang as setLangLocalStorage } from '@/utils/localStorage';
import { useTheme } from '@/hooks/useTheme';

import styles from './index.less';

const themeList = [
  {
    code: ThemeType.Light,
    name: i18n('setting.text.light'),
    img: themeLightImg,
  },
  {
    code: ThemeType.Dark,
    name: i18n('setting.text.dark'),
    img: themeDarkImg,
  },
  {
    code: ThemeType.FollowOs,
    name: i18n('setting.text.followOS'),
    img: themeAutoImg,
  },
];

const languageOptions = [
  { value: LangType.ZH_CN, label: '简体中文' },
  { value: LangType.EN_US, label: 'English' },
];

const colorList = [
  {
    code: 'golden-purple',
    name: '淡雅紫',
    color: '#9373ee',
  },
  {
    code: 'polar-blue',
    name: '清爽蓝',
    color: '#1a90ff',
  },
  // {
  //   code: 'blue2',
  //   name: i18n('setting.label.violet'),
  //   color: '#00c3ee',
  // },
  {
    code: 'polar-green',
    name: i18n('setting.label.green'),
    // color: '#039e74',
    color: '#2ecc71',
  },
  {
    code: 'gold',
    name: i18n('setting.label.violet'),
    color: '#9a7d56',
  },
  {
    code: 'silver',
    name: i18n('setting.label.violet'),
    color: '#8e8374',
  },
  {
    code: 'red',
    name: i18n('setting.label.violet'),
    color: '#e74c3c',
  },
  {
    code: 'orange',
    name: i18n('setting.label.violet'),
    color: '#f39c12',
  },
];

// baseBody 基础设置
export default function BaseSetting() {
  const [appTheme, setAppTheme] = useTheme();
  const [currentTheme, setCurrentTheme] = useState<ThemeType>(appTheme.backgroundColor);
  const [currentPrimaryColor, setCurrentPrimaryColor] = useState(localStorage.getItem('primary-color'));

  const changePrimaryColor = (item: any) => {
    const html = document.documentElement;
    html.setAttribute('primary-color', item.code);
    localStorage.setItem('primary-color', item.code);
    setCurrentPrimaryColor(item.code);
    setAppTheme({
      ...appTheme,
      primaryColor: item.code,
    });
  };

  function changeLang(e: any) {
    setLangLocalStorage(e);
    location.reload();
  }

  function handleChangeTheme(backgroundColor: any) {
    setAppTheme({
      ...appTheme,
      backgroundColor,
    });
    setCurrentTheme(backgroundColor);
  }

  return (
    <>
      <div className={styles.title}>{i18n('setting.title.backgroundColor')}</div>
      <ul className={styles.backgroundList}>
        {themeList.map((t) => {
          return (
            <div key={t.code} className={styles.themeItemBox}>
              <div
                className={classnames({ [styles.current]: currentTheme == t.code }, styles.themeImg)}
                onClick={handleChangeTheme.bind(null, t.code)}
                style={{ backgroundImage: `url(${t.img})` }}
              />
              <div className={styles.themeName}>{t.name}</div>
            </div>
          );
        })}
      </ul>
      <div className={styles.title}>{i18n('setting.title.language')}</div>
      <div className={styles.langBox}>
        <Select style={{ width: 120 }} onChange={changeLang} value={currentLang} options={languageOptions} />
      </div>
      <div className={styles.title}>{i18n('setting.title.themeColor')}</div>
      <ul className={styles.primaryColorList}>
        {colorList.map((item) => {
          return (
            <div key={item.code} className={styles.themeColorItem}>
              <div
                className={styles.colorLump}
                key={item.code}
                onClick={changePrimaryColor.bind(null, item)}
                style={{ backgroundColor: item.color }}
              >
                {currentPrimaryColor == item.code && <Iconfont code="&#xe715;" />}
              </div>
              {/* <div className={styles.colorName}>{item.name}</div> */}
            </div>
          );
        })}
      </ul>
    </>
  );
}
