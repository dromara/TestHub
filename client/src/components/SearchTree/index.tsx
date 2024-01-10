import React, { useState } from 'react';
import Iconfont from '../Iconfont';
import styles from './index.less';
import './index.less';
import classnames from 'classnames';
import SearchInput from '../SearchInput';
import { Dropdown, Menu, Tooltip } from 'antd';
import i18n from '@/i18n';
import { TreeNodeResDto } from '@/typings';
import LoadingContent from '../Loading/LoadingContent';
import { IAppPageState } from 'umi';

interface IProps {
    treeData?: TreeNodeResDto[]
    recognizeIcon: Function;
    renderMenu: Function;
    refresh: Function;
    doubleClick: Function;
    singleClick: Function;
}
function throttle(func, wait) {
    let lastCallTime = 0;
    return function (...args) {
        const now = Date.now();
        if (now - lastCallTime >= wait) {
            func(...args);
            lastCallTime = now;
        }
    };
}

function findNodesByText(treeData: TreeNodeResDto[], text: string) {
    const result: TreeNodeResDto[] = [];
    for (const treeNode of treeData) {
        var hit = false;
        if (treeNode.name?.toUpperCase()?.indexOf(text?.toUpperCase()) != -1) {
            treeNode.name = treeNode.name?.replace(text, `<span style='color:red;'>${text}</span>`);
            hit = true;
        }
        if (treeNode.children) {
            const childResult = findNodesByText(treeNode.children, text);
            if (childResult.length > 0) {
                treeNode.children = childResult;
                hit = true;
            }
        }
        if (hit) {
            result.push(treeNode);
        }

    }
    return result;
}
function deepCopyTreeData(treeData: TreeNodeResDto[]) {
    if (!Array.isArray(treeData)) {
        return [];
    }

    return treeData.map(node => {
        const copy = { ...node };
        copy.data = undefined;
        if (node.children) {
            copy.children = deepCopyTreeData(node.children);
        }
        return copy;
    });
}

function SearchTree(props: IProps) {
    const [searchInfo, setSearchInfo] = useState<string>();
    const [searchTreeData, setSearchTreeData] = useState<TreeNodeResDto[]>([]);

    const searchChange = (value: string) => {
        setSearchInfo(value);

        const result: TreeNodeResDto[] = findNodesByText(props.treeData == undefined ? [] : deepCopyTreeData(props.treeData), value);
        setSearchTreeData(result);

    };


    return <>
        <div className={styles.searchTreeAside}>
            <div className={styles.searchTreeHeader}>
                <div className={styles.searchTreeSearchBox}>
                    <SearchInput onChange={throttle(searchChange, 200)} placeholder={"搜索"} />
                    <div
                        className={classnames(styles.searchTreeRefresh, styles.searchTreeButton)}
                        onClick={props.refresh()}
                    >
                        <Iconfont code="&#xe8aa;" size={15} />
                    </div>

                    <div
                        className={classnames(styles.searchTreeCreate, styles.searchTreeButton)}
                    >
                        <Dropdown overlay={props.renderMenu({ key: "0" })} trigger={['click']}>
                            <Iconfont code="&#xe727;" />
                        </Dropdown>
                    </div>
                </div>
            </div>
            <div className={styles.searchTreeOverview}>
                <Iconfont code="&#xe6ad;" />
                <span>{i18n('case.button.overview')}</span>
            </div>
            <div className={classnames(styles.box)}>
                <LoadingContent data={props?.treeData || []} handleEmpty={true}>
                    {
                        ((searchInfo == undefined || searchInfo == "") ? props?.treeData : searchTreeData)?.map((item, index) => {
                            return <TreeNode
                                appPage={undefined}
                                key={item.key + index}
                                show={item.show != true ? false : true}
                                level={0}
                                data={item}
                                dispatch={undefined}
                                recognizeIcon={props.recognizeIcon}
                                renderMenu={props.renderMenu}
                                doubleClick={props.doubleClick}
                                singleClick={props.singleClick}
                            />
                        })
                    }
                </LoadingContent>
            </div>
        </div>
    </>
};

interface INodeProps {
    appPage?: IAppPageState;
    level: number,
    show: boolean;
    dispatch?: Function;
    data: TreeNodeResDto;
    recognizeIcon: Function;
    renderMenu: Function;
    singleClick: Function;
    doubleClick: Function;
}

function TreeNode(props: INodeProps) {
    const { appPage, show, dispatch, data, level } = props;
    const isLoading = false;


    //单击 展开-收起
    const handleClick = () => {
        props.singleClick(data)
    };
    //双击
    function nodeDoubleClick() {
        props.doubleClick(data);
    }
    return <>
        {
            data != undefined &&
            <>
                <Dropdown overlay={props.renderMenu(data)} trigger={['contextMenu']}>
                    <Tooltip placement="right" title={<span>{data.name}</span>}>
                        <div
                            className={classnames(styles.treeNode)} >
                            <div className={styles.left}>
                                {
                                    new Array(level).fill('indent').map((item, i) => {
                                        return <div key={i} className={styles.indent}></div>
                                    })
                                }
                            </div>
                            <div className={styles.right}>
                                {
                                    !data.leaf &&
                                    <div onClick={handleClick.bind(null)} className={styles.arrows}>
                                        {
                                            isLoading
                                                ?
                                                <div className={styles.loadingIcon}>
                                                    <Iconfont code='&#xe62d;' />
                                                </div>
                                                :
                                                <Iconfont className={classnames(styles.arrowsIcon, { [styles.rotateArrowsIcon]: show })} code='&#xe618;' />
                                        }
                                    </div>
                                }
                                <div className={styles.dblclickArea} onDoubleClick={nodeDoubleClick}>
                                    <div className={styles.typeIcon}>
                                        <Iconfont code={props.recognizeIcon(data.nodeType)!}></Iconfont>
                                    </div>
                                    <div className={styles.contentText} >
                                        <div className={styles.name} dangerouslySetInnerHTML={{ __html: data.name }}></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </Tooltip>
                </Dropdown>
                {show &&
                    data.children?.map((item: any, i: number) => {
                        return <TreeNode
                            appPage={appPage}
                            key={item.key + i}
                            show={item.show}
                            level={level + 1}
                            data={item}
                            dispatch={dispatch}
                            recognizeIcon={props.recognizeIcon}
                            renderMenu={props.renderMenu}
                            doubleClick={props.doubleClick}
                            singleClick={props.singleClick}
                        />
                    })
                }
            </>

        }

    </>

};


export default SearchTree;
