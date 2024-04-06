import React, { useEffect, useState } from 'react';
import classnames from 'classnames';
import styles from './index.less';
import { Col, Input, Row } from 'antd';
import { SearchOutlined } from '@ant-design/icons';
import { useDebounceFn } from 'ahooks';
interface IProps {
  activeKey: string | undefined;
  titleName: string | null;
  hidden: boolean;
  itemContainer?: React.ReactNode;
  setSearchKey: any;
  searchKey: string | undefined | '';
}

function MetaRight(props: IProps) {
  const { run: myDebounceFn } = useDebounceFn(
    (data: any, callback) => {
      callback(data);
    },
    {
      wait: 200,
    },
  );
  return (
    <>
      <div className={styles.metaRight} hidden={props.hidden}>
        <div className={styles.title}>
          <Row >
            <Col span={20} className={styles.titleName}>{props.titleName}</Col>
            <Col span={4} hidden={props.setSearchKey}>
              <Input placeholder="搜索"
                prefix={<SearchOutlined style={{ fontSize: 18 }} />}
                defaultValue={props.searchKey}
                variant="borderless"
                onChange={(e) => {
                  myDebounceFn(e.target.value, (dataOld) => {
                    props.setSearchKey(e.target.value)
                  });
                }}
              />
            </Col>
          </Row></div>
        <div className={styles.body} >
          {props.itemContainer}
        </div>
      </div>
    </>
  );
}


export default MetaRight;
