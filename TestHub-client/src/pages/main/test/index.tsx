import React, { useEffect, useMemo, useRef, useState } from 'react';
import classnames from 'classnames';
import styles from './index.less';
import Draggable from '@/components/base/Draggable';
import { Panel, PanelGroup, PanelResizeHandle } from 'react-resizable-panels';

function App() {
  return (
    <>
      {/* <Draggable className={styles.container}>
        <div style={{ height: '100%' }}>左</div>
        <div style={{ height: '100%' }}>
          <Draggable className={styles.container} layout="column">
            <div style={{ height: '100%' }}>右上</div>
            <div style={{ height: '100%' }}>右下</div>
          </Draggable>
        </div>
      </Draggable> */}

      <Draggable className={styles.container} layout="column">
        <div style={{ height: '100%' }}>上</div>
        <div style={{ height: '100%' }}>
          <Draggable className={styles.container}>
            <div style={{ height: '100%' }}>左下</div>
            <div style={{ height: '100%' }}>右下</div>
          </Draggable>
        </div>
      </Draggable>

      {/* <PanelGroup direction="horizontal" className={styles.container}>
        <Panel>Left panel</Panel>
        <PanelResizeHandle className="mx-1 w-2 bg-slate-300" />
        <Panel>
          <PanelGroup direction="vertical" className={styles.container}>
            <Panel>Right top</Panel>
            <PanelResizeHandle className="mx-1 w-2 bg-slate-300" />
            <Panel>Right button</Panel>
          </PanelGroup>
        </Panel>
      </PanelGroup> */}
    </>
  );
}
export default App;
