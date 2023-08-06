import React, { useEffect, useState } from 'react';

import { Card, Descriptions, Tooltip } from 'antd';
import { ExecuteResult, ExecutionResult, FlowResult } from '@/typings/server/execution';
import MonacoEditor from 'react-monaco-editor/lib/editor';
import { useTheme } from '@/utils/hooks';
import i18n from '@/i18n';
interface IProps {
    className?: string;
    size: number,
    flowResult: FlowResult;
    executionResult: ExecutionResult;
    executeResult: ExecuteResult;
}



function Sql(props: IProps) {
    const themeColor = useTheme();
    const [theme, setTheme] = useState(themeColor == 'dark' ? 'BlackTheme' : 'default');
    const { executionResult, executeResult } = props;
    const data = executeResult.runStateItem.runParams;
    useEffect(() => {
        setTheme(themeColor == 'dark' ? 'BlackTheme' : 'default');
    }, [themeColor]);

    if (executeResult?.actionType.toUpperCase() == 'SQL' || executeResult?.actionType.toUpperCase() == 'SQL_CALL') {
        return (
            <>
                <Descriptions column={1}>
                    <Descriptions.Item label={<b>{i18n('sql.text.url')}</b>}>{data['url']}</Descriptions.Item>
                </Descriptions>
                <Descriptions column={props.size > 400 ? 3 : 2}>
                    <Descriptions.Item label={<b>{i18n('sql.text.Driver')}</b>}>{data['driver']}</Descriptions.Item>
                    <Descriptions.Item label={<b>{i18n('sql.text.User')}</b>}>{data['username']}</Descriptions.Item>
                    <Descriptions.Item label={<b>{i18n('sql.text.Password')}</b>}>{data['password']}</Descriptions.Item>
                    <Descriptions.Item label={<b>{i18n('sql.text.timeout')}</b>}>
                        <Tooltip title={i18n('sql.text.timeouttip')}>{data['timeout']}</Tooltip>
                    </Descriptions.Item>

                    <Descriptions.Item label={<b>{i18n('sql.text.query')}</b>}>
                        {data['flag'] ? i18n('sql.text.yes') : i18n('sql.text.no')}
                    </Descriptions.Item>
                    <Descriptions.Item label={<b>{i18n('sql.text.conkey')}</b>}>{data['conKey']}</Descriptions.Item>
                </Descriptions>
                <MonacoEditor
                    height={data['sql'] == undefined ? 50 : data['sql'].split("\n").length > 10 ? data['sql'].split("\n").length * 10 : 100}
                    language="sql"
                    value={data['sql']}
                    theme={theme}
                    options={{
                        minimap: { enabled: false },
                        readOnly: true,
                        scrollBeyondLastLine: false, // 设置编辑器是否可以滚动到最后一行之后
                        automaticLayout: true, // 自动布局
                        scrollbar: {
                            // vertical: 'hidden', // 关闭垂直滚动条
                            // horizontal: 'hidden', // 关闭水平滚动条

                            verticalScrollbarSize: 6,
                            horizontalScrollbarSize: 6,
                            verticalSliderSize: 6,
                            horizontalSliderSize: 6,
                            verticalHasArrows: false,
                            horizontalHasArrows: false,
                            arrowSize: 0,
                            useShadows: true,
                        },
                    }}
                />
            </>
        );
    }
    if (executeResult?.actionType.toUpperCase() == 'SQL_BEGIN' || executeResult?.actionType.toUpperCase() == 'SQL_COMMIT') {
        return (
            <>
                <Descriptions column={1}>
                    <Descriptions.Item label={<b>{i18n('sql.text.url')}</b>}>{data['url']}</Descriptions.Item>
                </Descriptions>
                <Descriptions column={props.size > 400 ? 3 : 2}>
                    <Descriptions.Item label={<b>{i18n('sql.text.Driver')}</b>}>{data['driver']}</Descriptions.Item>
                    <Descriptions.Item label={<b>{i18n('sql.text.User')}</b>}>{data['username']}</Descriptions.Item>
                    <Descriptions.Item label={<b>{i18n('sql.text.Password')}</b>}>{data['password']}</Descriptions.Item>
                    <Descriptions.Item label={<b>{i18n('sql.text.timeout')}</b>}>
                        <Tooltip title={i18n('sql.text.timeouttip')}>{data['timeout']}</Tooltip>
                    </Descriptions.Item>

                    <Descriptions.Item label={<b>{i18n('sql.text.query')}</b>}>
                        {data['flag'] ? i18n('sql.text.yes') : i18n('sql.text.no')}
                    </Descriptions.Item>
                    <Descriptions.Item label={<b>{i18n('sql.text.conkey')}</b>}>{data['conKey']}</Descriptions.Item>
                </Descriptions>
            </>
        );
    }

    return <></>
};

export default Sql;