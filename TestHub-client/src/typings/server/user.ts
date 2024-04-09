import { RuleProjectSimpleResDto } from "./project";

export enum IRole {
  'ADMIN' = 'ADMIN',
  'USER' = 'USER',
  'DESKTOP' = 'DESKTOP',
}
export interface IUser {
  id?: number;
  userName: string;
  nickName: string;
  password?: string;
  password2?: string;
  email: string;
  role?: IRole;
}

export interface ILoginUser {
  /**
   * Is it an administrator
   */
  admin?: boolean;
  /**
   * 用户id
   */
  id?: number;
  /**
   * 昵称
   */
  nickName?: string;
  roleCode: IRole;
}

export interface LoginReqDto {
  userName?: string;
  password?: string;
}


export interface LoginResDto {
  /** 用户名 */
  userName: string;

  /** 密码 */
  password: string;

  /** 头像 */
  avatar: string;

  /** 消息 */
  msg: string;

  /** token */
  token: any;

  /** 成功或失败 */
  flag: boolean;

  projectDtos: RuleProjectSimpleResDto[]
}

export interface IUserData extends LoginReqDto {
  remember?: boolean;
}
