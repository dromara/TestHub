import React, { forwardRef, useImperativeHandle } from 'react';

import { RuleActionResDto } from '@/typings';
interface IProps {
    data: RuleActionResDto;
}

const Http = forwardRef((props: IProps, ref) => {
    useImperativeHandle(ref, () => ({
        getData: async () => {
            return { flag: true, data: { code: "Http" } };
        },
    }));

    return <></>;
});

export default Http;