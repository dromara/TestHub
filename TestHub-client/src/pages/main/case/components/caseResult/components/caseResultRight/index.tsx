import React, { useState } from 'react';
import ReactResizeDetector from 'react-resize-detector';
import { Badge, Card, Collapse, Descriptions, Tabs, Tag, theme } from 'antd';
import styles from './index.less';
import classnames from 'classnames';
import { ConsoleInfo, ExecutionXmlReqDto } from '@/typings';
import { ExecutionResult, FlowResult } from '@/typings/server/execution';
import { CardTabListType } from 'antd/lib/card';
import Plugins from '@/components/TestHub/plugins/Result';
import MyReactJson from '@/components/TestHub/myReactJson';
import ErrorBoundary from '@/components/TestHub/errorBoundary';
import i18n from '@/i18n';
import { CasePageData, CasePageInfo } from '@/models/casePage';
import StickyBox from 'react-sticky-box';
import { TabsProps } from 'antd/lib';

const { Panel } = Collapse;

interface IProps {
  className?: string;
  consoleInfo: ConsoleInfo<CasePageData, CasePageInfo>;
  executionResult: ExecutionResult;
  runXmlParam: ExecutionXmlReqDto;
}

function caseResultRight(props: IProps) {
  const { executionResult, runXmlParam } = props;

  const {
    token: { colorBgContainer },
  } = theme.useToken();

  const items = new Array(executionResult.ruleFlow?.flows.length).fill(null).map((_, index) => {
    return {
      label: executionResult.ruleFlow?.flows[index].name
        ? executionResult.ruleFlow?.flows[index].name
        : executionResult.ruleFlow?.flows[index].code,
      key: index + '',
      children: (
        <Flow
          flowResult={executionResult.flowResults[index]}
          executionResult={executionResult}
          runXmlParam={runXmlParam}
        />
      ),
    };
  });
  const renderTabBar: TabsProps['renderTabBar'] = (props, DefaultTabBar) => (
    <StickyBox offsetTop={0} offsetBottom={0} style={{ zIndex: 1 }}>
      <DefaultTabBar {...props} style={{ background: colorBgContainer }} />
    </StickyBox>
  );
  return (
    <div style={{ paddingLeft: 16, paddingRight: 16, paddingBottom: 16 }}>
      <Tabs defaultActiveKey={'0'} renderTabBar={renderTabBar} items={items} />
    </div>
  );
}
interface IRunInfoProps {
  executionResult: ExecutionResult;
  flowResult: FlowResult;
  runXmlParam?: ExecutionXmlReqDto;
}

const Flow = (props: IRunInfoProps) => {
  const [width, setWidth] = useState(0);
  const componentRef = React.createRef();
  return (
    <>
      <Collapse defaultActiveKey={[]}>
        {props.flowResult.executeResults.map((item, index) => {
          return (
            <Panel
              header={item.executeCode + (item.executeName == null ? '' : ' - ' + item.executeName)}
              key={index + ''}
              ref={componentRef}
              className={
                item.runStateItem.result.flag || item.runStateItem.result.bizError ? styles.error : styles.success
              }
              extra={
                item.actionType +
                '   ' +
                i18n('case.text.return') +
                ':' +
                item.dataType +
                (item.complex > 0 ? '  ' + i18n('case.text.complex') + ':' + item.complex : '')
              }
            >
              <ReactResizeDetector
                handleWidth
                onResize={(width, height) => {
                  setWidth(width || 0);
                }}
              />
              <Descriptions column={width > 300 ? (width / 100 > 7 ? 4 : width / 100 - 2) : 2} size="small">
                <Descriptions.Item label={<b>{i18n('case.text.actionCode')}</b>}>{item.actionCode}</Descriptions.Item>
                <Descriptions.Item label={<b>{i18n('case.text.actionName')}</b>}>{item.actionName}</Descriptions.Item>
                {/* <Descriptions.Item label={<b>行为类型</b>}>{item.actionType}</Descriptions.Item> */}
                <Descriptions.Item label={<b>{i18n('case.text.acceptReturn')}</b>}>
                  {item.executeInit ? (
                    <Tag color="processing">{i18n('case.text.acceptReturnYes')}</Tag>
                  ) : (
                    <Tag color="default">{i18n('case.text.acceptReturnNo')}</Tag>
                  )}
                </Descriptions.Item>
                <Descriptions.Item label={<b>{i18n('case.text.errorBlock')}</b>}>
                  {item.executeBlock ? (
                    <Tag color="processing">{i18n('case.text.yes')}</Tag>
                  ) : (
                    <Tag color="default">{i18n('case.text.no')}</Tag>
                  )}
                </Descriptions.Item>
              </Descriptions>
              <Collapse defaultActiveKey={['2']}>
                <Panel header={i18n('case.text.systemParam')} key="0">
                  <Descriptions column={width > 500 ? 3 : 1} size="small">
                    <Descriptions.Item label={<b>{i18n('case.text.start')}</b>}>
                      {item.runStateItem?.systemParams['startTime']}
                    </Descriptions.Item>
                    <Descriptions.Item label={<b>{i18n('case.text.end')}</b>}>
                      {item.runStateItem?.systemParams['endTime']}
                    </Descriptions.Item>
                    <Descriptions.Item label={<b>{i18n('case.text.cost')}</b>}>
                      {item.runStateItem?.systemParams['costUnit'] == 'SECONDS'
                        ? item.runStateItem?.systemParams['cost'] + ' ' + i18n('case.text.second')
                        : item.runStateItem?.systemParams['cost'] + ' ' + i18n('case.text.millisecond')}
                    </Descriptions.Item>
                  </Descriptions>
                </Panel>
                {item.runStateItem.params != undefined && Object.keys(item.runStateItem.params).length > 0 && (
                  <Panel header={i18n('case.text.argument')} key="1">
                    <MyReactJson data={item.runStateItem?.params} />
                  </Panel>
                )}
                {item.runStateItem.runParams != undefined && Object.keys(item.runStateItem.runParams).length > 0 && (
                  <Panel header={i18n('case.text.processlog')} key="2">
                    <Plugins
                      size={width}
                      flowResult={props.flowResult}
                      executeResult={item}
                      executionResult={props.executionResult}
                    />
                  </Panel>
                )}
                {item.runStateItem.result.content != undefined &&
                  Object.keys(item.runStateItem.result.content).length > 0 && (
                    <Panel header={i18n('case.text.returnData')} key="3">
                      <MyReactJson data={item.runStateItem?.result?.content} />
                    </Panel>
                  )}
                {item.runStateItem.result.flag && (
                  <Panel header={i18n('case.text.errorBoundary')} key="4">
                    {i18n('case.text.errorBoundary')}
                    <ErrorBoundary data={item.runStateItem.result.exception}></ErrorBoundary>
                  </Panel>
                )}
              </Collapse>
            </Panel>
          );
        })}
      </Collapse>
    </>
  );
};

export default caseResultRight;
