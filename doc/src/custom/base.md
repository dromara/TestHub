---
title: 🐼 基础组件
order: 1
editLink: false
category:
  - 用例编写
---

## rule 用例

rule 代表用例流程一个用例文件中 rule 必须为根标签,并且一个用例文件只能存在一个 rule 组件

<h3>参数</h3>

| 参数  | 名称     | 唯一 | 必填 | 可选值 | 说明                                        |
| ----- | -------- | ---- | ---- | ------ | ------------------------------------------- |
| code  | 用例编码 | 是   | 是   |        | ==如果编码不唯一<br/>会出现启动失败，切记== |
| name  | 用例名称 |      |      |        | 建议唯一                                    |
| model | 用例模式 |      | 是   | flow   | 目前用例仅支持 flow 流模式                  |

```xml copy
<?xml version="1.0" encoding="UTF-8"?>
<rule code = "DEMO_HTTP" name = "HTTP测试" model="flow"></rule>
```

这个配置我们可以解释为：定义了一个 HTTP 测试的用例 编码是 DEMO_HTTP，采用 flow 模式运行

<h3>子组件</h3>
子组件就是  rule组件中内部还可以有哪些组件标签

| 组件                                           | 名称         | 必选 |
| ---------------------------------------------- | ------------ | ---- |
| <a href="#param参数声明">params</a>            | 用例级参数   |      |
| [metaClasses](../principle/datatype.md#元对象) | 用例级元对象 |
| <a href="#action行为">actions</a>              | 用例级行为   |      |
| <a href="#flow流程">flows</a>                  | 用例流程列表 | 是   |

```xml copy
<rule code = "xlr" name = "xlr测试" model="flow">
    <params></params>
    <metaClasses></metaClasses>
    <actions></actions>
    <flows></flows>
</rule>
```

<a name="param参数声明"></a>

## param 参数声明

| 参数      | 名称     | 唯一 | 是否必填 | 可选值                                    | 说明                                             |
| --------- | -------- | ---- | -------- | ----------------------------------------- | ------------------------------------------------ |
| code      | 型参编码 | 是   | 是       |                                           | ==编码需要在<a href="#参数组">参数组</a>内唯一== |
| name      | 型参名称 |      | 是       |                                           | 建议唯一                                         |
| dataType  | 数据类型 |      |          | [metaClasses](../principle/datatype.md)   |                                                  |
| complex   | 列表维度 |      |          | [metaClasses](../principle/datatype.md)   |                                                  |
| necessary | 是否必传 |      |          | true<br/>false 默认                       |                                                  |
| data      | 默认值   |      |          | <a href="#formula基础">formula 表达式</a> |                                                  |

<a name="参数组"></a>

<h3>参数组</h3>

参数组就是这样的一个结构，他的作用时声明变量充当`型参`

```xml copy
<params>
    <param code="a" name = "变量a" dataType="NUMBER" complex="0" necessary="true"/>
    <param code="b" name = "变量b" dataType="NUMBER" complex="0" necessary="true"/>
</params>
```

<a name="型实参"></a>

<h3>型参</h3>

型实参是个开发用语，以下边的例子：add 方法声明中的 a 和 b 就叫做`型参`,在第 4 行调用的时候 1 和 2 就是`实参`

```java linenums="1"
public int add(int a,int b){
    return a+b;
}
add(1,2);
```

<a name="flow流程"></a>

## flow 流程

flow 是流程组件，表示在一个用例中 若干个流程，不同流程会顺序执行，不同流程中参数是隔离的。

| 参数 | 名称     | 唯一 | 必填 | 可选值 | 说明                 |
| ---- | -------- | ---- | ---- | ------ | -------------------- |
| code | 流程编码 | 是   | 是   |        | ==流程组内必须唯一== |
| name | 流程名称 |      |      |        | 流程组内建议唯一     |

<a name="流程组"></a>

<h3>流程组</h3>

流程组就是这样的一个结构

```xml copy
<flows>
    <flow code = "RU001G1" name="流程1"></flow >
</flows>
```

<h3>子组件</h3>

| 组件                                | 名称     | 必选 |
| ----------------------------------- | -------- | ---- |
| <a href="#execute执行">executes</a> | 执行步骤 | 是   |

<a name="action行为"></a>

## action 行为

| 参数     | 名称           | 唯一 | 必填 | 可选值                               | 说明                                           |
| -------- | -------------- | ---- | ---- | ------------------------------------ | ---------------------------------------------- |
| code     | 行为编码       | 是   | 是   |                                      | ==组必须唯一==                                 |
| name     | 行为名称       |      |      |                                      | 组内建议唯一                                   |
| type     | 能力类型       |      |      | [能力标识](../ability#能力标识)      |                                                |
| dataType | 返回值数据类型 |      |      | [数据类型](../principle/datatype.md) | 基础阶段建议 只使用 STRING 字符串、NUMBER 数值 |
| complex  | 返回值列表维度 |      |      | [数据类型](../principle/datatype.md) | 很重要注意 🔔 🔔 🔔 🔔 🔔 🔔                   |

::: danger 🔔 🔔 🔔 🔔 🔔 🔔

action 组件中的 complex 很重要，你要很清楚的明确当前 action 的响应值是不是列表，列表设置 complex = 1

:::

<h3>子组件</h3>

| 组件                                | 名称       | 必选                       | 说明 |
| ----------------------------------- | ---------- | -------------------------- | ---- |
| [params](../global/#行为级)         | 行为级参数 |                            |      |
| <a href="#execute执行">mappings</a> | 映射器     |                            |      |
| <a href="#bound基础">bound</a>      | 动态字符串 | ==SQL,APP,HTTP 必选==<br/> |      |

<a name="流程组"></a>

<h3>流程组</h3>

流程组就是这样的一个结构

```xml copy
<actions>
    <action></action >
</actions>
```

action 的全局配置移步[全局配置](../global#全局配置)

<a name="execute执行"></a>

## execute 执行步骤

<h3>基础参数</h3>
所有的execute均可以配置

| 参数       | 名称           | 唯一 | 必填 | 可选值              | 说明                                                                                                                        |
| ---------- | -------------- | ---- | ---- | ------------------- | --------------------------------------------------------------------------------------------------------------------------- |
| code       | 步骤编码       | 是   | 是   |                     | ==组件内必须唯一==                                                                                                          |
| name       | 步骤名称       |      |      |                     | 组件内建议唯一                                                                                                              |
| init       | 是否接受返回值 |      |      | true 默认<br/>false | 如果关闭接收则没有办法在后续获取执行行为的返回值了                                                                          |
| actionCode | 调用行为编码   |      | 是   |                     | actionCode 必填且必须存在 [判定存在规则](../global#行为覆盖规则)                                                            |
| block      | 异常阻断       |      |      | true<br/>false 默认 | 配置如果发生技术异常 或不满足组件规则时，该行为的后续行为还要不要执行，例如下单都发生异常了也就没必要检查订单信息是否正确了 |

[SQL ](sql.md#sql-执行-sql) ,[SQL_BEGIN ](sql.md#sql-begin-开启事务) ,[SQL_COMMIT ](sql.md#sql-commit-提交事务) 扩展参数

当被执行的行为属于 SQL，SQL_Begin，SQL_commit 能力类型的 可以在基础参数之上扩展一下参数

| 参数   | 名称     | 默认值                       | 说明                                                                                                                            |
| ------ | -------- | ---------------------------- | ------------------------------------------------------------------------------------------------------------------------------- |
| conKey | 事务标识 | 如果没有指定，默认为步骤编码 | 因为<a href="/ability/sql#完整-dmi-操作">完整 DMI 操作</a>只设计到一个事务所以需要指定同一个 conKey，要是多个事务指定多个就好了 |

[sleep ](/ability/sleep.md)扩展参数

当被执行的行为属于 sleep 能力类型的 可以在基础参数之上扩展一下参数

| 参数      | 名称     | 默认值                  | 说明           |
| --------- | -------- | ----------------------- | -------------- |
| sleepTime | 停顿时间 | 如果没有指定，默认 1000 | 时间单位为毫秒 |

<a name="步骤组"></a>

<h3>步骤组是不存在的</h3>

步骤组就是<a href="#flow流程">flow 组件</a>

<h3>子组件</h3>

| 组件                                     | 名称     | 必选                            | 说明                                                                                  |
| ---------------------------------------- | -------- | ------------------------------- | ------------------------------------------------------------------------------------- |
| <a href="#injects实参传递">injects</a>   | 实参传递 |                                 | <a href="#action行为">action</a>声明参数必传时必选                                    |
| <a href="#checkItem检查项">checkItem</a> | 检查项   | [CHECK ](/ability/check.md)必选 | 参考[如何测试下单接口 ](/ability/#如何测试下单接口) 。 的 stp5 校验信息你可以声明多个 |

<a name="injects实参传递"></a>

## injects 实参传递

在[如何测试下单接口 ](/ability/#如何测试下单接口) 中 stp2 下单返回的合同号要被传递到 stp4 下单后-查询订单信息调用 作为查询 test_order 的合同号时我们就已经在用参数传递了

在这里行为中我们声明型参 orderNo 单号

```xml copy
<action code="loadOrder" name="查询订单信息" type="SQL" dataType="MAP">
    <params>
        <param code="orderCode" name="订单单号" dataType="STRING"/>
    </params>
    <bound>
        SELECT * FROM test_order where code = '${orderCode}'
    </bound>
</action>
```

那么我们需要在调用这个行为的时候传递 orderCode 的值为 `${stp2.data.data.code}` 代表 第二步调用 HTTP 下单返回的合同号。<a href="#Formula基础">为什么如此表示</a>

```xml copy
<execute code="stp4" name="下单后-查询订单信息" actionCode="loadOrder">
    <injects>
        <inject code="orderCode" data="${stp2.data.data.code}"/>
    </injects>
</execute>
```

<a name="expression运算表达式"></a>

## expression 运算表达式

expression 就是[操作符](/principle/expression.md#操作符)，目前阶段只需要掌握简单的用法，有两种模式

### 逻辑运算

我们常说的 与、或的就属于逻辑运算

| 参数           | 名称     | 必填 | 可选值     | 说明 |
| -------------- | -------- | ---- | ---------- | ---- |
| expressionType | 类型     | 是   | logic      |      |
| operationCode  | 逻辑运算 | 是   | and<br/>or |      |

<h3>逻辑运算-组件</h3>

| 组件                                               | 名称     | 必选 |
| -------------------------------------------------- | -------- | ---- |
| <a href="#expression运算表达式">subExpressions</a> | 子运算符 | 是   |

```xml copy
<expression expressionType ="logic" operationCode="and" >
    <subExpressions>
        ....
    </subExpressions>
</expression>
```

### 关系运算

我们常说的 大于、小于、等于之类的就属于关系运算符

关系运算我们可以抽象为 A opention B 的形式。参考数学中 被除数 / 除数 的形式我们称 A 为被比较的阈值,B 为阈值。

| 参数             | 名称                 | 必填 | 可选值                                              | 说明                                           |
| ---------------- | -------------------- | ---- | --------------------------------------------------- | ---------------------------------------------- |
| expressionType   | 类型                 | 是   | relation                                            | =                                              |
| operationCode    | 关系运算符           | 是   | [关系运算符](/principle/expression.html#关系运算符) |                                                |
| cover            | 被比较的阀值         | 是   | <a href="#formula基础">formula 表达式</a>           |                                                |
| coverComplex     | 被比较的阀值列表维度 |      | 默认 0                                              |                                                |
| threshold        | 阀值                 | 是   | <a href="#formula基础">formula 表达式</a>           |                                                |
| thresholdComplex | 阀值列表维度         |      | 默认 0                                              |                                                |
| dataType         | 数据类型             |      | [数据类型](../principle/datatype.md)                | 基础阶段建议 只使用 STRING 字符串、NUMBER 数值 |

<a name="mapping映射器"></a>

## mapping 映射器

如果你是一名 JAVA 开发并且学习过 mybatis，那你一定对这个概念不陌生。mapping 映射器的功能也类似，主要解决参数映射的问题。我们调用能力的返回值与我们要用到变量名称的不同则可以通过 mapping 映射器组件解决

| 参数   | 名称     | 唯一 | 必填 | 说明 |
| ------ | -------- | ---- | ---- | ---- |
| code   | 源编码   | 是   | 是   |      |
| result | 目标编码 | 是   | 是   |      |

例如 源数据

```json
{
  "buy_frozen_amt": 1000,
  "sell_frozen_amt": 2000,
  "usable_amt": 3000
}
```

配置映射器

```xml copy
<mappings>
    <mapping code="buy_frozen_amt" result="buyFrozenAmt"/>
    <mapping code="sell_frozen_amt" result="sellFrozenAmt"/>
    <mapping code="usable_amt" result="usableAmt"/>
</mappings>
```

通过转译后

```json
{
  "buyFrozenAmt": 1000,
  "sellFrozenAmt": 2000,
  "usableAmt": 3000
}
```

<a name="formula基础"></a>

## formula 操作数基础

- TestHub 中内置了 3 种基础操作数
  - DataNode 表示固定值,是有配置人员手动指定的, 例如:xxxx
  - PathNode 表示变量值,可以基于 jsonPath 的写法从决策上下文中获取指定的变量值,标识为${xxx.yyy}
  - FuncNode 表示方法型函数的调用,标识为%{方法名(形参 1:操作数,......)

基础阶段我们掌握 DataNode 和 PathNode 就可以了，深度学习移步[操作数](/principle/operand.md)

data="root"中的 我们写死的 root 其实就是 DataNode 类型的操作数

```xml copy
<param code="password" name = "密码" dataType="STRING" data="root"/>
```

<!-- 在<a href="/#demo测试步骤">测试一个需求</a> 中我们每次执行测试用例传入的 regid 都不一定相同那么怎么保证发送到 appserver 的 comm 日志中 regid 为你输入的那个股东 id 呢？这就用到了 PathNode 类型了

$开头的`{}`扩住的就是 PathNode 类型的操作数了 -->

<a name="bound基础"></a>

## bound 动态字符串基础

bound 的作用就是构建字符串，比如用于[SQL ](sql.md#sql-执行-sql) 生成 sql 语句、

bound 的底层采用 Freemarker 模板引擎支持所有 [Freemarker](https://freemarker.apache.org/index.html) 的语法规则

<!-- 深度学习移步<a href="/12如何使用-高级#Bound动态字符串">高级功能</a> -->

目前阶段我们需要掌握最简单的用法就可以了

例入：acctId = 960307

需要组装一个 sql 字符串 acctId 占位符会替换是取的由执行用例时传入的 acctId 参数的真实值

```xml copy
<bound >
     SELECT * FROM test_account where id = '${acctId}'
</bound>
```

生成的 sql

```sql copy
<bound >
     SELECT * FROM test_account where id = '960307'
</bound>
```

生成的字符串中包含占位符的，组装一个 sql 字符串 ,需要组装的 sql 如下

```sql copy
    SELECT * FROM test_account where id = '${acctId}'
```

则需要 通过 CDATA 转译

```xml copy
<bound >
    <![CDATA[
            SELECT * FROM test_account where id = '${acctId}'
        ]]>
</bound>
```

生成的字符串中包含存在 xml 或者 Freemarker 语法标签的 比如用于[HTTP ](http.md)中也需要通过 CDATA 转译

```xml copy
<bound>
    <![CDATA[<acctId>960703</acctId>
            <orderPrice>1.8</orderPrice>
            <orderQty>100</orderQty>
    ]]>
</bound>
```
