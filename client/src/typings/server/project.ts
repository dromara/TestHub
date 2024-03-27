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