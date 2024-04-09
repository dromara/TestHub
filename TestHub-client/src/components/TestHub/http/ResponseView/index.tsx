import i18n from '@/i18n';
import { HttpApiSendResDto } from '@/typings/server/plugins/http';
import { ProTable } from '@ant-design/pro-components';
import { Badge, Descriptions, Tabs, Tag } from 'antd';
import React, { useEffect, useState } from 'react';
import styles from './index.less';
import classnames from 'classnames';
import './index.less';
import { v4 as uuid } from 'uuid';
import MyMonacoEditor from '../../MonacoEditor/MyMonacoEditor';

export type Props = {
  data?: HttpApiSendResDto;
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
  return (
    <ProTable
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
  );
};
const colorMap = {
  POST: 'magenta',
  GET: 'green',
  DELETE: 'red',
  PUT: 'volcano',
  HEAD: 'orange',
  PATCH: 'lime',
  OPTIONS: 'blue',
};

const getBaseInfo = function (data: HttpApiSendResDto) {
  return (
    <>
      <Descriptions column={1}>
        <Descriptions.Item label={<b>{i18n('http.text.url')}</b>}>{data.url}</Descriptions.Item>
      </Descriptions>
      <Descriptions column={3}>
        <Descriptions.Item label={<b>{i18n('http.text.method')}</b>}>
          <Tag color={colorMap[data.method ? data.method.toUpperCase() : 'GET']}>{data.method}</Tag>
        </Descriptions.Item>
        <Descriptions.Item label={<b>{i18n('http.text.status')}</b>}>{data.statusCode}</Descriptions.Item>
        <Descriptions.Item label={<b>{i18n('http.text.statusDesc')}</b>}>{data.statusName}</Descriptions.Item>
      </Descriptions>
      {!isUndefinedOrEmpty(data.rests) && (
        <Descriptions column={1}>
          <Descriptions.Item label={<b>{i18n('http.data.rests')}</b>}> </Descriptions.Item>
          <Descriptions.Item> {getProTable(data.rests)}</Descriptions.Item>
        </Descriptions>
      )}
      {!isUndefinedOrEmpty(data.params) && (
        <Descriptions column={1}>
          <Descriptions.Item label={<b>{i18n('http.data.params')}</b>}> </Descriptions.Item>
          <Descriptions.Item> {getProTable(data.params)}</Descriptions.Item>
        </Descriptions>
      )}
      {!isUndefinedOrEmpty(data.cookies) && (
        <Descriptions column={1}>
          <Descriptions.Item label={<b>cookies</b>}> </Descriptions.Item>
          <Descriptions.Item> {getProTable(data.cookies)}</Descriptions.Item>
        </Descriptions>
      )}
      {!isUndefinedOrEmpty(data.headers) && (
        <Descriptions column={1}>
          <Descriptions.Item label={<b>{i18n('http.data.headers')}</b>}> </Descriptions.Item>
          <Descriptions.Item> {getProTable(data.headers)}</Descriptions.Item>
        </Descriptions>
      )}
      {!isUndefinedOrEmpty(data.body) && (
        <Descriptions column={1}>
          <Descriptions.Item label={<b>{i18n('http.data.body')}</b>}> </Descriptions.Item>
          <Descriptions.Item>
            <div style={{ width: '100%', height: 200 }}>
              <MyMonacoEditor
                language={data.reqType}
                defaultValue={data.body}
                options={{
                  readOnly: true,
                }}
              />
            </div>
          </Descriptions.Item>
        </Descriptions>
      )}
    </>
  );
};

const getView = function (data: HttpApiSendResDto) {
  const [uid, setUid] = useState<string | null>(null);

  useEffect(() => {
    setUid(uuid());
  }, []);
  const items = [
    {
      label: (
        <Badge dot color="lime">
          {'实际请求'}
        </Badge>
      ),
      key: 'base',
      children: getBaseInfo(data),
    },
    {
      label: isUndefinedOrEmpty(data.reHeaders) ? (
        <>{i18n('http.data.resheaders')}</>
      ) : (
        <Badge dot color="lime">
          {i18n('http.data.resheaders')}
        </Badge>
      ),
      key: 'reHeaders',
      children: getProTable(data.reHeaders),
    },
    {
      label: isUndefinedOrEmpty(data.reCookies) ? (
        <>{i18n('http.data.recookies')}</>
      ) : (
        <Badge dot color="lime">
          {i18n('http.data.recookies')}
        </Badge>
      ),
      key: 'reCookies',
      children: getProTable(data.reCookies),
    },
    {
      label: (
        <Badge dot color="lime">
          返回值
        </Badge>
      ),
      key: 'reData',
      children: (
        <MyMonacoEditor
          language={data.reType}
          defaultValue={data.resData}
          options={{
            readOnly: true,
          }}
        />
      ),
    },
  ];
  return <Tabs key={data.uuid} items={items} defaultActiveKey="reData" />;
};

const ResponseView = (props: Props) => {
  return (
    <div className={classnames(styles.responseViewContainer)}>
      {props.data == undefined && <>没有数据</>}
      {props.data != undefined && (
        <div
          className="responseViewContainerTabs"
          // className={classnames(styles.responseViewContainerTabs)}
        >
          {getView(props.data)}
        </div>
      )}
    </div>
  );
};

export default ResponseView;
