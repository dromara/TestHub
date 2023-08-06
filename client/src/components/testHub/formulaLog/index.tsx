import { IFormulaLog } from '@/typings';
import { ExecutionResult, FlowResult } from '@/typings/server/execution';
import { Col, Row } from 'antd';
import React from 'react';
import MyReactJson from '../myReactJson';
import i18n from '@/i18n';

export type Props = {
    flow: FlowResult;
    data: IFormulaLog;
    result: ExecutionResult;
};

const FormulaLog = (props: Props) => {
    const formulaTypeConver = (type: string) => {
        if (type == 'data') {
            return (
                <>
                    <Col>{i18n('testhub.FormulaLog.val') + ': ' + props.data.data}</Col>
                </>
            );
        }
        if (type == 'func') {
            return (
                <>
                    <Col>
                        <Row>
                            <Col>
                                <Row>
                                    <Col>{i18n('testhub.FormulaLog.func')}&nbsp;:&nbsp;{props.data.func}</Col>
                                    <Col>&nbsp;&nbsp;{i18n('testhub.FormulaLog.result')}&nbsp;:&nbsp;{props.data.data}</Col>
                                </Row>
                            </Col>
                            <Col>&nbsp;&nbsp;</Col>
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
                </>
            );
        }
        if (type == 'path') {
            let show = props.data.val;
            // if (props.result?.propertyMap[props.data.val] != null) {
            //     show = props.result?.propertyMap[props.data.val]['name'];
            // } else if (props.result?.propertyMap[props.flow.code + '.' + props.data.val] != null) {
            //     show = props.result?.propertyMap[props.flow.code + '.' + props.data.val]['name'];
            // }
            return (
                <Col>
                    {(props.data.indexLog == undefined || props.data.indexLog == null) && (
                        <Row>
                            <Col>
                                <Row>
                                    <Col>{i18n('testhub.FormulaLog.variable')}:&nbsp;{show}</Col>
                                    <Col>
                                        &nbsp;{i18n('testhub.FormulaLog.val')}:&nbsp;
                                        {(typeof props.data.data != 'object' || props.data.data == null) ? (
                                            (typeof props.data.data == 'boolean') ? props.data.data + "" : props.data.data
                                        ) : (
                                            <MyReactJson data={props.data.data} />
                                        )}
                                    </Col>
                                </Row>
                            </Col>
                            <Col>&nbsp;&nbsp;</Col>
                        </Row>
                    )}
                    {props.data.indexLog != undefined && props.data.indexLog != null && (
                        <>
                            <Row>
                                <Col>
                                    <Row>
                                        <Col>
                                            {i18n('testhub.FormulaLog.variable')}:&nbsp;{show} [{props.data.index}]{props.data.suffix}
                                        </Col>
                                        <Col>
                                            &nbsp;{i18n('testhub.FormulaLog.val')}:&nbsp;
                                            {(typeof props.data.data != 'object' || props.data.data == null) ? (
                                                (typeof props.data.data == 'boolean') ? props.data.data + "" : props.data.data
                                            ) : (
                                                <MyReactJson data={props.data.data} />
                                            )}
                                        </Col>
                                    </Row>
                                </Col>
                            </Row>
                            <Row>
                                <Col>{i18n('testhub.FormulaLog.index')}&nbsp;</Col>
                                <Col>
                                    <FormulaLog flow={props.flow} data={props.data.indexLog} result={props.result} />
                                </Col>
                            </Row>
                        </>
                    )}
                </Col>
            );
        }
    };

    return (
        <>
            <Row align="middle">{formulaTypeConver(props.data.type?.toLocaleLowerCase())}</Row>
        </>
    );
};

export default FormulaLog;
