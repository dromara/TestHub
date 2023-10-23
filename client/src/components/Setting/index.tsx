import React, { memo, useState } from 'react';
import styles from './index.less';
import classnames from 'classnames';
import Iconfont from '@/components/Iconfont';
import { Modal } from 'antd';
import i18n from '@/i18n';
import BaseBody from './components/basic';
import ProxyBody from './components/proxy';
import AboutUs from './components/aboutUs';
import User from './components/user';



interface IProps {
  className?: string;
  text?: string;
}

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
      icon: '\ue608',
      body: <ProxyBody />
    },
    {
      label: i18n('setting.nav.user'),
      icon: '\ue8c9',
      body: <User />
    },
    {
      label: i18n('setting.nav.aboutUs'),
      icon: '\ue625',
      body: <AboutUs />
    },
  ]

  const [currentMenu, setCurrentMenu] = useState(menusList[0]);


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