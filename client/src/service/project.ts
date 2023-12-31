import { ExecutionXmlReqDto, RuleActionResDto, RuleDocumentReqDto, RuleEnvironmentResDto, RuleProjectResDto, RuleProjectSimpleResDto, RuleResDto, RuleTreeReqDto } from '@/typings';
import createRequest from './base';
import { ExecutionResult } from '@/typings/server/execution';

/** 查询项目列表 */
const getProjectSimpleList = createRequest<void, RuleProjectSimpleResDto[]>('/api/project/simpleList', {
  method: 'get',
});

/** 获取项目详情 */
const getProject = createRequest<{ projectCode: string }, RuleProjectResDto>('/api/project/one/:projectCode', {
  method: 'get',
});

/** 保存或更新规则XML */
const saveRuleDocumentXml = createRequest<{ model: string, treeId: number, code: string, projectCode: string, documentStr: string }, RuleResDto>('/api/project/saveRuleDocument/:model', {
  method: 'post',
});

/** 格式化内容 */
const formatXml = createRequest<{ documentStr: string }, string>('/api/project/formatXml', {
  method: 'post',
});

/** 解析规则 */
const parserXml = createRequest<RuleDocumentReqDto, RuleResDto>('/api/project/parserXml', {
  method: 'post',
});

/** 执行XML */
const executionXml = createRequest<ExecutionXmlReqDto, ExecutionResult>('/api/project/executionXml', {
  method: 'post',
});

/** 更新规则类目 */
const saveRuleTree = createRequest<RuleTreeReqDto, RuleResDto>('/api/project/saveRuleTree', {
  method: 'post',
});
/** 删除环境 */
const delEnvironment = createRequest<{ code: string, projectCode: string }, boolean>('/api/project/delEnvironment', {
  method: 'post',
});
/** 保存环境 */
const addEnvironment = createRequest<RuleEnvironmentResDto, RuleEnvironmentResDto>('/api/project/addEnvironment', {
  method: 'post',
});
/** 更新环境 */
const updateEnvironment = createRequest<RuleEnvironmentResDto, RuleEnvironmentResDto>('/api/project/updateEnvironment', {
  method: 'post',
});

/** 保存行为 */
const addAction = createRequest<RuleActionResDto, RuleActionResDto>('/api/project/addAction', {
  method: 'post',
});
/** 更新行为 */
const updateAction = createRequest<RuleActionResDto, RuleActionResDto>('/api/project/updateAction', {
  method: 'post',
});

export default {
  addAction,
  updateAction,
  addEnvironment,
  updateEnvironment,
  delEnvironment,
  saveRuleDocumentXml,
  getProjectSimpleList,
  getProject,
  executionXml,
  parserXml,
  formatXml,
  saveRuleTree
};
