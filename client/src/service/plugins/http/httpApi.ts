import { HTTP } from '@/components/testHub/http/typings';
import createRequest from '@/service/base';
import { TreeNodeResDto } from '@/typings';
import { HttpApiSendResDto, HttpTreeReqDto } from '@/typings/server/plugins/http';

export interface UpdateTreeInfoReqDto extends HttpTreeReqDto {
  id: number;
}

/** 获取项目中的Http树 */
const getTree = createRequest<{ projectCode: string }, TreeNodeResDto[]>('/api/plugin/http/getTree/:projectCode', {
  method: 'get',
});

/** 获取API详情 */
const getApi = createRequest<{ id: number }, HTTP.HttpRequestResDto>('/api/plugin/http/getOne/:id', {
  method: 'get',
});


/** 发送 */
const sendApi = createRequest<HTTP.HttpRequestResDto, HttpApiSendResDto>('/api/plugin/http/api/send', {
  method: 'post',
});

/** 保存树节点 */
const saveTree = createRequest<HttpTreeReqDto, TreeNodeResDto>('/api/plugin/http/save', {
  method: 'post',
});

/** 更新树节点 */
const updateTree = createRequest<UpdateTreeInfoReqDto, TreeNodeResDto>('/api/plugin/http/updateTree/:id', {
  method: 'post',
});


export default {
  getApi,
  getTree,
  sendApi,
  saveTree,
  updateTree
};
