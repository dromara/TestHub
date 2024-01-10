import i18n from '@/i18n';
import { TreeNodeResDto } from '@/typings';
import { ModalForm, ProFormSelect, ProFormText, ProFormTreeSelect } from '@ant-design/pro-components';
import { DefaultOptionType } from 'antd/lib/select';
import React, { useCallback, useContext, useEffect, useMemo, useRef, useState } from 'react';
import { IAppPageState, IHttpPageState } from 'umi';


interface IProps {
    httpPage: IHttpPageState;
    appPage: IAppPageState;
    dispatch: Function;
    thisKey?: string;
    callback: Function;
}

interface TreeDataItem {
    title: string;
    value: string;
    disabled: boolean;
    children?: TreeDataItem[];
}

const convertToTreeData = (key: string, treeNodes: TreeNodeResDto[]): TreeDataItem[] => {
    return treeNodes.map((node) => {
        const treeDataNode: TreeDataItem = {
            title: node.name,
            value: node.key,
            disabled: false
        };
        if (node.key === key || node.nodeType != "DIR") {
            treeDataNode.disabled = true;
        }

        if (node.children) {
            treeDataNode.children = convertToTreeData(key, node.children);
        }

        return treeDataNode;
    });
};
function filterTreeData(treeData) {
    return treeData.reduce((acc, node) => {
        if (node.nodeType === 'DIR') {
            const filteredChildren = node.children ? filterTreeData(node.children) : [];
            const newNode = { ...node, children: filteredChildren };
            acc.push(newNode);
        }
        return acc;
    }, []);
}
function findNodeByKey(nodes: TreeNodeResDto[], key: string) {
    for (const node of nodes) {
        if (node.key === key) {
            return node;
        }
        if (node.children) {
            const foundNode: TreeNodeResDto = findNodeByKey(node.children, key);
            if (foundNode) {
                return foundNode;
            }
        }
    }
    return null; // 如果没有找到匹配的节点
}
function getNodeByKey(nodes: TreeNodeResDto[] | undefined, key: string) {
    // return {};
    const node: TreeNodeResDto = findNodeByKey(nodes == undefined ? [] : nodes, key);
    if (node != null && node.parentKey == "0") {
        node.parentKey = undefined;
    }
    return node;
}

function updateNodeNameByKey(nodes: TreeNodeResDto[], key: string, newName: string): TreeNodeResDto[] {
    return nodes.map((node) => {
        if (node.key === key) {
            return { ...node, name: newName }; // 替换指定节点的 name
        }
        if (node.children) {
            node.children = updateNodeNameByKey(node.children, key, newName); // 递归处理子节点
        }
        return node;
    });
}

function deleteNodeByKey(nodes: TreeNodeResDto[], key: string): TreeNodeResDto[] {
    return nodes.filter((node) => {
        if (node.key === key) {
            return false; // 过滤掉指定的节点
        }
        if (node.children) {
            node.children = deleteNodeByKey(node.children, key); // 递归处理子节点
        }
        return true; // 保留其他节点
    });
}
function insertNodeByKey(nodes: TreeNodeResDto[], key: string, newNode: TreeNodeResDto): TreeNodeResDto[] {
    return nodes.map((node) => {
        if (node.key === key) {
            if (node.children) {
                const index = node.children.findIndex((node) => node.nodeType === 'DIR');
                if (index > -1) {
                    node.children.splice(index, 0, newNode); // 将新节点插入到指定节点的 children 中
                } else {
                    node.children.push(newNode);
                }
            } else {
                node.children = [newNode]; // 如果指定节点没有 children，创建一个新的 children 数组并插入新节点
            }
        } else if (node.children) {
            node.children = insertNodeByKey(node.children, key, newNode); // 递归处理子节点
        }
        return node;
    });
}


function TreeUpdate(props: IProps) {
    const { appPage, httpPage, dispatch } = props;

    const getOptions = (key: string) => {
        console.log(httpPage.httpTrees || [])
        return convertToTreeData(key, filterTreeData(httpPage.httpTrees || []));
    }


    return <ModalForm
        width={560}
        title={i18n('case.title.updateClass')}
        open={props.thisKey != undefined}
        initialValues={getNodeByKey(httpPage.httpTrees, props.thisKey)}
        onFinish={(values) => {
            dispatch({
                type: 'httpPage/updateTree',
                payload: {
                    id: parseInt(props.thisKey),
                    parentId: values.parentKey == undefined ? 0 : parseInt(values.parentKey),
                    name: values.name
                },
                callback: (treeNode: TreeNodeResDto) => {
                    let httpTrees = httpPage.httpTrees || [];
                    httpTrees = deleteNodeByKey(httpTrees, treeNode.key);
                    if (treeNode.parentKey !== "0") {
                        //不是根
                        httpTrees = insertNodeByKey(httpTrees, treeNode.parentKey, treeNode)
                    } else {
                        if (httpTrees.length > 0) {
                            httpTrees.splice(0, 0, treeNode);
                        } else {
                            httpTrees.push(treeNode);
                        }
                    }
                    dispatch({
                        type: 'httpPage/setHttpTrees',
                        payload: httpTrees,
                    })
                    props.callback();

                }
            });
            return true;
        }}
        maskClosable={true}
        modalProps={{
            destroyOnClose: true,
            okText: i18n('case.button.ok'),
            cancelText: i18n('case.button.cancel'),
            onCancel: () => {
                props.callback();
            },
        }}
    >
        <ProFormTreeSelect
            name="parentKey"
            label={i18n('case.label.parentName')}
            labelCol={{ span: 4 }} // 设置 label 标签占据整个宽度
            wrapperCol={{ span: 20 }} // 设置文本框占据整个宽度
            allowClear
            placeholder={i18n('case.placeholder.parentName')}
            request={async () => {
                return getOptions(props.thisKey);
            }}
        />
        <ProFormText
            name="name"
            label={i18n('case.label.directoryName')}
            labelCol={{ span: 4 }} // 设置 label 标签占据整个宽度
            wrapperCol={{ span: 20 }} // 设置文本框占据整个宽度
            rules={[{ required: true, message: i18n('case.label.directoryName') + i18n('case.label.notNull') }]}
        />
    </ModalForm >
};


export default TreeUpdate;

