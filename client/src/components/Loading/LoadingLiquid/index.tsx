import React, { memo } from 'react';
import styles from './index.less';
import classnames from 'classnames';
import './index.less'

interface IProps {
  className?: any;
}

// TODO： 首屏以及懒加载Lodding效果
export default memo(function PageLoadinng(props: IProps) {

  return <div className="loadinng">
    <div className="loader">
      <span className="text">TestHub</span>
      <span className="spinner"></span>
    </div>
  </div>

});
