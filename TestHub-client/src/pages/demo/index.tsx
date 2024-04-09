import React, { useRef } from 'react';
import styles from './index.less';
import i18n from '@/i18n';
import './index.less';
import { Card, List } from 'antd';
import Layout from 'react-masonry-list';

import { EditOutlined, PlusOutlined } from '@ant-design/icons';
import ActionIcon from '@/components/TestHub/plugins/Icon';
const data = Array.from({ length: 100 }, (_, index) => { return { name: `Item ${index}`, type: "http", code: index } });
function MyComponent() {

  return (
    <>
      <div className={styles.demo} >
        <div className={styles.title} >
          as
        </div>
        <div className={styles.body} >

          {data.map((item) => (
            <div key={item.code} className={styles.dcardsec}>
              <ActionIcon actionType={item.type} className={styles.tagRightTop} />
              <div className={styles.main}>
                <div className={styles.head}>{item.code}</div>
                <div className={styles.body}>
                  {item.name}
                </div>
              </div>
              <div className={styles.icon}>
                <EditOutlined onClick={() => {
                  console.log("as");
                  // setIndex(index);
                  // setActionOpen(true);
                }}
                />
              </div>
            </div>

          ))}

          {/* 
          <List
            grid={{ gutter: 16, column: 2 }}
            dataSource={data}
            renderItem={(item) => (
              <List.Item>
                <div className={styles.dcardsec}>
                  <ActionIcon actionType={item.type} className={styles.tagRightTop} />
                  <div className={styles.main}>
                    <div className={styles.head}>{item.code}</div>
                    <div className={styles.body}>
                      {item.name}
                    </div>
                  </div>
                  <div className={styles.icon}>
                    <EditOutlined onClick={() => {
                      console.log("as");
                      // setIndex(index);
                      // setActionOpen(true);
                    }}
                    />
                  </div>
                </div>
              </List.Item>
            )}
          /> */}
        </div>
      </div>
    </>
  );
}

export default MyComponent;
