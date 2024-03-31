import React, { useEffect, useState } from 'react';

import { Descriptions } from 'antd';
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



function App(props: IProps) {
    const themeColor = useTheme();
    const [theme, setTheme] = useState(themeColor == 'dark' ? 'BlackTheme' : 'default');
    const { executionResult, executeResult } = props;
    const data = executeResult.runStateItem.runParams;
    useEffect(() => {
        setTheme(themeColor == 'dark' ? 'BlackTheme' : 'default');
    }, [themeColor]);

    return (
        <>
            <Descriptions column={props.size > 500 ? 5 : 3}>
                <Descriptions.Item label={<b>{i18n('app.text.ip')}</b>}>{data['appIp']}</Descriptions.Item>
                <Descriptions.Item label={<b>{i18n('app.text.appPort')}</b>}>{data['appPort']}</Descriptions.Item>
                <Descriptions.Item label={<b>{i18n('app.text.appTimeout')}</b>}>{data['appTimeout']}</Descriptions.Item>
                <Descriptions.Item label={<b>{i18n('app.text.errorCode')}</b>}>{data['planErrorCode']}</Descriptions.Item>
                <Descriptions.Item label={<b>{i18n('app.text.response')}</b>}>{data['errorCode']}</Descriptions.Item>
            </Descriptions>

            <Descriptions column={1}>
                <Descriptions.Item label={<b>{i18n('app.text.request')}</b>}>
                    {/* <Alert
                        message={ */}
                    <MonacoEditor
                        width={props.size - 100}
                        height="150"
                        language="datapack"
                        value={data['request']}
                        theme={theme}
                        options={{
                            minimap: { enabled: false },
                            readOnly: true,
                            scrollBeyondLastLine: false, // 设置编辑器是否可以滚动到最后一行之后
                            automaticLayout: true, // 自动布局
                            scrollbar: {
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
                    {/* }
                         type={"info"}
                         showIcon={false}
                     banner
                 /> */}
                </Descriptions.Item>
                <Descriptions.Item label={<b>{i18n('app.text.response')}</b>}>
                    {/* <Alert
                        message={ */}
                    <MonacoEditor
                        width={props.size - 100}
                        height="150"
                        language="datapack"
                        value={data['response']}
                        options={{
                            readOnly: true,
                            selectOnLineNumbers: true,
                            minimap: {
                                // 关闭代码缩略图
                                enabled: false, // 是否启用预览图
                            },
                            scrollBeyondLastLine: false, // 设置编辑器是否可以滚动到最后一行之后
                            scrollbar: {
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
                    {/* }
                        type={data['errorFlag'] ? 'error' : 'success'}
                        showIcon={false}
                        banner
                    /> */}
                </Descriptions.Item>
            </Descriptions>
        </>
    );
};

export default App;