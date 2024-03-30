import React from 'react';
import { Tooltip } from 'antd';
import i18n from '@/i18n';
import classnames from 'classnames';
import styles from './index.less';
import contactImg from '@/assets/img/contact.jpeg';
import { GithubOutlined, WechatOutlined } from '@ant-design/icons';
import Iconfont from '../Iconfont';

interface IProps {}

const Contact = (props: IProps) => {
  return (
    <>
      <div className={styles.contact}>
        <div>{i18n('common.text.contactUs')}</div>
        <div className={styles.icons}>
          <GithubOutlined className={styles.icon} onClick={() => window.open('https://github.com/dromara/TestHub')} />
          <Iconfont
            className={classnames(styles.icon, styles.notFirst)}
            code="&#xe686;"
            onClick={() => window.open('https://gitee.com/dromara/TestHub')}
          />
          <Tooltip placement="bottom" title={<img style={{ width: 150, height: 145 }} src={contactImg} />}>
            <WechatOutlined className={classnames(styles.icon, styles.notFirst)} />
          </Tooltip>
        </div>
      </div>
    </>
  );
};

export default Contact;
