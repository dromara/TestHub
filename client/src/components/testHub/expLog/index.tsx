import { Button, Col, Row, Tag, Tooltip } from 'antd';
import React from 'react';
import styles from './index.less';
import './index.less';
import { ExecutionResult, FlowResult } from '@/typings/server/execution';
import FormulaLog from '../formulaLog';
import i18n from '@/i18n';
import DataTypeShow from '../dataTypeShow';

export type Props = {
  data: any;
  flow?: FlowResult;
  result?: ExecutionResult;
};

const ExpLog = (props: Props) => {
  return (
    <>
      <Row
        style={{ padding: 3 }}
        // style={{ padding: 3, display: "inline-block" }}
        className={props.data.flag ? 'log_success' : 'log_error'}
      >
        {props.data != undefined && (
          <Col className="left_border">
            <span className={styles.left_top_line} />
            <span className={styles.left_bottom_line} />
          </Col>
        )}
        <Col>
          <Row>
            <Col>
              {props.data.operationType.toLocaleLowerCase() == 'logic' && (
                <Row style={{ marginTop: 5 }} align="middle">
                  <Col style={{ marginRight: 10 }}>
                    {props.data.operationCode?.toLocaleLowerCase() == 'and' ? (
                      <Tag color="default">{i18n('testhub.ExpLog.and')}</Tag>
                    ) : (
                      <Tag color="default">{i18n('testhub.ExpLog.or')}</Tag>
                    )}
                  </Col>
                  <Col>
                    {props.data.subLog.map((item: any, index: any) => {
                      return (
                        <Row key={index} style={{ marginBottom: 5 }}>
                          <Col>
                            <ExpLog flow={props.flow} data={item} result={props.result}></ExpLog>
                          </Col>
                        </Row>
                      );
                    })}
                  </Col>
                </Row>
              )}
              {props.data != undefined && props.data.operationType?.toLocaleLowerCase() == 'relation' && (
                <Row align="middle">
                  <Col style={{ marginRight: 10 }}>
                    <Tooltip
                      title={<FormulaLog flow={props.flow} data={props.data.coverLog} result={props.result} />}
                      overlayStyle={{ maxWidth: 900 }}
                    >
                      <span>{props.data.cover}</span>
                    </Tooltip>
                  </Col>
                  <Col>
                    <DataTypeShow data={props.data.operationCode}></DataTypeShow>
                  </Col>
                  <Col style={{ marginLeft: 10 }}>
                    <Tooltip
                      overlayStyle={{ maxWidth: 900 }}
                      title={<FormulaLog flow={props.flow} data={props.data.thresholdLog} result={props.result} />}
                    >
                      <span>{props.data.threshold}</span>
                    </Tooltip>
                  </Col>
                  <Col> </Col>
                </Row>
              )}
            </Col>
          </Row>
        </Col>
        {props.data != undefined && (
          <Col className="right_border">
            <div>
              <span className={styles.right_top_line} />
              <span className={styles.right_bottom_line} />
            </div>
          </Col>
        )}
      </Row>
    </>
  );
};

export default ExpLog;
