import { ConsoleInfo, ConsoleStatus, HttpConsoleInfo } from '@/typings/client';

import httpService, { } from '@/service/plugins/http/httpApi';
import treeService, { } from '@/service/treeApi';
import caseService, { } from '@/service/caseApi';
import { HTTP } from '@/components/TestHub/http/typings';
import { TreeNodeResDto } from '@/typings/server/base';

import systemService from '@/service/system';
import cloneDeep from 'lodash/cloneDeep';
import isEqual from 'lodash/isEqual';
import { v4 as uuid } from 'uuid';


import { Effect, Reducer } from 'umi';
import { CaseNodeType } from '@/pages/main/case';
import { RuleResDto } from '@/typings/server/case';
import { ExecutionResult, ExecutionXmlReqDto } from '@/typings';

export interface CasePageData extends RuleResDto {

}
export interface CasePageInfo {
    runXmlParam?: ExecutionXmlReqDto;
    executionResult?: ExecutionResult;
}


export interface ICasePageState {
    isLoaded: boolean;
    showResult: boolean;
    activeKey?: string;
    consoles: ConsoleInfo<CasePageData, CasePageInfo>[];
    trees?: Map<string, TreeNodeResDto<CasePageData>>;
}

export interface ICasePageType {
    namespace: 'casePage',
    state: ICasePageState,
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
        setExecutionResult: Reducer<any>;//设置运行结果

    };
    effects: {
        loadTrees: Effect;//获取项目列表
        loadCase: Effect;//获取APi数据
        saveTree: Effect;//新增类目树
        updateTree: Effect;//编辑类目树
        rename: Effect;//重命名树节点、用例
        newCaseConsole: Effect;//新建用例控制台tab
        formatXml: Effect;//格式化内容
        saveCaseFileContent: Effect;//保存或更新规则
        parserXml: Effect;//解析规则
        executionXml: Effect;//执行XML脚本
    };
}

const HttpPageModel: ICasePageType = {
    namespace: 'casePage',

    state: {
        showResult: false,
        isLoaded: false,
        consoles: [],

    },

    reducers: {
        putTree(state, { payload }) {
            const oldTrees: Map<string, TreeNodeResDto<CasePageData>> = state.trees;

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
            const oldConsoles: ConsoleInfo<CasePageData, CasePageInfo>[] = state.consoles;
            if (oldConsoles.filter(item => item.key == payload.node.key).length == 0) {
                const console: ConsoleInfo<CasePageData, CasePageInfo> = {
                    key: payload.node.key,
                    name: payload.node.name,
                    status: payload.status ? payload.status : ConsoleStatus.SAVED,
                    data: (JSON.parse(JSON.stringify(payload.node.info)) as CasePageData),
                    oldData: cloneDeep((JSON.parse(JSON.stringify(payload.node.info)) as CasePageData)),
                    page: payload.page == undefined ? {} : payload.page,
                }
                oldConsoles.push(console);
                return { ...state, consoles: [...oldConsoles], activeKey: payload.node.key };
            } else {
                return { ...state, activeKey: payload.node.key };
            }
        },
        delConsole(state, { payload }) {
            const oldConsoles: ConsoleInfo<CasePageData, CasePageInfo>[] = state.consoles;
            const filteredConsoles = oldConsoles.filter(item => !payload.includes(item.key));
            return { ...state, consoles: [...filteredConsoles] };
        },
        setConsolePage(state, { payload }) {
            const oldConsoles: ConsoleInfo<CasePageData, CasePageInfo>[] = state.consoles;
            oldConsoles[oldConsoles.findIndex(item => item.key == payload.key)].page = payload.page;
            return { ...state, consoles: oldConsoles };
        },
        setConsoleData(state, { payload }) {
            const oldConsoles: ConsoleInfo<CasePageData, CasePageInfo>[] = state.consoles;
            const index = oldConsoles.findIndex(item => item.key == payload.key);
            const oldConsole = oldConsoles[index];

            oldConsole.data = payload.data;
            if (oldConsole.status != ConsoleStatus.UNTRACKED) {
                if (isEqual(oldConsole.data, oldConsole.oldData)) {
                    oldConsole.status = ConsoleStatus.SAVED
                } else {
                    oldConsole.status = ConsoleStatus.CHANGE
                }
            }
            oldConsoles[index] = { ...oldConsole };
            return { ...state, consoles: [...oldConsoles] };
        },
        saveConsoleData(state, { payload }) {
            const oldConsoles: ConsoleInfo<CasePageData, CasePageInfo>[] = state.consoles;
            const index = oldConsoles.findIndex(item => item.key == payload.key);
            const oldConsole = oldConsoles[index];
            oldConsole.oldData = payload.data;
            oldConsole.data = payload.data;
            oldConsole.status = ConsoleStatus.SAVED
            oldConsoles[index] = { ...oldConsole };
            return { ...state, consoles: [...oldConsoles] };
        },
        setShowResult(state, { payload }) {
            return { ...state, showResult: payload };
        },
        setExecutionResult(state, { payload }) {
            const oldConsoles: ConsoleInfo<CasePageData, CasePageInfo>[] = state.consoles;
            oldConsoles[oldConsoles.findIndex(item => item.key == payload.key)].page.executionResult = payload.executionResult;
            return { ...state, consoles: oldConsoles, showResult: true };
        },
    },
    effects: {
        *loadTrees({ payload, callback }, { put, select }) {
            try {
                const resDtos = (yield treeService.getTree(payload)) as Map<string, TreeNodeResDto<any>>;
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
        *loadCase({ payload, callback }, { put, select }) {
            try {
                const data = (yield caseService.getOne({ caseCode: payload })) as RuleResDto;
                if (callback && typeof callback === 'function') {
                    callback(data);
                }
            }
            catch {
                if (callback && typeof callback === 'function') {
                    callback(undefined);
                }
            }
        },
        *saveTree({ payload, callback }, { put, select }) {
            try {
                const resData = (yield treeService.saveTree(payload)) as TreeNodeResDto<any>;
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
            console.log(payload)
            try {
                let resData: TreeNodeResDto<any> | null = null;
                if (payload.nodeType == CaseNodeType.DIR) {
                    resData = (yield treeService.rename({
                        key: payload.key,
                        name: payload.name
                    })) as TreeNodeResDto<any>;
                } else {
                    resData = (yield caseService.rename({
                        key: payload.key,
                        name: payload.name
                    })) as TreeNodeResDto<any>;
                }
                if (callback && typeof callback === 'function') {
                    callback(resData);
                }

            }
            catch {
                console.log();
            }
        },
        *newCaseConsole({ payload, callback }, { put, select }) {
            try {
                const key = (yield systemService.getId()) as string;

                const info = {
                    id: parseInt(key),
                    parentId: payload.parentId,
                    projectCode: payload.projectCode,
                    name: payload.name,
                    method: "POST",
                    timeout: 60,
                    body: {
                        type: "raw",
                        language: "json",
                        datas: [],
                        content: "{}"
                    },
                    headers: [], params: [], rests: [], cookices: []
                }


                // 妈的有鬼👻

                // 输出后 这个body的type = raw
                console.log(info)

                // 输出后 这个body的type = none
                console.log(JSON.stringify(info))

                // 输出后 这个body的type = none
                console.log((JSON.parse(JSON.stringify(info)) as HTTP.HttpRequestResDto))


                yield put({
                    type: 'addConsole',
                    payload: {
                        node: {
                            key: key,
                            parentKey: payload.parentId,
                            name: payload.name,
                            nodeType: "API",
                            info: (JSON.parse(JSON.stringify(info)) as HTTP.HttpRequestResDto)
                        },
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

        *formatXml({ payload, callback }, { put, call }) {
            try {
                const documentStr = (yield caseService.formatXml({
                    documentStr: payload
                })) as string;
                if (callback && typeof callback === 'function') {
                    callback(documentStr);
                }
            }
            catch {
                console.log()
            }
        },
        *saveCaseFileContent({ payload, callback }, { put, call }) {
            try {
                const ruleInfo = (yield caseService.saveRuleDocumentXml({
                    model: payload.model, treeId: parseInt(payload.parentKey), code: payload.code, documentStr: payload.documentStr, projectCode: payload.project
                })) as RuleResDto;
                //成功要更新 caseConsoles
                yield put({
                    type: 'setCaseConsoleByRuleResDto',
                    payload: ruleInfo,
                });
                if (callback && typeof callback === 'function') {
                    callback(ruleInfo);
                }
            }
            catch {
                console.log("error")
            }
        },
        *parserXml({ payload, callback }, { put, call }) {
            try {
                const ruleResDto = (yield caseService.parserXml({ ...payload })) as RuleResDto;
                if (callback && typeof callback === 'function') {
                    callback(ruleResDto);
                }
            }
            catch {
                console.log("error")
            }
        },
        *executionXml({ payload, callback }, { put, select }) {

            // let oldConsoles = (yield select((state: { appPage: { caseConsoles: any; }; }) => state.appPage.caseConsoles)) as IConsoleIndo<TreeNodeResDto>[];
            // const index = oldConsoles.findIndex(item => item.key === payload.key);
            // oldConsoles[index].executionResult = undefined;
            // yield put({
            //   type: 'setCaseConsole',
            //   payload: oldConsoles[index],
            // });

            try {
                const executionResult = (yield caseService.executionXml({ ...payload.runXmlParam, params: Object.fromEntries(payload.runXmlParam.params) })) as ExecutionResult;
                yield put({
                    type: 'setExecutionResult',
                    payload: {
                        key: payload.key,
                        executionResult: executionResult
                    },
                });
                if (callback && typeof callback === 'function') {
                    callback(executionResult);
                }
            }
            catch {
                if (callback && typeof callback === 'function') {
                    callback(false);
                }
            }
        },
    }
};



export default HttpPageModel;
