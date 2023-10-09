import React, { } from 'react';

import i18n from '@/i18n';
import { Tag } from 'antd';
import { AllTypeMap } from './ActionType';

interface IProps {
    actionType: string;
}



{/* 

<Tag color="blue">blue</Tag>
<Tag color="geekblue">geekblue</Tag>
<Tag color="purple">purple</Tag> 
*/}


function Icon(props: IProps) {
    const { actionType } = props;
    if (actionType.toUpperCase() == 'SLEEP') {
        return <Tag color="magenta">{AllTypeMap.SLEEP}</Tag>
    } else if (actionType.toUpperCase() == 'SQL') {
        return <Tag color="red">{AllTypeMap.SQL}</Tag>
    } else if (actionType.toUpperCase() == 'SQL_CALL') {
        return <Tag color="volcano">{AllTypeMap.SQL_CALL}</Tag>
    } else if (actionType.toUpperCase() == 'SQL_BEGIN') {
        return <Tag color="orange">{AllTypeMap.SQL_BEGIN}</Tag>
    } else if (actionType.toUpperCase() == 'SQL_COMMIT') {
        return <Tag color="gold">{AllTypeMap.SQL_COMMIT}</Tag>
    } else if (actionType.toUpperCase() == 'CHECK_OBJ') {
        return <Tag color="lime">{AllTypeMap.CHECK_OBJ}</Tag>
    } else if (actionType.toUpperCase() == 'CHECK') {
        return <Tag color="green">{AllTypeMap.CHECK}</Tag>
    } else if (actionType.toUpperCase() == 'HTTP') {
        return <Tag color="cyan">{AllTypeMap.HTTP}</Tag>
    }
    return <>{actionType.toUpperCase()}</>;
};

export default Icon;