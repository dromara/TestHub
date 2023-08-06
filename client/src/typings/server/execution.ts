import { RuleResDto } from "./project";

export interface ExecutionResult {
    createTime?: string;
    defGlobalParams?: Record<string, any>;
    endTime: string;
    execFlag: boolean;
    flowResults: FlowResult[];
    globalParams?: Record<string, any>;
    id?: string;
    initParams: Record<string, any>;
    propertyMap?: Record<string, any>;
    ruleCode: string;
    ruleFlow: RuleResDto;
    ruleParams?: Record<string, any>;
};

export interface FlowResult {
    code: string;
    /** 异常数量 */
    errorNumber: number;
    executeResults: ExecuteResult[];
};

export interface ExecuteResult {
    actionCode: string;
    actionName: string;
    actionType: string;
    complex: number;
    dataType: string;
    executeAlias: string;
    executeBlock: boolean;
    executeCode: string;
    executeInit?: boolean;
    executeName: string;
    runStateItem: RunStateItem;
};

export interface RunStateItem {
    code: string;
    params?: any;
    runParams: any;
    systemParams: RunStateItemSystemParams;
    result: RunStateItemResult;
};
export interface RunStateItemSystemParams {
    costUnit: string;
    cost: number;
    startTime: string;
    endTime: string;
}
export interface RunStateItemResult {
    exception: any;
    content: any;
    log: any;
    flag: boolean;
    bizError: boolean;
};