import React, { useEffect, useMemo, useRef, useState } from 'react';
import classnames from 'classnames';
import styles from './index.less';
import Draggable from '@/components/base/Draggable';

function App() {
  return (
    <Draggable className={styles.httpConsoleContainer} layout="column">
      <div className={styles.httpConsoleInfo}>xinxi</div>
      <div className={styles.httpConsoleResult}>执行结果</div>
    </Draggable>
  );
}
export default App;
