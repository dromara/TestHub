
export interface HttpTreeReqDto {

    /** 项目 */
    projectCode: string;

    /** 父节点key */
    parentId: number;

    /** 名称 */
    name: string;
};


export interface HttpApiSendResDto {
    timeout?: number;
    reqType?: string;
    method?: string;
    url?: string;

    rests?: Record<string, any>;
    params?: Record<string, any>;
    headers?: Record<string, any>;
    cookies?: Record<string, any>;
    body?: string;

    reHeaders?: Record<string, any>;
    reCookies?: Record<string, any>;
    reType?: string;
    resData?: string;
    error?: string;
    statusCode?: string;
    statusName?: string;
}
