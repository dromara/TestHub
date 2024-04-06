---
title: ⚽️ 操作数
order: 2
editLink: false
category:
  - 原理介绍
---

操作数是 TestHub 数据的最小操作单元,

我们用 FormulaNode 表示一个的操作数 - TestHub 中内置了 5 种基础操作数

- DataNode 表示固定值,是有配置人员手动指定的, 例如:`xxxx`
- PathNode 表示变量值,可以基于 jsonPath 从决策上下文中获取指定的变量值,标识为`${xxx.yyy}`
- FuncNode 表示方法型函数的调用,标识为`%{方法名(形参 1:操作数,......)}`
- ArithmeticNode 表示计算算数运算, 例如:`1+2+3*4/5%3`
- MixNode 表示混合表达式

## 操作对象

后续介绍均以上下文中存在如下数据为前提

```xml copy
{
  "store": {
    "book": [
      {
        "category": "reference",
        "author": "Nigel Rees",
        "title": "Sayings of the Century",
        "price": 8.95
      },
      {
        "category": "fiction",
        "author": "Evelyn Waugh",
        "title": "Sword of Honour",
        "price": 12.99
      },
      {
        "category": "fiction",
        "author": "Herman Melville",
        "title": "Moby Dick",
        "isbn": "0-553-21311-3",
        "price": 8.99
      },
      {
        "category": "fiction",
        "author": "J. R. R. Tolkien",
        "title": "The Lord of the Rings",
        "isbn": "0-395-19395-8",
        "price": 22.99
      }
    ],
    "bicycle": {
      "color": "red",
      "price": 19.95
    }
  },
  "expensive": 2
}
```

## 语法

### 固定值-DataNode

data="root"中的 我们写死的 root 其实就是 DataNode 类型的操作数

```xml copy
<param code="password" name = "密码" dataType="STRING" data="root"/>
```

| 写法                   | 例子                                                     | 值                         |
| ---------------------- | -------------------------------------------------------- | -------------------------- |
| `-1.0086`              | 数字                                                     | -1.0086                    |
| `{attr:{a:1},attr1:1}` | json 对象                                                | {"attr":{"a":1},"attr1":1} |
| `[${expensive},1]`     | 列表 操作对象的 expensive 做为第一个元素，1 是第二个元素 | `[2,1]`                    |

### 变量值-PathNode

::: warning PathNode 几乎支持了所有 jsonPath 的语法,但是我们不支持 `$['store']['book'][0]['title']` 这种`[]`的写法
如果目前的语法满足不了你的测试过程，请于<a href="https://gitee.com/failedgoddess/TestHub/issues">`gitee`</a>仓库提 lssues，我们会优先支持。
jsonPath 语法学习 请参考 https://github.com/alibaba/fastjson/wiki/JSONPath
:::

| 写法                                       | 例子                                                                | 值                                         |
| ------------------------------------------ | ------------------------------------------------------------------- | ------------------------------------------ |
| `${expensive}`                             | 操作对象的 expensive 的值                                           | 2                                          |
| `${store.book.price}`                      | 操作对象的 store 的每一个 book 的 price 值                          | `[8.95, 12.99, 8.99, 22.99]`               |
| `${store.book[-1].author}`                 | 操作对象的 store 的最后一个 book 的 author                          | J. R. R. Tolkien                           |
| `${store.book[0:2].author}`                | 操作对象的 store 的 0 到 2book 的 author                            | Nigel Rees,Sword of Honour,Herman Melville |
| `${store.book[?(isbn)]}`                   | 操作对象的 store 的 book 中 isbn 属性不等于空的                     | Herman Melville,J. R. R. Tolkien           |
| `${store.book[author='Nigel Rees'].title}` | 操作对象的 store 的 book 中 author 等于 Nigel Rees 的 book 的 title | Sayings of the Century                     |

### 内置方法-FuncNode

| 写法                      | 例子                           | 值   |
| ------------------------- | ------------------------------ | ---- |
| `%{getNowTime()}`         | 获取当前时间                   | 看表 |
| `%{add(attr1:1,attr2:2)}` | 进行 1+2 ，key:val 的写法      | 3    |
| `%{add(1,2)}`             | 进行 1+2 默认 key 为下标的写法 | 3    |

内置函数是我们基于 SPI 机制提供的扩展点之一，目前我们实现了这些[内置函数](./function.md)

如果有不满足你的可以按照一下方式扩展

1. 继承 FunctionHandler

```java copy
package org.dromara.testhub.nsrule.core.executer.mode.base.function;

import com.alibaba.fastjson.JSONObject;
import org.dromara.testhub.nsrule.core.executer.context.Context;

public interface FunctionHandler {
    String getName();

    Object execute(Context context, JSONObject data);
}

```

2. 在 META-INF.services 路径下的 org.dromara.testhub.nsrule.core.executer.mode.base.function.FunctionHandler 配置的你扩展方法

### 算数运算-ArithmeticNode

是的我们支持了手写算数运算的表达式

| 符号 | 作用   |
| ---- | ------ |
| +    | 加     |
| -    | 减     |
| `*`  | 乘     |
| /    | 除     |
| %    | 取余   |
| ()   | 优先级 |

| 写法            | 例子                | 值  |
| --------------- | ------------------- | --- |
| `1+2+3*4/5%3`   | 计算`1+2+3*4/5%3`   | 5.4 |
| `1+(2+3)*4/5%3` | 计算`1+(2+3)*4/5%3` | 2   |

### 混合表达式-MixNode

用户组合所有 FormulaNode 最终结果返回字符串

| 写法                                             | 例子                                        | 值                                  |
| ------------------------------------------------ | ------------------------------------------- | ----------------------------------- |
| `http:${expensive}:10010/server/workerHeartbeat` | 将操作对象的 expensive 的值替换后返回字符串 | http:2:10010/server/workerHeartbeat |

## 实践

![](/images/build/表达式.jpg)

```xml copy
<?xml version="1.0" encoding="UTF-8"?>

<rule code="RULE_0000000013" name="Formula操作数使用说明" model="flow" project="default">
    <actions>
        <action code="getData" name="操作数据" type="CONST" dataType="MAP">
            <bound>
                {"store":{"book":[{"category":"reference","author":"Nigel Rees","title":"Sayings of the Century","price":8.95},{"category":"fiction","author":"Evelyn Waugh","title":"Sword of Honour","price":12.99},{"category":"fiction","author":"Herman Melville","title":"Moby Dick","isbn":"0-553-21311-3","price":8.99},{"category":"fiction","author":"J. R. R. Tolkien","title":"The Lord of the Rings","isbn":"0-395-19395-8","price":22.99}],"bicycle":{"color":"red","price":19.95}},"expensive":2}
            </bound>
        </action>
    </actions>
    <flows>
        <flow code="flow1">
            <execute code="stp1" name="获取基础操作数据" actionCode="getData"/>
            <execute code="stp2" name="固定值-DataNode" actionCode="check">
                <checkItem code="check1" name="固定值数字" msg="">
                    <expression expressionType="relation" operationCode="eq" dataType="NUMBER" cover="-1.0086" threshold="-1.0086"/>
                </checkItem>
                <checkItem code="check2" name="固定值字符串" msg="">
                    <expression expressionType="relation" operationCode="eq" dataType="STRING" cover="* 0/1 * * * ?" threshold="* 0/1 * * * ?"/>
                </checkItem>
                <checkItem code="check3" name="固定值列表" msg="">
                    <expression expressionType="relation" operationCode="eq" dataType="NUMBER" coverComplex="1" cover="[${stp1.expensive},1]" thresholdComplex="1" threshold="[2,1]"/>
                </checkItem>
            </execute>
            <execute code="stp3" name="变量值-PathNode" actionCode="check">
                <checkItem code="check1" name="操作对象的 expensive 的值" msg="">
                    <expression expressionType="relation" operationCode="eq" dataType="NUMBER" cover="${stp1.expensive}" threshold="2"/>
                </checkItem>
                <checkItem code="check2" name="操作对象的 store 的每一个 book 的 price 值" msg="">
                    <expression expressionType="relation" operationCode="eq" dataType="STRING" coverComplex="1" cover="${stp1.store.book.price}" thresholdComplex="1" threshold="[8.95,12.99,8.99,22.99]"/>
                </checkItem>
                <checkItem code="check3" name="操作对象的 store 的最后一个 book 的 author" msg="">
                    <expression expressionType="relation" operationCode="eq" dataType="STRING" cover="${stp1.store.book[-1].author}" threshold="J. R. R. Tolkien"/>
                </checkItem>
                <checkItem code="check4" name="操作对象的 store 的 0 到 2book 的 author" msg="">
                    <expression expressionType="relation" operationCode="eq" dataType="STRING" coverComplex="1" cover="${stp1.store.book[0:2].author}" thresholdComplex="1" threshold="[Nigel Rees,Sword of Honour,Herman Melville]"/>
                </checkItem>
                <checkItem code="check5" name="操作对象的 store 的 book 中 isbn 属性不等于空的 的 author" msg="">
                    <expression expressionType="relation" operationCode="eq" dataType="STRING" coverComplex="1" cover="${stp1.store.book[?(isbn)].author}" thresholdComplex="1" threshold="[Herman Melville,J. R. R. Tolkien]"/>
                </checkItem>
                <checkItem code="check6" name="操作对象的 store 的 book 中 author 等于 Nigel Rees 的 book 的 title" msg="">
                    <expression expressionType="relation" operationCode="eq" dataType="STRING" cover="${stp1.store.book[author='Nigel Rees'].title}" threshold="Sayings of the Century"/>
                </checkItem>
            </execute>
            <execute code="stp4" name="内置方法-FuncNode" actionCode="check">
                <checkItem code="check1" name="参数key:val形式" msg="">
                    <expression expressionType="relation" operationCode="eq" dataType="NUMBER" cover="%{add(attr1:1,attr2:2)}" threshold="3"/>
                </checkItem>
                <checkItem code="check2" name="参数,形式" msg="">
                    <expression expressionType="relation" operationCode="eq" dataType="NUMBER" cover="%{add(1,2)}" threshold="3"/>
                </checkItem>
            </execute>
            <execute code="stp5" name="算数运算-ArithmeticNode" actionCode="check">
                <checkItem code="check1" name="计算1+2+3*4/5%3" msg="">
                    <expression expressionType="relation" operationCode="eq" dataType="NUMBER" cover="1+2+3*4/5%3" threshold="5.4"/>
                </checkItem>
                <checkItem code="check2" name="计算1+(2+3)*4/5%3" msg="">
                    <expression expressionType="relation" operationCode="eq" dataType="NUMBER" cover="1+(2+3)*4/5%3" threshold="2"/>
                </checkItem>
            </execute>
            <execute code="stp6" name="混合表达式-MixNode" actionCode="check">
                <checkItem code="check1" name="store 的 book中第expensive+1个  的 author" msg="">
                    <expression expressionType="relation" operationCode="eq" dataType="STRING" cover="${stp1.store.book[%{add(${stp1.expensive},1)}].author}" threshold="J. R. R. Tolkien"/>
                </checkItem>
                <checkItem code="check2" name="store 的 book中第expensive+1个  的 author" msg="">
                    <expression expressionType="relation" operationCode="eq" dataType="STRING" cover="${stp1.expensive}.1" threshold="2.1"/>
                </checkItem>
            </execute>
        </flow>
    </flows>
</rule>

```
