import React, { memo, useEffect, useLayoutEffect, useRef, useState } from 'react';
import styles from './index.less';
import classnames from 'classnames';
import { ConfigProvider } from 'antd';
import systemService from '@/service/system';
import LoadingLiquid from '@/components/Loading/LoadingLiquid';
import i18n from '@/i18n';
import { ThemeType } from '@/utils/constants';
import Setting from '@/components/Setting';
import { ipcApiRoute } from '@/api/main';
import { ipc } from '@/utils/ipcRenderer';

interface IProps {
  className?: any;
}

/** 重启次数 */
const restartCount = 30;

declare global {
  interface Window {
    _BaseURL: string;
  }
  const __APP_VERSION__: string;
  const __BUILD_TIME__: string;
  const __UMI_ENV__: string;
  const __APP_PORT__: string;
  const __APP_NAME__: string;
}
console.log("AppContainer=======__APP_VERSION__" + __APP_VERSION__);
console.log("AppContainer=======__UMI_ENV__" + __UMI_ENV__);
console.log("AppContainer=======__BUILD_TIME__" + __BUILD_TIME__);
console.log("AppContainer=======__APP_PORT__" + __APP_PORT__);


export default memo<IProps>(function AppContainer({ className, children }) {
  const [startSchedule, setStartSchedule] = useState(0); // 0 初始状态 1 服务启动中 2 启动成功
  const [serviceFail, setServiceFail] = useState(false);


  useLayoutEffect(() => {
    settings();
  }, []);

  useEffect(() => {
    startJavaServer();
    detectionService();
  }, []);

  function detectionService() {
    setServiceFail(false);
    let flag = 0;
    const time = setInterval(() => {
      systemService.testService().then(() => {
        clearInterval(time);
        setStartSchedule(2);
        flag++;
      }).catch(error => {
        setStartSchedule(1);
        flag++;
      });
      if (flag > restartCount) {
        setServiceFail(true);
        clearInterval(time);
      }
    }, 1000);
  }

  function startJavaServer() {
    console.log("环境" + __UMI_ENV__);
    if (__UMI_ENV__ == "desktop") {
      const model = localStorage.getItem('_MODEL') || "local";
      console.log("模式" + model);
      if (model == "local") {
        const appPort = localStorage.getItem('_APP_PORT') || __APP_PORT__;
        console.log("本地模式" + parseInt(appPort));
        ipc.invoke(ipcApiRoute.javaServerStatus, {}).then((runStatus) => {
          console.log("运行状态：" + JSON.stringify(runStatus));
          if (runStatus.flag == false) {
            ipc.invoke(ipcApiRoute.startJavaServer, parseInt(appPort)).then((dara) => {
              if (dara.code != 0) {
                console.log("启动一场");
              }
            })
          }
        })
      }
    }
  }

  function settings() {
    let theme = localStorage.getItem('theme') || ThemeType.Dark;
    if (theme === 'followOs') {
      theme = window.matchMedia && window.matchMedia('(prefers-color-scheme: dark)').matches ? 'dark' : 'default'
    }
    document.documentElement.setAttribute('theme', theme);

    document.documentElement.setAttribute(
      'primary-color',
      localStorage.getItem('primary-color') || 'polar-blue',
    );

    if (!localStorage.getItem('lang')) {
      localStorage.setItem('lang', 'zh-cn');
    }
  }

  return (
    <ConfigProvider prefixCls="custom">
      {/* 待启动状态 */}
      {startSchedule === 0 && <div className={classnames(className, styles.app)}></div>}
      {/* 服务启动中 */}
      {startSchedule === 1 && <div className={styles.starting}>
        <div>
          {!serviceFail && <LoadingLiquid />}
          <div className={styles.hint}>
            <Setting text={i18n('app.setting.text')} />
          </div>
          {serviceFail && (
            <>
              <div className={styles.restart}>
                {i18n('app.setting.contact')}：<a href="https://gitee.com/failedgoddess/TestHub/issues">Gitee</a>
              </div>
              <div className={styles.restart} onClick={detectionService}>
                {i18n('app.setting.restart')}
              </div>
              {/* <div className={styles.restart} onClick={startJavaServer}>
                设置服务器
              </div> */}
            </>
          )}
        </div>
      </div>}
      {/* 服务启动完成 */}
      {startSchedule === 2 && <div className={classnames(className, styles.app)}>{children}</div>}
    </ConfigProvider>
  );
});
