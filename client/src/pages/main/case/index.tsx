import React, { useEffect, useRef, useState } from 'react';

import classnames from 'classnames';

import styles from './index.less';
import BasePage from '@/components/base/BasePage';
import CaseLeft from './components/CaseLeft';
import { getCurrentProject } from '@/utils/localStorage';
import { connect } from 'umi';
import { ICasePageState } from '@/models/casePage';
import CaseRigth from './components/CaseRight';

const dvaModel = connect(({ casePage }: { casePage: ICasePageState }) => ({
  casePage: casePage,
}));

interface IProps {
  casePage: ICasePageState;
  dispatch: any;
}

export enum CaseNodeType {
  DIR = 'DIR',
  CASE = 'CASE',
}

function Case(props: IProps) {
  const [leftShow, setLeftShow] = useState(true);
  const { casePage, dispatch } = props;
  const currentProject = getCurrentProject();
  const refresh = (flag: boolean) => {
    //现在不考虑 没有切换当前项目的时候
    if (flag) {
      dispatch({
        type: 'casePage/loadTrees',
        payload: { projectCode: currentProject?.code },
      });
    } else if (!casePage.isLoaded) {
      dispatch({
        type: 'casePage/loadTrees',
        payload: { projectCode: currentProject?.code },
      });
    }
  };
  useEffect(() => {
    refresh(false);
  }, []);

  const recognizeIcon = (nodeType: string) => {
    if (nodeType === CaseNodeType.DIR) {
      return '\ueac5';
    } else {
      return '\ue945';
    }
  };
  return (
    <BasePage
      className={styles.casePage}
      left={
        <CaseLeft
          dispatch={dispatch}
          casePage={casePage}
          recognizeIcon={recognizeIcon}
          refresh={() => {
            refresh(true);
          }}
        />
      }
      rigth={<CaseRigth dispatch={dispatch} casePage={casePage} icon={'\ue63b'} />}
      leftShow={leftShow}
      resultShow={casePage.showResult}
      changeLeftShow={() => {
        setLeftShow(!leftShow);
      }}
      changeResultShow={() => {
        dispatch({
          type: 'casePage/setShowResult',
          payload: !casePage.showResult,
        });
      }}
    />
  );
}

export default dvaModel(Case);
