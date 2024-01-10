import { BaseConsoleInfo, BaseConsoleStatus, HttpConsoleInfo, TreeNodeResDto } from '@/typings';
import httpService, { } from '@/service/plugins/http/httpApi';
import systemService from '@/service/system';
import { Effect, Reducer } from 'umi';
import { HTTP } from '@/components/testHub/http/typings';
import { HttpApiSendResDto } from '@/typings/server/plugins/http';

export interface IHttpPageState {
    consoles: BaseConsoleInfo<TreeNodeResDto, HttpConsoleInfo>[];
    httpTrees?: TreeNodeResDto[];
    isLoaded: boolean;
    showResult: boolean;
    activeKey?: string;
    searchInfo?: string;
}



export interface IHttpPageType {
    namespace: 'httpPage',
    state: IHttpPageState,
    reducers: {
        setActiveKey: Reducer<any>;//设置控制台激活tab页
        setHttpTrees: Reducer<any>;//设置http 树
        addHttpConsole: Reducer<any>;//新增tab页
        setSendResult: Reducer<any>;//设置执行结果
        setShowResult: Reducer<any>;//设置执行结果
        setNodeShow: Reducer<any>;//设置节点关闭或者打开
        delConsole: Reducer<any>;//关闭tab页
    };
    effects: {
        loadHttpTrees: Effect;//获取项目列表
        addTree: Effect;//新增类目树
        updateTree: Effect;//编辑类目树
        newApiConsole: Effect;//新建用例控制台tab
        loadApi: Effect;//获取APi数据
        sendApi: Effect;//发送APi请求
    };
}

function toggleShowByKey(nodes: TreeNodeResDto[], targetKey: string): TreeNodeResDto[] {
    return nodes.map(node => {
        if (node.key === targetKey) {
            return { ...node, show: node.show == undefined ? true : !node.show }; // 找到目标节点，取反 show 属性
        }
        if (node.children) {
            node.children = toggleShowByKey(node.children, targetKey); // 递归处理子节点
        }
        return node;
    });
}

const HttpPageModel: IHttpPageType = {
    namespace: 'httpPage',
    state: {
        consoles: [],
        isLoaded: false,
        showResult: true
    },

    reducers: {
        setActiveKey(state, { payload }) {
            return { ...state, activeKey: payload };
        },
        addHttpConsole(state, { payload }) {
            let oldConsoles: BaseConsoleInfo<TreeNodeResDto, HttpConsoleInfo>[] = state.consoles;
            if (oldConsoles.filter(item => item.key == payload.node.key).length == 0) {
                let console: BaseConsoleInfo<TreeNodeResDto, HttpConsoleInfo> = { ...payload.node, data: payload.node, page: payload.page == undefined ? {} : payload.page, status: payload.status }
                oldConsoles.push(console);
                return { ...state, consoles: [...oldConsoles], activeKey: payload.node.key };
            } else {
                return { ...state, activeKey: payload.node.key };
            }
        },
        setHttpTrees(state, { payload }) {
            return { ...state, isLoaded: true, httpTrees: payload };
        },
        setSendResult(state, { payload }) {
            let oldConsoles: BaseConsoleInfo<TreeNodeResDto, HttpConsoleInfo>[] = state.consoles;
            const index = oldConsoles.findIndex(item => item.key === payload.key);
            oldConsoles[index].page.resultData = payload.data;
            return { ...state, showResult: true, consoles: [...oldConsoles] };
        },
        setShowResult(state, { payload }) {
            return { ...state, showResult: payload };
        },
        setNodeShow(state, { payload }) {
            const httpTrees = toggleShowByKey(state.httpTrees, payload)
            return { ...state, httpTrees: httpTrees };
        },
        delConsole(state, { payload }) {
            let oldConsoles: BaseConsoleInfo<TreeNodeResDto, HttpConsoleInfo>[] = state.consoles;
            const index = oldConsoles.findIndex(item => item.key === payload);
            let newConsoles = oldConsoles.filter(item => item.key != payload);
            if (index == 0 && newConsoles.length > 0) {
                //删除第一个 后面还有 切换到后边的一个 新控制台的第一个
                return { ...state, consoles: [...newConsoles], activeKey: newConsoles[index].key };
            } else if (index == 0 && newConsoles.length == 0) {
                //删除第一个 后面没有 切换到空
                return { ...state, consoles: [...newConsoles], activeKey: undefined };
            } else if (index > 0) {
                //不是删除第一个 前边必然有有 切换到前一个
                return { ...state, consoles: [...newConsoles], activeKey: newConsoles[index - 1].key };
            }

        },
    },
    effects: {
        *loadHttpTrees({ payload, callback }, { put, select }) {
            try {
                const resDtos = (yield httpService.getTree(payload)) as TreeNodeResDto[];
                yield put({
                    type: 'setHttpTrees',
                    payload: resDtos,
                });
                if (callback && typeof callback === 'function') {
                    callback(resDtos);
                }
            }
            catch {
            }
        },
        *addTree({ payload, callback }, { put, select }) {
            console.log();
            try {
                const treeNode = (yield httpService.saveTree({ ...payload })) as TreeNodeResDto;
                if (callback && typeof callback === 'function') {
                    callback(treeNode);
                }
            }
            catch {
                if (callback && typeof callback === 'function') {
                    callback(false);
                }
            }
        },
        *updateTree({ payload, callback }, { put, select }) {
            try {
                const treeNode = (yield httpService.updateTree({ ...payload })) as TreeNodeResDto;
                if (callback && typeof callback === 'function') {
                    callback(treeNode);
                }
            }
            catch {
            }
        },
        *newApiConsole({ payload, callback }, { put, select }) {
            try {
                const key = (yield systemService.getId()) as string;
                const node: TreeNodeResDto = {
                    key: key,
                    name: payload.name,
                    parentKey: payload.parentKey,

                    nodeType: "API",
                    leaf: false,
                    nameDiv: payload.name,
                }
                yield put({
                    type: 'addHttpConsole',
                    payload: {
                        node: node,
                        status: BaseConsoleStatus.DRAFT
                    },
                });

                if (callback && typeof callback === 'function') {
                    callback();
                }
            }
            catch {
            }
        },
        *loadApi({ payload, callback }, { put, select }) {
            try {
                const apiData = (yield httpService.getApi({ id: payload })) as HTTP.HttpRequestResDto;

                if (callback && typeof callback === 'function') {
                    callback(apiData);
                }
            }
            catch {
            }
        },
        *sendApi({ payload, callback }, { put, select }) {
            try {
                const resData = (yield httpService.sendApi(payload)) as HttpApiSendResDto;
                if (callback && typeof callback === 'function') {
                    callback(resData);
                }
            }
            catch {
            }
        },

    }
};



export default HttpPageModel;
