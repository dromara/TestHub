import { IUserInfo, TreeInfoReqDto } from '@/typings';
import createRequest from './base';
import { LoginResDto } from '@/typings/server/user';

export interface UpdateTreeInfoReqDto extends TreeInfoReqDto {
  id: number;
}

/** 获取项目中的树 */
const login = createRequest<IUserInfo | {}, LoginResDto>('/api/user/login', {
  method: 'post',
});



export default {
  login,
};
