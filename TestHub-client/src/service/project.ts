import { RuleEnvironmentResDto, RuleProjectResDto, RuleProjectSimpleResDto } from '@/typings/server/project';
import createRequest from './base';
import { RuleActionResDto } from '@/typings';

/** 查询项目列表 */
const getProjectSimpleList = createRequest<void, RuleProjectSimpleResDto[]>('/api/project/simpleList', {
  method: 'get',
});

/** 获取项目详情 */
const getProject = createRequest<{ projectCode: string }, RuleProjectResDto>('/api/project/one/:projectCode', {
  method: 'get',
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
  getProject,
  addEnvironment,
  updateEnvironment,
  delEnvironment,
  getProjectSimpleList,
};
