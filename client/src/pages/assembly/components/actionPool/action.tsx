import { AllTypes, EntryTypes } from '@/components/testHub/plugins/ActionType';
import Entry, { getActionComplex, getActionDataType } from '@/components/testHub/plugins/Entry';
import { RuleActionResDto } from '@/typings';
import {
    ProForm,
    ProFormDigit,
    ProFormInstance,
    ProFormSelect,
    ProFormText,
} from '@ant-design/pro-components';
import { Col, Form, Row } from 'antd';
import React, { forwardRef, useImperativeHandle, useRef, useState } from 'react';


export type Props = {
    data: RuleActionResDto;
    // onChange: Function;
};

const Action = forwardRef((props: Props, ref) => {
    const getOptions = () => {
        const res = [
            {
                value: 'STRING',
                label: '字符串',
            },
            {
                value: 'NUMBER',
                label: '数字',
            },
            {
                value: 'BOLL',
                label: '布尔',
            }, {
                value: 'MAP',
                label: '键值对',
            },
            {
                value: 'TIME_HMS',
                label: '时分秒',
            },
            {
                value: 'TIME_YMD',
                label: '年月日',
            },
            {
                value: 'TIME_YMDHMS',
                label: '年月日时分秒',
            },
        ];
        return res;
    };
    const actionRef = useRef();
    const formRef = useRef<ProFormInstance>();
    const [data, setData] = useState<RuleActionResDto>(props.data);
    useImperativeHandle(ref, () => ({
        getData: async () => {
            const vals = await formRef.current?.validateFieldsReturnFormatValue?.();
            if (actionRef.current != undefined) {
                const reData = await actionRef.current.getData();
                reData.data.code = vals.code;
                reData.data.name = vals.name;
                reData.data.type = vals.type;
                reData.data.complex = vals.complex;
                reData.data.dataType = vals.dataType;
                return reData;
            } else {
                return { flag: true, data: {} };
            }
        },
    }));
    return (
        <>
            <ProForm formRef={formRef} submitter={false}>
                <Row>
                    <Col span={11}><ProFormText
                        label="编码"
                        tooltip="编码重复会覆盖需要参考覆盖策略"
                        name="code"
                        disabled={data.id != undefined}
                        labelCol={{ span: 4 }}
                        wrapperCol={{ span: 19, offset: 1 }}
                        rules={[
                            { required: true, message: '此项为必填项' },
                            { pattern: /^[a-zA-Z]([-_a-zA-Z0-9]{1,30})$/, message: '30位以内必须以字母开头' },
                        ]}
                        initialValue={data.code}
                        fieldProps={{
                            defaultValue: data.code,
                            onChange: (e: { target: { value: string | undefined } }) => {
                                const newDatas = data;
                                newDatas.name = e.target.value as string;
                                setData(JSON.parse(JSON.stringify(newDatas)));
                            },
                        }}
                    /></Col>
                    <Col span={12} offset={1}><ProFormText
                        label="名称"
                        name="name"
                        rules={[{ required: true }]}
                        initialValue={data.name}
                        labelCol={{ span: 4 }}
                        wrapperCol={{ span: 19, offset: 1 }}
                        fieldProps={{
                            defaultValue: data.name,
                            onChange: (e: { target: { value: string | undefined } }) => {
                                const newDatas = data;
                                newDatas.name = e.target.value as string;
                                setData(newDatas);
                            },
                        }}
                    /></Col>
                </Row>
                <Row>
                    <Col span={7}>
                        <ProFormSelect
                            options={AllTypes}
                            initialValue={data.type}
                            rules={[{ required: true, message: '此项为必填项' }]}
                            name="type"
                            labelCol={{ span: 6 }}
                            wrapperCol={{ span: 16, offset: 2 }}
                            label="类型"
                            disabled={data.id != undefined}
                            fieldProps={{
                                onChange: (e) => {
                                    const newDatas = data;
                                    newDatas.type = e as string;
                                    newDatas.complex = getActionComplex(data.type) != -1 ? getActionComplex(data.type) : data.complex;
                                    newDatas.dataType = getActionDataType(data.type) != undefined ? getActionDataType(data.type) : data.dataType;
                                    setData(JSON.parse(JSON.stringify(newDatas)));
                                },
                            }}
                        />
                    </Col>
                    <Col span={8}>
                        <ProFormDigit
                            label="结果维度"
                            name="complex"
                            min={0}
                            max={2}
                            labelCol={{ span: 8, offset: 5 }}
                            wrapperCol={{ span: 10 }}
                            disabled={getActionComplex(data.type) != -1}
                            initialValue={
                                getActionComplex(data.type) != -1 ? getActionComplex(data.type) : (data.complex == undefined ? 0 : data.complex)
                            }
                            rules={[{ required: true, message: '此项为必填项' }]}
                            fieldProps={{
                                precision: 0,
                                defaultValue: getActionComplex(data.type) != -1 ? getActionComplex(data.type) : (data.complex == undefined ? 0 : data.complex),
                                onChange: (value: string | number | null) => {
                                    const value2 = value == null ? 0 : value;
                                    const newDatas = data;
                                    newDatas.complex = parseInt(value2 + '');
                                    setData(newDatas);
                                },
                            }}
                        />
                    </Col>
                    <Col span={9}>
                        <ProFormSelect
                            options={getOptions()}
                            rules={[{ required: true, message: '此项为必填项' }]}
                            initialValue={
                                getActionDataType(data.type) != undefined ? getActionDataType(data.type) : (data.dataType == undefined ? 'STRING' : data.dataType)
                            }
                            name="dataType"
                            label="结果类型"
                            disabled={getActionDataType(data.type) != undefined}
                            labelCol={{ span: 7, offset: 3 }}
                            wrapperCol={{ span: 14 }}
                            fieldProps={{
                                defaultValue: getActionDataType(data.type) != undefined ? getActionDataType(data.type) : (data.dataType == undefined ? 'STRING' : data.dataType),
                                onChange: (e) => {
                                    const newDatas = data;
                                    newDatas.dataType = e;
                                    setData(newDatas);
                                },
                            }}
                        />
                    </Col>
                </Row>
            </ProForm>
            <Entry data={data} ref={actionRef} />
        </>
    );
});

export default Action;
