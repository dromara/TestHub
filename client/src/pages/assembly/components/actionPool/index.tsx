import React, { useEffect, useRef, useState } from 'react';
import { Button, Card, Modal, Tag } from 'antd';
import { IAppPageState, IAssemblyPageState } from 'umi';
import styles from './index.less';
import i18n from '@/i18n';

import { DeleteOutlined, EditOutlined, PlusOutlined, } from '@ant-design/icons';
import Action from './action';
import Icon from '@/components/testHub/plugins/Icon';

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
                        <Card
                            key={Math.random() * 10000000000000000}
                            style={{ marginTop: 10 }}
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
                            title={item.code}
                            extra={<Icon actionType={item.type}></Icon>}
                        >
                            <div className={styles.ellipsis}>{item.name}</div>
                        </Card >
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
                <Button type="dashed" block style={{ width: "100%", height: 180, marginTop: 10 }} onClick={() => { setIndex(-1); setEnvOpen(true); }} >
                    <PlusOutlined style={{ fontSize: '22px' }} />
                </Button>
            </div>
            <Modal
                title={"配置行为"}
                width={750}
                open={envOpen}
                destroyOnClose
                onOk={async () => {
                    if (envRef.current != undefined) {
                        const res = await envRef.current.getData();
                        if (res.flag) {

                        }
                        console.log(res)
                    }
                }}
                onCancel={() => { setEnvOpen(false) }}
                maskClosable={true}
                okText={i18n('case.button.ok')}
                cancelText={i18n('case.button.cancel')}
            >
                <Action data={(index > -1 && envOpen) ? JSON.parse(JSON.stringify(appPage.curProject?.actions[index])) : { type: "SQL", dataType: "MAP", extraInto: {} }} ref={envRef}></Action>
            </Modal >
            <Modal
                title="是否确定删除行为"
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