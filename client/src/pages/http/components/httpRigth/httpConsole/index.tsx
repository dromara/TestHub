

import React, { useEffect, useRef, useState } from 'react';
import { IAppPageState, IHttpPageState } from 'umi';
import { Button, Col, Dropdown, Input, Menu, Radio, RadioChangeEvent, Row, Select } from 'antd';
import styles from './index.less';
import './index.less';
import classnames from 'classnames';
import DraggableContainer from '@/components/DraggableContainer';
import { HTTP } from '@/components/testHub/http/typings';
import RequestView from '@/components/testHub/http/RequestView';
import { BaseConsoleInfo, BaseConsoleStatus, HttpConsoleInfo, RuleEnvironmentResDto, TreeNodeResDto } from '@/typings';
import ResponseView from '@/components/testHub/http/ResponseView';
import i18n from '@/i18n';
import { CheckCircleOutlined } from '@ant-design/icons';
import TextEllipsis from '@/components/TextEllipsis';
import LoadingContent from '@/components/Loading/LoadingContent';
import StateIndicator from '@/components/StateIndicator';
import { useDebounceFn } from 'ahooks';
const { Option } = Select;



interface IProps {
    appPage: IAppPageState;
    httpPage: IHttpPageState;
    dispatch: Function;
    console: BaseConsoleInfo<TreeNodeResDto, HttpConsoleInfo>;
    node: TreeNodeResDto;
    data: HTTP.HttpRequestResDto;
    info: HttpConsoleInfo;
}

const getEnvs = (environments: RuleEnvironmentResDto[] | undefined, envCode: string) => {
    const envs = [];
    environments?.map((environment: RuleEnvironmentResDto, index: number) => {
        envs.push(
            <Menu.Item key={environment.code}>
                <Row style={{ width: 80 }}>
                    <Col span={8}>
                        {envCode == environment.code && <CheckCircleOutlined style={{ color: "#87d068" }} />}
                        {envCode != environment.code && <CheckCircleOutlined style={{ color: "#A9A9A9" }} />}
                    </Col>
                    <Col span={16}>
                        <TextEllipsis text={environment.name || environment.code}></TextEllipsis>
                    </Col>
                </Row>
            </Menu.Item>
        );
    });
    return envs;
}
const colorMap = { GET: 'blue', POST: 'green', PUT: 'orange', DELETE: 'red' };


function HttpConsole(props: IProps) {
    const requestViewRef = useRef();
    const [info, setInfo] = useState(props.info);
    const { httpPage, appPage, dispatch } = props;
    const [showResult, setShowResult] = useState<boolean>(httpPage.showResult);
    const [data, setData] = useState<HTTP.HttpRequestResDto>(
        props.data == undefined ?
            {
                method: "GET",
                body: {
                    type: "row",
                    language: "json",
                    datas: []
                }
            } : props.data
    );
    function dispatchData(newData: HTTP.HttpRequestResDto) {
        dispatch({
            type: 'httpPage/setConsoleData',
            payload: {
                key: props.node.key,
                data: newData
            }
        })
    }


    useEffect(() => {
        setShowResult(httpPage.showResult);
    }, [httpPage.showResult]);


    useEffect(() => {
        dispatchData(data)
    }, [data]);


    const selectBefore = (
        <Select
            defaultValue={data.method ? data.method : "GET"}
            className={styles.httpMethod}
            style={{ color: colorMap[data.method ? data.method : "GET"] }}
            onChange={(value) => {
                const dataOld = data;
                dataOld.method = value;
                setData({ ...dataOld });
            }}>
            <Option value="GET" className={styles.boldText} style={{ color: colorMap["GET"] }}>GET</Option>
            <Option value="POST" className={styles.boldText} style={{ color: colorMap["POST"] }}>POST</Option>
            <Option value="PUT" className={styles.boldText} style={{ color: colorMap["PUT"] }}>PUT</Option>
            <Option value="DELETE" className={styles.boldText} style={{ color: colorMap["DELETE"] }}>DELETE</Option>
        </Select>

    );

    const volatileRef = useRef<any>(null);

    const { run: myUseDebounceFn } = useDebounceFn(
        (data: any, callback: Function) => {
            callback(data);
        },
        {
            wait: 500
        }
    );


    function handleMenuClick(e) {
        const newData = { ...data };
        newData.envCode = e.key;
        setData(newData);
    }


    const menu = (
        <Menu onClick={handleMenuClick}>
            {getEnvs(appPage.curProject.environments, data.envCode)}
        </Menu>
    );
    const sendApi = async () => {
        if (!await check()) {
            return;
        }
        dispatch({
            type: 'httpPage/sendApi',
            payload: data,
            callback: (resData) => {
                resData.projectCode = appPage.curProject.code;
                dispatch({
                    type: 'httpPage/setSendResult',
                    payload: {
                        key: data.id + "",
                        data: resData
                    },
                })
            }
        })
    };
    const check = async () => {
        if (requestViewRef.current != undefined) {
            const res = await requestViewRef.current.getData();
            console.log("验证结果" + res.flag);
            if (!res.flag) {
                return false;
            }
        }
        return true;
    }
    const save = async () => {
        const newData = { ...data };
        newData.projectCode = appPage.curProject.code;
        newData.id = parseInt(props.console.key)
        newData.parentId = (props.node.parentKey == undefined || props.node.parentKey == null) ? 0 : parseInt(props.node.parentKey)
        newData.name = props.node.name;
        newData.nodeType = "API";
        let flag = await check()
        if (!flag) {
            return;
        } else {
            console.log(newData)
            dispatch({
                type: 'httpPage/saveTree',
                payload: newData,
                callback: (resData: TreeNodeResDto) => {
                    if (resData != undefined) {
                        dispatch({
                            type: 'httpPage/saveConsoleData',
                            payload: {
                                key: props.node.key,
                                data: data
                            }
                        })
                    }

                }
            })
        }
    }

    return <div className={classnames(styles.httpConsoleBox)}>
        <div className={classnames(styles.httpTar)}>
            <Row style={{ paddingTop: 15 }}>
                <Col span={16}>
                    <Input addonBefore={selectBefore} className="httpInput" defaultValue={data.url} onChange={(e) => {
                        const dataOld = data;
                        dataOld.url = e.target.value;
                        setData({ ...dataOld });
                    }} />
                </Col>
                <Col offset={1} span={7}>

                    <Dropdown.Button overlay={menu} style={{ height: 40, width: 130 }}
                        onClick={() => { sendApi() }}
                        type='primary'
                    // disabled={info.isRunIng}
                    >
                        发送
                    </Dropdown.Button>

                    <Button style={{ height: 40, width: 80, marginLeft: 40 }}
                        hidden={props.console.status == BaseConsoleStatus.SAVED}
                        // hidden={data.ip == info.ip && data.port == info.port && data.reqInfo == info.reqInfo}
                        onClick={() => { save() }}
                    >保存</Button>
                </Col>
            </Row>
        </div>

        <DraggableContainer
            className={classnames(styles.httpConsoleContainer)}
            callback={() => {
                dispatch({
                    type: 'httpPage/setShowResult',
                    payload: true,
                })
            }}
            direction="row"
            volatileDom={{
                volatileRef: volatileRef,
                volatileIndex: 0,
            }}
        >
            <div ref={volatileRef} className={showResult ? styles.httpConsoleInfo : styles.httpConsoleInfoNoResult}>
                <div className={classnames(styles.httpConsole)}>
                    <RequestView data={data} ref={requestViewRef} effective={true} onChange={(t) =>
                        myUseDebounceFn(t, (tData) => {
                            setData(tData)
                        })
                    } activeKey={props.info.activeKey} />
                </div>
            </div>
            <div className={styles.httpConsoleResult} hidden={!showResult}>


                {/* <LoadingContent data={info.resultData} handleEmpty={true} >
                    <ResponseView data={info.resultData} />
                </LoadingContent> */}

                {
                    info.resultData != undefined && <ResponseView data={info.resultData} />
                }
                {
                    info.resultData == undefined && <StateIndicator state="empty" />
                }

                {/* <StateIndicator state="empty" /> */}
            </div>
        </DraggableContainer>

    </div>;

};

export default HttpConsole;

