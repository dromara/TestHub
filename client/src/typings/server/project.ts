import { RuleActionResDto, RuleMetaClassResDto } from "./case";

export interface RuleProjectSimpleResDto {
  code: string;
  name: string;
  description?: string;
}
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
  /** 是否有效果 */
  effective?: boolean;
}

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
}

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
}