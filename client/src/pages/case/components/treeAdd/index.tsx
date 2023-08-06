import i18n from '@/i18n';
import { TreeNodeResDto } from '@/typings';
import { ModalForm, ProFormText } from '@ant-design/pro-components';
import React, { } from 'react';
import { IAppPageState } from 'umi';


interface IProps {
    appPage: IAppPageState;
    dispatch: Function;
    showFlag: boolean;
    parentKey: string;
    callback: Function;
}

function insertNodeByKey(nodes: TreeNodeResDto[], key: string, newNode: TreeNodeResDto): TreeNodeResDto[] {
    return nodes.map((node) => {
        if (node.key === key) {
            if (node.children) {
                const index = node.children.findIndex((node) => node.nodeType === 'CASE');
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

function TreeAdd(props: IProps) {
    const { appPage, dispatch, showFlag } = props;

    return <ModalForm
        width={560}
        title={i18n('case.button.addClass')}
        open={showFlag}
        onFinish={(values) => {
            dispatch({
                type: 'appPage/addTree',
                payload: { treeType: appPage.curProject?.code + "_CASE", parentId: parseInt(props.parentKey), nodeType: "DIR", name: values.name },
                callback: (treeNode: TreeNodeResDto) => {
                    let ruleTrees = appPage.curProject?.ruleTrees || [];
                    if (treeNode.parentKey !== "0") {
                        //不是根
                        ruleTrees = insertNodeByKey(ruleTrees, treeNode.parentKey, treeNode)
                    } else {
                        if (ruleTrees.length > 0) {
                            const index = ruleTrees.findIndex((node) => node.nodeType === 'CASE');
                            ruleTrees.splice(index, 0, treeNode);
                        } else {
                            ruleTrees.push(treeNode);
                        }
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
            okText: i18n('case.button.ok'),
            cancelText: i18n('case.button.cancel'),
            onCancel: () => {
                // 取消按钮的回调函数
                props.callback();
            },
        }}
    >
        <ProFormText
            name="name"
            label={i18n('case.label.directoryName')}
            labelCol={{ span: 4 }} // 设置 label 标签占据整个宽度
            wrapperCol={{ span: 20 }} // 设置文本框占据整个宽度
            rules={[{ required: true, message: i18n('case.label.directoryName') + i18n('case.label.notNull') }]}
        />
    </ModalForm >
};


export default TreeAdd;

