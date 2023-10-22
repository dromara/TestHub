import { TabsProps, Tabs } from 'antd';
import React, { forwardRef, useImperativeHandle, useRef, useState } from 'react';
import Body from './Body';
import Params from '../assembly/params';
import { HTTP } from './typings';
export type Props = {
  data: HTTP.HttpRequestResDto;
};



const RequestView = forwardRef((props: Props, ref) => {
  const headersRef = useRef();
  const paramsRef = useRef();
  const bodyRef = useRef();
  const restsRef = useRef();
  const [activeKey, setActiveKey] = useState('Body');
  const [request, setRequest] = useState<HTTP.HttpRequestResDto>(props.data);
  const onChange = (key: string) => {
    setActiveKey(key);
  };
  useImperativeHandle(ref, () => ({
    getData: async () => {
      const data = request;
      if (restsRef.current != undefined) {
        const res = await restsRef.current.getData();
        if (!res.flag) {
          return { flag: false, data: {} };
        }
        data.rests = res.data;
      }
      if (headersRef.current != undefined) {
        const res = await headersRef.current.getData();
        if (!res.flag) {
          return { flag: false, data: {} };
        }
        data.headers = res.data;
      }
      if (paramsRef.current != undefined) {
        const res = await paramsRef.current.getData();
        if (!res.flag) {
          return { flag: false, data: {} };
        }
        data.params = res.data;
      }
      if (bodyRef.current != undefined) {
        const res = await bodyRef.current.getData();
        if (!res.flag) {
          return { flag: false, data: {} };
        }
        data.body = res.data;
      }

      return { flag: true, data: data };
    },
  }));

  const items: TabsProps['items'] = [
    {
      key: 'Rests',
      label: `Rests`,
      children: <Params params={request.rests == undefined ? [] : request.rests} ref={restsRef} />,
    },
    {
      key: 'Headers',
      label: `Headers`,
      children: <Params params={request.headers == undefined ? [] : request.headers} ref={headersRef} />,
    },
    {
      key: 'Params',
      label: `Params`,
      children: <Params params={request.params == undefined ? [] : request.params} ref={paramsRef} />,
    },
    {
      key: 'Body',
      label: `Body`,
      children: <Body data={request.body == undefined ? { type: "none", language: "json", content: "" } : request.body} ref={bodyRef} />,
    },
  ];
  return (
    <>
      <Tabs activeKey={activeKey} items={items} onChange={onChange} destroyInactiveTabPane={false} />
    </>
  );
});

export default RequestView;
