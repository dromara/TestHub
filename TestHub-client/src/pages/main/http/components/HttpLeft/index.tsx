import React, { useState } from 'react';
import MenuLabel from '@/components/base/MenuLabel';
import SearchTree from '@/components/TestHub/SearchTree';
import { MenuProps } from 'antd/lib';
import { TreeItem } from 'react-complex-tree';
import { IHttpPageState } from '@/models/httpPage';
import { TreeNodeResDto } from '@/typings/server/base';
import { HTTP } from '@/components/TestHub/http/typings';
import { Spin } from 'antd';
import { HttpNodeType } from '../..';
import TreeAdd from './components/TreeAdd';
import TreeRename from './components/TreeRename';
import ApiAdd from './components/ApiAdd';
import { getCurrentProject } from '@/utils/localStorage';
interface IProps {
  httpPage: IHttpPageState;
  dispatch: any;
  refresh: () => void;
  recognizeIcon: (nodeType: string) => string;
}

function HttpLeft(props: IProps) {
  const { dispatch, httpPage, recognizeIcon } = props;
  const [dirFlag, setDirFlag] = useState<string>();
  const [upDirFlag, setUpDirFlag] = useState<TreeItem>();
  const [addApiFlag, setAddApiFlag] = useState<string>();
  const [renameFlag, setRenameFlag] = useState<TreeItem>();
  return (
    <>
      {!httpPage.isLoaded && (
        <div style={{ width: '100%', height: '100%' }}>
          <Spin />
        </div>
      )}
      {httpPage.isLoaded && (
        <SearchTree
          datas={httpPage?.trees}
          onDrop={(items, targetNode) => {
            console.log(items);
            console.log(targetNode);
          }}
          onDoubleClick={(item) => {
            const node = item as TreeNodeResDto<HTTP.HttpRequestResDto>;
            dispatch({
              type: 'httpPage/loadApi',
              payload: item.index,
              callback: (data) => {
                node.data.info = data;
                dispatch({
                  type: 'httpPage/addConsole',
                  payload: {
                    node: item.data,
                  },
                });
              },
            });
          }}
          onRefresh={() => {
            dispatch({
              type: 'httpPage/loadTrees',
              payload: { projectCode: getCurrentProject()?.code },
            });
          }}
          addMenu={() => {
            const closeTabsMenu: MenuProps['items'] = [];
            closeTabsMenu.push({
              label: <MenuLabel icon={'\ueac5'} label="新增文件夹" />,
              key: 'ueac5',
              onClick: () => {
                setDirFlag('0');
              },
            });
            closeTabsMenu.push({
              label: <MenuLabel icon={'\ue713'} label="新增接口" />,
              key: 'ue713',
              onClick: () => {
                setAddApiFlag('0');
              },
            });
            return closeTabsMenu;
          }}
          recognizeIcon={(item) => {
            return recognizeIcon((item as TreeNodeResDto<HTTP.HttpRequestResDto>).data.nodeType);
          }}
          renderMenu={(item: TreeItem) => {
            const closeTabsMenu: MenuProps['items'] = [];
            if (item.data.nodeType == HttpNodeType.DIR) {
              closeTabsMenu.push({
                label: <MenuLabel icon={'\ueac5'} label="新增文件夹" />,
                key: 'ueac5',
                onClick: () => {
                  setDirFlag(item.index + '');
                },
              });
              closeTabsMenu.push({
                label: <MenuLabel icon={'\ue713'} label="新增接口" />,
                key: 'ue713',
                onClick: () => {
                  setAddApiFlag(item.index);
                },
              });
            } else {
              //  api
            }
            closeTabsMenu.push({
              label: <MenuLabel icon={'\ue626'} label="重命名" />,
              key: 'ue626',
              onClick: () => {
                setRenameFlag(item);
              },
            });

            return closeTabsMenu;
          }}
        />
      )}
      <TreeAdd
        parentKey={dirFlag}
        httpPage={httpPage}
        dispatch={props.dispatch}
        callback={() => {
          setDirFlag(undefined);
        }}
      />
      <ApiAdd
        parentKey={addApiFlag}
        httpPage={httpPage}
        dispatch={props.dispatch}
        callback={() => {
          setAddApiFlag(undefined);
        }}
      />
      <TreeRename
        thisNode={renameFlag}
        httpPage={httpPage}
        dispatch={props.dispatch}
        callback={() => {
          setRenameFlag(undefined);
        }}
      />
    </>
  );
}

export default HttpLeft;
