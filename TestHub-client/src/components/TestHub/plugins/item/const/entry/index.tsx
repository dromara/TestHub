import React, { forwardRef, useEffect, useImperativeHandle, useState } from 'react';

import { RuleActionResDto } from '@/typings';
import { Descriptions } from 'antd';
import MyMonacoEditor from '@/components/TestHub/MonacoEditor/MyMonacoEditor';
interface IProps {
  data: RuleActionResDto;
}

const Const = forwardRef((props: IProps, ref) => {
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

  return (
    <>
      <Descriptions column={1} layout="vertical">
        <Descriptions.Item
          label={
            <label>
              <span style={{ color: '#f00' }}>* </span>文本
            </label>
          }
        >
          <div style={{ height: 200, width: '100%' }}>
            <MyMonacoEditor
              language={'freemarker2'}
              defaultValue={data.extraInto.bound}
              onChange={(text) => {
                const newDatas = data;
                newDatas.extraInto.bound = text;
                setData(newDatas);
              }}
            />
          </div>
        </Descriptions.Item>
      </Descriptions>
    </>
  );
});

export default Const;
