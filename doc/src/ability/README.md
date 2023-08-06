---
title: 可以干什么
index: false
icon: laptop-code
editLink: false
category:
  - 使用指南
---

## 能力标识

TestHub 的设计思想是工作流流程编排，本质职能是帮助我们将测试过程中的用例流程通过脚本的形式编排出来,然后通过脚本多次执行的方式将我们从复杂的重复的流程中解脱出来。

::: warning
如果下列能力满足不了你的测试过程，请于<a href="https://gitee.com/failedgoddess/TestHub/issues">`gitee`</a>仓库提 lssues，我们会优先支持。
:::

| 能力标识                 | 说明                       | 是否内置 action |
| ------------------------ | -------------------------- | --------------- |
| [check ](check.md)       | 对数据进行校验，类似于断言 | ✅              |
| [checkobj ](checkobj.md) | 对比对象、列表             | ✅              |
| [sql ](sql.md)           | 无事务执行 sql 语句        |                 |
| [sql_call ](sql.md)      | 调用存储过程               |                 |
| [sql_begin ](sql.md)     | 开启事务                   | ✅              |
| [sql_commit ](sql.md)    | 提交事务                   | ✅              |
| [sleep ](sleep.md)       | 让程序休息指定毫秒数       | ✅              |
| [convert ](convert.md)   | 数据转换处理               |                 |
| [const ](const.md)       | 定义常量                   |                 |
| [http ](http.md)         | 发送 Http 请求             |                 |

<a name="demo测试步骤"></a>

## 如何测试下单接口

例如我们在 项目中 开发了一个 HTTP 下单接口功能如下

```
根据资金账号、买入价格、买入数量进行买入
买入下单成功后资金账号可用金额扣减 买入价格*买入数量
生成订单号
生成订单到test_order表，记录到买入价格到openorder中的买入金额、买入数量到test_order中的买入数量、记录订单金额为 买入价格*买入数量
```

我们要对该接口进行测试步骤如下：

```
第一步、下单前在数据库中查询资金账号，记录下单前可用金额
第二步、在http工具中发送调用接口下单的日志，记录返回的订单信息
第三步、下单后在数据库中查询资金账号，记录下单后可用金额
第四步、下单后在数据库中根据第二步返回的订单号查询test_order信息，记录买入数量、买入价格、订单金额
第五步、检查如下内容
    （1）第一步的下单前可用金额 - 买入价格*买入数量 是否等于 第三步的下单后可用金额
    （2）第二步的订单金额 是否等于 买入价格*买入数量
    （3）第四步的买入价格 是否等于 第二步的买入价格
    （4）第四步的买入数量 是否等于 第二步的买入数量
```

```xml copy
<?xml version="1.0" encoding="UTF-8"?>
<rule code="DEMO_Demo" name="完整下单测试demo" model="flow">
  <params>
        <param code="acctId" name="资金账号ID" dataType="STRING" data="960307"/>
        <param code="orderPrice" name="商品单价" dataType="NUMBER" data="1.8"/>
        <param code="orderQty" name="商品数量" dataType="NUMBER" data="100"/>
        <param code="url" name="数据库地址" dataType="STRING" data="jdbc:postgresql://192.168.0.5:5432/postgres?currentSchema=public"/>
        <param code="driver" name="驱动" dataType="STRING" data="org.postgresql.Driver"/>
        <param code="username" name="用户" dataType="STRING" data="postgres"/>
        <param code="password" name="密码" dataType="STRING" data="postgres"/>
        <param code="timeout" name="超时时间" dataType="NUMBER" data="1000"/>
    </params>
    <metaClasses>
        <metaClass code="account" name="资金账号">
            <properties>
                <property code="id" name="资金账号ID" dataType="NUMBER"/>
                <property code="usableAmt" name="可用金额" dataType="NUMBER"/>
            </properties>
        </metaClass>
    </metaClasses>
    <actions>
        <action code="getMap" name="常量MAP" type="CONST" dataType="MAP">
            <bound>
                {"acctId": 960307,"orderAmt": 180,"orderPrice": 1.8,"orderQty": 100}
            </bound>
        </action>
        <action code="remove" name="删除测试" type="CONVERT" dataType="MAP">
            <params>
                <param code="source" name="源数据" dataType="MAP"/>
            </params>
            <converts>
                <convert code="code" type="DEL" name="移除code"/>
                <convert code="id" type="DEL" name="移除id"/>
            </converts>
        </action>
        <action code="loadAccount" name="查询资金账号" type="SQL" dataType="account">
            <bound>
                SELECT * FROM test_account where id = ${acctId}
            </bound>
            <mappings>
                <mapping code="id" result="id"/>
                <mapping code="usable_amt" result="usableAmt"/>
            </mappings>
        </action>
        <action code="loadOrder" name="查询订单信息" type="SQL" dataType="MAP">
            <params>
                <param code="orderCode" name="订单单号" dataType="STRING"/>
            </params>
            <bound>
                SELECT * FROM test_order where code = '${orderCode}'
            </bound>
        </action>
        <action code="order" name="下单" type="HTTP" dataType="map">
            <params>
                <param code="acctId" name="资金账号ID" dataType="STRING" data="${acctId}"/>
                <param code="orderPrice" name="商品单价" dataType="NUMBER" data="${orderPrice}"/>
                <param code="orderQty" name="商品数量" dataType="NUMBER" data="${orderQty}"/>
            </params>
            <httpModel url="http://192.168.0.4:12004/order" method="post">
                <headers>
                    <param code="Content-Type" dataType="STRING" data="application/json;charset=utf-8"/>
                </headers>
                <body type="row" language="json">
                    <content>
                        {
                        "acctId": "${acctId}",
                        "orderPrice": "${orderPrice}",
                        "orderQty": "${orderQty}"
                        }
                    </content>
                </body>
            </httpModel>
        </action>
        <action code="upAccount" name="更新资金账号可用金额" type="SQL" dataType="MAP">
            <bound>
                UPDATE test_account set usable_amt = 10000 WHERE id = 960307
            </bound>
        </action>
    </actions>
    <flows>
        <flow code="RU001G2" name="下单流程">
            <execute code="stp1" name="下单前-查询资金账号" actionCode="loadAccount"/>
            <execute code="stp2" name="下单" actionCode="order"/>
            <execute code="stp3" name="下单后-查询资金账号" actionCode="loadAccount"/>
            <execute code="stp4" name="下单后-查询订单信息" actionCode="loadOrder">
                <injects>
                    <inject code="orderCode" data="${stp2.data.data.code}"/>
                </injects>
            </execute>
            <execute code="stp5" name="下单后-检查订单数据-资金账户数据" actionCode="check">
                <checkItem code="check1" name="数据异常" msg="数据异常">
                    <expression expressionType="logic" operationCode="and">
                        <subExpressions>
                            <expression expressionType="relation" operationCode="eq" dataType="NUMBER" cover="%{sub(attr1:${stp1.usableAmt},attr2:%{mult(attr1:${orderPrice},attr2:${orderQty})})}" threshold="${stp3.usableAmt}"/>
                            <expression expressionType="relation" operationCode="eq" dataType="NUMBER" cover="${stp2.data.data.orderAmt}" threshold="%{mult(attr1:${orderPrice},attr2:${orderQty})}"/>
                            <expression expressionType="relation" operationCode="eq" dataType="STRING" cover="${stp2.data.data.orderPrice}" threshold="${orderPrice}"/>
                            <expression expressionType="relation" operationCode="eq" dataType="STRING" cover="${stp2.data.data.orderQty}" threshold="${orderQty}"/>
                        </subExpressions>
                    </expression>
                </checkItem>
            </execute>
        </flow>
    </flows>
</rule>
```

<img class="heardImg" src="/demo/demo.png">
