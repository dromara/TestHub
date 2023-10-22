import { RuleParamResDto } from '@/typings';
import { CheckOutlined, CloseOutlined, MinusOutlined } from '@ant-design/icons';
import { EditableFormInstance, ProCard, ProColumns, ProFormField } from '@ant-design/pro-components';
import { EditableProTable } from '@ant-design/pro-components';
import { Switch } from 'antd';
import React, { forwardRef, useEffect, useImperativeHandle, useRef, useState } from 'react';
export type Props = {
    params: RuleParamResDto[];
};

const Params = forwardRef((props: Props, ref) => {

    const editorFormRef = useRef<EditableFormInstance<RuleParamResDto>>();
    const [datas, setDatas] = useState<RuleParamResDto[]>(props.params);
    const [editableKeys, setEditableRowKeys] = useState<React.Key[]>([]);
    useEffect(() => {
        setDatas(props.params);
    }, [props.params]);

    useImperativeHandle(ref, () => ({
        getData: async () => {
            await editorFormRef.current?.validateFieldsReturnFormatValue?.();
            return { flag: true, data: datas };
        },
    }));

    function handleSwitchChange(record, checked) {

        // 使用 map 方法遍历 datas 列表
        const updatedDatas = datas.map((item) => {
            // 如果记录的 id 匹配当前操作的记录的 id
            if (item.id === record.id) {
                // 更新记录的开关字段
                return { ...item, necessary: checked };
            }
            // 如果记录的 id 不匹配，保持原样
            return item;
        });

        // 使用 setDatas 更新组件的 datas 状态
        setDatas(updatedDatas);

    }



    const columns: ProColumns<RuleParamResDto>[] = [
        {
            title: '参数名称',
            dataIndex: 'code',
            tooltip: '编码需要在组内唯一,必须以字母开头',
            formItemProps: (form, { rowIndex }) => {
                return {
                    rules: [
                        { required: true, message: '此项为必填项' },
                        { pattern: /^[a-zA-Z][a-zA-Z0-9_-]{0,20}$/, message: '必须以字母开头' },
                        {
                            validator: async (_, value, row) => {
                                const codes: string[] = [];
                                for (let i = 0; i < datas.length; i++) {
                                    if (rowIndex != i) {
                                        codes.push(datas[i].code);
                                    }
                                }
                                codes.push(value);
                                if (Array.from(new Set(codes)).length != codes.length) {
                                    return Promise.reject(new Error('编码已经存在'))
                                }
                                Promise.resolve()

                            },
                        },
                    ],
                };
            },
            width: '15%',
        }, {
            title: '描述',
            dataIndex: 'name',
            width: '20%',
        },
        {
            title: '数据类型',
            dataIndex: 'dataType',
            width: '17%',
            valueType: 'select',
            valueEnum: {
                STRING: { text: '字符串', status: 'STRING' },
                NUNBER: { text: '数字', status: 'NUNBER' },
                BOLL: { text: '布尔值', status: 'BOLL' },
                MAP: { text: '键值对', status: 'MAP' },
                TIME_YMD: { text: '年月日', status: 'TIME_YMD' },
                TIME_HMS: { text: '时分秒', status: 'TIME_HMS' },
                TIME_YMDHMS: { text: '年月日时分秒', status: 'TIME_YMDHMS' },
            }, formItemProps: (form, { rowIndex }) => {
                return {
                    rules: [
                        {
                            required: true,
                            message: '数据类型不能为空',
                        },
                    ]
                }
            },
        },
        {
            title: '是否必传',
            width: '10%',
            dataIndex: 'necessary',
            valueType: 'switch',
            // 如果需要自定义开关的样式，可以使用 render 属性
            render: (text, record) => {
                return (
                    <Switch
                        checked={record.necessary}
                        // 在状态变化时执行相应的操作，比如更新数据
                        onChange={(checked) => handleSwitchChange(record, checked)}
                    />
                );
            },
        }, {
            title: '默认值',
            dataIndex: 'data',
            width: '28%'
        },
        {
            title: '操作',
            valueType: 'option',
            width: '10%',
            render: (text, record, _, action) => [
                <a
                    key="editable"
                    onClick={() => {
                        action?.startEditable?.(record.id);
                    }}
                >
                    编辑
                </a>,
                <a
                    key="delete"
                    onClick={() => {
                        setDatas(datas.filter((item) => item.id !== record.id));
                    }}
                >
                    删除
                </a>,
            ],
        },
    ];

    return (
        <>
            <EditableProTable<RuleParamResDto>
                editableFormRef={editorFormRef}
                rowKey="id"
                recordCreatorProps={{
                    record: () => ({
                        id: Math.random() * 10000000000000000,
                        code: "",
                        dataType: "STRING"
                    }),
                }}
                columns={columns}
                value={datas}
                onChange={setDatas}
                editable={{
                    type: 'multiple',
                    editableKeys,
                    actionRender: (row, config, defaultDoms) => {
                        return [defaultDoms.save, defaultDoms.delete];
                    },
                    onValuesChange: (record, recordList) => {
                        setDatas(recordList);
                    },
                    onChange: setEditableRowKeys,
                }}
            />
            {/* <ProCard title="表格数据" headerBordered collapsible>
                <ProFormField
                    ignoreFormItem
                    fieldProps={{
                        style: {
                            width: '100%',
                        },
                    }}
                    mode="read"
                    valueType="jsonCode"
                    text={JSON.stringify(datas)}
                />
            </ProCard > */}
        </>
    );
});

export default Params;
