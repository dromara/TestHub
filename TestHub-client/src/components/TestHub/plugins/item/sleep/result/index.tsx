import React from 'react';
import { Descriptions } from 'antd';
import { ExecuteResult, ExecutionResult, FlowResult } from '@/typings/server/execution';
import i18n from '@/i18n';
interface IProps {
  className?: string;
  size: number;
  flowResult: FlowResult;
  executionResult: ExecutionResult;
  executeResult: ExecuteResult;
}

function Sleep(props: IProps) {
  const { executeResult } = props;

  return (
    <>
      {executeResult.runStateItem != undefined && (
        <Descriptions column={1}>
          <Descriptions.Item label={<b>{i18n('sleep.text.sleepTime')}</b>}>
            {executeResult.runStateItem.runParams['sleepTime']} {'  '} {i18n('sleep.text.time')}
          </Descriptions.Item>
        </Descriptions>
      )}
    </>
  );
}

export default Sleep;
