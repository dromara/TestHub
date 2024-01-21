import i18n from '@/i18n';
import { RuleParamResDto } from '@/typings';
import { BarsOutlined, CheckCircleOutlined } from '@ant-design/icons';
import { EditableFormInstance, ProColumns } from '@ant-design/pro-components';
import { EditableProTable } from '@ant-design/pro-components';
import { Dropdown, Menu, Modal, Radio, RadioChangeEvent, Space, Switch, message } from 'antd';
import classNames from 'classnames';
import React, { forwardRef, useEffect, useImperativeHandle, useRef, useState } from 'react';
import MonacoEditor from 'react-monaco-editor';
import systemService, { } from '@/service/system';
import { useTheme } from '@/utils/hooks';
import { useDebounceFn } from 'ahooks';
export type Props = {
    params: RuleParamResDto[];
    effective?: boolean;
    className?: string;
    onChange?: Function;
};

const options = [
    { label: 'XML', value: 'xml' },
    // { label: 'JSON', value: 'json' },
];
export enum MenuType {
    EDIT = 'edit',//批量编辑
    // COPY_ = 'COPY',//
}



const Params = forwardRef((props: Props, ref) => {
    const themeColor = useTheme();
    const [theme, setTheme] = useState(themeColor == 'dark' ? 'BlackTheme' : 'default');
    useEffect(() => {
        setTheme(themeColor == 'dark' ? 'BlackTheme' : 'default');
    }, [themeColor]);

    const editorFormRef = useRef<EditableFormInstance<RuleParamResDto>>();
    const [datas, setDatas] = useState<RuleParamResDto[]>(props.params);
    const [editableKeys, setEditableRowKeys] = useState<React.Key[]>([]);
    const [info, setInfo] = useState<string | undefined>();
    const [language, setLanguage] = useState('xml');
    // useEffect(() => {
    //     setDatas(props.params);
    // }, [props.params]);
    useEffect(() => {
        if (props.onChange != undefined) {
            props.onChange(datas);
        }
    }, [datas]);

    useImperativeHandle(ref, () => ({
        getData: async () => {
            if (editableKeys.length > 0) {
                message.error("有参数正在编辑中无法操作");
                return { flag: false };
            }
            await editorFormRef.current?.validateFieldsReturnFormatValue?.();
            return { flag: true, data: datas };
        },
    }));

    const { run } = useDebounceFn(
        (text: any, callback: Function) => {
            callback(text);
        },
        {
            wait: 500,
        },
    );

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
    function handleEffectiveChange(record) {
        const updatedDatas = datas.map((item) => {
            if (item.id === record.id) {
                return { ...item, effective: record.effective != false ? false : true };
            }
            return item;
        });
        setDatas(updatedDatas);
    }
    function handleAllEffectiveChange(flag: boolean) {
        const updatedDatas = datas.map((item) => {
            return { ...item, effective: flag };
        });
        setDatas(updatedDatas);
    }
    const dirClickMenuList = async (item: any) => {
        if (editableKeys.length > 0) {
            message.error("有参数正在编辑中无法操作");
            return;
        }
        var res = null;
        if (item == MenuType.EDIT) {
            if (datas.length > 0) {
                res = (await systemService.paramsJson2Xml({ info: JSON.stringify(datas) })) as string;
            } else {
                res = "<params>\n\t<param code=\"\" name=\"\" dataType=\"STRING\" data=\"\"/>\n</params>";
            }
            setInfo(res)
        } else {

        }
    }

    const menu = (
        <Menu>
            <Menu.Item key={MenuType.EDIT} onClick={dirClickMenuList.bind(null, MenuType.EDIT)}>批量编辑</Menu.Item>
            {/* <Menu.Item key="B" onClick={dirClickMenuList.bind(null, "B")}>复制调用</Menu.Item> */}
        </Menu>
    );
    const allEffective = function handleSwitchChange() {
        if (Array.isArray(datas) && datas.length > 0) {
            const allFlagsTrue = datas.every(item => item.effective != false);
            if (allFlagsTrue) {
                return <span style={{ fontSize: 18 }}><CheckCircleOutlined style={{ color: "#87d068" }} onClick={() => handleAllEffectiveChange(false)} /></span>
            } else {
                return <span style={{ fontSize: 18 }}><CheckCircleOutlined style={{ color: "#A9A9A9" }} onClick={() => handleAllEffectiveChange(true)} /></span>
            }
        } else {
            return "启用"
        }
    }


    const columns: ProColumns<RuleParamResDto>[] = [
        {
            title: allEffective(),
            dataIndex: 'effective',
            width: '5%',
            hideInTable: props.effective == true ? false : true,
            editable: false,
            render: (text, record, index, action) => {
                if (record.effective != false) {
                    return (
                        <span style={{ fontSize: 18 }}><CheckCircleOutlined style={{ color: "#87d068" }} onClick={() => handleEffectiveChange(record)} /></span>
                    );
                } else {
                    return (
                        <span style={{ fontSize: 18 }}><CheckCircleOutlined style={{ color: "#A9A9A9" }} onClick={() => handleEffectiveChange(record)} /></span>
                    );
                }
            },
        }, {
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
            title: <>{"操作 "}
                <Dropdown overlay={menu}>
                    <Space>
                        <BarsOutlined style={{ fontSize: 14 }} />
                    </Space>
                </Dropdown>
            </>,
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
            <div className={classNames(props.className)}>
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
            </div>
            <Modal
                title={"批量编辑"}
                width={750}
                open={info != undefined}
                destroyOnClose
                onOk={async () => {
                    var res = null;
                    if (info != undefined) {
                        res = (await systemService.paramsXml2Json({ info: info })) as RuleParamResDto[];
                        setDatas(res);
                        setInfo(undefined);
                    }
                }}
                onCancel={() => { setInfo(undefined) }}
                maskClosable={false}
                okText={i18n('case.button.ok')}
                cancelText={i18n('case.button.cancel')}
            >
                <div style={{ paddingTop: 5, paddingBottom: 15 }}>
                    <Radio.Group options={options} onChange={({ target: { value } }: RadioChangeEvent) => {
                        setLanguage(value);
                    }} value={language} optionType="button" />
                </div>
                <MonacoEditor
                    width={'100%'}
                    language={language}
                    height={400}
                    onChange={(text) => {
                        run(text, setInfo)
                    }}
                    // theme="vs"
                    value={info}
                    theme={theme}
                    options={{
                        minimap: { enabled: false },
                        scrollBeyondLastLine: false, // 设置编辑器是否可以滚动到最后一行之后
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
            </Modal >
        </>
    );
});

export default Params;
