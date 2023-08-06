import React, { useEffect, useState } from 'react';
import styles from './index.less';
import './index.less';
import classnames from 'classnames';
import { Modal } from 'antd';
import { IAppPageState } from '@/models/appPage';
import AppHeader from '@/components/AppHeader';
import { TreeNodeResDto } from '@/typings';
import { Tabs } from 'antd';
import { IConsoleIndo } from '@/typings/client';
import CaseEditor from './caseEditor';
import i18n from '@/i18n';

interface IProps {
  appPage: IAppPageState;
  dispatch: any;
}

function CaseRight(props: IProps) {
  const { appPage, dispatch } = props;
  const [consoles, setConsoles] = useState<IConsoleIndo<TreeNodeResDto>[]>(appPage.caseConsoles);
  const [activeKey, setActiveKey] = useState<string>();
  const [confirmModal, setConfirmModal] = useState<number>(-1);
  const onChangeTab = (newActiveKey: string) => {
    dispatch({
      type: 'appPage/setCaseActiveKey',
      payload: newActiveKey,
    })
  };

  useEffect(() => {
    setConsoles(appPage.caseConsoles);
  }, [appPage.caseConsoles]);
  useEffect(() => {
    setActiveKey(appPage.caseActiveKey);
  }, [appPage.caseActiveKey]);

  function renderCurrentTab(iconsole: IConsoleIndo<TreeNodeResDto>) {
    return (
      <CaseEditor id={iconsole.key} iconsole={iconsole} appPage={appPage} dispatch={dispatch} />
    );
  }
  return <>
    <div className={styles.box}>
      <AppHeader className={styles.appHeader} showRight={false}>
        <div className={styles.tabsBox}>
          {!!consoles.length && (
            <Tabs
              hideAdd
              type="editable-card"
              onChange={onChangeTab}
              activeKey={activeKey}
              onEdit={(targetKey: any, action: 'add' | 'remove') => {
                const index = consoles.findIndex(item => item.key === targetKey);
                const isDraft = consoles[index].nowFileContent != undefined && consoles[index].nowFileContent !== consoles[index].data.data?.fileContent;
                if (!isDraft) {
                  //没有修改
                  dispatch({
                    type: 'appPage/delCaseConsole',
                    payload: targetKey,
                  })
                } else {
                  setConfirmModal(index);
                }
              }}
              items={consoles.map((t) => {
                return {
                  key: t.key,
                  label: t.nowFileContent != undefined && t.nowFileContent !== t.data.data?.fileContent ? <div className={styles.tabDraft}>{t.name}</div> : <div>{t.name}</div>,
                };
              })}
            ></Tabs>
          )}
        </div>
      </AppHeader>
      <div className={styles.databaseQueryBox}>
        {!consoles.length && <div className={styles.ears}>{__APP_NAME__}</div>}
        {consoles?.map((i: IConsoleIndo<TreeNodeResDto>, index: number) => {
          return (
            <div
              key={index}
              className={classnames(styles.windowContent, {
                [styles.concealTab]: activeKey !== i.key,
              })}
            >
              {renderCurrentTab(i)}
            </div>
          );
        })}
      </div>
      {confirmModal > -1 && (
        <Modal
          title={i18n('case.message.fileModified') + consoles[confirmModal].name}
          open={true}
          onOk={() => {
            dispatch({
              type: 'appPage/saveCaseFileContent',
              payload: {
                code: consoles[confirmModal].key,
                documentStr: consoles[confirmModal].nowFileContent
              },
            })
            dispatch({
              type: 'appPage/delCaseConsole',
              payload: consoles[confirmModal].key,
            })
            setConfirmModal(-1);
            return true
          }}
          onCancel={() => {
            setConfirmModal(-1);
            dispatch({
              type: 'appPage/delCaseConsole',
              payload: consoles[confirmModal].key,
            })
          }}
          maskClosable={true}
          okText={i18n('case.button.save')}
          cancelText={i18n('case.button.unsave')}
        >
          {i18n('case.message.unsavedChanges')}
        </Modal >
      )
      }
    </div >
  </>
};


export default CaseRight;
