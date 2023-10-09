import React, { forwardRef, useImperativeHandle, useRef } from 'react';

import i18n from '@/i18n';
import { ExecuteResult, ExecutionResult, FlowResult } from '@/typings/server/execution';
import Sleep from './item/sleep/entry';
import Sql from './item/sql/entry';
import CheckObj from './item/checkObj/entry';
import Check from './item/check/entry';
import Http from './item/http/entry';
import { RuleActionResDto } from '@/typings';
import Params from '../assembly/params';
interface IProps {
    data: RuleActionResDto;
}



export function getActionDataType(type: string) {
    if (type.toUpperCase() == 'SLEEP' ||
        type.toUpperCase() == 'CHECK_OBJ' ||
        type.toUpperCase() == 'CHECK' ||
        type.toUpperCase() == 'SQL_BEGIN' ||
        type.toUpperCase() == 'SQL_COMMIT') {
        return "MAP";
    } else {
        return undefined;
    }
}
export function getActionComplex(type: string) {
    if (type.toUpperCase() == 'SLEEP' ||
        type.toUpperCase() == 'CHECK_OBJ' ||
        type.toUpperCase() == 'CHECK' ||
        type.toUpperCase() == 'SQL_BEGIN' ||
        type.toUpperCase() == 'SQL_COMMIT') {
        return 1;
    } else {
        return -1;
    }
}
function noParams(type: string) {
    if (type.toUpperCase() == 'SLEEP' ||
        type.toUpperCase() == 'CHECK_OBJ' ||
        type.toUpperCase() == 'CHECK') {
        return true;
    } else {
        return false;
    }
}


const Entry = forwardRef((props: IProps, ref) => {
    const { data } = props;
    const itemRef = useRef();
    const paramsRef = useRef();
    useImperativeHandle(ref, () => ({
        getData: async () => {
            if (noParams(data.type)) {
                data.params = [];
            } else if (paramsRef.current != undefined) {
                const res = await paramsRef.current.getData();
                data.params = res.data;
            }

            if (itemRef.current != undefined) {
                const res = await itemRef.current.getData();
                data.extraInto = res.data;
                return { flag: res.flag, data: data };
            } else {
                return { flag: true, data: data };
            }
        },
    }));



    if (data.type.toUpperCase() == 'SLEEP') {
        return <Sleep data={data} ref={itemRef} />
    } else if (
        data.type.toUpperCase() == 'SQL_BEGIN' ||
        data.type.toUpperCase() == 'SQL_COMMIT') {
        return <>
            <Params params={data?.params ? data.params : []} ref={paramsRef} />
        </>
    } else if (data.type.toUpperCase() == 'SQL' ||
        data.type.toUpperCase() == 'SQL_CALL') {
        return <>
            <Params params={data?.params ? data.params : []} ref={paramsRef} />
            <Sql data={data} ref={itemRef} />
        </>
    } else if (data.type.toUpperCase() == 'CHECK_OBJ') {
        return <CheckObj data={data} ref={itemRef} />
    } else if (data.type.toUpperCase() == 'CHECK') {
        return <Check data={data} ref={itemRef} />
    } else if (data.type.toUpperCase() == 'HTTP') {
        return <>
            <Params params={data?.params ? data.params : []} ref={paramsRef} />
            <Http data={data} ref={itemRef} />
        </>
    }
    return <>{"请处理" + data.type.toUpperCase()}</>;
});

export default Entry;