import React from 'react';
import i18n from '@/i18n';
import { IHttpPageState } from '@/models/httpPage';
import { TreeNodeResDto } from '@/typings';
import { getCurrentProject } from '@/utils/localStorage';
import { ModalForm, ProFormSelect, ProFormText, ProFormTreeSelect } from '@ant-design/pro-components';
import { TreeItem } from 'react-complex-tree';

interface IProps {
  httpPage: IHttpPageState;
  dispatch: any;
  thisNode?: TreeItem;
  callback: () => void;
}

function TreeRename(props: IProps) {
  const { dispatch, thisNode } = props;

  return (
    <ModalForm
      layout="horizontal"
      width={560}
      title={i18n('common.text.rename') + ' - ' + (thisNode != undefined ? thisNode?.data.name : '')}
      open={thisNode != undefined}
      initialValues={{ name: thisNode?.data.name }}
      onFinish={async (values) => {
        if (thisNode == undefined) {
          return false;
        }
        if (thisNode.data.name == values.name) {
          props.callback();
          return false;
        }

        dispatch({
          type: 'httpPage/rename',
          payload: {
            name: values.name,
            id: parseInt(thisNode?.data.key),
          },
          callback: (treeNode: TreeNodeResDto<any>) => {
            dispatch({
              type: 'httpPage/putTree',
              payload: {
                oldParentId: parseInt(thisNode.data.parentKey),
                parentId: parseInt(thisNode.data.parentKey),
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

export default TreeRename;
