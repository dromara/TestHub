
import { RuleActionResDto, RuleEnvironmentResDto, RuleProjectResDto } from '@/typings';
import { Effect, Reducer } from 'umi';
import projectService, { } from '@/service/project';

export interface IAppPageState {
    isLoaded: boolean;
    activeKey?: string;
    searchKey: string | undefined;
    //当前项目
    curProject?: RuleProjectResDto;
}

export interface IAppPageType {
    namespace: 'appPage',
    state: IAppPageState,
    reducers: {
        setActiveKey: Reducer<any>;//设置
        setSearchKey: Reducer<any>;//搜索
        setCurProject: Reducer<any>;//设置当前项目信息
        setEnvironment: Reducer<any>;//设置环境信息
        remoreEnvironment: Reducer<any>;//设置环境信息
        setAction: Reducer<any>;//设置环境信息
    };
    effects: {
        intiProject: Effect;//获取项目完整信息
        delEnvironment: Effect;//删除环境
        saveEnvironment: Effect;//保存环境
        saveAction: Effect;//保存行为
    };
}

const AppPageModel: IAppPageType = {
    namespace: 'appPage',

    state: {
        isLoaded: false,
        activeKey: 'paramPool',
        searchKey: ''
    },

    reducers: {
        setCurProject(state, { payload }) {
            return { ...state, curProject: payload };
        },
        setActiveKey(state, { payload }) {
            return { ...state, activeKey: payload };
        },
        setSearchKey(state, { payload }) {
            return { ...state, searchKey: payload };
        },
        setEnvironment(state, { payload }) {
            const newCurProject = state.curProject;
            if (payload.index > -1) {
                newCurProject.environments.splice(payload.index, 1);
            }
            newCurProject.environments.push(payload.data);
            return { ...state, curProject: newCurProject };
        },
        remoreEnvironment(state, { payload }) {
            const newCurProject = state.curProject;
            if (payload.index > -1) {
                newCurProject.environments.splice(payload.index, 1);
            }
            return { ...state, curProject: newCurProject };
        },
        setAction(state, { payload }) {
            const newCurProject = state.curProject;
            if (payload.index > -1) {
                newCurProject.actions.splice(payload.index, 1);
            }
            newCurProject.actions.push(payload.data);
            return { ...state, curProject: newCurProject };
        },

    },
    effects: {
        *intiProject({ payload, callback }, { put, call }) {
            try {
                const project = (yield projectService.getProject(payload)) as RuleProjectResDto;
                yield put({
                    type: 'setCurProject',
                    payload: project,
                });
                if (callback && typeof callback === 'function') {
                    callback(project);
                }
            }
            catch {
                console.log();
            }
        },
        *delEnvironment({ payload, callback }, { put, select }) {
            try {
                const flag = (yield projectService.delEnvironment(payload)) as boolean;
                if (flag) {
                    yield put({
                        type: 'remoreEnvironment',
                        payload: {
                            index: payload.index
                        },
                    });
                }
                if (callback && typeof callback === 'function') {
                    callback(flag);
                }
            }
            catch {
                console.log();
            }
        },
        *saveEnvironment({ payload, callback }, { put, select }) {
            try {
                let data: RuleEnvironmentResDto | null = null;
                if (payload.index > -1) {
                    data = (yield projectService.updateEnvironment(payload.data)) as RuleEnvironmentResDto;
                } else {
                    data = (yield projectService.addEnvironment(payload.data)) as RuleEnvironmentResDto;
                }
                const project = (yield projectService.getProject(payload)) as RuleProjectResDto;
                yield put({
                    type: 'setCurProject',
                    payload: project,
                });
                if (callback && typeof callback === 'function') {
                    callback();
                }
            }
            catch {
                console.log();
            }
        },
        *saveAction({ payload, callback }, { put, select }) {
            try {
                let data: RuleActionResDto | null = null;
                if (payload.index > -1) {
                    data = (yield projectService.updateAction(payload.data)) as RuleActionResDto;
                } else {
                    data = (yield projectService.addAction(payload.data)) as RuleActionResDto;
                }
                yield put({
                    type: 'setAction',
                    payload: {
                        index: payload.index,
                        data: data
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



export default AppPageModel;
