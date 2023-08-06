import AppHeader from '@/components/AppHeader';
import { IConsoleInfo } from '@/typings';
import { Card, Tabs } from 'antd';
import React, { useCallback, useEffect, useState } from 'react';
import classnames from 'classnames';
import styles from './index.less';
import './index.less';
import { IToolsPageState, connect } from 'umi';
import ToolsMain from './components/main';
import { ToolsTypeCode } from '@/utils/constants';
import Compar from './components/compar';
const dvaModel = connect(
    ({ toolsPage }: { toolsPage: IToolsPageState }) => ({
        toolsPage: toolsPage
    }),
);
interface IProps {
    toolsPage: IToolsPageState;
    dispatch: any;
}

function Tools(props: IProps) {
    const { dispatch, toolsPage } = props;
    const [activeKey, setActiveKey] = useState<ToolsTypeCode>(toolsPage.activeKey);
    const [consoles, setConsoles] = useState<IConsoleInfo<any>[]>(toolsPage.consoles);
    useEffect(() => {
        setActiveKey(toolsPage.activeKey);
    }, [toolsPage.activeKey]);
    useEffect(() => {
        setConsoles(toolsPage.consoles);
    }, [toolsPage.consoles]);

    const onChangeTab = (newActiveKey: string) => {
        dispatch({
            type: 'toolsPage/setActiveKey',
            payload: newActiveKey,
        })
    };
    function renderCurrentTab(iConsole: IConsoleInfo<any>) {
        if (iConsole.key == ToolsTypeCode.MAIN) {
            return (
                <ToolsMain dispatch={dispatch} toolsPage={toolsPage} />

            );
        } else if (iConsole.key == ToolsTypeCode.COMPAR) {
            return (
                <Compar />
            );
        } else {
            return (
                <>{iConsole.key}</>
            );
        }
    }

    return <div className={styles.toolsBox}>
        <AppHeader showRight={false}>
            <div className={styles.toolsTabsBox}>
                {!!consoles.length && (
                    <Tabs
                        hideAdd
                        type="editable-card"
                        onChange={onChangeTab}
                        activeKey={activeKey + ""}
                        onEdit={(targetKey: any, action: 'add' | 'remove') => {
                            const index = consoles.findIndex(item => item.key === targetKey);
                        }}
                        items={consoles.map((t, index: number) => {
                            return {
                                key: index + "",
                                closable: t.key == ToolsTypeCode.MAIN ? false : true,
                                label: <div>{t.name}</div>,
                            };
                        })}
                    ></Tabs>
                )}
            </div>
        </AppHeader>
        <div className={ToolsTypeCode.MAIN == activeKey ? styles.toolsConsoleMain : styles.toolsConsole}>
            {consoles?.map((iConsole: IConsoleInfo<any>, index: number) => {
                if (activeKey !== iConsole.key) {
                    return <></>;
                }
                return (
                    renderCurrentTab(iConsole)
                );
            })}
        </div>
    </div>
};


export default dvaModel(Tools);
