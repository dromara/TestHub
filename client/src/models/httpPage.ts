import { ConsoleInfo, ConsoleStatus, HttpConsoleInfo } from '@/typings/client';

import httpService, { } from '@/service/plugins/http/httpApi';
import { HTTP } from '@/components/TestHub/http/typings';
import { TreeNodeResDto } from '@/typings/server/base';

import { HttpApiSendResDto } from '@/typings';
import systemService from '@/service/system';
import cloneDeep from 'lodash/cloneDeep';
import isEqual from 'lodash/isEqual';
import { v4 as uuid } from 'uuid';


import { Effect, Reducer } from 'umi';

export interface HttpPageData extends HTTP.HttpRequestResDto {

}
export interface HttpPageInfo extends HttpConsoleInfo {

}


export interface IHttpPageState {
    isLoaded: boolean;
    showResult: boolean;
    activeKey?: string;
    consoles: ConsoleInfo<HttpPageData, HttpPageInfo>[];
    trees?: Map<string, TreeNodeResDto<HttpPageData>>;
}

export interface IHttpPageType {
    namespace: 'httpPage',
    state: IHttpPageState,
    reducers: {
        putTree: Reducer<any>;//设置http 树节点
        setTrees: Reducer<any>;//设置http 树
        setActiveKey: Reducer<any>;//设置控制台激活tab页
        addConsole: Reducer<any>;//新增tab页
        delConsole: Reducer<any>;//关闭tab页
        setConsolePage: Reducer<any>;//设置页面信息
        setConsoleData: Reducer<any>;//设置页面数据
        saveConsoleData: Reducer<any>;//保存页面数据  后台保存之后
        setSendResult: Reducer<any>;//设置执行结果
        setShowResult: Reducer<any>;//设置执行结果
        editConsoleName: Reducer<any>;//设置名称

    };
    effects: {
        loadTrees: Effect;//获取项目列表
        saveApi: Effect;//新增类目树
        updateApi: Effect;//编辑类目树
        loadApi: Effect;//获取APi数据
        sendApi: Effect;//发送APi请求
        saveTree: Effect;//新增类目树
        updateTree: Effect;//编辑类目树
        rename: Effect;//重命名树节点、接口
        newApiConsole: Effect;//新建用例控制台tab
    };
}

const HttpPageModel: IHttpPageType = {
    namespace: 'httpPage',

    state: {
        showResult: true,
        isLoaded: false,
        consoles: [],

    },

    reducers: {
        putTree(state, { payload }) {
            const oldTrees: Map<string, TreeNodeResDto<HttpPageData>> = state.trees;

            if (payload.node.index in oldTrees) {
                payload.node.children = oldTrees[payload.node.index].children;
            }
            oldTrees[payload.node.index] = payload.node;

            if (payload.oldParentId != undefined && payload.oldParentId != payload.parentId) {
                //之前的兄弟节点
                const oldChildren: string[] = oldTrees[payload.oldParentId].children;
                oldTrees[payload.oldParentId].children = oldChildren.filter(node => node !== payload.node.index)

                //现在的兄弟节点
                const nowChildren: string[] = oldTrees[payload.parentId].children;
                if (nowChildren.filter(item => item == payload.node.index).length == 0) {
                    nowChildren.push(payload.node.index);
                    oldTrees[payload.parentId].children = nowChildren;
                }
            } else {
                //现在的兄弟节点
                const nowChildren: string[] = oldTrees[payload.parentId].children;
                if (nowChildren.filter(item => item == payload.node.index).length == 0) {
                    nowChildren.push(payload.node.index);
                    oldTrees[payload.parentId].children = nowChildren;
                }
            }

            return { ...state, isLoaded: true, trees: { ...oldTrees } };
        },
        setTrees(state, { payload }) {
            return { ...state, isLoaded: true, trees: payload };
        },
        setActiveKey(state, { payload }) {
            return { ...state, activeKey: payload };
        },
        addConsole(state, { payload }) {
            const oldConsoles: ConsoleInfo<HttpPageData, HttpPageInfo>[] = state.consoles;
            if (oldConsoles.filter(item => item.key == payload.node.key).length == 0) {
                const console: ConsoleInfo<HttpPageData, HttpPageInfo> = {
                    key: payload.node.key,
                    name: payload.node.name,
                    status: payload.status ? payload.status : ConsoleStatus.SAVED,
                    data: payload.node.info,
                    oldData: cloneDeep(payload.node.info),
                    page: payload.page == undefined ? {} : payload.page,
                }
                oldConsoles.push(console);
                return { ...state, consoles: [...oldConsoles], activeKey: payload.node.key };
            } else {
                return { ...state, activeKey: payload.node.key };
            }
        },
        delConsole(state, { payload }) {
            const oldConsoles: ConsoleInfo<HttpPageData, HttpPageInfo>[] = state.consoles;
            const filteredConsoles = oldConsoles.filter(item => !payload.includes(item.key));
            return { ...state, consoles: [...filteredConsoles] };
        },
        setConsolePage(state, { payload }) {
            const oldConsoles: ConsoleInfo<HttpPageData, HttpPageInfo>[] = state.consoles;
            oldConsoles[oldConsoles.findIndex(item => item.key == payload.key)].page = payload.page;
            return { ...state, consoles: oldConsoles };
        },
        setConsoleData(state, { payload }) {
            const oldConsoles: ConsoleInfo<HttpPageData, HttpPageInfo>[] = state.consoles;
            const index = oldConsoles.findIndex(item => item.key == payload.key);
            const oldConsole = oldConsoles[index];

            oldConsole.data = payload.data;
            if (oldConsole.status != ConsoleStatus.UNTRACKED) {
                if (isEqual(oldConsole.data, oldConsole.oldData)) {
                    oldConsole.status = ConsoleStatus.SAVED
                } else {
                    oldConsole.status = ConsoleStatus.CHANGE
                    // console.log(findDifferentProperties(oldConsole.data, oldConsole.oldData))
                }
            }
            oldConsoles[index] = { ...oldConsole };
            return { ...state, consoles: [...oldConsoles] };
        },
        saveConsoleData(state, { payload }) {
            const oldConsoles: ConsoleInfo<HttpPageData, HttpPageInfo>[] = state.consoles;
            const index = oldConsoles.findIndex(item => item.key == payload.key);
            const oldConsole = oldConsoles[index];
            oldConsole.oldData = payload.data;
            oldConsole.data = payload.data;
            oldConsole.status = ConsoleStatus.SAVED
            oldConsoles[index] = { ...oldConsole };
            return { ...state, consoles: [...oldConsoles] };
        },
        setSendResult(state, { payload }) {
            let oldConsoles: ConsoleInfo<HttpPageData, HttpPageInfo>[] = state.consoles;
            const index = oldConsoles.findIndex(item => item.key === payload.key);
            oldConsoles[index].page.resultData = payload.data;
            return { ...state, showResult: true, consoles: [...oldConsoles] };
        },
        setShowResult(state, { payload }) {
            return { ...state, showResult: payload };
        },
        editConsoleName(state, { payload }) {
            console.log(payload)
            const oldConsoles: ConsoleInfo<HttpPageData, HttpPageInfo>[] = state.consoles;
            const index = oldConsoles.findIndex(item => item.key == payload.key);
            const oldConsole = oldConsoles[index];
            oldConsole.name = payload.name;
            oldConsole.data.name = payload.name;
            if (isEqual(oldConsole.data, oldConsole.oldData)) {
                oldConsole.status = ConsoleStatus.SAVED
            } else {
                oldConsole.status = ConsoleStatus.CHANGE
            }
            oldConsoles[index] = { ...oldConsole };
            return { ...state, consoles: [...oldConsoles] };
        },
    },
    effects: {
        *loadTrees({ payload, callback }, { put, select }) {
            try {
                const resDtos = (yield httpService.getTree(payload)) as Map<string, TreeNodeResDto<any>>;
                yield put({
                    type: 'setTrees',
                    payload: resDtos,
                });
                if (callback && typeof callback === 'function') {
                    callback(resDtos);
                }
            }
            catch {
                console.log();
            }
        },
        *saveApi({ payload, callback }, { put, select }) {
            try {
                const treeNode = (yield httpService.saveApi({ ...payload })) as HTTP.HttpRequestResDto;
                if (callback && typeof callback === 'function') {
                    callback(treeNode);
                }
            }
            catch {
                if (callback && typeof callback === 'function') {
                    callback(undefined);
                }
            }
        },
        *updateApi({ payload, callback }, { put, select }) {
            try {
                const treeNode = (yield httpService.updateApi({ ...payload })) as HTTP.HttpRequestResDto;
                if (callback && typeof callback === 'function') {
                    callback(treeNode);
                }
            }
            catch {
                if (callback && typeof callback === 'function') {
                    callback(undefined);
                }
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
                if (callback && typeof callback === 'function') {
                    callback(undefined);
                }
            }
        },
        *sendApi({ payload, callback }, { put, select }) {
            try {
                const resData = (yield httpService.sendApi(payload)) as HttpApiSendResDto;
                if (callback && typeof callback === 'function') {
                    resData.uuid = uuid();
                    callback(resData);
                }
            }
            catch {
                console.log();
            }
        },
        *saveTree({ payload, callback }, { put, select }) {
            try {
                const resData = (yield httpService.saveTree(payload)) as TreeNodeResDto<any>;
                if (callback && typeof callback === 'function') {
                    callback(resData);
                }
            }
            catch {
                console.log();
            }
        },
        *updateTree({ payload, callback }, { put, select }) {
            try {
                const resData = (yield httpService.updateTree(payload)) as TreeNodeResDto<any>;
                if (callback && typeof callback === 'function') {
                    callback(resData);
                }
            }
            catch {
                console.log();
            }
        },
        *rename({ payload, callback }, { put, select }) {
            try {
                const resData = (yield httpService.rename(payload)) as TreeNodeResDto<any>;
                if (callback && typeof callback === 'function') {
                    callback(resData);
                }
            }
            catch {
                console.log();
            }
        },
        *newApiConsole({ payload, callback }, { put, select }) {
            try {
                const key = (yield systemService.getId()) as string;
                const node = {
                    key: key,
                    parentKey: payload.parentId,
                    name: payload.name,
                    nodeType: "API",
                    info: undefined,
                    info1: {
                        id: parseInt(key),
                        parentId: payload.parentId,
                        projectCode: payload.projectCode,
                        name: payload.name,
                        envCode: null,
                        url: null,
                        method: "GET",
                        timeout: 60,
                        body: {
                            type: "none",
                            language: "json",
                            datas: []
                        },
                        headers: [], params: [], rests: [], cookices: []
                    }
                }
                yield put({
                    type: 'addConsole',
                    payload: {
                        node: node,
                        status: ConsoleStatus.UNTRACKED
                    },
                });

                if (callback && typeof callback === 'function') {
                    callback();
                }
            }
            catch {
                console.log();
            }
        },
    }
};



export default HttpPageModel;
