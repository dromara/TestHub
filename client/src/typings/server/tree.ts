export interface TreeInfoReqDto {
    /** 树类型 */
    treeType: string;
    /** 父节点 */
    parentId: number;
    /** 节点类型 */
    nodeType: string;
    /** 名称 */
    name: string;
}