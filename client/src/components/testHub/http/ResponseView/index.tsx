import i18n from '@/i18n';
import { HttpApiSendResDto } from '@/typings/server/plugins/http';
import { ProTable } from '@ant-design/pro-components';
import { Badge, Descriptions, Tabs, Tag } from 'antd';
import React from 'react';

export type Props = {
  data?: HttpApiSendResDto
};

function isUndefinedOrEmpty(obj: any) {
  return obj == null || typeof obj === 'undefined' || Object.keys(obj).length === 0;
}
const columns = [
  {
    title: i18n('http.columns.param'),
    dataIndex: 'key',
    width: '20%',
  },
  {
    title: i18n('http.columns.val'),
    dataIndex: 'val',
    width: '80%',
    ellipsis: true,
  },
];
const getProTable = function (data: Record<string, any> | undefined) {
  return <ProTable
    dataSource={Object.entries(data || {}).map(([key, val]) => ({ key, val }))}
    columns={columns}
    search={false}
    pagination={false}
    options={{
      density: false,
      reload: false,
      search: false,
      setting: false,
    }}
  />
}
const colorMap = {
  "POST": "magenta",
  "GET": "green",
  "DELETE": "red",
  "PUT": "volcano",
  "HEAD": "orange",
  "PATCH": "lime",
  "OPTIONS": "blue"
}

const getBaseInfo = function (data: HttpApiSendResDto) {
  return <>
    <Descriptions column={1}>
      <Descriptions.Item label={<b>{i18n('http.text.url')}</b>}>{data.url}</Descriptions.Item>
    </Descriptions>
    <Descriptions column={3}>
      <Descriptions.Item label={<b>{i18n('http.text.method')}</b>}><Tag color={colorMap[data.method == undefined ? "GET" : data.method.toUpperCase()]}>{data.method.toUpperCase()}</Tag></Descriptions.Item>
      <Descriptions.Item label={<b>{i18n('http.text.status')}</b>}>{data.statusCode}</Descriptions.Item>
      <Descriptions.Item label={<b>{i18n('http.text.statusDesc')}</b>}>{data.statusName}</Descriptions.Item>
    </Descriptions>
  </>
}

const getView = function (data: HttpApiSendResDto) {
  const items = [
    {
      label: <Badge dot color='lime'>{"实际请求"}</Badge>,
      key: 'base',
      children: getBaseInfo(data)
    },
    {
      label: isUndefinedOrEmpty(data.rests) ? <>{i18n('http.data.rests')}</> : <Badge dot color='lime'>{i18n('http.data.rests')}</Badge>,
      key: 'rests',
      children: getProTable(data.rests)
    },
    {
      label: isUndefinedOrEmpty(data.params) ? <>{i18n('http.data.params')}</> : <Badge dot color='lime'>{i18n('http.data.params')}</Badge>,
      key: 'params',
      children: getProTable(data.params)
    }, {
      label: isUndefinedOrEmpty(data.cookies) ? <>{"cookies"}</> : <Badge dot color='lime'>{"cookies"}</Badge>,
      key: 'cookies',
      children: getProTable(data.cookies)
    },
    {
      label: isUndefinedOrEmpty(data.headers) ? <>{i18n('http.data.headers')}</> : <Badge dot color='lime'>{i18n('http.data.headers')}</Badge>,
      key: 'headers',
      children: getProTable(data.headers)
    }, {
      label: isUndefinedOrEmpty(data.reHeaders) ? <>{i18n('http.data.resheaders')}</> : <Badge dot color='lime'>{i18n('http.data.resheaders')}</Badge>,
      key: 'reHeaders',
      children: getProTable(data.reHeaders)
    }, {
      label: isUndefinedOrEmpty(data.reCookies) ? <>{i18n('http.data.recookies')}</> : <Badge dot color='lime'>{i18n('http.data.recookies')}</Badge>,
      key: 'reCookies',
      children: getProTable(data.reCookies)
    },
  ];
  return <Tabs items={items} />;
}


const ResponseView = (props: Props) => {
  return (
    <>
      {
        props.data == undefined && <>没有数据</>
      }
      {
        props.data != undefined && <>
          {getView(props.data)}
        </>
      }
    </>
  )
};

export default ResponseView;
