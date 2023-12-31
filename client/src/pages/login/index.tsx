import {
  LockOutlined,
  UserOutlined,
} from '@ant-design/icons';
import {
  LoginForm,
  ProConfigProvider,
  ProFormCheckbox,
  ProFormText,
} from '@ant-design/pro-components';
import React, { useEffect, useState } from 'react';
import './index.less';
import styles from './index.less';
import { IUserInfo } from '@/typings';
import userService, { } from '@/service/user';
import { LoginResDto } from '@/typings/server/user';
import { message } from 'antd';
import { getLocationHash } from '@/utils';
import { getUserInfo, removeUserInfo, setSatoken, setUserInfo } from '@/utils/localStorage';
import i18n from '@/i18n';

const ipcRenderer = __UMI_ENV__ == "desktop" ? window.require("electron").ipcRenderer : null;

export default () => {
  const [formData, setFormData] = useState<IUserInfo>(getUserInfo());
  function handleLogin() {
    userService.login(formData)
      .then((loginResDto: LoginResDto) => {
        if (loginResDto.flag) {
          setSatoken(loginResDto.token);
          if (formData.rememberMe) {
            setUserInfo(formData);
          } else {
            // 否则移除
            removeUserInfo();
          }
          if (__UMI_ENV__ == "desktop") {
            ipcRenderer.send("login-success");
          } else {
            const params = getLocationHash();
            if (params.callback == undefined) {
              window.location.href = "/";
            } else {
              window.location.href = params.callback;
            }
          }
        } else {
          message.error(loginResDto.msg);
        }
      })
      .catch((error) => {
        message.error(error);
      });
  }
  useEffect(() => {
    // console.log(formData);
  }, [formData]);
  return (
    // <ProConfigProvider hashed={false} >
    <div className={styles.loginBox}>
      <div className={styles.loginItem}>
        <LoginForm
          className={styles.loginBox1}
          title={__APP_NAME__}
          subTitle={i18n('login.title.slogan')}
          initialValues={formData}
          onFinish={async (values) => { handleLogin(); return true }}
          submitter={{
            searchConfig: {
              submitText: i18n('login.title.name')
            },
          }}
        >
          <ProFormText
            name="userName"
            fieldProps={{
              size: 'large',
              prefix: <UserOutlined className={'prefixIcon'} />,
              onChange: (e) => {
                setFormData(prev => ({ ...prev, userName: e.target.value }))
              }
            }}
            placeholder={i18n('login.text.userName') + ': admin'}
            rules={[
              {
                required: true,
                message: i18n('login.msg.notNull'),
              },
              {
                max: 18,
                message: i18n('login.msg.userNameLength'),
              },
            ]}
          />
          <ProFormText.Password
            name="password"
            fieldProps={{
              size: 'large',
              prefix: <LockOutlined className={'prefixIcon'} />,
              onChange: (e) => setFormData(prev => ({ ...prev, password: e.target.value }))
            }}
            placeholder={i18n('login.text.password') + ': 123456'}
            rules={[
              {
                required: true,
                message: i18n('login.msg.notNull'),
              },
              {
                min: 6,
                max: 12,
                message: i18n('login.msg.passwordLength'),
              },
            ]}
          />

          <div
            style={{
              marginBlockEnd: 24,
            }}
          >
            <ProFormCheckbox noStyle
              name="rememberMe"
              fieldProps={{
                onChange: (e) => setFormData(prev => ({ ...prev, rememberMe: e.target.checked }))
              }}>

              {i18n('login.text.rememberMe')}
            </ProFormCheckbox>
            <a
              style={{
                float: 'right',
              }}
            >
              {i18n('login.text.forgot')}
            </a>
          </div>
        </LoginForm>
      </div>
    </div >
    // </ProConfigProvider>
  );
};