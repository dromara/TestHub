import React from 'react';
import Highlighter from 'react-highlight-words';
import styles from './index.less';
interface IProps {
  words: string[];
  text: string;
  className?: string;
}
function TextHighlighter(props: IProps) {
  const highlightStyle = {
    color: 'red', // 设置高亮文本的颜色为红色
    fontWeight: 'bold', // 可以根据需要设置其他样式，比如加粗
  };
  const { className, words, text } = props;
  return (
    <Highlighter
      highlightClassName={styles.highlightStyle}
      searchWords={words}
      autoEscape={true}
      textToHighlight={text}
      className={className}
    />
  );
}

export default TextHighlighter;
