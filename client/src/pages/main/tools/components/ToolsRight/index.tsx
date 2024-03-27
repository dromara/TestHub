import React, { useEffect, useState } from 'react';
// import { PageContainer } from '@ant-design/pro-layout';
import classnames from 'classnames';
import styles from './index.less';
interface IProps {
  toolCode: string | null;
  toolName: string | null;
  hidden: boolean;
  itemContainer?: React.ReactNode;
}

function ToolsRight(props: IProps) {
  // console.log(props.itemContainer);
  return (
    <>
      <div className={styles.toolsRight} hidden={props.hidden}>
        <div className={styles.title}>{props.toolName}</div>
        <div className={styles.body} style={{ paddingLeft: 10, paddingRight: 10 }}>
          {props.itemContainer}
        </div>
      </div>
    </>
  );
}

export default ToolsRight;
