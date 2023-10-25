import React, { memo } from 'react';
import './index.less'

interface IProps {
  className?: any;
}

// TODO： 首屏以及懒加载Lodding效果
export default memo(function PageLoadinng(props: IProps) {
  return <div className="loading_main">
    <div className="loaders">
      <div className="loader">
        <div className="loader-inner ball-scale-multiple">
          <div></div>
          <div></div>
          <div></div>
        </div>
      </div>
    </div>
  </div>

});
