import React, { memo, useEffect, useLayoutEffect, useState } from 'react';
import styles from './index.less';
import classnames from 'classnames';
import Iconfont from '@/components/Iconfont';
import Button from '@/components/Button';
import { Modal, Radio, message, notification, Tooltip } from 'antd';
import i18n from '@/i18n';
import BrandLogo from '@/components/BrandLogo';
import themeDarkImg from '@/assets/theme-dark.png';
import themeLightImg from '@/assets/theme-light.png';
import themeAutoImg from '@/assets/theme-auto.png';
import { ProFormDigit, ProFormText } from '@ant-design/pro-components';


// 代理设置
export default function ProxyBody() {
    const [apiPrefix, setApiPrefix] = useState(window._BaseURL)
    const [appPort, setAppPort] = useState<number>(parseInt(localStorage.getItem('_APP_PORT') || __APP_PORT__ || "12003"))
    //服务模式
    const [model, setModel] = useState(__UMI_ENV__ == "desktop" ? localStorage.getItem('_MODEL') || "local" : "server");

    const handleRadioChange = (e: { target: { value: any; }; }) => {
        setModel(e.target.value)
    };

    function updateApi(e: any) {
        console.log(e.target.value)
        setApiPrefix(e.target.value)
    }

    function affirmUpdateApi() {
        if (model == 'local') {
            if (!appPort) {
                return
            }
            const loaclApiPrefix = 'http://localhost:' + appPort
            localStorage.setItem('_BaseURL', loaclApiPrefix);
            localStorage.setItem('_APP_PORT', appPort + "");
            localStorage.setItem('_MODEL', model);
            console.log("异步起动中");
            location.reload();

        } else {
            if (!apiPrefix) {
                return
            }
            const xhr = new XMLHttpRequest();
            xhr.withCredentials = true;
            xhr.open('GET', `${apiPrefix}/api/system/getVersion`);
            xhr.onload = function () {
                if (xhr.status === 200) {
                    localStorage.setItem('_BaseURL', apiPrefix);
                    localStorage.setItem('_MODEL', model);
                    location.reload();
                } else {
                    message.error('接口测试不通过')
                }
            };
            xhr.send();
        }

    }

    return <>
        <div className={styles.title}>
            {i18n('setting.label.model')}
        </div>
        <div className={classnames(styles.content, styles.chatGPTKey)}>
            <Radio.Group onChange={handleRadioChange} value={model}>
                <Radio value='local'>
                    <Tooltip title={i18n('setting.text.localMsg')}>
                        {i18n('setting.text.local')}
                    </Tooltip>
                </Radio>
                <Radio value='server'>
                    <Tooltip title={i18n('setting.text.localMsg')}>
                        {i18n('setting.text.server')}
                    </Tooltip>
                </Radio>
            </Radio.Group>
        </div>
        {
            model == "local" &&
            <>
                <div className={styles.title}>
                    {i18n('setting.text.port')}
                </div>
                <div className={classnames(styles.content, styles.chatGPTKey)}>
                    <ProFormDigit
                        placeholder={i18n('setting.msg.port')}
                        rules={[{ required: true, message: i18n('setting.msg.notNull') }]}
                        fieldProps={{
                            defaultValue: appPort,
                            step: "1",
                            onChange: (value) => setAppPort(value)
                        }}
                    />
                </div>
            </>
        }
        {
            model == "server" &&
            <>
                <div className={styles.title}>
                    {i18n('setting.label.serviceAddress')}
                </div>
                <div className={classnames(styles.content, styles.chatGPTKey)}>
                    {/* <Input value={apiPrefix} onChange={updateApi} placeholder='http://localhost:12003' /> */}
                    <ProFormText
                        placeholder={"http://localhost:" + __APP_PORT__}
                        rules={[{ required: true, message: i18n('setting.msg.notNull') }]}
                        fieldProps={{
                            defaultValue: apiPrefix,
                            onChange: (e) => updateApi(e)
                        }}
                    />
                </div>
            </>
        }

        <div className={styles.bottomButton}>
            <Button theme='default' onClick={affirmUpdateApi}>{i18n('setting.button.apply')}</Button>
        </div>
    </>
}