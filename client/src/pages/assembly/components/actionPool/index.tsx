import React, { useEffect, useRef, useState } from 'react';
import { EditOutlined, PlusOutlined, } from '@ant-design/icons';
import { Badge, Button, Card, List, Modal, Tag } from 'antd';
import { IAppPageState, IAssemblyPageState } from 'umi';
import styles from './index.less';
import i18n from '@/i18n';
import './index.less';

import Action from './action';
import ActionIcon from '@/components/testHub/plugins/Icon';


interface IProps {
    appPage: IAppPageState;
    assemblyPage: IAssemblyPageState;
    dispatch: any;
}

export default (props: IProps) => {
    const actionRef = useRef();
    const [actionOpen, setActionOpen] = useState(false);
    const { appPage, assemblyPage, dispatch } = props;
    const [index, setIndex] = useState<number>(-1);

    const getMenu = () => {
        const cardList2 = [];
        if (appPage.curProject) {
            for (let index = 0; index < appPage.curProject.actions.length; index++) {
                const item = appPage.curProject.actions[index];
                if (
                    assemblyPage.searchKey == undefined ||
                    assemblyPage.searchKey == "" || (
                        item.name && item.name.toUpperCase().includes(assemblyPage.searchKey.toUpperCase()) || (
                            item.code && item.code.toUpperCase().includes(assemblyPage.searchKey.toUpperCase())) || (
                            item.remark && item.remark.toUpperCase().includes(assemblyPage.searchKey.toUpperCase())))
                ) {
                    cardList2.push(
                        <div>
                            {/* <Badge.Ribbon text="Hippies"> */}
                            <div className="dcardsec">
                                <ActionIcon actionType={item.type} className="tag-right-top" />
                                <div className="main">
                                    <div className="head">{item.code}</div>
                                    <div className="body">
                                        {item.name}
                                    </div>
                                </div>
                                <div className="icon">
                                    <EditOutlined onClick={() => {
                                        setIndex(index);
                                        setActionOpen(true);
                                    }} />
                                </div>
                            </div>
                            {/* </Badge.Ribbon> */}
                        </div>

                    );
                }
            }
        }

        return cardList2;
    };

    const [cardList, setCardList] = useState([]);
    useEffect(() => {
        setCardList(getMenu());
    }, [assemblyPage.searchKey]);
    return (
        <>
            <div className={styles.card_container}>
                {cardList.length > 0 ? cardList : getMenu()}
                <Button type="dashed" block style={{ width: "100%", height: 80 }} onClick={() => { setIndex(-1); setActionOpen(true); }} >
                    <PlusOutlined style={{ fontSize: '22px' }} />
                </Button>
            </div>
            <Modal
                title={"配置行为"}
                width={820}
                open={actionOpen}
                destroyOnClose
                onOk={async () => {
                    if (actionRef.current != undefined) {
                        const res = await actionRef.current.getData();
                        const action = res.data;
                        action.projectCode = appPage.curProject.code;
                        if (res.flag) {
                            dispatch({
                                type: 'appPage/saveAction',
                                payload: {
                                    index: index,
                                    data: res.data
                                },
                                callback: () => { setCardList(getMenu()); setActionOpen(false) }
                            })
                        }
                    }
                }}
                onCancel={() => { setActionOpen(false) }}
                maskClosable={true}
                okText={i18n('case.button.ok')}
                cancelText={i18n('case.button.cancel')}
            >
                <Action data={(index > -1 && actionOpen) ? JSON.parse(JSON.stringify(appPage.curProject?.actions[index])) : { type: "HTTP", dataType: "MAP", extraInto: {} }} ref={actionRef}></Action>
            </Modal >
        </>
    );
};