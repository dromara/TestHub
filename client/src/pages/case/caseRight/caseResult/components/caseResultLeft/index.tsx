import React, { useCallback, useContext, useEffect, useMemo, useRef, useState } from 'react';

import { Badge, Cascader, Descriptions, Divider, Dropdown, Modal, Tag, message } from 'antd';
import i18n from '@/i18n';
import styles from './index.less';
import classnames from 'classnames';
import { IAppPageState } from '@/models/appPage';
import { ExecutionXmlReqDto, IConsoleIndo, TreeNodeResDto } from '@/typings';
import { ExecutionResult } from '@/typings/server/execution';
import { CheckCircleOutlined, CloseCircleOutlined } from '@ant-design/icons';
import MyReactJson from '@/components/testHub/myReactJson';

interface IProps {
    className?: string;
    appPage: IAppPageState;
    dispatch: any;
    iconsole: IConsoleIndo<TreeNodeResDto>;
    executionResult: ExecutionResult;
    runXmlParam?: ExecutionXmlReqDto;
}

const initExecFlag = (entity: ExecutionResult) => {
    if (entity.execFlag) {
        return (
            <Tag icon={<CheckCircleOutlined />} color="green">
                通过
            </Tag>
        );
    } else {
        return (
            <Tag icon={<CloseCircleOutlined />} color="red">
                失败
            </Tag>
        );
    }
};

const getFlowName = (executionResult: ExecutionResult, runXmlParam?: ExecutionXmlReqDto) => {
    const reDatas: React.JSX.Element[] = [];
    runXmlParam?.flows?.map((key, index) => {
        const flow = executionResult.ruleFlow?.flows.find(item => item.code == key);
        reDatas.push(<Tag key={flow?.code} color="#108ee9">{flow?.name || flow?.code}</Tag>)
    })
    return reDatas;
};
function CaseResultLeft(props: IProps) {
    const { appPage, dispatch, iconsole, executionResult, runXmlParam } = props;

    return <div className={classnames(styles.box)}>
        <Descriptions style={{ marginBottom: 32 }} size="small" column={1}>
            <Descriptions.Item label={<b>执行编码</b>}>{executionResult.id}</Descriptions.Item>
            <Descriptions.Item label={<b>执行标识</b>}>{initExecFlag(executionResult)}</Descriptions.Item>
            <Descriptions.Item label={<b>用例</b>}>
                {executionResult.ruleCode + ' - ' + executionResult.ruleFlow?.name}
            </Descriptions.Item>
            <Descriptions.Item label={<b>用例模式</b>}>{executionResult.ruleFlow?.model}</Descriptions.Item>
            <Descriptions.Item label={<b>执行步骤</b>}> {getFlowName(executionResult, runXmlParam)}
            </Descriptions.Item>
        </Descriptions>
        <RunInfo executionResult={executionResult} runXmlParam={runXmlParam} />
    </div>
};

interface IRunInfoProps {
    executionResult: ExecutionResult;
    runXmlParam: ExecutionXmlReqDto;
}
const RunInfo = (props: IRunInfoProps) => {
    return <div>
        <MyReactJson data={props.executionResult.ruleParams} />
    </div>
};


export default CaseResultLeft;