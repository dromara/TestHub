import React from 'react';
import styles from './index.less';
import i18n from '@/i18n';
import { Button } from 'antd';
import { getUserInfo, setSatoken } from '@/utils/localStorage';

// User 用户信息
export default function User() {

    return <>
        <div className={styles.user}>
            <Button onClick={() => { setSatoken(""); location.reload(); }} hidden={getUserInfo().userName == undefined}>{i18n('setting.button.loginOut')}</Button>
            <Button hidden={getUserInfo().userName != undefined} >{i18n('setting.button.loginOut')}</Button>
        </div>
    </>
}