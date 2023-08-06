---
title: 全局配置
index: false
icon: laptop-code
editLink: false
category:
  - 使用指南
---

## 目录

## 参数声明

### 系统级

<!-- 例如在在测试过程中我们经常需要在开发环境测试、然后再到发布测试环境测试，两者采用同样的测试用例只不过 两次运作的appserver地址不同 -->

<!-- !!! bug "🤔 系统级参数声明属于超级进阶功能，熟练使用前慎用" -->
<!-- 使用说明前请认真学习<a href="/4如何使用-高级#参数上下文取值规则">参数上下文取值规则</a> -->

### 用例级

🤔 为什么要有用例级的参数声明？
我们用例编写好之后会经常用到 本次运行测试开发环境、下次运行测试发布测试环境，每次指定被测试的环境不同但是其他内容是相同的，那我们就可以采用用例级参数声明来解决这个问题

我们已经在基础 DEMO 中用到了

例如 [CHECK-断言校验 ](/ability/check.md) 中我们在用例子的 < rule >根标签下直接声明了变量 a、变量 b、变量 c

```xml copy
<?xml version="1.0" encoding="UTF-8"?>
<rule code="DEMO_Check" name="测试Check校验数据" model="flow">
    <params>
        <param code="a" name="变量a" dataType="NUMBER" data="1002"/>
        <param code="b" name="变量b" dataType="NUMBER" data="1002"/>
        <param code="c" name="变量c" dataType="NUMBER" data="100"/>
    </params>

</rule>
```

在测试用例再执行的前你可以指定参数的具体值，

以变量 a 为例 necessary 配置为 true 的时候， 执行前相应的参数就必须填写。

### 行为级

🤔 为什么要有行为级的参数声明可以做什么？
例如[SQL-执行查询类 ](/ability/sql.md)如果我们使用用例级参数声明、那么在一次测试用例的运行中就只能链接一个数据库。

    如果我们要在一个测试用例中同时链接多个数据库怎么办？这时就用到行为级参数声明了！！！其中necessary和data参考
    <a href="#param声明规则">param声明规则</a>

```xml copy
<?xml version="1.0" encoding="UTF-8"?>
<rule code = "DEMO_SQL2" name = "行为级参数声明" model="flow">
    <params>
        <param code="acctid" name = "资金账号" dataType="STRING" data="001304736720"/>
        <param code="exchid" name = "市场ID" dataType="STRING" data="1" />
    </params>

    <actions>
        <action code ="loadMysql" name = "查询资金账号" type = "SQL" dataType="MAP">
            <params>
                <param code="url" name = "数据库地址" dataType="STRING" complex="0" data="jdbc:mysql://127.0.0.1:3306/spring-boot-demo"/>
                <param code="driver" name = "驱动" dataType="STRING" complex="0" data="com.mysql.cj.jdbc.Driver"/>
                <param code="username" name = "用户" dataType="STRING" complex="0" data="root"/>
                <param code="password" name = "密码" dataType="STRING" complex="0" data="root"/>
            </params>
            <bound>
                select * from account where acctid = '${acctid}' and exchid = '${exchid}'
            </bound>
        </action>
         <action code ="loadOracle" name = "查询资金账号" type = "SQL" dataType="MAP">
            <params>
                <param code="url" name = "数据库地址" dataType="STRING" complex="0" data="jdbc:oracle:thin:@172.20.0.7:1521/ctsdb"/>
                <param code="driver" name = "驱动" dataType="STRING" complex="0" data="oracle.jdbc.driver.OracleDriver"/>
                <param code="username" name = "用户" dataType="STRING" complex="0" data="********"/>
                <param code="password" name = "密码" dataType="STRING" complex="0" data="********"/>
            </params>
            <bound>
                select * from account where acctid = '${acctid}' and exchid = '${exchid}'
            </bound>
        </action>
    </actions>
    <flows>
        <flow code = "RU001G1" >
            <execute code="stp1" name="查询Mysql资金账户" actionCode="loadMysql"/>
            <execute code="stp2" name="查询Oracle资金账号" actionCode="loadOracle"/>
        </flow>
    </flows>
</rule>
```

### 执行到行为的传参

学习完行为级参数声明观察 在不同数据库执行相同的 sql 语句我们需要声明两个行为，这是勉强可以接受的，但是面对一下场景就实在难以接受了：
例如在[如何测试下单接口 ](/ability/#如何测试下单接口) 我们需要第二步需要下单、第四步要根据 下单的单号进行查询数据库里的订单信息

在 loadOrder 行为中 声明编码 orderCode

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

在 stp4 查询 test_order 信息 获取 第二步 的合同号 传递到 loadOrder 行为的 orderCode 参数声明中

```xml
<execute code="stp4" name="下单后-查询订单信息" actionCode="loadOrder">
    <injects>
        <inject code="orderCode" data="${stp2.data.data.code}"/>
    </injects>
</execute>
```

## 全局配置

全局配置文件指的是 rule-config.xml，在你安装目录下如图路径你可以找打他
!!! warning "全局配置被修改后，你的测试用例文件分享给其他同学的时候要注意 要将全局配置行为给他"

<!-- ![avatar](./imgs/demo/全局配置.png) -->

<a name="全局行为"></a>

### 全局行为

理论上我们每步 execute 执行的 actionCode 的编码都是要在用例文件 rule->actions 中声明的 但是[SQL_Begin-开启事务 ](/ability/sql.md#sql-begin-开启事务)中的 begin 为什么不需要声明呢？

```xml
<execute code="stp5" name="开启事务" actionCode="begin"/>
```

这是因为我们在你安装目录下的 rule-config.xml 已经为您配置了，如果您也有需要制作全局的 action 你可以可以在该文件中声明，那么你在任何一个测试用例中都可以用到了

<a name="行为覆盖规则"></a>

行为覆盖规则

例如我们在 rule-config.xml 全局配置中声明了一个行为 action 在某一个用例中也声明了一个相同编码的 action 在执行该用例的时候用到该 action 使用用例中的实现。

<img class="heardImg" src="/demo/antiocn.png">

<!-- ### 全局元对象

### 系统级参数声明

覆盖规则参考

## 参数上下文


### 取值规则

<a name="Bound动态字符串"></a>

## Bound 动态字符串

<a name="异常规则"></a>

## 行为异常规则 -->
