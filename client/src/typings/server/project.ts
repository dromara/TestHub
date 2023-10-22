
export interface ExecutionXmlReqDto {
    /** 项目编码 */
    projectCode: string;
    /** 用例编码 */
    ruleCode: string;
    /** 环境 */
    envCode?: string;
    /** 流程列表 */
    flows: string[];
    /** 参数 */
    params?: any;
    /** XML内容 */
    documentStr?: string;
}
export interface RuleTreeReqDto {
    /** 规则编码 */
    ruleCode: string;
    /** 类目 */
    treeId: number;
}


export interface RuleDocumentReqDto {
    /** 规则编码 */
    code: string;
    /** 项目编码 */
    projectCode: string;
    /** 类目 */
    treeId: number;
    /** XML内容 */
    documentStr: string;
}

export interface RuleProjectSimpleResDto {
    /** 编码 */
    code: string;
    /** id */
    id?: number;
    /** 名称 */
    name?: string;
};
export interface RuleProjectResDto {
    /** 行为列表 */
    actions: RuleActionResDto[];
    /** 编码 */
    code: string;
    /** 环境列表 */
    environments: RuleEnvironmentResDto[];
    /** id */
    id?: number;
    /** 元对象列表 */
    metaClasses?: RuleMetaClassResDto[];
    /** 枚举列表 */
    metaEnums?: RuleMetaEnumResDto[];
    /** 名称 */
    name?: string;

    ruleTrees?: TreeNodeResDto[];
};

export interface TreeNodeResDto {
    /** 子节点列表 */
    children?: TreeNodeResDto[];
    /** 内容 */
    data?: RuleResDto;
    /** key */
    key: string;
    /** key */
    parentKey: string;
    /** 是否叶子节点 */
    leaf: boolean;
    /** 名称 */
    name: string;
    /** 搜索时展示的名字 */
    nameDiv: string;
    /** 节点类型：FILE文件夹 CASE用例 */
    nodeType: string;
};

export interface RuleMetaEnumResDto {
    /** 编码 */
    code?: string;
    /** 创建时间 */
    createTime?: string;
    /** 创建人 */
    createUserId?: number;
    /** 枚举值 */
    id?: string;
    /** 元素列表 */
    items?: Item[];
    /** 修改时间 */
    modifyTime?: string;
    /** 修改人 */
    modifyUserId?: number;
    /** 枚举值名称 */
    name?: string;
    /** 备注 */
    remark?: string;
    /** 类型 */
    type?: string;
};

export interface Item {
    /** 元素编码 */
    code?: string;
    /** 元素名称 */
    name?: string;
    pcde?: string;
};

export interface RuleEnvironmentResDto {
    /** id */
    id?: number;
    /** 名称 */
    name?: string;
    /** 编码 */
    code?: string;
    /** 备注 */
    remark?: string;
    /** 参数列表 */
    params?: RuleParamResDto[];
};


export interface RuleCodeResDto {
    id: string;
    code: string;
    name: string;
    model: string;
    project: string;
    fileName: string;
}

export interface RuleResDto {
    /** 行为列表 */
    actions?: RuleActionResDto[];
    /** 可以直接执行 */
    canRun?: boolean;
    /** 规则编码 */
    code: string;
    /** 父类目 */
    treeId: number;
    /** 用例文件内容 */
    fileContent: string;
    /** 用例文件名称 */
    fileName?: string;
    /** 流程列表 */
    flows: RuleFlowResDto[];
    /** 规则ID */
    id?: number;
    /** 最后更新时间 */
    lastModifyTime?: string;
    /** 元对象列表 */
    metaClasses?: RuleMetaClassResDto[];
    /** 规则模式 */
    model?: string;
    /** 规则名称 */
    name?: string;
    /** 参数列表 */
    params?: RuleParamResDto[];
    /** 所属项目 */
    project: string;
};

export interface RuleMetaClassResDto {
    /** 编码 */
    code?: string;
    /** 元对象id */
    id?: string;
    /** 元对象名称 */
    name?: string;
    /** 属性列表 */
    propertyResDtos?: RulePropertyResDto[];
    /** 备注 */
    remark?: string;
};

export interface RulePropertyResDto {
    /** 属性编码 */
    code?: string;
    /** 列表维度 */
    complex?: number;
    /** 数据类型 */
    dataType?: string;
    /** 枚举编码 */
    enumCode?: string;
    /** 属性 */
    id?: string;
    /** 是否列表 */
    isComplex?: boolean;
    /** 属性名称 */
    name?: string;
    /** 备注 */
    remarks?: string;
};

export interface RuleFlowResDto {
    /** 流程编码 */
    code: string;
    /** 执行步骤 */
    executes?: RuleExecuteResDto[];
    /** 流程id */
    id?: number;
    /** 流程名称 */
    name?: string;
    /** 备注 */
    remark?: string;
};
export interface RuleExecuteResDto {
    /** 行为编码 */
    actionCode?: string;
    /** 是否阻塞 */
    block?: boolean;
    /** 执行步骤编码 */
    code?: string;
    /** 扩展信息 */
    extraInto?: Record<string, any>;
    /** 执行步骤id */
    id?: number;
    init?: boolean;
    /** 参数传递 */
    injects?: RuleInjectResDto[];
    /** 执行步骤名称 */
    name?: string;
};

export interface RuleInjectResDto {
    /** 执行步骤编码 */
    code?: string;
    /** 执行步骤名称 */
    data?: string;
    /** 注入id */
    id?: number;
};

export interface RuleActionResDto {
    /** 行为编码 */
    code: string;
    /** 返回值列表维度 */
    complex?: number;
    /** 返回值数据类型 */
    dataType: string;
    /** 扩展信息 */
    extraInto: Record<string, any>;
    /** 行为描述 */
    id?: number;
    /** 映射列表 */
    mappings?: RuleMappingResDto[];
    /** 行为名称 */
    name?: string;
    /** 入参列表 */
    params?: RuleParamResDto[];
    /** 备注 */
    remark?: string;
    /** 类型 */
    type: string;
};

export interface RuleMappingResDto {
    /** 映射源 */
    code?: string;
    /** 映射描述 */
    id?: number;
    /** 映射目标 */
    result?: string;
};
export interface RuleParamResDto {
    code: string;
    /** 列表维度 */
    complex?: number;
    /** 默认值 */
    data?: string;
    /** 数据类型 */
    dataType?: string;
    /** 列表内id */
    id?: number;
    /** 是否列表 */
    isComplex?: boolean;
    /** 参数名称 */
    name?: string;
    /** 是否必传 */
    necessary?: boolean;
};
