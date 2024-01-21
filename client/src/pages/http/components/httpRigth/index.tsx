
import React, { useEffect, useState } from 'react';
import { IAppPageState } from '@/models/appPage';
import { BaseConsoleInfo, HttpConsoleInfo, TreeNodeResDto } from '@/typings';
import { IHttpPageState } from 'umi';
import ConsoleBox from '@/components/ConsoleBox';
import HttpConsole from './httpConsole';


interface IProps {
    appPage: IAppPageState;
    httpPage: IHttpPageState;
    dispatch: Function;
}

function HttpRigth(props: IProps) {
    const { appPage, httpPage, dispatch } = props;
    const [activeKey, setActiveKey] = useState<string>();
    const [consoles, setConsoles] = useState<BaseConsoleInfo<TreeNodeResDto, HttpConsoleInfo>[]>(httpPage.consoles);



    useEffect(() => {
        setConsoles(httpPage.consoles);
    }, [httpPage.consoles]);

    useEffect(() => {
        setActiveKey(httpPage.activeKey);
    }, [httpPage.activeKey]);

    const onChangeTab = (newActiveKey: string) => {
        dispatch({
            type: 'httpPage/setActiveKey',
            payload: newActiveKey,
        })
    };
    const delConsole = (targetKey: string) => {
        dispatch({
            type: 'httpPage/delConsole',
            payload: targetKey,
        })
    };
    const save = (targetKey: string) => {
        console.log("保存" + targetKey);
        // dispatch({
        //     type: 'httpPage/delConsole',
        //     payload: targetKey,
        // })
    };

    function renderCurrentTab(item: BaseConsoleInfo<TreeNodeResDto, HttpConsoleInfo>) {
        return (
            <>
                <HttpConsole key={item.data.key} httpPage={httpPage} appPage={appPage} dispatch={dispatch} console={item} node={item.data} data={item.data.data} info={item.page} />
            </>
        );
    }

    return <ConsoleBox
        renderCurrentTab={renderCurrentTab}
        onChangeTab={onChangeTab}
        consoles={consoles}
        activeKey={activeKey}
        save={save}
        delConsole={delConsole}
    />

};

export default HttpRigth;