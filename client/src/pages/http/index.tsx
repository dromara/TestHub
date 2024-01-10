import React, { useEffect, useRef, useState } from 'react';
import classnames from 'classnames';
import styles from './index.less';
import './index.less';
import { IAppPageState, connect } from 'umi';
import { IHttpPageState } from '@/models/httpPage';
import DraggableContainer from '@/components/DraggableContainer';
import i18n from '@/i18n';
import Iconfont from '@/components/Iconfont';
import HttpLeft from './components/httpLeft';
import HttpRigth from './components/httpRigth';
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
    const [isUnfold, setIsUnfold] = useState(true);
    const volatileRef = useRef<any>();

    useEffect(() => {
        if (appPage.curProject == null) {
            dispatch({
                type: 'appPage/getProjects',
                callback: (projects: any) => {
                    dispatch({
                        type: 'appPage/intiProject',
                        payload: { projectCode: projects[0].code },
                        callback: (project: any) => {
                            dispatch({
                                type: 'httpPage/loadHttpTrees',
                                payload: { projectCode: projects[0].code },
                            })
                        }
                    })
                }
            })
        } else {
            if (!httpPage.isLoaded) {
                dispatch({
                    type: 'httpPage/loadHttpTrees',
                    payload: { projectCode: appPage.curProject.code },
                })
            }
        }
    }, []);
    const moveLeftAside = () => {
        if (volatileRef.current) {
            if (volatileRef.current.offsetWidth === 0) {
                volatileRef.current.style.width = '260px';
                setIsUnfold(true);
            } else {
                volatileRef.current.style.width = '0px';
                setIsUnfold(false);
            }
        }
    };

    const callback = () => {
        // monacoEditorExternalList[activeKey!] && monacoEditorExternalList[activeKey!].layout();
    };
    return <>
        <DraggableContainer className={classnames(styles.httpBox)} callback={callback} volatileDom={{ volatileRef, volatileIndex: 0 }} >
            <div ref={volatileRef} className={styles.httpLeftBox}>
                <HttpLeft httpPage={httpPage} dispatch={dispatch} appPage={appPage} />
            </div>
            <div className={styles.httpRightCase}>
                <HttpRigth httpPage={httpPage} dispatch={dispatch} appPage={appPage} />
                <div className={styles.httpFooter}>
                    <div className={classnames({ [styles.httpReversalIconBox]: isUnfold }, styles.httpIconBox)} onClick={moveLeftAside}>
                        <Iconfont code='&#xe6b5;' />
                    </div>
                    {
                        !!httpPage.consoles.length &&
                        <div onClick={() => {
                            dispatch({
                                type: 'httpPage/setShowResult',
                                payload: !httpPage.showResult,
                            })
                        }} className={classnames(styles.httpCommandSearchResult, { [styles.httpUnfoldSearchResult]: httpPage.showResult })}>
                            执行结果
                            <Iconfont code='&#xe6b5;' />
                        </div>
                    }
                </div>
            </div>
        </DraggableContainer>
    </>
};


export default dvaModel(Http);
