import React from 'react';
import styles from './index.less';
interface IProps {}
function Empty(props: IProps) {
  return (
    <div className={styles.empty}>
      <div className={styles.ears}>{__APP_NAME__}</div>
    </div>
  );
}

export default Empty;
