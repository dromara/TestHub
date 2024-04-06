import React, { useEffect, useLayoutEffect, useState } from 'react';
import i18n, { isEn } from '@/i18n';
import { Button, ConfigProvider } from 'antd';
import antdEnUS from 'antd/locale/en_US';
import antdZhCN from 'antd/locale/zh_CN';
import systemService from '@/service/system';
import { useTheme } from '@/hooks/useTheme';
import { getAntdThemeConfig } from '@/theme';
import init from '../init/init';
import { ThemeType } from '@/constants';
import GlobalComponent from '../init/GlobalComponent';
import styles from './index.less';
import ServerLoading from './ServerLoading';
import Setting from '@/blocks/Setting';
import Contact from '@/components/base/Contact';
import { Outlet } from 'umi';
import { ipcApiRoute } from '@/electron/main';
import { ipc } from '@/electron/ipcRenderer';
import { getLocalPort, getModel } from '@/utils/localStorage';

console.log('AppContainer=======__APP_VERSION__' + __APP_VERSION__);
console.log('AppContainer=======__UMI_ENV__' + __UMI_ENV__);
console.log('AppContainer=======__BUILD_TIME__' + __BUILD_TIME__);
console.log('AppContainer=======__APP_PORT__' + __APP_PORT__);

/** 重启次数 */
const restartCount = 10;
const grap = 1000;

const GlobalLayout = () => {
  const [appTheme, setAppTheme] = useTheme();
  const [antdTheme, setAntdTheme] = useState<any>({});

  const [startSchedule, setStartSchedule] = useState(0); // 0 初始状态 1 服务启动中 2 启动成功
  const [serviceFail, setServiceFail] = useState(false);

  useLayoutEffect(() => {
    startJavaServer();
    detectionService();
  }, []);

  useLayoutEffect(() => {
    init();
    monitorOsTheme();
  }, []);

  function detectionService() {
    setServiceFail(false);
    let flag = 0;
    const time = setInterval(() => {
      systemService
        .testService()
        .then(() => {
          clearInterval(time);
          setStartSchedule(2);
          flag++;
        })
        .catch((error) => {
          setStartSchedule(1);
          flag++;
        });
      if (flag > restartCount) {
        setServiceFail(true);
        clearInterval(time);
      }
    }, grap);
  }

  function startJavaServer() {
    if (__UMI_ENV__ == 'desktop') {
      const model = getModel() || 'local';
      console.log('模式' + model);
      if (model == 'local') {
        const appPort = getLocalPort();
        console.log('本地模式' + parseInt(appPort));
        ipc.invoke(ipcApiRoute.javaServerStatus, {}).then((runStatus) => {
          console.log('运行状态：' + JSON.stringify(runStatus));
          if (runStatus.flag == false) {
            ipc.invoke(ipcApiRoute.startJavaServer, parseInt(appPort)).then((dara) => {
              if (dara.code != 0) {
                console.log('启动异常');
              }
            });
          }
        });
      }
    }
  }

  // useCopyFocusData();

  useLayoutEffect(() => {
    setAntdTheme(getAntdThemeConfig(appTheme));
  }, [appTheme]);

  // 监听系统(OS)主题变化
  const monitorOsTheme = () => {
    function change(e: any) {
      if (appTheme.backgroundColor === ThemeType.FollowOs) {
        setAppTheme({
          ...appTheme,
          backgroundColor: e.matches ? ThemeType.Dark : ThemeType.Light,
        });
      }
    }
    const matchMedia = window.matchMedia('(prefers-color-scheme: dark)');
    matchMedia.onchange = change;
  };

  return (
    <ConfigProvider locale={isEn ? antdEnUS : antdZhCN} theme={antdTheme}>
      <div className={styles.app}>
        {/* {startSchedule === 0 && <div>待启动状态</div>} */}
        {/* 服务启动中 */}
        {startSchedule != 2 && (
          <>
            <div className={styles.serverLoading}>
              {!serviceFail && <ServerLoading />}
              <div className={styles.hint}>
                <Setting
                  noLogin
                  className={styles.setIcon}
                  render={
                    <Button type="link" style={{ fontSize: 14 }}>
                      {i18n('setting.title.setting')}
                    </Button>
                  }
                />
              </div>
              {serviceFail && (
                <div className={styles.loadingBox}>
                  <Button type="link" onClick={detectionService} style={{ fontSize: 14 }}>
                    {i18n('common.text.tryToRestart')}
                  </Button>
                  <div style={{ marginTop: 8 }}>
                    <Contact />
                  </div>
                </div>
              )}
            </div>
          </>
        )}
        {/* 服务启动完成   <Outlet /> */}
        {startSchedule === 2 && <Outlet />}
      </div>
      <GlobalComponent />
    </ConfigProvider>
  );
};

export default GlobalLayout;
