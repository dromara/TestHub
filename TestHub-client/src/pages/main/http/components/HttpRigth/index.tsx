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
interface IProps {
  tabList?: any[];
  httpPage: IHttpPageState;
  dispatch: any;
  icon: string;
}

function HttpRigth(props: IProps) {
  const { dispatch, httpPage, icon } = props;
  const consoles = httpPage.consoles;
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
    <div className={styles.httpPageRight}>
      {!consoles.length && <Empty />}
      {consoles.length > 0 && (
        <>
          <div className={styles.httpPageTabs}>
            <Tabs
              items={tabItems}
              onChange={(key) => {
                if (key != httpPage.activeKey) {
                  dispatch({
                    type: 'httpPage/setActiveKey',
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
                  type: 'httpPage/delConsole',
                  payload: keys,
                });
              }}
              activeKey={httpPage.activeKey}
              editableNameOnBlur={(option: ITabItem) => {
                // dispatch({
                //   type: 'httpPage/editConsoleName',
                //   payload: {
                //     key: option.key,
                //     name: option.label,
                //   },
                // });
                dispatch({
                  type: 'httpPage/rename',
                  payload: {
                    name: option.label,
                    id: parseInt(option.key + ''),
                  },
                  callback: (treeNode: TreeNodeResDto<any>) => {
                    if (treeNode != undefined && treeNode != null) {
                      dispatch({
                        type: 'httpPage/putTree',
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
          <div className={styles.httpPageContent}>
            {consoles?.map((item: ConsoleInfo<any, any>, index: number) => {
              return (
                <div
                  key={item.key}
                  className={classnames(styles.windowContent, {
                    [styles.concealTab]: httpPage.activeKey !== item.key,
                  })}
                  hidden={httpPage.activeKey !== item.key}
                >
                  <HttpConsole key={item.key} httpPage={httpPage} dispatch={dispatch} consoleInfo={item} />
                </div>
              );
            })}
          </div>
        </>
      )}
    </div>
  );
}

export default HttpRigth;
