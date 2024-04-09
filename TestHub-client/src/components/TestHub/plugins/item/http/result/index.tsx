import React, { } from 'react';

import { ExecuteResult, ExecutionResult, FlowResult } from '@/typings/server/execution';
import HttpRes from './HttpRes';
interface IProps {
    className?: string;
    size: number,
    flowResult: FlowResult;
    executionResult: ExecutionResult;
    executeResult: ExecuteResult;
}



function Http(props: IProps) {
    const { executionResult, executeResult } = props;

    // return <>{JSON.stringify(executeResult.runStateItem)}</>

    return <>
        {
            executeResult.runStateItem != undefined &&
            <>
                {/* <div>{JSON.stringify(executeResult.runStateItem.result)}</div> */}
                <HttpRes res={executeResult.runStateItem.result.content} data={executeResult.runStateItem.runParams}></HttpRes>
            </>
        }
    </>
};

export default Http;