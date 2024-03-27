import React, { useEffect, useRef, useState } from 'react';

import classnames from 'classnames';

import styles from './index.less';
import LoadingContent from '@/components/base/Loading/LoadingContent';
import { Button, Col, Row } from 'antd';

function Case() {
  return (
    <>
      <Row display={'none'} align="middle" justify="center">
        <Col style={{ height: 237, lineHeight: 15 }}>
          没有正文
          <Button hidden={true}>保存</Button>
        </Col>
      </Row>
    </>
  );
}

export default Case;
