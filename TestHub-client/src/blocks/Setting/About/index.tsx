import React from 'react';
import styles from './index.less';
import i18n from '@/i18n';
import BrandLogo from '@/components/base/BrandLogo';
import { formatDate, getUserTimezoneTimestamp } from '@/utils/date';
import Contact from '@/components/base/Contact';

interface IProps {}

// 关于我们
export default function AboutUs(props: IProps) {
  return (
    <div className={styles.aboutUs}>
      <div className={styles.brief}>
        <BrandLogo size={98} className={styles.brandLogo} />
        <div className={styles.appName}>{__APP_NAME__}</div>
        <div className={styles.version}>{__APP_VERSION__}</div>

        <div className={styles.env}>环境:{__ENV__}</div>
        <div className={styles.build}>
          构建时间:
          {formatDate(getUserTimezoneTimestamp(__BUILD_TIME__), 'yyyy-MM-dd')}
        </div>
        <div style={{ marginTop: 5 }}>
          <Contact />
        </div>
        <div className={styles.build}>
          <a target="blank" href={__DOC_URL__ + '/log.html'} className={styles.log}>
            更新日志
          </a>
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          <a target="blank" href={__DOC_URL__} className={styles.log}>
            使用手册
          </a>
        </div>
      </div>
    </div>
  );
}
