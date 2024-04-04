import React, { Fragment, useMemo, useRef } from 'react';
import classnames from 'classnames';
import styles from './index.less';
import Empty from '@/components/base/Empty';
import { ConsoleInfo, ConsoleStatus } from '@/typings/client';
import Tabs, { ITabItem } from '@/components/base/Tabs';
import { IHttpPageState } from '@/models/httpPage';
import HttpConsole from '../HttpConsole';
import { getCurrentProject } from '@/utils/localStorage';
import { TreeNodeResDto } from '@/typings';
import { ICasePageState } from '@/models/casePage';
import CaseConsole from '../CaseConsole';
interface IProps {
  tabList?: any[];
  casePage: ICasePageState;
  dispatch: any;
  icon: string;
}

function CaseRigth(props: IProps) {
  const { dispatch, casePage, icon } = props;
  const consoles = casePage.consoles;
  const currentProject = getCurrentProject();
  // console.log(props.httpPage.consoles);

  const tabItems = useMemo(() => {
    return consoles?.map((item) => {
      return {
        prefixIcon: icon,
        label: item.name,
        key: item.key,
        status: item.status,
        editableName: true,
        children: <Fragment key={item.key}>{<>{item.name}</>}</Fragment>,
      };
    });
  }, [consoles]);

  return (
    <div className={styles.casePageRight}>
      {!consoles.length && <Empty />}
      {consoles.length > 0 && (
        <>
          <div className={styles.casePageTabs}>
            <Tabs
              items={tabItems}
              onChange={(key) => {
                if (key != casePage.activeKey) {
                  dispatch({
                    type: 'casePage/setActiveKey',
                    payload: key,
                  });
                }
              }}
              onSave={(data: ITabItem) => {
                //有坑
                return false;
              }}
              onEdit={(action: 'add' | 'remove', data?: ITabItem[], list?: ITabItem[]) => {
                if (data == undefined) {
                  return;
                }
                const keys = data.map((item) => item.key);
                dispatch({
                  type: 'casePage/delConsole',
                  payload: keys,
                });
              }}
              activeKey={casePage.activeKey}
              editableNameOnBlur={(option: ITabItem) => {
                dispatch({
                  type: 'casePage/rename',
                  payload: {
                    name: option.label,
                    id: parseInt(option.key + ''),
                  },
                  callback: (treeNode: TreeNodeResDto<any>) => {
                    if (treeNode != undefined && treeNode != null) {
                      dispatch({
                        type: 'casePage/putTree',
                        payload: {
                          oldParentId: parseInt(option.key + ''),
                          parentId: parseInt(option.key + ''),
                          node: treeNode,
                        },
                      });
                    }
                  },
                });
              }}
            />
          </div>
          <div className={styles.casePageContent}>
            {consoles?.map((item: ConsoleInfo<any, any>, index: number) => {
              return (
                <div
                  key={item.key}
                  className={classnames(styles.windowContent, {
                    [styles.concealTab]: casePage.activeKey !== item.key,
                  })}
                  hidden={casePage.activeKey !== item.key}
                >
                  <CaseConsole
                    key={item.key}
                    caseCode={item.key}
                    casePage={casePage}
                    dispatch={dispatch}
                    consoleInfo={item}
                  />
                </div>
              );
            })}
          </div>
        </>
      )}
    </div>
  );
}

export default CaseRigth;
