import { UseBoundStoreWithEqualityFn, createWithEqualityFn } from 'zustand/traditional';
import { devtools, persist } from 'zustand/middleware';
import { shallow } from 'zustand/shallow';
import { StoreApi } from 'zustand';

import { message } from 'antd';
import i18n from '@/i18n';

export interface ISettingState {
  hasWhite: boolean;
  holdingService: boolean;
}

const initSetting = {
  remainingUse: undefined,
  hasWhite: false,
  holdingService: false,
}

export const useSettingStore: UseBoundStoreWithEqualityFn<StoreApi<ISettingState>> = createWithEqualityFn(
  devtools(
    persist(
      () => (initSetting),
      {
        name: 'global-setting',
        getStorage: () => localStorage,
        // 工作区的状态只保存 layout布局信息
        partialize: (state: ISettingState) => ({
          holdingService: state.holdingService,
        }),
      },
    ),
  ),
  shallow
);




