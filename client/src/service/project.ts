import { RuleProjectSimpleResDto } from '@/typings/server/project';
import createRequest from './base';

/** 查询项目列表 */
const getProjectSimpleList = createRequest<void, RuleProjectSimpleResDto[]>('/api/project/simpleList', {
  method: 'get',
});


export {
  getProjectSimpleList,
};
