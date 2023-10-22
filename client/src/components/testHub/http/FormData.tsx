import type { ProColumns } from '@ant-design/pro-components';
import { EditableProTable } from '@ant-design/pro-components';
import React, { forwardRef, useImperativeHandle, useState } from 'react';
export type Props = {
  params: API.RuleParamResDto[];
};

const FormData = forwardRef((props: Props, ref) => {
  const [params, setParams] = useState<API.FormDataResDto[]>(props.params);

  const [editableKeys, setEditableRowKeys] = useState<React.Key[]>([]);

  const columns: ProColumns<API.FormDataResDto>[] = [
    {
      title: '参数',
      dataIndex: 'key',
      tooltip: '编码需要在组内唯一,必须以字母开头',
      formItemProps: (form, { rowIndex }) => {
        return {
          rules: [
            { required: true, message: '此项为必填项' },
            { pattern: /^[a-zA-Z]\w{0,20}$/, message: '必须以字母开头' },
          ],
        };
      },
      width: '20%',
    },
    {
      title: '类型',
      dataIndex: 'type',
      valueType: 'select',
      valueEnum: {
        file: {
          text: '文件',
          status: 'file',
        },
        text: {
          text: '文本',
          status: 'text',
        },
      },
      width: '10%',
      formItemProps: (form, { rowIndex }) => {
        return {
          rules: [{ required: true, message: '此项为必填项' }],
        };
      },
    },
    {
      title: '值',
      dataIndex: 'value',
      width: '40%',
      formItemProps: (form, { rowIndex }) => {
        return {
          rules: [{ required: true, message: '此项为必填项' }],
        };
      },
    },
    {
      title: '描述',
      dataIndex: 'desc',
      width: '15%',
    },

    {
      title: '操作',
      valueType: 'option',
      width: '15%',
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
            setParams(params.filter((item) => item.id !== record.id));
          }}
        >
          删除
        </a>,
      ],
    },
  ];
  useImperativeHandle(ref, () => ({
    getData: async () => {
      const codes = [];
      for (let i = 0; i < params.length; i++) {
        const item = params[i];
        codes.push(item.code);
      }
      if (Array.from(new Set(codes)).length != codes.length) {
        return { flag: false, data: {}, msg: '参数编码重复' };
      }

      const result: Front.CheckResult<any> = {
        flag: true,
        data: params,
        msg: '',
      };
      return result;
    },
  }));
  return (
    <>
      <EditableProTable<API.FormDataResDto>
        rowKey="id"
        recordCreatorProps={{
          record: () => ({
            id: Math.random() * 1000000,
            type: 'text',
          }),
        }}
        loading={false}
        columns={columns}
        request={async () => ({
          data: params,
          total: params.length,
          success: true,
        })}
        value={params}
        onChange={setParams}
        editable={{
          type: 'multiple',
          editableKeys,
          onSave: async (rowKey, data, row) => {
            const newDatas = [...params];
            newDatas[row.index] = data;
            setParams(newDatas);
          },
          onChange: setEditableRowKeys,
        }}
      />
    </>
  );
});

export default FormData;
