import React, { useEffect, useState } from 'react';

import { Descriptions } from 'antd';
import { ExecuteResult, ExecutionResult, FlowResult } from '@/typings/server/execution';
import i18n from '@/i18n';
import MyMonacoEditor from '@/components/TestHub/MonacoEditor/MyMonacoEditor';
interface IProps {
  className?: string;
  size: number;
  flowResult: FlowResult;
  executionResult: ExecutionResult;
  executeResult: ExecuteResult;
}

function App(props: IProps) {
  const { executeResult } = props;
  const data = executeResult.runStateItem.runParams;

  return (
    <>
      <Descriptions column={props.size > 500 ? 5 : 3}>
        <Descriptions.Item label={<b>{i18n('app.text.ip')}</b>}>{data['appIp']}</Descriptions.Item>
        <Descriptions.Item label={<b>{i18n('app.text.appPort')}</b>}>{data['appPort']}</Descriptions.Item>
        <Descriptions.Item label={<b>{i18n('app.text.appTimeout')}</b>}>{data['appTimeout']}</Descriptions.Item>
        <Descriptions.Item label={<b>{i18n('app.text.errorCode')}</b>}>{data['planErrorCode']}</Descriptions.Item>
        <Descriptions.Item label={<b>{i18n('app.text.response')}</b>}>{data['errorCode']}</Descriptions.Item>
      </Descriptions>

      <Descriptions column={1}>
        <Descriptions.Item label={<b>{i18n('app.text.request')}</b>}>
          {/* <Alert
                        message={ */}

          <MyMonacoEditor width={props.size - 100} height="150" language="datapack" defaultValue={data['request']} />
          {/* }
                         type={"info"}
                         showIcon={false}
                     banner
                 /> */}
        </Descriptions.Item>
        <Descriptions.Item label={<b>{i18n('app.text.response')}</b>}>
          {/* <Alert
                        message={ */}
          <MonacoEditor
            width={props.size - 100}
            height="150"
            language="datapack"
            value={data['response']}
            options={{
              readOnly: true,
              selectOnLineNumbers: true,
              minimap: {
                // 关闭代码缩略图
                enabled: false, // 是否启用预览图
              },
              scrollBeyondLastLine: false, // 设置编辑器是否可以滚动到最后一行之后
              scrollbar: {
                verticalScrollbarSize: 6,
                horizontalScrollbarSize: 6,
                verticalSliderSize: 6,
                horizontalSliderSize: 6,
                verticalHasArrows: false,
                horizontalHasArrows: false,
                arrowSize: 0,
                useShadows: true,
              },
            }}
          />
          {/* }
                        type={data['errorFlag'] ? 'error' : 'success'}
                        showIcon={false}
                        banner
                    /> */}
        </Descriptions.Item>
      </Descriptions>
    </>
  );
}

export default App;
