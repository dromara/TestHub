
import React, { useEffect, useState } from 'react';
import classnames from 'classnames';
import { BaseConsoleInfo, BaseConsoleStatus, BasePageInfo, TreeNodeResDto } from '@/typings';
import i18n from '@/i18n';
import styles from './index.less';
import './index.less'
import { Button, Modal, Tabs } from 'antd';
import AppHeader from '@/components/AppHeader';


interface IProps {
    onChangeTab: Function;
    renderCurrentTab: Function;
    save: Function;
    delConsole: Function;
    activeKey: string | undefined;
    consoles: BaseConsoleInfo<TreeNodeResDto, BasePageInfo>[];
}

function ConsoleBox(props: IProps) {
    const [targetKey, setTargetKey] = useState<string | undefined>(undefined);

    return <div className={styles.box}>
        <AppHeader className={styles.appHeader} showRight={false}>
            <div className={styles.tabsBox}>
                {!!props.consoles.length && (
                    <Tabs
                        hideAdd
                        type="editable-card"
                        onChange={props.onChangeTab}
                        activeKey={props.activeKey}
                        onEdit={(targetKey: any, action: 'add' | 'remove') => {
                            const index = props.consoles.findIndex(item => item.key === targetKey);
                            const isDraft = props.consoles[index].status == BaseConsoleStatus.DRAFT;
                            if (!isDraft) {
                                //没有修改
                                props.delConsole(targetKey);
                            } else {
                                setTargetKey(targetKey);
                            }
                        }}
                        items={props.consoles.map((t) => {
                            return {
                                key: t.key,
                                label: t.status == BaseConsoleStatus.DRAFT ? <div className={styles.tabDraft}>{t.name}</div> : <div>{t.name}</div>,
                            };
                        })}
                    ></Tabs>
                )}
            </div>
        </AppHeader>
        <div className={styles.databaseQueryBox}>
            {!props.consoles.length && <div className={styles.ears}>{__APP_NAME__}</div>}
            {props.consoles?.map((item: BaseConsoleInfo<TreeNodeResDto, BasePageInfo>, index: number) => {
                return (
                    <div
                        key={index}
                        className={classnames(styles.windowContent, {
                            [styles.concealTab]: props.activeKey !== item.key,
                        })}
                    >
                        {props.renderCurrentTab(item)}
                    </div>
                );
            })}
        </div>
        <Modal
            title={i18n('case.message.fileModified')}
            open={targetKey != undefined}
            onOk={() => {
                props.save(targetKey);
                props.delConsole(targetKey);
                setTargetKey(undefined);
                return true;
            }}
            onCancel={() => {
                setTargetKey(undefined);
                props.delConsole(targetKey);
            }}
            maskClosable={false}
            okText={i18n('case.button.save')}
            cancelText={i18n('case.button.unsave')}
        >
            是否进行保存
        </Modal >

    </div>

};

export default ConsoleBox;