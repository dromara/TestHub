import React, { memo, useEffect, useState, useRef } from 'react';
import classnames from 'classnames';
import Iconfont from '@/components/base/Iconfont';
import { Popover, Dropdown, Modal, message } from 'antd';
import i18n from '@/i18n';
import { isValid } from '@/utils/check';
import lodash from 'lodash';
import styles from './index.less';
import { ConsoleStatus } from '@/typings/client';

export interface ITabItem {
  prefixIcon?: string | React.ReactNode;
  label: React.ReactNode;
  key: number | string;
  popover?: string | React.ReactNode;
  children?: React.ReactNode;
  editableName?: boolean;
  canClosed?: boolean;
  styles?: React.CSSProperties;
  status?: ConsoleStatus;
}

export interface IOnchangeProps {
  type: 'add' | 'delete' | 'switch';
  data?: ITabItem;
}

const MAX_TABS = 18;

interface IProps {
  className?: string;
  items?: ITabItem[];
  activeKey?: number | string | null;
  onSave?: (data: ITabItem) => boolean;
  onChange?: (key: string | number | null) => void;
  onEdit?: (action: 'add' | 'remove', data?: ITabItem[], list?: ITabItem[]) => void;
  hideAdd?: boolean;
  editableNameOnBlur?: (option: ITabItem) => void;
  concealTabHeader?: boolean;
  // 最后一个tab不能关闭
  lastTabCannotClosed?: boolean;
  destroyInactiveTabPane?: boolean;
}

export default memo<IProps>((props) => {
  const {
    className,
    items,
    onChange,
    onEdit,
    onSave,
    activeKey,
    hideAdd = true,
    lastTabCannotClosed,
    editableNameOnBlur,
    concealTabHeader,
    destroyInactiveTabPane = true,
  } = props;
  const [internalTabs, setInternalTabs] = useState<ITabItem[]>([]);
  const [internalActiveTab, setInternalActiveTab] = useState<number | string | null>(null);
  const [editingTab, setEditingTab] = useState<ITabItem['key'] | undefined>();
  const tabListBoxRef = useRef<HTMLDivElement>(null);
  const tabsNavRef = useRef<HTMLDivElement>(null);
  const isNumberKey = useRef<boolean>(false);
  const [unSaved, setUnSaved] = useState<ITabItem | undefined>();
  const [showMoreTabs, setShowMoreTabs] = useState<boolean>(false);

  useEffect(() => {
    if (isValid(activeKey)) {
      setInternalActiveTab(activeKey!);
    }
    isNumberKey.current = typeof activeKey === 'number';
  }, [activeKey]);

  useEffect(() => {
    setInternalTabs(items || []);
    if (items?.length && !isValid(internalActiveTab)) {
      if (!activeKey) {
        setInternalActiveTab(items[0]?.key);
      }
    }
  }, [items]);

  useEffect(() => {
    const fn = (e) => {
      if (e.deltaY) {
        e.preventDefault();
        // 鼠标滚轮事件，让tab可以横向滚动
        if (tabsNavRef.current) {
          tabsNavRef.current.scrollLeft -= e.deltaY;
        }
      }
    };
    tabsNavRef.current?.addEventListener('wheel', fn);
    return () => {
      tabsNavRef.current?.removeEventListener('wheel', fn);
    };
  }, []);

  useEffect(() => {
    onChange?.(internalActiveTab);
    // 聚焦的时候，聚焦的tab要在第一个
    if (tabListBoxRef.current) {
      const activeTab = tabListBoxRef.current.querySelector(`.${styles.activeTab}`);
      if (activeTab) {
        activeTab.scrollIntoView({ block: 'nearest' });
      }
    }
  }, [internalActiveTab]);

  useEffect(() => {
    // from copilot
    if (tabListBoxRef.current) {
      const tabsNavWidth = tabsNavRef.current?.getBoundingClientRect().width || 0;
      const tabListBoxWidth = tabListBoxRef.current?.getBoundingClientRect().width || 0;
      setShowMoreTabs(tabsNavWidth < tabListBoxWidth);
    }
  }, [internalTabs]);

  const removeTab = (data: ITabItem) => {
    const newInternalTabs = internalTabs?.filter((t) => t.key !== data.key);
    let activeKeyTemp = internalActiveTab;
    // 删掉的是当前激活的tab，那么就切换到前一个,如果前一个没有就切换到后一个
    if (data.key === internalActiveTab) {
      const index = internalTabs.findIndex((t) => t.key === data.key);
      if (index === 0) {
        activeKeyTemp = internalTabs[1]?.key;
      } else {
        activeKeyTemp = internalTabs[index - 1]?.key;
      }
    }
    changeTab(activeKeyTemp);
    setInternalTabs(newInternalTabs);
    onEdit?.('remove', [data], newInternalTabs);
  };

  const deleteTab = (data: ITabItem) => {
    if (data.status != ConsoleStatus.SAVED) {
      //需要提醒保存
      // setUnSaved(data);
      message.info('尚未保存请进行保存后关闭');
      return;
    } else {
      removeTab(data);
    }
  };

  const deleteOtherTab = (data: ITabItem) => {
    const newInternalTabs = internalTabs?.filter((t) => t.key === data.key || t.status != ConsoleStatus.SAVED);
    const deleteTabs = internalTabs?.filter((t) => t.key !== data.key && t.status == ConsoleStatus.SAVED);
    changeTab(data.key);
    setInternalTabs(newInternalTabs);
    onEdit?.('remove', deleteTabs, newInternalTabs);
  };

  // 关闭所有tab
  const deleteAllTab = () => {
    const newInternalTabs = internalTabs?.filter((t) => t.status != ConsoleStatus.SAVED);
    const deleteTabs = internalTabs?.filter((t) => t.status == ConsoleStatus.SAVED);
    if (newInternalTabs != undefined && newInternalTabs.length > 0) {
      changeTab(newInternalTabs[0].key);
      setInternalTabs(newInternalTabs);
      onEdit?.('remove', deleteTabs, newInternalTabs);
    } else {
      changeTab(null);
      setInternalTabs([]);
      onEdit?.('remove', [...internalTabs]);
    }
  };

  const changeTab = (key: string | number | null) => {
    setInternalActiveTab(key);
  };

  const handleAdd = () => {
    if (internalTabs.length >= MAX_TABS) {
      return;
    }
    onEdit?.('add');
  };

  const onDoubleClick = (t: ITabItem) => {
    if (t.editableName) {
      setEditingTab(t.key);
    }
  };

  const renderTabItem = (t: ITabItem, index: number) => {
    function inputOnChange(value: string) {
      internalTabs[index].label = value;
      setInternalTabs([...internalTabs]);
    }

    function onBlur() {
      editableNameOnBlur?.(t);
      setEditingTab(undefined);
    }

    function showClosed() {
      if (lastTabCannotClosed && internalTabs.length === 1) {
        return false;
      }
      if (t.canClosed === true) {
        return false;
      }
      return true;
    }

    const closeTabsMenu = [
      {
        label: i18n('common.button.close'),
        key: 'close',
        onClick: () => {
          deleteTab(t);
        },
      },
      {
        label: i18n('common.button.closeOthers'),
        key: 'closeOther',
        onClick: () => {
          deleteOtherTab(t);
        },
      },
      {
        label: i18n('common.button.closeAll'),
        key: 'closeAll',
        onClick: () => {
          deleteAllTab();
        },
      },
    ];

    return (
      <Dropdown key={t.key} menu={{ items: closeTabsMenu }} trigger={['contextMenu']}>
        <Popover mouseEnterDelay={0.8} content={t.popover} key={t.key}>
          <div
            onDoubleClick={() => {
              onDoubleClick(t);
            }}
            style={t.styles}
            className={classnames(styles.tabItem, { [styles.activeTab]: t.key === internalActiveTab })}
          >
            {t.key === editingTab ? (
              <input
                value={t.label as string}
                onChange={(e) => {
                  inputOnChange(e.target.value);
                }}
                className={styles.input}
                autoFocus
                onBlur={onBlur}
                type="text"
              />
            ) : (
              <div
                className={classnames(
                  styles.textBox,
                  { [styles.saved]: ConsoleStatus.SAVED == t.status },
                  { [styles.change]: ConsoleStatus.CHANGE == t.status },
                  { [styles.untracked]: ConsoleStatus.UNTRACKED == t.status },
                )}
                key={t.key}
                onClick={changeTab.bind(null, t.key)}
              >
                {/* <div className={styles.textBox} key={t.key} onClick={changeTab.bind(null, t.key)}> */}
                {t.prefixIcon &&
                  (typeof t.prefixIcon == 'string' ? (
                    <Iconfont className={styles.prefixIcon} code={t.prefixIcon} />
                  ) : (
                    t.prefixIcon
                  ))}
                <div className={styles.text}>{t.label}</div>
              </div>
            )}
            {showClosed() && (
              <div className={styles.icon} onClick={deleteTab.bind(null, t)}>
                <Iconfont code="&#xe6d5;" />
              </div>
            )}
          </div>
        </Popover>
      </Dropdown>
    );
  };

  const moreTabsMenu = (internalTabs || []).map((t) => {
    return {
      label: t.label,
      key: t.key.toString(),
      value: t.key,
    };
  });

  return (
    <>
      <div className={classnames(styles.tabBox, className)}>
        {!concealTabHeader && (
          <div className={styles.tabsNav} ref={tabsNavRef}>
            {!!internalTabs?.length && (
              <div className={styles.tabList} ref={tabListBoxRef}>
                {internalTabs.map((t, index) => {
                  return renderTabItem(t, index);
                })}
              </div>
            )}
            {
              <div className={styles.rightBox}>
                {showMoreTabs && (
                  <div className={styles.moreTabs}>
                    <Dropdown
                      menu={{
                        style: { maxHeight: '200px', overflowY: 'auto' },
                        items: moreTabsMenu,
                        selectable: true,
                        selectedKeys: [`${internalActiveTab}`],
                        onClick: (v) => {
                          const key = moreTabsMenu.find((t) => t?.key === v.key)?.value || null;
                          changeTab(key);
                        },
                      }}
                      trigger={['click']}
                    >
                      <a onClick={(e) => e.preventDefault()}>
                        <Iconfont code="&#xe81d;" />
                      </a>
                    </Dropdown>
                  </div>
                )}
                {!hideAdd && (
                  <div
                    className={classnames(styles.addIcon, {
                      [styles.addIconDisabled]: internalTabs.length >= MAX_TABS,
                    })}
                    onClick={handleAdd}
                  >
                    <Iconfont code="&#xe727;" />
                  </div>
                )}
              </div>
            }
          </div>
        )}
        {/* 隐藏的方案 */}
        {/* {!destroyInactiveTabPane ? (
        <div className={styles.tabsContent}>
          {internalTabs?.map((t) => {
            return (
              <div
                key={t.key}
                className={classnames(styles.tabsContentItem, {
                  [styles.tabsContentItemActive]: t.key === internalActiveTab,
                })}
              >
                {t.children}
              </div>
            );
          })}
        </div>
      ) : (
        <div className={styles.tabsContent}>
          <div className={classnames(styles.tabsContentItem, styles.tabsContentItemActive)}>
            {internalTabs.find((t) => t.key === internalActiveTab)?.children}
          </div>
        </div>
      )} */}
      </div>

      {/* <Modal
        title={(unSaved ? unSaved.label : '') + '尚未保存'}
        open={unSaved != undefined}
        onOk={() => {
          if (onSave != undefined) {
            const flag = onSave(unSaved);
            if (flag) {
              removeTab(unSaved);
              setUnSaved(undefined);
            }
          } else {
            removeTab(unSaved);
            setUnSaved(undefined);
          }

          return true;
        }}
        onCancel={() => {
          setUnSaved(undefined);
        }}
        maskClosable={false}
        okText="保存"
        cancelText="不保存"
      >
        尚未保存是否进行保存
      </Modal> */}
    </>
  );
});
