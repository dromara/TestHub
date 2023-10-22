import type { ProColumns } from '@ant-design/pro-components';
import { EditableProTable } from '@ant-design/pro-components';
import { TabsProps, Col, Row, Tabs } from 'antd';
import React, { forwardRef, useImperativeHandle, useState } from 'react';

export type Props = {};

const ResponseView = forwardRef((props: Props, ref) => {
  const onChange = (key: string) => {
    console.log(key);
  };

  const items: TabsProps['items'] = [
    {
      key: '响应',
      label: `响应`,
      children: '响应',
    },
    {
      key: '请求头',
      label: `请求头`,
      children: '响应',
    },
    {
      key: '响应头',
      label: `响应头`,
      children: '响应头',
    },
  ];
  return (
    <>
      <Tabs defaultActiveKey="Body" items={items} onChange={onChange} />
    </>
  );
});

export default ResponseView;
