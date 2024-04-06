import React, { ForwardedRef, forwardRef, useEffect, useImperativeHandle, useRef, useState } from 'react';
import { HttpPageData, HttpPageInfo, IHttpPageState } from '@/models/httpPage';
import { Button, Col, Form, Input, Row, Select } from 'antd';
import styles from './index.less';
import classnames from 'classnames';
import { HTTP } from '@/components/TestHub/http/typings';
import RequestView from '@/components/TestHub/http/RequestView';
import ResponseView from '@/components/TestHub/http/ResponseView';
import i18n from '@/i18n';
import StateIndicator from '@/components/base/StateIndicator';
import { useDebounceFn } from 'ahooks';
import { getCurrentProject } from '@/utils/localStorage';
import { ConsoleInfo, ConsoleStatus } from '@/typings/client';
import Draggable from '@/components/base/Draggable';
import { ProFormText } from '@ant-design/pro-components';
import { TreeNodeResDto } from '@/typings';
import { ModelType } from '@/components/base/Draggable';
const { Option } = Select;

interface IProps {
  httpPage: IHttpPageState;
  dispatch: any;
  consoleInfo: ConsoleInfo<HttpPageData, HttpPageInfo>;
}

export interface IHttpConsoleRefFunction {
  doSave: () => boolean;
}

const colorMap = { GET: 'blue', POST: 'green', PUT: 'orange', DELETE: 'red' };

function HttpConsole(props: IProps, ref: ForwardedRef<IHttpConsoleRefFunction>) {
  const currentProject = getCurrentProject();
  const requestViewRef = useRef();
  const [form] = Form.useForm();
  const [page, setPage] = useState(props.consoleInfo.page);
  const { httpPage, dispatch, consoleInfo } = props;
  const [showResult, setShowResult] = useState<boolean>(httpPage.showResult);
  // console.log(consoleInfo.data);
  const [data, setData] = useState<HTTP.HttpRequestResDto>(
    consoleInfo.data == undefined
      ? {
          method: 'GET',
          body: {
            type: 'none',
            language: 'json',
            datas: [],
          },
          rests: [],
          headers: [],
          cookices: [],
        }
      : consoleInfo.data,
  );

  // console.log(data);

  useEffect(() => {
    setShowResult(httpPage.showResult);
  }, [httpPage.showResult]);

  const { run: myUseDebounceFn } = useDebounceFn(
    (data: any, callback) => {
      callback(data);
    },
    {
      wait: 100,
    },
  );

  function dispatchData(newData: HTTP.HttpRequestResDto) {
    dispatch({
      type: 'httpPage/setConsoleData',
      payload: {
        key: consoleInfo.key,
        data: newData,
      },
    });
  }
  useEffect(() => {
    dispatchData(data);
  }, [data]);

  const selectBefore = (
    <Select
      className={styles.httpMethod}
      defaultValue={data.method}
      style={{ width: 80 }}
      onChange={(value) => {
        const dataOld = data;
        dataOld.method = value;
        setData({ ...dataOld });
      }}
    >
      <Option value="GET" className={styles.boldText} style={{ color: colorMap['GET'] }}>
        GET
      </Option>
      <Option value="POST" className={styles.boldText} style={{ color: colorMap['POST'] }}>
        POST
      </Option>
      <Option value="PUT" className={styles.boldText} style={{ color: colorMap['PUT'] }}>
        PUT
      </Option>
      <Option value="DELETE" className={styles.boldText} style={{ color: colorMap['DELETE'] }}>
        DELETE
      </Option>
    </Select>
  );
  const check = async () => {
    if (requestViewRef.current != undefined) {
      const res = await requestViewRef.current.getData();
      if (!res.flag) {
        return false;
      }
    }
    return true;
  };
  const save = async () => {
    form
      .validateFields()
      .then(async (values) => {
        const newData = { ...data };
        newData.projectCode = currentProject?.code;
        newData.id = parseInt(consoleInfo.key);
        newData.parentId =
          consoleInfo.data.parentId == undefined || consoleInfo.data.parentId == null ? 0 : consoleInfo.data.parentId;
        newData.name = consoleInfo.name;
        let flag = await check();
        if (flag) {
          const oldStatus = consoleInfo.status;
          const url = consoleInfo.status == ConsoleStatus.UNTRACKED ? 'httpPage/saveApi' : 'httpPage/updateApi';
          dispatch({
            type: url,
            payload: newData,
            callback: (resData: any) => {
              if (resData != undefined) {
                dispatch({
                  type: 'httpPage/saveConsoleData',
                  payload: {
                    key: consoleInfo.key,
                    data: resData,
                  },
                });
                if (oldStatus == ConsoleStatus.UNTRACKED) {
                  const treeNode: TreeNodeResDto<any> = {
                    index: consoleInfo.key,
                    isFolder: false,
                    children: [],
                    data: resData,
                    canRename: false,
                    canMove: false,
                  };
                  dispatch({
                    type: 'httpPage/putTree',
                    payload: {
                      oldParentId: undefined,
                      parentId: newData.parentId,
                      node: treeNode,
                    },
                  });
                }

                return true;
              }
            },
          });
        }
      })
      .catch((errorInfo) => {
        console.log(errorInfo);
        // 处理验证失败逻辑
      });
    return false;
  };
  const sendApi = async () => {
    if (!(await check())) {
      return;
    }
    dispatch({
      type: 'httpPage/sendApi',
      payload: data,
      callback: (resData) => {
        resData.projectCode = currentProject?.code;
        dispatch({
          type: 'httpPage/setSendResult',
          payload: {
            key: consoleInfo.key,
            data: resData,
          },
        });
      },
    });
  };

  return (
    <div className={classnames(styles.httpConsoleBox)}>
      <div className={classnames(styles.httpTar)}>
        {/* <Row style={{ paddingTop: 14 }} justify="center"> */}
        <Row style={{ paddingTop: 14 }}>
          <Col span={3}>
            {/* <div style={{ width: '100%', height: '100%', display: 'flex', justifyContent: 'center' }}>
              <Select
                variant="filled"
                style={{ width: 80 }}
                size="large"
                placeholder="选择环境"
                onChange={() => {}}
                options={[
                  {
                    value: 'jack',
                    label: 'Jack',
                  },
                  {
                    value: 'lucy',
                    label: 'Lucy',
                  },
                  {
                    value: 'tom',
                    label: 'Tom',
                  },
                ]}
              />
            </div> */}
          </Col>
          <Col offset={1} span={13}>
            <Form
              form={form}
              initialValues={{ httpurl: data.url }}
              onFinish={({ form }) => {
                form
                  .validateFields()
                  .then((values) => {
                    // 处理表单提交逻辑
                    console.log('Validation passed:', values);
                  })
                  .catch((errorInfo) => {
                    // 处理验证失败逻辑
                    console.error('Validation failed:', errorInfo);
                  });
              }}
            >
              <Form.Item name="httpurl" rules={[{ required: true, message: '' }]}>
                <Input
                  size="large"
                  addonBefore={selectBefore}
                  className={classnames(styles.httpInput)}
                  placeholder="请输入URL"
                  // defaultValue={data.url}
                  // value={data.url}
                  onChange={(e) => {
                    const dataOld = data;
                    dataOld.url = e.target.value;
                    // dispatchData(dataOld);
                    setData({ ...dataOld });
                  }}
                />
              </Form.Item>
            </Form>
          </Col>
          <Col span={3}>
            <div style={{ width: '100%', height: '100%', display: 'flex', justifyContent: 'center' }}>
              <Button
                type="primary"
                size="large"
                style={{ width: 80 }}
                onClick={() => {
                  sendApi();
                }}
              >
                发送
              </Button>
            </div>
          </Col>
          <Col span={3}>
            <div style={{ width: '100%', height: '100%', display: 'flex', justifyContent: 'center' }}>
              <Button
                size="large"
                hidden={consoleInfo.status == ConsoleStatus.SAVED}
                style={{ width: 80 }}
                onClick={() => {
                  save();
                }}
              >
                保存
              </Button>
            </div>
          </Col>
        </Row>
      </div>
      <Draggable
        className={styles.httpConsoleContainer}
        model={ModelType.SECOND}
        show={httpPage.showResult}
        defaultSize={50}
        maxSize={100}
        minSize={0}
        secondShow={httpPage.showResult}
        layout="column"
      >
        <div className={styles.httpConsole}>
          <RequestView
            key={`RequestView-` + consoleInfo.key}
            data={data}
            ref={requestViewRef}
            effective={true}
            onDataChange={(t) => {
              myUseDebounceFn(t, (tData) => {
                tData.url = data.url;
                tData.method = data.method;
                tData.envCode = data.envCode;
                setData({ ...tData });
              });
            }}
            onPageChange={(activeKey) => {
              const newPage = page;
              newPage.activeKey = activeKey;
              // dispatchPage(newPage);
            }}
            activeKey={
              page != null ? (page.activeKey ? page.activeKey : data.method == 'GET' ? 'Params' : 'Body') : undefined
            }
          />
        </div>
        <div className={styles.httpConsoleResult}>
          {page != undefined && page.resultData != undefined && <ResponseView data={page.resultData} />}
          {page != undefined && page.resultData == undefined && <StateIndicator state="empty" />}
        </div>
      </Draggable>
    </div>
  );
}

export default HttpConsole;
