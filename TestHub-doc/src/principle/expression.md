---
title: 🏀 表达式与操作符
order: 3
editLink: false
category:
  - 原理介绍
---

## 操作符

<a name="关系运算符"></a>

### 关系运算符

| 操作符     | 标识符 | 类型 | 数值 | 字符串 | 布尔 | 年月日 | 时分秒 | 年月日时分秒 |
| ---------- | ------ | ---- | ---- | ------ | ---- | ------ | ------ | ------------ |
| `等于`     | eq     |      | ✅   | ✅     | ✅   | ✅     | ✅     | ✅           |
| `不等于`   | neq    |      | ✅   | ✅     | ✅   | ✅     | ✅     | ✅           |
| `大于`     | gt     |      | ✅   |        |      | ✅     | ✅     | ✅           |
| `小于等于` | le     |      | ✅   |        |      | ✅     | ✅     | ✅           |
| `小于`     | lt     |      | ✅   |        |      | ✅     | ✅     | ✅           |
| `大于等于` | ge     |      | ✅   |        |      | ✅     | ✅     | ✅           |
| `在集合`   | in     |      | ✅   | ✅     | ✅   | ✅     | ✅     | ✅           |
| `不在集合` | nin    |      | ✅   | ✅     | ✅   | ✅     | ✅     |
| `为空`     | en     | 单目 | ✅   | ✅     | ✅   | ✅     | ✅     | ✅           |
| `不为空`   | nn     | 单目 | ✅   | ✅     | ✅   | ✅     | ✅     | ✅           |
| `模糊匹配` | like   |      | ✅   |        |      |        |        |              |

### 逻辑运算符

::: info ThestHub 支持 || 或、 && 且，默认为短路运算

- check1 2=3 不成立 3=3 不进行比较
- check2 1=1 成立 2=3 不进行比较
  :::

```xml copy
<?xml version="1.0" encoding="UTF-8"?>
<rule code="cutoff" name="短路" model="flow">
    <flows>
        <flow code="flow1" name="流程1">
            <execute code="stp5" name="下单后-检查订单数据-资金账户数据" actionCode="check">
                <checkItem code="check1" name="短路且" msg="2=3不成立 3=3不进行比较">
                    <expression expressionType="logic" operationCode="and">
                        <subExpressions>
                            <expression expressionType="relation" operationCode="eq" dataType="NUMBER" cover="1" threshold="1"/>
                            <expression expressionType="relation" operationCode="eq" dataType="NUMBER" cover="2" threshold="3"/>
                            <expression expressionType="relation" operationCode="eq" dataType="NUMBER" cover="3" threshold="3"/>
                        </subExpressions>
                    </expression>
                </checkItem>

                <checkItem code="check2" name="短路或" msg="1=1 成立 2=3不进行比较">
                    <expression expressionType="logic" operationCode="or">
                        <subExpressions>
                            <expression expressionType="relation" operationCode="eq" dataType="NUMBER" cover="1" threshold="1"/>
                            <expression expressionType="relation" operationCode="eq" dataType="NUMBER" cover="2" threshold="3"/>
                        </subExpressions>
                    </expression>
                </checkItem>
            </execute>
        </flow>
    </flows>
</rule>
```

<img class="heardImg" src="/demo/cutoff.png">

## 运算表达式

ThestHub 支持关系、优先级、逻辑运算  
运算的模型我们可以抽象为 A opention B 的形式,opention 可以参考操作符部分。参考数学中 被除数 / 除数 的形式我们称 A 为被比较的阈值,B 为阈值。  
运算过程还要支持()优先级运算。

| 运算类型       | 必传          | 数据类型 | 被比较的阀值 | 阀值      | 子运算        |
| -------------- | ------------- | -------- | ------------ | --------- | ------------- |
| expressionType | operationCode | dataType | cover        | threshold | subExpression |

::: info 我们采用 Expression 来进行表达
[Expression](/custom/base.md#action-行为)

- 在 expressionType 为关系类的时候 operationCode 必须为关系运算符 被比较的阀值可以采用 Formula 表达式表示默认值
- 在 expressionType 为逻辑类的时候 operationCode 必须为逻辑运算符 表示 subExpression 列表元素之间的逻辑关系
  :::
