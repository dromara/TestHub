---
title: 🍎 check 数据校验
order: 1
author: 失败女神
editLink: false
category:
  - 能力
---

## CHECK

这些校验项可以在自动化测试中使用，以验证数据是否按预期规则。校验过程将根据表达式中的条件和操作符进行计算，并根据结果判断校验是否通过。如果校验失败，将返回相应的错误消息，提供有关校验失败的详细信息。

::: tip 特殊提示

check 已经是全局的配置，不需要单独定义 action

:::

### 数据校验

```xml copy
<?xml version="1.0" encoding="UTF-8"?>
<rule code="DEMO_Check" name="测试Check校验数据" model="flow">
    <params>
        <param code="a" name="变量a" dataType="NUMBER" data="1002"/>
        <param code="b" name="变量b" dataType="NUMBER" data="1002"/>
        <param code="c" name="变量c" dataType="NUMBER" data="100"/>
    </params>
    <flows>
        <flow code="RU001G1">
            <execute code="stp8" actionCode="check" name="校验信息">
                <checkItem code="check0" name="变量a大于变量b" msg="变量a不大于变量c错误" repeat="3">
                    <expression expressionType="relation" operationCode="gt" dataType="NUMBER" cover="${a}" threshold="${c}"/>
                </checkItem>
                <checkItem code="check1" name="变量b小于变量c+1" msg="变量b不小于变量c+1错误">
                    <expression expressionType="relation" operationCode="gt" dataType="NUMBER" cover="${b}" threshold="%{add(attr1:${c},attr2:1)}"/>
                </checkItem>
                <checkItem code="check2" name="变量a等于变量B等于1002" msg="变量a == 变量b == 1002 错误">
                    <expression expressionType="logic" operationCode="and">
                        <subExpressions>
                            <expression expressionType="relation" operationCode="eq" dataType="NUMBER" cover="${a}" threshold="${b}"/>
                            <expression expressionType="relation" operationCode="eq" dataType="NUMBER" cover="${a}" threshold="1002"/>
                        </subExpressions>
                    </expression>
                </checkItem>
            </execute>
        </flow>
    </flows>
</rule>
```

<img class="heardImg" src="/demo/check.png">

### 列表循环校验

这里测试用例中 stp1 的 结果 返回一个列表 我们要对 列表中的每一个元素的 属性值进行校验就用到：断言校验-循环
在断言校验-循环中我们需要指定循环次数 check0 中 repeat="%{size(attr1:${stp2})}" 表示 stp2 结果列表有多少条数据就要循环多少次 内置函数 size
在循环中会有一个额外的参数 ${index} 表示循环中的当前次数 结合 操作数操作列表 就实现了对列表中每一个元素的操作

```xml copy
<?xml version="1.0" encoding="UTF-8"?>
<rule code="DEMO_Check_list" name="循环Check校验数据" model="flow">
    <actions>
        <action code="getList" name="常量列表" type="CONST" complex="1" dataType="MAP">
            <bound>
            [{"id":1,"orderCode":"123"},{"id":2,"orderCode":"123"}]
			</bound>
        </action>
    </actions>
    <flows>
        <flow code="RU001G1">
            <execute code="stp1" name="获取列表" actionCode="getList"/>
            <execute code="stp2" actionCode="check" name="校验信息">
                <checkItem code="check0" name="第一步的返回值id必须从1开始递增" msg="数据异常" repeat="%{size(attr1:${stp1})}">
                    <expression expressionType="relation" operationCode="eq" dataType="NUMBER" cover="${stp1[${index}].id}" threshold="%{add(attr1:${index},attr2:1)}"/>
                </checkItem>
            </execute>
        </flow>
    </flows>
</rule>
```

<img class="heardImg" src="/demo/check_loop.png">
