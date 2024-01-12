import React from 'react';

import { Alert, Collapse, Tag } from 'antd';
import { ExecuteResult, ExecutionResult, FlowResult } from '@/typings/server/execution';
import { CheckCircleOutlined, CloseCircleOutlined } from '@ant-design/icons';
import ExpLog from '@/components/testHub/expLog';
import i18n from '@/i18n';
const { Panel } = Collapse;
interface IProps {
    size: number,
    className?: string;
    flowResult: FlowResult;
    executionResult: ExecutionResult;
    executeResult: ExecuteResult;
}


function Check(props: IProps) {
    const { executionResult, executeResult } = props;
    const data = executeResult.runStateItem.runParams;
    const checks: React.JSX.Element[] = [];
    Object.keys(data).map((key: string) => {
        const checkItem = data[key]["checkItem"];
        const flag = data[key]["flag"];
        const results = data[key]["results"];

        checks.push(

            <Panel
                header={checkItem.code + ' - ' + checkItem.name}
                key={checkItem.code}
                extra={
                    flag ? (
                        <Tag icon={<CheckCircleOutlined />} color="green">
                            {i18n('check.text.pass')}
                        </Tag>
                    ) : (
                        <Tag icon={<CloseCircleOutlined />} color="red">
                            {i18n('check.text.fail')}
                        </Tag>
                    )
                }
            >
                {
                    !flag && <Alert
                        message={checkItem.msg}
                        type="error"
                        banner
                        style={{
                            margin: -12,
                            marginBottom: 24,
                        }}
                    />
                }

                {
                    results.map((result: any, index: number) => {
                        return <>
                            <ExpLog
                                key={checkItem.code + "" + index}
                                data={result.log}
                                result={executionResult}
                                flow={props.flowResult}
                            />
                            <div
                                style={{
                                    marginBottom: 12,
                                }}
                            />
                        </>
                    })
                }
            </Panel>

        );
    })
    return <Collapse >{checks}</Collapse >


};

export default Check;