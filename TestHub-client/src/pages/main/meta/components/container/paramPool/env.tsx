import React, { forwardRef, useEffect, useImperativeHandle, useRef, useState } from 'react';
import classnames from 'classnames';
import styles from './index.less';
import cloneDeep from 'lodash/cloneDeep';


import { IAppPageState } from '@/models/appPage';
import i18n from '@/i18n';
import { ProForm, ProFormInstance, ProFormText, ProFormTextArea } from '@ant-design/pro-components';
import { RuleEnvironmentResDto } from '@/typings';
import Params from '@/components/TestHub/assembly/params';
interface IProps {
  data: RuleEnvironmentResDto;
}

const Env = forwardRef((props: IProps, ref) => {

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
          render: (props, doms) => {
            return [];
          },
        }
        }
      >
        <ProFormText
          name="code"
          label="编码"
          disabled={data.id != undefined}
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
        <ProFormText
          name="name"
          label="名称"
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
        <ProFormTextArea
          name="remark"
          label="备注"
          rules={[{ required: true, message: "备注" + i18n('case.label.notNull') }]}
          fieldProps={{
            onChange: (e) => {
              data.remark = e.target.value;
              setData(data);
            }
          }}
        />

      </ProForm >

      <Params params={data?.params ? data.params : []} ref={paramsRef} />
    </>
  );
});

export default Env
