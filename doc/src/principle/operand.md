---
title: ⚽️ 操作数
order: 2
editLink: false
category:
  - 原理介绍
---

操作数是 TestHub 数据的最小操作单元,

我们用 FormulaNode 表示一个的操作数 - TestHub 中内置了 3 种基础操作数

- DataNode 表示固定值,是有配置人员手动指定的, 例如:`xxxx`
- PathNode 表示变量值,可以基于 jsonPath 从决策上下文中获取指定的变量值,标识为`${xxx.yyy}`
- FuncNode 表示方法型函数的调用,标识为`%{方法名(形参 1:操作数,......)}`

## 固定值-DataNode

data="root"中的 我们写死的 root 其实就是 DataNode 类型的操作数

```xml copy
<param code="password" name = "密码" dataType="STRING" data="root"/>
```

## 变量值-PathNode

::: warning PathNode 还不完善
PathNode 的语法目标是和 jsonPath 语法完美匹配，但是现在还是一个比较初级语法
如果目前的语法满足不了你的测试过程，请于<a href="https://gitee.com/failedgoddess/TestHub/issues">`gitee`</a>仓库提 lssues，我们会优先支持。
:::

### 操作对象

```xml copy
{
    "code":"0001",
    "name":"TestHub",
    "acctInfo":{
        "buyFrozenAmt":1000,
        "sellFrozenAmt":2000,
        "usableAmt":3000,
    }
}
```

例如执行过程中 stp3 的返回值是上述结构，我们需要使用上述对象的信息

| 写法                    | 例子                   |
| ----------------------- | ---------------------- |
| `${stp3.code}`          | 使用 code 的值         |
| `${stp3.acctInfo.code}` | 使用 buyFrozenAmt 的值 |
| `${stp3.acctInfo}`      | 使用 acctInfo 对象     |

<a name="操作列表"></a>

### 操作列表

| 写法                     | 例子                                            |
| ------------------------ | ----------------------------------------------- |
| `${stp2[0].contractnum}` | stp2 中返回值列表中的第一个(下标为 0)值的合同号 |

## 内置方法-FuncNode

内置函数是我们基于 SPI 机制提供的扩展点之一，目前我们实现了这些[内置函数](./function.md)

如果有不满足你的可以按照一下方式扩展

1. 继承 FunctionHandler

```java copy
package com.goddess.nsrule.core.executer.mode.base.function;

import com.alibaba.fastjson.JSONObject;
import com.goddess.nsrule.core.executer.context.Context;

public interface FunctionHandler {
    String getName();

    Object execute(Context context, JSONObject data);
}
```

2. 在 META-INF.services 路径下的 com.goddess.nsrule.core.executer.mode.base.function.FunctionHandler.FunctionHandler 配置的你扩展方法
