import { EditorThemeType } from '@/constants';
import * as monaco from 'monaco-editor/esm/vs/editor/editor.api';

const initMonacoTheme = () => {
  monaco.editor.defineTheme(EditorThemeType.DashboardLightTheme, {
    base: 'vs',
    inherit: true,
    rules: [{ background: '#f8f9fa' }] as any,
    colors: {
      'editor.foreground': '#000000',
      'editor.background': '#f8f9fa', //背景色
    },
  });
  monaco.editor.defineTheme(EditorThemeType.DashboardBlackTheme, {
    base: 'vs-dark',
    inherit: true,
    rules: [{ background: '#131418' }] as any,
    colors: {
      'editor.foreground': '#ffffff',
      'editor.background': '#131418', //背景色
    },
  });
};

export default initMonacoTheme;
