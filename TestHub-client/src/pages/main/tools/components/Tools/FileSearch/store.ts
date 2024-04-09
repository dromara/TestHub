// import { UseBoundStoreWithEqualityFn, createWithEqualityFn } from 'zustand/traditional';
// import { devtools, persist } from 'zustand/middleware';
// import { shallow } from 'zustand/shallow';
// import { StoreApi } from 'zustand';


export interface IKeysState {
  keys: string[];
}


// const initKeys = {
//   keys: []
// }


// export const useFileSearchStore: UseBoundStoreWithEqualityFn<StoreApi<IKeysState>> = createWithEqualityFn(
//   devtools(
//     persist(
//       () => (initKeys),
//       {
//         name: 'tools-fileSearch',
//         getStorage: () => localStorage,
//         partialize: (state: IKeysState) => ({
//           holdingService: state.keys,
//         }),
//       },
//     ),
//   ),
//   shallow
// );


export const getStoreKeys = () => {
  const initKeys = localStorage.getItem('tools-fileSearch');
  if (initKeys) {
    return (JSON.parse(initKeys) as IKeysState).keys;
  }
  return [];
};

export const setStoreKeys = (keys: string[]) => {
  const initKeys: IKeysState = {
    keys: keys
  }
  return localStorage.setItem('tools-fileSearch', JSON.stringify(initKeys));
};




