import React, { useEffect, useState } from 'react';
import { Badge, Card, Col, Descriptions, Radio, Row, Tag, Tooltip } from 'antd';
import { ProTable } from '@ant-design/pro-components';
import MonacoEditor from 'react-monaco-editor/lib/editor';
import { useTheme } from '@/utils/hooks';
import { CardTabListType } from 'antd/lib/card';
import i18n from '@/i18n';
import ExpLog from '../../expLog';
import { CheckCircleOutlined, CloseCircleOutlined } from '@ant-design/icons';
import TextEllipsis from '@/components/TextEllipsis';



export type Props = {
    res: any;
    data: any;
};
const colorMap = {
    "POST": "magenta",
    "GET": "green",
    "DELETE": "red",
    "PUT": "volcano",
    "HEAD": "orange",
    "PATCH": "lime",
    "OPTIONS": "blue"
}

const options = {
    minimap: { enabled: false },
    selectOnLineNumbers: true,
    matchBrackets: 'near',
    scrollBeyondLastLine: false, // 设置编辑器是否可以滚动到最后一行之后
    automaticLayout: true, // 自动布局
    formatOnPaste: true,
    readOnly: true,
    scrollbar: {
        // vertical: 'hidden', // 关闭垂直滚动条
        // horizontal: 'hidden', // 关闭水平滚动条

        verticalScrollbarSize: 6,
        horizontalScrollbarSize: 6,
        verticalSliderSize: 6,
        horizontalSliderSize: 6,
        verticalHasArrows: false,
        horizontalHasArrows: false,
        arrowSize: 0,
        useShadows: true,
    },
};


const columns = [
    {
        title: i18n('http.columns.param'),
        dataIndex: 'key',
        width: '40%',
    },
    {
        title: i18n('http.columns.val'),
        dataIndex: 'val',
        width: '60%',
        ellipsis: true,
    },
];

function isUndefinedOrEmpty(obj: any) {
    return obj == null || typeof obj === 'undefined' || Object.keys(obj).length === 0;
}


const HttpRes = (props: Props) => {
    const themeColor = useTheme();
    useEffect(() => {
        setTheme(themeColor == 'dark' ? 'BlackTheme' : 'default');
    }, [themeColor]);
    const [theme, setTheme] = useState(themeColor == 'dark' ? 'BlackTheme' : 'default');


    const items = [
        //Rests参数
        <ProTable
            dataSource={Object.entries(props.data.rests || {}).map(([key, val]) => ({ key, val }))}
            columns={columns}
            search={false}
            pagination={false}
            options={{
                density: false,
                reload: false,
                search: false,
                setting: false,
            }}
        />,
        //Params参数
        <ProTable
            dataSource={Object.entries(props.data.params || {}).map(([key, val]) => ({ key, val }))}
            columns={columns}
            search={false}
            pagination={false}
            options={{
                density: false,
                reload: false,
                search: false,
                setting: false,
            }}
        />,
        //请求头
        <ProTable
            dataSource={Object.entries(props.data.headers || {}).map(([key, val]) => ({ key, val }))}
            columns={columns}
            search={false}
            pagination={false}
            options={{
                density: false,
                reload: false,
                search: false,
                setting: false,
            }}
        />,
        //请求体
        <>
            <Row style={{ height: 35, marginBottom: 10 }} align="middle">
                <Col span={24}>
                    <Radio.Group value={props.data.reqType == null ? "json" : props.data.reqType}>
                        <Radio.Button value={'json'} disabled={props.data.reqType != "json"}>json</Radio.Button>
                        <Radio.Button value={'text'} disabled={props.data.reqType != "text"}>text</Radio.Button>
                        <Radio.Button value={'xml'} disabled={props.data.reqType != "xml"}>xml</Radio.Button>
                    </Radio.Group>
                </Col>
            </Row>
            <MonacoEditor
                width={'100%'}
                theme={theme}
                language={props.data.reqType == null ? "json" : props.data.reqType}
                height={window.innerHeight * 0.3}
                value={props.data.reqType == "json" || props.data.reqType == null ? JSON.stringify(props.data.body, null, 2) : props.data.body}
                options={options}
            />
        </>,
        //返回值
        <>
            <Row style={{ height: 35, marginBottom: 10 }} align="middle">
                <Col span={24}>
                    <Radio.Group value={props.data.reType}>
                        <Radio.Button value={'json'} disabled={props.data.reType != "json"}>json</Radio.Button>
                        <Radio.Button value={'text'} disabled={props.data.reType != "text"}>text</Radio.Button>
                        <Radio.Button value={'xml'} disabled={props.data.reType != "xml"}>xml</Radio.Button>
                        <Radio.Button value={'html'} disabled={props.data.reType != "html"}>html</Radio.Button>
                    </Radio.Group>
                </Col>
            </Row>
            <MonacoEditor
                width={'100%'}
                language={props.data.reType}
                height={400}
                theme={theme}
                value={props.data.reType == "json" ? JSON.stringify(props.res?.data == undefined ? {} : props.res.data, null, 2) : props.res.data}
                options={options}
            />
        </>,
        //响应头
        <ProTable
            dataSource={Object.entries(props.res?.headers == undefined ? {} : props.res.headers).map(([key, val]) => ({ key, val }))}
            columns={columns}
            search={false}
            pagination={false}
            options={{
                density: false,
                reload: false,
                search: false,
                setting: false,
            }}
        />
    ];


    const operationTabList = () => {
        const reDatas: CardTabListType[] = [];
        reDatas.push({
            key: "0",
            tab: isUndefinedOrEmpty(props.data?.rests) ? <>{i18n('http.data.rests')}</> : <Badge dot color='lime'>{i18n('http.data.rests')}</Badge>,
        });
        reDatas.push({
            key: "1",
            tab: isUndefinedOrEmpty(props.data?.params) ? <>{i18n('http.data.params')}</> : <Badge dot color='lime'>{i18n('http.data.params')}</Badge>,
        });
        reDatas.push({
            key: "2",
            tab: isUndefinedOrEmpty(props.data?.headers) ? <>{i18n('http.data.headers')}</> : <Badge dot color='lime'>{i18n('http.data.headers')}</Badge>,
        });
        reDatas.push({
            key: "3",
            tab: isUndefinedOrEmpty(props.data?.body) ? <>{i18n('http.data.body')}</> : <Badge dot color='lime'>{i18n('http.data.body')}</Badge>,
        });
        reDatas.push({
            key: "4",
            tab: isUndefinedOrEmpty(props.res?.data) ? <>{i18n('http.data.resbody')}</> : <Badge dot color='lime'>{i18n('http.data.resbody')}</Badge>,
        });
        reDatas.push({
            key: "5",
            tab: isUndefinedOrEmpty(props.res?.headers) ? <>{i18n('http.data.resheaders')}</> : <Badge dot color='lime'>{i18n('http.data.resheaders')}</Badge>,
        });
        return reDatas;
    };

    const [tabStatus, seTabStatus] = useState<string>("0");
    const onOperationTabChange = (key: string) => {
        seTabStatus(key);
    };

    return (
        <>
            <Descriptions column={1}>
                <Descriptions.Item label={<b>{i18n('http.text.url')}</b>}>{props.data["url"]}</Descriptions.Item>
            </Descriptions>
            <Descriptions column={(props.data["errorLog"] !== null && props.data["errorLog"] !== undefined) ? 4 : 3}>
                <Descriptions.Item label={<b>{i18n('http.text.method')}</b>}><Tag color={colorMap[props.data.method == undefined ? "GET" : props.data.method.toUpperCase()]}>{props.data.method.toUpperCase()}</Tag></Descriptions.Item>
                <Descriptions.Item label={<b>{i18n('http.text.status')}</b>}>{props.data["statusCode"]}</Descriptions.Item>
                <Descriptions.Item label={<b>{i18n('http.text.statusDesc')}</b>}>{props.data["statusName"]}</Descriptions.Item>
                {
                    (props.data["errorLog"] !== null && props.data["errorLog"] !== undefined)
                    && (
                        <Descriptions.Item label={<b>{i18n('http.text.error')}</b>}>
                            <Tooltip
                                title={
                                    <ExpLog
                                        data={props.data["errorLog"]}
                                        result={{}}
                                        flow={{}}
                                    />
                                }
                                overlayStyle={{ maxWidth: 900 }}
                            >

                                {
                                    props.data["errorLog"].flag && <Tag icon={<CheckCircleOutlined />} color="green">
                                        {i18n('http.text.pass')}
                                    </Tag>
                                }
                                {
                                    !props.data["errorLog"].flag && <Tag icon={<CloseCircleOutlined />} color="red">
                                        {i18n('http.text.fail')}
                                    </Tag>
                                }

                            </Tooltip>
                        </Descriptions.Item>
                    )
                }

            </Descriptions>
            <Card
                bordered={false}
                tabList={operationTabList()}
                onTabChange={onOperationTabChange}
            >
                {items[parseInt(tabStatus)]}
            </Card >

        </>
    );
};

export default HttpRes;
