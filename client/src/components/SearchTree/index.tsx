import React from 'react';
import Iconfont from '../Iconfont';
import styles from './index.less';
import './index.less';
import classnames from 'classnames';
import SearchInput from '../SearchInput';
import { Dropdown, Menu } from 'antd';
import i18n from '@/i18n';
import MenuItem from 'antd/lib/menu/MenuItem';

interface IProps {
    dispatch: any;
}

enum handleType {
    DIR = 'DIR',
    API = 'API'
}

const menuList = [
    {
        key: handleType.DIR,
        icon: '\ueac5',
        title: " " + i18n('case.button.addClass'),
    },
    {
        key: handleType.API,
        icon: '\ue945',
        title: " " + i18n('case.button.addCase'),
    },
];

function SearchTree(props: IProps) {
    const { dispatch } = props;
    const searchTable = (value: string) => {
        // dispatch({
        //     type: 'appPage/setCaseSearchInfo',
        //     payload: value,
        // })
    };

    function refresh() {
        let flag = true;

    }
    const renderMenu = () => {

        const clickMenuList = (item: any) => {

        }

        return <Menu>
            {
                menuList.map((item, index) => {
                    return <MenuItem key={index} onClick={clickMenuList.bind(null, item)}>
                        <span>
                            <Iconfont code={item.icon!}></Iconfont>
                            {item.title}
                        </span>
                    </MenuItem>
                })
            }
        </Menu>
    };
    return <>
        <div className={styles.searchTreeAside}>
            <div className={styles.searchTreeHeader}>
                <div className={styles.searchTreeSearchBox}>
                    <SearchInput onChange={searchTable} placeholder={"搜索"} />
                    <div
                        className={classnames(styles.searchTreeRefresh, styles.searchTreeButton)}
                        onClick={refresh}
                    >
                        <Iconfont code="&#xe8aa;" size={15} />
                    </div>

                    <div
                        className={classnames(styles.searchTreeCreate, styles.searchTreeButton)}
                    >
                        <Dropdown overlay={renderMenu()} trigger={['click']}>
                            <Iconfont code="&#xe727;" />
                        </Dropdown>
                    </div>
                </div>
            </div>
            <div className={styles.searchTreeOverview}>
                <Iconfont code="&#xe6ad;" />
                <span>{i18n('case.button.overview')}</span>
            </div>
        </div>
    </>
};


export default SearchTree;
