import React, { useState, useRef, useLayoutEffect, useEffect } from 'react';
import { GridContent } from '@ant-design/pro-layout';
import { Col, Divider, Input, Menu, Row } from 'antd';
import styles from './style.less';
import ParamPool from './components/paramPool';
import { IAppPageState, IAssemblyPageState, connect } from 'umi';
import { WaterMark } from '@ant-design/pro-components';
import { MailOutlined, SearchOutlined, UserOutlined } from '@ant-design/icons';
import Iconfont from '@/components/Iconfont';
import ActionPool from './components/actionPool';
const dvaModel = connect(
  ({ appPage, assemblyPage }: { appPage: IAppPageState, assemblyPage: IAssemblyPageState }) => ({
    appPage: appPage,
    assemblyPage: assemblyPage
  }),
);
interface IProps {
  appPage: IAppPageState;
  assemblyPage: IAssemblyPageState;
  dispatch: any;
}
type ActiveKeys = 'paramPool' | 'actionPool';
// type ActiveKeys = 'paramPool' | 'actionPool' | 'metaEnumsPool' | 'metaClassesPool';

const { Item } = Menu;

type SettingsState = {
  mode: 'inline';
  selectKey: ActiveKeys;
};

function Assembly(props: IProps) {
  const { appPage, assemblyPage, dispatch } = props;

  useEffect(() => {
    if (appPage.curProject == null) {
      dispatch({
        type: 'appPage/getProjects',
        callback: (projects: any) => {
          dispatch({
            type: 'appPage/intiProject',
            payload: { projectCode: projects[0].code },
          })
        }
      })
    }
  }, []);

  const menuMap: Record<string, React.ReactNode> = {
    paramPool: '环境设置',
    actionPool: '行为设置',
    // metaEnumsPool: '枚举设置',
    // metaClassesPool: '元对象设置',
  };
  const iconMap: Record<string, React.ReactNode> = {
    paramPool: '\ue642',
    actionPool: '\ue68c',
    // metaEnumsPool: '\uee66',
    // metaClassesPool: '\ue60f',
  };

  const [initConfig, setInitConfig] = useState<SettingsState>({
    mode: 'inline',
    selectKey: assemblyPage.activeKey ? assemblyPage.activeKey as ActiveKeys : 'paramPool',
  });


  const getMenu = () => {
    return Object.keys(menuMap).map((item) =>
      <Item key={item} style={{ paddingRight: 24 }}>
        <Row>
          <Col span={4} offset={5}><Iconfont className={styles.icon} code={iconMap[item]} /></Col>
          <Col span={8}>{menuMap[item]}</Col>
        </Row>
      </Item>);
  };

  const renderChildren = () => {
    const { selectKey } = initConfig;
    switch (selectKey) {
      case 'paramPool':
        return <ParamPool appPage={appPage} assemblyPage={assemblyPage} dispatch={dispatch} />;
      case 'actionPool':
        return <ActionPool appPage={appPage} assemblyPage={assemblyPage} dispatch={dispatch} />;
      default:
        return null;
    }
  };

  return (
    <div className={styles.content} >
      <div
        className={styles.main}
      >
        <div className={styles.leftMenu}>
          <Menu
            mode={initConfig.mode}
            selectedKeys={[initConfig.selectKey]}
            onClick={({ key }) => {
              dispatch({
                type: 'assemblyPage/setActiveKey',
                payload: key
              })
              setInitConfig({
                ...initConfig,
                selectKey: key as ActiveKeys,
              });
            }}
          >
            {getMenu()}
          </Menu>
        </div>
        <div className={styles.right}>
          <div className={styles.title}>
            <Row >
              <Col span={20}>{menuMap[initConfig.selectKey]}</Col>
              {/* <Col span={4} hidden={assemblyPage.activeKey == "paramPool"}> */}
              <Col span={4} >
                <Input placeholder="搜索"
                  prefix={<SearchOutlined style={{ fontSize: 18 }} />}
                  bordered={false}
                  defaultValue={assemblyPage.searchKey}
                  onChange={(e) => {
                    const inputValue = e.target.value;
                    dispatch({
                      type: 'assemblyPage/setSearchKey',
                      payload: inputValue
                    })
                  }} />
              </Col>
            </Row>
          </div>
          {/* <Divider dashed /> */}
          {renderChildren()}
        </div>
      </div>
    </div>
  );
};

export default dvaModel(Assembly);
