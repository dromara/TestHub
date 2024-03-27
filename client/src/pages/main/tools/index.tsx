import React, { useEffect, useState } from 'react';
import classnames from 'classnames';
import styles from './index.less';
import { IHttpPageState } from '@/models/httpPage';
import BasePage from '@/components/base/BasePage';
import ToolsLeft from './components/ToolsLeft';
import ToolsRight from './components/ToolsRight';
import Empty from '@/components/base/Empty';
import ToolFileSearch from './components/Tools/FileSearch';

interface IProps {
  httpPage: IHttpPageState;
  dispatch: any;
}

interface ToolProps {
  code: string;
  name: string;
  icon: string;
  container: React.ReactNode;
}

const toolsTree = [
  {
    category: '文件类',
    tools: [{ code: 'fileSearch', name: '内容检索', icon: '\ue64e', container: <ToolFileSearch /> }],
  },
  // {
  //   category: '格式类',
  //   tools: [
  //     { code: 'xml', name: 'XML', icon: '\ue66e', item: <> 待开发 </> },
  //     { code: 'json', name: 'JSON', icon: '\ue6b3', item: <> 待开发 </> },
  //     // { code: 'sqlformat', name: 'SQL Format', icon: '\ue6b6', item: <> 待开发 </> },
  //   ],
  // },
];

function conversion() {
  const tools: ToolProps[] = [];
  toolsTree.map((item) => {
    item.tools.map((tool) => {
      tools.push(tool);
    });
  });
  return tools;
}
const tools = conversion();

function Tools() {
  const [leftShow, setLeftShow] = useState(true);
  // const [toolCode, setToolCode] = useState<string | null>(null);
  const [toolCode, setToolCode] = useState<string | null>('fileSearch');

  return (
    <BasePage
      className={styles.httpPage}
      left={
        <ToolsLeft
          toolCode={toolCode}
          toolsTree={toolsTree}
          setToolCode={(code: string) => {
            setToolCode(code);
          }}
        />
      }
      rigth={
        toolCode != null ? (
          tools.map((tool) => {
            return (
              <ToolsRight
                key={tool.code}
                hidden={tool.code != toolCode}
                toolCode={tool.code}
                toolName={tool.name}
                itemContainer={tool.container}
              />
            );
          })
        ) : (
          <Empty />
        )
      }
      leftShow={leftShow}
      resultHide={true}
      changeLeftShow={() => {
        setLeftShow(!leftShow);
      }}
    />
  );
}

export default Tools;
