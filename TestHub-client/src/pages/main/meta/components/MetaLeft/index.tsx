import React, { useEffect, useState } from 'react';
import classnames from 'classnames';
import styles from './index.less';
import ReactList from 'react-list';
import Iconfont from '@/components/base/Iconfont';

interface IProps {
  activeKey: string | undefined;
  metaMenus: any;
  setActiveCode: any;
}


function MetaLeft(props: IProps) {

  const { metaMenus, activeKey, setActiveCode } = props;

  const renderItem = (index, key) => {
    return (
      <div
        className={classnames({ [styles.activate]: activeKey == metaMenus[index].code }, styles.dcardsec)}
        key={key}
        onClick={() => {
          setActiveCode(metaMenus[index].code);
        }}
      >
        <div className={styles.icon}>
          <Iconfont size={16} code={metaMenus[index].icon} />
        </div>
        <div className={styles.main}>{metaMenus[index].name}</div>
      </div>
    );
  };

  return (
    <>
      <div className={styles.metaLeft}>
        <ReactList itemRenderer={renderItem} length={metaMenus.length} type="uniform" />
      </div>
    </>
  );
}

export default MetaLeft;
