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
          const params = getLocationHash();
          if (params.callback == undefined) {
            window.location.href = "/";
          } else {
            window.location.href = params.callback;
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
          title="TestHub"
          subTitle="第二懂你的自动化测试工具"
          initialValues={formData}
          onFinish={async (values) => { handleLogin(); return true }}
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
            placeholder={'用户名: admin'}
            rules={[
              {
                required: true,
                message: '请输入用户名!',
              },
              {
                max: 12,
                message: '用户名长度必须在20个字符之内',
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
            placeholder={'密码: 123456'}
            rules={[
              {
                required: true,
                message: '请输入密码！',
              },
              {
                min: 6,
                max: 12,
                message: '密码长度必须在6到12个字符之间',
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
              记住密码
            </ProFormCheckbox>
            <a
              style={{
                float: 'right',
              }}
            >
              忘记密码
            </a>
          </div>
        </LoginForm>
      </div>
    </div>
    // </ProConfigProvider>
  );
};