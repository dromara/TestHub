---
title: 全局配置
index: false
icon: laptop-code
editLink: false
category:
  - 使用指南
---

🤔 为什么会有全局配置，全局配置又是什么❓
在测试用例在执行过程中的，会用到参数、行为等组件，针对这些组件的覆盖策略TestHub内置了六种级别：系统级、项目级、环境级、用例（规则级）、流程级、行为级。
全局配置，特指：系统、项目、环境 三个级别，均在[组件库的使用 ](/logDesc/1_0_2.md#组件库的使用)中设置
```java copy
//见类定义
org.dromara.testhub.server.core.rule.Constant.OwnerType
```
1、系统级 整个系统都生效
2、项目级 项目中生效，一个系统中支持配置多个项目
3、环境级 一个项目下支持配置多个环境
4、用例级 一个项目下支持配置多个测试用例
5、流程级 一个用例中配置配置多个流程




## 参数声明
支持 ：环境级、用例（规则级）、行为级

<div style="display: flex; justify-content: center; align-items: center;">
    <img class="heardImg" src="/images/global/参数覆盖规则.jpg" width="30%">
</div>

### 环境级
🤔 为什么要有环境级的参数声明？

例如在测试过程中我们经常需要在开发环境测试、然后再到发布测试环境测试，两者采用同样的测试用例只不过 两次被测试的服务地址不同那我们就可以使用环境级别的参数声明
![](/images/logDesc/1_0_2/环境级参数.jpg)
![](/images/logDesc/1_0_2/选择环境.jpg)

### 用例级
🤔 为什么要有用例级的参数声明？

例如执行一个测试用例，我们每次执行可能采用不同的初始值
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

### 参数传递

执行到行为的传参学习完行为级参数声明观察 在不同数据库执行相同的 sql 语句我们需要声明两个行为，这是勉强可以接受的，但是面对一下场景就实在难以接受了：
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

## 行为声明
支持 ：系统级、项目级、用例级
<div class="centerDiv">
    <img class="heardImg" src="/demo/antiocn.png"  width="60%">
</div>

<div class="centerDiv">
    <img class="heardImg" src="/images/global/行为覆盖规则.jpg" width="30%">
</div>


### 用例级

在用例文件 rule->actions 中声明的 就是用例级行为
例如loadMysql查询资金账号、loadOracle查询资金账号
```xml copy
<?xml version="1.0" encoding="UTF-8"?>
<rule code = "DEMO_SQL2" name = "行为级参数声明" model="flow">
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
</rule>
```

### 系统级

🤔理论上我们每步 execute 执行的 actionCode 的编码都是要在用例文件 rule->actions 中声明的 但是[sleep 暂停休息 ](/ability/sleep.md#sleep)中的 sleep 为什么不需要声明呢？
这是因为我们为您已经为您配置了系统级行为sleep 
目前系统级行为只能通过系统管理员在数据库中以脚本的方式添加

```xml
<?xml version="1.0" encoding="UTF-8"?>
<rule code="DEMO_Sleep" name="sleep休息X秒" model="flow">
    <flows>
        <flow code="RU001G1" name="sleep休息1秒">
            <execute code="stp6" name="休息1秒" actionCode="sleep" sleepTime="1000"/>
        </flow>
    </flows>
</rule>


```
默认系统级行为
<div class="centerDiv">

| 编码                | 名称 |
| --------------------- | ---- |
| sleep              | 休眠   |
| check               | 校验   |
| checkObj              | 校验对象   |

</div>

### 项目级
如果您也有需要配置项目级别全局的 action 你可以创建[组件库的使用 ](/logDesc/1_0_2.md#组件库的使用)，那么你在当前项目中任何一个测试用例中都可以用到了

内置的默认项目配置了默认级行为
<div class="centerDiv">

| 编码                | 名称 |
| --------------------- | ---- |
| begin              | 事务开启   |
| commit               | 事务提交   |

</div>
