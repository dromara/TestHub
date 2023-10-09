import { RuleProjectResDto, RuleProjectSimpleResDto, RuleResDto, TreeNodeResDto, ConsoleStatus, IConsoleIndo, RuleEnvironmentResDto } from '@/typings';
import projectService, { } from '@/service/project';
import treeService, { } from '@/service/tree';
import systemService, { } from '@/service/system';
import { Effect, Reducer } from 'umi';
import { ExecutionResult } from '@/typings/server/execution';
import { parseInt } from 'lodash';
export interface IAppPageState {
  projects: RuleProjectSimpleResDto[],
  //当前项目
  curProject: RuleProjectResDto;

  caseConsoles: IConsoleIndo<TreeNodeResDto>[];
  caseActiveKey?: string;
  caseSearchInfo?: string;
  caseShowResult: boolean;

}

export interface IAppPageType {
  namespace: 'appPage',
  state: IAppPageState,
  reducers: {
    setCurProject: Reducer<any>;//设置当前项目信息
    setProjects: Reducer<IAppPageState['projects']>;//设置项目列表
    setRuleTrees: Reducer<any>;//修改树列表
    addCaseConsole: Reducer<any>;//添加控制台tab
    newCaseConsole: Reducer<any>;//新建用例控制台tab
    setCaseConsole: Reducer<any>;//修改控制台tab
    setCaseConsoleByRuleResDto: Reducer<any>;//根据规则信息修改控制台tab
    delCaseConsole: Reducer<any>;//移除控制台tab
    setCaseActiveKey: Reducer<any>;//设置控制台激活tab页
    setCaseSearchInfo: Reducer<any>;//设置搜索内容
    setCaseShowResult: Reducer<any>;//设置搜索结果
    setNowFileContent: Reducer<any>;//设置tab中编辑后内容
    setCaseFileContent: Reducer<any>;//设置tree中的文件内容
    setExecutionResult: Reducer<any>;//设置运行结果
    remoreEnvironment: Reducer<any>;//设置环境信息
    setEnvironment: Reducer<any>;//设置环境信息
  };
  effects: {
    getProjects: Effect;//获取项目列表
    intiProject: Effect;//获取项目完整信息
    saveCaseFileContent: Effect;//保存或更新规则
    formatXml: Effect;//格式化内容
    parserXml: Effect;//解析规则
    executionXml: Effect;//执行XML脚本
    addTree: Effect;//新增类目树
    updateTree: Effect;//编辑类目树
    getCode: Effect;//生成编码
    saveRuleTree: Effect;//调整规则类目
    delEnvironment: Effect;//删除环境
    saveEnvironment: Effect;//保存环境
  };
}

const AppPageModel: IAppPageType = {
  namespace: 'appPage',

  state: {
    projects: [],
    caseConsoles: [],
    caseShowResult: false
  },

  reducers: {
    setCurProject(state, { payload }) {
      // console.log(payload);
      return { ...state, curProject: payload, caseConsoles: [], caseActiveKey: undefined, caseSearchInfo: undefined };
    },
    setProjects(state, { payload }) {
      return { ...state, projects: [...payload] };
    },
    setRuleTrees(state, { payload }) {
      const newCurProject = state.curProject;
      newCurProject.ruleTrees = [...payload];
      return { ...state, curProject: newCurProject };
    },
    setCaseActiveKey(state, { payload }) {
      return { ...state, caseActiveKey: payload };
    },
    addCaseConsole(state, { payload }) {
      let oldConsoles: IConsoleIndo<TreeNodeResDto>[] = state.caseConsoles;
      if (oldConsoles.filter(item => item.key == payload.key).length == 0) {
        const console: IConsoleIndo<TreeNodeResDto> = { name: payload.name, key: payload.key, status: ConsoleStatus.SAVED, data: payload };
        oldConsoles.push(console);
        return { ...state, caseConsoles: [...oldConsoles], caseActiveKey: payload.key };
      } else {
        return { ...state, caseActiveKey: payload.key };
      }
    },
    newCaseConsole(state, { payload }) {
      let oldConsoles: IConsoleIndo<TreeNodeResDto>[] = state.caseConsoles;
      const fileContent = `<?xml version="1.0" encoding="UTF-8"?>\n\n<rule code="${payload.key}" name="${payload.name}" model="flow" project="${state.curProject.code}">\n    <flows>\n        <flow code="flow1">\n \n        </flow>\n    </flows>\n</rule>`;
      const rule: RuleResDto = { project: state.curProject.code, code: payload.key, treeId: payload.parentKey, name: payload.name, flows: [], fileContent: "" };
      const tree: TreeNodeResDto = { key: payload.key, parentKey: payload.parentKey, name: payload.name, leaf: true, nodeType: "CASE", data: rule }
      const console: IConsoleIndo<TreeNodeResDto> = { name: payload.name, key: payload.key, status: ConsoleStatus.DRAFT, data: tree };
      console.nowFileContent = fileContent;
      oldConsoles.push(console);
      return { ...state, caseConsoles: [...oldConsoles], caseActiveKey: payload.key };
    },
    setCaseConsole(state, { payload, callback }) {
      let oldConsoles: IConsoleIndo<TreeNodeResDto>[] = state.caseConsoles;
      const index = oldConsoles.findIndex(item => item.key === payload.code);
      oldConsoles[index] = payload;
      if (callback && typeof callback === 'function') {
        callback();
      }
      return { ...state, caseConsoles: [...oldConsoles] };
    },
    setCaseConsoleByRuleResDto(state, { payload }) {
      let oldConsoles: IConsoleIndo<TreeNodeResDto>[] = state.caseConsoles;
      const index = oldConsoles.findIndex(item => item.key === payload.code);
      oldConsoles[index].name = payload.name;
      oldConsoles[index].status = ConsoleStatus.SAVED;
      oldConsoles[index].nowFileContent = payload.fileContent;
      oldConsoles[index].data.name = payload.name;
      oldConsoles[index].data.data = payload;
      return { ...state, caseConsoles: [...oldConsoles] };
    },
    delCaseConsole(state, { payload }) {
      let oldConsoles: IConsoleIndo<TreeNodeResDto>[] = state.caseConsoles;
      const index = oldConsoles.findIndex(item => item.key === payload);
      let newConsoles = oldConsoles.filter(item => item.key != payload);
      if (index == 0 && newConsoles.length > 0) {
        //删除第一个 后面还有 切换到后边的一个 新控制台的第一个
        return { ...state, caseConsoles: [...newConsoles], caseActiveKey: newConsoles[index].key };
      } else if (index == 0 && newConsoles.length == 0) {
        //删除第一个 后面没有 切换到空
        return { ...state, caseConsoles: [...newConsoles], caseActiveKey: undefined };
      } else if (index > 0) {
        //不是删除第一个 前边必然有有 切换到前一个
        return { ...state, caseConsoles: [...newConsoles], caseActiveKey: newConsoles[index - 1].key };
      }

    },
    setCaseSearchInfo(state, { payload }) {
      return { ...state, caseSearchInfo: payload };
    },
    setCaseShowResult(state, { payload }) {
      return { ...state, caseShowResult: payload };
    },
    setNowFileContent(state, { payload }) {
      let oldConsoles: IConsoleIndo<TreeNodeResDto>[] = state.caseConsoles;
      const index = oldConsoles.findIndex(item => item.key === payload.key);
      if (payload.text !== oldConsoles[index].data.data.fileContent) {
        oldConsoles[index].nowFileContent = payload.text;
      } else {
        oldConsoles[index].nowFileContent = undefined;
      }
      return { ...state, caseConsoles: [...oldConsoles] };
    },
    setCaseFileContent(state, { payload }) {
      let oldConsoles: IConsoleIndo<TreeNodeResDto>[] = state.caseConsoles;
      const index = oldConsoles.findIndex(item => item.key === payload.key);
      oldConsoles[index].data.data.fileContent = payload.fileContent;
      return { ...state, caseConsoles: [...oldConsoles] };
    },
    setExecutionResult(state, { payload }) {
      let oldConsoles: IConsoleIndo<TreeNodeResDto>[] = state.caseConsoles;
      const index = oldConsoles.findIndex(item => item.key === payload.key);
      oldConsoles[index].executionResult = payload.executionResult;
      return { ...state, caseConsoles: [...oldConsoles] };
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
      console.log(newCurProject)
      return { ...state, curProject: newCurProject };
    }
  },
  effects: {
    *getProjects({ payload, callback }, { put, call }) {
      try {
        const projects = (yield projectService.getProjectSimpleList()) as RuleProjectSimpleResDto[];
        // console.log(projects);
        yield put({
          type: 'setProjects',
          payload: projects,
        });
        if (callback && typeof callback === 'function') {
          callback(projects);
        }
      }
      catch {
      }
    },
    *intiProject({ payload, callback }, { put, call }) {
      try {
        const project = (yield projectService.getProject(payload)) as RuleProjectResDto;
        // console.log(project);
        yield put({
          type: 'setCurProject',
          payload: project,
        });
      }
      catch {
      }
    },
    *saveCaseFileContent({ payload, callback }, { put, call }) {
      try {
        const ruleInfo = (yield projectService.saveRuleDocumentXml({
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
    *formatXml({ payload, callback }, { put, call }) {
      try {
        const documentStr = (yield projectService.formatXml({
          documentStr: payload
        })) as string;
        if (callback && typeof callback === 'function') {
          callback(documentStr);
        }
      }
      catch {
      }
    },
    *parserXml({ payload, callback }, { put, call }) {
      try {
        const ruleResDto = (yield projectService.parserXml({ ...payload })) as RuleResDto;
        if (callback && typeof callback === 'function') {
          callback(ruleResDto);
        }
      }
      catch {
      }
    },
    *executionXml({ payload, callback }, { put, select }) {

      let oldConsoles = (yield select((state: { appPage: { caseConsoles: any; }; }) => state.appPage.caseConsoles)) as IConsoleIndo<TreeNodeResDto>[];
      const index = oldConsoles.findIndex(item => item.key === payload.key);
      oldConsoles[index].executionResult = undefined;
      yield put({
        type: 'setCaseConsole',
        payload: oldConsoles[index],
      });

      try {
        const executionResult = (yield projectService.executionXml({ ...payload.runXmlParam, params: Object.fromEntries(payload.runXmlParam.params) })) as ExecutionResult;
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
    *addTree({ payload, callback }, { put, select }) {

      try {
        const treeNode = (yield treeService.saveTree({ ...payload })) as TreeNodeResDto;
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
        const treeNode = (yield treeService.updateTree({ ...payload })) as TreeNodeResDto;
        if (callback && typeof callback === 'function') {
          callback(treeNode);
        }
      }
      catch {
      }
    },
    *getCode({ payload, callback }, { put, select }) {

      try {
        const code = (yield systemService.getCode({ type: payload })) as string;
        if (callback && typeof callback === 'function') {
          callback(code);
        }
      }
      catch {
      }
    },
    *saveRuleTree({ payload, callback }, { put, select }) {
      try {
        const ruleResDto = (yield projectService.saveRuleTree(payload)) as RuleResDto;
        if (callback && typeof callback === 'function') {
          callback(ruleResDto);
        }
      }
      catch {
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
      }
    },
    *saveEnvironment({ payload, callback }, { put, select }) {
      try {
        var data = null;
        if (payload.index > -1) {
          data = (yield projectService.updateEnvironment(payload.data)) as RuleEnvironmentResDto;
        } else {
          data = (yield projectService.addEnvironment(payload.data)) as RuleEnvironmentResDto;
        }
        yield put({
          type: 'setEnvironment',
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
      }
    },
  }
};



export default AppPageModel
