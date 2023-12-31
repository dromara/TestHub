import React, { forwardRef, useEffect, useImperativeHandle, useState } from 'react';

import { RuleActionResDto } from '@/typings';
import MonacoEditor from 'react-monaco-editor';
import { useTheme } from '@/utils/hooks';
import { Descriptions } from 'antd';
import i18n from '@/i18n';
interface IProps {
    data: RuleActionResDto;
}

const Sql = forwardRef((props: IProps, ref) => {
    const themeColor = useTheme();
    const [theme, setTheme] = useState(themeColor == 'dark' ? 'BlackTheme' : 'default');
    useEffect(() => {
        setTheme(themeColor == 'dark' ? 'BlackTheme' : 'default');
    }, [themeColor]);
    const [data, setData] = useState(props.data.extraInto ? props.data : { ...props.data, extraInto: { bound: "" } });
    useImperativeHandle(ref, () => ({
        getData: async () => {
            if (data.extraInto.bound == "") {
                return { flag: false, data: data };
            }
            console.log(data.extraInto.bound)
            return { flag: true, data: { bound: data.extraInto.bound } };
        },
    }));
    return <>
        <Descriptions column={1} layout="vertical">
            <Descriptions.Item
                label={
                    <label>
                        <span style={{ color: '#f00' }}>* </span>SQL脚本
                    </label>
                }
            >
                <MonacoEditor
                    language="sql"
                    height={200}
                    value={data.extraInto.bound}
                    theme={theme}
                    onChange={(text) => {
                        const newDatas = data;
                        newDatas.extraInto.bound = text;
                        setData(newDatas);
                    }}
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

});


export default Sql;