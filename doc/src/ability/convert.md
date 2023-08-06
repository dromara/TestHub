---
title: 🥭 convert 数据处理
order: 21
author: 失败女神
editLink: false
category:
  - 能力
---

## CONVERT

在自动化测试中，某些测试用例需要对结果进行特殊处理，例如在检查信息时，可以忽略与业务无关的字段（如 ID、创建时间等）。为了实现这一需求，我们需要具备数据处理的能力。通过数据处理，可以筛选出需要验证的关键字段，忽略不相关的数据，从而确保测试结果的准确性和可读性。这种能力提供了灵活性，使得测试用例的结果验证更加高效和可定制化。

<a name="特殊说明"></a>
**特殊说明**

数据处理的行为必须声明一个变量 source 用于接受需要被处理的数据，如果你的数据是一个列表我们将会增加一个 item 来表示每一个元素，如果你的数据不是列表你也需要通过 item 来对它进行操作。

convert 的 type 为 DEL 表示删除 PUT 表示新增或更新，同一个 converts 中 convert 的类型可以不同

| 语法              | 说明                               |
| ----------------- | ---------------------------------- |
| orderCode         | code                               |
| users[*] 或 users | 整个 users 列表                    |
| users[*].code     | users 列表中 每一个元素的 code     |
| users[0].name     | users 列表中 第 0 个元素的 name    |
| shows[0,1]        | shows 列表中第[0,1)个元素 前闭后开 |
| shows[0,1]        | shows 列表中第[0,1)个元素 前闭后开 |

```xml copy
<?xml version="1.0" encoding="UTF-8"?>
<rule code="DEMO_CONVERT" name="数据转换" model="flow">
    <actions>
        <action code="getList" name="常量列表" type="CONST" complex="1" dataType="MAP">
            <bound>
                [{"id":1,"orderCode":"123","orderQur":123,"stkId":"123","users":[{"code":"0001","name":"vinc","tags":[{"id":123,"name":"vinc","shows":[{"id":1,"name":"vinc"},{"id":2,"name":"vinc"},{"id":3,"name":"vinc"}]},{"id":124,"name":"vinc"}]},{"code":"0002","name":"崔胜利"}]},{"id":2,"orderCode":"123","orderQur":123,"stkId":"123","users":[{"code":"0001","name":"vinc"},{"code":"0002","name":"崔胜利"}]}]
            </bound>
        </action>
        <action code="shanchujieguo" name="移除后结果" type="CONST" complex="1" dataType="MAP">
            <bound>
                [{"id":1,"orderQur":123,"stkId":"123","users":[{"tags":[{"name":"vinc","shows":[{"id":2,"name":"vinc"},{"id":3,"name":"vinc"}]},{"name":"vinc"}]},{"name":"崔胜利"}]},{"id":2,"orderQur":123,"stkId":"123","users":[{},{"name":"崔胜利"}]}]
            </bound>
        </action>
        <action code="gengxinjieguo" name="更新后结果" type="CONST" complex="1" dataType="MAP">
            <bound>
                [{"id":1,"orderCode":"vinc","orderQur":123,"stkId":"123","users":[{"code":"0001","name":"vinc","tags":[{"id":123,"name":"vinc","shows":[{"id":1,"name":"vinc"},{"id":2,"name":"vinc"},{"id":3,"name":"vinc"}]},{"id":124,"name":"vinc"}]},{"code":"0002","name":"崔胜利"}]},{"id":2,"orderCode":"vinc","orderQur":123,"stkId":"123","users":[{"code":"0001","name":"vinc"},{"code":"0002","name":"崔胜利"}]}]
            </bound>
        </action>
        <action code="shanchu" name="删除测试" type="CONVERT" complex="1" dataType="MAP">
            <params>
                <param code="source" name="源数据" dataType="MAP" complex="1"/>
                <param code="name" name="名称" dataType="MAP" complex="1"/>
            </params>
            <converts>
                <convert code="orderCode" type="DEL" name="移除字段"/>
                <convert code="users[*].code" type="DEL" name="移除字段"/>
                <convert code="users[0].name" type="DEL" name="移除字段"/>
                <convert code="users[*].tags[*].id" type="DEL" name="移除字段"/>
                <convert code="users[*].tags[0].shows[0,1]" type="DEL" name="移除字段"/>
            </converts>
        </action>
        <action code="gengxin" name="更新字段" type="CONVERT" complex="1" dataType="MAP">
            <params>
                <param code="source" name="源数据" dataType="MAP" complex="1"/>
                <param code="name" name="名称" dataType="MAP" complex="1"/>
            </params>
            <converts>
                <convert code="orderCode" type="PUT" data="${name}" name="更新orderCode字段的值为名称的值"/>
                <convert code="orderCode1" type="PUT" data="${name}" name="在根上设置不存在的字段可以成功"/>
                <convert code="orderCode2.accc" type="PUT" data="${name}" name="层级结构中有非末级之有不存在，字段不存在设置不上去"/>
                <convert code="orderQur" type="PUT" data="%{sub(attr1:${item.orderQur},attr2:23)}" name="数量减少23"/>
                <convert code="users[1].code" type="PUT" data="10086" name="users列表中第一个元素的code设置为10086"/>
                <convert code="users[*].name" type="PUT" data="10086" name="users列表中第一个元素的code设置为10086"/>
                <convert code="data" type="PUT" data="%{getData(name:vinc,age:18)}" name="users列表中第一个元素的code设置为10086"/>
                <convert code="datas" type="PUT" data="%{getData(size:2,name:vinc,age:18)}" name="users列表中第一个元素的code设置为10086"/>
                <convert code="datas[1].age" type="PUT" data="1" name="users列表中第一个元素的code设置为10086"/>
            </converts>
        </action>
    </actions>
    <flows>
        <flow code="RU001G1">
            <execute code="stp1" name="获取常量常量列表" actionCode="getList"/>
            <execute code="stp2" name="获取移除后结果" actionCode="shanchujieguo"/>
            <execute code="stp3" name="删除一些字段" actionCode="shanchu">
                <injects>
                    <inject code="source" data="${stp1}"/>
                    <inject code="name" data="vinc"/>
                </injects>
            </execute>
            <execute code="stp4" name="更新一些字段" actionCode="gengxin">
                <injects>
                    <inject code="source" data="${stp1}"/>
                    <inject code="name" data="vinc"/>
                </injects>
            </execute>
            <execute code="stp5" name="获取更新后结果" actionCode="gengxinjieguo"/>
            <execute code="stp10" name="对比两个对象" actionCode="checkObj">
                <checkObj code="check1" name="对比stp3移除的结果" msg="对象不一样" cover="${stp3}" threshold="${stp2}"/>
                <checkObj code="check2" name="对比stp4更新的结果" msg="对象不一样" cover="${stp4}" threshold="${stp5}"/>
            </execute>
        </flow>
    </flows>
</rule>
```

<img class="heardImg" src="/demo/convert.png">
