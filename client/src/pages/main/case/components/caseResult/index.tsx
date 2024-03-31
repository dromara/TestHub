import React from 'react';

import styles from './index.less';
import classnames from 'classnames';
import { ConsoleInfo } from '@/typings';
import CaseResultRight from './components/caseResultRight';
import { CasePageData, CasePageInfo } from '@/models/casePage';
import StateIndicator from '@/components/base/StateIndicator';

interface IProps {
  dispatch: any;
  isRunIng: boolean;
  consoleInfo: ConsoleInfo<CasePageData, CasePageInfo>;
}

function CaseResult(props: IProps) {
  const { consoleInfo } = props;
  const { executionResult, runXmlParam } = consoleInfo.page;
  return (
    <>
      {executionResult == undefined && <StateIndicator state="empty" />}
      {executionResult != undefined && runXmlParam != undefined && (
        <CaseResultRight consoleInfo={consoleInfo} executionResult={executionResult} runXmlParam={runXmlParam} />
      )}
    </>
  );
}

export default CaseResult;
