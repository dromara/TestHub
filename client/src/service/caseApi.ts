import createRequest from '@/service/base';

import { TreeNodeResDto } from '@/typings/server/base';
import { ExecutionResult } from '@/typings/server/execution';
import { RenameDto, ExecutionXmlReqDto, RuleDocumentReqDto, RuleResDto } from '@/typings';

export interface SaveRuleDto { model: string, treeId: number, code: string, projectCode: string, documentStr: string }

/** 重命名 */
const rename = createRequest<RenameDto, TreeNodeResDto<any>>('/api/case/rename', {
  method: 'post',
});

const getOne = createRequest<{ caseCode: string }, RuleResDto>('/api/case/getOne/:caseCode', {
  method: 'get',
});

/** 格式化内容 */
const formatXml = createRequest<{ documentStr: string }, string>('/api/case/formatXml', {
  method: 'post',
});

/** 保存或更新规则XML */
const saveRuleDocumentXml = createRequest<SaveRuleDto, RuleResDto>('/api/case/saveRuleDocument/:model', {
  method: 'post',
});

/** 解析规则 */
const parserXml = createRequest<RuleDocumentReqDto, RuleResDto>('/api/case/parserXml', {
  method: 'post',
});

/** 执行XML */
const executionXml = createRequest<ExecutionXmlReqDto, ExecutionResult>('/api/case/executionXml', {
  method: 'post',
});






export default {
  rename,
  getOne,
  formatXml,
  parserXml,
  executionXml,
  saveRuleDocumentXml,
};
