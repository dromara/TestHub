import i18n from '@/i18n';
import { HttpPageData, IHttpPageState } from '@/models/httpPage';
import { TreeNodeResDto } from '@/typings';
import { getCurrentProject } from '@/utils/localStorage';
import { ModalForm, ProFormText } from '@ant-design/pro-components';
import { message } from 'antd';
import React from 'react';

interface IProps {
  casePage: IHttpPageState;
  dispatch: any;
  parentKey?: string;
  callback: () => void;
}

function TreeAdd(props: IProps) {
  const { casePage, dispatch, parentKey } = props;
  const currentProject = getCurrentProject();

  return (
    <ModalForm
      layout="horizontal"
      width={560}
      title={i18n('common.text.newDir')}
      open={parentKey != undefined}
      onFinish={async (values) => {
        if (parentKey == undefined) {
          return false;
        }
        dispatch({
          type: 'casePage/saveTree',
          payload: {
            parentId: parseInt(parentKey),
            projectCode: currentProject?.code,
            name: values.name,
          },
          callback: (treeNode: TreeNodeResDto<any>) => {
            dispatch({
              type: 'casePage/putTree',
              payload: {
                oldParentId: undefined,
                parentId: parseInt(parentKey),
                node: treeNode,
              },
            });
            props.callback();
          },
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
        label={i18n('common.label.name')}
        labelCol={{ span: 4 }} // 设置 label 标签占据整个宽度
        wrapperCol={{ span: 20 }} // 设置文本框占据整个宽度
        rules={[{ required: true, message: i18n('common.label.name') + i18n('common.text.notNull') }]}
      />
    </ModalForm>
  );
}

export default TreeAdd;
