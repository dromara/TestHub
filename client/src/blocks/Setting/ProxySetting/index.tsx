import React, { useState } from 'react';
import styles from './index.less';
import classnames from 'classnames';
import Iconfont from '@/components/base/Iconfont';
// import Button from '@/components/Button';
import { Radio, message, Tooltip, Button, notification } from 'antd';
import i18n from '@/i18n';
import { ProFormDigit, ProFormText } from '@ant-design/pro-components';
import { RadioChangeEvent } from 'antd/lib';
import { getLocalPort, getModel, getRemoteUrl, setLocalPort, setRemoteUrl } from '@/utils/localStorage';
import { getThisModel } from '@/utils';

// 代理设置
export default function ProxySetting() {
  const [apiPrefix, setApiPrefix] = useState(getRemoteUrl());
  const [appPort, setAppPort] = useState<number>(parseInt(getLocalPort() || __APP_PORT__ || '12003'));
  //服务模式
  const [model, setModel] = useState(getModel() == null ? getThisModel() : getModel());

  function affirmUpdateApi() {
    if (model == 'local') {
      if (!appPort) {
        return;
      }
      const loaclApiPrefix = 'http://localhost:' + appPort;

      setLocalPort(appPort);
      setModel(model);
      setRemoteUrl(loaclApiPrefix);

      // localStorage.setItem('_BaseURL', loaclApiPrefix);
      // localStorage.setItem('_APP_PORT', appPort + '');
      // localStorage.setItem('_MODEL', model);
      console.log('异步起动中');
      location.reload();
    } else {
      // console.log('apiPrefix' + apiPrefix);
      if (!apiPrefix) {
        return;
      }
      const xhr = new XMLHttpRequest();
      xhr.withCredentials = true;
      xhr.open('GET', `${apiPrefix}/api/system/getVersion`);
      xhr.onload = function () {
        if (xhr.status === 200) {
          // localStorage.setItem('_BaseURL', apiPrefix);
          // localStorage.setItem('_MODEL', model);
          setModel(model);
          setRemoteUrl(apiPrefix);
          location.reload();
        } else {
          message.error('接口测试不通过');
        }
      };
      xhr.onerror = function () {
        message.error('接口测试不通过');
      };
      xhr.send();
    }
  }
  const modelOptions = [
    { label: '本地', value: 'local' },
    { label: '远程', value: 'server' },
  ];

  return (
    <>
      <div className={styles.title}>服务模式</div>
      <div className={classnames(styles.content, styles.chatGPTKey)}>
        <Radio.Group
          options={modelOptions}
          onChange={({ target: { value } }: RadioChangeEvent) => {
            setModel(value);
          }}
          value={model}
          //   optionType="button"
          //   buttonStyle="solid"
        />
      </div>
      {model == 'local' && (
        <>
          <div className={styles.title}>端口</div>
          <div className={classnames(styles.content, styles.chatGPTKey)}>
            <ProFormDigit
              placeholder={'端口'}
              rules={[{ required: true, message: '不能为空' }]}
              fieldProps={{
                defaultValue: appPort,
                step: '1',
                onChange: (value) => setAppPort(value),
              }}
            />
          </div>
        </>
      )}
      {model == 'server' && (
        <>
          <div className={styles.title}>{i18n('setting.label.serviceAddress')}</div>
          <div className={classnames(styles.content, styles.chatGPTKey)}>
            <ProFormText
              placeholder={'http://localhost:' + __APP_PORT__}
              rules={[{ required: true, message: '不能为空' }]}
              fieldProps={{
                defaultValue: apiPrefix,
                onChange: (e: any) => {
                  setApiPrefix(e.target.value);
                },
              }}
            />
          </div>
        </>
      )}

      <div className={styles.bottomButton}>
        <Button type="primary" onClick={affirmUpdateApi}>
          {i18n('setting.button.apply')}
        </Button>
      </div>
    </>
  );
}
