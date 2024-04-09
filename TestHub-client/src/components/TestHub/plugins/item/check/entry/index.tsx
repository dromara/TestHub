import React, { forwardRef, useImperativeHandle } from 'react';

import { RuleActionResDto } from '@/typings';
interface IProps {
  data: RuleActionResDto;
}

function Check(props: IProps, ref) {
  useImperativeHandle(ref, () => ({
    getData: async () => {
      return { flag: true, data: { code: 'Check' } };
    },
  }));

  return <></>;
}

export default forwardRef(Check);
