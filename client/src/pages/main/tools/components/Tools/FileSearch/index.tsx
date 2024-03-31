import React, { useEffect, useRef, useState } from 'react';
import classnames from 'classnames';
import styles from './index.less';
import { InboxOutlined, SearchOutlined } from '@ant-design/icons';
import type { UploadFile, Upload, UploadProps } from 'antd';
// eslint-disable-next-line no-duplicate-imports
import { Button, Select, message } from 'antd';
import MyMonacoEditor, { IExportRefFunction } from '@/components/TestHub/MonacoEditor/MyMonacoEditor';
import Dragger from 'antd/es/upload/Dragger';
import { getStoreKeys, setStoreKeys } from './store';
import Iconfont from '@/components/base/Iconfont';

function ToolFileSearch() {
  const [fileList, setFileList] = useState<UploadFile[]>([]);
  const editorRef = useRef<IExportRefFunction>(null);
  const oldKeys = getStoreKeys();
  const [keys, setKeys] = useState<string[]>(oldKeys);
  const [character, setCharacter] = useState<string>('gb2312');

  useEffect(() => {
    setStoreKeys(keys);
  }, [keys]);

  function renderFile(fileName, fileContent) {
    const filteredList = [];
    let i = 1;
    fileContent.split('\n').forEach((line) => {
      // 去除空格和换行符
      const item = line.trim();
      if (item !== '') {
        keys.forEach((key) => {
          if (item.includes(key)) {
            filteredList.push(i + ' 行 ' + item);
            return; // 找到匹配项后跳出内层循环
          }
        });
      }
      i += 1;
    });
    let content = '';
    if (filteredList.length > 0) {
      content = '开始 --------------------- ' + fileName;
      filteredList.forEach((filtered) => {
        content = content + '\n\t' + filtered;
      });
      content = content + '\n' + '结束 --------------------- ' + fileName;
    } else {
      content = '未找到 --------------------- ' + fileName;
    }

    editorRef.current?.setValue(content + '\n', 'end');
  }
  const handleFileChange = (info) => {
    setFileList(info.fileList);
  };

  const readAsText = () => {
    for (let i = 0; i < fileList.length; i++) {
      const file = fileList[i];
      // console.log(file.originFileObj);
      const reader = new FileReader();
      reader.onload = (event) => {
        if (event != null && event.target != null) {
          renderFile(file.name, event.target.result);
        }
      };
      // reader.readAsText(file.originFileObj, 'gb2312');
      reader.readAsText(file.originFileObj, character);
    }
  };

  const search = () => {
    //清空上次的
    editorRef.current?.setValue('', 'cover');
    setTimeout(() => {
      //校验
      readAsText();
    }, 0);
  };

  const props: UploadProps = {
    name: 'file',
    accept: '.sql,.txt',
    multiple: true,
    onChange: handleFileChange,
    beforeUpload: (file) => {
      const fileType = file.type;
      const isAllowedFileType =
        fileType === 'text/plain' ||
        fileType === 'application/sql' ||
        file.name.endsWith('.sql') ||
        file.name.endsWith('.txt');
      if (!isAllowedFileType) {
        message.error('只能上传 SQL 文件或文本文件！');
      }
      return false; // 阻止默认的上传行为
    },
    showUploadList: true,
  };

  return (
    <div className={styles.sqlCheck}>
      <div className={styles.sqlCheckTop}>
        <div className={styles.title}>配置</div>
        <div className={styles.line}>
          <div className={styles.character}>
            <div className={styles.left}>
              <div className={styles.leftIcon}>
                <Iconfont size={16} code={'\ue61f'} />
              </div>
              <div className={styles.leftTitle}>字符集</div>
            </div>
            <div className={styles.right}>
              <Select
                className={styles.rightSelect}
                defaultValue={character}
                style={{ width: 100 }}
                onChange={(value) => {
                  setCharacter(value);
                }}
                options={[
                  { value: 'UTF-8', label: 'UTF-8' },
                  { value: 'GBK', label: 'GBK' },
                  { value: 'gb2312', label: 'GB2312' },
                  { value: 'disabled', label: 'ISO-8859-1' },
                ]}
              />
            </div>
          </div>
        </div>
        <div className={styles.title}>文件</div>
        <div className={styles.line}>
          <div className={styles.fileSelecter}>
            <Dragger {...props}>
              <p className="ant-upload-drag-icon">
                <InboxOutlined />
              </p>
              <p className="ant-upload-text">点击或将文件拖入此处</p>
              <p className="ant-upload-hint">只支持sql、txt文件</p>
            </Dragger>
          </div>
          {/* <div>{fileList.length}</div> */}
        </div>
        <div className={styles.title}>关键字</div>
        <div className={classnames(styles.line, styles.row)} style={{ width: '100%' }}>
          <div style={{ width: '90%' }}>
            <Select
              mode="tags"
              variant="filled"
              defaultValue={oldKeys}
              onChange={(value) => {
                setKeys(value);
              }}
              style={{ width: '100%', minHeight: 30 }}
            />
          </div>
          <div className={styles.search} style={{ width: '10%', height: '100%' }}>
            <Button style={{ height: '100%' }} className={styles.searchBtn} icon={<SearchOutlined />} onClick={search}>
              搜索
            </Button>
          </div>
        </div>
      </div>
      <div className={styles.sqlCheckBottom}>
        <div className={styles.title}>结果</div>
        <div className={styles.editor}>
          <MyMonacoEditor
            ref={editorRef}
            language="text"
            defaultValue={''}
            options={
              {
                // readOnly: true,
              }
            }
          />
        </div>
      </div>
    </div>
  );
}

export default ToolFileSearch;
