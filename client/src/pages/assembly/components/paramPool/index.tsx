import React, { useEffect, useRef, useState } from 'react';
import { Button, Card, Modal } from 'antd';
import { IAppPageState, IAssemblyPageState } from 'umi';
import styles from './index.less';
import i18n from '@/i18n';

import { DeleteOutlined, EditOutlined, PlusOutlined, } from '@ant-design/icons';
import Env from './env';

const { Meta } = Card;

interface IProps {
    appPage: IAppPageState;
    assemblyPage: IAssemblyPageState;
    dispatch: any;
}

export default (props: IProps) => {
    const envRef = useRef();
    const [envOpen, setEnvOpen] = useState(false);
    const [delOpen, setDelOpen] = useState(false);
    const { appPage, assemblyPage, dispatch } = props;
    const [index, setIndex] = useState<number>(-1);

    const getMenu = () => {
        const cardList2 = [];
        if (appPage.curProject) {
            for (let index = 0; index < appPage.curProject.environments.length; index++) {
                const item = appPage.curProject.environments[index];
                if (
                    assemblyPage.searchKey == undefined ||
                    assemblyPage.searchKey == "" || (
                        item.name && item.name.toUpperCase().includes(assemblyPage.searchKey.toUpperCase()) || (
                            item.code && item.code.toUpperCase().includes(assemblyPage.searchKey.toUpperCase())) || (
                            item.remark && item.remark.toUpperCase().includes(assemblyPage.searchKey.toUpperCase())))
                ) {
                    cardList2.push(
                        <Card
                            key={Math.random() * 10000000000000000}
                            className={styles.dcard}
                            style={{ width: "100%", height: 150 }}
                            actions={[
                                <EditOutlined
                                    key="edit"
                                    onClick={() => {
                                        setIndex(index);
                                        setEnvOpen(true);
                                    }}
                                />,
                                <DeleteOutlined
                                    key="del"
                                    onClick={() => {
                                        setIndex(index);
                                        setDelOpen(true);
                                    }}
                                />,
                            ]}
                        >
                            <Meta
                                title={item.code + "-" + item.name}
                                description={
                                    <div className={styles.ellipsis}>{item.remark}</div>
                                }
                            />
                        </Card>
                    ); cardList2.push(
                        <Card
                            key={Math.random() * 10000000000000000}
                            className={styles.dcard}
                            style={{ width: "100%", height: 150 }}
                            actions={[
                                <EditOutlined
                                    key="edit"
                                    onClick={() => {
                                        setIndex(index);
                                        setEnvOpen(true);
                                    }}
                                />,
                                <DeleteOutlined
                                    key="del"
                                    onClick={() => {
                                        setIndex(index);
                                        setDelOpen(true);
                                    }}
                                />,
                            ]}
                        >
                            <Meta
                                title={item.code + "-" + item.name}
                                description={
                                    <div className={styles.ellipsis}>{item.remark}</div>
                                }
                            />
                        </Card>
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
                <Button type="dashed" block style={{ width: "100%", height: 150 }} onClick={() => { setIndex(-1); setEnvOpen(true); }} >
                    <PlusOutlined style={{ fontSize: '22px' }} />
                </Button>
            </div>
            <Modal
                title={"配置环境"}
                width={750}
                open={envOpen}
                destroyOnClose
                onOk={async () => {
                    if (envRef.current != undefined) {
                        const res = await envRef.current.getData();
                        const env = res.data;
                        env.projectCode = appPage.curProject.code;
                        dispatch({
                            type: 'appPage/saveEnvironment',
                            payload: {
                                index: index,
                                data: res.data
                            },
                            callback: () => { setEnvOpen(false) }
                        })
                    }
                }}
                onCancel={() => { setEnvOpen(false) }}
                maskClosable={true}
                okText={i18n('case.button.ok')}
                cancelText={i18n('case.button.cancel')}
            >
                <Env data={(index > -1 && envOpen) ? JSON.parse(JSON.stringify(appPage.curProject?.environments[index])) : {}} ref={envRef}></Env>
            </Modal >
            <Modal
                title="是否确实删除环境"
                width={360}
                open={delOpen}
                maskClosable={true}
                onCancel={() => { setDelOpen(false) }}
                footer={[
                    <Button
                        key="cancel"
                        type="default"
                        onClick={() => { setDelOpen(false) }}
                    >
                        {i18n('case.button.cancel')}
                    </Button>,
                    <Button
                        key="confirm"
                        danger
                        onClick={async () => {
                            dispatch({
                                type: 'appPage/delEnvironment',
                                payload: {
                                    index: index,
                                    projectCode: appPage.curProject.code,
                                    code: appPage.curProject?.environments[index].code
                                },
                                callback: (flag: boolean) => { setIndex(-1); setDelOpen(false); console.log(appPage.curProject) }
                            })
                        }}
                    >
                        {i18n('case.button.ok')}
                    </Button>,
                ]}
            >

            </Modal >
        </>
    );
};