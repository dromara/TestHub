import React, { forwardRef, useEffect, useImperativeHandle, useState } from 'react';

import { RuleActionResDto } from '@/typings';
import { useTheme } from '@/utils/hooks';
import { Descriptions } from 'antd';
import MonacoEditor from 'react-monaco-editor';
import MyMonacoEditor from '@/components/TestHub/MonacoEditor/MyMonacoEditor';
interface IProps {
  data: RuleActionResDto;
}

const App = forwardRef((props: IProps, ref) => {
  const [data, setData] = useState(props.data.extraInto ? props.data : { ...props.data, extraInto: { bound: '' } });
  useImperativeHandle(ref, () => ({
    getData: async () => {
      if (data.extraInto.bound == '') {
        return { flag: false, data: data };
      }
      console.log(data.extraInto.bound);
      return { flag: true, data: { bound: data.extraInto.bound } };
    },
  }));

  const themeColor = useTheme();
  const [theme, setTheme] = useState(themeColor == 'dark' ? 'BlackTheme' : 'default');
  useEffect(() => {
    setTheme(themeColor == 'dark' ? 'BlackTheme' : 'default');
  }, [themeColor]);
  return (
    <>
      <Descriptions column={1} layout="vertical">
        <Descriptions.Item
          label={
            <label>
              <span style={{ color: '#f00' }}>* </span>DataPack请求
            </label>
          }
        >
          <MyMonacoEditor
            height={200}
            language="datapack"
            defaultValue={data.extraInto.bound}
            onChange={(text) => {
              const newDatas = data;
              newDatas.extraInto.bound = text;
              setData(newDatas);
            }}
          />
        </Descriptions.Item>
      </Descriptions>
    </>
  );
});

export default App;
