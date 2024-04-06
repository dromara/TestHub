import i18n from '@/i18n';

import { TreeNodeResDto } from '@/typings';
import { ModalForm, ProFormText } from '@ant-design/pro-components';
import React from 'react';

import { IHttpPageState } from '@/models/httpPage';
import { getCurrentProject } from '@/utils/localStorage';

interface IProps {
  httpPage: IHttpPageState;
  dispatch: any;
  parentKey?: string;
  callback: any;
}
function ApiAdd(props: IProps) {
  const { httpPage, dispatch, parentKey } = props;

  return (
    <ModalForm
      layout="horizontal"
      width={560}
      title={'新增接口'}
      open={parentKey != undefined}
      onFinish={async (values) => {
        if (props.parentKey == undefined) {
          return false;
        }
        dispatch({
          type: 'httpPage/newApiConsole',
          payload: {
            name: values.name,
            projectCode: getCurrentProject()?.code,
            parentId: parseInt(parentKey != undefined ? parentKey : '0'),
          },
          callback: props.callback,
        });
        return true;
      }}
      modalProps={{
        destroyOnClose: true,
        okText: i18n('common.button.confirm'),
        cancelText: i18n('common.button.cancel'),
        onCancel: () => {
          // 取消按钮的回调函数
          props.callback();
        },
      }}
    >
      <ProFormText
        name="name"
        label={'名称'}
        labelCol={{ span: 4 }} // 设置 label 标签占据整个宽度
        wrapperCol={{ span: 20 }} // 设置文本框占据整个宽度
        rules={[{ required: true, message: i18n('common.label.name') + i18n('common.text.notNull') }]}
      />
    </ModalForm>
  );
}

export default ApiAdd;
