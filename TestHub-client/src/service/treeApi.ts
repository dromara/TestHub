import { HTTP } from '@/components/TestHub/http/typings';
import createRequest from '@/service/base';
import { RenameDto } from '@/typings';
import { TreeNodeResDto } from '@/typings/server/base';
// import { TreeNodeResDto } from '@/typings';
import { HttpApiSendResDto, HttpDirDto, HttpRenameDto } from '@/typings/server/plugins/http';


/** 获取项目中的Http树 */
const getTree = createRequest<{ projectCode: string }, Map<string, TreeNodeResDto<any>>>('/api/tree/getTree/:projectCode', {
  method: 'get',
});

/** 保存树节点 */
const saveTree = createRequest<HTTP.HttpRequestResDto, HTTP.HttpRequestResDto>('/api/tree/saveTree/add', {
  method: 'post',
});

/** 更新树节点 */
const updateTree = createRequest<HttpDirDto, TreeNodeResDto<any>>('/api/tree/saveTree/update', {
  method: 'post',
});

/** 重命名 */
const rename = createRequest<RenameDto, TreeNodeResDto<any>>('/api/tree/rename', {
  method: 'post',
});




export default {
  rename,
  getTree,
  saveTree,
  updateTree,
};
