export interface TreeNodeResDto<D> {
  /** key */
  index: string;
  /** 是否是文件夹 */
  isFolder: boolean;
  /** 子节点列表 */
  children: string[],
  /** 内容 */
  data: TreeNodeDataResDto<D>,
  /** 是否可以重命名 */
  canRename: boolean;
  /** 是否可以移动 */
  canMove: boolean;
}
export interface TreeNodeDataResDto<D> {
  /** key */
  key: string;
  /** key */
  parentKey: string;
  /** name */
  name: string;
  /** 节点类型 */
  nodeType: string;
  /** 内容 */
  info?: D;
}


