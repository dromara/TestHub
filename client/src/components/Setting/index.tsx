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
import { ipcApiRoute } from '@/api/main';
import { ipc } from '@/utils/ipcRenderer';


interface IProps {
  className?: string;
  text?: string;
}

export enum AiSqlSourceType {
  OPENAI = 'OPENAI',
  RESTAI = 'RESTAI'
}
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

export default memo<IProps>(function Setting({ className, text }) {
  const [isModalVisible, setIsModalVisible] = useState(false);

  const menusList = [
    {
      label: i18n('setting.nav.basic'),
      icon: '\ue795',
      body: <BaseBody />
    },
    {
      label: i18n('setting.nav.proxy'),
      icon: '\ue99b',
      body: <ProxyBody />
    },
    {
      label: i18n('setting.nav.aboutUs'),
      icon: '\ue60c',
      body: <>
        <div className={styles.aboutUs}>
          <BrandLogo size={60} className={styles.brandLogo} />
          <div className={styles.brief}>
            <div className={styles.appName}>{__APP_NAME__}</div>
            <div className={styles.env}>
              {i18n('setting.text.currentEnv')}:{__UMI_ENV__}
            </div>
            <div className={styles.version}>
              {i18n('setting.text.currentVersion')}:v{__APP_VERSION__} build {__BUILD_TIME__}
            </div>
            <a target='blank' href='https://gitee.com/failedgoddess/TestHub' className={styles.log}>
              {i18n('setting.text.viewingUpdateLogs')}
            </a>
          </div>
        </div>
      </>
    },
  ]

  const [currentMenu, setCurrentMenu] = useState(menusList[0]);

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

  useEffect(() => {
    // getVersions();
  }, [])

  const close = () => { };

  function getVersions() {
    try {
      const time = +(localStorage.getItem('update-hint-time') || 0);
      const nowTime = new Date().getTime();
      if (time < nowTime) {
        const xhr = new XMLHttpRequest();
        xhr.withCredentials = true;
        xhr.open('GET', `http://test.sqlgpt.cn/gateway/client/version/check`);
        xhr.onload = function () {
          if (xhr.readyState === 4) {
            if (xhr.status === 200) {
              var responseText = xhr.responseText;
              openNotification(JSON.parse(responseText));
              const time = new Date().getTime() + 2 * 60 * 60 * 1000;
              localStorage.setItem('update-hint-time', time.toString())
              // 处理返回值
            } else {
              // 请求失败的处理
            }
          }
        };
        xhr.send();
      }
    }
    catch {

    }
  }

  const openNotification = (responseText: any) => {
    try {
      if (responseText.version !== '1.0.11') {
        const key = `open${Date.now()}`;

        function updateHint() {
          notification.close(key);
          const time = new Date().getTime() + 24 * 60 * 60 * 1000;
          localStorage.setItem('update-hint-time', time.toString());
        }

        function go() {
          window.open('https://chat2db.opensource.alibaba.com/docs/guides/download');
          notification.close(key);
        }

        const btn = (
          <div className={styles.updateHint}>
            <div className={styles.later} onClick={updateHint}>
              稍后提醒我
            </div>
            <a target="_blank" href={responseText.downloadLink} className={styles.go} >
              前往更新
            </a>
          </div>

        );

        notification.open({
          message: '更新提醒',
          description: `监测到最新版本 v${responseText.version}`,
          btn,
          key,
          onClose: close,
        });
      }
    }
    catch {

    }
  };

  const showModal = () => {
    setIsModalVisible(true);
  };

  const handleOk = () => {
    setIsModalVisible(false);
  };

  const handleCancel = () => {
    setIsModalVisible(false);
  };

  function changeMenu(t: any) {
    setCurrentMenu(t);
  }

  return (
    <>
      <div className={classnames(className, styles.box)} onClick={showModal}>
        {
          text ?
            <span className={styles.setText}>{text}</span>
            :
            <Iconfont code="&#xe795;"></Iconfont>
        }
      </div>
      <Modal
        open={isModalVisible}
        onOk={handleOk}
        onCancel={handleCancel}
        footer={false}
        width={800}
        maskClosable={false}
      >
        <div className={styles.modalBox}>
          <div className={styles.menus}>
            <div className={classnames(styles.menusTitle)}>
              {i18n('setting.title.setting')}
            </div>
            {
              menusList.map((t, index) => {
                return <div key={index} onClick={changeMenu.bind(null, t)} className={classnames(styles.menuItem, { [styles.activeMenu]: t.label === currentMenu.label })}>
                  <Iconfont code={t.icon} />
                  {t.label}
                </div>
              })
            }
          </div>
          <div className={styles.menuContent}>
            <div className={classnames(styles.menuContentTitle)}>
              {currentMenu.label}
            </div>
            {currentMenu.body}
          </div>
        </div>
      </Modal>
    </>
  );
});

export function addColorSchemeListener(callback: (theme: string) => void) {
  colorSchemeListeners.push(callback);
}

// baseBody 基础设置
export function BaseBody() {
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

// 代理设置
export function ProxyBody() {
  const [apiPrefix, setApiPrefix] = useState(window._BaseURL)
  const [appPort, setAppPort] = useState<number>(parseInt(localStorage.getItem('_APP_PORT') || __APP_PORT__ || "12003"))
  //服务模式
  const [model, setModel] = useState(__UMI_ENV__ == "desktop" ? localStorage.getItem('_MODEL') || "local" : "server");

  const handleRadioChange = (e: { target: { value: any; }; }) => {
    setModel(e.target.value)
  };

  function updateApi(e: any) {
    console.log(e.target.value)
    setApiPrefix(e.target.value)
  }

  function affirmUpdateApi() {
    if (model == 'local') {
      if (!appPort) {
        return
      }
      const loaclApiPrefix = 'http://localhost:' + appPort
      localStorage.setItem('_BaseURL', loaclApiPrefix);
      localStorage.setItem('_APP_PORT', appPort + "");
      localStorage.setItem('_MODEL', model);
      console.log("异步起动中");
      location.reload();

    } else {
      if (!apiPrefix) {
        return
      }
      const xhr = new XMLHttpRequest();
      xhr.withCredentials = true;
      xhr.open('GET', `${apiPrefix}/api/system/getVersion`);
      xhr.onload = function () {
        if (xhr.status === 200) {
          localStorage.setItem('_BaseURL', apiPrefix);
          localStorage.setItem('_MODEL', model);
          location.reload();
        } else {
          message.error('接口测试不通过')
        }
      };
      xhr.send();
    }

  }

  return <>
    <div className={styles.title}>
      {i18n('setting.label.model')}
    </div>
    <div className={classnames(styles.content, styles.chatGPTKey)}>
      <Radio.Group onChange={handleRadioChange} value={model}>
        <Radio value='local'>
          <Tooltip title={i18n('setting.text.localMsg')}>
            {i18n('setting.text.local')}
          </Tooltip>
        </Radio>
        <Radio value='server'>
          <Tooltip title={i18n('setting.text.localMsg')}>
            {i18n('setting.text.server')}
          </Tooltip>
        </Radio>
      </Radio.Group>
    </div>
    {
      model == "local" &&
      <>
        <div className={styles.title}>
          {i18n('setting.text.port')}
        </div>
        <div className={classnames(styles.content, styles.chatGPTKey)}>
          <ProFormDigit
            placeholder={i18n('setting.msg.port')}
            rules={[{ required: true, message: i18n('setting.msg.notNull') }]}
            fieldProps={{
              defaultValue: appPort,
              step: "1",
              onChange: (value) => setAppPort(value)
            }}
          />
        </div>
      </>
    }
    {
      model == "server" &&
      <>
        <div className={styles.title}>
          {i18n('setting.label.serviceAddress')}
        </div>
        <div className={classnames(styles.content, styles.chatGPTKey)}>
          {/* <Input value={apiPrefix} onChange={updateApi} placeholder='http://localhost:12003' /> */}
          <ProFormText
            placeholder={"http://localhost:" + __APP_PORT__}
            rules={[{ required: true, message: i18n('setting.msg.notNull') }]}
            fieldProps={{
              defaultValue: apiPrefix,
              onChange: (e) => updateApi(e)
            }}
          />
        </div>
      </>
    }

    <div className={styles.bottomButton}>
      <Button theme='default' onClick={affirmUpdateApi}>{i18n('setting.button.apply')}</Button>
    </div>
  </>
}
