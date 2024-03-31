import React, { ForwardedRef, forwardRef, useEffect, useImperativeHandle, useRef, useState } from 'react';

import cs from 'classnames';
import styles from './index.less';
import { useTheme } from '@/hooks';
import MonacoEditor from 'react-monaco-editor';
import { editorDefaultOptions } from './editorConfig';
import * as monaco from 'monaco-editor/esm/vs/editor/editor.api';
import initMonacoTheme from './initMonaco';

export type IEditorIns = monaco.editor.IStandaloneCodeEditor;
export type IEditorOptions = monaco.editor.IStandaloneEditorConstructionOptions;
export type IEditorContentChangeEvent = monaco.editor.IModelContentChangedEvent;

interface IProps {
  language?: string;
  className?: string;
  options?: IEditorOptions;
  needDestroy?: boolean;
  defaultValue?: string;
  didMount?: (editor: IEditorIns) => any;
  shortcutKey?: (editor, monaco) => void;
  onChange?: (text) => void;
}

export interface IExportRefFunction {
  setValue: (text: any, range?: IRangeType) => void;
}

const MyMonacoEditor = (props: IProps, ref: ForwardedRef<IExportRefFunction>) => {
  const { className, language = 'json', shortcutKey, onChange, options } = props;
  const [appTheme] = useTheme();
  const [defaultValue, setDefaultValue] = useState(props.defaultValue);
  const [editor, setEditor] = useState(null);
  const [monaco, setMonaco] = useState(null); // 用于保存 monaco 实例

  useEffect(() => {
    if (onChange) {
      onChange(defaultValue);
    }
  }, [defaultValue]);

  useEffect(() => {
    initMonacoTheme();
  }, []);

  const editorDidMount = (editor, monaco) => {
    setEditor(editor);
    setMonaco(monaco); // 保存 monaco 实例以供后续使用
    editor.focus();
    shortcutKey?.(editor, monaco);
  };

  useImperativeHandle(ref, () => ({
    setValue,
  }));
  const setValue = (text: any, range?: IRangeType) => {
    appendMonacoValue(editor, monaco, text, range);
  };

  return (
    <>
      <MonacoEditor
        className={cs(className, styles.editorContainer)}
        language={language}
        onChange={(text) => {
          setDefaultValue(text);
        }}
        value={defaultValue}
        theme={appTheme.backgroundColor}
        editorDidMount={editorDidMount}
        options={{ ...editorDefaultOptions, ...options }}
      />
    </>
  );
};

export default forwardRef(MyMonacoEditor);

// text 需要添加的文本
// range 添加到的位置
// 'end' 末尾
// 'front' 开头
// 'cover' 覆盖掉原有的文字
// 自定义位置数组 new monaco.Range []
export type IRangeType = 'end' | 'front' | 'cover' | 'reset' | any;

export const appendMonacoValue = (editor: any, monaco: any, text: any, range: IRangeType = 'end') => {
  if (!editor) {
    return;
  }
  const model = editor?.getModel && editor.getModel(editor);

  let newRange: IRangeType = null;

  let newText = text;
  const lastLine = editor.getModel().getLineCount();
  const lastLineLength = editor.getModel().getLineMaxColumn(lastLine);

  newRange = new monaco.Range(lastLine, lastLineLength, lastLine, lastLineLength);

  switch (range) {
    // 覆盖所有内容
    case 'cover':
      newRange = model.getFullModelRange();
      editor.revealLine(lastLine);
      break;
    // 在开头添加内容
    case 'front':
      newRange = new monaco.Range(1, 1, 1, 1);
      editor.revealLine(1);
      editor.setPosition({ lineNumber: 1, column: 1 });
      break;
    // 格式化选中区域的sql
    case 'select': {
      const selection = editor.getSelection();
      if (selection) {
        newRange = new monaco.Range(
          selection.startLineNumber,
          selection.startColumn,
          selection.endLineNumber,
          selection.endColumn,
        );
      }
      break;
    }
    // 在末尾添加内容
    case 'end':
      newRange = new monaco.Range(lastLine, lastLineLength, lastLine, lastLineLength);
      newText = `${text}`;
      break;
    // 在光标处添加内容
    case 'cursor':
      {
        const position = editor.getPosition();
        if (position) {
          newRange = new monaco.Range(position.lineNumber, position.column, position.lineNumber, position.column);
        }
      }
      break;
    default:
      break;
  }

  const op = {
    range: newRange,
    text: newText,
  };

  editor.executeEdits('setValue', [op]);
  editor.focus();
};
