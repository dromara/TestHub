import React, { useEffect, useState } from 'react';

import { Card, Descriptions, Tooltip } from 'antd';
import { ExecuteResult, ExecutionResult, FlowResult } from '@/typings/server/execution';
import MonacoEditor from 'react-monaco-editor/lib/editor';
import i18n from '@/i18n';
import MyMonacoEditor from '@/components/TestHub/MonacoEditor/MyMonacoEditor';
interface IProps {
  className?: string;
  size: number;
  flowResult: FlowResult;
  executionResult: ExecutionResult;
  executeResult: ExecuteResult;
}

function Sql(props: IProps) {
  const { executionResult, executeResult } = props;
  const data = executeResult.runStateItem.runParams;

  if (
    executeResult?.actionType.toUpperCase() == 'SQL' ||
    executeResult?.actionType.toUpperCase() == 'SQL_CALL' ||
    executeResult?.actionType.toUpperCase() == 'SQL_BATCH'
  ) {
    return (
      <>
        <Descriptions column={1}>
          <Descriptions.Item label={<b>{i18n('sql.text.url')}</b>}>{data['url']}</Descriptions.Item>
        </Descriptions>
        <Descriptions column={props.size > 400 ? 3 : 2}>
          <Descriptions.Item label={<b>{i18n('sql.text.Driver')}</b>}>{data['driver']}</Descriptions.Item>
          <Descriptions.Item label={<b>{i18n('sql.text.User')}</b>}>{data['username']}</Descriptions.Item>
          <Descriptions.Item label={<b>{i18n('sql.text.Password')}</b>}>{data['password']}</Descriptions.Item>
          <Descriptions.Item label={<b>{i18n('sql.text.timeout')}</b>}>
            <Tooltip title={i18n('sql.text.timeouttip')}>{data['timeout']}</Tooltip>
          </Descriptions.Item>

          <Descriptions.Item label={<b>{i18n('sql.text.query')}</b>}>
            {data['flag'] ? i18n('sql.text.yes') : i18n('sql.text.no')}
          </Descriptions.Item>
          <Descriptions.Item label={<b>{i18n('sql.text.conkey')}</b>}>{data['conKey']}</Descriptions.Item>
        </Descriptions>

        <div
          style={{
            height:
              data['sql'] == undefined
                ? 50
                : data['sql'].split('\n').length > 10
                ? data['sql'].split('\n').length * 10
                : 100,
            width: '100%',
          }}
        >
          <MyMonacoEditor language="sql" defaultValue={data['sql']} options={{ readOnly: true }} />
        </div>
      </>
    );
  } else if (
    executeResult?.actionType.toUpperCase() == 'SQL_BEGIN' ||
    executeResult?.actionType.toUpperCase() == 'SQL_COMMIT'
  ) {
    return (
      <>
        <Descriptions column={1}>
          <Descriptions.Item label={<b>{i18n('sql.text.url')}</b>}>{data['url']}</Descriptions.Item>
        </Descriptions>
        <Descriptions column={props.size > 400 ? 3 : 2}>
          <Descriptions.Item label={<b>{i18n('sql.text.Driver')}</b>}>{data['driver']}</Descriptions.Item>
          <Descriptions.Item label={<b>{i18n('sql.text.User')}</b>}>{data['username']}</Descriptions.Item>
          <Descriptions.Item label={<b>{i18n('sql.text.Password')}</b>}>{data['password']}</Descriptions.Item>
          <Descriptions.Item label={<b>{i18n('sql.text.timeout')}</b>}>
            <Tooltip title={i18n('sql.text.timeouttip')}>{data['timeout']}</Tooltip>
          </Descriptions.Item>

          <Descriptions.Item label={<b>{i18n('sql.text.query')}</b>}>
            {data['flag'] ? i18n('sql.text.yes') : i18n('sql.text.no')}
          </Descriptions.Item>
          <Descriptions.Item label={<b>{i18n('sql.text.conkey')}</b>}>{data['conKey']}</Descriptions.Item>
        </Descriptions>
      </>
    );
  }

  return <></>;
}

export default Sql;
