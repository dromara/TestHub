import React, { useEffect, useState } from 'react';
import classnames from 'classnames';
import styles from './index.less';
import BasePage from '@/components/base/BasePage';
import { IAppPageState } from '@/models/appPage';
import { connect } from 'umi';
import MetaLeft from './components/MetaLeft';
import MetaRight from './components/MetaRight';
import ParamPool from './components/container/paramPool/index';
import { getCurrentProject } from '@/utils/localStorage';
import ActionPool from './components/container/actionPool';


interface IProps {
  appPage: IAppPageState;
  dispatch: any;
}

interface MetaProps {
  code: string;
  name: string;
  icon: string;
  container: React.ReactNode;
}



const dvaModel = connect(({ appPage }: { appPage: IAppPageState }) => ({
  appPage: appPage,
}));


function Metas(props: IProps) {
  const { appPage, dispatch } = props;
  const [leftShow, setLeftShow] = useState(true);

  const metaMenus = [{ code: 'paramPool', name: '环境设置', icon: '\ue642', container: <ParamPool appPage={appPage} searchKey={appPage.searchKey} dispatch={dispatch} /> },
  { code: 'actionPool', name: '行为设置', icon: '\ue68c', container: <ActionPool appPage={appPage} searchKey={appPage.searchKey} dispatch={dispatch} /> },
    // { code: 'metaEnumsPool', name: '枚举设置', icon: '\uee66' },
    // { code: 'metaClassesPool', name: '元对象设置', icon: '\ue60f' }

  ]

  const refresh = (flag: boolean) => {
    //现在不考虑 没有切换当前项目的时候
    if (flag) {
      dispatch({
        type: 'appPage/intiProject',
        payload: { projectCode: getCurrentProject()?.code },
      });
    } else if (!appPage.isLoaded) {
      dispatch({
        type: 'appPage/intiProject',
        payload: { projectCode: getCurrentProject()?.code },
      });
    }
  };
  useEffect(() => {
    refresh(false);
  }, []);

  return (
    <BasePage
      className={styles.metaPage}
      left={
        <MetaLeft metaMenus={metaMenus} activeKey={appPage.activeKey} setActiveCode={(activeKey) => {
          dispatch({
            type: 'appPage/setActiveKey',
            payload: activeKey
          })
        }}
        />
      }
      rigth={
        metaMenus.map((meta) => {
          return (
            <MetaRight
              key={meta.code}
              hidden={meta.code != appPage.activeKey}
              activeKey={meta.code}
              titleName={meta.name}
              searchKey={appPage.searchKey}
              itemContainer={meta.container}
              setSearchKey={(searchKey) => {
                dispatch({
                  type: 'appPage/setSearchKey',
                  payload: searchKey
                })
              }}
            />
          );
        })
      }
      leftShow={leftShow}
      resultHide={true}
      changeLeftShow={() => {
        setLeftShow(!leftShow);
      }}
    />
  );
}

export default dvaModel(Metas);

