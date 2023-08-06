import React, { useCallback, useContext, useEffect, useMemo, useRef, useState } from 'react';

import { Badge, Cascader, Divider, Dropdown, Modal, message } from 'antd';
import i18n from '@/i18n';
import styles from './index.less';
import classnames from 'classnames';
import { IAppPageState } from '@/models/appPage';
import { IConsoleIndo, TreeNodeResDto } from '@/typings';
import StateIndicator from '@/components/StateIndicator';
import CaseResultRight from './components/caseResultRight';


interface IProps {
    appPage: IAppPageState;
    dispatch: any;
    isRunIng: boolean;
    iconsole: IConsoleIndo<TreeNodeResDto>;
}

function CaseResult(props: IProps) {
    const { appPage, dispatch, iconsole } = props;
    const { executionResult, runXmlParam } = iconsole;
    return <>
        {
            props.iconsole.executionResult == undefined &&
            <StateIndicator state={props.isRunIng ? "loading" : "empty"}></StateIndicator>
        }
        {
            props.iconsole.executionResult != undefined &&
            <CaseResultRight dispatch={dispatch} appPage={appPage} iconsole={iconsole} executionResult={executionResult} runXmlParam={runXmlParam} />
        }
    </>
};



export default CaseResult;
