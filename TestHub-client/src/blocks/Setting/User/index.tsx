import React from 'react';
import styles from './index.less';
import i18n from '@/i18n';
import { Button } from 'antd';
import { getUserInfo, setSatoken } from '@/utils/localStorage';
import { loginOut } from '@/utils';

// User 用户信息
export default function User() {
  return (
    <>
      <div className={styles.user}>
        <Button
          onClick={() => {
            loginOut();
            location.reload();
          }}
        >
          退出登录
        </Button>
      </div>
    </>
  );
}
