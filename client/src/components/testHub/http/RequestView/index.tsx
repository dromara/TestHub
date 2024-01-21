import { TabsProps, Tabs } from 'antd';
import React, { forwardRef, useEffect, useImperativeHandle, useRef, useState } from 'react';
import { HTTP } from '../typings';


import styles from './index.less';
import './index.less';
import classnames from 'classnames';
import Body from '../Body';
import Params from '../../assembly/params';

export type Props = {
    data: HTTP.HttpRequestResDto;
    effective?: boolean;
    onChange?: Function;
    activeKey?: string;
};


const RequestView = forwardRef((props: Props, ref) => {
    const headersRef = useRef();
    const cookicesRef = useRef();
    const paramsRef = useRef();
    const bodyRef = useRef();
    const restsRef = useRef();
    const [activeKey, setActiveKey] = useState(props.activeKey);
    const [request, setRequest] = useState<HTTP.HttpRequestResDto>(props.data);
    const onChange = (key: string) => {
        //todo 切换的时候保存一下数据
        setActiveKey(key);
    };

    const onBodyChange = (body: HTTP.BodyResDto) => {
        const requestNew = request;
        requestNew.body = body;
        setRequest({ ...requestNew });
    };

    useEffect(() => {
        if (props.onChange != undefined) {
            props.onChange({ ...request });
        }
    }, [request]);
    useImperativeHandle(ref, () => ({
        getData: async () => {
            const data = request;
            if (restsRef.current != undefined) {
                const res = await restsRef.current.getData();
                if (!res.flag) {
                    setActiveKey("Rests");
                    return { flag: false, data: {} };
                }
                data.rests = res.data;
            }
            if (headersRef.current != undefined) {
                const res = await headersRef.current.getData();
                if (!res.flag) {
                    setActiveKey("Headers");
                    return { flag: false, data: {} };
                }
                data.headers = res.data;
            }
            if (cookicesRef.current != undefined) {
                const res = await cookicesRef.current.getData();
                if (!res.flag) {
                    setActiveKey("Cookices");
                    return { flag: false, data: {} };
                }
                data.cookices = res.data;
            }
            if (paramsRef.current != undefined) {
                const res = await paramsRef.current.getData();
                if (!res.flag) {
                    setActiveKey("Params");
                    return { flag: false, data: {} };
                }
                data.params = res.data;
            }
            if (bodyRef.current != undefined) {
                const res = await bodyRef.current.getData();
                if (!res.flag) {
                    console.log("bodyRef")
                    setActiveKey("Body");
                    return { flag: false, data: {} };
                }
                data.body = res.data;
            }

            return { flag: true, data: data };
        },
    }));

    const items: TabsProps['items'] = [
        {
            key: 'Rests',
            label: `Rests`,
            forceRender: true,
            children: <Params
                params={request.rests == undefined ? [] : request.rests}
                ref={restsRef} effective={props.effective}
                onChange={(datas) => {
                    const requestNew = request;
                    requestNew.rests = datas;
                    setRequest({ ...requestNew });
                }} />,
        },
        {
            key: 'Headers',
            label: `Headers`,
            forceRender: true,
            children: <Params params={request.headers == undefined ? [] : request.headers}
                ref={headersRef} effective={props.effective}
                onChange={(datas) => {
                    const requestNew = request;
                    requestNew.headers = datas;
                    setRequest({ ...requestNew });
                }} />,
        }, {
            key: 'Cookices',
            label: `Cookices`,
            forceRender: true,
            children: <Params params={request.cookices == undefined ? [] : request.cookices}
                ref={cookicesRef} effective={props.effective}
                onChange={(datas) => {
                    const requestNew = request;
                    requestNew.cookices = datas;
                    setRequest({ ...requestNew });
                }} />,
        },
        {
            key: 'Params',
            label: `Params`,
            forceRender: true,
            children: <Params params={request.params == undefined ? [] : request.params}
                ref={paramsRef} effective={props.effective}
                onChange={(datas) => {
                    const requestNew = request;
                    requestNew.params = datas;
                    setRequest({ ...requestNew });


                }} />,
        },
        {
            key: 'Body',
            label: `Body`,
            forceRender: true,
            children: <Body data={request.body == undefined ? { type: "raw", language: "json", content: "", datas: [] } : request.body}
                height={"100%"} ref={bodyRef} effective={props.effective} onChange={onBodyChange} />,
        },
    ];
    return (
        <div className={classnames(styles.requestViewContainer)}>
            <div className="requestViewContainerTabs">
                <Tabs activeKey={activeKey} defaultValue={activeKey ? "Body" : activeKey} items={items} onChange={onChange} />
            </div>
        </div>
    );
});

export default RequestView;
