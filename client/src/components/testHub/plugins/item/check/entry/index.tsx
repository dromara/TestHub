import React, { forwardRef, useImperativeHandle } from 'react';

import { RuleActionResDto } from '@/typings';
interface IProps {
    data: RuleActionResDto;
}

const Check = forwardRef((props: IProps, ref) => {
    useImperativeHandle(ref, () => ({
        getData: async () => {
            return { flag: true, data: { code: "Check" } };
        },
    }));

    return <></>;
});

export default Check;