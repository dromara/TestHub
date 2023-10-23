import React from 'react';
import styles from './index.less';
import i18n from '@/i18n';
import BrandLogo from '@/components/BrandLogo';


// AboutUs 关于我们
export default function AboutUs() {
    return <>
        <div className={styles.aboutUs}>
            <BrandLogo size={60} className={styles.brandLogo} />
            <div className={styles.brief}>
                <div className={styles.appName}>{__APP_NAME__}</div>
                <div className={styles.env}>
                    {i18n('setting.text.currentEnv')}:{__UMI_ENV__}
                </div>
                <div className={styles.version}>
                    {i18n('setting.text.currentVersion')}:v{__APP_VERSION__} build {__BUILD_TIME__}
                </div>
                <a target='blank' href='https://gitee.com/dromara/TestHub' className={styles.log}>
                    {i18n('setting.text.viewingUpdateLogs')}
                </a>
                <a target='blank' href='http://www.nsrule.com' className={styles.log}>
                    {i18n('setting.text.manual')}
                </a>
            </div>
        </div>
    </>
}