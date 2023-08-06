import { PageContainer, ProFormSelect } from '@ant-design/pro-components';
import { Col, Card, Row, Select } from 'antd';
import React, { useState } from 'react';
import { MonacoDiffEditor } from 'react-monaco-editor';
import styles from './index.less';
import './index.less';

const Compar: React.FC = () => {
  const [language, setLanguage] = useState<string>('json');
  const [code1, setCode1] = useState<string>('');
  const [code2, setCode2] = useState<string>('');

  return (
    <div className={styles.comparBox}>
      <Row>
        <Col>
          <Select
            bordered={false}
            style={{ width: 120, paddingLeft: 20 }}
            onChange={(value: string) => {
              setLanguage(value);
            }}
            defaultValue={language}
            options={[
              {
                value: 'json',
                label: 'JSON',
              },
              {
                value: 'text',
                label: 'TEXT',
              },
              {
                value: 'xml',
                label: 'XML',
              },
              {
                value: 'yaml',
                label: 'Yaml',
              },
              {
                value: 'sql',
                label: 'SQL',
              },
              {
                value: 'java',
                label: 'JAVA',
              },
              {
                value: 'shell',
                label: 'Shell',
              },
              {
                value: 'javascript',
                label: 'JavaScript',
              },
            ]}
          />
        </Col>
      </Row>
      <MonacoDiffEditor
        width="100%"
        height="100%"
        language={language}
        original={code1}

        value={code2}
        options={{
          originalEditable: true,
          selectOnLineNumbers: true,
          matchBrackets: 'near',
          minimap: {
            // 关闭代码缩略图
            enabled: false, // 是否启用预览图
          },
          scrollBeyondLastLine: false, // 设置编辑器是否可以滚动到最后一行之后
          automaticLayout: true, // 自动布局
        }}
      />
    </div>
  );
};
export default Compar;
