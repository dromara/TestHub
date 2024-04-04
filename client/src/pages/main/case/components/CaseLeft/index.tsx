import React, { useState } from 'react';
import MenuLabel from '@/components/base/MenuLabel';
import SearchTree from '@/components/TestHub/SearchTree';
import { MenuProps } from 'antd/lib';
import { TreeItem } from 'react-complex-tree';
import { TreeNodeResDto } from '@/typings/server/base';
import { HTTP } from '@/components/TestHub/http/typings';
import { Spin } from 'antd';
import { CaseNodeType } from '../..';
import TreeAdd from './components/TreeAdd';
import TreeRename from './components/TreeRename';
import ApiAdd from './components/CaseAdd';
import { getCurrentProject } from '@/utils/localStorage';
import { ICasePageState } from '@/models/casePage';
import CaseAdd from './components/CaseAdd';
interface IProps {
  casePage: ICasePageState;
  dispatch: any;
  refresh: () => void;
  recognizeIcon: (nodeType: string) => string;
}

function CaseLeft(props: IProps) {
  const { dispatch, casePage, recognizeIcon } = props;
  const [dirFlag, setDirFlag] = useState<string>();
  const [addCaseFlag, setAddCaseFlag] = useState<string>();
  const [renameFlag, setRenameFlag] = useState<TreeItem>();
  return (
    <>
      {!casePage.isLoaded && (
        <div style={{ width: '100%', height: '100%' }}>
          <Spin />
        </div>
      )}
      {casePage.isLoaded && (
        <SearchTree
          datas={casePage?.trees}
          onDrop={(items, targetNode) => {
            console.log(items);
            console.log(targetNode);
          }}
          onDoubleClick={(item) => {
            const node = item as TreeNodeResDto<HTTP.HttpRequestResDto>;
            dispatch({
              type: 'casePage/loadCase',
              payload: item.index,
              callback: (data) => {
                node.data.info = data;
                // console.log(JSON.stringify(item.data));
                dispatch({
                  type: 'casePage/addConsole',
                  payload: {
                    node: item.data,
                  },
                });
              },
            });
          }}
          onRefresh={() => {
            dispatch({
              type: 'casePage/loadTrees',
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
              label: <MenuLabel icon={'\ue713'} label="新增用例" />,
              key: 'ue713',
              onClick: () => {
                setAddCaseFlag('0');
              },
            });
            return closeTabsMenu;
          }}
          recognizeIcon={(item) => {
            return recognizeIcon((item as TreeNodeResDto<HTTP.HttpRequestResDto>).data.nodeType);
          }}
          renderMenu={(item: TreeItem) => {
            const closeTabsMenu: MenuProps['items'] = [];
            if (item.data.nodeType == CaseNodeType.DIR) {
              closeTabsMenu.push({
                label: <MenuLabel icon={'\ueac5'} label="新增文件夹" />,
                key: 'ueac5',
                onClick: () => {
                  setDirFlag(item.index + '');
                },
              });
              closeTabsMenu.push({
                label: <MenuLabel icon={'\ue713'} label="新增用例" />,
                key: 'ue713',
                onClick: () => {
                  setAddCaseFlag(item.index);
                },
              });
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
      <CaseAdd
        parentKey={addCaseFlag}
        dispatch={props.dispatch}
        callback={() => {
          setAddCaseFlag(undefined);
        }}
      />
      <TreeAdd
        parentKey={dirFlag}
        casePage={casePage}
        dispatch={props.dispatch}
        callback={() => {
          setDirFlag(undefined);
        }}
      />
      <TreeRename
        thisNode={renameFlag}
        dispatch={props.dispatch}
        functionRename="casePage/rename"
        functionPutTree="casePage/putTree"
        callback={() => {
          setRenameFlag(undefined);
        }}
      />
    </>
  );
}

export default CaseLeft;
