import React, { useContext, createContext, useState } from 'react';


interface IProps {
    text: string;
}

// 创建一个 Context
const MyContext = createContext<{
    value: string | null;
    setValue: React.Dispatch<React.SetStateAction<string | null>>;
}>({
    value: null,
    setValue: () => { },
});

// 父组件
const ParentComponent = (props: IProps) => {
    // 在父组件中使用状态管理上下文的值
    const [value, setValue] = useState<string | null>(props.text);

    return (
        <MyContext.Provider value={{ value, setValue }}>
            <h1>Parent Component</h1>
            <p>{value}</p>
            <ChildComponent />
        </MyContext.Provider>
    );
};

// 子组件
const ChildComponent = () => {
    // 在子组件中获取上下文的值和修改函数
    const { value, setValue } = useContext(MyContext);

    const handleChangeValue = () => {
        // 在子组件中调用修改上下文值的方法
        setValue('New Value from Child');
    };

    return (
        <div>
            <h2>Child Component</h2>
            <p>Value from Context: {value}</p>
            <button onClick={handleChangeValue}>Change Context Value</button>
        </div>
    );
};



const App = () => {

    return (
        <>
            <ParentComponent text={"组件1"} />
            <ParentComponent text={"组件2"} />
        </>
    );
};

export default App;
