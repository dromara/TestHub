import { RuleResDto, TreeNodeResDto } from '@/typings';
import { ModalForm, ProFormSelect, ProFormText, ProFormTreeSelect } from '@ant-design/pro-components';
import { DefaultOptionType } from 'antd/lib/select';
import React, { useCallback, useContext, useEffect, useMemo, useRef, useState } from 'react';
import { IAppPageState } from 'umi';


interface IProps {
    appPage: IAppPageState;
    dispatch: Function;
    showFlag: boolean;
    data: TreeNodeResDto;
    callback: Function;
}
interface TreeDataItem {
    title: string;
    value: string;
    disabled: boolean;
    children?: TreeDataItem[];
}
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
                node.children.push(newNode);
            } else {
                node.children = [newNode]; // 如果指定节点没有 children，创建一个新的 children 数组并插入新节点
            }
        } else if (node.children) {
            node.children = insertNodeByKey(node.children, key, newNode); // 递归处理子节点
        }
        return node;
    });
}
function MoveRule(props: IProps) {
    const { appPage, dispatch, showFlag } = props;

    const getOptions = (key: string) => {
        return convertToTreeData(key, filterTreeData(appPage.curProject?.ruleTrees || []));
    }

    return <ModalForm
        width={560}
        title={"移动用例:" + props.data.name}
        initialValues={props.data}
        open={showFlag}
        onFinish={(values) => {
            dispatch({
                type: 'appPage/saveRuleTree',
                payload: { treeId: values.parentKey ? parseInt(values.parentKey) : 0, ruleCode: props.data.key },
                callback: (ruleResDto: RuleResDto) => {
                    let ruleTrees = appPage.curProject?.ruleTrees || [];
                    ruleTrees = deleteNodeByKey(ruleTrees, props.data.key);
                    const treeNode = props.data;
                    treeNode.parentKey = values.parentKey ? values.parentKey : "0";
                    if (treeNode.parentKey && treeNode.parentKey !== "0") {
                        //不是根
                        ruleTrees = insertNodeByKey(ruleTrees, treeNode.parentKey, treeNode)
                    } else {
                        ruleTrees.push(treeNode);
                    }
                    dispatch({
                        type: 'appPage/setRuleTrees',
                        payload: ruleTrees,
                    })
                    props.callback();
                }
            });
            return true;
        }}
        maskClosable={true}
        modalProps={{
            destroyOnClose: true,
            onCancel: () => {
                props.callback();
            },
        }}
    >
        <ProFormTreeSelect
            name="parentKey"
            label={"所属类目"}
            labelCol={{ span: 4 }} // 设置 label 标签占据整个宽度
            wrapperCol={{ span: 20 }} // 设置文本框占据整个宽度
            allowClear
            placeholder="默认为根目录"
            request={async () => {
                return getOptions(props.data.parentKey);
            }}
        />
    </ModalForm >
};


export default MoveRule;

