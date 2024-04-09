import React, { useEffect, useState } from 'react';
import classnames from 'classnames';
import styles from './index.less';
import HttpLeft from './components/HttpLeft';
import HttpRigth from './components/HttpRigth';
import BasePage from '@/components/base/BasePage';
import { connect } from 'umi';
import { IHttpPageState } from '@/models/httpPage';
import { getCurrentProject } from '@/utils/localStorage';

const dvaModel = connect(({ httpPage }: { httpPage: IHttpPageState }) => ({
  httpPage: httpPage,
}));

interface IProps {
  httpPage: IHttpPageState;
  dispatch: any;
}

export enum HttpNodeType {
  DIR = 'DIR',
  API = 'API',
}

const recognizeIcon = (nodeType: string) => {
  if (nodeType === HttpNodeType.DIR) {
    return '\ueac5';
  } else {
    return '\ue63b';
  }
};

function Http(props: IProps) {
  const [leftShow, setLeftShow] = useState(true);
  const { httpPage, dispatch } = props;
  const currentProject = getCurrentProject();
  const refresh = (flag: boolean) => {
    //现在不考虑 没有切换当前项目的时候
    if (flag) {
      dispatch({
        type: 'httpPage/loadTrees',
        payload: { projectCode: currentProject?.code },
      });
    } else if (!httpPage.isLoaded) {
      dispatch({
        type: 'httpPage/loadTrees',
        payload: { projectCode: currentProject?.code },
      });
    }
  };

  useEffect(() => {
    refresh(false);
  }, []);
  return (
    <BasePage
      className={styles.httpPage}
      left={
        <HttpLeft
          dispatch={dispatch}
          httpPage={httpPage}
          recognizeIcon={recognizeIcon}
          refresh={() => {
            refresh(true);
          }}
        />
      }
      rigth={<HttpRigth dispatch={dispatch} httpPage={httpPage} icon={'\ue63b'} />}
      leftShow={leftShow}
      resultShow={httpPage.showResult}
      changeLeftShow={() => {
        setLeftShow(!leftShow);
      }}
      changeResultShow={() => {
        dispatch({
          type: 'httpPage/setShowResult',
          payload: !httpPage.showResult,
        });
      }}
    />
  );
}

export default dvaModel(Http);
