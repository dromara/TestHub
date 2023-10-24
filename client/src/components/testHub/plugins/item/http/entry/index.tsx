import React, { forwardRef, useImperativeHandle, useRef, useState } from 'react';

import { RuleActionResDto } from '@/typings';
import RequestView from '@/components/testHub/http/RequestView';
import { ProForm, ProFormInstance, ProFormSelect, ProFormText } from '@ant-design/pro-components';
import { HTTP } from '@/components/testHub/http/typings';
import { Col, Row } from 'antd';
interface IProps {
    data: RuleActionResDto;
}
const options = [
    {
        value: 'GET',
        label: 'GET',
    },
    {
        value: 'POST',
        label: 'POST',
    },
    {
        value: 'PUT',
        label: 'PUT',
    },
    {
        value: 'DELETE',
        label: 'DELETE',
    },
];
const Http = forwardRef((props: IProps, ref) => {
    const requestViewRef = useRef();
    const formRef = useRef<ProFormInstance>();
    const [data, setData] = useState<HTTP.HttpRequestResDto>(props.data.extraInto == undefined ||
        props.data.extraInto.httpModel == undefined ? {
        method: "GET"
    } : props.data.extraInto.httpModel as HTTP.HttpRequestResDto);
    useImperativeHandle(ref, () => ({
        getData: async () => {
            const vals = await formRef.current?.validateFieldsReturnFormatValue?.();
            if (requestViewRef.current != undefined) {
                const res = await requestViewRef.current.getData();
                if (!res.flag) {
                    return { flag: false, data: data };
                }
                const reData = res.data;
                reData.url = vals.url;
                reData.method = vals.method;
                console.log({ httpModel: reData })
                return { flag: true, data: { httpModel: reData } };
                // return { flag: false, data: { httpModel: reData } };
            }
            return { flag: false, data: data };
        },
    }));
    return <>
        <ProForm formRef={formRef} submitter={false}>
            <Row style={{ marginTop: 10 }}>
                <Col span={6}>
                    <ProFormSelect
                        key="method"
                        name="method"
                        initialValue={data.method}
                        options={options}
                        labelCol={{ span: 10 }}
                        wrapperCol={{ span: 14 }}
                        label="请求方式"
                        rules={[{ required: true, message: '此项为必填项' }]}
                        fieldProps={{
                            value: data.method,
                            onChange: (e) => {
                                const newData = data;
                                newData.method = e;
                                setData(JSON.parse(JSON.stringify(newData)));
                            },
                        }}
                    />
                </Col>
                <Col span={17} offset={1}>
                    <ProFormText
                        name="url"
                        key="url"
                        label="请求路径"
                        labelCol={{ span: 4 }}
                        wrapperCol={{ span: 20 }}
                        initialValue={data.url}
                        rules={[{ required: true, message: '此项为必填项' }]}
                        fieldProps={{
                            value: data.url,
                            onChange: (e: { target: { value: string | undefined } }) => {
                                const newData = data;
                                newData.url = e.target.value;
                                setData(JSON.parse(JSON.stringify(newData)));
                            },
                        }}
                    />
                </Col>
            </Row>
        </ProForm>
        <RequestView data={data} ref={requestViewRef} />
    </>;
});

export default Http;