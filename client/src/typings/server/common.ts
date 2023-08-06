
export interface IResultResponse<T> {
  code: string;
  msg: string;
  traceId: string;
  data: T;
}






export interface PageResult<T> {
  size: number;
  current: number;
  pages: number;
  total: number;
  records: T[];
}