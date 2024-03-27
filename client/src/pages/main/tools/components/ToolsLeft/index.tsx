import React, { useEffect, useState } from 'react';
import classnames from 'classnames';
import styles from './index.less';
import ReactList from 'react-list';
import Iconfont from '@/components/base/Iconfont';

interface IProps {
  toolCode: string | null;
  toolsTree: any;
  setToolCode: any;
}

function renderTools(category, tools, toolCode, setToolCode) {
  const renderItem = (index, key) => {
    return (
      <div
        className={classnames({ [styles.activate]: toolCode == tools[index].code }, styles.dcardsec)}
        key={key}
        onClick={() => {
          setToolCode(tools[index].code);
        }}
      >
        <div className={styles.icon}>
          <Iconfont size={16} code={tools[index].icon} />
        </div>
        <div className={styles.main}>{tools[index].name}</div>
      </div>
    );
  };

  return (
    <div key={category} style={{ paddingTop: 10, paddingBottom: 10 }}>
      <div className={styles.category}>{category}</div>
      <div>
        <ReactList itemRenderer={renderItem} length={tools.length} type="uniform" />
      </div>
    </div>
  );
}

function ToolsLeft(props: IProps) {
  return (
    <>
      {props.toolsTree.map((item) => {
        return renderTools(item.category, item.tools, props.toolCode, props.setToolCode);
      })}
    </>
  );
}

export default ToolsLeft;
