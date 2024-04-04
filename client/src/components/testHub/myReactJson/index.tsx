import { ThemeType } from '@/constants';
import { useTheme } from '@/hooks';
import React, { useCallback, useContext, useEffect, useMemo, useRef, useState } from 'react';
import ReactJson, { ThemeObject } from 'react-json-view';

interface IProps {
  data: any;
}

const darkTheme: ThemeObject = {
  base00: '#1b1c21', //背景色
  base01: '#383830', //边框颜色
  base02: '#49483e', //文本颜色
  base03: '#75715e', //注释颜色
  base04: '#a59f85', //属性名称颜色
  base05: '#f8f8f2', //字符串值颜色
  base06: '#f5f4f1', //数字值颜色
  base07: '#f9f8f5', //布尔值颜色
  base08: '#f92672', //null 值颜色
  base09: '#fd971f', //链接颜色
  base0A: '#f4bf75', //关键字颜色
  base0B: '#a6e22e', //数组标记颜色
  base0C: '#a1efe4', //对象标记颜色
  base0D: '#66d9ef', //函数颜色
  base0E: '#ae81ff', //日期颜色
  base0F: '#cc6633', //其他值颜色
};
const defaultTheme: ThemeObject = {
  base00: '#ffffff',
  base01: '#df5000',
  base02: '#4f4f4f',
  base03: '#d0d0d0',
  base04: '#808080',
  base05: '#808080',
  base06: '#4f4f4f',
  base07: '#000000',
  base08: '#c40000',
  base09: '#aa8c00',
  base0A: '#986800',
  base0B: '#007400',
  base0C: '#0074d9',
  base0D: '#0000d3',
  base0E: '#c100c1',
  base0F: '#00aaff',
};

function MyReactJson(props: IProps) {
  const [appTheme] = useTheme();

  const [theme, setTheme] = useState<ThemeObject>(
    appTheme.backgroundColor.includes(ThemeType.Dark) ? darkTheme : defaultTheme,
  );

  useEffect(() => {
    setTheme(appTheme.backgroundColor.includes(ThemeType.Dark) ? darkTheme : defaultTheme);
  }, [appTheme.backgroundColor]);

  return (
    <>
      <ReactJson
        src={props.data}
        theme={theme}
        collapsed={true}
        displayObjectSize={false}
        enableClipboard={true}
        displayDataTypes={false}
        iconStyle="square"
        collapseStringsAfterLength={20}
      />
    </>
  );
}

export default MyReactJson;
