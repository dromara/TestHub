import React, { useEffect, useRef, useState } from 'react';
import styles from './index.less';
import './index.less';
import classnames from 'classnames';
import Iconfont from '@/components/Iconfont';
import DraggableContainer from '@/components/DraggableContainer';
import { IAppPageState } from '@/models/appPage';
import { connect } from 'umi';
import CaseLeft from './caseLeft';
import CaseRight from './caseRight';
import { TreeNodeResDto } from '@/typings';
import { IConsoleIndo } from '@/typings/client';
import i18n from '@/i18n';
const dvaModel = connect(
  ({ appPage }: { appPage: IAppPageState }) => ({
    appPage: appPage
  }),
);

interface IProps {
  appPage: IAppPageState;
  dispatch: any;
}

function CasePage(props: IProps) {
  const { appPage, dispatch } = props;
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

  const [openDropdown, setOpenDropdown] = useState(false);
  const [isUnfold, setIsUnfold] = useState(true);
  const volatileRef = useRef<any>();
  const [consoles, setConsoles] = useState<IConsoleIndo<TreeNodeResDto>[]>(appPage.caseConsoles);

  useEffect(() => {
    setConsoles(appPage.caseConsoles);
  }, [appPage.caseConsoles]);

  const closeDropdownFn = () => {
    setOpenDropdown(false);
  };

  useEffect(() => {
    if (openDropdown) {
      document.documentElement.addEventListener('click', closeDropdownFn);
    }
    return () => {
      document.documentElement.removeEventListener('click', closeDropdownFn);
    };
  }, [openDropdown]);

  const moveLeftAside = () => {
    if (volatileRef.current) {
      if (volatileRef.current.offsetWidth === 0) {
        volatileRef.current.style.width = '250px';
        setIsUnfold(true);
      } else {
        volatileRef.current.style.width = '0px';
        setIsUnfold(false);
      }
    }
  };

  const callback = () => {
    // monacoEditorExternalList[activeKey!] && monacoEditorExternalList[activeKey!].layout();
  };

  return <>
    <DraggableContainer className={classnames(styles.box)} callback={callback} volatileDom={{ volatileRef, volatileIndex: 0 }} >
      <div ref={volatileRef} className={styles.asideBox}>
        <CaseLeft appPage={appPage} dispatch={dispatch} />
      </div>
      <div className={styles.mainCase}>
        <CaseRight appPage={appPage} dispatch={dispatch} />
        <div className={styles.footer}>
          <div className={classnames({ [styles.reversalIconBox]: isUnfold }, styles.iconBox)} onClick={moveLeftAside}>
            <Iconfont code='&#xe6b5;' />
          </div>
          {
            !!consoles.length &&
            <div onClick={() => {
              dispatch({
                type: 'appPage/setCaseShowResult',
                payload: !appPage.caseShowResult,
              })
            }} className={classnames(styles.commandSearchResult, { [styles.unfoldSearchResult]: appPage.caseShowResult })}>
              {i18n('case.button.executeResult')}
              <Iconfont code='&#xe6b5;' />
            </div>
          }
        </div>
      </div>
    </DraggableContainer>
  </>
};





export default dvaModel(CasePage);
