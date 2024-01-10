import i18n from '@/i18n';
import { TreeNodeResDto } from '@/typings';
import { ModalForm, ProFormText } from '@ant-design/pro-components';
import React, { } from 'react';
import { IAppPageState, IHttpPageState } from 'umi';


interface IProps {
    appPage: IAppPageState;
    httpPage: IHttpPageState;
    dispatch: Function;
    parentKey?: string;
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
    const { httpPage, dispatch, parentKey } = props;

    return <ModalForm
        width={560}
        title={i18n('case.button.addClass')}
        open={parentKey != undefined}
        onFinish={(values) => {
            if (props.parentKey == undefined) {
                return false;
            }
            dispatch({
                type: 'httpPage/addTree',
                payload: { parentId: parseInt(props.parentKey), projectCode: props.appPage.curProject.code, name: values.name },
                callback: (treeNode: TreeNodeResDto) => {
                    let httpTrees = httpPage.httpTrees || [];
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

