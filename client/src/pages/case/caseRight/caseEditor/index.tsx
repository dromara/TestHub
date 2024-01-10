import React, { memo, useEffect, useRef, useState } from 'react';
import classnames from 'classnames';
import { setLocaleData } from 'monaco-editor-nls';
import zh_CN from 'monaco-editor-nls/locale/zh-hans.json';
import { useTheme } from '@/utils/hooks';

import styles from './index.less';
import DraggableContainer from '@/components/DraggableContainer';
import { OSnow } from '@/utils';
import { OSType } from '@/utils/constants';
import { Button, Divider, Modal, Tooltip, message } from 'antd';
import Iconfont from '@/components/Iconfont';
import { ExecutionXmlReqDto, RuleResDto, TreeNodeResDto } from '@/typings';
import { ConsoleStatus, IConsoleIndo } from '@/typings/client';
import MonacoEditor from 'react-monaco-editor/lib/editor';
import { IAppPageState } from 'umi';
import CaseRunParam from '../caseRunParam';
import { delay } from 'lodash';
import { ExecutionResult } from '@/typings/server/execution';
import CaseResult from '../caseResult';
import i18n from '@/i18n';
import { useDebounceFn } from 'ahooks';

setLocaleData(zh_CN);

interface IProps {
  id: string | number;
  caseShowResult?: boolean;
  iconsole: IConsoleIndo<TreeNodeResDto>

  appPage: IAppPageState;
  dispatch: any;
}


function findNodeByKey(nodes: TreeNodeResDto[], key: string) {
  for (const node of nodes) {
    if (node.key === key) {
      return node; // 找到匹配的节点，返回它
    }
    if (node.children) {
      const foundNode = findNodeByKey(node.children, key); // 递归遍历子节点
      if (foundNode) {
        return foundNode; // 找到匹配的节点，返回它
      }
    }
  }
  return { name: key, key: key }; // 未找到匹配的节点，返回 null
}
function insertNodeByKey(nodes: TreeNodeResDto[], key: string, newNode: TreeNodeResDto): TreeNodeResDto[] {
  return nodes.map((node) => {
    if (node.key === key) {
      if (node.children) {
        const index = node.children.findIndex((node) => node.nodeType === 'CASE');
        if (index > -1) {
          node.children.splice(index, 0, newNode); // 将新节点插入到指定节点的 children 中
        } else {
          node.children.push(newNode);
        }
      } else {
        node.children = [newNode]; // 如果指定节点没有 children，创建一个新的 children 数组并插入新节点
      }
    } else if (node.children) {
      node.children = insertNodeByKey(node.children, key, newNode); // 递归处理子节点
    }
    return node;
  });
}

export default function caseEditorConsole(props: IProps) {
  const runParamRef = useRef();
  const { appPage, dispatch, iconsole } = props;
  const themeColor = useTheme();
  const [theme, setTheme] = useState(themeColor == 'dark' ? 'BlackTheme' : 'default');
  const [paramOpen, setParamOpen] = useState(false);
  const [isRunIng, setIsRunIng] = useState(false);
  const [ruleResDto, setRuleResDto] = useState<RuleResDto>();
  // const [caseShowResult, setCaseShowResult] = useState(true);
  const [caseShowResult, setCaseShowResult] = useState(appPage.caseShowResult);

  const volatileRef = useRef<any>(null);

  useEffect(() => {
    setCaseShowResult(appPage.caseShowResult);
  }, [appPage.caseShowResult]);

  const onSave = () => {
    const status = iconsole.status;
    //有变化才可以保存
    if (props.iconsole.nowFileContent != undefined && props.iconsole.nowFileContent !== props.iconsole.data.data?.fileContent) {
      dispatch({
        type: 'appPage/saveCaseFileContent',
        payload: {
          model: iconsole.status == ConsoleStatus.DRAFT ? "save" : "updata",
          code: iconsole.key,
          parentKey: iconsole.data.data?.treeId,
          project: appPage.curProject?.code,
          documentStr: props.iconsole.nowFileContent
        },
        callback: (ruleResDto: RuleResDto) => {
          if (status == ConsoleStatus.DRAFT) {
            let ruleTrees = appPage.curProject?.ruleTrees || [];
            const treeNode: TreeNodeResDto = { key: ruleResDto.code, nodeType: "CASE", parentKey: ruleResDto.treeId + "", name: ruleResDto.name, leaf: true };
            if (treeNode.parentKey !== "0") {
              //不是根
              ruleTrees = insertNodeByKey(ruleTrees, treeNode.parentKey, treeNode)
            } else {
              if (ruleTrees.length > 0) {
                const index = ruleTrees.findIndex((node) => node.nodeType === 'CASE');
                ruleTrees.splice(index, 0, treeNode);
              } else {
                ruleTrees.push(treeNode);
              }
            }
          }
        }
      })
    }
  };
  const format = () => {
    if ((props.iconsole.nowFileContent || props.iconsole.data.data?.fileContent || "").trim().length > 0) {
      dispatch({
        type: 'appPage/formatXml',
        payload: props.iconsole.nowFileContent || props.iconsole.data.data?.fileContent,
        callback: (documentStr: string) => {
          monacoEditorChange(documentStr);
        }
      })
    }
  };


  const onRunParam = (flag: boolean) => {
    if (paramOpen || isRunIng) {
      return message.error(i18n('case.message.runing'));
    }
    const text = props.iconsole.nowFileContent || props.iconsole.data.data?.fileContent;
    //先执行解析、再设置参数、再运行
    dispatch({
      type: 'appPage/parserXml',
      payload: {
        code: iconsole.key,
        projectCode: appPage.curProject?.code,
        documentStr: text
      },
      callback: (ruleResDto: RuleResDto) => {
        setRuleResDto(ruleResDto);
        if (flag) {
          setParamOpen(true);
        } else {
          if (iconsole.runXmlParam) {
            //参数不同 流程不同 进入重新设置
            // setParamOpen(true);
            //否则直接运行
            const runXmlParam = iconsole.runXmlParam;
            runXmlParam.documentStr = text;
            onExec(runXmlParam);
          } else {
            setParamOpen(true);
          }
        }
      }
    })



  };
  const onExec = (runXmlParam: ExecutionXmlReqDto) => {
    if (isRunIng) {
      return message.error(i18n('case.message.runing'));
    }
    setIsRunIng(true);
    if (caseShowResult == false) {
      dispatch({
        type: 'appPage/setCaseShowResult',
        payload: true,
      })
    }

    //执行
    dispatch({
      type: 'appPage/executionXml',
      payload: {
        key: iconsole.key,
        runXmlParam: runXmlParam
      },
      callback: (executionResult: ExecutionResult) => {
        // delay(() => {
        // console.log(JSON.stringify(executionResult));
        setIsRunIng(false);
        // }, 30000)
      }
    })
  };

  const optBtn = [
    /** 基础SQL命令 */
    [
      {
        name: i18n('case.tip.run') + (OSnow === OSType.WIN ? ' Ctrl + R' : ' ⌘ + R'),
        icon: '\ue623',
        onClick: () => {
          onRunParam(false);
        },
      },
      {
        name: i18n('case.tip.debug') + (OSnow === OSType.WIN ? ' Ctrl + D' : ' ⌘ + D'),
        icon: '\ue8e8',
        onClick: () => {
          onRunParam(true);
        },
      },
      {
        name: i18n('case.tip.save') + (OSnow === OSType.WIN ? ' Ctrl + S' : ' ⌘ + S'),
        icon: '\ue8c8',
        onClick: () => { onSave() },
      },
      {
        name: i18n('case.tip.format') + (OSnow === OSType.WIN ? ' Ctrl + L' : ' ⌘ + L'),
        icon: '\ue60d',
        onClick: () => { format(); },
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
          <Tooltip key={index} placement="bottom" title={item.name}>
            <Iconfont
              code={item.icon}
              className={styles.icon}
              onClick={item.onClick}
            />
          </Tooltip>
        ));
      } else {
        tmpDom = (optList || []).map((item: any, index) => (
          <Button
            key={index}
            type="link"
            onClick={item.onClick}
            className={styles['ai-btn']}
          >
            {item.name}
          </Button>
        ));
      }
      tmpDom.push(<Divider key={'divider'} type="vertical" />);
      dom.push([...tmpDom]);
    }
    return dom;
  };


  const { run } = useDebounceFn(
    (text: string) => {
      dispatch({
        type: 'appPage/setNowFileContent',
        payload: {
          key: iconsole.key,
          text: text
        },
      })
    },
    {
      wait: 500,
    },
  );
  const monacoEditorChange = (text: string) => {
    run(text);
  };

  useEffect(() => {
    setTheme(themeColor == 'dark' ? 'BlackTheme' : 'default');
  }, [themeColor]);
  return (
    <>

      <DraggableContainer
        className={classnames(styles.databaseQuery)}
        callback={() => {
          dispatch({
            type: 'appPage/setCaseShowResult',
            payload: true,
          })
        }}
        showLine={true}
        direction="line"
        volatileDom={{
          volatileRef: volatileRef,
          volatileIndex: 1,
        }}
      >
        <div className={styles.console}>
          <div className={styles.operatingArea}>
            <div className={styles.left}>{renderOptBtn()}</div>
            <div className={styles.right}>
              <span>{i18n('case.text.dir')}: {props.iconsole.data.data?.treeId == 0 ? i18n('case.text.root') : findNodeByKey(appPage.curProject?.ruleTrees || [], props.iconsole.data.data?.treeId + "").name}</span>
              <span>{i18n('case.text.code')}: {props.iconsole.data.data?.code}</span>
              {
                props.iconsole.status == ConsoleStatus.DRAFT &&
                <span>{i18n('case.text.unsaved')}</span>
              }
            </div>
          </div>
          <div className={styles.monacoEditor}>
            <MonacoEditor
              // className={styles.monacoEditor}
              value={props.iconsole.nowFileContent || props.iconsole.data.data?.fileContent}//有草稿先展示草稿
              theme={theme}
              language="casexml"
              onChange={(text) => {
                monacoEditorChange(text);
              }}
              options={{
                quickSuggestions: true,
                selectOnLineNumbers: true,
                fixedOverflowWidgets: true,
                matchBrackets: 'near',
                foldingStrategy: 'indentation',
                scrollBeyondLastLine: false, // 设置编辑器是否可以滚动到最后一行之后
                automaticLayout: true, // 自动布局
                overviewRulerLanes: 0,
                bracketPairColorization: {
                  enabled: true,
                },
                scrollbar: {
                  // vertical: 'hidden', // 关闭垂直滚动条
                  // horizontal: 'hidden', // 关闭水平滚动条

                  verticalScrollbarSize: 6,
                  horizontalScrollbarSize: 6,
                  verticalSliderSize: 6,
                  horizontalSliderSize: 6,
                  verticalHasArrows: false,
                  horizontalHasArrows: false,
                  arrowSize: 0,
                  useShadows: true,
                },
              }}
              editorDidMount={(editor, monaco) => {
                // 获取编辑器实例并设置快捷键
                editor.addAction({
                  id: 'save',
                  label: i18n('case.tip.save'),
                  keybindings: [monaco.KeyMod.CtrlCmd | monaco.KeyCode.KeyS],
                  run: () => {
                    onSave();
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
                  // keybindings: [monaco.KeyMod.chord(monaco.KeyMod.CtrlCmd | monaco.KeyMod.Alt, monaco.KeyCode.KeyL)],
                  run: () => {
                    format();
                  },
                });
              }}
            />
          </div>
        </div>
        <div
          ref={volatileRef}
          style={{ display: caseShowResult ? 'block' : 'none' }}
          className={styles.searchResult}
        >
          <CaseResult dispatch={dispatch} appPage={appPage} iconsole={iconsole} isRunIng={isRunIng} />
        </div>
      </DraggableContainer >

      {paramOpen && (
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
                const temp = iconsole;
                temp.runXmlParam = res.data;
                temp.runXmlParam.ruleCode = iconsole.key;
                temp.runXmlParam.projectCode = appPage.curProject?.code;
                temp.runXmlParam.documentStr = (props.iconsole.nowFileContent || props.iconsole.data.data?.fileContent)
                dispatch({
                  type: 'appPage/setCaseConsole',
                  payload: temp
                })
                onExec(temp.runXmlParam);
                setParamOpen(false);
              }

              return res.flag;
            }
          }}
          onCancel={() => { setParamOpen(false); setIsRunIng(false) }}
          maskClosable={true}
          okText={i18n('case.button.ok')}
          cancelText={i18n('case.button.cancel')}
        >
          <CaseRunParam rule={ruleResDto} project={appPage.curProject} ref={runParamRef} />
        </Modal >
      )
      }
    </>
  );
};
