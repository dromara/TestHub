import { TabsProps, Tabs, Badge } from 'antd';
import React, { forwardRef, useEffect, useImperativeHandle, useRef, useState } from 'react';
import { HTTP } from '../typings';

import styles from './index.less';
import './index.less';
import classnames from 'classnames';
import Body from '../Body';
import Params from '../../assembly/params';
import i18n from '@/i18n';

export type Props = {
  data: HTTP.HttpRequestResDto;
  effective?: boolean;
  onDataChange?: Function;
  // onPageChange?: Function;
  activeKey?: string;
};

const RequestView = React.memo(
  forwardRef((props: Props, ref) => {
    const headersRef = useRef();
    const cookicesRef = useRef();
    const paramsRef = useRef();
    const bodyRef = useRef();
    const restsRef = useRef();
    const [activeKey, setActiveKey] = useState(props.activeKey ? props.activeKey : 'Rests');
    const [errorKey, setErroreKey] = useState<string | null>(null);
    const [request, setRequest] = useState<HTTP.HttpRequestResDto>(props.data);
    const onChange = (key: string) => {
      //todo 切换的时候保存一下数据
      setActiveKey(key);
      setErroreKey(null);
      // if (props.onPageChange != undefined) {
      //   props.onPageChange(key);
      // }
    };

    console.log(props.data);

    const onBodyChange = (body: HTTP.BodyResDto) => {
      const requestNew = request;
      requestNew.body = body;
      setRequest({ ...requestNew });
      if (props.onDataChange != undefined) {
        props.onDataChange({ ...requestNew });
      }
    };
    const onDataChange = (data: HTTP.HttpRequestResDto) => {
      setRequest({ ...data });
      if (props.onDataChange != undefined) {
        props.onDataChange({ ...data });
      }
    };

    useImperativeHandle(ref, () => ({
      getData: async () => {
        const data = request;
        if (restsRef.current != undefined) {
          const res = await restsRef.current.getData();
          if (!res.flag) {
            setErroreKey('Rests');
            return { flag: false, data: {} };
          }
          data.rests = res.data;
        }
        if (headersRef.current != undefined) {
          const res = await headersRef.current.getData();
          if (!res.flag) {
            setErroreKey('Headers');
            return { flag: false, data: {} };
          }
          data.headers = res.data;
        }
        if (cookicesRef.current != undefined) {
          const res = await cookicesRef.current.getData();
          if (!res.flag) {
            setErroreKey('Cookices');
            return { flag: false, data: {} };
          }
          data.cookices = res.data;
        }
        if (paramsRef.current != undefined) {
          const res = await paramsRef.current.getData();
          if (!res.flag) {
            setErroreKey('Params');
            return { flag: false, data: {} };
          }
          data.params = res.data;
        }
        if (bodyRef.current != undefined) {
          const res = await bodyRef.current.getData();
          if (!res.flag) {
            setErroreKey('Body');
            return { flag: false, data: {} };
          }
          data.body = res.data;
        }
        setErroreKey(null);

        return { flag: true, data: data };
      },
    }));

    const items: TabsProps['items'] = [
      {
        key: 'Rests',
        label:
          errorKey == 'Rests' ? (
            <Badge dot color="red">
              {i18n('http.data.rests')}
            </Badge>
          ) : (
            i18n('http.data.rests')
          ),
        // label: i18n('http.data.rests'),
        forceRender: true,
        children: (
          <div style={{ paddingTop: 10 }}>
            <Params
              params={request.rests == undefined ? [] : request.rests}
              ref={restsRef}
              effective={props.effective}
              onChange={(datas) => {
                const requestNew = request;
                requestNew.rests = datas;
                onDataChange({ ...requestNew });
              }}
            />
          </div>
        ),
      },
      {
        key: 'Headers',
        label:
          errorKey == 'Headers' ? (
            <Badge dot color="red">
              {i18n('http.data.headers')}
            </Badge>
          ) : (
            i18n('http.data.headers')
          ),
        forceRender: true,
        children: (
          <div style={{ paddingTop: 10 }}>
            <Params
              params={request.headers == undefined ? [] : request.headers}
              ref={headersRef}
              effective={props.effective}
              onChange={(datas) => {
                const requestNew = request;
                requestNew.headers = datas;
                onDataChange({ ...requestNew });
              }}
            />
          </div>
        ),
      },
      {
        key: 'Cookices',
        label:
          errorKey == 'Cookices' ? (
            <Badge dot color="red">
              {i18n('http.data.cookies')}
            </Badge>
          ) : (
            i18n('http.data.cookies')
          ),
        forceRender: true,
        children: (
          <div style={{ paddingTop: 10 }}>
            <Params
              params={request.cookices == undefined ? [] : request.cookices}
              ref={cookicesRef}
              effective={props.effective}
              onChange={(datas) => {
                const requestNew = request;
                requestNew.cookices = datas;
                onDataChange({ ...requestNew });
              }}
            />
          </div>
        ),
      },
      {
        key: 'Params',
        label:
          errorKey == 'Params' ? (
            <Badge dot color="red">
              {i18n('http.data.params')}
            </Badge>
          ) : (
            i18n('http.data.params')
          ),
        forceRender: true,
        children: (
          <div style={{ paddingTop: 10 }}>
            <Params
              params={request.params == undefined ? [] : request.params}
              ref={paramsRef}
              effective={props.effective}
              onChange={(datas) => {
                const requestNew = request;
                requestNew.params = datas;
                onDataChange({ ...requestNew });
              }}
            />
          </div>
        ),
      },
      {
        key: 'Body',
        // label: i18n('http.data.body'),
        label:
          errorKey == 'Body' ? (
            <Badge dot color="red">
              {i18n('http.data.body')}
            </Badge>
          ) : (
            i18n('http.data.body')
          ),
        forceRender: true,
        children: (
          <div style={{ paddingTop: 10, width: '100%', height: '100%' }}>
            <Body
              data={
                request.body == undefined ? { type: 'none', language: 'json', content: '', datas: [] } : request.body
              }
              height={'100%'}
              ref={bodyRef}
              effective={props.effective}
              onChange={onBodyChange}
              className={styles.body}
              bodyRawClassName={styles.bodyRow}
            />
          </div>
        ),
      },
    ];
    return (
      <div className={classnames(styles.requestViewContainer)}>
        <div className="requestViewContainerTabs">
          <Tabs
            activeKey={errorKey ? errorKey : activeKey ? activeKey : 'Body'}
            // activeKey={activeKey ? activeKey : 'Body'}
            // defaultActiveKey={activeKey ? activeKey : 'Rests'}
            items={items}
            onChange={onChange}
          />
        </div>
      </div>
    );
  }),
);

export default RequestView;
