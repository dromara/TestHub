import React from 'react';
import { Typography } from 'antd';
import styles from './index.less';
import { Exception } from '@/typings';
export type IProps = {
  data: Exception;
};

function ErrorBoundary(props: IProps) {
  return (
    <>
      <div className={styles.contentStyle}>
        <Typography.Text copyable>
          <span className={styles.message}>{props.data.message + ''}</span>
          {props.data?.stackTrace?.map((item, index) => {
            return (
              <>
                <br />
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                {item.className + '.' + item.methodName + ':' + item.lineNumber}
              </>
            );
          })}
        </Typography.Text>
      </div>
      {props.data.cause != undefined && props.data.cause != null && <ErrorBoundary data={props.data.cause} />}
    </>
  );
}

export default ErrorBoundary;
