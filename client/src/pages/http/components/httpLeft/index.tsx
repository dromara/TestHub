
import React, { useEffect, useState } from 'react';
import classnames from 'classnames';
import { Dropdown, Menu, Tooltip } from 'antd';
import Iconfont from '@/components/Iconfont';
import { IAppPageState } from '@/models/appPage';
import { BaseConsoleStatus, TreeNodeResDto } from '@/typings';
import i18n from '@/i18n';
import { IHttpPageState } from 'umi';
import SearchTree from '@/components/SearchTree';
import MenuItem from 'antd/lib/menu/MenuItem';
import TreeAdd from './treeAdd';
import TreeUpdate from './treeUpdate';
import ApiAdd from './apiAdd';


interface IProps {
    appPage: IAppPageState;
    httpPage: IHttpPageState;
    dispatch: Function;
}

enum HttpNodeType {
    DIR = 'DIR',
    API = 'API',
    CASE = 'CASE'
}

enum handleType {
    ADD_DIR = 'ADD_DIR',
    UP_DIR = 'UP_DIR',
    ADD_API = 'ADD_API',
    CASE = 'CASE'
}

const menuList = [
    {
        key: handleType.ADD_DIR,
        icon: '\ueac5',
        title: " " + "新增文件夹",
    },
    {
        key: handleType.ADD_API,
        icon: '\ue713',
        title: " " + "新增接口",
    },
];
const dirList = [
    {
        key: handleType.ADD_DIR,
        icon: '\ueac5',
        title: " " + "新增文件夹",
    },
    {
        key: handleType.UP_DIR,
        icon: '\ue626',
        title: " " + "修改文件夹",
    },
    {
        key: handleType.ADD_API,
        icon: '\ue713',
        title: " " + "新增接口",
    },
];
const apiMenuList = [
    {
        key: handleType.CASE,
        icon: '\ueac5',
        title: " 接口用例",
    },
];

const recognizeIcon = (nodeType: string) => {
    //http 树的
    if (nodeType === "DIR") {
        return '\ueac5';
    } else {
        return '\ue713'
    }
}




function refresh() {
    let flag = true;

}

function HttpLeft(props: IProps) {
    const { httpPage, dispatch } = props;
    const [dirFlag, setDirFlag] = useState<string>();
    const [upDirFlag, setUpDirFlag] = useState<string>();
    const [addApiFlag, setAddApiFlag] = useState<string>();
    const [caseFlag, setCaseFlag] = useState<string>();

    const renderMenu = (node: TreeNodeResDto) => {
        const dirClickMenuList = (item: any) => {
            console.log(item.key)
            switch (item.key) {
                case handleType.ADD_DIR:
                    return setDirFlag(node.key);
                case handleType.UP_DIR:
                    return setUpDirFlag(node.key);
                case handleType.CASE:
                    return setCaseFlag(node.key);
                case handleType.ADD_API:
                    return setAddApiFlag(node.key);
            }
        }
        if (node.nodeType == undefined) {
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
        } else if (node.nodeType === "DIR") {
            return <Menu>
                {
                    dirList.map((item, index) => {
                        return <MenuItem key={index} onClick={dirClickMenuList.bind(null, item)}>
                            <span>
                                <Iconfont code={item.icon!}></Iconfont>
                                {item.title}
                            </span>
                        </MenuItem>
                    })
                }
            </Menu>
        } else if (node.nodeType === "API") {
            return <Menu>
                {
                    apiMenuList.map((item, index) => {
                        return <MenuItem key={index} onClick={dirClickMenuList.bind(null, item)}>
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

    const doubleClick = (node: TreeNodeResDto) => {
        if (node.nodeType == HttpNodeType.API) {
            if (node.data == null) {
                dispatch({
                    type: 'httpPage/loadApi',
                    payload: node.key,
                    callback: (data) => {
                        node.data = data;
                        dispatch({
                            type: 'httpPage/addHttpConsole',
                            payload: {
                                node: node,
                                status: BaseConsoleStatus.SAVED,
                            }
                        })
                    }
                })
            } else {
                dispatch({
                    type: 'httpPage/addHttpConsole',
                    payload: {
                        node: node,
                        status: BaseConsoleStatus.SAVED

                    }
                })
            }
        } else if (node.nodeType == HttpNodeType.DIR) {
            dispatch({
                type: 'httpPage/setNodeShow',
                payload: node.key
            })
        }

        return true;
    }
    const singleClick = (node: TreeNodeResDto) => {
        dispatch({
            type: 'httpPage/setNodeShow',
            payload: node.key
        })
    }

    return <>
        <SearchTree treeData={httpPage.httpTrees} recognizeIcon={recognizeIcon} refresh={refresh} renderMenu={renderMenu} doubleClick={doubleClick} singleClick={singleClick} />
        <TreeAdd parentKey={dirFlag} httpPage={httpPage} appPage={props.appPage} dispatch={props.dispatch} callback={() => { setDirFlag(undefined) }} />
        <TreeUpdate thisKey={upDirFlag} httpPage={httpPage} appPage={props.appPage} dispatch={props.dispatch} callback={() => { setUpDirFlag(undefined) }} />
        <ApiAdd parentKey={addApiFlag} httpPage={httpPage} appPage={props.appPage} dispatch={props.dispatch} callback={() => { setAddApiFlag(undefined) }} />
    </>

};

export default HttpLeft;