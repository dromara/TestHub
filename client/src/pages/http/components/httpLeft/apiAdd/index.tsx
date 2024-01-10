import i18n from '@/i18n';

import { TreeNodeResDto } from '@/typings';
import { ModalForm, ProFormText } from '@ant-design/pro-components';
import React, { } from 'react';
import { IAppPageState, IHttpPageState } from 'umi';


interface IProps {
    appPage: IAppPageState;
    httpPage: IHttpPageState;
    dispatch: Function;
    parentKey?: string;
    callback: Function;
}
function ApiAdd(props: IProps) {
    const { httpPage, dispatch, parentKey } = props;

    return <ModalForm
        width={560}
        title={"新增接口"}
        open={parentKey != undefined}
        onFinish={async (values) => {
            if (props.parentKey == undefined) {
                return false;
            }

            dispatch({
                type: 'httpPage/newApiConsole',
                payload: {
                    name: values.name,
                    parentKey: parentKey,
                },
                callback: props.callback
            })
            return true;
        }}
        maskClosable={true}

        modalProps={{
            destroyOnClose: true,
            okText: i18n('case.button.ok'),
            cancelText: i18n('case.button.cancel'),
            onCancel: () => {
                // 取消按钮的回调函数
                props.callback();
            },
        }}
    >
        <ProFormText
            name="name"
            label={"名称"}
            labelCol={{ span: 4 }} // 设置 label 标签占据整个宽度
            wrapperCol={{ span: 20 }} // 设置文本框占据整个宽度
            rules={[{ required: true, message: i18n('case.label.directoryName') + i18n('case.label.notNull') }]}
        />
    </ModalForm >
};


export default ApiAdd;

