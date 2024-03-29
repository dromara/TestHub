import React, { memo, useState } from 'react';
import classnames from 'classnames';
import Iconfont from '@/components/Iconfont'
import { Input } from 'antd';
import i18n from '@/i18n';
import styles from './index.less';

interface IProps {
  text?: string;
  className?: string;
  placeholder: string;
  onChange: (value: string) => void;
}

export default memo<IProps>(function SearchInput({ text, className, placeholder, onChange }) {
  const [isFocus, setIsFocus] = useState(false);
  return <div className={classnames(className, styles.searchInput, { [styles.focus]: isFocus })}>
    <Iconfont code="&#xe8ba;" />
    <Input
      onBlur={() => { setIsFocus(false) }}
      onFocus={() => { setIsFocus(true) }}
      type="text"
      placeholder={placeholder}
      defaultValue={text}
      onChange={(e) => {
        onChange(e.target.value)
      }}
    />
  </div>
})
