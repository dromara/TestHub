

import React, { useEffect, useRef, useState } from 'react';
import { IAppPageState, IHttpPageState } from 'umi';
import { Button, Col, Input, Radio, RadioChangeEvent, Row, Select } from 'antd';
import styles from './index.less';
import './index.less';
import classnames from 'classnames';
import DraggableContainer from '@/components/DraggableContainer';
import { HTTP } from '@/components/testHub/http/typings';
import RequestView from '@/components/testHub/http/RequestView';
import { HttpConsoleInfo, RuleEnvironmentResDto } from '@/typings';
import ResponseView from '@/components/testHub/http/ResponseView';
import { HttpApiSendResDto } from '@/typings/server/plugins/http';
import { ProFormSelect } from '@ant-design/pro-components';
import { DefaultOptionType } from 'antd/lib/select';
import i18n from '@/i18n';
import { values } from 'lodash';
const { Option } = Select;



interface IProps {
    appPage: IAppPageState;
    httpPage: IHttpPageState;
    dispatch: Function;
    data: HTTP.HttpRequestResDto;
    info: HttpConsoleInfo;
}

const options = [
    { label: '请求', value: 'req' },
    { label: '文档', value: 'doc' },
];

const getEnvs = (environments: RuleEnvironmentResDto[] | undefined) => {
    const envs: DefaultOptionType[] = [];
    environments?.map((environment: RuleEnvironmentResDto, index: number) => {
        envs.push({ value: environment.code, label: environment.name || environment.code });
    });
    return envs;
}
const colorMap = { GET: 'blue', POST: 'green', PUT: 'orange', DELETE: 'red' };

// const testData: HttpApiSendResDto = { "timeout": 60, "reqType": "json", "method": "GET", "url": "http://172.20.101.65:14001/api/bas/operator/loginPwd?optPwd=666666&optId=99990", "rests": {}, "params": { "optPwd": "666666", "optId": "99990" }, "headers": {}, "body": "{}", "reHeaders": { "date": "Mon, 27 Nov 2023 07:08:43 GMT", "set-cookie": "rts-token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJsb2dpblR5cGUiOiJsb2dpbiIsImxvZ2luSWQiOiI5OTk5MCIsInJuU3RyIjoib0xBcGdESzVpNW4zTGlVbHdVMEd0bTdEbXNtSEhUVDQiLCJvcHROYW1lIjoi566h55CG5ZGYQ1JPT1RfQ1RTIiwib3B0UHdkIjoiWDhHM1lXUmEifQ.qIybdmpWFaH-kis-CurytzTLf6MfxRXKtTg-Tj-tvJc; Max-Age=2147483647; Expires=Sat, 15 Dec 2091 18:22:50 +0800; Path=/", "content-type": "application/json", "transfer-encoding": "chunked", "vary": "Origin;Access-Control-Request-Method;Access-Control-Request-Headers" }, "reCookies": { "Max-Age": "2147483647", "Path": "/", "rts-token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJsb2dpblR5cGUiOiJsb2dpbiIsImxvZ2luSWQiOiI5OTk5MCIsInJuU3RyIjoib0xBcGdESzVpNW4zTGlVbHdVMEd0bTdEbXNtSEhUVDQiLCJvcHROYW1lIjoi566h55CG5ZGYQ1JPT1RfQ1RTIiwib3B0UHdkIjoiWDhHM1lXUmEifQ.qIybdmpWFaH-kis-CurytzTLf6MfxRXKtTg-Tj-tvJc", "Expires": "Sat, 15 Dec 2091 18:22:50 +0800" }, "reType": "json", "resData": "{\"msg\":\"Success\",\"traceId\":\"b70972b115a14e31a9c05f60ae961ece.1446.17010689238950151\",\"result\":{\"pwdChangePeriod\":-1,\"optName\":\"管理员CROOT_CTS\",\"optId\":\"99990\",\"pwdChangeDate\":\"2023-11-10\",\"token\":\"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJsb2dpblR5cGUiOiJsb2dpbiIsImxvZ2luSWQiOiI5OTk5MCIsInJuU3RyIjoib0xBcGdESzVpNW4zTGlVbHdVMEd0bTdEbXNtSEhUVDQiLCJvcHROYW1lIjoi566h55CG5ZGYQ1JPT1RfQ1RTIiwib3B0UHdkIjoiWDhHM1lXUmEifQ.qIybdmpWFaH-kis-CurytzTLf6MfxRXKtTg-Tj-tvJc\"},\"code\":\"000000\"}", "error": false, "statusCode": "200", "statusName": "OK" };


function HttpConsole(props: IProps) {
    const [info, setInfo] = useState(props.info);
    const [model, setModel] = useState(props.info.model == undefined ? "req" : props.info.model);
    const { httpPage, appPage, dispatch } = props;
    const [showResult, setShowResult] = useState<boolean>(httpPage.showResult);
    const [data, setData] = useState<HTTP.HttpRequestResDto>(
        props.data == undefined ?
            {
                method: "GET"
            } : props.data
    );
    const requestViewRef = useRef();

    useEffect(() => {
        setShowResult(httpPage.showResult);
    }, [httpPage.showResult]);


    const selectBefore = (
        <Select
            defaultValue={data.method}
            className={styles.httpMethod}
            // style={{ color: colorMap[data.method] }}
            onChange={(value) => {
                const dataOld = data;
                dataOld.method = value;
                setData(dataOld);
            }}>
            <Option value="GET" className={styles.boldText} style={{ color: colorMap["GET"] }}>GET</Option>
            <Option value="POST" className={styles.boldText} style={{ color: colorMap["POST"] }}>POST</Option>
            <Option value="PUT" className={styles.boldText} style={{ color: colorMap["PUT"] }}>PUT</Option>
            <Option value="DELETE" className={styles.boldText} style={{ color: colorMap["DELETE"] }}>DELETE</Option>
        </Select>

    );

    const volatileRef = useRef<any>(null);

    const onChange = (value: string) => {
        console.log(`selected ${value}`);
    };

    const sendApi = () => {
        dispatch({
            type: 'httpPage/sendApi',
            payload: data,
            callback: (resData) => {
                console.log(JSON.stringify(resData));
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

    return <div className={classnames(styles.httpConsoleBox)}>
        <div className={classnames(styles.httpTar)}>
            <Row justify="space-between">
                <Col>
                    <Radio.Group options={options} onChange={({ target: { value } }: RadioChangeEvent) => {
                        setModel(value);
                    }} value={model} optionType="button" />
                </Col>
                <Col >

                    <ProFormSelect
                        style={{ width: 150 }}
                        name={['envCode']}
                        label={"环境"}
                        labelCol={{ span: 6 }} // 设置 label 标签占据整个宽度
                        wrapperCol={{ span: 18 }} // 设置文本框占据整个宽度
                        options={getEnvs(appPage.curProject.environments)}
                        fieldProps={{
                            defaultValue: data.envCode,
                            onChange: (value) => {
                                const dataOld = data;
                                if (undefined == value) {
                                    dataOld.envCode = undefined;
                                } else {
                                    dataOld.envCode = value as string;
                                }
                                setData(dataOld);
                            }
                        }}
                    />

                </Col>
            </Row>

            <Row style={{ paddingTop: 15 }}>
                <Col span={16}>
                    <Input addonBefore={selectBefore} className="httpInput" defaultValue={data.url} onChange={(e) => {
                        const dataOld = data;
                        dataOld.url = e.target.value;
                        setData(dataOld);
                    }} />
                </Col>
                <Col offset={1} span={7}>
                    <Button type="primary" style={{ height: 40, width: 100 }} onClick={sendApi}>发送</Button>
                    <Button style={{ height: 40, width: 100, marginLeft: 40 }}>保存</Button>
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
                <div className="httpConsole" >
                    <RequestView data={data} ref={requestViewRef} effective={true} onChange={(t) => setData(t)} activeKey={props.info.activeKey} />
                </div>
            </div>
            <div className={styles.httpConsoleResult} hidden={!showResult}>
                {/* {JSON.stringify(info.resultData)} */}
                {
                    info.resultData != undefined && <ResponseView data={info.resultData} />
                }

            </div>
        </DraggableContainer>

    </div>;

};

export default HttpConsole;

