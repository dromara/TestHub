import React, { useEffect, useRef, useState } from 'react';
import {
  ImperativePanelGroupHandle,
  ImperativePanelHandle,
  Panel,
  PanelGroup,
  PanelResizeHandle,
} from 'react-resizable-panels';
import classnames from 'classnames';
import styles from './index.less';

export enum ModelType {
  FIRST = 'FIRST',
  SECOND = 'SECOND',
  // All = 'ALL',
}
interface IProps {
  className?: string;
  model?: ModelType;
  show?: boolean;

  children: any; //TODO: TS，约定接受一个数组
  defaultSize?: number;
  maxSize?: number;
  minSize?: number;
  layout?: 'row' | 'column';
  showLine?: boolean;
  onResize?: (firstSize: number, secondSize: number) => void;
}

function Draggable(props: IProps) {
  const ref = useRef<ImperativePanelGroupHandle>(null);
  const {
    children,
    className,
    layout = 'row',
    model = ModelType.FIRST,
    show = true,
    defaultSize = 50,
    maxSize = 100,
    minSize = 50,
    onResize = (firstSize: number, secondSize: number) => {},
  } = props;

  const [dragging, setDragging] = useState(false);
  const [initFlag, setInitFlag] = useState(true);

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
    const panelGroup = ref.current;
    if (panelGroup) {
      if (show) {
        if (model == ModelType.FIRST) {
          // panelGroup.setLayout([99, 1]);
          panelGroup.setLayout([100 - defaultSize, defaultSize]);
        } else {
          // panelGroup.setLayout([1, 99]);
          panelGroup.setLayout([defaultSize, 100 - defaultSize]);
        }
      } else {
        if (model == ModelType.FIRST) {
          panelGroup.setLayout([0, 100]);
        } else {
          panelGroup.setLayout([100, 0]);
        }
      }
    }
  }, [show]);

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
      ref={ref}
    >
      <Panel
        collapsible={model == ModelType.FIRST ? true : false}
        defaultSize={model == ModelType.SECOND ? defaultSize : 100 - defaultSize}
        minSize={model == ModelType.SECOND ? minSize : undefined}
        maxSize={model == ModelType.SECOND ? maxSize : undefined}
        order={0}
      >
        {children[0]}
      </Panel>
      <PanelResizeHandle className={styles.divider}>
        <div className={classnames(styles.dividerCenter, { [styles.dividerDragging]: dragging })} />
      </PanelResizeHandle>

      <Panel
        collapsible={model == ModelType.SECOND ? true : false}
        defaultSize={model == ModelType.FIRST ? defaultSize : 100 - defaultSize}
        minSize={model == ModelType.FIRST ? minSize : undefined}
        maxSize={model == ModelType.FIRST ? maxSize : undefined}
        order={1}
      >
        {children[1]}
      </Panel>
    </PanelGroup>
  );
}

export default Draggable;
