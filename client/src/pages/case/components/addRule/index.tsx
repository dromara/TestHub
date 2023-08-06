import i18n from '@/i18n';
import { TreeNodeResDto } from '@/typings';
import { ModalForm, ProFormSelect, ProFormText, ProFormTreeSelect } from '@ant-design/pro-components';
import { DefaultOptionType } from 'antd/lib/select';
import React, { useCallback, useContext, useEffect, useMemo, useRef, useState } from 'react';
import { IAppPageState } from 'umi';


interface IProps {
    appPage: IAppPageState;
    dispatch: Function;
    showFlag: boolean;
    parentKey: string;
    callback: Function;
}


function AddRule(props: IProps) {
    const { appPage, dispatch, showFlag } = props;

    return <ModalForm
        width={560}
        title={i18n('case.button.addCase')}
        open={showFlag}
        onFinish={(values) => {
            if (values.code) {
                props.callback();
                dispatch({
                    type: 'appPage/newCaseConsole',
                    payload: { key: values.code, name: values.name, parentKey: parseInt(props.parentKey) },
                })
            } else {
                dispatch({
                    type: 'appPage/getCode',
                    payload: "RULE",
                    callback: (code: string) => {
                        props.callback();
                        dispatch({
                            type: 'appPage/newCaseConsole',
                            payload: { key: code, name: values.name, parentKey: parseInt(props.parentKey) },
                        })
                    }
                });
            }
            return true;
        }}
        maskClosable={true}
        modalProps={{
            okText: i18n('case.button.ok'),
            cancelText: i18n('case.button.cancel'),
            destroyOnClose: true,
            onCancel: () => {
                props.callback();
            },
        }}
    >
        <ProFormText
            name="code"
            label={i18n('case.label.caseCode')}
            tooltip={i18n('case.label.caseCodeTip')}
            labelCol={{ span: 4 }} // 设置 label 标签占据整个宽度
            wrapperCol={{ span: 20 }} // 设置文本框占据整个宽度
        />
        <ProFormText
            name="name"
            label={i18n('case.label.caseName')}
            labelCol={{ span: 4 }} // 设置 label 标签占据整个宽度
            wrapperCol={{ span: 20 }} // 设置文本框占据整个宽度
            rules={[{ required: true, message: i18n('case.label.caseName') + i18n('case.label.notNull') }]}
        />
    </ModalForm >
};


export default AddRule;

