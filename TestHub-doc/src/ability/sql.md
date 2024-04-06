---
title: 🍇 sql 操作数据库
order: 4
author: 失败女神
editLink: false
category:
  - 能力
---

## 前言

以下 sql、sql_begin、sql_commit、sql_call 需要声明 数据库 URL、驱动、用户名、密码。

```xml copy
<param code="username" name = "用户" dataType="STRING"  data="********"/>
<param code="password" name = "密码" dataType="STRING"  data="********"/>
```

超时时间单位为毫秒，如果你没有指定我们将默认 为 1000

```xml copy
<param code="timeout" name="超时时间" dataType="NUMBER" data="1000"/>
```

## 已支持数据库

我们目前已经支持了下列数据库如果您需要操作其他数据库，请于<a href="https://gitee.com/failedgoddess/TestHub/issues">gitee</a>仓库提 lssues，我们会优先支持。不同数据库的驱动信息如下：

::: code-tabs#xml

@tab Mysql

```xml copy
<param code="url" name = "数据库地址" dataType="STRING"  data="jdbc:mysql://127.0.0.1:3306/spring-boot-demo"/>
<param code="driver" name = "驱动" dataType="STRING"  data="com.mysql.cj.jdbc.Driver"/>
```

@tab:active PGsql

```xml copy
<param code="url" name = "数据库地址" dataType="STRING"  data="jdbc:postgresql://192.168.0.145:5432/postgres"/>
<param code="driver" name = "驱动" dataType="STRING"  data="org.postgresql.Driver"/>
```

@tab Orecle

```xml copy
<param code="url" name = "数据库地址" dataType="STRING"  data="jdbc:oracle:thin:@172.20.0.7:1521/ctsdb"/>
<param code="driver" name = "驱动" dataType="STRING"  data="oracle.jdbc.driver.OracleDriver"/>
```

@tab Sql Server

```xml copy
<param code="url" name="数据库地址" dataType="STRING" data="jdbc:sqlserver://127.0.0.1:1433;encrypt=true;trustServerCertificate=true;databaseName=test"/>
<param code="driver" name="驱动" dataType="STRING" data="com.microsoft.sqlserver.jdbc.SQLServerDriver"/>
```

:::

## SQL 执行 sql

例如 select 等查询类 sql 等不涉及事务的 sql 可以直接使用 SQL，如果涉及到 UPDATE、DELETE、INSERT 等 DMI 类型的 sql 需要结合 SQL_Begin 和 SQL_commit 完成 DEMO 移步更新删除添加 SQL

事务说明

```xml copy
<?xml version="1.0" encoding="UTF-8"?>
<rule code="DEMO_SQL" name="查询类SQL" model="flow">
    <params>
        <param code="acctId" name="资金账号ID" dataType="STRING" data="960307"/>
        <param code="url" name="数据库地址" dataType="STRING" data="jdbc:postgresql://192.168.0.5:5432/postgres?currentSchema=public"/>
        <param code="driver" name="驱动" dataType="STRING" data="org.postgresql.Driver"/>
        <param code="username" name="用户" dataType="STRING" data="postgres"/>
        <param code="password" name="密码" dataType="STRING" data="postgres"/>
        <param code="timeout" name="超时时间" dataType="NUMBER" data="1000"/>
    </params>
    <actions>
        <action code="loadAccount" name="查询资金账号" type="SQL" dataType="MAP">
            <bound>
                SELECT * FROM test_account where id = ${acctId}
            </bound>
        </action>
    </actions>
    <flows>
        <flow code="RU001G1">
            <execute code="stp1" name="查询账户" actionCode="loadAccount"/>
        </flow>
    </flows>
</rule>
```

<img class="heardImg" src="/demo/sql.png">

## SQL_BEGIN 开启事务

::: tip 特殊提示
begin 已经是全局的配置，不需要单独定义 action
:::

**_关于 sql、sql_begin、sql_commit 中事务 ID 的说明_**

```flow
cond=>condition: 是否指定事务ID?
process=>operation: 指定的事务ID
e=>end: conKey = conKey = execute中code的值

cond(yes)->process->e
cond(no)->e
```

```xml copy
<?xml version="1.0" encoding="UTF-8"?>
<rule code="DEMO_SQL_Begin" name="开启事务SQL" model="flow">
    <params>
        <param code="url" name="数据库地址" dataType="STRING" data="jdbc:postgresql://192.168.0.5:5432/postgres?currentSchema=public"/>
        <param code="driver" name="驱动" dataType="STRING" data="org.postgresql.Driver"/>
        <param code="username" name="用户" dataType="STRING" data="postgres"/>
        <param code="password" name="密码" dataType="STRING" data="postgres"/>
        <param code="timeout" name="超时时间" dataType="NUMBER" data="1000"/>
    </params>
    <flows>
        <flow code="RU001G1">
            <execute code="stp5" name="开启事务" actionCode="begin"/>
        </flow>
    </flows>
</rule>
```

## SQL_COMMIT 提交事务

::: tip 特殊提示
begin 已经是全局的配置，不需要单独定义 action
:::

```xml copy
<?xml version="1.0" encoding="UTF-8"?>
<rule code="DEMO_SQL_commit" name="事务提交SQL" model="flow">
    <params>
        <param code="url" name="数据库地址" dataType="STRING" data="jdbc:postgresql://192.168.0.5:5432/postgres?currentSchema=public"/>
        <param code="driver" name="驱动" dataType="STRING" data="org.postgresql.Driver"/>
        <param code="username" name="用户" dataType="STRING" data="postgres"/>
        <param code="password" name="密码" dataType="STRING" data="postgres"/>
        <param code="timeout" name="超时时间" dataType="NUMBER" data="1000"/>
    </params>
    <flows>
        <flow code="RU001G1">
            <execute code="stp7" name="提交事务" actionCode="commit"/>
        </flow>
    </flows>
</rule>
```

## SQL_CALL 执行存储过程

```xml copy
<?xml version="1.0" encoding="UTF-8"?>
<rule code = "DEMO_SQLCALL" name = "存储过程" model="flow">
    <actions>
        <action code ="loadAccount" name = "调用存储过程" type = "SQL_CALL" dataType="MAP">
            <params>
                <param code="url" name = "数据库地址" dataType="STRING"  data="jdbc:postgresql://127.0.0.1:5433/cts_db"/>
                <param code="driver" name = "驱动" dataType="STRING"  data="org.postgresql.Driver"/>
                <param code="username" name = "用户" dataType="STRING"  data="postgres"/>
                <param code="password" name = "密码" dataType="STRING"  data="goddess_root"/>
            </params>
            <bound>
                select mycount3() as number
            </bound>
        </action>
    </actions>
    <flows>
        <flow code = "RU001G1" >
            <execute code="stp1" name="调用存储过程" actionCode="loadAccount" conKey="stp1"/>
        </flow>
    </flows>
</rule>
```

## 完整 DMI 操作

```xml copy
<?xml version="1.0" encoding="UTF-8"?>
<rule code="DEMO_SQLDMI" name="DMI类型SQL" model="flow">
    <params>
        <param code="yue" name="余额" dataType="NUMBER" data="999"/>
        <param code="url" name="数据库地址" dataType="STRING" data="jdbc:postgresql://192.168.0.5:5432/postgres?currentSchema=public"/>
        <param code="driver" name="驱动" dataType="STRING" data="org.postgresql.Driver"/>
        <param code="username" name="用户" dataType="STRING" data="postgres"/>
        <param code="password" name="密码" dataType="STRING" data="postgres"/>
        <param code="timeout" name="超时时间" dataType="NUMBER" data="1000"/>
    </params>
    <actions>
        <action code="upAccount" name="更新资金账号可用金额" type="SQL" dataType="MAP">
            <bound>
                UPDATE test_account set usable_amt = ${yue} WHERE id = 960307
            </bound>
        </action>
        <action code="loadAccount" name="查询资金账号" type="SQL" dataType="MAP">
            <params>
                <param code="acctId" name="账号" dataType="STRING"/>
            </params>
            <bound>
                SELECT * FROM test_account where id = ${acctId}
            </bound>
        </action>
    </actions>
    <flows>
        <flow code="RU001G1">
            <execute code="stp1" name="事务开启" actionCode="begin" conKey="con001"/>
            <execute code="stp2" name="更新资金账号可用金额为999" actionCode="upAccount" conKey="con001"/>
            <execute code="stp3" name="事务提交" actionCode="commit" conKey="con001"/>
            <execute code="stp4" name="查询资金账号" actionCode="loadAccount">
                <injects>
                    <inject code="acctId" data="960307"/>
                </injects>
            </execute>
            <execute code="stp5" name="检查资金账号余额" actionCode="check">
                <checkItem code="check1" name="检查资金账号余额" msg="余额不对">
                    <expression expressionType="relation" operationCode="eq" dataType="NUMBER" cover="${yue}" threshold="${stp4.usable_amt}"/>
                </checkItem>
            </execute>
        </flow>
    </flows>
</rule>
```

<img class="heardImg" src="/demo/sqldmi.png">
