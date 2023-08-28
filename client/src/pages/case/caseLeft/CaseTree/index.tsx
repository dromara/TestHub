import React, { useEffect, useState } from 'react';
import styles from './index.less';
import './index.less';
import classnames from 'classnames';
import { Dropdown, Menu, Tooltip } from 'antd';
import Iconfont from '@/components/Iconfont';
import { IAppPageState } from '@/models/appPage';
import { TreeNodeResDto } from '@/typings';
import LoadingContent from '@/components/Loading/LoadingContent';
import MenuItem from 'antd/lib/menu/MenuItem';
import TreeUpdate from '../../components/treeUpdate';
import AddRule from '../../components/addRule';
import TreeAdd from '../../components/treeAdd';
import MoveRule from '../../components/moveRule';
import i18n from '@/i18n';

interface IProps {
    appPage: IAppPageState;
    dispatch: Function;
}


function approximateTreeNode(treeData: TreeNodeResDto[], target: string, isDelete = true) {
    if (target) {
        const newTree: TreeNodeResDto[] = JSON.parse(JSON.stringify(treeData));
        newTree.map((item, index) => {
            if (item.children?.length) {
                item.children = approximateTreeNode(item.children, target, false);
            }
            if (item.children?.length) {
                if (item.name?.toUpperCase()?.indexOf(target?.toUpperCase()) == -1 && approximateTreeNode(item.children, target).length < 1 && isDelete) {
                    delete newTree[index];
                } else {
                    item.nameDiv = item.name?.replace(target, `<span style='color:red;'>${target}</span>`);
                }
            } else {
                if (item.name?.toUpperCase()?.indexOf(target?.toUpperCase()) == -1 && isDelete) {
                    delete newTree[index];
                } else {
                    item.nameDiv = item.name?.replace(target, `<span style='color:red;'>${target}</span>`);
                }
            }

        })
        return newTree.filter(i => i)
    } else {
        return treeData;
    }
}

function CaseTree(props: IProps) {
    const { appPage, dispatch } = props;
    const [searchedTreeData, setSearchedTreeData] = useState<TreeNodeResDto[] | null>(null);
    function filtrationDataTree(keywords: string | undefined) {
        if (!keywords) {
            setSearchedTreeData(null)
        } else if (appPage.curProject?.ruleTrees?.length && keywords) {
            setSearchedTreeData(approximateTreeNode(appPage.curProject?.ruleTrees, keywords));
        }
    }
    useEffect(() => {
        filtrationDataTree(appPage.caseSearchInfo);
    }, [appPage.caseSearchInfo])

    return <div className={classnames(styles.box)}>
        <LoadingContent data={appPage.curProject?.ruleTrees || []} handleEmpty={true}>
            {
                (searchedTreeData || appPage.curProject?.ruleTrees)?.map((item, index) => {
                    return <CaseTreeNode
                        appPage={appPage}
                        key={item.key + index}
                        show={true}
                        level={0}
                        data={item}
                        dispatch={dispatch}
                    />
                })
            }
        </LoadingContent>
    </div>
};
interface INodeProps {
    appPage: IAppPageState;
    level: number,
    show: boolean;
    dispatch: Function;
    data: TreeNodeResDto;
}

enum DirOption {
    UPTATE = 'UPTATE',
    ADD_DIR = 'ADD_DIR',
    ADD_CADSE = "ADD_CADSE"
}

enum CaseOption {
    MOVE = 'MOVE'
}


const menuList = [
    {
        key: DirOption.UPTATE,
        icon: '\ue626',
        title: " " + i18n('case.button.modify'),
    },
    {
        key: DirOption.ADD_DIR,
        icon: '\ueac5',
        title: " " + i18n('case.button.addClass'),
    },
    {
        key: DirOption.ADD_CADSE,
        icon: '\ue945',
        title: " " + i18n('case.button.addCase'),
    },
];
const caseMenuList = [
    {
        key: CaseOption.MOVE,
        icon: '\ue67d',
        title: " " + i18n('case.button.move'),
    },
];


function CaseTreeNode(props: INodeProps) {
    const { appPage, show, dispatch, data, level } = props;
    const [showChildren, setShowChildren] = useState(false);
    const [moveFlag, setMoveFlag] = useState(false);
    const [dirFlag, setDirFlag] = useState(false);
    const [subDirFlag, setSubDirFlag] = useState(false);
    const [caseFlag, setCaseFlag] = useState(false);
    const isLoading = false;
    const indentArr = new Array(level).fill('indent');
    const renderMenu = (nodeType: string) => {
        const dirClickMenuList = (item: any) => {
            switch (item.key) {
                case DirOption.UPTATE:
                    return setDirFlag(true);
                case DirOption.ADD_CADSE:
                    return setCaseFlag(true);
                case DirOption.ADD_DIR:
                    return setSubDirFlag(true);
            }
        }
        const caseClickMenuList = (item: any) => {
            switch (item.key) {
                case CaseOption.MOVE:
                    return setMoveFlag(true);
            }
        }
        if (nodeType === "DIR") {
            return <Menu>
                {
                    menuList.map((item, index) => {
                        return <MenuItem key={index} onClick={dirClickMenuList.bind(null, item)}>
                            <span>
                                <Iconfont code={item.icon!}></Iconfont>
                                {item.title}
                            </span>
                        </MenuItem>
                    })
                }
            </Menu>
        } else {
            return <Menu>
                {
                    caseMenuList.map((item, index) => {
                        return <MenuItem key={index} onClick={caseClickMenuList.bind(null, item)}>
                            <span>
                                <Iconfont code={item.icon!}></Iconfont>
                                {item.title}
                            </span>
                        </MenuItem>
                    })
                }
            </Menu>
        }

    }
    function renderTitle(data: TreeNodeResDto) {
        return <>
            <span>{data.name}</span>
            {/* {
                data.columnType && data.nodeType === TreeNodeType.COLUMN &&
                <span style={{ color: callVar('--custom-primary-color') }}>（{data.columnType}）</span>
            } */}
        </>
    }
    const recognizeIcon = (nodeType: string) => {
        if (nodeType === "DIR") {
            return '\ueac5';
        } else {
            return '\ue945'
        }
    }
    //单击 展开-收起
    const handleClick = () => {
        setShowChildren(!showChildren);
    };
    //双击
    function nodeDoubleClick() {
        if (data.nodeType === "DIR") {
            handleClick();
        } else {
            dispatch({
                type: 'appPage/addCaseConsole',
                payload: data,
            })
        }
    }
    return <>
        {
            data != undefined &&
            <>
                <MoveRule data={data} appPage={appPage} dispatch={dispatch} showFlag={moveFlag} callback={() => { setMoveFlag(false) }} />
                <TreeAdd parentKey={data.key} appPage={appPage} dispatch={dispatch} showFlag={subDirFlag} callback={() => { setSubDirFlag(false) }} />
                <TreeUpdate data={data} appPage={appPage} dispatch={dispatch} dirFlag={dirFlag} callback={() => { setDirFlag(false) }} />
                <AddRule parentKey={data.key} appPage={appPage} dispatch={dispatch} showFlag={caseFlag} callback={() => { setCaseFlag(false) }} />
                <Dropdown overlay={renderMenu(data.nodeType)} trigger={['contextMenu']}>
                    <Tooltip placement="right" title={renderTitle(data)}>
                        <div
                            className={classnames(styles.treeNode, { [styles.hiddenTreeNode]: !show })} >
                            <div className={styles.left}>
                                {
                                    indentArr.map((item, i) => {
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
                                                <Iconfont className={classnames(styles.arrowsIcon, { [styles.rotateArrowsIcon]: showChildren })} code='&#xe618;' />
                                        }
                                    </div>
                                }
                                <div className={styles.dblclickArea} onDoubleClick={nodeDoubleClick}>
                                    <div className={styles.typeIcon}>
                                        <Iconfont code={recognizeIcon(data.nodeType)!}></Iconfont>
                                    </div>
                                    <div className={styles.contentText} >
                                        <div className={styles.name} dangerouslySetInnerHTML={{ __html: appPage.caseSearchInfo == null || appPage.caseSearchInfo == "" ? data.name : data.nameDiv }}></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </Tooltip>
                </Dropdown>
                {
                    data.children?.map((item: any, i: number) => {
                        return <CaseTreeNode
                            appPage={appPage}
                            key={item.key + i}
                            show={showChildren && show}
                            level={level + 1}
                            data={item}
                            dispatch={dispatch}
                        />
                    })
                }
            </>

        }

    </>

};

export default CaseTree;