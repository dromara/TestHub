import React, { useCallback, useEffect, useMemo, useRef, useState } from 'react';
import classnames from 'classnames';
import styles from './index.less';
import {
  DraggingPosition,
  DraggingPositionItem,
  StaticTreeDataProvider,
  Tree,
  TreeItem,
  TreeItemIndex,
  TreeRef,
  UncontrolledTreeEnvironment,
} from 'react-complex-tree';
// import 'react-complex-tree/lib/style-modern.css';
import SearchInput from '../SearchInput';
import Iconfont from '../../base/Iconfont';
import { Button, Dropdown, message } from 'antd';
import { useDebounce } from 'ahooks';
import TextHighlighter from '../../base/TextHighlighter';

interface IProps {
  datas?: any;
  addMenu?: () => void;
  onRefresh?: () => void;
  onDoubleClick?: (item: TreeItem) => void;
  onDrop?: (items: TreeItem[], target: DraggingPositionItem) => void;
  renderMenu?: (item: TreeItem) => void;
  recognizeIcon?: (item: TreeItem) => string;
}
function SearchTree(props: IProps) {
  const { datas, onDrop, addMenu, onRefresh, renderMenu, onDoubleClick, recognizeIcon } = props;
  const [search, setSearch] = useState<string | ''>('');
  const tree = useRef<TreeRef>(null);

  const dataProvider = useMemo(() => {
    const provider = new StaticTreeDataProvider({ ...datas }, (item, data) => ({
      ...item,
      data,
    }));
    return provider;
  }, [datas]);
  useEffect(() => {
    dataProvider.onDidChangeTreeDataEmitter.emit(Object.keys(datas));
  }, [dataProvider]);

  const findItemPath = useCallback(
    async function findItemPaths(search, searchRoot = '0') {
      const item: TreeItem = await dataProvider.getTreeItem(searchRoot);
      const paths: TreeItemIndex[] = [];
      if (item.data.name.toLowerCase().includes(search.toLowerCase())) {
        paths.push([item.index]);
      }
      const searchedItems = await Promise.all(item.children?.map((child) => findItemPaths(search, child)) ?? []);
      searchedItems.forEach((searchedItem) => {
        searchedItem.forEach((path) => paths.push([item.index, ...path]));
      });
      return paths;
    },
    [dataProvider],
  );
  const debouncedValue = useDebounce(search, { wait: 100 });

  const handleInputChange = useCallback(
    (search: string) => {
      if (search) {
        findItemPath(search).then((paths) => {
          if (paths) {
            const prefixes = new Set<TreeItemIndex>();
            paths.forEach((path: any[]) => {
              path.slice(0, path.length - 1).forEach((prefix) => prefixes.add(prefix));
            });
            const uniquePrefixes: TreeItemIndex[] = Array.from(prefixes);
            tree.current?.collapseAll();
            tree.current?.expandSubsequently(uniquePrefixes).then(() => {});
          }
        });
      } else {
        tree.current?.abortSearch();
      }
    },
    [findItemPath],
  );
  useEffect(() => {
    handleInputChange(debouncedValue);
  }, [debouncedValue]);

  const onDropInner = (items: TreeItem[], target: DraggingPosition) => {
    if (onDrop) {
      onDrop(items, target as DraggingPositionItem);
    }
    tree.current?.expandItem((target as DraggingPositionItem).targetItem);
  };

  return (
    <div className={styles.searchTree}>
      <UncontrolledTreeEnvironment
        onDrop={onDropInner}
        canDragAndDrop={onDrop != null}
        canDropOnFolder={onDrop != null}
        canSearch={false}
        autoFocus={false}
        disableMultiselect
        dataProvider={dataProvider}
        showLiveDescription={true}
        getItemTitle={(item) => {
          return item.data.name;
        }}
        viewState={{
          tree: {},
        }}
        // defaultInteractionMode={{
        //   mode: 'custom',
        //   createInteractiveElementProps: (item, treeId, actions, renderFlags) => ({
        //     onClick: (e) => {
        //       actions.focusItem();
        //     },
        //     onDoubleClick: (e) => {
        //       actions.focusItem();
        //       if (!item.isFolder) {
        //         if (onDoubleClick) {
        //           onDoubleClick(item);
        //         }
        //       } else {
        //         if (renderFlags.isExpanded) {
        //           tree.current?.collapseItem(item.index);
        //         } else {
        //           tree.current?.expandItem(item.index);
        //         }
        //       }
        //     },
        //     tabIndex: !renderFlags.isRenaming ? (renderFlags.isFocused ? 0 : -1) : undefined,
        //   }),
        // }}
        renderItemTitle={(props) => {
          return search ? <TextHighlighter text={props.title} words={[search]} /> : props.title;
        }}
        renderItemArrow={({ item, context }) =>
          item.isFolder ? (
            <Iconfont
              className={classnames(styles.arrowsIcon, {
                [styles.rotateArrowsIcon]: context.isExpanded,
              })}
              code="&#xe618;"
            />
          ) : null
        }
        renderItem={({ item, title, info, depth, arrow, context, children }) => (
          <div>
            <Dropdown
              menu={{ items: renderMenu ? renderMenu(item) : [] }}
              disabled={renderMenu == null}
              trigger={['contextMenu']}
              overlayStyle={{
                zIndex: 99,
              }}
            >
              <div {...context.itemContainerWithChildrenProps}>
                <div
                  className={styles.treeNode}
                  {...context.itemContainerWithoutChildrenProps}
                  {...(context.interactiveElementProps as any)}
                >
                  <div className={styles.left}>
                    {new Array(depth).fill('indent').map((item, i) => {
                      return <div key={i} className={styles.indent} />;
                    })}
                  </div>

                  <div
                    className={classnames(styles.right, {
                      [styles.focused]:
                        context.viewStateFlags['focusedItem'] || context.viewStateFlags['selectedItems'],
                    })}
                    onDoubleClick={() => {
                      if (!item.isFolder) {
                        if (onDoubleClick) {
                          onDoubleClick(item);
                        }
                      }
                    }}
                  >
                    <div className={styles.arrows}>{arrow}</div>
                    <div className={styles.icon}>
                      {recognizeIcon && (
                        <Iconfont className={classnames(styles.arrowsIcon)} code={recognizeIcon(item)} />
                      )}
                    </div>
                    <div className={styles.contentText}>{title}</div>
                  </div>
                </div>
              </div>
            </Dropdown>
            {children}
          </div>
        )}
        renderTreeContainer={({ children, containerProps }) => <div {...containerProps}>{children}</div>}
        renderItemsContainer={({ children, containerProps }) => <div {...containerProps}>{children}</div>}
      >
        <div className={styles.searchTreeHeader}>
          <div className={styles.searchTreeSearchBox}>
            <SearchInput
              onChange={(value: string) => {
                setSearch(value);
              }}
              placeholder={'搜索'}
              className={styles.searchTreeInput}
            />
            {onRefresh && (
              <Button
                type="primary"
                className={styles.searchTreeButton}
                icon={<Iconfont code="&#xe8aa;" size={15} />}
                onClick={() => {
                  if (onRefresh) {
                    onRefresh();
                  }
                }}
              />
            )}

            {addMenu && (
              <Dropdown
                menu={{ items: addMenu() }}
                disabled={addMenu == null}
                trigger={['click']}
                overlayStyle={{
                  zIndex: 99,
                }}
              >
                <Button
                  type="primary"
                  className={styles.searchTreeButton}
                  icon={<Iconfont code="&#xe727;" size={15} />}
                />
              </Dropdown>
            )}
          </div>
        </div>
        <div className={styles.searchTreeOverview}>
          <Iconfont code="&#xe6ad;" />
          <span>概览</span>
        </div>
        <div className={styles.searchTreeBox}>
          <Tree treeId="tree" rootItem="0" treeLabel="Tree" ref={tree} />
        </div>
      </UncontrolledTreeEnvironment>
    </div>
  );
}

export default SearchTree;
