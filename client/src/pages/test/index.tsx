import { Button, Drawer } from 'antd';
import React, { useContext, createContext, useState } from 'react';


const Cvvv = () => {
    const [envOpen, setEnvOpen] = useState(false);
    return (
        <>
            <Button type="primary" onClick={() => {
                setEnvOpen(true)

            }}>
                打开
            </Button>

            <Drawer title="配置环境"
                placement="right"
                onClose={() => { setEnvOpen(false) }}
                open={envOpen}
                width={800}
                maskClosable={false}
                forceRender={true}
                // destroyOnClose={true}
                extra={
                    <Button type="primary" onClick={() => {
                        setEnvOpen(false)

                    }}>
                        保存
                    </Button>
                }>
                内容
            </Drawer>
        </>
    );
};

export default Cvvv;
