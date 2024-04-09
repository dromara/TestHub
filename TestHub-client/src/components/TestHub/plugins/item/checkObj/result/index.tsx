import React from 'react';
import { Alert, Collapse, Descriptions, Row, Tag } from 'antd';
import { ExecuteResult, ExecutionResult, FlowResult } from '@/typings/server/execution';
import { CheckCircleOutlined, CloseCircleOutlined } from '@ant-design/icons';
import i18n from '@/i18n';
import MyMonacoDiffEditor from '@/components/TestHub/MonacoEditor/MyMonacoDiffEditor';

const { Panel } = Collapse;
interface IProps {
  className?: string;
  size: number;
  flowResult: FlowResult;
  executionResult: ExecutionResult;
  executeResult: ExecuteResult;
}

function CheckObj(props: IProps) {
  const { executeResult } = props;
  const data = executeResult.runStateItem.runParams;

  return (
    <Collapse>
      {Object.keys(data).map((key: string) => {
        const checkObj = data[key]['checkObj'];
        return (
          <Panel
            header={checkObj?.code + ' - ' + checkObj?.name}
            key={checkObj?.code}
            extra={
              data[key].flag ? (
                <Tag icon={<CheckCircleOutlined />} color="green">
                  {i18n('checkObj.text.pass')}
                </Tag>
              ) : (
                <Tag icon={<CloseCircleOutlined />} color="red">
                  {i18n('checkObj.text.fail')}
                </Tag>
              )
            }
          >
            {!data[key].flag && (
              <Alert
                message={data[key].msg}
                type="error"
                banner
                style={{
                  margin: -12,
                  marginBottom: 24,
                }}
              />
            )}
            <Row>
              <Descriptions column={2}>
                <Descriptions.Item label={<b>{i18n('checkObj.text.cover')}</b>}>{checkObj?.cover}</Descriptions.Item>
                <Descriptions.Item label={<b>{i18n('checkObj.text.threshold')}</b>}>
                  {checkObj?.threshold}
                </Descriptions.Item>
              </Descriptions>
            </Row>
            <MyMonacoDiffEditor
              width="100%"
              height="400"
              cover={data[key].cover}
              threshold={data[key].threshold}
              language="json"
            />
          </Panel>
        );
      })}
    </Collapse>
  );
}

export default CheckObj;
