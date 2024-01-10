import { ProCard } from '@ant-design/pro-components';
import { RadioChangeEvent, Radio, Row, Col, Space, Select } from 'antd';
import React, { forwardRef, useImperativeHandle, useEffect, useState, useRef, useContext } from 'react';
import MonacoEditor from 'react-monaco-editor/lib/editor';
import FormData from './FormData';
import { HTTP } from './typings';
import Params from '../assembly/params';
import { useTheme } from '@/utils/hooks';
export type Props = {
  data: HTTP.BodyResDto;
  effective?: boolean;
  height?: number | string;
};

const Body = forwardRef((props: Props, ref) => {
  const themeColor = useTheme();
  const [theme, setTheme] = useState(themeColor == 'dark' ? 'BlackTheme' : 'default');
  useEffect(() => {
    setTheme(themeColor == 'dark' ? 'BlackTheme' : 'default');
  }, [themeColor]);
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
  }, [data]);

  const onChange = (e: RadioChangeEvent) => {
    const newData = JSON.parse(JSON.stringify(data));
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
      if (newData.type == "form-data") {
        if (paramsRef.current != undefined) {
          const res = await paramsRef.current.getData();
          if (!res.flag) {
            return { flag: false, data: {} };
          }
          newData.datas = res.data;
        }
      }
      return { flag: true, data: newData };
    },
  }));

  return (
    <>
      <Row style={{ height: 35, marginBottom: 10 }} align="middle">
        <Col span={18}>
          <Radio.Group onChange={onChange} value={data.type == undefined ? 'none' : data.type}>
            <Radio value={'none'}>none</Radio>
            <Radio value={'form-data'}>form-data</Radio>
            {/* <Radio value={'x-www-form-urlencoded'}>x-www-form-urlencoded</Radio> */}
            <Radio value={'raw'}>raw</Radio>
            {/* <Radio value={'binary'}>binary</Radio> */}
          </Radio.Group>
        </Col>
        <Col span={6} style={{ textAlign: 'right' }} >
          <Space wrap hidden={subFlag}>
            <Select
              value={language}
              // style={{ width: 10 }}
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
        </Col>
      </Row>
      <Row
        hidden={data.type != null && data.type != undefined && data.type != 'none'}
        align="middle"
        justify="center"
      >
        <Col style={{ height: 237, lineHeight: 15 }}>没有正文</Col>
      </Row>
      <ProCard split="vertical" hidden={data.type != 'form-data'}>
        <Params params={data.datas == undefined ? [] : data.datas} ref={paramsRef} effective={props.effective} />
      </ProCard>
      <ProCard
        split="vertical"
        hidden={
          !(
            data.type == 'x-www-form-urlencoded' ||
            data.type == 'raw' ||
            data.type == 'binary' ||
            data.type == 'GraphQL'
          )
        }
      >
        <MonacoEditor
          width={'100%'}
          language={language}
          height={props.height == undefined ? 200 : props.height}
          onChange={(text) => {
            const newData = JSON.parse(JSON.stringify(data));
            newData.content = text;
            newData.type = 'raw'
            setData(newData);
          }}
          value={data.content}
          theme={theme}
          options={{
            minimap: { enabled: false },
            overviewRulerBorder: false,
            scrollBeyondLastLine: true, // 设置编辑器是否可以滚动到最后一行之后
            automaticLayout: true, // 自动布局
            scrollbar: {
              verticalScrollbarSize: 6,
              horizontalScrollbarSize: 6,
              verticalSliderSize: 6,
              horizontalSliderSize: 6,
              verticalHasArrows: false,
              horizontalHasArrows: false,
              arrowSize: 0,
              useShadows: true,
            },
          }}
        />
      </ProCard>
    </>
  );
});

export default Body;
