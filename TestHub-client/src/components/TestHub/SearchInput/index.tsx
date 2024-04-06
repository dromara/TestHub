import React, { memo, useState } from 'react';
import classnames from 'classnames';
import Iconfont from '@/components/base/Iconfont';
import { Input } from 'antd';
import i18n from '@/i18n';
import styles from './index.less';

interface IProps {
  text?: string;
  className?: string;
  placeholder: string;
  onChange: (value: string) => void;
}

function SearchInput(props: IProps) {
  const { text, className, placeholder, onChange } = props;
  const [isFocus, setIsFocus] = useState(false);
  return (
    <Input
      placeholder={placeholder}
      prefix={<Iconfont code="&#xe8ba;" />}
      className={className}
      defaultValue={text}
      onChange={(e) => {
        onChange(e.target.value);
      }}
    />

    // <div className={classnames(className, styles.searchInput, { [styles.focus]: isFocus })}>
    //   <Iconfont code="&#xe8ba;" />
    //   <Input
    //     onBlur={() => {
    //       setIsFocus(false);
    //     }}
    //     onFocus={() => {
    //       setIsFocus(true);
    //     }}
    //     type="text"
    //     placeholder={placeholder}
    //     defaultValue={text}
    //     onChange={(e) => {
    //       onChange(e.target.value);
    //     }}
    //   />
    // </div>
  );
}

export default SearchInput;
