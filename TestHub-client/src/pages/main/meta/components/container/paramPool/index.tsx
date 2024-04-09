import React, { useEffect, useRef, useState } from 'react';
import classnames from 'classnames';
import styles from './index.less';
import cloneDeep from 'lodash/cloneDeep';

import ActionIcon from '@/components/TestHub/plugins/Icon';
import { DeleteOutlined, EditOutlined, PlusOutlined } from '@ant-design/icons';

// const data = Array.from({ length: 40 }, (_, index) => { return { name: `Item ${index}`, remark: `Item ${index}`, type: "http", code: `Item ${index}` } });


import { Button, Card, List, Modal } from 'antd';
import { IAppPageState } from '@/models/appPage';
import { RuleEnvironmentResDto } from '@/typings';
import i18n from '@/i18n';
import Env from './env';
const { Meta } = Card;
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


function ParamPool(props: IProps) {
  const envRef = useRef();
  const { appPage, dispatch } = props;
  const [index, setIndex] = useState<number>(-1);
  const [envOpen, setEnvOpen] = useState(false);
  const [delOpen, setDelOpen] = useState(false);
  const environments = filter(props.searchKey, appPage.curProject?.environments);

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
            dataSource={environments}
            renderItem={(item: RuleEnvironmentResDto, index: number) => (
              <>
                <List.Item>
                  {
                    index != environments.length - 1 &&
                    <Card
                      key={Math.random() * 10000000000000000}
                      style={{ width: "100%", height: 115 }}
                      actions={[
                        <EditOutlined
                          key="edit"
                          onClick={() => {
                            setIndex(index);
                            setEnvOpen(true);
                          }}
                        />,
                        <DeleteOutlined
                          key="del"
                          onClick={() => {
                            setIndex(index);
                            setDelOpen(true);
                          }}
                        />,
                      ]}
                    >
                      <Meta
                        title={item.code + "-" + item.name}
                        description={
                          <div className={styles.ellipsis}>{item.remark}</div>
                        }
                      />
                    </Card>
                  }
                  {
                    index == environments.length - 1 &&
                    <Card
                      key={Math.random() * 10000000000000000}
                    >
                      <Button type="text" block style={{ width: "100%", height: 82 }} onClick={() => { setIndex(-1); setEnvOpen(true); }} >
                        <PlusOutlined style={{ fontSize: '22px' }} />
                      </Button>
                    </Card>
                  }
                </List.Item>
              </>
            )}
          />
        </div>
      </div>
      <Modal
        title={"配置环境"}
        width={750}
        open={envOpen}
        destroyOnClose
        onOk={async () => {
          if (envRef.current != undefined) {
            const res = await envRef.current.getData();
            const env = res.data;
            env.projectCode = appPage.curProject?.code;
            dispatch({
              type: 'appPage/saveEnvironment',
              payload: {
                index: index,
                data: res.data
              },
              callback: () => { setIndex(-1); setEnvOpen(false) }
            })
          }
        }}
        onCancel={() => { setEnvOpen(false) }}
        maskClosable={false}
        okText={i18n('case.button.ok')}
        cancelText={i18n('case.button.cancel')}
      >
        <div className={styles.env_div}>
          {
            appPage.curProject &&
            <Env data={(index > -1 && envOpen) ? JSON.parse(JSON.stringify(appPage.curProject?.environments[index])) : {}} ref={envRef} />
          }
        </div>

      </Modal>
    </>
  );
}

export default ParamPool;
