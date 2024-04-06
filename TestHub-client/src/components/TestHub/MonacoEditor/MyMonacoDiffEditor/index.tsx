import { useTheme } from '@/hooks';
import React, { useEffect, useState } from 'react';
import { MonacoDiffEditor } from 'react-monaco-editor';
import { editorDefaultOptions } from './editorConfig';
export type Props = {
  cover: any;
  threshold: any;
  language: string | undefined;
  options?: any;
  width?: string | number;
  height?: string | number;
};

export default function MyMonacoDiffEditor(props: Props) {
  const [inlineView, setInlineView] = useState(false);
  const [appTheme] = useTheme();


  const options = {
    renderSideBySide: true, // 并排展示差异
    readOnly: true, // 将编辑器设置为只读模式
    originalEditable: false,
  };

  return (
    <MonacoDiffEditor
      width={props.width}
      height={props.height}
      theme={appTheme.backgroundColor}
      language={props.language}
      original={typeof props.cover == 'object' ? JSON.stringify(props.cover, null, 2) : props.cover}
      value={typeof props.threshold == 'object' ? JSON.stringify(props.threshold, null, 2) : props.threshold}
      options={{ ...editorDefaultOptions }}
    // options={{ ...editorDefaultOptions, ...options, ...props.options }}
    />
  );
}
