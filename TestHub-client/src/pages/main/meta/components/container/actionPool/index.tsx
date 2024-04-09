import React, { useEffect, useRef, useState } from 'react';
import classnames from 'classnames';
import styles from './index.less';
import cloneDeep from 'lodash/cloneDeep';

import ActionIcon from '@/components/TestHub/plugins/Icon';
import { EditOutlined, PlusOutlined } from '@ant-design/icons';


import { Button, Card, List, Modal } from 'antd';
import { IAppPageState } from '@/models/appPage';
import { RuleActionResDto } from '@/typings';
import Action from './env';
import i18n from '@/i18n';
interface IProps {
  dispatch: any;
  appPage: IAppPageState;
  searchKey: string | undefined | '';
}

const filter = (searchKey, allData) => {
  let newData = [];
  if (allData == undefined &&
    allData == null) {
    newData = [];
  } else if (searchKey == undefined ||
    searchKey == "") {
    newData = cloneDeep(allData);
  } else {
    for (let index = 0; index < allData.length; index++) {
      const item = allData[index];
      if (
        item.name && item.name.toUpperCase().includes(searchKey.toUpperCase()) || (
          item.code && item.code.toUpperCase().includes(searchKey.toUpperCase())) || (
          item.remark && item.remark.toUpperCase().includes(searchKey.toUpperCase()))
      ) {
        newData.push(item);
      }
    }
  }
  if (newData != undefined) {
    newData.push({ code: "+" });
  }

  return newData;
}


function ActionPool(props: IProps) {
  const { appPage, dispatch } = props;
  const [index, setIndex] = useState<number>(-1);
  const actionRef = useRef();
  const [actionOpen, setActionOpen] = useState(false);
  const actions = filter(props.searchKey, appPage.curProject?.actions);

  return (
    <>
      <div className={styles.paramPool}>
        <div className={styles.paramContainer} >
          <List
            grid={{
              gutter: 16,
              xs: 2,
              sm: 2,
              md: 3,
              lg: 4,
              xl: 4,
              xxl: 5,
            }}
            dataSource={actions}
            renderItem={(item: RuleActionResDto, index: number) => (
              <>
                <List.Item>
                  {
                    index != actions.length - 1 &&
                    <div className={styles.dcardsec} key={item.code}>
                      <ActionIcon actionType={item.type} className={styles.tagRightTop} />
                      <div className={styles.main}>
                        <div className={styles.head}>{item.code}</div>
                        <div className={styles.body}>
                          {item.name}
                        </div>
                      </div>
                      <div className={styles.icon}>
                        <EditOutlined onClick={() => {
                          setIndex(index);
                          setActionOpen(true);
                        }}
                        />
                      </div>
                    </div>
                  }
                  {
                    index == actions.length - 1 &&
                    <div className={styles.dcardsec}>
                      <Button type="text" block style={{ width: "100%", height: 48 }} onClick={() => { setIndex(-1); setActionOpen(true); }} >
                        <PlusOutlined style={{ fontSize: '22px' }} />
                      </Button>
                    </div>
                  }
                </List.Item>
              </>
            )}
          />
        </div>
      </div>


      <Modal
        title={"配置行为"}
        width={820}
        open={actionOpen}
        destroyOnClose
        onOk={async () => {
          if (actionRef.current != undefined) {
            const res = await actionRef.current.getData();
            const action = res.data;
            action.projectCode = appPage.curProject?.code;
            if (res.flag) {
              dispatch({
                type: 'appPage/saveAction',
                payload: {
                  index: index,
                  data: res.data
                },
                callback: () => { setIndex(-1); setActionOpen(false) }
              })
            }
          }
        }}
        onCancel={() => { setActionOpen(false) }}
        maskClosable={false}
        okText={i18n('case.button.ok')}
        cancelText={i18n('case.button.cancel')}
      >
        <Action data={(index > -1 && actionOpen) ? JSON.parse(JSON.stringify(appPage.curProject?.actions[index])) : { type: "HTTP", dataType: "MAP", extraInto: {} }} ref={actionRef} />
      </Modal >
    </>
  );
}

export default ActionPool;



{/*  */ }
