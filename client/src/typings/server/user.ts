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
}