import { HttpApiSendResDto } from "../server";

export enum ConsoleStatus {
  //参考 git status
  UNTRACKED = 'UNTRACKED', //草稿。   未跟踪Untracked
  SAVED = 'SAVED', //已保存。 已跟踪tracked
  CHANGE = 'CHANGE', //变更。 已修改Changes not staged for commit
}
export interface ConsoleInfo<D, P> {
  name: string; // 名称
  id?: number;
  key: string; // key 唯一
  status: ConsoleStatus; //状态
  data: D; //节点 数据
  oldData: D; //旧节点 数据
  page: P; // 页面临时不需要保存的数据
}

//http相关的
export interface HttpConsoleInfo {
  activeKey?: string;
  resultData?: HttpApiSendResDto;
}
