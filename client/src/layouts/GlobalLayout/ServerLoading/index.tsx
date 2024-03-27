import React from 'react';
import './index.less';

// TODO： 首屏以及懒加载Lodding效果
const ServerLoading = () => {
  return (
    <div className="loading_main">
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
  );
};

export default ServerLoading;
