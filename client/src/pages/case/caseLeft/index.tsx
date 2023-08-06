import React, { useEffect, useRef, useState } from 'react';
import styles from './index.less';
import './index.less';
import classnames from 'classnames';
import { Cascader, Dropdown, Input, Menu, Modal, message } from 'antd';
import i18n from '@/i18n';
import Iconfont from '@/components/Iconfont';
import SearchInput from '@/components/SearchInput';
import { IAppPageState } from '@/models/appPage';
import CaseTree from './CaseTree';
import { IConsoleIndo, TreeNodeResDto } from '@/typings';
import MenuItem from 'antd/lib/menu/MenuItem';
import { ModalForm, ProFormText, ProFormTextArea } from '@ant-design/pro-components';
import AddRule from '../components/addRule';
import TreeAdd from '../components/treeAdd';

interface IProps {
  appPage: IAppPageState;
  dispatch: any;
}

enum handleType {
  DIR = 'DIR',
  CASE = 'CASE'
}

const menuList = [
  {
    key: handleType.DIR,
    icon: '\ueac5',
    title: " " + i18n('case.button.addClass'),
  },
  {
    key: handleType.CASE,
    icon: '\ue945',
    title: " " + i18n('case.button.addCase'),
  },
];

function CaseLatf(props: IProps) {
  const { appPage, dispatch } = props;
  const [dirFlag, setDirFlag] = useState(false);
  const [caseFlag, setCaseFlag] = useState(false);


  const searchTable = (value: string) => {
    dispatch({
      type: 'appPage/setCaseSearchInfo',
      payload: value,
    })
  };

  function refresh() {
    let flag = true;
    appPage.caseConsoles?.map((iconsole: IConsoleIndo<TreeNodeResDto>, index: number) => {
      if (iconsole.nowFileContent != iconsole.data.data?.fileContent) {
        message.error("请先保存:" + iconsole.name);
        flag = false;
      }
    });
    if (flag) {
      dispatch({
        type: 'appPage/intiProject',
        payload: { projectCode: appPage.curProject.code },
      })
    }
  }
  const renderMenu = () => {

    const clickMenuList = (item: any) => {
      switch (item.key) {
        case handleType.DIR:
          return setDirFlag(true);
        case handleType.CASE:
          return setCaseFlag(true);
      }
    }

    return <Menu>
      {
        menuList.map((item, index) => {
          return <MenuItem key={index} onClick={clickMenuList.bind(null, item)}>
            <span>
              <Iconfont code={item.icon!}></Iconfont>
              {item.title}
            </span>
          </MenuItem>
        })
      }
    </Menu>
  };


  return <>
    <div className={styles.aside}>
      <div className={styles.header}>
        <SelectProJect appPage={appPage} dispatch={dispatch} />
        <div className={styles.searchBox}>
          <SearchInput onChange={searchTable} placeholder={"搜索"} />
          <div
            className={classnames(styles.refresh, styles.button)}
            onClick={refresh}
          >
            <Iconfont code="&#xe8aa;" size={15} />
          </div>

          <div
            className={classnames(styles.create, styles.button)}
          >
            <Dropdown overlay={renderMenu()} trigger={['click']}>
              <Iconfont code="&#xe727;" />
            </Dropdown>
          </div>
        </div>
      </div>
      <div className={styles.overview}>
        <Iconfont code="&#xe6ad;" />
        <span>{i18n('case.button.overview')}</span>
      </div>
      <CaseTree appPage={appPage} dispatch={dispatch} />
    </div>
    <TreeAdd parentKey={"0"} appPage={appPage} dispatch={dispatch} showFlag={dirFlag} callback={() => { setDirFlag(false) }} />
    <AddRule parentKey={"0"} appPage={appPage} dispatch={dispatch} showFlag={caseFlag} callback={() => { setCaseFlag(false) }} />
  </>
};

const SelectProJect = function (props: IProps) {
  const { appPage, dispatch } = props;
  const dropdownRender = (menus: React.ReactNode) => <div>{menus}</div>;

  const onChange: any = (valueArr: any, selectedOptions: any) => {
    if (valueArr[0] !== appPage.curProject?.code) {
      dispatch({
        type: 'appPage/intiProject',
        payload: { projectCode: valueArr[0] },
      })
    }
  };
  return (
    <div className={styles.selectDatabaseBox}>
      <Cascader
        // popupClassName={styles.cascaderPopup}
        options={(appPage.projects || []).map((t) => {
          return {
            value: t.code,
            label: t.name,
          };
        })}
        onChange={onChange}
        bordered={false}
        dropdownRender={dropdownRender}
      >
        <div className={styles.currentDatabase}>
          <div className={styles.name}>
            {appPage.curProject?.name || <span style={{ opacity: 0.8 }}>选择项目</span>}
          </div>
          <Iconfont code="&#xe62d;" />
        </div>
      </Cascader>
    </div >
  );
};



export default CaseLatf;
