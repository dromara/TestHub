import i18n from '@/i18n';

import { RuleResDto } from '@/typings';
import { ModalForm, ProFormText } from '@ant-design/pro-components';
import React from 'react';
import { ConsoleStatus } from '@/typings/client';
import { getCurrentProject } from '@/utils/localStorage';

interface IProps {
  dispatch: any;
  parentKey?: string;
  callback: any;
}
function CaseAdd(props: IProps) {
  const { dispatch, parentKey } = props;

  return (
    <ModalForm
      layout="horizontal"
      width={560}
      title={'新增用例'}
      open={parentKey != undefined}
      onFinish={async (values) => {
        if (props.parentKey == undefined) {
          return false;
        }

        if (values.code) {
          // ;

          const fileContent = `<?xml version="1.0" encoding="UTF-8"?>\n\n<rule code="${values.code}" name="${
            values.name
          }" model="flow" project="${
            getCurrentProject()?.code
          }">\n    <flows>\n        <flow code="flow1">\n \n        </flow>\n    </flows>\n</rule>`;
          // eslint-disable-next-line max-len
          const rule: RuleResDto = {
            project: getCurrentProject()?.code,
            code: values.code,
            treeId: parseInt(parentKey != undefined ? parentKey : '0'),
            name: values.name,
            flows: [],
            fileContent: fileContent,
          };
          const node = {
            key: values.code,
            parentKey: parentKey != undefined ? parentKey : '0',
            name: values.name,
            nodeType: 'CASE',
            info: rule,
          };

          dispatch({
            type: 'casePage/addConsole',
            payload: {
              node: node,
              status: ConsoleStatus.UNTRACKED,
            },
          });
          props.callback();
        } else {
          // dispatch({
          //     type: 'appPage/getCode',
          //     payload: "RULE",
          //     callback: (code: string) => {
          //         props.callback();
          //         dispatch({
          //             type: 'appPage/newCaseConsole',
          //             payload: { key: code, name: values.name, parentKey: parseInt(props.parentKey) },
          //         })
          //     }
          // });
        }
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
        name="code"
        label={i18n('case.label.caseCode')}
        tooltip={i18n('case.label.caseCodeTip')}
        labelCol={{ span: 4 }} // 设置 label 标签占据整个宽度
        wrapperCol={{ span: 20 }} // 设置文本框占据整个宽度
        rules={[{ pattern: /^[a-zA-Z]([-_a-zA-Z0-9]{1,30})$/, message: '30位以内必须以字母开头' }]}
      />
      <ProFormText
        name="name"
        label={i18n('case.label.caseName')}
        labelCol={{ span: 4 }} // 设置 label 标签占据整个宽度
        wrapperCol={{ span: 20 }} // 设置文本框占据整个宽度
        rules={[{ required: true, message: i18n('case.label.caseName') + i18n('case.label.notNull') }]}
      />
      {/* 
      <ProFormText
        name="name"
        label={'名称'}
        labelCol={{ span: 4 }} // 设置 label 标签占据整个宽度
        wrapperCol={{ span: 20 }} // 设置文本框占据整个宽度
        rules={[{ required: true, message: i18n('common.label.name') + i18n('common.text.notNull') }]}
      /> */}
    </ModalForm>
  );
}

export default CaseAdd;
