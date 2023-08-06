import { Card, Tabs, message } from 'antd';
import React, { useCallback, useEffect, useState } from 'react';
import classnames from 'classnames';
import styles from './index.less';
import './index.less';
import { IToolsPageState } from 'umi';
import Iconfont from '@/components/Iconfont';
import { ToolsTypeCode } from '@/utils/constants';
interface IProps {
    toolsPage: IToolsPageState;
    dispatch: any;
}

export interface IToolsConfg {
    name: string;
    code: string;
    icon: string;
}





function ToolsMain(props: IProps) {
    const { dispatch, toolsPage } = props;
    const tools: IToolsConfg[] = [
        // { name: "HTTP工具", code: ToolsTypeCode.HTTP, icon: "\ue60a" },
        { name: "文本对比", code: ToolsTypeCode.COMPAR, icon: "\ue63c" },
        { name: "字母工具", code: ToolsTypeCode.LETTER, icon: "\ue648" },
        { name: "JSON预览", code: ToolsTypeCode.JSON, icon: "\ue7bd" },
        { name: "时间工具", code: ToolsTypeCode.TIME, icon: "\ue65b" }];


    return <div className={styles.toolsList}>
        {tools.map((t) => {
            return (
                <div key={t.code} className={styles.toolsItem} onClick={() => {
                    dispatch({
                        type: 'toolsPage/addConsole',
                        payload: {
                            key: t.code, name: t.name, data: {}
                        },
                    })
                }}>
                    <div className={styles.toolsItemMain}>
                        <div className={styles.toolsItemLeft}>
                            <div className={styles.logoBox}>
                                <Iconfont code={t.icon} size={20} />
                            </div>
                            {t.name}
                        </div>
                        <div className={styles.toolsItemRight}>
                            <Iconfont code="&#xe727;" />
                        </div>
                    </div>
                </div>
            );
        })}
    </div>
};
export default ToolsMain;