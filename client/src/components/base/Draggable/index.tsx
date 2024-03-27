import React, { useEffect, useRef, useState } from 'react';
import { ImperativePanelHandle, Panel, PanelGroup, PanelResizeHandle } from 'react-resizable-panels';
import classnames from 'classnames';
import styles from './index.less';

interface IProps {
  className?: string;
  firstShow?: boolean;
  secondShow?: boolean;
  children: any; //TODO: TS，约定接受一个数组
  defaultSize?: number;
  maxSize?: number;
  layout?: 'row' | 'column';
  showLine?: boolean;
  onResize?: (firstSize: number, secondSize: number) => void;
}

function Draggable(props: IProps) {
  const firstRef = useRef<ImperativePanelHandle>(null);
  const secondRef = useRef<ImperativePanelHandle>(null);
  const dividerRef = useRef<HTMLDivElement | null>(null);
  const {
    children,
    className,
    layout = 'row',
    firstShow = true,
    secondShow = true,
    defaultSize = 50,
    maxSize,
    onResize = (firstSize: number, secondSize: number) => {},
  } = props;

  const [dragging, setDragging] = useState(false);
  const [initFlag, setInitFlag] = useState(true);
  const [firstFlag, setFirstFlag] = useState(true);
  const [secondFlag, setSecondFlag] = useState(true);

  const moveHandle = () => {
    if (!initFlag) {
      setDragging(true);
      setInitFlag(false);
    }
    setTimeout(() => {
      setDragging(false);
    }, 500);
  };

  useEffect(() => {
    const panel = firstRef.current;
    if (panel) {
      if (firstFlag) {
        setFirstFlag(false);
        return;
      }
      if (firstShow) {
        panel.expand();
      } else {
        panel.collapse();
      }
    }
  }, [firstShow]);
  useEffect(() => {
    const panel = secondRef.current;
    if (panel) {
      if (secondFlag) {
        setSecondFlag(false);
        return;
      }
      if (secondShow) {
        panel.expand();
      } else {
        panel.collapse();
      }
    }
  }, [secondShow]);

  return (
    <PanelGroup
      direction={layout == 'row' ? 'horizontal' : 'vertical'}
      className={classnames(
        { [styles.draggableRow]: layout == 'row' },
        { [styles.draggableColumn]: layout != 'row' },
        className,
      )}
      onLayout={(data) => {
        moveHandle();
        onResize(data[0], data[1]);
      }}
    >
      <Panel collapsible ref={firstRef} defaultSize={defaultSize} maxSize={maxSize} order={0}>
        {children[0]}
      </Panel>
      <PanelResizeHandle className={styles.divider}>
        <div ref={dividerRef} className={classnames(styles.dividerCenter, { [styles.dividerDragging]: dragging })} />
      </PanelResizeHandle>

      <Panel collapsible ref={secondRef} order={1}>
        {children[1]}
      </Panel>
    </PanelGroup>
  );
}

export default Draggable;
