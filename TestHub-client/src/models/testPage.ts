
import { ConsoleInfo } from '@/typings/client';
import { Effect, Reducer } from 'umi';
import isEqual from 'lodash/isEqual';
import cloneDeep from 'lodash/cloneDeep';

export interface ITestPageState {
    activeKey?: string;
    consoles: ConsoleInfo<any, any>[];
}

export interface ITestPageType {
    namespace: 'testPage',
    state: ITestPageState,
    reducers: {
        setActiveKey: Reducer<any>;//设置控制台激活tab页
        addConsole: Reducer<any>;//新增tab页
        delConsole: Reducer<any>;//关闭tab页
    };
}

const TestPageModel: ITestPageType = {
    namespace: 'testPage',

    state: {
        // activeKey: "02"
        consoles: [],
    },

    reducers: {
        setActiveKey(state, { payload }) {
            return { ...state, activeKey: payload };
        },
        addConsole(state, { payload }) {
            const oldConsoles: ConsoleInfo<any, any>[] = state.consoles;
            if (oldConsoles.filter(item => item.key == payload.node.key).length == 0) {
                const console: ConsoleInfo<any, any> = {
                    key: payload.node.key,
                    name: payload.node.name,
                    status: payload.status,
                    data: payload.node,
                    oldData: cloneDeep(payload.node),
                    page: payload.page == undefined ? {} : payload.page,
                }
                oldConsoles.push(console);
                return { ...state, consoles: [...oldConsoles], activeKey: payload.node.key };
            } else {
                return { ...state, activeKey: payload.node.key };
            }
        },
        delConsole(state, { payload }) {
            return { ...state };
        }
    }
};



export default TestPageModel;
