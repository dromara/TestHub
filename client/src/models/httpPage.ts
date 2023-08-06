import { IConsoleInfo } from '@/typings';
import { ToolsTypeCode } from '@/utils/constants';
import { Effect, Reducer } from 'umi';

export interface IHttpPageState {
    consoles: IConsoleInfo<any>[];
    activeKey: number;
}

export interface IHttpPageType {
    namespace: 'httpPage',
    state: IHttpPageState,
    reducers: {
        setActiveKey: Reducer<any>;//设置控制台激活tab页
        addConsole: Reducer<any>;//新增tab页
    };
    effects: {

    };
}

const HttpPageModel: IHttpPageType = {
    namespace: 'httpPage',
    state: {
        consoles: [],
        activeKey: 0
    },

    reducers: {
        setActiveKey(state, { payload }) {
            return { ...state, activeKey: payload };
        },
        addConsole(state, { payload }) {
            let oldConsoles: IConsoleInfo<any>[] = state.consoles;
            if (oldConsoles.filter(item => item.key == payload.key).length == 0) {
                oldConsoles.push(payload);
                return { ...state, consoles: [...oldConsoles], activeKey: payload.key };
            } else {
                return { ...state, activeKey: payload.key };
            }
        },
    },
    effects: {

    }
};



export default HttpPageModel;
