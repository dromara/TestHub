import React, { memo } from 'react';
import styles from './index.less';
import classnames from 'classnames';
import Loading from '@/components/base/Loading/Loading';

interface IProps {
  className?: string;
}

export default memo<IProps>(({ className }) => {
  return (
    <div className={classnames(className, styles.box)}>
      <Loading />
    </div>
  );
});
