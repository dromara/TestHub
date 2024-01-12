import { IFormulaLog } from '@/typings';
import { ExecutionResult, FlowResult } from '@/typings/server/execution';
import { Col, Row } from 'antd';
import React from 'react';
import MyReactJson from '../myReactJson';
import i18n from '@/i18n';
import styles from './index.less';
import './index.less';

export type Props = {
    flow: FlowResult;
    data: IFormulaLog;
    result: ExecutionResult;
};

const FormulaLog = (props: Props) => {
    console.log(props.data)
    const formulaTypeConver = (type: string) => {
        if (type == 'data') {
            return (
                <>
                    <Col>
                        <Row>
                            <Col>
                                {i18n('testhub.FormulaLog.result') + ': ' + props.data.data}
                            </Col>
                        </Row>
                        {
                            props.data.exec == 2
                            && (
                                <Row>
                                    <Col>明细&nbsp;:&nbsp;</Col>
                                    <Col>
                                        {props.data.nodes?.map((item2) => (
                                            <FormulaLog flow={props.flow} data={item2} result={props.result} />
                                        ))}
                                    </Col>
                                </Row>
                            )
                        }
                        {
                            props.data.exec == 3
                            && (
                                props.data.mapLog?.map((key, item) => {
                                    return (
                                        <>
                                            <Row>
                                                <Col>&nbsp;&nbsp;{key}:</Col>
                                                <Col>
                                                    <FormulaLog flow={props.flow} data={item.log} result={props.result} />
                                                </Col>
                                            </Row>
                                        </>
                                    );
                                })
                            )
                        }

                    </Col>
                </>
            );
        } else if (type == 'mix') {
            return (
                <>
                    <Col>
                        <Row>
                            <Col>
                                {i18n('testhub.FormulaLog.result') + ': ' + props.data.data}
                            </Col>
                        </Row>
                        <Row>
                            <Col>明细&nbsp;:&nbsp;</Col>
                            <Col>
                                {props.data.nodes?.map((item2) => (
                                    <FormulaLog flow={props.flow} data={item2} result={props.result} />
                                ))}
                            </Col>
                        </Row>
                    </Col>
                </>
            );
        } else if (type == 'arith') {
            return (
                <>
                    <Col>{i18n('testhub.FormulaLog.result') + ': ' + props.data.data}</Col>
                </>
            );
        } else if (type == 'func') {
            return (
                <>
                    <Col>
                        <Row>
                            <Col>
                                <Row>
                                    <Col>{i18n('testhub.FormulaLog.func')}&nbsp;:&nbsp;{props.data.func}</Col>
                                    <Col>
                                        &nbsp;{i18n('testhub.FormulaLog.result')}:&nbsp;
                                        {(typeof props.data.data != 'object' || props.data.data == null) ? (
                                            (typeof props.data.data == 'boolean') ? props.data.data + "" : props.data.data
                                        ) : (
                                            <MyReactJson data={props.data.data} />
                                        )}
                                    </Col>
                                </Row>
                                {props.data.paramLogs.map((item, index) => {
                                    return (
                                        <>
                                            <Row>
                                                <Col>&nbsp;&nbsp;{props.data.func + '.' + item.code}:</Col>
                                                <Col>
                                                    <FormulaLog flow={props.flow} data={item.log} result={props.result} />
                                                </Col>
                                            </Row>
                                        </>
                                    );
                                })}
                            </Col>
                        </Row>
                    </Col>
                </>
            );
        } else if (type == 'path') {
            return (
                <Col>

                    <Row>
                        <Col>{i18n('testhub.FormulaLog.path')}:&nbsp;{props.data.path}</Col>
                        <Col>
                            &nbsp;{i18n('testhub.FormulaLog.result')}:&nbsp;
                            {(typeof props.data.data != 'object' || props.data.data == null) ? (
                                (typeof props.data.data == 'boolean') ? props.data.data + "" : props.data.data
                            ) : (
                                <MyReactJson data={props.data.data} />
                            )}
                        </Col>
                    </Row>
                    <Row>
                        <Col>明细&nbsp;:&nbsp;</Col>
                        <Col>
                            {props.data.itemLogs.map((item, index) => {
                                return (
                                    <>
                                        <Row>
                                            <Col>&nbsp;&nbsp;{item.attrName}</Col>
                                            {item.nodes && item.nodes.length > 0 && (
                                                <Col>
                                                    {item.nodes.map((item2, index1) => (

                                                        <React.Fragment key={index1}>
                                                            <Row>
                                                                <Col>
                                                                    <FormulaLog flow={props.flow} data={item2} result={props.result} />
                                                                </Col>
                                                            </Row>
                                                        </React.Fragment>
                                                    ))}
                                                </Col>
                                            )}
                                        </Row>
                                    </>
                                );
                            })}
                        </Col>
                    </Row>

                </Col>
            );
        }
    };

    return (
        <>
            <Row className={styles.row_border}>
                <Col>
                    <Row>
                        <Col>{i18n('testhub.FormulaLog.text')}:&nbsp;{props.data.text}</Col>
                    </Row>
                    <Row align="middle"  >{formulaTypeConver(props.data.type?.toLocaleLowerCase())}</Row>
                </Col>
            </Row>

        </>
    );
};

export default FormulaLog;
