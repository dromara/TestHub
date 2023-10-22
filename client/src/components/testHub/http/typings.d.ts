import { RuleParamResDto } from "@/typings";

declare namespace HTTP {

    type BodyResDto = {
        /** 内容 */
        content?: string;
        /** 表单数据 */
        formDatas?: RuleParamResDto[];
        /** id */
        id?: number;
        language: string;
        /** 子类型 */
        subType?: string;
        /** 类型 */
        type?: string;
    };
    type FormDataResDto = {
        /** 描述 */
        desc?: string;
        /** id */
        id?: number;
        /** 属性 */
        key?: string;
        /** 类型 */
        type?: string;
        /** 值 */
        value?: string;
    };
    type HttpRequestResDto = {
        /** 正文 */
        body?: BodyResDto;
        /** 请求头 */
        headers?: RuleParamResDto[];
        /** rests参数 */
        rests?: RuleParamResDto[];
        /** id */
        id?: number;
        /** 请求方式 */
        method?: string;
        /** 参数 */
        params?: RuleParamResDto[];
        /** 服务 */
        server?: string;
        /** 路径 */
        url?: string;
    };
    type KVResDto = {
        /** 描述 */
        desc?: string;
        /** id */
        id?: number;
        /** 属性 */
        key?: string;
        /** 值 */
        value?: string;
    };
}