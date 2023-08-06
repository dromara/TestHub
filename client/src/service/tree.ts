import { TreeInfoReqDto, TreeNodeResDto } from '@/typings';
import createRequest from './base';

export interface UpdateTreeInfoReqDto extends TreeInfoReqDto {
  id: number;
}

/** 获取项目中的树 */
const getByTreeType = createRequest<{ treeType: string }, TreeNodeResDto[]>('/api/tree/getByTreeType', {
  method: 'get',
});

/** 保存树节点 */
const saveTree = createRequest<TreeInfoReqDto, TreeNodeResDto>('/api/tree', {
  method: 'post',
});

/** 更新树节点 */
const updateTree = createRequest<UpdateTreeInfoReqDto, TreeNodeResDto>('/api/tree/:id', {
  method: 'post',
});


export default {
  getByTreeType,
  updateTree,
  saveTree,
};
