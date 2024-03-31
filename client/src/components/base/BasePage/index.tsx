import React, { useEffect, useMemo, useRef, useState } from 'react';
import classnames from 'classnames';
import styles from './index.less';
import Draggable, { ModelType } from '@/components/base/Draggable';
import Iconfont from '@/components/base/Iconfont';

interface IProps {
  className?: string;
  leftShow?: boolean;
  resultShow?: boolean;
  resultHide?: boolean;
  changeLeftShow?: (flag: boolean) => void;
  changeResultShow?: (flag: boolean) => void;
  left: any;
  rigth: any;
}

export default function BasePage(props: IProps) {
  const {
    left,
    rigth,
    className,
    resultShow = true,
    resultHide = false,
    leftShow = true,
    changeLeftShow = () => {},
    changeResultShow = () => {},
  } = props;
  const [isUnfold, setIsUnfold] = useState(leftShow);
  useEffect(() => {
    setIsUnfold(leftShow);
  }, [leftShow]);

  return (
    <Draggable
      className={classnames(styles.basePage, className)}
      model={ModelType.FIRST}
      show={isUnfold}
      defaultSize={85}
      maxSize={100}
      minSize={80}
      onResize={(firstSize: number, secondSize: number) => {
        if (firstSize == 0 || firstSize < 0) {
          if (isUnfold) {
            changeLeftShow(false);
          }
        } else {
          if (!isUnfold) {
            changeLeftShow(true);
          }
        }
      }}
    >
      <div className={styles.left}>{left}</div>
      <div className={styles.right}>
        <div className={styles.rightBody}>{rigth}</div>
        <div className={styles.footer}>
          <div
            className={classnames({ [styles.reversalIconBox]: isUnfold }, styles.iconBox)}
            onClick={() => {
              changeLeftShow(!isUnfold);
            }}
          >
            <Iconfont code="&#xe6b5;" />
          </div>
          <div
            hidden={resultHide}
            onClick={() => {
              changeResultShow(!resultShow);
            }}
            className={classnames(styles.result, {
              [styles.unfoldResult]: resultShow,
            })}
          >
            执行结果
            <Iconfont code="&#xe6b5;" />
          </div>
        </div>
      </div>
    </Draggable>
  );
}
