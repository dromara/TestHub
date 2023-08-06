import { useTheme } from '@/utils/hooks';
import React, { useEffect, useState } from 'react';
import { MonacoDiffEditor } from 'react-monaco-editor';
import styles from './index.less';
import './index.less';
export type Props = {
    cover: any;
    threshold: any;
    language: string | undefined;
    options?: any
};


export default function DiffObj(props: Props) {
    const [inlineView, setInlineView] = useState(false);
    const themeColor = useTheme();
    useEffect(() => {
        setTheme(themeColor == 'dark' ? 'BlackTheme' : 'default');
    }, [themeColor]);
    const [theme, setTheme] = useState(themeColor == 'dark' ? 'BlackTheme' : 'default');


    const scrollbar = {
        verticalScrollbarSize: 6,
        horizontalScrollbarSize: 6,
        verticalSliderSize: 6,
        horizontalSliderSize: 6,
        verticalHasArrows: false,
        horizontalHasArrows: false,
        arrowSize: 0,
        useShadows: true,
    }

    const options = {
        minimap: { enabled: false },
        renderSideBySide: !inlineView,
        originalEditable: false,
        readOnly: true,
        scrollBeyondLastLine: false, // 设置编辑器是否可以滚动到最后一行之后
        automaticLayout: true, // 自动布局
        scrollbar: scrollbar
    }


    return (
        <MonacoDiffEditor
            width="100%"
            height="400"
            theme={theme}
            language={props.language}
            original={typeof props.cover == 'object' ? JSON.stringify(props.cover, null, 2) : props.cover}
            value={typeof props.threshold == 'object' ? JSON.stringify(props.threshold, null, 2) : props.threshold}
            options={props.options ? { ...props.options, scrollbar: scrollbar } : options}
        />
    );
}
