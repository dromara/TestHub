import { ExecutionXmlReqDto, RuleDocumentReqDto, RuleProjectResDto, RuleProjectSimpleResDto, RuleResDto, RuleTreeReqDto } from '@/typings';
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

export default {
  saveRuleDocumentXml,
  getProjectSimpleList,
  getProject,
  executionXml,
  parserXml,
  formatXml,
  saveRuleTree
};
