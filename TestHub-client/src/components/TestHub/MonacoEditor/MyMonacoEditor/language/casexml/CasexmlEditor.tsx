import * as monacoEditor from 'monaco-editor/esm/vs/editor/editor.api';
import MonacoEditor from 'react-monaco-editor/lib/editor';
import React from 'react';

export type Props = {
  width: number | string;
  height: number | string;
  text: string;
  onChange: Function;
  options?: monacoEditor.editor.IStandaloneEditorConstructionOptions;
};

const CasexmlEditor: React.FC<Props> = (props) => {
  return (
    <MonacoEditor
      width={props.width}
      height={props.height}
      value={props.text}
      // theme="vs-dark"
      language="casexml"
      onChange={(text) => {
        props.onChange(text);
      }}
      // editorDidMount={(editor, monaco) => {
      //   monaco.languages.registerDocumentFormattingEditProvider('casexml', {
      //     async provideDocumentFormattingEdits(model, options, token) {
      //       return [
      //         {
      //           range: model.getFullModelRange(),
      //           text: xmlFormat(model.getValue()),
      //         },
      //       ];
      //     },
      //   });
      // }}
      options={props.options}
    />
  );
};

export default CasexmlEditor;
