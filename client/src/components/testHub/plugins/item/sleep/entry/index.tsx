import React, { forwardRef, useImperativeHandle } from 'react';

import { RuleActionResDto } from '@/typings';
interface IProps {
    data: RuleActionResDto;
}

const Sleep = forwardRef((props: IProps, ref) => {
    useImperativeHandle(ref, () => ({
        getData: async () => {
            return { flag: true, data: {} };
        },
    }));

    return <></>;
});

export default Sleep;