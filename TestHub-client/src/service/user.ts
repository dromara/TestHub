import createRequest from './base';
import { LoginReqDto, LoginResDto } from '@/typings/server/user';

/** 用户登录接口 */
const userLogin = createRequest<LoginReqDto, LoginResDto>('/api/user/login', {
  method: 'post',
});

/** 用户登出 */
const userLogout = createRequest<void, void>('/api/oauth/logout_a', {
  method: 'post',
});

/** 获取用户信息 */
const getUser = createRequest<void, LoginResDto>('/api/oauth/user_a', { method: 'get' });


export { userLogin, userLogout, getUser };


// /** 获取用户列表信息 */
// const getUserList = createRequest<IPageParams, IPageResponse<IUser>>('/api/user/list', {
//   method: 'get',
// });

// /** 创建新用户 */
// const createUser = createRequest<IUser, boolean>('/api/user/create', {
//   method: 'post',
// });

// /** 更新用户信息 */
// const updateUser = createRequest<IUser, boolean>('/api/user/update', {
//   method: 'post',
// });

// /** 查询用户 */
// const queryUserById = createRequest<{ id: number }>('/api/user/:id', {
//   method: 'get',
// });

// /** 删除用户 */
// const deleteUser = createRequest<{ id: number }>('/api/user/:id', {
//   method: 'delete',
// });

// export { createUser, updateUser, queryUserById, deleteUser, getUserList, userLogin, userLogout, getUser };

