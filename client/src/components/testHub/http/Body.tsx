import { ProCard } from '@ant-design/pro-components';
import { RadioChangeEvent, Radio, Row, Col, Space, Select } from 'antd';
import React, { forwardRef, useImperativeHandle, useEffect, useState, useRef } from 'react';
// import MonacoEditor from 'react-monaco-editor/lib/editor';
import { HTTP } from './typings';
import Params from '../assembly/params';
import { useDebounceFn } from 'ahooks';
import { RuleParamResDto } from '@/typings';
import classnames from 'classnames';
import { v4 as uuid } from 'uuid';
import MyMonacoEditor from '../MonacoEditor/MyMonacoEditor';
export type Props = {
  data: HTTP.BodyResDto;
  effective?: boolean;
  height?: number | string;
  onChange?: (data: HTTP.BodyResDto) => void;
  className?: string;
  bodyRawClassName: string;
};

const Body = forwardRef((props: Props, ref) => {
  const [language, setLanguage] = useState(props.data.language);
  const [data, setData] = useState(props.data);
  const [subFlag, setSubFlag] = useState(true);
  const paramsRef = useRef();

  useEffect(() => {
    if (data.type == 'raw') {
      setSubFlag(false);
    } else if (data.type == 'binary' || data.type == 'x-www-form-urlencoded') {
      setSubFlag(true);
    } else {
      setSubFlag(true);
    }
    if (props.onChange != undefined) {
      props.onChange({ ...data });
    }
  }, [data]);

  const { run: myUseDebounceFn } = useDebounceFn(
    (text: any, callback) => {
      callback(text);
    },
    {
      wait: 100,
    },
  );

  const onChange = (e: RadioChangeEvent) => {
    const newData = { ...data };
    newData.type = e.target.value;
    if (e.target.value == 'raw') {
      setSubFlag(false);
      if (newData.subType == undefined || newData.subType == 'text') {
        newData.subType = 'Json';
      }
    } else if (e.target.value == 'binary' || e.target.value == 'x-www-form-urlencoded') {
      setSubFlag(true);
      newData.subType = undefined;
    } else {
      if (newData.subType == undefined) {
        newData.subType = 'Json';
      }
      setSubFlag(true);
    }
    setData(newData);
  };
  useImperativeHandle(ref, () => ({
    getData: async () => {
      const newData = data;
      if (newData.type == 'form-data') {
        if (paramsRef.current != undefined) {
          const res = await paramsRef.current.getData();
          if (!res.flag) {
            return { flag: false, data: {} };
          }
          newData.datas = res.data;
        }
      } else if (newData.type == 'raw') {
        if (!newData.content) {
          return { flag: false, data: {} };
        }
        if (newData.language == 'json') {
          try {
            // 这里放置可能引发异常的代码
            JSON.parse(newData.content);
          } catch (error) {
            return { flag: false, data: {} };
          }
        }
      }
      return { flag: true, data: newData };
    },
  }));

  return (
    <div className={props.className}>
      <Row style={{ height: 35 }} align="middle">
        <Col span={18}>
          <Radio.Group onChange={onChange} value={data.type == undefined ? 'none' : data.type}>
            <Radio value={'none'}>none</Radio>
            <Radio value={'form-data'}>form-data</Radio>
            {/* <Radio value={'x-www-form-urlencoded'}>x-www-form-urlencoded</Radio> */}
            <Radio value={'raw'}>raw</Radio>
            {/* <Radio value={'binary'}>binary</Radio> */}
          </Radio.Group>
        </Col>
        <Col span={6} style={{ textAlign: 'right' }}>
          <div hidden={subFlag}>
            <Space wrap>
              <Select
                value={language}
                style={{ width: 90 }}
                bordered={false}
                options={[
                  { value: 'json', label: 'JSON' },
                  { value: 'text', label: 'TEXT' },
                  // { value: 'xml', label: 'XML' },
                  // { value: 'html', label: 'HTML' },
                  // { value: 'javascript', label: 'JavaScript' },
                ]}
                onChange={(e) => {
                  setLanguage(e);
                }}
              />
            </Space>
          </div>
        </Col>
      </Row>
      <div hidden={data.type != null && data.type != undefined && data.type != 'none'}>
        <Row align="middle" justify="center">
          <Col style={{ height: 237, lineHeight: 15 }}>没有正文</Col>
        </Row>
      </div>
      <div hidden={data.type != 'form-data'}>
        <ProCard split="vertical">
          <Params
            params={data.datas == undefined ? [] : data.datas}
            ref={paramsRef}
            effective={props.effective}
            onChange={(datas: RuleParamResDto[] | undefined) => {
              const newData = data;
              newData.datas = datas;
              setData({ ...newData });
            }}
          />
        </ProCard>
      </div>
      <div
        className={classnames({
          [props.bodyRawClassName]:
            data.type == 'x-www-form-urlencoded' ||
            data.type == 'raw' ||
            data.type == 'binary' ||
            data.type == 'GraphQL',
        })}
        hidden={
          !(
            data.type == 'x-www-form-urlencoded' ||
            data.type == 'raw' ||
            data.type == 'binary' ||
            data.type == 'GraphQL'
          )
        }
      >
        <ProCard split="vertical">
          <MyMonacoEditor
            language={'json'}
            defaultValue={data.content}
            onChange={(text) => {
              const newData = data;
              newData.content = text;
              newData.type = 'raw';
              myUseDebounceFn('', () => {
                setData({ ...newData });
              });
            }}
          />
        </ProCard>
      </div>
    </div>
  );
});

export default Body;
