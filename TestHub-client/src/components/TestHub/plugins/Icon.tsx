import React, { } from 'react';

import i18n from '@/i18n';
import { Tag } from 'antd';
import { AllTypeMap } from './ActionType';

interface IProps {
    actionType: string;
    className: string;
}



{/* 

<Tag color="blue">blue</Tag>
<Tag color="geekblue">geekblue</Tag>
<Tag color="purple">purple</Tag> 
*/}


function ActionIcon(props: IProps) {
    const { actionType, className } = props;
    if (actionType.toUpperCase() == 'SLEEP') {
        return <Tag color="magenta" className={className}>{AllTypeMap.SLEEP}</Tag>
    } else if (actionType.toUpperCase() == 'SQL') {
        return <Tag color="red" className={className}>{AllTypeMap.SQL}</Tag>
    } else if (actionType.toUpperCase() == 'SQL_CALL') {
        return <Tag color="volcano" className={className}>{AllTypeMap.SQL_CALL}</Tag>
    } else if (actionType.toUpperCase() == 'SQL_BEGIN') {
        return <Tag color="orange" className={className}>{AllTypeMap.SQL_BEGIN}</Tag>
    } else if (actionType.toUpperCase() == 'SQL_COMMIT') {
        return <Tag color="gold" className={className}>{AllTypeMap.SQL_COMMIT}</Tag>
    } else if (actionType.toUpperCase() == 'CHECK_OBJ') {
        return <Tag color="lime" className={className}>{AllTypeMap.CHECK_OBJ}</Tag>
    } else if (actionType.toUpperCase() == 'CHECK') {
        return <Tag color="green" className={className}>{AllTypeMap.CHECK}</Tag>
    } else if (actionType.toUpperCase() == 'HTTP') {
        return <Tag color="cyan" className={className}>{AllTypeMap.HTTP}</Tag>
    } else if (actionType.toUpperCase() == 'CONST') {
        return <Tag color="blue" className={className}>{AllTypeMap.CONST}</Tag>
    }
    return <>{actionType.toUpperCase()}</>;
};

export default ActionIcon;