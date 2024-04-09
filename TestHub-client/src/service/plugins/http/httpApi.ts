import { HTTP } from '@/components/TestHub/http/typings';
import createRequest from '@/service/base';
import { TreeNodeResDto } from '@/typings/server/base';
// import { TreeNodeResDto } from '@/typings';
import { HttpApiSendResDto, HttpDirDto, HttpRenameDto } from '@/typings/server/plugins/http';


/** 获取项目中的Http树 */
const getTree = createRequest<{ projectCode: string }, Map<string, TreeNodeResDto<any>>>('/api/plugin/http/getTree/:projectCode', {
  method: 'get',
});

/** 获取API详情 */
const getApi = createRequest<{ id: number }, HTTP.HttpRequestResDto>('/api/plugin/http/getOne/:id', {
  method: 'get',
});

/** 保存树节点 */
const saveApi = createRequest<HTTP.HttpRequestResDto, HTTP.HttpRequestResDto>('/api/plugin/http/saveApi/add', {
  method: 'post',
});

/** 更新树节点 */
const updateApi = createRequest<HTTP.HttpRequestResDto, HTTP.HttpRequestResDto>('/api/plugin/http/saveApi/update', {
  method: 'post',
});

/** 发送 */
const sendApi = createRequest<HTTP.HttpRequestResDto, HttpApiSendResDto>('/api/plugin/http/send', {
  method: 'post',
});

/** 保存树节点 */
const saveTree = createRequest<HttpDirDto, TreeNodeResDto<any>>('/api/plugin/http/saveDir/add', {
  method: 'post',
});

/** 更新树节点 */
const updateTree = createRequest<HttpDirDto, TreeNodeResDto<any>>('/api/plugin/http/saveDir/update', {
  method: 'post',
});


/** 重命名 */
const rename = createRequest<HttpRenameDto, TreeNodeResDto<any>>('/api/plugin/http/rename', {
  method: 'post',
});



export default {
  rename,
  getApi,
  sendApi,
  getTree,
  saveApi,
  updateApi,
  saveTree,
  updateTree,
};
