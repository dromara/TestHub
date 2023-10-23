import React, { memo, useEffect, useLayoutEffect, useState } from 'react';
import styles from './index.less';
import classnames from 'classnames';
import Iconfont from '@/components/Iconfont';
import Button from '@/components/Button';
import { Modal, Radio, message, notification, Tooltip } from 'antd';
import i18n from '@/i18n';
import BrandLogo from '@/components/BrandLogo';
import themeDarkImg from '@/assets/theme-dark.png';
import themeLightImg from '@/assets/theme-light.png';
import themeAutoImg from '@/assets/theme-auto.png';
import { ProFormDigit, ProFormText } from '@ant-design/pro-components';

const backgroundList = [
    {
        code: 'dark',
        name: i18n('setting.text.dark'),
        img: themeDarkImg
    },
    {
        code: 'default',
        name: i18n('setting.text.light'),
        img: themeLightImg
    },
    {
        code: 'followOs',
        name: i18n('setting.text.followOS'),
        img: themeAutoImg
    },
];
let colorSchemeListeners: ((theme: string) => void)[] = [];
export function addColorSchemeListener(callback: (theme: string) => void) {
    colorSchemeListeners.push(callback);
}

// baseBody 基础设置
export default function BaseBody() {


    useLayoutEffect(() => {
        function change(e: any) {
            if (e.matches) {
                document.documentElement.setAttribute('theme', 'dark');
                colorSchemeListeners.forEach(t => t('dark'));
            } else {
                document.documentElement.setAttribute('theme', 'default');
                colorSchemeListeners.forEach(t => t('default'));
            }
        }
        const themeMedia = window.matchMedia("(prefers-color-scheme: dark)");
        themeMedia.addListener(change);
        return () => {
            themeMedia.removeListener(change)
        }
    }, [])


    const [lang, setLang] = useState(localStorage.getItem('lang'));
    const [currentTheme, setCurrentTheme] = useState(localStorage.getItem('theme'));
    const [currentPrimaryColor, setCurrentPrimaryColor] = useState(localStorage.getItem('primary-color'));

    function changeTheme(item: any) {
        let theme = item.code
        if (theme === 'followOs') {
            theme = window.matchMedia && window.matchMedia('(prefers-color-scheme: dark)').matches ? 'dark' : 'default'
        }

        const html = document.documentElement;
        html.setAttribute('theme', theme);
        localStorage.setItem('theme', item.code);
        setCurrentTheme(item.code);
        colorSchemeListeners.forEach(t => t(theme));
    }

    const changePrimaryColor = (item: any) => {
        const html = document.documentElement;
        html.setAttribute('primary-color', item.code);
        localStorage.setItem('primary-color', item.code);
        setCurrentPrimaryColor(item.code)
    };

    function changeLang() {
        const lang = localStorage.getItem('lang') === 'en' ? 'zh-cn' : 'en'
        localStorage.setItem('lang', lang);
        location.reload();
    }

    return <>
        <div className={styles.title}>
            {i18n('setting.text.background')}
        </div>
        <ul className={styles.backgroundList}>
            {backgroundList.map((item) => {
                return (
                    <div className={styles.themeItemBox}>
                        <li key={item.code} className={classnames({ [styles.current]: currentTheme == item.code })} onClick={changeTheme.bind(null, item)} style={{ backgroundImage: `url(${item.img})` }} />
                        {item.name}
                    </div>
                );
            })}
        </ul>
        <div className={styles.title}>
            {i18n('setting.text.language')}
        </div>
        <div>
            <Radio.Group onChange={changeLang} value={lang}>
                <Radio value='zh-cn'>{i18n('setting.text.zh-cn')}</Radio>
                <Radio value='en'>{i18n('setting.text.en')}</Radio>
            </Radio.Group>
        </div>
    </>
}