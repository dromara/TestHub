import React, { ReactNode, useEffect, useState } from 'react';
import classnames from 'classnames';
import Iconfont from '@/components/base/Iconfont';
import { Modal, Tooltip } from 'antd';
import i18n from '@/i18n';
import BaseSetting from './BaseSetting';
import ProxySetting from './ProxySetting';
import About from './About';
import styles from './index.less';
import { ILatestVersion } from '@/service/config';
import UpdateDetection, { IUpdateDetectionRef, UpdatedStatusEnum } from '@/blocks/Setting/UpdateDetection';
import User from './User';

interface IProps {
  className?: string;
  render?: ReactNode;
  noLogin?: boolean; // 用于在没有登录的页面使用，不显示登录等需要登录的功能
  defaultArouse?: boolean; // 是否默认弹出
  defaultMenu?: number; // 默认选中的菜单
}
export interface IUpdateDetectionData extends ILatestVersion {
  updatedStatusEnum: UpdatedStatusEnum;
  needUpdate: boolean;
}

function Setting(props: IProps) {
  const { className, noLogin = true, defaultArouse, defaultMenu = 0 } = props;
  const [isModalVisible, setIsModalVisible] = useState(false);
  const [currentMenu, setCurrentMenu] = useState<number>(defaultMenu);

  useEffect(() => {
    if (defaultArouse) {
      showModal();
    }
  }, []);

  const showModal = (_currentMenu: number = 0) => {
    setCurrentMenu(_currentMenu);
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

  const menusList = [
    {
      label: i18n('setting.nav.basic'),
      icon: '\ue795',
      body: <BaseSetting />,
      code: 'basic',
    },
    {
      label: '服务设置',
      icon: '\ue608',
      body: <ProxySetting />,
      code: 'proxy',
    },
    {
      label: '用户设置',
      icon: '\ue8c9',
      body: <User />,
    },
    {
      label: i18n('setting.nav.aboutUs'),
      icon: '\ue625',
      // rightSlot: (
      //   <div className={classnames(styles.rightSlot, styles.rightSlotAbout)}>
      //     <Tooltip title={`发现新版本`}>
      //       <Iconfont code="&#xe69c;" />
      //     </Tooltip>
      //   </div>
      // ),
      body: <About />,
      code: 'about',
    },
  ];

  return (
    <>
      {/* <Tooltip placement="right" title={i18n('setting.title.setting')}> */}
      <div
        className={classnames(className, styles.box)}
        onClick={() => {
          showModal();
        }}
      >
        {props.render ? props.render : <Iconfont className={styles.settingIcon} code="&#xe795;" />}
      </div>
      {/* </Tooltip> */}

      {/* <UpdateDetection
        setUpdateDetectionData={setUpdateDetectionData}
        updateDetectionData={updateDetectionData}
        openSettingModal={showModal}
        ref={updateDetectionRef}
      /> */}
      <Modal
        open={isModalVisible}
        onOk={handleOk}
        onCancel={handleCancel}
        // footer={false}
        footer={null}
        width={800}
        maskClosable={false}
      >
        <div className={styles.modalBox}>
          <div className={styles.menus}>
            <div className={classnames(styles.menusTitle)}>{i18n('setting.title.setting')}</div>
            {menusList.map((item, index) => {
              // 如果是没有登录的页面，不显示用户设置的功能
              if (noLogin && item.code == 'user') {
                return false;
              }
              return (
                <div
                  key={index}
                  onClick={changeMenu.bind(null, index)}
                  className={classnames(styles.menuItem, {
                    [styles.activeMenu]: item.label === menusList[currentMenu].label,
                  })}
                >
                  <Iconfont className={styles.prefixIcon} code={item.icon} />
                  {item.label}
                  {/* {item.rightSlot} */}
                </div>
              );
            })}
          </div>
          <div className={styles.menuContent}>
            <div className={classnames(styles.menuContentTitle)}>{menusList[currentMenu].label}</div>
            {menusList[currentMenu].body}
          </div>
        </div>
      </Modal>
    </>
  );
}

export default Setting;
