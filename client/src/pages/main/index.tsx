import React, { memo, useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { Dropdown, Modal, Tooltip } from 'antd';
import classnames from 'classnames';
import { history } from 'umi';
import Iconfont from '@/components/base/Iconfont';
import BrandLogo from '@/components/base/BrandLogo';

import i18n from '@/i18n';
import { getUser, userLogout } from '@/service/user';
import { INavItem } from '@/typings/main';
import { ILoginUser, IRole } from '@/typings/server/user';

// ----- store -----
import { useMainStore, setMainPageActiveTab } from '@/pages/main/store/main';

// ----- block -----

import Setting from '@/blocks/Setting';

import styles from './index.less';
import { useUpdateEffect } from '@/hooks';
import useMonacoTheme from '@/components/TestHub/MyMonacoEditor/useMonacoTheme';
import ProjectSelect from '@/components/TestHub/ProjectSelect';
import { getCurrentProject } from '@/utils/localStorage';
import MainTest from './test';
import Http from './http';
import Case from './case';
import Tools from './tools';

const initNavConfig: INavItem[] = [
  // {
  //   key: 'case',
  //   isLoad: false,
  //   name: '用例',
  //   iconFontSize: 24,
  //   icon: '\ue6b6',
  //   component: <Case />,
  // },
  // {
  //   key: 'assembly',
  //   isLoad: false,
  //   name: '组件库',
  //   icon: '\ue60f',
  //   iconFontSize: 24,
  //   component: <MainTest />,
  // },
  // {
  //   key: 'dataPack',
  //   isLoad: false,
  //   name: 'DataPack',
  //   icon: '\ue630',
  //   iconFontSize: 24,
  //   component: <MainTest />,
  // },
  {
    key: 'http',
    isLoad: false,
    name: 'HTTP',
    icon: '\ue63b',
    iconFontSize: 24,
    component: <Http />,
  },
  {
    key: 'tools',
    isLoad: false,
    name: '百宝箱',
    icon: '\ue64f',
    iconFontSize: 24,
    component: <Tools />,
  },

  // {
  //   key: 'github',
  //   icon: '\ue885',
  //   iconFontSize: 24,
  //   isLoad: false,
  //   openBrowser: 'https://gitee.com/dromara/TestHub/',
  //   name: 'Github',
  // },
];

const MainPage = memo(() => {
  const project = getCurrentProject();
  const navigate = useNavigate();
  const [navConfig, setNavConfig] = useState<INavItem[]>(initNavConfig);
  const [userInfo, setUserInfo] = useState<ILoginUser>();
  const mainPageActiveTab = useMainStore((state) => state.mainPageActiveTab);
  const [activeNavKey, setActiveNavKey] = useState<string>(
    __ENV__ === 'desktop' ? mainPageActiveTab : window.location.pathname.split('/')[1] || mainPageActiveTab,
  );

  useMonacoTheme();
  // useEffect(() => {
  // }, []);

  useUpdateEffect(() => {
    switchingNav(mainPageActiveTab);
  }, [mainPageActiveTab]);

  // 切换tab
  useEffect(() => {
    // 获取当前地址栏的tab
    const activeIndex = navConfig.findIndex((t) => `${t.key}` === activeNavKey);
    if (activeIndex > -1) {
      navConfig[activeIndex].isLoad = true;
      setNavConfig([...navConfig]);
      if (__ENV__ !== 'desktop') {
        const href = window.location.origin + '/' + activeNavKey;
        window.history.pushState({}, '', href);
      }
    }
  }, [activeNavKey]);
  const switchingNav = (key: string) => {
    if (key === 'github') {
      window.open('https://gitee.com/dromara/TestHub/', '_blank');
    } else {
      setActiveNavKey(key);
      setMainPageActiveTab(key);
    }
  };

  const handleLogout = () => {
    userLogout().then(() => {
      setUserInfo(undefined);
      navigate('/login');
    });
  };
  const [isProjectSelect, setIsProjectSelect] = useState(false);
  const renderUser = () => {
    return (
      <Dropdown
        menu={{
          items: [
            {
              key: '1',
              label: (
                <div className={styles.userDropdown} onClick={handleLogout}>
                  <Iconfont code="&#xe6b2;" />
                  {i18n('login.text.logout')}
                </div>
              ),
            },
          ],
        }}
        placement="bottomRight"
        trigger={['click']}
      >
        <div className={styles.userBox}>
          <Iconfont code="&#xe64c;" className={styles.questionIcon} />
        </div>
      </Dropdown>
    );
  };

  return (
    <div className={styles.page}>
      <div className={styles.layoutLeft}>
        {project != null && (
          <div
            style={{ width: 38, height: 38 }}
            className={styles.projectLogo}
            onClick={() => {
              setIsProjectSelect(true);
            }}
          >
            {project.name?.charAt(0)}
          </div>
        )}
        {project == null && (
          <BrandLogo
            size={48}
            onClick={() => {
              setIsProjectSelect(true);
            }}
            className={styles.brandLogo}
          />
        )}

        <ProjectSelect
          isOpen={isProjectSelect}
          callback={(flag) => {
            if (flag) {
              //有变更
              location.reload();
            }
            setIsProjectSelect(false);
          }}
        />

        <ul className={styles.navList}>
          {navConfig.map((item) => {
            return (
              <Tooltip key={item.key} placement="right" title={item.name}>
                <li
                  className={item.key == activeNavKey ? styles.activeNav : styles.nav}
                  onClick={() => switchingNav(item.key)}
                >
                  <Iconfont size={item.iconFontSize} className={styles.icon} code={item.icon} />
                  <div className={styles.name}>{item.name}</div>
                </li>
              </Tooltip>
            );
          })}
        </ul>
        <div className={styles.footer}>
          <Tooltip placement="right" title="个人中心">
            {userInfo?.roleCode !== IRole.DESKTOP ? renderUser() : null}
          </Tooltip>
          <Setting className={styles.setIcon} noLogin={false} />
        </div>
      </div>
      <div className={styles.layoutRight}>
        {navConfig.map((item) => {
          return (
            <div key={item.key} className={styles.componentBox} hidden={activeNavKey !== item.key}>
              {item.isLoad ? item.component : null}
            </div>
          );
        })}
      </div>
    </div>
  );
});
export default MainPage;
