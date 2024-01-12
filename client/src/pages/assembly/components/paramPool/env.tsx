import React, { forwardRef, useImperativeHandle, useRef, useState } from 'react';
import { Badge, Card, Col, Collapse, Descriptions, Divider, Row, Steps, Tag } from 'antd';
import { IAppPageState } from 'umi';
import Params from '@/components/testHub/assembly/params';
import { RuleEnvironmentResDto, RuleParamResDto } from '@/typings';
import { ProForm, ProFormInstance, ProFormText, ProFormTextArea } from '@ant-design/pro-components';
import i18n from '@/i18n';
import styles from './index.less';
const { Step } = Steps;
interface IProps {
    data: RuleEnvironmentResDto;
}

export default forwardRef((props: IProps, ref) => {
    const [data, setData] = useState(props.data ? props.data : {});
    const formRef = useRef<ProFormInstance>();
    const paramsRef = useRef();

    useImperativeHandle(ref, () => ({
        getData: async () => {

            if (paramsRef.current != undefined) {
                const res = await paramsRef.current.getData();
                if (res.flag == false) {
                    return { flag: false, data: data };
                }
                data.params = res.data;
            }
            const vals = await formRef.current?.validateFieldsReturnFormatValue?.();
            data.code = vals.code;
            data.name = vals.name;
            data.remark = vals.remark;

            return { flag: true, data: data };
        },
    }));
    return (
        <>
            <ProForm
                formRef={formRef}
                initialValues={data}
                submitter={{
                    // 完全自定义整个区域
                    render: (props, doms) => {
                        return [];
                    },
                }}>
                {/* <Row>
                    <Col span={12}> */}
                <ProFormText
                    name="code"
                    label="编码"
                    disabled={data.id != undefined}
                    labelCol={{ span: 3 }} // 设置 label 标签占据整个宽度
                    wrapperCol={{ span: 20 }} // 设置文本框占据整个宽度
                    rules={[{ required: true, message: "编码" + i18n('case.label.notNull') }, { pattern: /^[a-zA-Z]\w{0,20}$/, message: '必须以字母开头' },]}
                    fieldProps={{
                        maxLength: 10, // 设置最大长度为10
                        minLength: 2,  // 设置最小长度为4
                        onChange: (e) => {
                            data.code = e.target.value;
                            setData(data);
                        }
                    }}
                />
                {/* </Col>
                    <Col span={12}>  */}
                <ProFormText
                    name="name"
                    label="名称"
                    labelCol={{ span: 3 }} // 设置 label 标签占据整个宽度
                    wrapperCol={{ span: 20 }} // 设置文本框占据整个宽度
                    rules={[{ required: true, message: "名称" + i18n('case.label.notNull') }]}
                    fieldProps={{
                        maxLength: 10, // 设置最大长度为10
                        minLength: 2,  // 设置最小长度为4
                        onChange: (e) => {
                            data.name = e.target.value;
                            setData(data);
                        }
                    }}
                />
                {/* </Col>
                </Row> */}
                <ProFormTextArea
                    name="remark"
                    label="备注"
                    labelCol={{ span: 3 }} // 设置 label 标签占据整个宽度
                    wrapperCol={{ span: 20 }} // 设置文本框占据整个宽度
                    rules={[{ required: true, message: "备注" + i18n('case.label.notNull') }]}
                    fieldProps={{
                        onChange: (e) => {
                            data.remark = e.target.value;
                            setData(data);
                        }
                    }}
                />

            </ProForm>

            <Params params={data?.params ? data.params : []} ref={paramsRef}></Params>
        </>
    );
});