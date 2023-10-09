import React, { useEffect, useState } from 'react';

import { RuleActionResDto } from '@/typings';
import MonacoEditor from 'react-monaco-editor';
import { useTheme } from '@/utils/hooks';
import { Descriptions } from 'antd';
interface IProps {
    data: RuleActionResDto;
}


function Sql(props: IProps) {
    const themeColor = useTheme();
    const [theme, setTheme] = useState(themeColor == 'dark' ? 'BlackTheme' : 'default');
    useEffect(() => {
        setTheme(themeColor == 'dark' ? 'BlackTheme' : 'default');
    }, [themeColor]);
    const { data } = props;
    return <>
        <Descriptions column={1} layout="vertical">
            <Descriptions.Item
                label={
                    <label>
                        <span style={{ color: '#f00' }}>* </span>sql脚本
                    </label>
                }
            >
                <MonacoEditor
                    language="sql"
                    height={200}
                    value={data.extraInto['sql']}
                    theme={theme}
                    options={{
                        minimap: { enabled: false },
                        // readOnly: true,
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
                /> </Descriptions.Item>
        </Descriptions>
    </>;


};

export default Sql;