import AppHeader from '@/components/AppHeader';
import { IConsoleInfo } from '@/typings';
import { Card, Tabs } from 'antd';
import React, { useCallback, useEffect, useRef, useState } from 'react';
import classnames from 'classnames';
import styles from './index.less';
import './index.less';
import { IAppPageState, IToolsPageState, connect } from 'umi';
import { IHttpPageState } from '@/models/httpPage';
import DraggableContainer from '@/components/DraggableContainer';
import SearchTree from '@/components/SearchTree';
const dvaModel = connect(
    ({ httpPage, appPage }: { httpPage: IHttpPageState, appPage: IAppPageState }) => ({
        appPage: appPage,
        httpPage: httpPage
    }),
);
interface IProps {
    httpPage: IHttpPageState;
    appPage: IAppPageState;
    dispatch: any;
}

function Http(props: IProps) {
    const { dispatch, appPage, httpPage } = props;

    useEffect(() => {
        if (appPage.curProject == null) {
            dispatch({
                type: 'appPage/getProjects',
                callback: (projects: any) => {
                    dispatch({
                        type: 'appPage/intiProject',
                        payload: { projectCode: projects[0].code },
                    })
                }
            })
        }
    }, []);

    const volatileRef = useRef<any>();
    const callback = () => {
        // monacoEditorExternalList[activeKey!] && monacoEditorExternalList[activeKey!].layout();
    };
    return <>
        <DraggableContainer className={classnames(styles.httpBox)} callback={callback} volatileDom={{ volatileRef, volatileIndex: 0 }} >
            <div ref={volatileRef} className={styles.httpLeftBox}>
                <SearchTree dispatch={dispatch} />
            </div>
            <div className={styles.httpRightCase}>
                右
            </div>
        </DraggableContainer>
    </>
};


export default dvaModel(Http);
