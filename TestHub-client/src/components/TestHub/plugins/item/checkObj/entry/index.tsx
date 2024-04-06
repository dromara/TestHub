import React, { forwardRef, useImperativeHandle, useState } from 'react';

import { RuleActionResDto } from '@/typings';
import { ProForm, ProFormText } from '@ant-design/pro-components';
import { Col, Form, Row } from 'antd';
import i18n from '@/i18n';
interface IProps {
    data: RuleActionResDto;
}

const CheckObj = forwardRef((props: IProps, ref) => {
    useImperativeHandle(ref, () => ({
        getData: async () => {
            return { flag: true, data: { code: "CheckObj" } };
        },
    }));

    return <> </>;
});

export default CheckObj;