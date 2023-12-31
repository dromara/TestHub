import { addColorSchemeListener } from '@/components/Setting/components/basic';
import { useCallback, useEffect, useRef, useState } from 'react';

export function useDebounce<A extends any[]>(
  callback: (...args: A) => void,
  timeout: number,
) {
  const timer = useRef<any>();
  return useCallback<(...args: A) => void>(
    (...args) => {
      if (timer.current) {
        clearTimeout(timer.current);
        timer.current = undefined;
      }
      timer.current = setTimeout(() => {
        callback(...args);
        timer.current = undefined;
      }, timeout);
    },
    [callback, timeout],
  );
}


export function useTheme() {
  let theme = localStorage.getItem('theme') || 'dark'
  if (theme === 'followOs') {
    theme = window.matchMedia && window.matchMedia('(prefers-color-scheme: dark)').matches ? 'dark' : 'default'
  }
  const [currentColorScheme, setCurrentColorScheme] = useState(theme);
  useEffect(() => {
    addColorSchemeListener(setCurrentColorScheme);
  }, []);
  return currentColorScheme;
}

export function useCanDoubleClick() {
  const count = useRef(0);
  return ({
    onClick,
    onDoubleClick,
  }: {
    onClick?: Function;
    onDoubleClick?: Function;
  }) => {
    count.current = count.current + 1;
    if (count.current == 2) {
      onDoubleClick && onDoubleClick();
      count.current = 0;
    } else {
      setTimeout(() => {
        if (count.current == 1) {
          onClick && onClick();
        }
        count.current = 0;
      }, 200);
    }
  };
}
