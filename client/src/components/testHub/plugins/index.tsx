import React, { } from 'react';

import i18n from '@/i18n';
import { ExecuteResult, ExecutionResult, FlowResult } from '@/typings/server/execution';
import Sleep from './sleep';
import Sql from './sql';
import CheckObj from './checkObj';
import Check from './check';
import Http from './http';
interface IProps {
    className?: string;
    size: number,
    flowResult: FlowResult;
    executionResult: ExecutionResult;
    executeResult: ExecuteResult;
}



function Plugins(props: IProps) {
    const { executeResult } = props;
    if (executeResult.actionType.toUpperCase() == 'SLEEP') {
        return <Sleep size={props.size} flowResult={props.flowResult} executeResult={props.executeResult} executionResult={props.executionResult} />
    } else if (executeResult.actionType.toUpperCase() == 'SQL' ||
        executeResult.actionType.toUpperCase() == 'SQL_CALL' ||
        executeResult.actionType.toUpperCase() == 'SQL_BEGIN' ||
        executeResult.actionType.toUpperCase() == 'SQL_COMMIT') {
        return <Sql size={props.size} flowResult={props.flowResult} executeResult={props.executeResult} executionResult={props.executionResult} />
    } else if (executeResult.actionType.toUpperCase() == 'CHECK_OBJ') {
        return <CheckObj size={props.size} flowResult={props.flowResult} executeResult={props.executeResult} executionResult={props.executionResult} />
    } else if (executeResult.actionType.toUpperCase() == 'CHECK') {
        return <Check size={props.size} flowResult={props.flowResult} executeResult={props.executeResult} executionResult={props.executionResult} />
    } else if (executeResult.actionType.toUpperCase() == 'HTTP') {
        return <Http size={props.size} flowResult={props.flowResult} executeResult={props.executeResult} executionResult={props.executionResult} />
    }
    return <>{"请处理" + executeResult.actionType.toUpperCase()}</>;
};

export default Plugins;