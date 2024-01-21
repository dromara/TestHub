import { ToolsTypeCode } from "@/utils/constants";
import { ExecutionXmlReqDto } from "../server";
import { ExecutionResult } from "../server/execution";
import { HttpApiSendResDto } from "../server/plugins/http";


export interface IUserInfo {
    userName: string;
    password: string;
    rememberMe: boolean
}

//

export enum BaseConsoleStatus {
    DRAFT = 'DRAFT',//草稿
    SAVED = 'SAVED',//后台托管
}
export interface BaseConsoleInfo<D, P> {
    name: string; // 名称
    key: string; // key 唯一
    status: BaseConsoleStatus;//状态
    data: D;//节点 数据
    oldData: any;//旧的数据 就是原始的 data.data
    page: P;
    // extend?: E;//
}

export interface BasePageInfo {

}



//页面
export interface IConsoleInfo<T> {
    name: string; // 名称
    key: ToolsTypeCode; // key 唯一
    data: T | undefined | null;//数据
}

export enum ConsoleStatus {
    DRAFT = 'DRAFT',//草稿
    SAVED = 'SAVED',//后台托管
}

export interface IConsoleIndo<T> {
    name: string; // 名称
    key: string; // key 唯一
    status: ConsoleStatus;//状态
    data: T;//数据
    nowFileContent?: string;//修改后的文件内容
    runXmlParam?: ExecutionXmlReqDto;//运行参数
    executionResult?: ExecutionResult//运行结果
}



export interface IFormulaLog {
    exec?: number;
    data?: Record<string, any>;
    text?: string;
    type?: string;

    path?: string;
    val?: string;

    func?: string;
    paramLogs?: ParamLog[];
    mapLog?: Record<string, ParamLog>;

    lastAttr?: string;

    nodes?: ParamLog[];
    dataLog?: ParamLog[];

    suffix?: string;
    index?: number;
    indexLog?: IFormulaLog;
}

export interface ParamLog {
    code?: string;
    log?: IFormulaLog;
}

export interface Exception {
    cause?: Throwable;
    localizedMessage?: string;
    message?: string;
    stackTrace?: StackTraceElement[];
    suppressed?: Throwable[];
};


export interface StackTraceElement {
    classLoaderName?: string;
    className?: string;
    fileName?: string;
    lineNumber?: number;
    methodName?: string;
    moduleName?: string;
    moduleVersion?: string;
    nativeMethod?: boolean;
};

export interface Throwable {
    cause?: Throwable;
    localizedMessage?: string;
    message?: string;
    stackTrace?: StackTraceElement[];
    suppressed?: Throwable[];
};


export interface HttpConsoleInfo {
    activeKey?: string;
    resultData?: HttpApiSendResDto;
};