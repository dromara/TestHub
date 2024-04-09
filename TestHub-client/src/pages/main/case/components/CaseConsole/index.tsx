import React, { memo, useEffect, useRef, useState } from 'react';
import styles from './index.less';
import classnames from 'classnames';
import { ConsoleInfo, ConsoleStatus, ExecutionResult, ExecutionXmlReqDto, TreeNodeResDto } from '@/typings';
import Draggable, { ModelType } from '@/components/base/Draggable';
import Iconfont from '@/components/base/Iconfont';
import { CasePageData, CasePageInfo, ICasePageState } from '@/models/casePage';
import i18n from '@/i18n';
import MyMonacoEditor, { IExportRefFunction } from '@/components/TestHub/MonacoEditor/MyMonacoEditor';
import { getOS } from '@/utils';
import { OSType } from '@/constants';
import { Button, Divider, Modal, Tooltip, message } from 'antd';
import { useDebounceFn } from 'ahooks';
import { getCurrentProject } from '@/utils/localStorage';
import { RuleResDto } from '@/typings/server/case';
import { CaseNodeType } from '../..';
import CaseRunParam, { ICaseRunParamRef } from '../caseRunParam';
import StateIndicator from '@/components/base/StateIndicator';
import CaseResult from '../caseResult';
import { IAppPageState } from '@/models/appPage';

interface IProps {
  caseCode: string;
  consoleInfo: ConsoleInfo<CasePageData, CasePageInfo>;
  casePage: ICasePageState;
  appPage: IAppPageState;
  dispatch: any;
}

export default function CaseConsole(props: IProps) {
  const runParamRef = useRef<ICaseRunParamRef>();
  const [paramOpen, setParamOpen] = useState(false);
  const [isRunIng, setIsRunIng] = useState(false);
  const [ruleResDto, setRuleResDto] = useState<RuleResDto>();
  const editorRef = useRef<IExportRefFunction>(null);
  const { caseCode, appPage, casePage, dispatch, consoleInfo } = props;
  const [show, setShow] = useState(false);
  const [firstSize, setFirstSize] = useState(100);
  const [data, setData] = useState<CasePageData>(consoleInfo.data);
  const [caseShowResult, setCaseShowResult] = useState(casePage.showResult);
  useEffect(() => {
    dispatchData(data);
  }, [data]);
  function dispatchData(newData: CasePageData) {
    dispatch({
      type: 'casePage/setConsoleData',
      payload: {
        key: consoleInfo.key,
        data: newData,
      },
    });
  }
  useEffect(() => {
    if (show != casePage.showResult) {
      dispatch({
        type: 'casePage/setShowResult',
        payload: show,
      });
    }
  }, [show]);
  useEffect(() => {
    if (show != casePage.showResult) {
      setShow(casePage.showResult);
    }
  }, [casePage.showResult]);

  //格式化
  const format = () => {
    if ((consoleInfo.data.fileContent || '').trim().length > 0) {
      dispatch({
        type: 'casePage/formatXml',
        payload: consoleInfo.data.fileContent,
        callback: (documentStr: string) => {
          myDebounceFn(documentStr, (text) => {
            data.fileContent = text;
            setData({ ...data });
            editorRef.current?.setValue(text, 'cover');
          });
        },
      });
    }
  };
  //保存
  const onSave = (flag) => {
    const status = props.consoleInfo.status;
    //有变化才可以保存
    if (flag || status != ConsoleStatus.SAVED) {
      dispatch({
        type: 'casePage/saveCaseFileContent',
        payload: {
          model: status == ConsoleStatus.UNTRACKED ? 'save' : 'updata',
          code: caseCode,
          parentKey: data?.treeId,
          project: getCurrentProject()?.code,
          documentStr: data.fileContent,
        },
        callback: (ruleResDto: RuleResDto) => {
          if (ruleResDto != undefined) {
            dispatch({
              type: 'casePage/saveConsoleData',
              payload: {
                key: consoleInfo.key,
                data: ruleResDto,
              },
            });
            if (status == ConsoleStatus.UNTRACKED) {
              if (status == ConsoleStatus.UNTRACKED) {
                const treeNode: TreeNodeResDto<any> = {
                  index: consoleInfo.key,
                  isFolder: false,
                  children: [],
                  data: {
                    key: ruleResDto.code,
                    parentKey: ruleResDto.treeId + '',
                    name: ruleResDto.name ? ruleResDto.name : '',
                    nodeType: CaseNodeType.CASE,
                    info: ruleResDto,
                  },
                  canRename: false,
                  canMove: false,
                };
                dispatch({
                  type: 'casePage/putTree',
                  payload: {
                    oldParentId: ruleResDto.treeId,
                    parentId: ruleResDto.treeId,
                    node: treeNode,
                  },
                });
              }
            }
          }
        },
      });
    }
  };
  //运行参数
  const onRunParam = (flag: boolean) => {
    if (paramOpen || isRunIng) {
      return message.error(i18n('case.message.runing'));
    }
    //先执行解析、再设置参数、再运行
    dispatch({
      type: 'casePage/parserXml',
      payload: {
        code: caseCode,
        projectCode: getCurrentProject()?.code,
        documentStr: consoleInfo.data.fileContent,
      },
      callback: (ruleResDto: RuleResDto) => {
        setRuleResDto(ruleResDto);
        if (flag) {
          setParamOpen(true);
        } else {
          if (consoleInfo.page.runXmlParam) {
            //参数不同 流程不同 进入重新设置
            // setParamOpen(true);
            //否则直接运行
            const runXmlParam = consoleInfo.page.runXmlParam;
            runXmlParam.documentStr = consoleInfo.data.fileContent;
            onExec(runXmlParam);
          } else {
            setParamOpen(true);
          }
        }
      },
    });
  };
  //运行结果
  const onExec = (runXmlParam: ExecutionXmlReqDto) => {
    if (isRunIng) {
      return message.error(i18n('case.message.runing'));
    }
    setIsRunIng(true);
    if (caseShowResult == false) {
      dispatch({
        type: 'casePage/setShowResult',
        payload: true,
      });
    }

    //执行
    dispatch({
      type: 'casePage/executionXml',
      payload: {
        key: caseCode,
        runXmlParam: runXmlParam,
      },
      callback: (executionResult: ExecutionResult) => {
        setIsRunIng(false);
      },
    });
  };

  const { run: myDebounceFn } = useDebounceFn(
    (data: any, callback) => {
      callback(data);
    },
    {
      wait: 100,
    },
  );

  /** 基础SQL命令 */
  const optBtn = [
    [
      {
        name: i18n('case.tip.run') + (getOS === OSType.WIN ? ' Ctrl + R' : ' ⌘ + R'),
        icon: '\ue623',
        onClick: () => {
          onRunParam(false);
        },
      },
      {
        name: i18n('case.tip.debug') + (getOS === OSType.WIN ? ' Ctrl + D' : ' ⌘ + D'),
        icon: '\ue8e8',
        onClick: () => {
          onRunParam(true);
        },
      },
      {
        name: i18n('case.tip.save') + (getOS === OSType.WIN ? ' Ctrl + S' : ' ⌘ + S'),
        icon: '\ue8c8',
        onClick: () => {
          onSave(false);
        },
      },
      {
        name: i18n('case.tip.format') + (getOS === OSType.WIN ? ' Ctrl + L' : ' ⌘ + L'),
        icon: '\ue60d',
        onClick: () => {
          format();
        },
      },
    ],
  ];

  const renderOptBtn = () => {
    let dom = [];
    for (let i = 0; i < optBtn.length; i++) {
      const optList = optBtn[i];
      let tmpDom: Array<React.ReactNode> = [];
      if (i === 0) {
        tmpDom = (optList || []).map((item: any, index) => (
          <div key={index} className={styles.iconDiv}>
            <Tooltip key={index} placement="bottom" title={item.name}>
              <Iconfont code={item.icon} className={styles.icon} onClick={item.onClick} />
            </Tooltip>
          </div>
        ));
      } else {
        tmpDom = (optList || []).map((item: any, index) => (
          <div key={index} className={styles.iconDiv}>
            <Button key={index} type="link" onClick={item.onClick} className={styles.icon}>
              {item.name}
            </Button>
          </div>
        ));
      }
      tmpDom.push(<Divider key={'divider'} type="vertical" style={{ height: 30 }} />);
      dom.push([...tmpDom]);
    }
    return dom;
  };

  return (
    <>
      <Draggable
        className={classnames(styles.caseConsolePage)}
        model={ModelType.SECOND}
        show={show}
        defaultSize={show ? 60 : 100}
        minSize={50}
        maxSize={100}
        onResize={(firstSize: number, secondSize: number) => {
          setFirstSize(firstSize);
          if (secondSize == 0 || secondSize < 1) {
            if (show) {
              setShow(false);
            }
          } else {
            if (!show) {
              setShow(true);
            }
          }
        }}
      >
        <div className={styles.caseConsoleLeft}>
          <div className={styles.console}>
            <div className={styles.operatingArea}>
              <div className={styles.left}>{renderOptBtn()}</div>
              <div className={styles.right}>
                <span>
                  {i18n('case.text.dir')}:{' '}
                  {consoleInfo.data.treeId == 0 ? '根' : casePage.trees[consoleInfo.data.treeId + '']['data']['name']}
                  {/* {props.iconsole.data.data?.treeId == 0
                    ? i18n('case.text.root')
                    : findNodeByKey(appPage.curProject?.ruleTrees || [], props.iconsole.data.data?.treeId + '').name} */}
                </span>
                <span>
                  {i18n('case.text.code')}: {caseCode}
                </span>
                {props.consoleInfo.status == ConsoleStatus.UNTRACKED && <span>{i18n('case.text.unsaved')}</span>}
                {props.consoleInfo.status == ConsoleStatus.CHANGE && <span>{i18n('case.text.change')}</span>}
                {props.consoleInfo.status == ConsoleStatus.SAVED && <span>{i18n('case.text.saved')}</span>}
              </div>
            </div>
            <div className={styles.monacoEditor}>
              <MyMonacoEditor
                ref={editorRef}
                language={'casexml'}
                defaultValue={consoleInfo.data.fileContent}
                onChange={(text) => {
                  myDebounceFn(text, (dataOld) => {
                    data.fileContent = dataOld;
                    setData({ ...data });
                  });
                }}
                options={{
                  minimap: { enabled: firstSize > 80 ? true : false },
                }}
                shortcutKey={(editor, monaco) => {
                  // 获取编辑器实例并设置快捷键
                  editor.addAction({
                    id: 'save',
                    label: i18n('case.tip.save'),
                    keybindings: [monaco.KeyMod.CtrlCmd | monaco.KeyCode.KeyS],
                    run: () => {
                      onSave(true);
                    },
                  });
                  editor.addAction({
                    id: 'run',
                    label: i18n('case.tip.run'),
                    keybindings: [monaco.KeyMod.CtrlCmd | monaco.KeyCode.KeyR],
                    run: () => {
                      onRunParam(false);
                    },
                  });
                  editor.addAction({
                    id: 'debug',
                    label: i18n('case.tip.debug'),
                    keybindings: [monaco.KeyMod.CtrlCmd | monaco.KeyCode.KeyD],
                    run: () => {
                      onRunParam(true);
                    },
                  });
                  editor.addAction({
                    id: 'format',
                    label: i18n('case.tip.format'),
                    keybindings: [monaco.KeyMod.CtrlCmd | monaco.KeyCode.KeyL],
                    run: () => {
                      format();
                    },
                  });
                }}
                addAction={[
                  {
                    id: 'format',
                    label: i18n('case.tip.format'),
                    action: (selectedText: string) => {
                      format();
                    },
                  },
                  {
                    id: 'save',
                    label: i18n('case.tip.save'),
                    action: (selectedText: string) => {
                      onSave(true);
                    },
                  },
                  {
                    id: 'run',
                    label: i18n('case.tip.run'),
                    action: (selectedText: string) => {
                      onRunParam(false);
                    },
                  },
                  {
                    id: 'debug',
                    label: i18n('case.tip.debug'),
                    action: (selectedText: string) => {
                      onRunParam(true);
                    },
                  },
                ]}
              />
            </div>
          </div>
        </div>
        <div className={classnames([styles.caseConsoleRight])}>
          <CaseResult isRunIng={isRunIng} consoleInfo={consoleInfo} />
          {/* {consoleInfo?.page?.executionResult != undefined && <>{JSON.stringify(consoleInfo?.page?.executionResult)}</>} */}
          {/* {consoleInfo?.page?.executionResult == undefined && <StateIndicator state="empty" />} */}
        </div>
      </Draggable>

      {paramOpen && ruleResDto && (
        <Modal
          title={i18n('case.message.executionConfigMessage')}
          width={560}
          open={paramOpen}
          onOk={async () => {
            // console.log("nowFileContent\n" + props.iconsole.nowFileContent)
            // console.log("fileContent\n" + props.iconsole.data.data?.fileContent)
            if (runParamRef.current != undefined) {
              const res = await runParamRef.current.getData();
              if (res.flag) {
                console.log(res.data);
                const runXmlParam: ExecutionXmlReqDto = res.data;
                runXmlParam.ruleCode = caseCode;
                runXmlParam.projectCode = getCurrentProject()?.code;
                runXmlParam.documentStr = data.fileContent;
                dispatch({
                  type: 'casePage/setConsolePage',
                  payload: {
                    key: caseCode,
                    page: { runXmlParam: runXmlParam, executionResult: consoleInfo.page.executionResult },
                  },
                });
                onExec(runXmlParam);
                setParamOpen(false);
              }

              return res.flag;
            }
          }}
          onCancel={() => {
            setParamOpen(false);
            setIsRunIng(false);
          }}
          maskClosable={true}
          okText={i18n('case.button.ok')}
          cancelText={i18n('case.button.cancel')}
        >
          <CaseRunParam rule={ruleResDto} project={appPage.curProject} ref={runParamRef} />
        </Modal>
      )}
    </>
  );
}
