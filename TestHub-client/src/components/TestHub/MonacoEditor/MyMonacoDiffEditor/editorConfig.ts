import * as monaco from 'monaco-editor/esm/vs/editor/editor.api';
export type IEditorOptions = monaco.editor.IStandaloneDiffEditorConstructionOptions;

export const editorDefaultOptions: IEditorOptions = {
  fontFamily:
    `"Menlo", 
    "DejaVu Sans Mono", 
    "Liberation Mono", 
    "Consolas", 
    "Ubuntu Mono", 
    "Courier New", 
    "andale mono", 
    "lucida console", 
    "monospace"`,

  renderSideBySide: true, // 并排展示差异
  originalEditable: false,
  useInlineViewWhenSpaceIsLimited: false,

  overviewRulerBorder: false,
  scrollBeyondLastLine: false, // 滚动超过最后一行
  automaticLayout: true, // 自动布局
  dragAndDrop: false, // 拖拽
  fontSize: 12, // 字体大小
  lineHeight: 18, // 行高
  // theme: 'vscode', // 主题
  roundedSelection: false, // 圆角选择
  readOnly: false, // 只读
  folding: true, // 显示折叠
  autoClosingQuotes: 'always', // 自动闭合引号
  wordWrap: 'off', // 自动换行
  fixedOverflowWidgets: true, // 固定溢出小部件
  // renderLineHighlight: 'none', // 渲染行高亮
  codeLens: false, // 代码镜头
  scrollbar: {
    // 滚动条
    // alwaysConsumeMouseWheel: false, // 总是消耗鼠标滚轮
    verticalScrollbarSize: 6,
    horizontalScrollbarSize: 6,
    verticalSliderSize: 6,
    horizontalSliderSize: 6,
    verticalHasArrows: false,
    horizontalHasArrows: false,
    arrowSize: 0,
    useShadows: true,
  },
  unicodeHighlight: {
    ambiguousCharacters: false,
    invisibleCharacters: false,
  },
  // padding: {
  //   top: 2,
  //   bottom: 2,
  // },
  minimap: {
    // 缩略图
    enabled: false, // 启用
  },
};
