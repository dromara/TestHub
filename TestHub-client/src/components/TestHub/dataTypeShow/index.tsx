import i18n from '@/i18n';
import { Tag } from 'antd';
import React from 'react';

type Props = {
    data: string;
};

const map = {
    gt: i18n('testhub.DataTypeShow.gt'),
    le: i18n('testhub.DataTypeShow.le'),
    lt: i18n('testhub.DataTypeShow.lt'),
    ge: i18n('testhub.DataTypeShow.ge'),
    eq: i18n('testhub.DataTypeShow.eq'),
    neq: i18n('testhub.DataTypeShow.neq'),
    eqd: i18n('testhub.DataTypeShow.eqd'),
    neqd: i18n('testhub.DataTypeShow.neqd'),
    in: i18n('testhub.DataTypeShow.in'),
    nin: i18n('testhub.DataTypeShow.nin'),
    en: i18n('testhub.DataTypeShow.en'),
    nn: i18n('testhub.DataTypeShow.nn'),
    be: i18n('testhub.DataTypeShow.be'),
    nbe: i18n('testhub.DataTypeShow.nbe'),
    bed: i18n('testhub.DataTypeShow.bed'),
    nbed: i18n('testhub.DataTypeShow.nbed')
};

const DataTypeShow = (props: Props) => {
    return (
        <>
            <Tag color="default">{map[props.data] == null ? props.data : map[props.data]}</Tag>
        </>
    );
};

export default DataTypeShow;
