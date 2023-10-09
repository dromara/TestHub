import { IConsoleInfo } from '@/typings';
import { ToolsTypeCode } from '@/utils/constants';
import { Effect, Reducer } from 'umi';

export interface IAssemblyPageState {
    activeKey: string;
    searchKey: string | undefined;
}



export interface IAssemblyPageType {
    namespace: 'assemblyPage',
    state: IAssemblyPageState,
    reducers: {
        setActiveKey: Reducer<any>;//设置控制台激活tab页
        setSearchKey: Reducer<any>;//搜索
    };
    effects: {

    };
}

const AssemblyPageModel: IAssemblyPageType = {
    namespace: 'assemblyPage',
    state: {
        activeKey: 'actionPool',
        searchKey: ''
    },

    reducers: {
        setActiveKey(state, { payload }) {
            return { ...state, activeKey: payload };
        },
        setSearchKey(state, { payload }) {
            return { ...state, searchKey: payload };
        },
    },
    effects: {

    }
};



export default AssemblyPageModel;
